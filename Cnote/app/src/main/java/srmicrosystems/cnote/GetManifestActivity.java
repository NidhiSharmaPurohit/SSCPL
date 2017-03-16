package srmicrosystems.cnote;

import android.app.Activity;
import android.app.Notification;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cie.btp.CieBluetoothPrinter;
import com.cie.btp.PrinterWidth;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.File;
import java.util.List;

import srmicrosystems.cnote.Model.CNNoteDetailsExt;
import srmicrosystems.cnote.Repository.CNNoteRepo;
import srmicrosystems.cnote.Utils.PrintSrvMsgHandler;

/**
 * Created by saman_000 on 09-10-2016.
 */
public class GetManifestActivity extends Activity {

    Context context;
    CNNoteRepo cnnRepo;
    private TextView statusMessage;
    private TextView barcodeValue;
    private ImageButton barcode ;
    EditText MID;
    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";
    ProgressDialog pd ;
    public static CieBluetoothPrinter mPrinter = CieBluetoothPrinter.INSTANCE;
    Messenger mMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context  =this;
        setContentView(R.layout.activity_getmenifestconf);
        Button btn = (Button) findViewById(R.id.btnSearchMenifest);
        MID = (EditText) findViewById(R.id.MenifestId);
        statusMessage = (TextView)findViewById(R.id.Mstatus_message);

        pd= new ProgressDialog(this);
        pd.setIndeterminate(false);
        pd.incrementProgressBy(10);
        pd.setMessage("Getting Data from server");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER );

        TextView tv = new TextView(context);

        mMessenger = new Messenger(new PrintSrvMsgHandler( tv,context));



        final Button btnPrint = (Button) findViewById(R.id.btnPrintAll);
        btnPrint.setEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
