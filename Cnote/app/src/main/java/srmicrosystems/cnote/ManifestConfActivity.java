package srmicrosystems.cnote;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import srmicrosystems.cnote.Model.Adapters.CarrierSpinerAdapter;
import srmicrosystems.cnote.Model.Adapters.CityAdapter;
import srmicrosystems.cnote.Model.Adapters.CitySpinerAdapter;
import srmicrosystems.cnote.Model.CarrierType;
import srmicrosystems.cnote.Model.City;
import srmicrosystems.cnote.Repository.CarrierTypeRepo;
import srmicrosystems.cnote.Repository.CityRepo;

public class ManifestConfActivity extends AppCompatActivity {
    ListView list;
    ListViewAdapter listviewadapter;
    View.OnClickListener ckl;
     GridView gv1;
    CityAdapter ca;
    CityAdapter aca;
   // private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private TextView EndDateView;
    private int year, month, day;
    private  String currentDV;

    public void showDatePickerDialog(View v) {
     //   DialogFragment newFragment = new DatePickerFragment();
       // newFragment.show(getFragmentManager(),"datePicker");
        currentDV = "SD";
        DatePickerDialog dp =  new DatePickerDialog(this, mDateSetListener, year, month, day);
        dp.show();

    }

    public void showEndDatePickerDialog(View v) {
        //   DialogFragment newFragment = new DatePickerFragment();
        // newFragment.show(getFragmentManager(),"datePicker");
        currentDV = "ED";
        DatePickerDialog dp =  new DatePickerDialog(this, mDateSetListener, year, month, day);
        dp.show();

    }

    private void showDate(int year, int month, int day,TextView dv) {
        dv.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    year = year;
                    month = monthOfYear;
                    day = dayOfMonth;
                    if (currentDV.equals("SD") )
                    {
                        showDate(year,month+1,day,dateView);
                    }
                    else
                    {
                        showDate(year,month+1,day,EndDateView);
                    }


                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifest_conf);
        dateView = (TextView) findViewById(R.id.Manidate);
        EndDateView= (TextView) findViewById(R.id.ManiEnddate);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day,dateView);
        showDate(year,month+1,day,EndDateView);
        CityRepo c = new CityRepo();
        CarrierTypeRepo ctrepo = new CarrierTypeRepo();
        ArrayList<City> city = new ArrayList<City> (c.GetAllCity(this));
        ArrayList<CarrierType> ct = new ArrayList<CarrierType> (ctrepo.GetAllCarrierType(this));

        CitySpinerAdapter cadp = new CitySpinerAdapter(this,R.layout.support_simple_spinner_dropdown_item, city);
       // CitySpinerAdapter ACityAdp = new CitySpinerAdapter(this,R.layout.)
        CarrierSpinerAdapter ctADP = new CarrierSpinerAdapter(this,R.layout.support_simple_spinner_dropdown_item,ct);
        //CitySpinerAdapter cityAddADP = new CitySpinerAdapter(this,R.layout., city);
        final Spinner scity = (Spinner) findViewById(R.id.SCity);
        final Spinner dcity = (Spinner) findViewById(R.id.DCity);
        final Spinner adCity =(Spinner) findViewById(R.id.ADCity);
        final Spinner ctSpn = (Spinner) findViewById(R.id.gadi) ;
 //gv1 = (GridView) findViewById(R.id.gv);

        list = (ListView) findViewById(R.id.listview);
       /* MultiSelectionSpinner mySpinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner1);
        mySpinner.setAdapter(cadp);*/

      //  listviewadapter = new ListViewAdapter(this, R.layout.listview_item, city);
       // ListAdapter la ;
      ca = new CityAdapter(this,R.layout.gvlayout,city);
        /*gv1.setAdapter(ca  );
        gv1.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gv1.setMultiChoiceModeListener(new MultiChoiceModeListener());*/
        scity.setAdapter(cadp);
        dcity.setAdapter(cadp);
        adCity.setAdapter(cadp);
        ctSpn.setAdapter(ctADP);
Button b1;
        b1 = (Button) findViewById(R.id.btmGenM)    ;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ManifestConfActivity.this ,srmicrosystems.cnote.ManifestGenActivity.class);
             //   Object o= list.getSelectedItem();
                in.putExtra("SCity",((City) scity.getSelectedItem()).getCityId());
                in.putExtra("DCity",((City) dcity.getSelectedItem()).getCityId());
               // in.putExtra("DCity",((City) scity.getSelectedItem()).getCityId());
                in.putExtra("gadi",((CarrierType) ctSpn.getSelectedItem()).getId());
                in.putExtra("mDate",((TextView) findViewById(R.id.Manidate)).getText());
                in.putExtra("mEndDate",((TextView) findViewById(R.id.ManiEnddate)).getText()  );
                startActivity(in);
            }
        });
        Button b2 ;




    }




   /* public class MultiChoiceModeListener implements
            GridView.MultiChoiceModeListener {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Select Items");
            mode.setSubtitle("One item selected");
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        *//*public void onItemCheckedStateChanged(ActionMode mode, int position,
                                              long id, boolean checked) {
            int selectCount = gv1.getCheckedItemCount();
            ca.SetSelected(position,checked);
            ca.notifyDataSetChanged();
        //    gv1.notify();
            if (checked)
            {
            }
            else
            {}
            switch (selectCount) {
                case 1:
                    mode.setSubtitle("One item selected");
                    break;
                default:
                    mode.setSubtitle("" + selectCount + " items selected");
                    break;
            }
        }*//*

    }
*/

    /*View.OnClickListener ckl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            list.setAdapter(listviewadapter);
            list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

            list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                @Override
                public void onItemCheckedStateChanged(ActionMode mode,
                                                      int position, long id, boolean checked) {
                    // Capture total checked items
                    final int checkedCount = list.getCheckedItemCount();
                    // Set the CAB title according to total checked items
                    mode.setTitle(checkedCount + " Selected");
                    // Calls toggleSelection method from ListViewAdapter Class
                    listviewadapter.toggleSelection(position);
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
				*//*case R.id.delete:
					// Calls getSelectedIds method from ListViewAdapter Class
					SparseBooleanArray selected = listviewadapter
							.getSelectedIds();
					// Captures all selected ids with a loop
					for (int i = (selected.size() - 1); i >= 0; i--) {
						if (selected.valueAt(i)) {
							WorldPopulation selecteditem = listviewadapter
									.getItem(selected.keyAt(i));
							// Remove selected items following the ids
							listviewadapter.remove(selecteditem);
						}
					}
					// Close CAB
					mode.finish();
					return true;*//*
                        default:
                            return false;
                    }
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.activity_main, menu);
                    return true;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    // TODO Auto-generated method stub
                    listviewadapter.removeSelection();
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    // TODO Auto-generated method stub
                    return false;
                }
            });

        }
    };*/
}
