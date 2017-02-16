package srmicrosystems.cnote.Model.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.City;

/**
 * Created by saman_000 on 16-06-2016.
 */
public class CarrierSpinerAdapter implements SpinnerAdapter {


    private ArrayList<CarrierType> items;
    private ArrayList<CarrierType> itemsAll;
    private ArrayList<CarrierType> suggestions;
    private int viewResourceId;
    Context context;
    public CarrierSpinerAdapter(Context ctx, int resource, ArrayList<CarrierType> objects) {
       // super(context, resource, objects);
super();
        this.items = objects;
        this.itemsAll = (ArrayList<CarrierType>) items.clone();
        this.suggestions = new ArrayList<CarrierType>();
        this.viewResourceId = resource;
        context = ctx;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(viewResourceId, null);

        CarrierType carrierType = items.get(position);
        if (carrierType != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(carrierType.getCNumber());
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
    public CarrierType getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
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

        CarrierType carrierType = items.get(position);
        if (carrierType != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(carrierType.getCNumber());
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
