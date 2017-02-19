package srmicrosystems.cnote.Model;

/**
 * Created by User on 2/19/2017.
 */

public class AirFlight
{
    private String CenterName;
    private String CenterCity;
    private String CenterAddress;
    private int CenterId;
    private int DestCityID;
    private String DestCity;
    private String DestCityCode;
    private String FlightName;
    private String FlightNumber;
    private int FlightId;



    public int getCenterId ()
    {
        return CenterId;
    }

    public void setCenterId (int CenterId)
    {
        this.CenterId = CenterId;
    }

    public int getDestCityID ()
    {
        return DestCityID;
    }

    public void setDestCityID (int DestCityID)
    {
        this.DestCityID = DestCityID;
    }

    public String getCenterAddress ()
    {
        return CenterAddress;
    }

    public void setCenterAddress (String CenterAddress)
    {
        this.CenterAddress = CenterAddress;
    }

    public String getFlightName ()
    {
        return FlightName;
    }

    public void setFlightName (String FlightName)
    {
        this.FlightName = FlightName;
    }

    public String getDestCity ()
    {
        return DestCity;
    }

    public void setDestCity (String DestCity)
    {
        this.DestCity = DestCity;
    }

    public String getCenterName ()
    {
        return CenterName;
    }

    public void setCenterName (String CenterName)
    {
        this.CenterName = CenterName;
    }

    public String getCenterCity ()
    {
        return CenterCity;
    }

    public void setCenterCity (String CenterCity)
    {
        this.CenterCity = CenterCity;
    }

    public String getDestCityCode ()
    {
        return DestCityCode;
    }

    public void setDestCityCode (String DestCityCode)
    {
        this.DestCityCode = DestCityCode;
    }

    public String getFlightNumber ()
    {
        return FlightNumber;
    }

    public void setFlightNumber (String FlightNumber)
    {
        this.FlightNumber = FlightNumber;
    }

    public int getFlightId ()
    {
        return FlightId;
    }

    public void setFlightId (int FlightId)
    {
        this.FlightId = FlightId;
    }

}

