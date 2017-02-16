package srmicrosystems.cnote;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cie.btp.Barcode;
import com.cie.btp.CieBluetoothPrinter;
import com.cie.btp.PrinterWidth;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import srmicrosystems.cnote.Model.Adapters.TranportModeSpinerADP;
import srmicrosystems.cnote.Model.Adapters.PackagingModeSpinerADP;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.CNNoteDetailsExt;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.Company;
import srmicrosystems.cnote.Model.PackagingMode;
import srmicrosystems.cnote.Model.SQL.CNNoteSQLHelper;
import srmicrosystems.cnote.Model.TransportMode;
import srmicrosystems.cnote.Repository.CNNoteRepo;
import srmicrosystems.cnote.Repository.CompADP;
import srmicrosystems.cnote.Repository.CompRepo;
import srmicrosystems.cnote.Repository.PackagingModeRepo;
import srmicrosystems.cnote.Repository.TransportModeRepo;
import srmicrosystems.cnote.Service.ServiceHub;
import srmicrosystems.cnote.Repository.CityRepo;
import srmicrosystems.cnote.Utils.PrintSrvMsgHandler;

import static srmicrosystems.cnote.Service.ServiceHub.GetjsonData;

/**
 * Created by saman_000 on 10-05-2016.
 */


public class CnoteActivity extends BaseActivity {
    AutoCompleteTextView textView ;// = (AutoCompleteTextView) findViewById(R.id.ConsignerName);
    Callback<CNNote> cbcn ;
    public static final int SIGNATURE_ACTIVITY = 1;
    CompADP adp;
    ProgressDialog pd ;
    TranportModeSpinerADP tmadp ;
    PackagingModeSpinerADP pmadp;
    CNNote tmpCN = new CNNote();
    String CNNumber;
       ArrayList<TransportMode>  tm;
    ArrayList<PackagingMode> pm;
    Context current;
    Date date ;//= Calendar.getInstance().getTime();
    CNNoteDetailsExt data = new CNNoteDetailsExt();
    public String tmpHandedBy;
    SQLiteOpenHelper CNnoteHelper;
    public static CieBluetoothPrinter mPrinter = CieBluetoothPrinter.INSTANCE;
    Messenger mMessenger;
    private String mConnectedDeviceName = "";
    public static final String title_connecting = "connecting...";
    public static final String title_connected_to = "connected: ";
    public static final String title_not_connected = "not connected";
    private ToggleButton tbPrinter;
    private TextView statusMsg;


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnnote);
        pd= new ProgressDialog(this);
        pd.setIndeterminate(false);
        pd.incrementProgressBy(10);
        pd.setMessage("Getting Data from server");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER );

        ctx = this;
        TextView tv = new TextView(ctx);

        mMessenger = new Messenger(new PrintSrvMsgHandler( tv,ctx));
        current= this;
        CNnoteHelper = new CNNoteSQLHelper(this);

        date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        CNNumber=sdf.format(date);

        Button getSignature = (Button) findViewById(R.id.btnSign);
        getSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CnoteActivity.this, CaptureSignature.class);
                intent.putExtra("CNN",CNNumber);
                startActivityForResult(intent,SIGNATURE_ACTIVITY);
            }
        });


