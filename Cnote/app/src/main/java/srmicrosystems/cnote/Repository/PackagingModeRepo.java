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
import srmicrosystems.cnote.Model.PackagingMode;
import srmicrosystems.cnote.Model.TransportMode;
import srmicrosystems.cnote.R;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

import static srmicrosystems.cnote.Service.ServiceHub.GetComapanyData;

/**
 * Created by User on 2/11/2017.
 */
public class PackagingModeRepo {

    public void GetPackaging(Callback<List<PackagingMode>> cb){

        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<PackagingMode>> crepo = sh.GetPackagingMode();
        crepo.enqueue(cb);

    }
    public static  ArrayList<PackagingMode> GetPackagingModeList(String productJSONStr)
    {
        ArrayList<PackagingMode> PackagingModeList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                PackagingModeList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<PackagingMode>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return PackagingModeList;
    }


    public ArrayList<PackagingMode> GetPackagingModeCached(Context c){

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("packagingMode","PackagingMode");

        ArrayList<PackagingMode>  lstTM=  GetPackagingModeList(jsonObject);


        return lstTM;

    }

}
