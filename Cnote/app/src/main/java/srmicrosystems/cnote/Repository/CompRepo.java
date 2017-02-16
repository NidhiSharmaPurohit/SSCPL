package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.Company;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

import static srmicrosystems.cnote.Service.ServiceHub.GetComapanyData;
import static srmicrosystems.cnote.Service.ServiceHub.GetComapanyjsonData;
import static srmicrosystems.cnote.Service.ServiceHub.GetjsonData;

/**
 * Created by saman_000 on 17-05-2016.
 */
public class CompRepo {


    public void GetCompany(Callback<List<Company>> comp){
        List<City> city;
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<Company>> comprepo = sh.GetCompany();
        comprepo.enqueue(comp);

    }


    public ArrayList<String> GetComp(Context c)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("company","Company");

        List<Company>  lstComp=  GetComapanyjsonData(jsonObject);

        if ( lstComp == null) {

        }


        ArrayList<String> lstCompany ;//  = new List<String>();
        lstCompany = new ArrayList<String>() ;

        for (int i =0;i<lstComp.size();i++)
        {
            lstCompany.add(lstComp.get(i).getCompanyName());
        }
      /*  for (City i : lstCty )
        {
            lstCity.add(i.getCityName());
        }*/
        return lstCompany;
    }


    public ArrayList<Company> GetAllComp(Context c)
    {

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(c).getString("company","Company");

        ArrayList<Company>  lstComp=  GetComapanyData(jsonObject);


        return lstComp;
    }



}
