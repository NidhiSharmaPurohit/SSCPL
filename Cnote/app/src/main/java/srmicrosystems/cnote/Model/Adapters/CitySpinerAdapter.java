package srmicrosystems.cnote.Model.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;

import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.R;

import static java.security.AccessController.getContext;

/**
 * Created by saman_000 on 16-06-2016.
 */
public class CitySpinerAdapter implements SpinnerAdapter {


    private ArrayList<City> items;
    private ArrayList<City> itemsAll;
    private ArrayList<City> suggestions;
    private int viewResourceId;
    Context context;
    public CitySpinerAdapter(Context ctx, int resource, ArrayList<City> objects) {
       // super(context, resource, objects);
super();
        this.items = objects;
        this.itemsAll = (ArrayList<City>) items.clone();
        this.suggestions = new ArrayList<City>();
        this.viewResourceId = resource;
        context = ctx;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(viewResourceId, null);

        City city = items.get(position);
        if (city != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setTextSize(25);
                productLabel.setPadding(0,10,0,10);
                productLabel.setText(city.getCityName());
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
    public City getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getCityId();
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

        City city = items.get(position);
        if (city != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
            if (productLabel != null) {
                productLabel.setText(city.getCityName());
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
