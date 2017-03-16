package srmicrosystems.cnote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.Adapters.MasterSyncAdapter;
import srmicrosystems.cnote.Model.Adapters.TranportModeSpinerADP;
import srmicrosystems.cnote.Model.AirFlight;
import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.Company;
import srmicrosystems.cnote.Model.PackagingMode;
import srmicrosystems.cnote.Model.TransportMode;
import srmicrosystems.cnote.Repository.AirFlightRepo;
import srmicrosystems.cnote.Repository.CarrierTypeRepo;
import srmicrosystems.cnote.Repository.CityRepo;
import srmicrosystems.cnote.Repository.CompRepo;
import srmicrosystems.cnote.Repository.PackagingModeRepo;
import srmicrosystems.cnote.Repository.TransportModeRepo;

import static srmicrosystems.cnote.Service.ServiceHub.GetjsonData;

/**
 * Created by saman_000 on 21-05-2016.
 */
public class SyncActivity extends Activity {

     Context c;

    public void DownloadLogo(){
        final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("DownloadLogo");
        msAdp.updateItem(si.Id-1,"In-Prgoress",true);
        msAdp.notifyDataSetChanged();
        try
        {
            Long  i1 = new DownloadLogoTask().execute("sscpl.jpg").get();
            Toast.makeText(c,"Logo Download Done",Toast.LENGTH_LONG);
            msAdp.updateItem(si.Id-1,"Sync-Done",false);
            msAdp.notifyDataSetChanged();
        }
        catch(Exception e){
            Toast.makeText(c,"Logo Download Failed",Toast.LENGTH_LONG);
        }

    }

    public void SyncTM()
    {
        TransportModeRepo tmr = new TransportModeRepo();
       final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("Transport Mode");
        msAdp.updateItem(si.Id-1,"In-Prgoress",true);
        msAdp.notifyDataSetChanged();

        tmr.GetCarrier(new Callback<List<TransportMode>>() {
            @Override
            public void onResponse(Call<List<TransportMode>> call, Response<List<TransportMode>> response) {
                PutData(response);
                Toast.makeText(c,"Transport Mode Sync Done",Toast.LENGTH_LONG);
                msAdp.updateItem(si.Id-1,"Sync-Done",false);
                msAdp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TransportMode>> call, Throwable t) {
                Toast.makeText(c,"Transport Mode Sync Failed",Toast.LENGTH_LONG);
            }
        });
    }

    public void SyncPM()
    {
        PackagingModeRepo tmr = new PackagingModeRepo();
        final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("Packaging Mode");
        msAdp.updateItem(si.Id-1,"In-Prgoress",true);
        msAdp.notifyDataSetChanged();

        tmr.GetPackaging(new Callback<List<PackagingMode>>() {
            @Override
            public void onResponse(Call<List<PackagingMode>> call, Response<List<PackagingMode>> response) {
                PutDataPm(response);
                Toast.makeText(c,"Packaging Mode Sync Done",Toast.LENGTH_LONG);
                msAdp.updateItem(si.Id-1,"Sync-Done",false);
                msAdp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PackagingMode>> call, Throwable t) {
                Toast.makeText(c,"Packaging Mode Sync Failed",Toast.LENGTH_LONG);
            }
        });
    }

    public void SyncAF()
    {
        AirFlightRepo tmr = new AirFlightRepo();
        final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("AirFlight");
        msAdp.updateItem(si.Id-1,"In-Prgoress",true);
        msAdp.notifyDataSetChanged();

        tmr.GetAirFlights(new Callback<List<AirFlight>>() {
            @Override
            public void onResponse(Call<List<AirFlight>> call, Response<List<AirFlight>> response) {
                PutDataAF(response);
                Toast.makeText(c,"AirFlight Sync Done",Toast.LENGTH_LONG);
                msAdp.updateItem(si.Id-1,"Sync-Done",false);
                msAdp.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AirFlight>> call, Throwable t) {
                Toast.makeText(c,"Air Flight Sync Failed",Toast.LENGTH_LONG);
            }
        });
    }