//CityRepo cr = new CityRepo();
        //ArrayList<String> lstCity = cr.GetCity(this);
        CompRepo cr = new CompRepo();
        /*ArrayList<String> lstComp= cr.GetComp(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lstComp);*/
        AutoCompleteTextView Congs = (AutoCompleteTextView) findViewById(R.id.ConsignerName);
        AutoCompleteTextView Congsinee = (AutoCompleteTextView) findViewById(R.id.ConsigneeName);
        //Congs.setAdapter(adapter);

        ArrayList<Company> com = cr.GetAllComp(this);
     //   AutoCompleteTextView etProductSearch = (AutoCompleteTextView)getView().findViewById(R.id.edtSearchBoxTakeOrder);
         adp = new CompADP(this, android.R.layout.select_dialog_singlechoice, com);
        //etProductSearch.setAdapter(adapter );
        Congs.setAdapter(adp);
        Congsinee.setAdapter(adp);

        TransportModeRepo tmr  = new TransportModeRepo();

        tm=tmr.GetCarrierCached(this);
        tmadp = new TranportModeSpinerADP(current,R.layout.support_simple_spinner_dropdown_item,tm);
        final Spinner tmMode = (Spinner) findViewById(R.id.spntm);
        tmMode.setAdapter(tmadp);

        PackagingModeRepo pmr = new PackagingModeRepo();
        pm=pmr.GetPackagingModeCached(this);
        pmadp = new PackagingModeSpinerADP(current,R.layout.support_simple_spinner_dropdown_item,pm);
        final Spinner pmMode = (Spinner) findViewById(R.id.spnpm);
        pmMode.setAdapter(pmadp);

        Congs.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Company c =  adp.getItem(position);
                String add =c.getCompanyAddress();

                EditText ed = (EditText) findViewById(R.id.ConsignerAddress);
                ed.setText(c.getCompanyAddress() + "," + c.getCompanyCity());
                data.setShipperCompany(c.getCompanyName());
                data.setShipperCompanyAddress(c.getCompanyAddress());
                data.setSourceCity(c.getCompanyCity());
                CityRepo cr = new CityRepo();

                data.setSourceCityCode( cr.GetCityById(ctx,c.GetCityId()).getCityCode());


                tmpCN.setShipperCompId( c.getCompanyId());

                tmpCN.setOriginCityID(c.GetCityId());
            }
        });


        Congsinee.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Company c =  adp.getItem(position);
                String add =c.getCompanyAddress();

                EditText ed = (EditText) findViewById(R.id.ConsigneeAddress);
                ed.setText(c.getCompanyAddress() + "," + c.getCompanyCity());
                tmpCN.setConsigneeCompId( c.getCompanyId());
                tmpCN.setDestCityID(c.GetCityId());

                data.setConsigneeCompany(c.getCompanyName());
                data.setConsigneeCompanyAddress(c.getCompanyAddress());
                data.setDestCity(c.getCompanyCity());
                CityRepo cr = new CityRepo();
                data.setDestCityCode(cr.GetCityById(ctx,c.GetCityId()).getCityCode());

            }
        });

