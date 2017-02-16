package srmicrosystems.cnote.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import srmicrosystems.cnote.R;

/**
 * Created by saman_000 on 01-10-2016.
 */
public class PrintADP {
    Context context;

    public void Print(final Context c,final String Path,final String MID)
    {
        context = c;
        PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
        String jobName = context.getString(R.string.Mainfest) + " Document";

        PrintDocumentAdapter pda = new PrintDocumentAdapter(){
            @Override
            public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback){
                InputStream input = null;
                OutputStream output = null;

                try {
                    //  File pdfFolder = (Environment.getExternalStoragePublicDirectory("Documents"));
                //    String s = Environment.getExternalStoragePublicDirectory("Documents").getPath();
                    input = new FileInputStream(Path);
                    output = new FileOutputStream(destination.getFileDescriptor());

                    byte[] buf = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = input.read(buf)) > 0) {
                        output.write(buf, 0, bytesRead);
                    }

                    callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});


                } catch (FileNotFoundException ee){
                    //Catch exception
                } catch (Exception e) {
                    //Catch exception
                } finally {
                    try {
                        input.close();
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras){

                if (cancellationSignal.isCanceled()) {
                    callback.onLayoutCancelled();
                    return;
                }

                int pages =1;// computePageCount(newAttributes);

                PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("MID").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

                callback.onLayoutFinished(pdi, true);
            }
            @Override
            public void onFinish ()
            {
                Intent in=new Intent(context ,srmicrosystems.cnote.ManifestConfActivity.class);
                context.startActivity(in);
            }
        };
        printManager.print(jobName, pda, null);
    }

}
