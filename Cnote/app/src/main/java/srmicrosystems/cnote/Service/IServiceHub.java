package srmicrosystems.cnote.Service;

import java.util.List;
import java.io.File;

import retrofit2.http.Query;
import srmicrosystems.cnote.Model.CNNoteDetails;
import srmicrosystems.cnote.Model.CNNoteDetailsExt;
import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.Manifest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Multipart;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.Company;
import srmicrosystems.cnote.Model.ManifestDetail;
import srmicrosystems.cnote.Model.ManifestItem;
import srmicrosystems.cnote.Model.ManifestItemDetails;
import srmicrosystems.cnote.Model.PackagingMode;
import srmicrosystems.cnote.Model.TransportMode;

/**
 * Created by saman_000 on 12-05-2016.
 */
public interface IServiceHub {
    @GET("cnnotes")
    Call<List<CNNote>> listRepos();

    @GET("cnnotesdetails")
    Call<List<CNNoteDetails>> GetCNNDetails();

    @GET("city")
    Call<List<City>> GetCity();

    @GET("company")
    Call<List<Company>> GetCompany();

@PUT("cnnotes")
Call<CNNote> CreateDeatils(@Body CNNote user);

    @Multipart
     @POST("uploadCNNOtesSignImage")
    Call<ResponseBody> Upload(@Part("description") RequestBody description,@Part MultipartBody.Part file);
    //  @Headers("enctype: multipart/form-data")


    @PUT("Manifest")
    Call<ResponseBody> CreateManifest(@Body Manifest manifest);

    @POST("ManifestItem")
    Call<ResponseBody> CreateManifestItem(@Body ManifestItem manifest);


    @GET("CarrierType")
    Call<List<CarrierType>> GetCarrierType();

    @GET("GetCNNotesDetailsForManifest")
    Call<List<CNNoteDetails>> GetCNNotesDetailsForManifest(@Query("StartDate") String StartDate, @Query("EndDate") String EndDate, @Query("Cities") String Cities);

    @GET("TransportMode")
    Call<List<TransportMode>> GetTransportMode();

    @GET("PackagingMode")
    Call<List<PackagingMode>> GetPackagingMode();

    @GET("ManifestDetailById/{ManifestId}")
    Call<List<ManifestDetail>> GetManifestDetailById(@Path("ManifestId") String ManifestId);

    @GET("ManifestItemDetail/{ManifestId}")
    Call<List<ManifestItemDetails>> GetManifestItemDetailById(@Path("ManifestId") String ManifestId);

    @GET("GetCNNoteDetailForManifest/{ManifestId}")
    Call<List<CNNoteDetailsExt>> GetCNNoteDetailByManifestId(@Path("ManifestId") String ManifestId);

    @GET("CNNotesFullDetails/{CNNote}")
    Call<CNNoteDetailsExt> GetCNNoteDetailsExt(@Path("CNNote") String CNNote);

}
