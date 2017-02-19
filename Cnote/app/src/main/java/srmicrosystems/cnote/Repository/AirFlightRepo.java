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
import srmicrosystems.cnote.Model.AirFlight;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

/**
 * Created by User on 2/19/2017.
 */
public class AirFlightRepo {

    public void GetAirFlights(Callback<List<AirFlight>> cb){

        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<AirFlight>> crepo = sh.GetAirFlight();
        crepo.enqueue(cb);

    }
    public static ArrayList<AirFlight> GetAirFlightList(String productJSONStr)
    {
        ArrayList<AirFlight> AirFlightList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                AirFlightList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<AirFlight>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return AirFlightList;
    }


    public ArrayList<AirFlight> GetAirFlightCached(Context c){

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("airflight","AirFlight");

        ArrayList<AirFlight>  lstTM=  GetAirFlightList(jsonObject);


        return lstTM;

    }
}
