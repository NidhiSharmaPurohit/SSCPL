package srmicrosystems.cnote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by User on 3/5/2017.
 */
public class DownloadLogoTask extends AsyncTask<String, Integer, Long> {


    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        //setProgressPercent(progress[0]);
    }

    @Override
    protected Long doInBackground(String... params) {
        try{
            FileOutputStream out = null;
            URL url = new URL("http://52.11.236.231/uploadCNNOtesSignImage/sscpl.jpg" );
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            File logoFolder = new File(Environment.getExternalStorageDirectory().getPath().toString());
            String path =       logoFolder + File.separator + "sscpl.jpg";
            if (bmp.getByteCount() > 0 ){
                out = new FileOutputStream(path);

                bmp.compress(Bitmap.CompressFormat.JPEG,100,out);
            }
        }catch(Exception ex){
            String s = ex.getMessage();
            Log.d("Error",ex.getMessage());

        }




        return Long.valueOf(1);
    }

    @Override
    protected void onPostExecute(Long result) {
        //showDialog("Downloaded " + result + " bytes");
    }
}

