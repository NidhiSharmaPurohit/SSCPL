package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

import static srmicrosystems.cnote.Service.ServiceHub.GetjsonData;

/**
 * Created by saman_000 on 18-06-2016.
 */
public class CarrierTypeRepo {


    public void GetCarrier(Callback<List<CarrierType>> cb){
        List<CarrierType> city;
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<CarrierType>> crepo = sh.GetCarrierType();
        crepo.enqueue(cb);

    }
    public  CarrierType GetCarrierTypeById(Context c,int ctId)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("CT","CarrierType");

        List<CarrierType>  lstCty=  GetjsonData(jsonObject);
        CarrierType resp = null;
        for (CarrierType tmp :lstCty)
        {
            if (tmp.getId() == ctId)
            {resp= tmp;

            }
        }


        return resp;
    }


    List<CarrierType> ct=null;
    public List<CarrierType> GetAllCarrierType(Context c)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("CT","CarrierType");

        List<CarrierType>  lstCT=  GetjsonData(jsonObject);


        /* Callback<List<CarrierType>> cb = new Callback<List<CarrierType>>() {
             @Override
             public void onResponse(Call<List<CarrierType>> call, Response<List<CarrierType>> response) {
                 if( response.isSuccessful() )
                 {
                     ct = response.body();

                 }

             }

             @Override
             public void onFailure(Call<List<CarrierType>> call, Throwable t) {

             }
         } ;*/
        return lstCT;
    }

    public static  List<CarrierType> GetjsonData( String productJSONStr)
    {
        ArrayList<CarrierType> CT = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                CT = gson.fromJson(productJSONStr, new TypeToken<ArrayList<CarrierType>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return CT;
    }

}
