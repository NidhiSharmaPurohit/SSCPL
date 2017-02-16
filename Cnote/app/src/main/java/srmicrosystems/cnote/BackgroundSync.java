package srmicrosystems.cnote;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import srmicrosystems.cnote.Model.CNNote;
import srmicrosystems.cnote.Model.SQL.CNNoteSQLHelper;
import srmicrosystems.cnote.Repository.CNNoteRepo;

/**
 * Created by saman_000 on 31-08-2016.
 */
public class BackgroundSync extends IntentService {

  /*  public void onCreate(){
        super.onCreate();
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this,"oncreate", Toast.LENGTH_LONG).show();

    }*/
  private boolean isNetworkAvailable() {
      ConnectivityManager connectivityManager
              = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
      return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
public BackgroundSync()

{

    super("");
    Log.d("thesr","Initialized");
    try {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.box_icon)
                        .setContentTitle("Background Sync SVC")
                        .setContentText("Background Sync SVC Staarted!");

        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify( Counter.NotificationCounter.GetNotificationCounter(),mBuilder.build());

    //    getResources().getInteger(R.integer.NotificationCounter)

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("thesr","Handle Intent");
        try {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.box_icon)
                            .setContentTitle("Background Sync SVC")
                            .setContentText("Background Sync SVC Staarted!");

            Intent resultIntent = new Intent(this, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify( Counter.NotificationCounter.GetNotificationCounter(),mBuilder.build());
            if (isNetworkAvailable()) {
                SyncCNOTE(this);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Callback<CNNote> cbcn ;
  private void SyncCNOTE(Context c){
      final SQLiteOpenHelper CNnoteHelper;
     final Context context;
      context = c;
      CNnoteHelper = new CNNoteSQLHelper(context);
//Boolean s = CNNoteSQLHelper.DeleteAllCnNote(CNnoteHelper.getWritableDatabase());
      ArrayList<CNNote> CNs=      CNNoteSQLHelper.getAppCategoryDetail(CNnoteHelper.getReadableDatabase());
      CNNoteRepo CNRepo = new CNNoteRepo();


      cbcn = new Callback<CNNote>() {
          @Override
          public void onResponse(Call<CNNote> call, Response<CNNote> response) {
              if (response.isSuccessful())
              {

                  CNNoteSQLHelper.UpdateCnNoteStatus(CNnoteHelper.getWritableDatabase(),response.body().getCNNumber());
                  NotificationCompat.Builder mBuilder =
                          new NotificationCompat.Builder(context)
                                  .setSmallIcon(R.drawable.box_icon)
                                  .setContentTitle("CNNote Update Successfully")
                                  .setContentText(response.body().getCNNumber() + " Synced to Server Successfully");

                  Intent resultIntent = new Intent(context, MainActivity.class);

                  TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
                  stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                  stackBuilder.addNextIntent(resultIntent);
                  PendingIntent resultPendingIntent =
                          stackBuilder.getPendingIntent(
                                  0,
                                  PendingIntent.FLAG_UPDATE_CURRENT
                          );
                  mBuilder.setContentIntent(resultPendingIntent);
                  NotificationManager mNotificationManager =
                          (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                  mNotificationManager.notify(Counter.NotificationCounter.GetNotificationCounter(),mBuilder.build());
              }
          }

          @Override
          public void onFailure(Call<CNNote> call, Throwable t) {
              Toast.makeText(context,"Error Occured while Creating the CNOTE" + t.getMessage() , Toast.LENGTH_LONG).show();

          }
      };

      for (CNNote cn:CNs ) {

          CNRepo.CreateCNNNotes(cbcn,cn);

      }
  }
}
