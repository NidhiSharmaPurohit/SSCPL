package srmicrosystems.cnote;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Environment;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cie.btp.CieBluetoothPrinter;

import java.io.File;

import srmicrosystems.cnote.Utils.PrintSrvMsgHandler;

public class UploadLogoToPrinter extends AppCompatActivity {

    ProgressDialog pd;
    public static CieBluetoothPrinter mPrinter = CieBluetoothPrinter.INSTANCE;
    Context ctx;
    Messenger mMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_logo_to_printer);
        ctx = this;
        pd= new ProgressDialog(this);
        pd.setIndeterminate(false);
        pd.incrementProgressBy(10);
        pd.setMessage("Connecting to Printer");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER );

        TextView tv = new TextView(ctx);

        mMessenger = new Messenger(new PrintSrvMsgHandler( tv,ctx));

        Button btnlogoprint = (Button) findViewById(R.id.btnUploadLogo);
        btnlogoprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File logoFolder = new File(Environment.getExternalStorageDirectory().getPath().toString());
                String path = logoFolder + File.separator + "sscpl.jpg";

                pd.setMessage("Connecting to Printer");
                pd.show();
                final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mAdapter == null) {
                    pd.setMessage("Connection to Printer failed. Printer not connected ");
                    Toast.makeText(ctx, "Printer not connected", Toast.LENGTH_SHORT).show();
                    finish();
                }


                try {
                    if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED)
                    {   pd.setMessage("Printing CNNote:Connecting ");
                        if ( mPrinter.initService(ctx, mMessenger) == true)
                        {
                            if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED){
                                pd.setMessage("Printing CNNote:Printer Connected: " + mPrinter.name() + " Battery status" + mPrinter.getBatteryStatus());
                                //mPrinter.showDeviceList();

                            }

                            mPrinter.saveImage(path,true,134,1);
                            Toast.makeText(ctx, "Image saved on index " + 1,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    if(mPrinter.getState() == CieBluetoothPrinter.STATE_CONNECTED){
                        pd.hide();
                        pd.setMessage("Saving Image on Printer");
                        pd.show();
                        mPrinter.saveImage(path,true,134,1);
                        Toast.makeText(ctx, "Image saved on index " + 1,
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    pd.setMessage("Printer Error : " + e.getMessage());
                }



            }
        });
    }
}
