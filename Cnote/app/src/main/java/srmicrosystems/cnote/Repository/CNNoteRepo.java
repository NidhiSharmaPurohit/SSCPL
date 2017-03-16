package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.DownloadImageTask;
import srmicrosystems.cnote.GetManifestActivity;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.CNNoteDetails;
import srmicrosystems.cnote.Model.CNNoteDetailsExt;
import srmicrosystems.cnote.Model.Company;
import srmicrosystems.cnote.Model.SQL.CNNoteDetailsExtSQLHelper;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

/**
 * Created by saman_000 on 21-05-2016.
 */
public class CNNoteRepo {
public String imgPath;
    public String  GetCNNNumber(){
        SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
        Date date = new Date();
        String dt = df.format(date);
        SimpleDateFormat df1 = new SimpleDateFormat("HHMMSS");
        return  dt;
    }

    public void CreateCNNNotes(Callback<CNNote> cb, CNNote cnNote){

        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<CNNote> SMRepo = sh.CreateDeatils(cnNote);
        SMRepo.enqueue(cb);
    }

    public void UpdateCNNNotes(Callback<CNNote> cb, CNNote cnNote){
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<CNNote> SMRepo = sh.UpdateCNNDetails(cnNote);
        SMRepo.enqueue(cb);
    }

    public CNNoteDetailsExt GetCNNoteDetailsExtSync(Context ctx,String CNN) throws Exception{
        Object[] obj = new Object[2];
        obj[0] = ctx;
        obj[1] =CNN;
     Long  i1=   new DownloadImageTask().execute(CNN).get();
     Long i2=   new DownloadImageTask().execute("D_"+CNN).get();
     return   new GetCNNoteDetailsAsyncTask().execute(obj).get();
    }

    public CNNoteDetailsExt GetCNNoteDetailsExt(Context ctx,String CNN) throws Exception {

        CNNoteDetailsExtSQLHelper sq = new CNNoteDetailsExtSQLHelper(ctx);
        try {
            CNNoteDetailsExt data = CNNoteDetailsExtSQLHelper.GetCNNoteDetailsExt(sq.getWritableDatabase(), CNN);
            if (TextUtils.isEmpty(data.getCNNumber())) {
                try

                {


                    data = GetCNNoteDetailsEXt(CNN);
                    return data;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    throw ex;
                } catch (Exception exp) {
                    throw exp;
                }

            }
            else{
                return  data;
            }

        }
        catch(Exception exception){
            exception.printStackTrace();
            try

            {


                CNNoteDetailsExt  datafromservice = GetCNNoteDetailsEXt(CNN);
                return  datafromservice;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                throw ex;
            } catch (Exception exp) {
                throw exp;
            }
        }

         return  GetCNNoteDetailsEXt(CNN);

    }

    public CNNoteDetailsExt GetCNNoteDetailsEXt(String Cnnote) throws Exception {
        IServiceHub sh = ServiceHub.createRetrofitService();

       Call<CNNoteDetailsExt> resp = sh.GetCNNoteDetailsExt(Cnnote);
    //    resp.enqueue(cb);

        try{
            CNNoteDetailsExt result = resp.execute().body();
            return  result;
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }


    }

    public List<CNNoteDetailsExt> GetListOfCNNoteDetailsExt(Context ctx,String MID) throws Exception {
        Object[] obj = new Object[2];
        obj[0] = ctx;
        obj[1] =MID;
        return  new CNNoteListDetailsAsyncTask().execute(obj).get();
    }

    public List<CNNoteDetailsExt> ListOfCNNoteDetailsExt(String MID) throws Exception {
        IServiceHub sh = ServiceHub.createRetrofitService();

        Call<List<CNNoteDetailsExt>> resp = sh.GetCNNoteDetailByManifestId(MID);

        try{
            List<CNNoteDetailsExt> result = resp.execute().body();
            return  result;
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }


    }

    public void GetCNNNotDetailsExt(String MID,Callback<List<CNNoteDetailsExt>> cb)
    {
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<CNNoteDetailsExt>> resp = sh.GetCNNoteDetailByManifestId(MID);
        resp.enqueue(cb);
    }

    public void SaveCNNNotDetailsExt(String MID, final Context ctx)
    {
        Callback<List<CNNoteDetailsExt>> cb;

        cb= new Callback<List<CNNoteDetailsExt>>() {
                @Override
                public void onResponse(Call<List<CNNoteDetailsExt>> call, Response<List<CNNoteDetailsExt>> response) {
                    CNNoteDetailsExtSQLHelper sq = new CNNoteDetailsExtSQLHelper(ctx);
                    sq.onCreate(sq.getWritableDatabase());
                    sq.DeleteAllCnNote(sq.getWritableDatabase());
                    for (CNNoteDetailsExt tmp : response.body()) {
                       int res=  CNNoteDetailsExtSQLHelper.InsertCNNOteDetailsExt(sq.getWritableDatabase(),tmp);
                       try {
                               Toast.makeText(ctx.getApplicationContext(),"CN Note Details inserted -" + res, Toast.LENGTH_LONG);
                               new DownloadImageTask().execute(tmp.getCNNumber());
                       }
                       catch(Exception ex){
                               Log.d("Error",ex.getMessage());
                               String s = ex.getMessage();
                               Toast.makeText(ctx,"CN Note Details inserted -" + res +", But Image is not saved.", Toast.LENGTH_LONG);


                       }

                    }


                }

                @Override
                public void onFailure(Call<List<CNNoteDetailsExt>> call, Throwable t) {
                    String s = t.getMessage();
                }
            };

        GetCNNNotDetailsExt(MID,cb);
    }



}
