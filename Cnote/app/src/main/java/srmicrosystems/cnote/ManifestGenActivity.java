package srmicrosystems.cnote;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.Adapters.ManifestviewAdapter;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.CNNoteDetails;
import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.Manifest;
import srmicrosystems.cnote.Model.ManifestDetail;
import srmicrosystems.cnote.Model.ManifestItem;
import srmicrosystems.cnote.Model.ManifestItemDetails;
import srmicrosystems.cnote.Repository.CarrierTypeRepo;
import srmicrosystems.cnote.Repository.CityRepo;
import srmicrosystems.cnote.Repository.Manifestrepo;
import srmicrosystems.cnote.Service.ServiceHub;
import srmicrosystems.cnote.Utils.ManifestPDF;

public class ManifestGenActivity extends Activity {
    public ProgressDialog pd;
    protected ListView mList;
    ArrayAdapter<String> m1Adapter;
    public ArrayList<CNNoteDetails> CNNotes = null;
    ManifestviewAdapter madapter;
    Manifest m = new Manifest();
    Activity Current;
    String MId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd = new ProgressDialog(this);
        pd.setIndeterminate(false);
        pd.incrementProgressBy(10);
        pd.setMessage("Getting Data from server");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        String scity = getIntent().getExtras().get("SCity").toString();

        Date mDate,mEndDate;
        mDate = new Date();
        mEndDate= new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
             mDate = df.parse( getIntent().getExtras().get("mDate").toString());
            mEndDate = df.parse(getIntent().getExtras().get("mEndDate").toString());
        }
        catch (Exception ex ) {
            ////To do
        }


        String dcity =getIntent().getExtras().get("DCity").toString();
        String gadi =getIntent().getExtras().get("gadi").toString();
        //dcity="3";
        CityRepo cr = new CityRepo();
        CarrierTypeRepo ctRepo = new CarrierTypeRepo();
        City SourceCity = cr.GetCityById(this,Integer.parseInt(scity));
        City DestCity = cr.GetCityById(this,Integer.parseInt(dcity));
        CarrierType carriers  =ctRepo.GetCarrierTypeById(this,Integer.parseInt(gadi));
        setContentView(R.layout.activity_manifestgen);

        mList = (ListView)findViewById(R.id.listview);

        final ArrayList<CNNoteDetails> CNNotes = null;
        Current = this;
        m.setCarrierTypeID(gadi);
        m.setCenterID("1");
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String s,s1;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        s = sdf.format(date);

        m.setManifestDate(sdf1.format(date));
         MId = s+ "_"+DestCity.getCityName()+"_"+carriers.getCNumber();
        m.setManifestId(MId );
        m.setSourceCityID(Integer.toString(SourceCity.getCityId()));
        m.setTargetCityID(Integer.toString(DestCity.getCityId()));

        Call<ResponseBody> cb = ServiceHub.createRetrofitService().CreateManifest(m);
        Callback<ResponseBody> cbrb;
        cb.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
if (response.isSuccessful()){
    String s = response.message();
  //  Toast t = new Toast(Current);
   // t.setText("Mainfest Created Sucessfully");
    Toast.makeText(Current,"Mainfest Created Sucessfully", Toast.LENGTH_LONG).show();

}
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            String s = t.getMessage();
                Object e = t.getStackTrace();
                Toast t1 = new Toast(Current);
                t1.setText("Error Creating Mainfest");
                t1.show();

            }
        });

       final ListView lview = getListView();
        final ArrayList<CNNoteDetails> CND = null;
        String StartDate,EndDate,Cities;
        //    StartDate="2016-04-04";
        //   EndDate="2016-07-31";
        StartDate = sdf.format(mDate);
        EndDate = sdf.format(mEndDate);

        Cities="    2,3,4,5,6";

       // StartDate = mDate.toString();
        //EndDate = mEndDate.toString();
        Call<List<CNNoteDetails>> cnotecb = ServiceHub.createRetrofitService().GetCNNotesDetailsForManifest(StartDate,EndDate,Cities);
        cnotecb.enqueue(new Callback<List<CNNoteDetails>>() {
            @Override
            public void onResponse(Call<List<CNNoteDetails>> call, Response<List<CNNoteDetails>> response) {

             //   ArrayList<CNNoteDetails> cnd = < response;
                List<CNNoteDetails> cnd = response.body();
                madapter = new ManifestviewAdapter(Current,cnd,pd,MId);
                lview.setAdapter(madapter);
                pd.hide();
            }

            @Override
            public void onFailure(Call<List<CNNoteDetails>> call, Throwable t) {

            }
        });
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "test";
            }
        });


      final  Manifestrepo mRepo = new Manifestrepo();


        final ManifestDetail MDREsp;
       // final Callback<ResponseBody>[] cb1 = new Callback<ResponseBody>[0];
        FloatingActionButton fab = (FloatingActionButton ) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // pd.show();


                Callback<List<ManifestDetail>> cb =new Callback<List<ManifestDetail>>() {
                    @Override
                    public void onResponse(Call<List<ManifestDetail>> call, final Response<List<ManifestDetail>> response) {

                        if (response.isSuccessful())
                        {
                            Toast.makeText(Current,response.message(), Toast.LENGTH_LONG).show();
                            pd.hide();
                          //  MDREsp = response.body();
                           Callback<List<ManifestItemDetails>> cb1 = new Callback<List<ManifestItemDetails>>() {
                                @Override
                                public void onResponse(Call<List<ManifestItemDetails>> call1, Response<List<ManifestItemDetails>> response1) {
                                    ManifestPDF mPDF = new ManifestPDF(Current);
                                   mPDF.PrintManifest(response.body().get(0),response1.body());
                                }

                                @Override
                                public void onFailure(Call<List<ManifestItemDetails>> call1, Throwable t) {

                                }
                            };
                            //mRepo.GetManifestItemDetails("12072016234203MUMBAITata Truck", cb1);
                            mRepo.GetManifestItemDetails(MId,cb1);


                        }
                    }

                    @Override
                    public void onFailure(Call<List<ManifestDetail>> call, Throwable t) {
                        Log.d("sr", t.getMessage());
                        Toast.makeText(Current,t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                };

                mRepo.GetManifestDetails(MId,cb);

            }

        });

mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = "t";
    }
});


       /* SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        mList,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                pd.show();
                                for (int position : reverseSortedPositions) {
                                    //adapter.remove((Integer) adapter.getItem(position));
                                    CNNoteDetails cn = (CNNoteDetails) madapter.getItem(position);
                                    cn.setLodedBag(1);
                                    if (cn.getLodedBag() == cn.getPackageNo()) {
                                        //cn.setDispPackageNo();
                                        madapter.remove(position);}
                                    else
                                    {
                                        //cn.setDispPackageNo();
                                        ManifestItem mi = new ManifestItem();
                                        mi.setCNoteNo(cn.getCNNumber());
                                        mi.setLoadedQuantity(1);
                                        madapter.updateDispQty(position);
                                                mi.setManifestId(MId);
                                        Call<ResponseBody> cbmi = ServiceHub.createRetrofitService().CreateManifestItem(mi);
                                        cbmi.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful() ){
                                                    pd.hide();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                            }
                                        });


                                    }

                                }
                                madapter.notifyDataSetChanged();
                            }
                        });
        mList.setOnTouchListener(touchListener);*/



    }


    public ListView getListView() {
        ensureList();
        return mList;
    }

    private void ensureList() {
        if (mList != null) {
            return;
        }
        // setContentView(com.android.internal.R.layout.list_content_simple);
        setContentView(R.layout.activity_manifest_items);
    }
}
