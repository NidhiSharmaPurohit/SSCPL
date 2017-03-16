package srmicrosystems.cnote;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by saman_000 on 10-10-2016.
 */
public class DownloadImageTask extends AsyncTask<String, Integer, Long> {
   /* @Override
    protected Long doInBackground(URL... urls)
    {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++)
        {
            totalSize += Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return totalSize;
    }*/

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        //setProgressPercent(progress[0]);
    }

    @Override
    protected Long doInBackground(String... params) {
       try{
           FileOutputStream out = null;
           URL url = new URL("http://52.11.236.231/uploadCNNOtesSignImage/" + params[0] + ".jpg" );
           Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
           File pdfFolder = new File(Environment.getExternalStorageDirectory().getPath().toString() + File.separator + "GetSignature");
           String path =       pdfFolder + File.separator + params[0] + ".jpg";
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
