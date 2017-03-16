package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.os.AsyncTask;

import srmicrosystems.cnote.Model.PaymentDetailsWithTaxForCnote;

/**
 * Created by User on 2/20/2017.
 */
public class GetCNNPaymentDetailswithTaxAsyncTask extends AsyncTask<Object,Context,PaymentDetailsWithTaxForCnote> {

    @Override
    protected PaymentDetailsWithTaxForCnote doInBackground(Object... params) {
        CNNotePaymentRepo repo = new CNNotePaymentRepo();
        PaymentDetailsWithTaxForCnote result = null ;

        try
        {
            result=  repo.GetPaymentDetailsWithTax((String) params[1]);
        }
        catch(Exception ex)
        {

        }

        return  result;

    }

}
