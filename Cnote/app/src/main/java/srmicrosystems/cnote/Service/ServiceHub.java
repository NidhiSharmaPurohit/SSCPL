package srmicrosystems.cnote.Service;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.Company;

/**
 * Created by saman_000 on 12-05-2016.
 */
public class ServiceHub  {

    public static  <T> List<T> GetjsonData(final Class<T> cls, String productJSONStr)
    {
        ArrayList<T> CNNoteList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                CNNoteList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<T>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return CNNoteList;
    }

    public static  List<City> GetjsonData( String productJSONStr)
    {
        ArrayList<City> CNNoteList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                CNNoteList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<City>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return CNNoteList;
    }

    public static  List<Company> GetComapanyjsonData(String productJSONStr)
    {
        ArrayList<Company> CNNoteList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                CNNoteList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<Company>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return CNNoteList;
    }

    public static  ArrayList<Company> GetComapanyData(String productJSONStr)
    {
        ArrayList<Company> CNNoteList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                CNNoteList = gson.fromJson(productJSONStr, new TypeToken<ArrayList<Company>>() {
                }.getType());
            } catch (IllegalStateException e) {
                Log.d("Error",e.getMessage());
                e.printStackTrace();
            }
            catch (Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        return CNNoteList;
    }

    public static IServiceHub  createRetrofitService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.11.236.231/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //T service = restAdapter.create(clazz);

        IServiceHub service = retrofit.create(IServiceHub.class);

        return service;
    }


  /*  public List<CNNote> GetDNNotes()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.11.236.231/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IServiceHub service = retrofit.create(IServiceHub.class);
        Call<List<CNNote>> repos = service.listRepos();
         final List<CNNote> Resp = null;
        try {
            //     Resp = repos.execute().body();

            repos.enqueue(new  Callback<List<CNNote>>() {
                @Override
                public void onResponse(Call<List<CNNote>> call, Response<List<CNNote>> response) {
                    Resp.addAll( response.body());
                }

                @Override
                public void onFailure(Call<List<CNNote>> call, Throwable t) {

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  Resp;
    }*/



}
