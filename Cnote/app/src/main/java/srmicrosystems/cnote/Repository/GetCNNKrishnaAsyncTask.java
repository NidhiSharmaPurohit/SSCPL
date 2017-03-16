package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.os.AsyncTask;

import srmicrosystems.cnote.Model.PaymentDetailsForCnote;



/**
 * Created by User on 3/3/2017.
 */
public class GetCNNKrishnaAsyncTask extends AsyncTask<Object,Context,PaymentDetailsForCnote> {

    @Override
    protected PaymentDetailsForCnote doInBackground(Object... params) {
        CNNotePaymentRepo repo = new CNNotePaymentRepo();
        PaymentDetailsForCnote result = null ;

        try
        {
            result=  repo.GetPaymentDetailsForCnote((String) params[1]);
        }
        catch(Exception ex)
        {

        }

        return  result;

    }
}
