package srmicrosystems.cnote.Model.SQL;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import srmicrosystems.cnote.Model.CNNoteDetailsExt;

/**
 * Created by saman_000 on 27-08-2016.
 */
public class CNNoteDetailsExtSQLHelper extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SSCPL.db";
public CNNoteDetailsExtSQLHelper(Context context)
{


    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
try
{
    db.execSQL(CNNoteDetailsExtTable.CNNoteExt.SQL_CREATE_ENTRIES);
}
catch (Exception ex)
{
    String s = ex.getMessage();
}

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CNNoteDetailsExtTable.CNNoteExt.SQL_DELETE_ENTRIES);
    }

public void CreateTable(SQLiteDatabase db)
{
    db.execSQL(CNNoteDetailsExtTable.CNNoteExt.SQL_CREATE_ENTRIES);
}
    public  static  boolean DeleteAllCnNote(SQLiteDatabase sqLiteDatabase)
    {
        //String selectQuery = "Delete FROM " + CNNoteDetailsExtTable.CNNoteExt.TABLE_NAME ;
        //SQLiteDatabase db  = sqLiteDatabase;
       // Cursor cursor      = db.rawQuery(selectQuery, null);
        sqLiteDatabase.delete(CNNoteDetailsExtTable.CNNoteExt.TABLE_NAME,null,null);
        return true;
    }

    public  static  boolean UpdateCnNoteStatus(SQLiteDatabase sqLiteDatabase,String CNNumber)
    {
        String selectQuery = "Update " + CNNoteDetailsExtTable.CNNoteExt.TABLE_NAME + " set " + CNNoteDetailsExtTable.CNNoteExt.CN_Status + " = 'Synced' where " + CNNoteDetailsExtTable.CNNoteExt.CN_CNNumber + " = '"+ CNNumber + "'"  ;
        SQLiteDatabase db  = sqLiteDatabase;
         Cursor cursor      = db.rawQuery(selectQuery, null);
        //sqLiteDatabase.delete(CNNoteDetailsExtTable.CNNoteExt.TABLE_NAME,null,null);
        return true;
    }
    public static CNNoteDetailsExt GetCNNoteDetailsExt(SQLiteDatabase sqLiteDatabase,String CNN) {



        String selectQuery = "SELECT  * FROM " + CNNoteDetailsExtTable.CNNoteExt.TABLE_NAME + " where " +  CNNoteDetailsExtTable.CNNoteExt.CN_CNNumber + " ='" + CNN +"'";
        SQLiteDatabase db  = sqLiteDatabase;
        Cursor cursor      = db.rawQuery(selectQuery, null);
        CNNoteDetailsExt data   = new CNNoteDetailsExt();
        String dat ="";
        if (cursor.moveToFirst()) {
            do {
              //  data =  new CNNoteDetailsExt();
                CNNoteDetailsExt cn = new CNNoteDetailsExt();
                cn.setHandedBy ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_HandedBy)));
                cn.setShipperCompany(cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompany)));
                cn.setMaterialDesc (cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_MaterialDesc)));
                cn.setActualWeight(cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ActualWight)));
                cn.setModeID(cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ModeID)));
                cn.setDestCityID ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_DestCityID)));
                cn.setBookingDate ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_BookingDate)));
                cn.setCenterID (cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_CenterID)));
                cn.setCNNumber ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_CNNumber)));
                cn.setConsigneeCompId ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompId)));
                cn.setConsignmentWeight ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsignmentWeight)));
                cn.setConsigneeCompId ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompId)));
                cn.setOriginCityID (cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_OriginCityID)));
                cn.setPackageNo( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_PackageNo)));
                cn.setRemarks ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_Remarks)));
                cn.setServiceTax ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ServiceTax)));
                cn.setShipperCompId ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompId)));
                cn.setToPayMode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ToPayMode)));
                cn.setTOTAL ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_TOTAL)));
                cn.setCenterName ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_CenterName )));
                cn.setCenterCity ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_CenterCity )));
                cn.setCenterAddress ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_CenterAddress )));
                cn.setSourceCity ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_SourceCity )));
                cn.setSourceCityCode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_SourceCityCode )));
                cn.setDestCity ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_DestCity )));
                cn.setDestCityCode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_DestCityCode )));
                cn.setPayMode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_PayMode)));
                cn.setTransportMode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_TransportMode )));
                cn.setShipperCompany ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompany )));
                cn.setShipperCity ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCity )));
                cn.setShipperCompanyCode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompanyCode )));
                cn.setShipperCompanyAddress ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompanyAddress)));
                cn.setShipperContactPerson ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperContactPerson )));
                cn.setShipperEmailID ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperEmailID )));

                cn.setShipperPrimaryContactNumber ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperPrimaryContactNumber )));
                cn.setShipperSecondarycontactNumber ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperSecondarycontactNumber )));
                cn.setConsigneeCompany ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompany )));
                cn.setConsigneeCity ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCity )));
                cn.setConsigneeCompanyCode ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompanyCode )));
                cn.setConsigneeCompanyAddress ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompanyAddress )));
                cn.setConsigneeContactPerson ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeContactPerson )));
                cn.setConsigneeEmailID ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeEmailID )));
                cn.setConsigneePrimaryContactNumber ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneePrimaryContactNumber )));
                cn.setConsigneeSecondaryContactNumber ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeSecondaryContactNumber )));
                cn.setCenterID ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_CenterID )));
                cn.setReceivedBy ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_ReceivedBy )));
                cn.setSignImageURL ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_SignImageURL )));
                cn.setSignImageDeliveredURL ( cursor.getString(cursor.getColumnIndex(CNNoteDetailsExtTable.CNNoteExt.CN_SignImageDeliveredURL )));

                data=cn;

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    /*
    public static int InsertCNNOteDetailsExt( SQLiteDatabase sqLiteDatabase,   String CNNumber, String BookingDate
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
            ,  int CenterID ,
                                    String Remarks,
                                    String HandedBy,
                                    String Status,
                                    String CenterName,
                                    String CenterAddress,
                                    String CenterCity,
                                    String SourceCity,
                                    String SourceCityCode,
                                    String DestCity,
                                    String DestCityCode,
                                    String PayMode,
                                    String TransportMode,
                                    String ShipperCompany,
                                    String ShipperCity,
                                    String ShipperCompanyCode,
                                    String ShipperCompanyAddress,
                                    String ShipperContactPerson,
                                    String ShipperEmailID,
                                    String ShipperPrimaryContactNumber,
                                    String ShipperSecondarycontactNumber,
                                    String ConsigneeCompany,
                                    String ConsigneeCity,
                                    String ConsigneeCompanyCode,
                                    String ConsigneeCompanyAddress,
                                    String ConsigneeContactPerson,
                                    String ConsigneeEmailID,
                                    String ConsigneePrimaryContactNumber,
                                    String ConsigneeSecondaryContactNumber,
                                    String ReceivedBy,
                                    String SignImageURL,String SignImageDeliveredURL
                                    )
*/

    public static int InsertCNNOteDetailsExt( SQLiteDatabase sqLiteDatabase,CNNoteDetailsExt CNNnoteExtDt)
    {



      //  SQLiteDatabase db = sqLiteDatabase.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ActualWight,CNNnoteExtDt.getActualWeight());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_BookingDate,CNNnoteExtDt.getBookingDate());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_CenterID,CNNnoteExtDt.getCenterID());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_CNNumber,CNNnoteExtDt.getCNNumber());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompId,CNNnoteExtDt.getConsigneeCompId());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsignmentWeight,CNNnoteExtDt.getConsignmentWeight());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_DestCityID,CNNnoteExtDt.getDestCityID());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_MaterialDesc,CNNnoteExtDt.getMaterialDesc());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ModeID,CNNnoteExtDt.getModeID());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_OriginCityID,CNNnoteExtDt.getOriginCityID());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_PackageNo,CNNnoteExtDt.getPackageNo());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_Remarks,CNNnoteExtDt.getRemarks());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ServiceTax,CNNnoteExtDt.getServiceTax());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompId,CNNnoteExtDt.getShipperCompId());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_Status,CNNnoteExtDt.getStatus());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ToPayMode,CNNnoteExtDt.getToPayMode());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_TOTAL,CNNnoteExtDt.getTOTAL());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_HandedBy,CNNnoteExtDt.getHandedBy());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_CenterName,CNNnoteExtDt.getCenterName() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_CenterCity,CNNnoteExtDt.getCenterCity() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_CenterAddress,CNNnoteExtDt.getCenterAddress());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_SourceCity,CNNnoteExtDt.getSourceCity() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_SourceCityCode,CNNnoteExtDt.getSourceCityCode());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_DestCity,CNNnoteExtDt.getDestCity());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_DestCityCode,CNNnoteExtDt.getDestCityCode() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_PayMode,CNNnoteExtDt.getPayMode() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_TransportMode,CNNnoteExtDt.getTransportMode() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompany, CNNnoteExtDt.getShipperCompany());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCity,CNNnoteExtDt.getShipperCity() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompanyCode,CNNnoteExtDt.getShipperCompanyCode() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperCompanyAddress,CNNnoteExtDt.getShipperCompanyAddress() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperContactPerson,CNNnoteExtDt.getShipperContactPerson() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperEmailID ,CNNnoteExtDt.getShipperEmailID());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperPrimaryContactNumber ,CNNnoteExtDt.getShipperPrimaryContactNumber());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ShipperSecondarycontactNumber,CNNnoteExtDt.getShipperSecondarycontactNumber() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompany,CNNnoteExtDt.getConsigneeCompany() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCity,CNNnoteExtDt.getConsigneeCity() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompanyCode ,CNNnoteExtDt.getConsigneeCompanyCode() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeCompanyAddress ,CNNnoteExtDt.getConsigneeCompanyAddress() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeContactPerson ,CNNnoteExtDt.getConsigneeContactPerson() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeEmailID ,CNNnoteExtDt.getConsigneeEmailID() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneePrimaryContactNumber ,CNNnoteExtDt.getConsigneePrimaryContactNumber() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ConsigneeSecondaryContactNumber ,CNNnoteExtDt.getConsigneeSecondaryContactNumber());
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_ReceivedBy ,CNNnoteExtDt.getReceivedBy() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_SignImageURL ,CNNnoteExtDt.getSignImageURL() );
        values.put(CNNoteDetailsExtTable.CNNoteExt.CN_SignImageDeliveredURL ,CNNnoteExtDt.getSignImageDeliveredURL() );





// Insert the new row,CNNnoteExtDt.returning the primary key value of the new row
        long newRowId = sqLiteDatabase.insert(CNNoteDetailsExtTable.CNNoteExt.TABLE_NAME, null, values);

return (int)newRowId;
    }
}
