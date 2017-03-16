package srmicrosystems.cnote.Model;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by saman_000 on 11-06-2016.
 */
public class CNNoteDetails {

    private String CenterName;

    public String getCenterName() { return this.CenterName; }

    public void setCenterName(String CenterName) { this.CenterName = CenterName; }

    private String CenterCity;

    public String getCenterCity() { return this.CenterCity; }

    public void setCenterCity(String CenterCity) { this.CenterCity = CenterCity; }

    private String SourceCity;

    public String getSourceCity() { return this.SourceCity; }

    public void setSourceCity(String SourceCity) { this.SourceCity = SourceCity; }

    private String DestCity;

    public String getDestCity() { return this.DestCity; }

    public void setDestCity(String DestCity) { this.DestCity = DestCity; }

    private String PayMode;

    public String getPayMode() { return this.PayMode; }

    public void setPayMode(String PayMode) { this.PayMode = PayMode; }

    private String TransportMode;

    public String getTransportMode() { return this.TransportMode; }

    public void setTransportMode(String TransportMode) { this.TransportMode = TransportMode; }

    private String ShipperCompany;

    public String getShipperCompany() { return this.ShipperCompany; }

    public void setShipperCompany(String ShipperCompany) { this.ShipperCompany = ShipperCompany; }

    private String ShipperCity;

    public String getShipperCity() { return this.ShipperCity; }

    public void setShipperCity(String ShipperCity) { this.ShipperCity = ShipperCity; }

    private String ConsigneeCompany;

    public String getConsigneeCompany() { return this.ConsigneeCompany; }

    public void setConsigneeCompany(String ConsigneeCompany) { this.ConsigneeCompany = ConsigneeCompany; }

    private String ConsigneeCity;

    public String getConsigneeCity() { return this.ConsigneeCity; }

    public void setConsigneeCity(String ConsigneeCity) { this.ConsigneeCity = ConsigneeCity; }

    private int CenterID;

    public int getCenterID() { return this.CenterID; }

    public void setCenterID(int CenterID) { this.CenterID = CenterID; }

    private String CNNumber;

    public String getCNNumber() { return this.CNNumber; }

    public void setCNNumber(String CNNumber) { this.CNNumber = CNNumber; }

    public  int LodedBag;

    public  void setLodedBag(int qty)
    {
        LodedBag = qty;
    }
    public int getLodedBag(){
        return this.LodedBag;
    }

    public int LodedBagPartial =0;

    public void setLodedBagPartial(int qty)
    {
        LodedBagPartial = LodedBagPartial + qty;
    }

    public int getLodedBagPartial(){
        return this.LodedBagPartial;
    }

    private String BookingDate;

    public String getBookingDate() { return this.BookingDate.substring(0,10); }

    public void setBookingDate(String BookingDate) { SimpleDateFormat format = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        try
        {
            this.BookingDate =  BookingDate.substring(0,10);
        }
        catch (Exception e)
        {
        }


        // this.BookingDate = BookingDate;
    }

    private int PackageNo;

    public int getPackageNo() { return this.PackageNo; }

    public void setPackageNo(int PackageNo) { this.PackageNo = PackageNo; }

    private int DispPackageNo;

    public int getDispPackageNo() { return this.DispPackageNo; }

    public void setDispPackageNo() { this.DispPackageNo = this.DispPackageNo-1; }

    public void setDispPackageNo(int quantity) { this.DispPackageNo = this.DispPackageNo - quantity;}

    public void setintiDispPackageNo() { this.DispPackageNo = this.PackageNo-this.GetManifestItemLoadedQuantity(); }

    private int ModeID;

    public int getModeID() { return this.ModeID; }

    public void setModeID(int ModeID) { this.ModeID = ModeID; }

    private int ActualWeight;
    private  int ManifestItemLoadedQuantity;

    public  void setManifestItemLoadedQuantity(int ManifestItemLoadedQuantity)
    {
        this.ManifestItemLoadedQuantity=ManifestItemLoadedQuantity;
    }

    public int GetManifestItemLoadedQuantity(){
        return  this.ManifestItemLoadedQuantity;
    }

    public int getActualWeight() { return this.ActualWeight; }

    public void setActualWeight(int ActualWeight) { this.ActualWeight = ActualWeight; }

    private int ConsignmentWeight;

    public int getConsignmentWeight() { return this.ConsignmentWeight; }

    public void setConsignmentWeight(int ConsignmentWeight) { this.ConsignmentWeight = ConsignmentWeight; }

    private String MaterialDesc;

    public String getMaterialDesc() { return this.MaterialDesc; }

    public void setMaterialDesc(String MaterialDesc) { this.MaterialDesc = MaterialDesc; }

    private int ShipperCompId;

    public int getShipperCompId() { return this.ShipperCompId; }

    public void setShipperCompId(int ShipperCompId) { this.ShipperCompId = ShipperCompId; }

    private int ConsigneeCompId;

    public int getConsigneeCompId() { return this.ConsigneeCompId; }

    public void setConsigneeCompId(int ConsigneeCompId) { this.ConsigneeCompId = ConsigneeCompId; }

    private int OriginCityID;

    public int getOriginCityID() { return this.OriginCityID; }

    public void setOriginCityID(int OriginCityID) { this.OriginCityID = OriginCityID; }

    private int DestCityID;

    public int getDestCityID() { return this.DestCityID; }

    public void setDestCityID(int DestCityID) { this.DestCityID = DestCityID; }

    private int ToPayMode;

    public int getToPayMode() { return this.ToPayMode; }

    public void setToPayMode(int ToPayMode) { this.ToPayMode = ToPayMode; }

    private double ServiceTax;

    public double getServiceTax() { return this.ServiceTax; }

    public void setServiceTax(double ServiceTax) { this.ServiceTax = ServiceTax; }

    private double TOTAL;

    public double getTOTAL() { return this.TOTAL; }

    public void setTOTAL(double TOTAL) { this.TOTAL = TOTAL; }

    private String Remarks;

    public String getRemarks() { return this.Remarks; }

    public void setRemarks(String Remarks) { this.Remarks = Remarks; }

    private String Status;

    public String getStatus() { return this.Status; }

    public void setStatus(String Status) { this.Status = Status; }

}
