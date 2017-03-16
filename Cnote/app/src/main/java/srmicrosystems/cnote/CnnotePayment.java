package srmicrosystems.cnote;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cie.btp.Barcode;
import com.cie.btp.CieBluetoothPrinter;
import com.cie.btp.PrinterWidth;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.Adapters.PackagingModeSpinerADP;
import srmicrosystems.cnote.Model.PackagingMode;
import srmicrosystems.cnote.Model.PaymentCnnote;
import srmicrosystems.cnote.Model.PaymentDetailsWithTaxForCnote;
import srmicrosystems.cnote.Repository.CNNotePaymentRepo;
import srmicrosystems.cnote.Repository.PackagingModeRepo;
import srmicrosystems.cnote.Service.ServiceHub;
import srmicrosystems.cnote.Utils.PrintSrvMsgHandler;

import srmicrosystems.cnote.databinding.ActivityCnnotePaymentBinding;

public class CnnotePayment extends AppCompatActivity {

    ProgressDialog pd ;
    ProgressDialog paysubmitdialog;
    Context ctx;
    PackagingModeSpinerADP pmadp;
    ArrayList<PackagingMode> pm;
    String CNN;

    public static CieBluetoothPrinter mPrinter = CieBluetoothPrinter.INSTANCE;
    Messenger mMessenger;
    private String mConnectedDeviceName = "";
    public static final String title_connecting = "connecting...";
    public static final String title_connected_to = "connected: ";
    public static final String title_not_connected = "not connected";
    private ToggleButton tbPrinter;
    private TextView statusMsg;
    PaymentDetailsWithTaxForCnote data;
    PaymentCnnote paymentdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnnote_payment);

        pd= new ProgressDialog(this);
        pd.setIndeterminate(false);
        pd.incrementProgressBy(10);
        pd.setMessage("Getting Data from server");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER );
        pd.show();
        ctx = this;
        TextView tv = new TextView(ctx);

        mMessenger = new Messenger(new PrintSrvMsgHandler( tv,ctx));
        ActivityCnnotePaymentBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_cnnote_payment);

        PackagingModeRepo pmr = new PackagingModeRepo();
        pm=pmr.GetPackagingModeCached(this);
        pmadp = new PackagingModeSpinerADP(ctx,R.layout.support_simple_spinner_dropdown_item,pm);
        final Spinner pmMode = (Spinner) findViewById(R.id.spnPayMode);
        pmMode.setAdapter(pmadp);

        CNN = getIntent().getExtras().get("CNNNumber").toString();
        CNNotePaymentRepo cnrepo = new CNNotePaymentRepo();
        try{
            data = cnrepo.GetPaymentDetailsWithTaxForCnoteSync(this,CNN);
            if (data == null) {
                pd.setMessage("CNote Not Found ");
                Toast.makeText(this,"CNote Not Found ",Toast.LENGTH_LONG);
                return;
            }
            binding.setPay(data);
            pd.hide();

            Button btnPrint = (Button) findViewById(R.id.btnPayPrint);
            btnPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Print();
                }
            });


            final Button btnSubmit = (Button) findViewById(R.id.btnPaySubmit);
            btnSubmit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    paysubmitdialog= new ProgressDialog(ctx);
                    paysubmitdialog.setIndeterminate(false);
                    paysubmitdialog.incrementProgressBy(10);
                    paysubmitdialog.setMessage("Submitting Payment Information");
                    paysubmitdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER );
                    paysubmitdialog.show();

                    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                    paymentdetails = new PaymentCnnote();
                    String dt ;
                    try
                    {
                        dt = df1.format(data.getBookingDate());
                    }
                    catch(Exception e)
                    {
                        //dt = new Date();
                        dt = data.getBookingDate().toString();
                    }

                    EditText payamount = (EditText) findViewById(R.id.PayAmounttxt);
                    EditText chequenum = (EditText) findViewById(R.id.chqnum);
                    EditText bankname = (EditText) findViewById(R.id.bankname);
                    EditText accountnum = (EditText) findViewById(R.id.accountnum);
                    EditText paytmmob = (EditText) findViewById(R.id.paytmmobile);
                    EditText banktransid = (EditText) findViewById(R.id.onlinetransactionid);
                    EditText paycreperson = (EditText) findViewById(R.id.paycreatedperson);
                    Spinner paymodespinner = (Spinner)findViewById(R.id.spnPayMode);
                    int pmposition = paymodespinner.getSelectedItemPosition();
                    PackagingMode paymenttmode = pmadp.getItem(pmposition);

                    paymentdetails.AccountNumber = accountnum.getText().toString();
                    paymentdetails.Amount = data.getAmount();
                    paymentdetails.BankName = bankname.getText().toString();
                    paymentdetails.CenterId = data.getCenterID();
                    paymentdetails.ChequeNumber = chequenum.getText().toString();
                    paymentdetails.CNNumber = data.getCNNumber();
                    paymentdetails.CreatedBy = paycreperson.getText().toString();
                    paymentdetails.CompanyId = data.getShipperCompId();
                    paymentdetails.DateCreated = dt;
                    paymentdetails.TotalAmount = Double.parseDouble(payamount.getText().toString());
                    paymentdetails.Discount = 0;
                    paymentdetails.FromDate = dt;
                    paymentdetails.InvoiceDate = dt;
                    paymentdetails.InvoiceNum = "Invoice_" + data.getCNNumber();



                    paymentdetails.NEFTDebitCreditTransactionId = banktransid.getText().toString();
                    paymentdetails.PaymentMode = Integer.parseInt(paymenttmode.getId());
                    paymentdetails.PayTMMobile = paytmmob.getText().toString();
                    paymentdetails.RateId = data.getRateId();
                    paymentdetails.Statuss = 1;
                    paymentdetails.TaxAmount = data.getTaxableAmount();
                    paymentdetails.ToDate = dt;

                    paymentdetails.Weight = data.getConsignmentWeight();

                    if(isNetworkAvailable()){

                        Call<ResponseBody> cb = ServiceHub.createRetrofitService().CreatePaymentForCNNote(paymentdetails);
                        Callback<ResponseBody> cbrb;
                        cb.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    String s = response.message();

                                    Toast.makeText(ctx,"Payment Submitted Sucessfully. Printing Receipt", Toast.LENGTH_LONG).show();
                                    paysubmitdialog.hide();
                                    Print();

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                String s = t.getMessage();
                                Object e = t.getStackTrace();
                                Toast t1 = new Toast(ctx);
                                t1.setText("Error Creating Payment");
                                t1.show();
                                paysubmitdialog.hide();
                            }
                        });


                    }

                    Button printbtn = (Button) findViewById(R.id.btnPayPrint);
                    printbtn.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.INVISIBLE);

                }
            });

        }
        catch (Exception ex)
        {
            String s=ex.getMessage();
            Log.d("Error",ex.getMessage());
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG);
            pd.hide();
        }


    }

    public void Print(){
        pd.setMessage("Printing Payment Receipt");
        pd.show();
        final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mAdapter == null) {
            pd.setMessage("Printing Payment Receipt:Not Supported ");
            Toast.makeText(this, "Printing Not Supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //un comment the line below to debug the print service
        //mPrinter.setDebugService(BuildConfig.DEBUG);
        try {
            if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED)
            {   pd.setMessage("Printing Payment Receipt:Connecting ");
                if ( mPrinter.initService(this, mMessenger) == true)
                {
                    if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED){
                        pd.setMessage("Printing Payment Receipt:Printer Connected: " + mPrinter.name() + " Battery status" + mPrinter.getBatteryStatus());
                        mPrinter.showDeviceList(this);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            pd.setMessage("Printer Error : " + e.getMessage());
        }



        mPrinter.setPrinterWidth(PrinterWidth.PRINT_WIDTH_72MM);

        mPrinter.setHighIntensity();
        mPrinter.setStyleCourier();

        boolean b1=   mPrinter.printSavedImage(1);

        mPrinter.setFondSizeMedium();
        mPrinter.printTextLine("Payment Receipt for AWB#:  " + data.getCNNumber());
        mPrinter.printLineFeed();
        mPrinter.printTextLine(data.getTransportMode() + "    ");
        // mPrinter.printTextLine("AIR              ");
        // mPrinter.printTextLine("SRT-BOM\n");
        mPrinter.printTextLine(data.getSourceCityCode() + "-"+ data.getDestCityCode() + "\n");
        mPrinter.setFondSizeSmall();
        mPrinter.printLineFeed();

        //   mPrinter.printBarcode("2016120912", Barcode.CODE_128,384,100);
        mPrinter.printBarcode(paymentdetails.InvoiceNum, Barcode.CODE_128,384,100);
        mPrinter.setAlignmentLeft();

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
        mPrinter.setStyleCourier();
        mPrinter.setRegular();
        mPrinter.printTextLine("No of Packages: ");
        mPrinter.setBold();
        //mPrinter.printTextLine("100\n");
        mPrinter.printTextLine( data.getPackageNo() + "\n");
        mPrinter.setRegular();

        Spinner paymodespinner = (Spinner)findViewById(R.id.spnPayMode);
        int pmposition = paymodespinner.getSelectedItemPosition();
        PackagingMode paymenttmode = pmadp.getItem(pmposition);

        mPrinter.setRegular();
        mPrinter.printTextLine("Consingment Weight: ");
        mPrinter.setBold();
        //  mPrinter.printTextLine("100\n");
        mPrinter.printTextLine( data.getConsignmentWeight() + "\n");
        mPrinter.setRegular();

        mPrinter.printTextLine(" Amount: ");
        mPrinter.setBold();
        mPrinter.printTextLine( String.valueOf(data.getTotalAmount()) + " \n");
        mPrinter.printLineFeed();
        mPrinter.setRegular();
        mPrinter.printTextLine("Tax Amount: ");
        mPrinter.setBold();
        mPrinter.printTextLine( String.valueOf(data.getTaxableAmount()) +  "\n");

        mPrinter.printTextLine("Tax Details: ");
        mPrinter.setBold();
        mPrinter.printTextLine( data.getTaxDetails() + " \n");
        mPrinter.printLineFeed();
        mPrinter.setRegular();
        mPrinter.printTextLine("Total Amount Including Taxes: ");
        mPrinter.setBold();
        mPrinter.printTextLine( String.valueOf(paymentdetails.TotalAmount) +  "\n");

        mPrinter.printTextLine("Mode Of Payment: ");
        mPrinter.setBold();
        mPrinter.printTextLine( paymenttmode.getTypee().toString() + " \n");
        mPrinter.printLineFeed();
        mPrinter.setRegular();

        mPrinter.printTextLine("Payment Created By: ");
        mPrinter.setBold();
        mPrinter.printTextLine( paymentdetails.CreatedBy + " \n");
        mPrinter.printLineFeed();
        mPrinter.setRegular();

        if(!paymentdetails.ChequeNumber.isEmpty()) {
            mPrinter.printTextLine("Cheque Number: ");
            mPrinter.setBold();
            mPrinter.printTextLine(paymentdetails.ChequeNumber + " \n");
            mPrinter.printLineFeed();
            mPrinter.setRegular();
        }

        if(!paymentdetails.BankName.isEmpty()) {
            mPrinter.printTextLine("Bank Name: ");
            mPrinter.setBold();
            mPrinter.printTextLine(paymentdetails.BankName + " \n");
            mPrinter.printLineFeed();
            mPrinter.setRegular();
        }

        if(!paymentdetails.AccountNumber.isEmpty()) {
            mPrinter.printTextLine("Account Number: ");
            mPrinter.setBold();
            mPrinter.printTextLine(paymentdetails.AccountNumber + " \n");
            mPrinter.printLineFeed();
            mPrinter.setRegular();
        }

        if(!paymentdetails.PayTMMobile.isEmpty()) {
            mPrinter.printTextLine("PayTM Mobile Number: ");
            mPrinter.setBold();
            mPrinter.printTextLine(paymentdetails.PayTMMobile + " \n");
            mPrinter.printLineFeed();
            mPrinter.setRegular();
        }

        if(!paymentdetails.NEFTDebitCreditTransactionId.isEmpty()) {
            mPrinter.printTextLine("Credit/Debit Online Transaction ID: ");
            mPrinter.setBold();
            mPrinter.printTextLine(paymentdetails.NEFTDebitCreditTransactionId + " \n");
            mPrinter.printLineFeed();
            mPrinter.setRegular();
        }



        mPrinter.printLineFeed();
        mPrinter.printLineFeed();
        mPrinter.printLineFeed();
        //      }
        //  });
        pd.setMessage("Printing Payment Receipt:Printing Done");
        // Intent in=new Intent(CnoteActivity.this ,srmicrosystems.cnote.MainActivity.class);
        //startActivity(in);
        pd.hide();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
