package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.Adapters.TranportModeSpinerADP;
import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.Company;
import srmicrosystems.cnote.Model.TransportMode;
import srmicrosystems.cnote.R;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

import static srmicrosystems.cnote.Service.ServiceHub.GetComapanyData;

/**
 * Created by saman_000 on 03-07-2016.
 */
public class TransportModeRepo {

    public void GetCarrier(Callback<List<TransportMode>> cb){

        List<CarrierType> city;
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<TransportMode>> crepo = sh.GetTransportMode();
        crepo.enqueue(cb);

    }
    public static  ArrayList<TransportMode> GetTransportMode(String productJSONStr)
    {
        ArrayList<TransportMode> TransportModeList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                TransportModeList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<TransportMode>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return TransportModeList;
    }


    public ArrayList<TransportMode> GetCarrierCached(Context c){

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("transportMode","TransportMode");

        ArrayList<TransportMode>  lstTM=  GetTransportMode(jsonObject);


        return lstTM;

    }
}
