package srmicrosystems.cnote.Model.SQL;

import android.provider.BaseColumns;

/**
 * Created by saman_000 on 27-08-2016.
 */
public class CNOTETable {

    private CNOTETable() {}

    public  static  final class  CNNoteEntry implements BaseColumns
    {
        public static  final String TABLE_NAME = "entry";

        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

        public static final  String CN_CNNumber ="CNNumber";
        public static final String CN_BookingDate="BookingDate";
        public static final  String CN_PackageNo="PackageNo";
        public static final  String CN_ModeID="ModeID";
        public static final String CN_ActualWight="ActualWight";
        public static final  String CN_ConsignmentWeight="ConsignmentWeight";
        public static final  String CN_MaterialDesc="MaterialDesc";
        public static final  String CN_ShipperCompId="ShipperCompId";
        public static final  String CN_ConsigneeCompId="ConsigneeCompId";
        public static final  String CN_OriginCityID="OriginCityID";
        public static final  String CN_DestCityID="DestCityID";
        public static final String CN_ToPayMode="ToPayMode";
        public static final  String CN_ServiceTax="ServiceTax";
        public static final  String CN_TOTAL="TOTAL";
        public static final  String CN_CenterID="CenterID";
        public static final  String CN_Remarks="Remarks";
        public static final  String CN_Status="Status";
        private static final String TEXT_TYPE = " TEXT";
        public static final String CN_HandedBy ="HandedBy";
        public static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + CNNoteEntry.TABLE_NAME + " (" +
                        CNNoteEntry.CN_ActualWight + " REAL" + COMMA_SEP +
                        CNNoteEntry.CN_BookingDate + " DATE" + COMMA_SEP +
                        CNNoteEntry.CN_CenterID + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_CNNumber + TEXT_TYPE + " PRIMARY KEY " + COMMA_SEP +
                        CNNoteEntry.CN_ConsigneeCompId + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_ConsignmentWeight + " REAL" + COMMA_SEP +
                        CNNoteEntry.CN_DestCityID + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_MaterialDesc + TEXT_TYPE + COMMA_SEP +
                        CNNoteEntry.CN_ModeID + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_OriginCityID + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_PackageNo + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_ServiceTax + " REAL" + COMMA_SEP +
                        CNNoteEntry.CN_ShipperCompId + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_ToPayMode + " INTEGER" + COMMA_SEP +
                        CNNoteEntry.CN_TOTAL + " REAL" + COMMA_SEP +
                        CNNoteEntry.CN_Remarks + TEXT_TYPE + COMMA_SEP +
                        CNNoteEntry.CN_HandedBy + TEXT_TYPE + COMMA_SEP +
                        CNNoteEntry.CN_Status + TEXT_TYPE +   " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CNNoteEntry.TABLE_NAME;

    }
}
