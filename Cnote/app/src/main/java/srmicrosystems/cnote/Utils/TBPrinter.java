package srmicrosystems.cnote.Utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Messenger;
import android.widget.TextView;
import android.widget.Toast;

import com.cie.btp.CieBluetoothPrinter;

/**
 * Created by saman_000 on 11-09-2016.
 */
public class TBPrinter {
    Context context;
    public static CieBluetoothPrinter mPrinter = CieBluetoothPrinter.INSTANCE;
    final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
    final TextView tv;

    final Messenger mMessenger ;
    public  TBPrinter(Context ccontext, Activity activity,TextView tv1){
        tv=tv1;
      context=ccontext;
        mMessenger = new Messenger(new PrintSrvMsgHandler(tv,context));
        if (mAdapter == null) {
            Toast.makeText(context, "Not Supported", Toast.LENGTH_SHORT).show();

        }else
        {
            try {
                if ( mPrinter.getState()!= CieBluetoothPrinter.STATE_CONNECTED)
                {   mPrinter.initService(context, mMessenger);}
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ( mPrinter.getState()!=CieBluetoothPrinter.STATE_CONNECTED){
                mPrinter.showDeviceList(activity);}
        }
    }
}