  public void SyncCompany()
  {
      retrofit2.Callback<List<Company>> cb;
      try
      {
          final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("Company");
          msAdp.updateItem(si.Id-1,"In-Prgoress",true);
          msAdp.notifyDataSetChanged();
          cb =new retrofit2.Callback<List<Company>>(){


              public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                  /*  Adapter a = new Adapter() {
                    }*/
                  PutComapdata(response);
                  msAdp.updateItem(si.Id-1,"Sync-Done",false);
                  msAdp.notifyDataSetChanged();
                  Toast.makeText(c,"Sync Done",Toast.LENGTH_LONG);
              }

              @Override
              public void onFailure(Call<List<Company>> call, Throwable t) {
                  Toast.makeText(c,"Sync Failed",Toast.LENGTH_LONG);
              }
          };
          CompRepo cr = new CompRepo();
          cr.GetCompany(cb);
      }
      catch (Exception exception)
      {

      }
  }


    public void SyncCT()
    {
        retrofit2.Callback<List<CarrierType>> cb;
        try
        {
            final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("Carrier Type");
            msAdp.updateItem(si.Id-1,"In-Prgoress",true);
            msAdp.notifyDataSetChanged();
            cb =new retrofit2.Callback<List<CarrierType>>(){


                @Override
                public void onResponse(Call<List<CarrierType>> call, Response<List<CarrierType>> response) {
                  /*  Adapter a = new Adapter() {
                    }*/
                    PutCtData(response);
                    msAdp.updateItem(si.Id-1,"Sync-Done",false);
                    msAdp.notifyDataSetChanged();
                    Toast.makeText(c,"CT Sync Done",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<CarrierType>> call, Throwable t) {
                    Toast.makeText(c,"Sync Failed",Toast.LENGTH_LONG).show();
                }
            };
            CarrierTypeRepo cr = new CarrierTypeRepo();
            cr.GetCarrier(cb);
        }
        catch (Exception e)
        {

        }


    }
    public void SyncCity()
    {
        retrofit2.Callback<List<City>> cb;
        try
        {
            final  MasterSyncAdapter.SyncItem si =msAdp.GetItem("City");
            msAdp.updateItem(si.Id-1,"In-Prgoress",true);
            msAdp.notifyDataSetChanged();
            cb =new retrofit2.Callback<List<City>>(){


                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                  /*  Adapter a = new Adapter() {
                    }*/
                    Putdata(response);
                    msAdp.updateItem(si.Id-1,"Sync-Done",false);
                    msAdp.notifyDataSetChanged();
                    Toast.makeText(c,"City Sync Done",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    Toast.makeText(c,"Sync Failed",Toast.LENGTH_LONG);
                }
            };
            CityRepo cr = new CityRepo();
            cr.GetCity(cb);
        }
        catch (Exception exception)
        {

        }


    }
    private int mProgressStatus = 0;
public ListView mList;
    public MasterSyncAdapter msAdp;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_sync);
        c = this;

        mList = (ListView) findViewById(R.id.syncit);
        //MasterSyncAdapter.SyncItem si = new MasterSyncAdapter().SyncItem("City","In Progress",true);
         msAdp = new MasterSyncAdapter(this);
        msAdp.InsertItems("City","Yet to Started",false);
        msAdp.InsertItems("Transport Mode","Yet to Started",false);
        msAdp.InsertItems("Company","Yet to Started",false);
        msAdp.InsertItems("Carrier Type","Yet to Started",false);
        msAdp.InsertItems("Packaging Mode","Yet to Started",false);
        msAdp.InsertItems("AirFlight", "Yet to Started",false);
        msAdp.InsertItems("DownloadLogo", "Yet to Started",false);

        mList.setAdapter(msAdp);
        Button syncBtn = (Button) findViewById(R.id.syncButton);

        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,"Sync Started",Toast.LENGTH_LONG).show();


                SyncTM();
                SyncCompany();
                SyncCity();
                SyncPM();
                SyncCT();;
                SyncAF();
                DownloadLogo();
            }
        });




    }
    protected void Getdata(Response<List<City>> response){

      /*  Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("city",gson.toJson(response.body())).apply();*/

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(this).getString("city","City");

        List<City>  CNNotes=  GetjsonData(City.class,jsonObject);

    }

    protected  void PutData(Response<List<TransportMode>> response)
    {
        Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("transportMode",gson.toJson(response.body())).apply();
    }

    protected  void PutDataPm(Response<List<PackagingMode>> response)
    {
        Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("packagingMode",gson.toJson(response.body())).apply();
    }

    protected  void PutDataAF(Response<List<AirFlight>> response)
    {
        Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("airflight",gson.toJson(response.body())).apply();
    }


    protected void Putdata(Response<List<City>> response){

        Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("city",gson.toJson(response.body())).apply();



    }

    protected void PutCtData(Response<List<CarrierType>> response){

        Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("CT",gson.toJson(response.body())).apply();



    }

    protected void PutComapdata(Response<List<Company>> response){

        Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("company",gson.toJson(response.body())).apply();



    }

    protected void Storedata(Response<List<Company>> response){

      /*  Gson gson = new Gson();
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString("city",gson.toJson(response.body())).apply();*/

        String jsonObject = PreferenceManager.getDefaultSharedPreferences(this).getString("company","Comapany");

        List<Company>  CNNotes=  GetjsonData(Company.class,jsonObject);

        ArrayList<String> items  ;
        items = new ArrayList<String>();
        for (Company i :CNNotes )
        {

            items.add(i.getCompanyName());
        }


    }





}
