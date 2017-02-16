package srmicrosystems.cnote.Repository;



import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import srmicrosystems.cnote.Model.Manifest;
import srmicrosystems.cnote.Model.ManifestDetail;
import srmicrosystems.cnote.Model.ManifestItem;
import srmicrosystems.cnote.Model.ManifestItemDetails;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

/**
 * Created by saman_000 on 11-06-2016.
 */
public class Manifestrepo {

    public  boolean CreateManifest(Callback<ResponseBody> cb, Manifest manifest){
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<ResponseBody> SMRepo = sh.CreateManifest(manifest);
        SMRepo.enqueue(cb);
      return  true;
    }

    public void GetManifestDetails(String mid,Callback<List<ManifestDetail>> cb){
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<ManifestDetail>> resp = sh.GetManifestDetailById(mid);
        resp.enqueue(cb);

    }

    public void GetManifestItemDetails(String mid,Callback<List<ManifestItemDetails>> cb){
        IServiceHub sh = ServiceHub.createRetrofitService();
        Call<List<ManifestItemDetails>> resp = sh.GetManifestItemDetailById(mid);
        resp.enqueue(cb);

    }
}
