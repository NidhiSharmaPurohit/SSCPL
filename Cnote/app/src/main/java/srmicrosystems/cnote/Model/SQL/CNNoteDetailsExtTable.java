package srmicrosystems.cnote.Model.SQL;

import android.provider.BaseColumns;

/**
 * Created by saman_000 on 09-10-2016.
 */
public class CNNoteDetailsExtTable {

    private CNNoteDetailsExtTable() {}

    public  static  final class  CNNoteExt implements BaseColumns
    {
        public static  final String TABLE_NAME = "CNNoteExt";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

        public static final  String CN_CenterName = "CenterName";
        public static final  String CN_CenterCity = "CenterCity";
        public static final  String CN_CenterAddress = "CenterAddress";
        public static final  String CN_SourceCity = "SourceCity";
        public static final  String CN_SourceCityCode = "SourceCityCode";
        public static final  String CN_DestCity = "DestCity";
        public static final  String CN_DestCityCode = "DestCityCode";
        public static final  String CN_PayMode = "PayMode";
        public static final  String CN_TransportMode = "TransportMode";
        public static final  String CN_ShipperCompany = "ShipperCompany";
        public static final  String CN_ShipperCity = "ShipperCity";
        public static final  String CN_ShipperCompanyCode = "ShipperCompanyCode";
        public static final  String CN_ShipperCompanyAddress = "ShipperCompanyAddress";
        public static final  String CN_ShipperContactPerson = "ShipperContactPerson";
        public static final  String CN_ShipperEmailID = "ShipperEmailID";
        public static final  String CN_ShipperPrimaryContactNumber = "ShipperPrimaryContactNumber";
        public static final  String CN_ShipperSecondarycontactNumber = "ShipperSecondarycontactNumber";
        public static final  String CN_ConsigneeCompany = "ConsigneeCompany";
        public static final  String CN_ConsigneeCity = "ConsigneeCity";
        public static final  String CN_ConsigneeCompanyCode = "ConsigneeCompanyCode";
        public static final  String CN_ConsigneeCompanyAddress = "ConsigneeCompanyAddress";
        public static final  String CN_ConsigneeContactPerson = "ConsigneeContactPerson";
        public static final  String CN_ConsigneeEmailID = "ConsigneeEmailID";
        public static final  String CN_ConsigneePrimaryContactNumber = "ConsigneePrimaryContactNumber";
        public static final  String CN_ConsigneeSecondaryContactNumber = "ConsigneeSecondaryContactNumber";
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
        public static final String CN_ReceivedBy ="ReceivedBy";
        public static final String CN_SignImageURL ="SignImageURL";
        public static final String CN_SignImageDeliveredURL ="SignImageDeliveredURL";


        public static final String COMMA_SEP = ",";





        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE if not exists  " + CNNoteExt.TABLE_NAME + " (" +
                        CNNoteExt.CN_ActualWight + " REAL" + COMMA_SEP +
                        CNNoteExt.CN_BookingDate + " DATE" + COMMA_SEP +
                        CNNoteExt.CN_CenterID + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_CNNumber + TEXT_TYPE + " PRIMARY KEY " + COMMA_SEP +
                        CNNoteExt.CN_ConsigneeCompId + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_ConsignmentWeight + " REAL" + COMMA_SEP +
                        CNNoteExt.CN_DestCityID + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_MaterialDesc + TEXT_TYPE + COMMA_SEP +
                        CNNoteExt.CN_ModeID + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_OriginCityID + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_PackageNo + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_ServiceTax + " REAL" + COMMA_SEP +
                        CNNoteExt.CN_ShipperCompId + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_ToPayMode + " INTEGER" + COMMA_SEP +
                        CNNoteExt.CN_TOTAL + " REAL" + COMMA_SEP +
                        CNNoteExt.CN_Remarks + TEXT_TYPE + COMMA_SEP +
                        CNNoteExt.CN_HandedBy + TEXT_TYPE + COMMA_SEP +
                        CNNoteExt.CN_Status + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_CenterName + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_CenterCity + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_CenterAddress + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_SourceCity + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_SourceCityCode + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_DestCity + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_DestCityCode + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_PayMode + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_TransportMode + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperCompany + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperCity + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperCompanyCode + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperCompanyAddress + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperContactPerson + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperEmailID + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperPrimaryContactNumber + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ShipperSecondarycontactNumber + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeCompany + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeCity + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeCompanyCode + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeCompanyAddress + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeContactPerson + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeEmailID + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneePrimaryContactNumber + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ConsigneeSecondaryContactNumber + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_ReceivedBy + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_SignImageURL + TEXT_TYPE +   COMMA_SEP +
                        CNNoteExt.CN_SignImageDeliveredURL + TEXT_TYPE +    " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CNNoteExt.TABLE_NAME;
    }

}
