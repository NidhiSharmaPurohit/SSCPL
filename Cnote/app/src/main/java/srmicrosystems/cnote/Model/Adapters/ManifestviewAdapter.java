package srmicrosystems.cnote.Model.Adapters;

/**
 * Created by saman_000 on 03-05-2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.locks.Lock;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.CNNoteDetails;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.ManifestItem;
import srmicrosystems.cnote.R;
import srmicrosystems.cnote.Repository.CityRepo;
import srmicrosystems.cnote.Service.ServiceHub;

/**
 *
 * @author Paresh N. Mayani
 */
public class ManifestviewAdapter extends BaseAdapter

{
    public List<CNNoteDetails> list;
    public String MID;
    Activity activity;
public  List<City> Cities;
 public   ProgressDialog pd;
    public ManifestviewAdapter(Activity activity, List<CNNoteDetails> list,ProgressDialog pd1,String MID1) {
        super();
        this.activity = activity;
        this.list = list;
        CityRepo cr = new CityRepo();
        Cities = cr.GetAllCity(activity);
        pd=pd1;
        MID= MID1;
        for( CNNoteDetails t : this.list)
        {
            t.setintiDispPackageNo();
        }

    }

    public void updateDispQty(int Position)
    {
    list.get(Position).setDispPackageNo();
    }


    public void remove(int position){
       list.remove(position);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public CNNoteDetails getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtCNNumber;
        TextView txtBookingDate;
        TextView txtConsignerDetails;
        TextView txtfromcc;
        TextView txtdestcc;
        TextView txttqty;
        TextView txtuqty;
        TextView txtSCity;
        TextView txtDCity;
        TextView txtConsigneeName;
        EditText txtUplodqty;
        ImageButton btnFullLoad;
        ImageButton btnLoad;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        final ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_manifest_items, null);
            holder = new ViewHolder();
            holder.txtCNNumber = (TextView) convertView.findViewById(R.id.CNNumber);
            holder.txtBookingDate = (TextView) convertView.findViewById(R.id.BookingDate);
            holder.txtConsignerDetails = (TextView) convertView.findViewById(R.id.ConsignerDetails);
            holder.txtfromcc = (TextView) convertView.findViewById(R.id.fromcc);
            holder.txtdestcc = (TextView) convertView.findViewById(R.id.destcc);
            holder.txttqty=(TextView) convertView.findViewById(R.id.tqty);
            holder.txtuqty=(TextView) convertView.findViewById(R.id.uqty);
            holder.txtDCity =(TextView) convertView.findViewById(R.id.dsetcity);
            holder.txtSCity =(TextView) convertView.findViewById(R.id.fromc);
            holder.txtConsigneeName = (TextView) convertView.findViewById(R.id.tocon);
            holder.txtUplodqty = (EditText) convertView.findViewById(R.id.updateqty);
holder.btnFullLoad = (ImageButton) convertView.findViewById(R.id.UploadFULLBTN);
            holder.btnLoad = (ImageButton) convertView.findViewById((R.id.UploadBTN));
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
try {
   final CNNoteDetails map = list.get(position);
 //   map.setintiDispPackageNo();
    holder.txtCNNumber.setText(map.getCNNumber());
    holder.txtBookingDate.setText( map.getBookingDate().toString());
    holder.txtConsignerDetails.setText(map.getShipperCompany());
    holder.txtSCity.setText( "("+ map.getSourceCity() +")");
    holder.txtDCity.setText("(" + map.getDestCity() + ")");
    holder.txtuqty.setText(Integer.toString(  map.getDispPackageNo()));
    holder.txttqty.setText(Integer.toString(map.getPackageNo()));
    holder.btnFullLoad.setTag(position);
    holder.btnLoad.setTag(position);
       holder.txtConsigneeName.setText(map.getConsigneeCompany());
    for(int i=0; i<Cities.size();i++) {
        if (Cities.get(i).getCityId() ==  map.getOriginCityID())
        {
            holder.txtdestcc.setText(Cities.get(i).getCityCode());
        }
    }

    for(int i=0; i<Cities.size();i++) {
        if (Cities.get(i).getCityId() == map.getDestCityID())
        {
            holder.txtdestcc.setText(Cities.get(i).getCityCode());
        }
    }


holder.btnLoad.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pd.show();
        final int position=(Integer)  v.getTag();
        map.setLodedBag(Integer.parseInt(holder.txtUplodqty.getText().toString()));
        ManifestItem mi = new ManifestItem();
        mi.setCNoteNo(map.getCNNumber());
        mi.setLoadedQuantity(map.getLodedBag());
        updateDispQty(position);
        mi.setManifestId(MID);
        Call<ResponseBody> cbmi = ServiceHub.createRetrofitService().CreateManifestItem(mi);
        cbmi.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() ){

                    synchronized (list){
                        if(map.getLodedBag() == map.getTOTAL()){
                        list.remove(position);
                       }
                        list.notifyAll();
                        notifyDataSetChanged();
                    }
                    synchronized (this){
                        this.notifyAll();
                    }
                    pd.hide();

                }
                else
                {
                    synchronized (list){
                     //   list.remove(position);
                        list.notifyAll();
                        notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                synchronized (list){
                    list.remove(position);
                    list.notifyAll();
                    notifyDataSetChanged();

                }
                synchronized (this){
                    this.notifyAll();
                    //  notifyDataSetChanged();
                }
                pd.hide();
            }
        });
    }});

    holder.btnFullLoad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
pd.show();
            final int position=(Integer)  v.getTag();
           // CNNoteDetails cn = (CNNoteDetails) madapter.getItem(position);
            map.setLodedBag((int) map.getTOTAL());
            ManifestItem mi = new ManifestItem();
            mi.setCNoteNo(map.getCNNumber());
            mi.setLoadedQuantity(map.getLodedBag());
            updateDispQty(position);
            mi.setManifestId(MID);
            Call<ResponseBody> cbmi = ServiceHub.createRetrofitService().CreateManifestItem(mi);
            cbmi.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() ){

                        synchronized (list){
                        list.remove(position);
                        list.notifyAll();
                            notifyDataSetChanged();
                        }
                        synchronized (this){
                            this.notifyAll();
                        }
                        pd.hide();

                    }
                    else
                    {
                        synchronized (list){
                            list.remove(position);
                            list.notifyAll();
notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    synchronized (list){
                        list.remove(position);
                        list.notifyAll();
                        notifyDataSetChanged();

                    }
                    synchronized (this){
                        this.notifyAll();
                      //  notifyDataSetChanged();
                    }
                    pd.hide();
                }
            });
        }
    });


}
catch (Exception e)
{
    Log.d("sa",e.getMessage());
}
        return convertView;
    }

}