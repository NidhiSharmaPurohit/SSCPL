package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

import static srmicrosystems.cnote.Service.ServiceHub.GetjsonData;

/**
 * Created by saman_000 on 12-05-2016.
 */
public class CityRepo {


    public void GetCity(Callback<List<City>> cb){
        List<City> city;
        IServiceHub sh = ServiceHub.createRetrofitService();
      Call<List<City>> comprepo = sh.GetCity();
        comprepo.enqueue(cb);

    }


    public  ArrayList<String> GetCity(Context c)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("city","City");

        List<City>  lstCty=  GetjsonData(jsonObject);

        if ( lstCty == null) {

        }


        ArrayList<String> lstCity ;//  = new List<String>();
        lstCity = new ArrayList<String>() ;

        for (int i =0;i<lstCty.size();i++)
        {
            lstCity.add(lstCty.get(i).getCityName());
        }
      /*  for (City i : lstCty )
        {
            lstCity.add(i.getCityName());
        }*/
        return lstCity;
    }

    public  List<City> GetAllCity(Context c)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("city","City");

        List<City>  lstCty=  GetjsonData(jsonObject);



        return lstCty;
    }
    public  City GetCityById(Context c,int CityId)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("city","City");

        List<City>  lstCty=  GetjsonData(jsonObject);
City resp = null;
        for (City tmp :lstCty)
        {
            if (tmp.getCityId() == CityId)
            {resp= tmp;

            }
        }


        return resp;
    }


    public void RepoCallback(List<City> lstCity){

    }




}
