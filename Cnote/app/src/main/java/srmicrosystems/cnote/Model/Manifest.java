package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 11-06-2016.
 */
public class Manifest {

    private String CenterID;

    private String TargetCityID;

    private String SourceCityID;

    private String CarrierTypeID;

    private String ManifestId;

    private String ManifestDate;

    public String getCenterID ()
    {
        return CenterID;
    }

    public void setCenterID (String CenterID)
    {
        this.CenterID = CenterID;
    }

    public String getTargetCityID ()
    {
        return TargetCityID;
    }

    public void setTargetCityID (String TargetCityID)
    {
        this.TargetCityID = TargetCityID;
    }

    public String getSourceCityID ()
    {
        return SourceCityID;
    }

    public void setSourceCityID (String SourceCityID)
    {
        this.SourceCityID = SourceCityID;
    }

    public String getCarrierTypeID ()
    {
        return CarrierTypeID;
    }

    public void setCarrierTypeID (String CarrierTypeID)
    {
        this.CarrierTypeID = CarrierTypeID;
    }

    public String getManifestId ()
    {
        return ManifestId;
    }

    public void setManifestId (String ManifestId)
    {
        this.ManifestId = ManifestId;
    }

    public String getManifestDate ()
    {
        return ManifestDate;
    }

    public void setManifestDate (String ManifestDate)
    {
        this.ManifestDate = ManifestDate;
    }

   /* @Override
    public String toString()
    {
        return "ClassPojo [CenterID = "+CenterID+", TargetCityID = "+TargetCityID+", SourceCityID = "+SourceCityID+", CarrierTypeID = "+CarrierTypeID+", ManifestId = "+ManifestId+", ManifestDate = "+ManifestDate+"]";
    }*/
}
