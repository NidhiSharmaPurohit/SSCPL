package srmicrosystems.cnote.Model.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import srmicrosystems.cnote.Model.CNNoteDetails;
import srmicrosystems.cnote.R;

/**
 * Created by saman_000 on 18-09-2016.
 */

public class MasterSyncAdapter extends BaseAdapter{


    public MasterSyncAdapter(Activity act )
    {
        activity= act;
    }
public static int IDcounter=1;
public class SyncItem
{
    public String TaskName;
    public String TaskStatus;
    public Boolean TaskProgress;
    public int Id;

    public SyncItem(String tn,String ts,Boolean progressBar)
    {
        this.TaskName= tn;
        this.TaskProgress= progressBar;
        this.TaskStatus = ts;
        this.Id =IDcounter;
        IDcounter= IDcounter+1;
    }
}

    public SyncItem GetItem(String s ) {
        for (SyncItem i : Items) {
            if (i.TaskName == s) {
                return i;
            }
        }
        return  null;
    }

    public List<SyncItem> Items = new ArrayList<SyncItem>() ;
    public void InsertItems(String tn,String ts,Boolean progressBar)
    {
        Items.add(new SyncItem(tn,ts,true));
    }

    public void updateItem(int position,String s,Boolean pb){
        Items.get(position).TaskStatus = s;
        Items.get(position).TaskProgress = pb;

    }

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Items.get(position).Id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_master_sync, null);
            holder = new ViewHolder();
            holder.txtTaskName = (TextView) convertView.findViewById(R.id.TaskSync);
            holder.txtTaskStatus = (TextView) convertView.findViewById(R.id.TaskSyncStatus);
            holder.pbTaksProgress = (ProgressBar) convertView.findViewById(R.id.TaksProgress);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        final SyncItem map = Items.get(position);
        //   map.setintiDispPackageNo();
        holder.txtTaskName.setText(map.TaskName);
        holder.txtTaskStatus.setText( map.TaskStatus);
        if (map.TaskProgress) {
            holder.pbTaksProgress.setVisibility(View.VISIBLE);
            holder.pbTaksProgress.setIndeterminate(true);
        }
        else
            holder.pbTaksProgress.setVisibility(View.INVISIBLE);
        holder.pbTaksProgress.setIndeterminate(false);

        return convertView;
    }


    Activity activity;
    private class ViewHolder {
        TextView txtTaskStatus;
        TextView txtTaskName;
        ProgressBar pbTaksProgress;

    }

}
