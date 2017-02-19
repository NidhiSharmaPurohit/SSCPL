package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

import srmicrosystems.cnote.Model.AirFlight;
import srmicrosystems.cnote.Model.Company;

/**
 * Created by User on 2/19/2017.
 */
public class AirFlightADP extends ArrayAdapter<AirFlight> {

    private ArrayList<AirFlight> items;
    private ArrayList<AirFlight> itemsAll;
    private ArrayList<AirFlight> suggestions;
    private int viewResourceId;



    public AirFlightADP(Context context, int resource, ArrayList<AirFlight> objects) {
        super(context, resource, objects);

        this.items = objects;
        this.itemsAll = (ArrayList<AirFlight>) items.clone();
        this.suggestions = new ArrayList<AirFlight>();
        this.viewResourceId = resource;


    }



    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
//v.setBackgroundColor(0xFF00FF00);
        }
        AirFlight air = items.get(position);
        if (air != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
//productLabel.setTextColor(1);
            if (productLabel != null) {
                productLabel.setTextSize(25);
                productLabel.setPadding(0,10,0,10);
                productLabel.setText(air.getFlightName() + " " + air.getFlightNumber() + " " + air.getDestCity());
            }
        }
        return v;
    }



    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            AirFlight air = (AirFlight)resultValue;

            String str = air.getFlightName() + " " + air.getFlightNumber() + " " + air.getDestCity();
            //String str = air.getFlightName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (AirFlight air : itemsAll) {
                    if (air.getFlightName().toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(air);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            @SuppressWarnings("unchecked")
            ArrayList<AirFlight> filteredList = (ArrayList<AirFlight>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (AirFlight c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

