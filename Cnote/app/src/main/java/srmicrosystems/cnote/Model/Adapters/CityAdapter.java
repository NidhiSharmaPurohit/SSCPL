package srmicrosystems.cnote.Model.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.R;

/**
 * Created by saman_000 on 13-05-2016.
 */
public class CityAdapter extends ArrayAdapter<City> {

    private ArrayList<City> items;
    private ArrayList<City> itemsAll;
    private ArrayList<City> suggestions;
    private int viewResourceId;

private HashMap<Integer,Boolean> mSelectedItems = new HashMap<>();

    public CityAdapter(Context context, int resource, ArrayList<City> objects) {
        super(context, resource, objects);

        this.items = objects;
        this.itemsAll = (ArrayList<City>) items.clone();
        this.suggestions = new ArrayList<City>();
        this.viewResourceId = resource;


    }

    public String GetSelecteditems() {
      String Selectedcity="";
       for(int i=0; i<= mSelectedItems.size();i++)
       {
        if( mSelectedItems.containsKey(i)){
            if (Selectedcity.length() > 0 )
            Selectedcity= Selectedcity + ","+  items.get(i).getCityId();
            else
                Selectedcity= Integer.toString(  items.get(i).getCityId());


       }
       }
        return Selectedcity;
    }
public void SetSelected(int postion,boolean checked)
{
    if(  mSelectedItems.containsKey(postion))
    {
        mSelectedItems.remove(postion);
    }

        mSelectedItems.put(postion,checked);


}

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        RelativeLayout p=null;
        TextView productLabel=null;
        City city = items.get(position);
        if (city != null) {
             productLabel = (TextView)  v.findViewById(R.id.text1);
            p = (RelativeLayout) v.findViewById(R.id.gvparent);
            if (productLabel != null) {

                productLabel.setText(city.getCityName());
            }
        }

      //  p.setBackgroundColor(Color.GREEN);
        productLabel.setTextColor(Color.YELLOW);

        if (mSelectedItems.containsKey(position)  && mSelectedItems.get(position) )

        {
          //  p.setBackgroundColor(Color.RED);
            productLabel.setTextColor(Color.RED);

        }


        return v;
    }



    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            String str = ((City) (resultValue)).getCityName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (City city : itemsAll) {
                    if (city.getCityName().toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(city);
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
            ArrayList<City> filteredList = (ArrayList<City>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (City c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