Button btnPrint = (Button) findViewById(R.id.btnPrint);
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Print();
            }
        });

       final Button btnsub = (Button) findViewById(R.id.btnSubmit);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
               // CNNote tmpCN = new CNNote();
                EditText aw = (EditText) findViewById(R.id.ACW);
                EditText pn = (EditText) findViewById(R.id.PacakgeNo);
                EditText md = (EditText) findViewById(R.id.MD);
               tmpCN.ActualWight =   aw.getText().toString();
                data.setActualWeight(tmpCN.getActualWight());
                tmpCN.ConsignmentWeight = Integer.parseInt( aw.getText().toString());
                data.setConsignmentWeight( String.valueOf( tmpCN.getConsignmentWeight()));
                tmpCN.setPackageNo( Integer.parseInt( pn.getText().toString()));
                data.setPackageNo(pn.getText().toString());
                String s,s1;

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                s = sdf1.format(date);
                tmpCN.BookingDate = s;
                data.setBookingDate(s.toString());
                tmpCN.CenterID=1;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                tmpCN.CNNumber=CNNumber;
                data.setCNNumber(CNNumber);
              //  CNNumber = tmpCN.CNNumber;
               // tmpCN.ConsigneeCompId=2;
               // 'remove hc'
               // tmpCN.ConsignmentWeight=200;
                //tmpCN.DestCityID=2;
                tmpCN.ModeID=1;
                data.setModeID("1");

                //TransportModeRepo tmr = new TransportModeRepo();
                Spinner transportspinner = (Spinner)findViewById(R.id.spntm);
                int tmposition = transportspinner.getSelectedItemPosition();
                TransportMode transportmode = tmadp.getItem(tmposition);
                tmpCN.ModeID = Integer.parseInt(transportmode.getModelId().toString());
                data.setModeID(transportmode.getModelId().toString());
                //ArrayList<TransportMode> tm= tmr.GetCarrierCached(ctx);
               /* for (tmp:tm) {
                    if(tmp.)
                }   tm*/
                data.setTransportMode(transportmode.getModee().toString());
                tmpCN.MaterialDesc=md.getText().toString();
                //tmpCN.setShipperCompId(3);
                //tmpCN.setOriginCityID(1);
                tmpCN.setMaterialDesc(md.getText().toString());
                tmpCN.setToPayMode(1);

                Spinner paymodespinner = (Spinner)findViewById(R.id.spnpm);
                int pmposition = paymodespinner.getSelectedItemPosition();
                PackagingMode paymenttmode = pmadp.getItem(pmposition);
                tmpCN.setToPayMode(Integer.parseInt(paymenttmode.getId().toString()));
                data.setToPayMode(paymenttmode.getId().toString());
                data.setPayMode(paymenttmode.getTypee().toString());

                tmpCN.HandedBy =tmpHandedBy;
                data.setHandedBy(tmpHandedBy);
                data.setMaterialDesc(md.getText().toString());
               CNNoteRepo cr1 = new CNNoteRepo();


           /* public static void InsertCNNOte(SQLiteDatabase sqLiteDatabase,   String CNNumber, String BookingDate
                    ,  int PackageNo
                    ,  int ModeID
                    , String ActualWight
                    ,  double ConsignmentWeight
                    ,  String MaterialDesc
                    ,  int ShipperCompId
                    ,  int ConsigneeCompId
                    ,  int OriginCityID
                    ,  int DestCityID
                    , int ToPayMode
                    ,  double ServiceTax
                    ,  double TOTAL
                    ,  int CenterID , String Remarks, String Status)*/
               try{

                   //CNNoteSQLHelper cnh = new CNNoteSQLHelper(current);
                   //cnh.onCreate(CNnoteHelper.getWritableDatabase());
                   //String[] db=      CNNoteSQLHelper.getAppCategoryDetail(CNnoteHelper.getReadableDatabase());
                  int rid=  CNNoteSQLHelper.InsertCNNOte(CNnoteHelper.getWritableDatabase(),tmpCN.CNNumber,tmpCN.BookingDate, tmpCN.PackageNo,tmpCN.ModeID,tmpCN.ActualWight,tmpCN.ConsignmentWeight,
                           tmpCN.MaterialDesc,tmpCN.ShipperCompId,tmpCN.ConsigneeCompId,tmpCN.OriginCityID,tmpCN.DestCityID,tmpCN.ToPayMode,tmpCN.ServiceTax,tmpCN.TOTAL,tmpCN.CenterID,tmpCN.Remarks,tmpCN.HandedBy,
                           "Initial");
                   Toast.makeText(current,rid, Toast.LENGTH_LONG).show();

               }
               catch (Exception ex){
                   String m = ex.getMessage();
               }
                Button printbtn = (Button) findViewById(R.id.btnPrint);
                printbtn.setVisibility(View.VISIBLE);
                btnsub.setVisibility(View.INVISIBLE);
              if(  isNetworkAvailable()) {
                  cr1.CreateCNNNotes(cbcn, tmpCN);

              }
                else
              {
                  pd.hide();
                  Intent in=new Intent(CnoteActivity.this ,srmicrosystems.cnote.MainActivity.class);
                  startActivity(in);
                  Print();

              }

            }
        });


        //Callback<CNNote> cbcn ;

        cbcn = new Callback<CNNote>() {
            @Override
            public void onResponse(Call<CNNote> call, Response<CNNote> response) {
                if (response.isSuccessful())
                {
                    pd.hide();
                    CNNoteSQLHelper.UpdateCnNoteStatus(CNnoteHelper.getWritableDatabase(),response.body().getCNNumber());
                    pd.setMessage("Printing");
                 Toast.makeText(current,"CNNNOte Creaetd. Printing CNNote.", Toast.LENGTH_LONG).show();

                Print();

                }
            }

            @Override
            public void onFailure(Call<CNNote> call, Throwable t) {
                Toast.makeText(current,"Error Occured while Createing the CNOTE" + t.getMessage() , Toast.LENGTH_LONG).show();
                pd.hide();
                /*Intent in=new Intent(CnoteActivity.this ,srmicrosystems.cnote.MainActivity.class);
                startActivity(in);*/

            }
        };
    }

    public void Print(){
        pd.setMessage("Printing CNNote");
        pd.show();
        final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter == null) {
            pd.setMessage("Printing CNNote:Not Supported ");
            Toast.makeText(this, "Not Supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //un comment the line below to debug the print service
        //mPrinter.setDebugService(BuildConfig.DEBUG);
        try {
            if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED)
            {   pd.setMessage("Printing CNNote:Connecting ");
               if ( mPrinter.initService(this, mMessenger) == true)
                {
                    if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED){
                        pd.setMessage("Printing CNNote:Printer Connected: " + mPrinter.name() + " Battery status" + mPrinter.getBatteryStatus());
                        mPrinter.showDeviceList(this);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            pd.setMessage("Printer Error : " + e.getMessage());
        }



            mPrinter.setPrinterWidth(PrinterWidth.PRINT_WIDTH_72MM);

        //   Button btnprint = (Button) findViewById(R.id.btnprint);
        //  btnprint.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View v) {
        mPrinter.setHighIntensity();
        mPrinter.setStyleCourier();
    //    file:///android_asset
      //  Uri fileUri = Uri.parse("/storage/emulated/0/Download/SSCPL LOGO1.jpg");
       /* String imageUri = "drawable://" + R.drawable.sscpllogo1;
        Uri path = Uri.parse("android.resource:///srmicrosystems.cnote" + R.drawable.sscpllogo1);
        Uri otherPath = Uri.parse("android.resource://com.segf4ult.test/drawable/icon");*/
        boolean b1=   mPrinter.printSavedImage(1);
      /*  String path1 = path.toString();
File f = new File("drawable://" + R.drawable.sscpllogo1);
        try {
          Bitmap b = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.sscpllogo1);

        } catch (Exception e1)
        {
            pd.setMessage(e1.getMessage());
        }

            if (f.exists())
        {
            Boolean r = mPrinter.printDirect(f.getPath(),true,134);
        }
     Uri fileUri = Uri.parse("file:///android_asset/sscpllogo2.jpg");
  //      Boolean r = mPrinter.printDirect(fileUri.getPath(),true,134);*/
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

        mPrinter.printTextLine("   Date: "+ data.getBookingDate().toString()  + "\n");




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
        mPrinter.printTextLine("Mode Of Payment: ");
        mPrinter.setBold();
        mPrinter.printTextLine( data.getPayMode() + " \n");
        mPrinter.printLineFeed();
        mPrinter.setRegular();
        mPrinter.printTextLine("Material Description: ");
        mPrinter.setBold();
        mPrinter.printTextLine( data.getMaterialDesc() + " \n");
        mPrinter.printLineFeed();
        mPrinter.setRegular();
        mPrinter.printTextLine("Handed By: ");
        mPrinter.setBold();
        mPrinter.printTextLine( data.getHandedBy() +  "\n");
        mPrinter.setBold();
        mPrinter.printTextLine("Signature \n");
try
{
        //      Uri fileUri1 = Uri.parse("/storage/emulated/0/20160911042047.jpg");
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator  + CNNumber + ".jpg");
        Uri fileUri1 = Uri.parse(file.getPath());
           if (file.exists()){

        Boolean r1 = mPrinter.printDirect(fileUri1.getPath(),true,134);}
}
        catch(Exception ex1){
            pd.setMessage(ex1.getMessage());
        }

        mPrinter.printLineFeed();
        mPrinter.printLineFeed();
        mPrinter.printLineFeed();
        //      }
        //  });
        pd.setMessage("Printing CNNote:Printing Done");
       // Intent in=new Intent(CnoteActivity.this ,srmicrosystems.cnote.MainActivity.class);
        //startActivity(in);
        pd.hide();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                    tmpHandedBy = (String) bundle.get("HandedBy");
                    if(status.equalsIgnoreCase("done")){
                        String url = bundle.getString("path");
                    //    File file = new File(url);
                     //   File file = new File("/storage/emulated/0/Pictures/"+CNNumber+".jpg");
                        File file = new File(Environment.getExternalStorageDirectory()
                                + File.separator +  CNNumber + ".jpg");
                        RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("picture", file.getName(), fbody);

                        String descriptionString = "hello, this is description speaking";
                        RequestBody description =
                                RequestBody.create(
                                        MediaType.parse("multipart/form-data"), descriptionString);

                       /*
                        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), AZUtils.getUserId(this));*/
                        try {
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

                        }catch (Exception ex){
                           String s = ex.getMessage();
                        }
                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                    }
                }
                break;
        }

    }



}
