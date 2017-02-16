package srmicrosystems.cnote.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cie.btp.CieBluetoothPrinter;
import com.cie.btp.DebugLog;

/**
 * Created by saman_000 on 11-09-2016.
 */
public class PrintSrvMsgHandler extends Handler {
    private TextView statusMsg;
    private String mConnectedDeviceName = "";
    public static final String title_connecting = "connecting...";
    public static final String title_connected_to = "connected: ";
    public static final String title_not_connected = "not connected";
    private ToggleButton tbPrinter;
    Context context;
    public PrintSrvMsgHandler(TextView statusMsgs,  Context context1)
    {
        statusMsg = statusMsgs;
        //tbPrinter;= tbPrinters;
        context = context1;
    }
    public void setStatusMsg(String msg) {
        statusMsg.setText(msg);

    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case CieBluetoothPrinter.MESSAGE_STATE_CHANGE:
                switch (msg.arg1) {
                    case CieBluetoothPrinter.STATE_CONNECTED:
                        setStatusMsg("Connected to" + mConnectedDeviceName);
                      //  tbPrinter.setText("ON");
                       // tbPrinter.setChecked(true);
                        break;
                    case CieBluetoothPrinter.STATE_CONNECTING:
                        setStatusMsg(title_connecting);
                        try {
                         //   tbPrinter.setText("...");
                           // tbPrinter.setChecked(false);
                        }catch (NullPointerException e){
                            DebugLog.logTrace("Fragment creating");
                        }
                        break;
                    case CieBluetoothPrinter.STATE_LISTEN:
                        setStatusMsg(title_connected_to + mConnectedDeviceName);
                    case CieBluetoothPrinter.STATE_NONE:
                        setStatusMsg(title_not_connected);
                        try {
                            //tbPrinter.setText("OFF");
                            //tbPrinter.setChecked(false);
                        }catch (NullPointerException n){
                            DebugLog.logTrace("Fragment creating");
                        }
                        break;
                }
                break;
            case CieBluetoothPrinter.MESSAGE_DEVICE_NAME:
                mConnectedDeviceName = msg.getData().getString(
                        CieBluetoothPrinter.DEVICE_NAME);
                break;
            case CieBluetoothPrinter.MESSAGE_STATUS:
                DebugLog.logTrace("Message Status Received");
                setStatusMsg(msg.getData().getString(
                        CieBluetoothPrinter.STATUS_TEXT));
                break;
            case CieBluetoothPrinter.PRINT_COMPLETE:
                setStatusMsg("PRINT OK");
                break;
            case CieBluetoothPrinter.PRINTER_CONNECTION_CLOSED:
                setStatusMsg("Printer Connection closed");
                break;
            case CieBluetoothPrinter.PRINTER_DISCONNECTED:
                setStatusMsg("Printer Connection failed");
                break;
            default:
                DebugLog.logTrace("Some un handled message : " + msg.what);
                super.handleMessage(msg);
        }
    }
}
