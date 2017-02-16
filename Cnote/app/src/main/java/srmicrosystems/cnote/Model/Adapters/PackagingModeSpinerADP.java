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
import srmicrosystems.cnote.Model.PackagingMode;

/**
 * Created by User on 2/11/2017.
 */
public class PackagingModeSpinerADP implements SpinnerAdapter {
    private ArrayList<PackagingMode> items;
    private ArrayList<PackagingMode> itemsAll;
    private ArrayList<PackagingMode> suggestions;
    private int viewResourceId;
    Context context;
    public PackagingModeSpinerADP(Context ctx, int resource, ArrayList<PackagingMode> objects) {
        // super(context, resource, objects);
        super();
        this.items = objects;
        this.itemsAll = (ArrayList<PackagingMode>) items.clone();
        this.suggestions = new ArrayList<PackagingMode>();
        this.viewResourceId = resource;
        context = ctx;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(viewResourceId, null);

        PackagingMode pm = items.get(position);
        if (pm != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(pm.getTypee());
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
    public PackagingMode getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt( items.get(position).getId());
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

        PackagingMode pm = items.get(position);
        if (pm != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(pm.getTypee());
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

