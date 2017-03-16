package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import srmicrosystems.cnote.Model.CNNoteDetailsExt;

/**
 * Created by User on 3/13/2017.
 */
public class CNNoteListDetailsAsyncTask extends AsyncTask<Object,Context,List<CNNoteDetailsExt> > {


    @Override
    protected List<CNNoteDetailsExt> doInBackground(Object... params) {
        CNNoteRepo repo = new CNNoteRepo();
        List<CNNoteDetailsExt> result = null ;

        try
        {
            result=  repo.ListOfCNNoteDetailsExt((String) params[1]);
        }
        catch(Exception ex)
        {

        }

        return  result;

    }


}
