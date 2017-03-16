package srmicrosystems.cnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class CNNoteKrishnaSerach extends Activity implements View.OnClickListener {

    Context ctx;

    private TextView statusMessage;
    private TextView barcodeValue;
    private ImageButton barcode ;
    EditText CNNID;
    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnnote_krishna_serach);
        ctx = this;
        statusMessage = (TextView)findViewById(R.id.status_message);
        CNNID = (EditText) findViewById(R.id.CnoteID);
        // barcodeValue = (TextView)findViewById(R.id.barcode_value);
        Button btnSearchCNNote = (Button) findViewById(R.id.btnSearchCNNote);
        btnSearchCNNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ctx ,srmicrosystems.cnote.CNNoteKrishna.class);

                in.putExtra("CNNNumber",CNNID.getText());
                startActivity(in);
            }
        });
        barcode = (ImageButton) findViewById(R.id.read_barcode);
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.read_barcode) {
                    // launch barcode activity.
                    Intent intent = new Intent(ctx, BarcodeCaptureActivity.class);
                    intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                    intent.putExtra(BarcodeCaptureActivity.UseFlash, true);

                    startActivityForResult(intent, RC_BARCODE_CAPTURE);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, true);

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);
                    CNNID.setText(barcode.displayValue);
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
}
