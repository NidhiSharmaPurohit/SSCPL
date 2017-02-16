package srmicrosystems.cnote.Model.SQL;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import srmicrosystems.cnote.Model.CNNote;
import  srmicrosystems.cnote.Model.SQL.CNOTETable;
/**
 * Created by saman_000 on 27-08-2016.
 */
public class CNNoteSQLHelper extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SSCPL.db";
public  CNNoteSQLHelper(Context context)
{


    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CNOTETable.CNNoteEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CNOTETable.CNNoteEntry.SQL_DELETE_ENTRIES);
    }


    public  static  boolean DeleteAllCnNote(SQLiteDatabase sqLiteDatabase)
    {
        //String selectQuery = "Delete FROM " + CNOTETable.CNNoteEntry.TABLE_NAME ;
        //SQLiteDatabase db  = sqLiteDatabase;
       // Cursor cursor      = db.rawQuery(selectQuery, null);
        sqLiteDatabase.delete(CNOTETable.CNNoteEntry.TABLE_NAME,null,null);
        return true;
    }

    public  static  boolean UpdateCnNoteStatus(SQLiteDatabase sqLiteDatabase,String CNNumber)
    {
        String selectQuery = "Update " + CNOTETable.CNNoteEntry.TABLE_NAME + " set " + CNOTETable.CNNoteEntry.CN_Status + " = 'Synced' where " + CNOTETable.CNNoteEntry.CN_CNNumber + " = '"+ CNNumber + "'"  ;
        SQLiteDatabase db  = sqLiteDatabase;
         Cursor cursor      = db.rawQuery(selectQuery, null);
        //sqLiteDatabase.delete(CNOTETable.CNNoteEntry.TABLE_NAME,null,null);
        return true;
    }
    public static ArrayList<CNNote> getAppCategoryDetail(SQLiteDatabase sqLiteDatabase) {



        String selectQuery = "SELECT  * FROM " + CNOTETable.CNNoteEntry.TABLE_NAME + " where " +  CNOTETable.CNNoteEntry.CN_Status + " ='Initial'";
        SQLiteDatabase db  = sqLiteDatabase;
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<CNNote> data      = new ArrayList<CNNote>();
        String dat ="";
        if (cursor.moveToFirst()) {
            do {
                CNNote cn = new CNNote();
                cn.HandedBy = cursor.getString(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_HandedBy));
                cn.MaterialDesc =cursor.getString(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_MaterialDesc));
                cn.ActualWight=cursor.getString(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ActualWight));
                cn.ModeID=cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ModeID));
                cn.DestCityID = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_DestCityID));
                cn.BookingDate = cursor.getString(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_BookingDate));
                cn.CenterID =cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_CenterID));
                cn.CNNumber = cursor.getString(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_CNNumber));
                cn.ConsigneeCompId = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ConsigneeCompId));
                cn.ConsignmentWeight = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ConsignmentWeight));
                cn.ConsigneeCompId = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ConsigneeCompId));
                cn.OriginCityID =cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_OriginCityID));
                cn.PackageNo= cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_PackageNo));
                cn.Remarks = cursor.getString(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_Remarks));
                cn.ServiceTax = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ServiceTax));
                cn.ShipperCompId = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ShipperCompId));
                cn.ToPayMode = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_ToPayMode));
                cn.TOTAL = cursor.getInt(cursor.getColumnIndex(CNOTETable.CNNoteEntry.CN_TOTAL));

                data.add(cn);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public static int InsertCNNOte( SQLiteDatabase sqLiteDatabase,   String CNNumber, String BookingDate
            ,  int PackageNo
            ,  int ModeID
            , String ActualWight
            ,  double ConsignmentWeight
            ,  String MaterialDesc
            ,  int ShipperCompId
            ,  int ConsigneeCompId
            ,  int OriginCityID
            ,  int DestCityID
            , int ToPayMode
            ,  double ServiceTax
            ,  double TOTAL
            ,  int CenterID , String Remarks,String HandedBy, String Status)


    {


      //  SQLiteDatabase db = sqLiteDatabase.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CNOTETable.CNNoteEntry.CN_ActualWight, ActualWight);
        values.put(CNOTETable.CNNoteEntry.CN_BookingDate, BookingDate);
        values.put(CNOTETable.CNNoteEntry.CN_CenterID, CenterID);
        values.put(CNOTETable.CNNoteEntry.CN_CNNumber, CNNumber);
        values.put(CNOTETable.CNNoteEntry.CN_ConsigneeCompId, ConsigneeCompId);
        values.put(CNOTETable.CNNoteEntry.CN_ConsignmentWeight, ConsignmentWeight);
        values.put(CNOTETable.CNNoteEntry.CN_DestCityID, DestCityID);
        values.put(CNOTETable.CNNoteEntry.CN_MaterialDesc, MaterialDesc);
        values.put(CNOTETable.CNNoteEntry.CN_ModeID, ModeID);
        values.put(CNOTETable.CNNoteEntry.CN_OriginCityID, OriginCityID);
        values.put(CNOTETable.CNNoteEntry.CN_PackageNo, PackageNo);
        values.put(CNOTETable.CNNoteEntry.CN_Remarks, Remarks);
        values.put(CNOTETable.CNNoteEntry.CN_ServiceTax, ServiceTax);
        values.put(CNOTETable.CNNoteEntry.CN_ShipperCompId, ShipperCompId);
        values.put(CNOTETable.CNNoteEntry.CN_Status, Status);
        values.put(CNOTETable.CNNoteEntry.CN_ToPayMode, ToPayMode);
        values.put(CNOTETable.CNNoteEntry.CN_TOTAL, TOTAL);
        values.put(CNOTETable.CNNoteEntry.CN_HandedBy,HandedBy);


// Insert the new row, returning the primary key value of the new row
        long newRowId = sqLiteDatabase.insert(CNOTETable.CNNoteEntry.TABLE_NAME, null, values);

return (int)newRowId;
    }
}
