package srmicrosystems.cnote.Model.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Model.TransportMode;

/**
 * Created by saman_000 on 03-07-2016.
 */
public class TranportModeSpinerADP implements SpinnerAdapter {
    private ArrayList<TransportMode> items;
    private ArrayList<TransportMode> itemsAll;
    private ArrayList<TransportMode> suggestions;
    private int viewResourceId;
    Context context;
    public TranportModeSpinerADP(Context ctx, int resource, ArrayList<TransportMode> objects) {
        // super(context, resource, objects);
        super();
        this.items = objects;
        this.itemsAll = (ArrayList<TransportMode>) items.clone();
        this.suggestions = new ArrayList<TransportMode>();
        this.viewResourceId = resource;
        context = ctx;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(viewResourceId, null);

        TransportMode tm = items.get(position);
        if (tm != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(tm.getModee());
            }
        }
        return v;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public TransportMode getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt( items.get(position).getModelId());
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(viewResourceId, null);

        TransportMode tm = items.get(position);
        if (tm != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(tm.getModee());
            }
        }
        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