try{

    cnnRepo = new CNNoteRepo();
    cnnRepo.SaveCNNNotDetailsExt(MID.getText().toString(),context);
    //Intent in=new Intent(GetManifestActivity.this ,srmicrosystems.cnote.MainActivity.class);
    //startActivity(in);
    btnPrint.setEnabled(true);
} catch (Exception ex)
{
    String s = ex.getMessage();
}

            }
        });


        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    cnnRepo = new CNNoteRepo();
                    List<CNNoteDetailsExt> result = cnnRepo.GetListOfCNNoteDetailsExt(context,MID.getText().toString());

                    PrintAll(result);



                } catch (Exception ex)
                {
                    String s = ex.getMessage();
                }

            }
        });

        barcode = (ImageButton) findViewById(R.id.Manifestread_barcode);
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.Manifestread_barcode) {
                    // launch barcode activity.
                    Intent intent = new Intent(context, BarcodeCaptureActivity.class);
                    intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                    intent.putExtra(BarcodeCaptureActivity.UseFlash, true);

                    startActivityForResult(intent, RC_BARCODE_CAPTURE);
                }

            }
        });




        Button btn1 = (Button) findViewById(R.id.viewImg);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try{
                ImageView img1 = (ImageView) findViewById(R.id.img1);
                Glide.with(context).load("http://52.11.236.231/uploadCNNOtesSignImage/20160831044119.jpg")
                        .into(img1)
                    ;
            }
            catch (Exception ex){
                String s = ex.getMessage();
            }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);
                    MID.setText(barcode.displayValue);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String getblankspace(String left, String right)
    {
        String result = "";
        int resultlenght = 24 - (left.length() + right.length());
        for(int i=0; i <resultlenght; i++){
            result = result + " ";
        }
        return  result;
    }

    private  void PrintAll(List<CNNoteDetailsExt> result){

        final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter == null) {
            Toast.makeText(this, "Not Supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //un comment the line below to debug the print service
        //mPrinter.setDebugService(BuildConfig.DEBUG);
        try {
            if ( mPrinter.getState()!= CieBluetoothPrinter.STATE_CONNECTED)
            {  if ( mPrinter.initService(context, mMessenger)== true)
            {

            }
            else
            if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED){
                mPrinter.showDeviceList(this);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPrinter.setPrinterWidth(PrinterWidth.PRINT_WIDTH_72MM);


        mPrinter.setHighIntensity();
        mPrinter.setStyleCourier();
        File logoFolder = new File(Environment.getExternalStorageDirectory().getPath().toString());
        String path = logoFolder + File.separator + "sscpl.jpg";

        for(CNNoteDetailsExt cnn : result){

            mPrinter.printDirect(path, true, 127);
            mPrinter.printTextLine("\n");

            //boolean b1=   mPrinter.printSavedImage(1);
            mPrinter.setFondSizeMedium();
            mPrinter.printTextLine(cnn.getTransportMode() + getblankspace(cnn.getTransportMode() , cnn.getSourceCityCode() + "-"+ cnn.getDestCityCode()));

            mPrinter.printTextLine(cnn.getSourceCityCode() + "-"+ cnn.getDestCityCode() + "\n");
            mPrinter.setFondSizeSmall();
            mPrinter.printLineFeed();

            mPrinter.printBarcode(cnn.getCNNumber(), com.cie.btp.Barcode.CODE_128,384,100);
            mPrinter.setAlignmentLeft();

            mPrinter.printTextLine(" AWB#:  " + cnn.getCNNumber());

            mPrinter.printTextLine("   Date: " + cnn.getBookingDate().substring(0,10) +"\n");




            mPrinter.setFondSizeSmall();
            mPrinter.printLineFeed();
            mPrinter.printLineFeed();
            mPrinter.setAlignmentLeft();
            mPrinter.printTextLine("Consinger: \n");

            mPrinter.setBold();

            mPrinter.printTextLine(cnn.getShipperCompany() + "\n");
            mPrinter.setRegular();
            mPrinter.setStyleFixedsys();

            mPrinter.printTextLine( cnn.getShipperCompanyAddress() + "\n");

            mPrinter.printLineFeed();
            mPrinter.printTextLine("------------------------------------------------\n");
            mPrinter.setBold();
            mPrinter.setStyleCourier();
            mPrinter.printTextLine("Consignee: \n");


            //   mPrinter.printTextLine("Fed Ex Shipment Pvt Ltd. Mumbai \n");
            mPrinter.printTextLine(cnn.getConsigneeCompany() + "\n");
            mPrinter.setRegular();
            mPrinter.setStyleFixedsys();

            mPrinter.printTextLine( cnn.getConsigneeCompanyAddress() + "\n");

            mPrinter.printLineFeed();
            mPrinter.printTextLine("------------------------------------------------\n");
            mPrinter.setStyleCourier();
            mPrinter.setRegular();
            mPrinter.printTextLine("No of Packages: ");
            mPrinter.setBold();

            mPrinter.printTextLine( cnn.getPackageNo() + "\n");
            mPrinter.setRegular();


            mPrinter.setRegular();
            mPrinter.printTextLine("Consingment Weight: ");
            mPrinter.setBold();

            mPrinter.printTextLine( cnn.getConsignmentWeight() + "\n");
            mPrinter.setRegular();
            mPrinter.printTextLine("Mode Of Packing: ");
            mPrinter.setBold();
            mPrinter.printTextLine( cnn.getPayMode() + "\n");
            mPrinter.printLineFeed();
            mPrinter.setRegular();
            mPrinter.printTextLine("Handed By: ");
            mPrinter.setBold();
            mPrinter.printTextLine( cnn.getHandedBy() +  "\n");
            mPrinter.setBold();
            mPrinter.printTextLine("Signature \n");

            //      Uri fileUri1 = Uri.parse("/storage/emulated/0/20160911042047.jpg");
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "GetSignature"
                    + File.separator +   cnn.getCNNumber() + ".jpg");
            Uri fileUri1 = Uri.parse(file.getPath());
            Boolean r1 = mPrinter.printDirect(fileUri1.getPath(),true,134);


            mPrinter.printLineFeed();
            mPrinter.setBold();
            mPrinter.setBold();
            mPrinter.printTextLine("Recieved By: " + cnn.getReceivedBy() + "\n");
            mPrinter.printTextLine("\n");
            mPrinter.printTextLine("Signature \n");
            File file1 = new File(Environment.getExternalStorageDirectory() + File.separator + "GetSignature"
                    + File.separator +  "D_" + cnn.getCNNumber() + ".jpg");
            Uri fileUri2 = Uri.parse(file1.getPath());
            Boolean r2 = mPrinter.printDirect(fileUri2.getPath(),true,134);
            mPrinter.printTextLine("\n");
            mPrinter.printTextLine("\n");
            mPrinter.printTextLine("\n");

            mPrinter.printLineFeed();
            mPrinter.printLineFeed();

            mPrinter.printTextLine("\n");
            mPrinter.printTextLine("\n");
            mPrinter.printTextLine("\n");
            mPrinter.printTextLine("\n");

        }

    }
}
