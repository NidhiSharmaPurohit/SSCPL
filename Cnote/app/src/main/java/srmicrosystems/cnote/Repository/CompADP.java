package srmicrosystems.cnote.Repository;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.Filter;

import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.Company;

/**
 * Created by saman_000 on 21-05-2016.
 */
public class CompADP extends ArrayAdapter<Company> {

    private ArrayList<Company> items;
    private ArrayList<Company> itemsAll;
    private ArrayList<Company> suggestions;
    private int viewResourceId;



    public CompADP(Context context, int resource, ArrayList<Company> objects) {
        super(context, resource, objects);

        this.items = objects;
        this.itemsAll = (ArrayList<Company>) items.clone();
        this.suggestions = new ArrayList<Company>();
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
        Company company = items.get(position);
        if (company != null) {
            TextView productLabel = (TextView)  v.findViewById(android.R.id.text1);
//productLabel.setTextColor(1);
            if (productLabel != null) {
                productLabel.setTextSize(25);
                productLabel.setPadding(0,10,0,10);
                productLabel.setText(company.getCompanyName());
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
            String str = ((Company) (resultValue)).getCompanyName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Company company : itemsAll) {
                    if (company.getCompanyName().toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(company);
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
            ArrayList<Company> filteredList = (ArrayList<Company>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Company c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

    }
