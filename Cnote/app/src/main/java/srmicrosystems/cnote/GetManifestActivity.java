package srmicrosystems.cnote;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import srmicrosystems.cnote.Repository.CNNoteRepo;

/**
 * Created by saman_000 on 09-10-2016.
 */
public class GetManifestActivity extends Activity {

    Context context;
    CNNoteRepo cnnRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context  =this;
        setContentView(R.layout.activity_getmenifestconf);
        Button btn = (Button) findViewById(R.id.btnSearchMenifest);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
try{
    EditText MID = (EditText) findViewById(R.id.MenifestId);
    cnnRepo = new CNNoteRepo();
    cnnRepo.SaveCNNNotDetailsExt(MID.getText().toString(),context);
    Intent in=new Intent(GetManifestActivity.this ,srmicrosystems.cnote.MainActivity.class);
    startActivity(in);
} catch (Exception ex)
{
    String s = ex.getMessage();
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
}
