package srmicrosystems.cnote;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.SQL.CNNoteSQLHelper;
import srmicrosystems.cnote.Service.ServiceHub;

public class MainActivity extends BaseActivity{
    public void copyAppDbToDownloadFolder() throws IOException {
        File backupDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "toDatabaseName"); // for example "my_data_backup.db"
        File currentDB = getApplicationContext().getDatabasePath("SSCPL.db"); //databaseName=your current application database name, for example "my_data.db"
        if (currentDB.exists()) {
            FileChannel src = new FileInputStream(currentDB).getChannel();
            FileChannel dst = new FileOutputStream(backupDB).getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SR","pon done");

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
*/

        try {
            copyAppDbToDownloadFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button b1,b2,b3,b4,b5,b6;
        //startService(new Intent(this,BackgroundSync.class));

        b1 = (Button) findViewById(R.id.btnCNote);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.CnoteActivity.class);
                startActivity(in);
            }
        });

        b2 = (Button) findViewById(R.id.btnCreateM);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.ManifestConfActivity.class);
                startActivity(in);
            }
        });

        b4 = (Button) findViewById(R.id.btnSearchM);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.GetManifestActivity.class);
                startActivity(in);
            }
        });

        b3 = (Button) findViewById(R.id.btnSync);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.SyncActivity.class);
                startActivity(in);
            }
        });

        b5 = (Button) findViewById(R.id.btnEditM1);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.CNNoteExtDetailsActivity.class);
                startActivity(in);
            }
        });


        b6 = (Button) findViewById(R.id.btnSearchCNote);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.SearchCNNoteActivity.class);
                startActivity(in);
            }
        });

    }

  /*  @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent in=new Intent(MainActivity.this ,srmicrosystems.cnote.CnoteActivity.class);
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
