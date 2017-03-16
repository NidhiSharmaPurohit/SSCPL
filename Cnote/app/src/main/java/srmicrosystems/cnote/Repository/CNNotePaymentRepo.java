package srmicrosystems.cnote.Repository;

import android.content.Context;

import retrofit2.Call;
import srmicrosystems.cnote.Model.CNNoteDetailsExt;
import srmicrosystems.cnote.Model.PaymentDetailsForCnote;
import srmicrosystems.cnote.Model.PaymentDetailsWithTaxForCnote;
import srmicrosystems.cnote.Service.IServiceHub;
import srmicrosystems.cnote.Service.ServiceHub;

/**
 * Created by User on 2/20/2017.
 */
public class CNNotePaymentRepo {

    public PaymentDetailsWithTaxForCnote GetPaymentDetailsWithTaxForCnoteSync(Context ctx, String CNN) throws Exception{
        Object[] obj = new Object[2];
        obj[0] = ctx;
        obj[1] =CNN;

        return   new GetCNNPaymentDetailswithTaxAsyncTask().execute(obj).get();
    }

    public PaymentDetailsWithTaxForCnote GetPaymentDetailsWithTax(String Cnnote) throws Exception {
        IServiceHub sh = ServiceHub.createRetrofitService();

        Call<PaymentDetailsWithTaxForCnote> resp = sh.GetPaymentDetailsWithTax(Cnnote);
        //    resp.enqueue(cb);

        try{
            PaymentDetailsWithTaxForCnote result = resp.execute().body();
            return  result;
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }


    }

    public PaymentDetailsForCnote GetPaymentDetailsForCnoteSync(Context ctx, String CNN) throws Exception{
        Object[] obj = new Object[2];
        obj[0] = ctx;
        obj[1] =CNN;

        return   new GetCNNKrishnaAsyncTask().execute(obj).get();
    }

    public PaymentDetailsForCnote GetPaymentDetailsForCnote(String Cnnote) throws Exception {
        IServiceHub sh = ServiceHub.createRetrofitService();

        Call<PaymentDetailsForCnote> resp = sh.GetPaymentDetailsForCnnote(Cnnote);
        //    resp.enqueue(cb);

        try{
            PaymentDetailsForCnote result = resp.execute().body();
            return  result;
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }


    }

}
