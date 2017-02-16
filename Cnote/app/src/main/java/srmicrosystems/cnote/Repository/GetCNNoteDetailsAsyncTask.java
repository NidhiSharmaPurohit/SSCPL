package srmicrosystems.cnote.Repository;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import srmicrosystems.cnote.Model.CNNoteDetails;
import srmicrosystems.cnote.Model.CNNoteDetailsExt;

/**
 * Created by saman_000 on 26-11-2016.
 */
public class GetCNNoteDetailsAsyncTask  extends  AsyncTask<Object,Context,CNNoteDetailsExt>   {


    @Override
    protected CNNoteDetailsExt doInBackground(Object... params) {
        CNNoteRepo repo = new CNNoteRepo();
        CNNoteDetailsExt result = null ;

        try
        {
            result=  repo.GetCNNoteDetailsExt((Context) params[0],(String) params[1]);
        }
        catch(Exception ex)
        {

        }

        return  result;

    }


}
