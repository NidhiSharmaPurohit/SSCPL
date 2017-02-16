package srmicrosystems.cnote.Model;

import java.util.Date;

/**
 * Created by saman_000 on 05-09-2016.
 */
public class ManifestDetail {
    private String SourceCity;

    private String DestCityCode;

    public String getDestCityCode() { return this.DestCityCode; }
    public void setDestCityCode(String DestCityCode) { this.DestCityCode = DestCityCode; }

    private String SourceCityCode;

    public String getSourceCityCode() { return this.SourceCityCode; }
    public void setSourceCityCode(String SourceCityCode) { this.SourceCityCode = SourceCityCode; }
    public String getSourceCity() { return this.SourceCity; }

    public void setSourceCity(String SourceCity) { this.SourceCity = SourceCity; }

    private String DestCity;

    public String getDestCity() { return this.DestCity; }

    public void setDestCity(String DestCity) { this.DestCity = DestCity; }

    private String CarrierType;

    public String getCarrierType() { return this.CarrierType; }

    public void setCarrierType(String CarrierType) { this.CarrierType = CarrierType; }

    private double Capacity;

    public double getCapacity() { return this.Capacity; }

    public void setCapacity(double Capacity) { this.Capacity = Capacity; }

    private String Model;

    public String getModel() { return this.Model; }

    public void setModel(String Model) { this.Model = Model; }

    private String ModelID;

    public String getModelID() { return this.ModelID; }

    public void setModelID(String ModelID) { this.ModelID = ModelID; }

    private String CarrierNumber;

    public String getCarrierNumber() { return this.CarrierNumber; }

    public void setCarrierNumber(String CarrierNumber) { this.CarrierNumber = CarrierNumber; }

    private String CenterName;

    public String getCenterName() { return this.CenterName; }

    public void setCenterName(String CenterName) { this.CenterName = CenterName; }

    private String CenterCity;

    public String getCenterCity() { return this.CenterCity; }

    public void setCenterCity(String CenterCity) { this.CenterCity = CenterCity; }

    private String CenterAddress;

    public String getCenterAddress() { return this.CenterAddress; }

    public void setCenterAddress(String CenterAddress) { this.CenterAddress = CenterAddress; }

    private String ManifestId;

    public String getManifestId() { return this.ManifestId; }

    public void setManifestId(String ManifestId) { this.ManifestId = ManifestId; }

    private int CenterID;

    public int getCenterID() { return this.CenterID; }

    public void setCenterID(int CenterID) { this.CenterID = CenterID; }

    private Date ManifestDate;

    public Date getManifestDate() { return this.ManifestDate; }

    public void setManifestDate(Date ManifestDate) { this.ManifestDate = ManifestDate; }

    private int SourceCityID;

    public int getSourceCityID() { return this.SourceCityID; }

    public void setSourceCityID(int SourceCityID) { this.SourceCityID = SourceCityID; }

    private int TargetCityID;

    public int getTargetCityID() { return this.TargetCityID; }

    public void setTargetCityID(int TargetCityID) { this.TargetCityID = TargetCityID; }

    private int CarrierTypeID;

    public int getCarrierTypeID() { return this.CarrierTypeID; }

    public void setCarrierTypeID(int CarrierTypeID) { this.CarrierTypeID = CarrierTypeID; }

}
