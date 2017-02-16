package srmicrosystems.cnote;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import srmicrosystems.cnote.Model.City;

public class ListViewAdapter extends ArrayAdapter<City> {
	// Declare Variables
	Context context;
	LayoutInflater inflater;
	List<City> worldpopulationlist;
	private SparseBooleanArray mSelectedItemsIds;

	public ListViewAdapter(Context context, int resourceId,
			List<City> worldpopulationlist) {
		super(context, resourceId, worldpopulationlist);
		mSelectedItemsIds = new SparseBooleanArray();
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
	}

	private class ViewHolder {
		TextView rank;
		TextView country;
		TextView population;
		ImageView flag;
	}

	public View getView(int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.rank = (TextView) view.findViewById(R.id.rank);
		//	holder.country = (TextView) view.findViewById(R.id.country);
		//	holder.population = (TextView) view.findViewById(R.id.population);
			// Locate the ImageView in listview_item.xml
			//holder.flag = (ImageView) view.findViewById(R.id.flag);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Capture position and set to the TextViews
		holder.rank.setText(worldpopulationlist.get(position).getCityName());
		if( mSelectedItemsIds.get(position) ) {
			holder.rank.setTextColor(Color.GREEN);
		}
		else
		{
			holder.rank.setTextColor(Color.YELLOW);
		}
//		holder.country.setText(worldpopulationlist.get(position).getCountry());
//		holder.population.setText(worldpopulationlist.get(position)
//				.getPopulation());
		// Capture position and set to the ImageView
		/*holder.flag.setImageResource(worldpopulationlist.get(position)
				.getFlag());*/
		return view;
	}

	@Override
	public void remove(City object) {
		worldpopulationlist.remove(object);
		notifyDataSetChanged();
	}

	public List<City> getWorldPopulation() {
		return worldpopulationlist;
	}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);
		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return mSelectedItemsIds.size();
	}

	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}
}