package srmicrosystems.cnote;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.cie.btp.Barcode;
import com.cie.btp.CieBluetoothPrinter;
import com.cie.btp.PrinterWidth;

import java.io.File;
import java.net.URI;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.CNNoteDetailsExt;
import srmicrosystems.cnote.Repository.CNNoteRepo;
import srmicrosystems.cnote.Service.ServiceHub;
import srmicrosystems.cnote.Utils.PrintSrvMsgHandler;
import srmicrosystems.cnote.databinding.ActivityCnnoteExtDetailsBinding;

public class CNNoteExtDetailsActivity extends AppCompatActivity {
    ProgressDialog pd ;
    Context ctx;
    public static final int SIGNATURE_ACTIVITY = 1;
    String CNN;
    ImageView imgRecBy;
    Button recby;

    public static CieBluetoothPrinter mPrinter = CieBluetoothPrinter.INSTANCE;
     Messenger mMessenger;
    private String mConnectedDeviceName = "";
    public static final String title_connecting = "connecting...";
    public static final String title_connected_to = "connected: ";
    public static final String title_not_connected = "not connected";
    private ToggleButton tbPrinter;
    private TextView statusMsg;
     CNNoteDetailsExt data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd= new ProgressDialog(this);
        pd.setIndeterminate(false);
        pd.incrementProgressBy(10);
        pd.setMessage("Getting Data from server");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER );
        pd.show();
        ctx = this;
        TextView tv = new TextView(ctx);

        mMessenger = new Messenger(new PrintSrvMsgHandler( tv,ctx));
        ActivityCnnoteExtDetailsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_cnnote_ext_details);
        CNNoteDetailsExt cn  = new CNNoteDetailsExt();
        CNN = getIntent().getExtras().get("CNNNumber").toString();
        CNNoteRepo CNNRepo = new CNNoteRepo();
        try {
          //  data= CNNRepo.GetCNNoteDetailsExt(this,CNN);
           data = CNNRepo.GetCNNoteDetailsExtSync(this,CNN);
            if (data == null) {
                pd.setMessage("CNote Not Found ");
                Toast.makeText(this,"CNote Not Found ",Toast.LENGTH_LONG);
                return;
            }


        }
        catch (Exception ex)
        {
           String s=ex.getMessage();
            Log.d("Error",ex.getMessage());
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG);
        }

        ImageView imgHBy = (ImageView) findViewById(R.id.imgHBy);
         imgRecBy = (ImageView) findViewById(R.id.imgRecBy);
       // File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "SSCPL");
                //String path =       pdfFolder+"/" + data.getCNNumber() + ".jpg";

        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator  + data.getCNNumber() + ".jpg");
        File file1 = new File(Environment.getExternalStorageDirectory()
                + File.separator +"D_" + data.getCNNumber() + ".jpg");

      //  String path1 =       pdfFolder+"/" + "D_"+data.getCNNumber() + ".jpg";
           try {
             //  URI uri = new URI(path);


               imgHBy.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
           }
           catch (Exception ex)
           {

           }

        try {
            URI uri = new URI(file1.getPath());
            File file2 = new File(file1.getPath());
            Bitmap s =null;
            if( file2.exists()){
            s =  BitmapFactory.decodeFile(file2.getPath());}
             recby = (Button) findViewById(R.id.btnRecBYSign);

if (file1.exists() && s != null )
{
    imgRecBy.setImageBitmap(BitmapFactory.decodeFile(file1.getPath()));
    recby.setVisibility(View.INVISIBLE);

}

else {
    recby.setVisibility(View.VISIBLE);


    recby.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(ctx, CaptureSignature.class);
            intent.putExtra("CNN","D_"+ data.getCNNumber());
            startActivityForResult(intent, SIGNATURE_ACTIVITY);
        }
    });

            }


        }
        catch (Exception ex)
        {

        }


       // Glide.with(this).load(data.getSignImageURL()).into(imgHBy);
        //Glide.with(this).load(data.getSignImageDeliveredURL()).into(imgRecBy);
        binding.setCn(data);

        pd.hide();

        Button btnPrint = (Button) (findViewById(R.id.btnprint))  ;


        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Print();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                   String tmpHandedBy = (String) bundle.get("HandedBy");
                    if(status.equalsIgnoreCase("done")){
                        String url = bundle.getString("path");
                        //    File file = new File(url);
                        //   File file = new File("/storage/emulated/0/Pictures/"+CNNumber+".jpg");
                        File file = new File(Environment.getExternalStorageDirectory()
                                + File.separator +  "D_" + CNN + ".jpg");

                        imgRecBy.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                        recby.setVisibility(View.INVISIBLE);
                        RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("picture", file.getName(), fbody);

                        String descriptionString = "hello, this is description speaking";
                        RequestBody description =
                                RequestBody.create(
                                        MediaType.parse("multipart/form-data"), descriptionString);

                       /*
                        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), AZUtils.getUserId(this));*/
                        Call<ResponseBody> call = ServiceHub.createRetrofitService().Upload(description, body);
                        call.enqueue(new Callback<ResponseBody>() {

                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    String resp = response.message();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                call.isExecuted();
                            }
                        });


                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
        }

    }

    public void Print(){
        final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter == null) {
            Toast.makeText(this, "Not Supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //un comment the line below to debug the print service
        //mPrinter.setDebugService(BuildConfig.DEBUG);
        try {
            if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED)
            {  if ( mPrinter.initService(ctx, mMessenger)== true)
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

     //   Button btnprint = (Button) findViewById(R.id.btnprint);
      //  btnprint.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
                mPrinter.setHighIntensity();
                mPrinter.setStyleCourier();
               /* Uri fileUri = Uri.parse("/storage/emulated/0/Download/SSCPL LOGO1.jpg");
                Boolean r = mPrinter.printDirect(fileUri.getPath(),true,134);*/
                boolean b1=   mPrinter.printSavedImage(1);
                mPrinter.setFondSizeMedium();
                mPrinter.printTextLine(data.getTransportMode() + "              ");
               // mPrinter.printTextLine("AIR              ");
               // mPrinter.printTextLine("SRT-BOM\n");
                mPrinter.printTextLine(data.getSourceCityCode() + "-"+ data.getDestCityCode() + "\n");
                mPrinter.setFondSizeSmall();
                mPrinter.printLineFeed();

             //   mPrinter.printBarcode("2016120912", Barcode.CODE_128,384,100);
                mPrinter.printBarcode(data.getCNNumber(), Barcode.CODE_128,384,100);
                mPrinter.setAlignmentLeft();
               // mPrinter.printTextLine(" AWB#:  201612091212121   ");
                mPrinter.printTextLine(" AWB#:  " + data.getCNNumber());

                mPrinter.printTextLine("   Date: 11-09-2016\n");




                mPrinter.setFondSizeSmall();
                mPrinter.printLineFeed();
                mPrinter.printLineFeed();
                mPrinter.setAlignmentLeft();
                mPrinter.printTextLine("Consinger: \n");

                mPrinter.setBold();
               // mPrinter.printTextLine("Fed Ex Shipment Pvt Ltd. Surat \n");
                mPrinter.printTextLine(data.getShipperCompany() + "\n");
                mPrinter.setRegular();
                mPrinter.setStyleFixedsys();
                //mPrinter.printTextLine("4 1240, Begampura Rd, Begampura, Surat, Gujarat 395003 \n");
                mPrinter.printTextLine( data.getShipperCompanyAddress() + "\n");

                mPrinter.printLineFeed();
                mPrinter.printTextLine("------------------------------------------------\n");
                mPrinter.setBold();
                mPrinter.setStyleCourier();
                mPrinter.printTextLine("Consignee: \n");


             //   mPrinter.printTextLine("Fed Ex Shipment Pvt Ltd. Mumbai \n");
                mPrinter.printTextLine(data.getConsigneeCompany() + "\n");
                mPrinter.setRegular();
                mPrinter.setStyleFixedsys();
                //mPrinter.printTextLine(" 8th Floor, Unit No 801, Boomerang, Wings A & B1, Chandivali Farm Road, Near Chandivali Studio, Andheri (E), Mumbai, Maharashtra 400072 \n");
                mPrinter.printTextLine( data.getConsigneeCompanyAddress() + "\n");

                mPrinter.printLineFeed();
                mPrinter.printTextLine("------------------------------------------------\n");
                mPrinter.setStyleCourier();
                mPrinter.setRegular();
                mPrinter.printTextLine("No of Packages: ");
                mPrinter.setBold();
                //mPrinter.printTextLine("100\n");
                mPrinter.printTextLine( data.getPackageNo() + "\n");
                mPrinter.setRegular();


                mPrinter.setRegular();
                mPrinter.printTextLine("Consingment Weight: ");
                mPrinter.setBold();
              //  mPrinter.printTextLine("100\n");
                mPrinter.printTextLine( data.getConsignmentWeight() + "\n");
                mPrinter.setRegular();
                mPrinter.printTextLine("Mode Of Packing: ");
                mPrinter.setBold();
                mPrinter.printTextLine( " \n");
                mPrinter.printLineFeed();
        mPrinter.setRegular();
        mPrinter.printTextLine("Handed By: ");
        mPrinter.setBold();
        mPrinter.printTextLine( data.getHandedBy() +  "\n");
                mPrinter.setBold();
                mPrinter.printTextLine("Signature \n");

          //      Uri fileUri1 = Uri.parse("/storage/emulated/0/20160911042047.jpg");
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator +   CNN + ".jpg");
                Uri fileUri1 = Uri.parse(file.getPath());
                Boolean r1 = mPrinter.printDirect(fileUri1.getPath(),true,134);


                mPrinter.printLineFeed();
        mPrinter.setBold();
        mPrinter.setBold();
        mPrinter.printTextLine("Recieved By: ");

        mPrinter.printTextLine("Signature \n");
        File file1 = new File(Environment.getExternalStorageDirectory()
                + File.separator +  "D_" + CNN + ".jpg");
        Uri fileUri2 = Uri.parse(file1.getPath());
        Boolean r2 = mPrinter.printDirect(fileUri2.getPath(),true,134);

                mPrinter.printLineFeed();
                mPrinter.printLineFeed();
      //      }
      //  });

    }


}
