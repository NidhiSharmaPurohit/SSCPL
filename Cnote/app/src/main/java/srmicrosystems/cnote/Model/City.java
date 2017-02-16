package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 12-05-2016.
 */
public class City
{
    private int CityId;

    private String CityName;

    private String CityCode;

    public int getCityId ()
    {
        return CityId;
    }

    public void setCityId (int CityId)
    {
        this.CityId = CityId;
    }

    public String getCityName ()
    {
        return CityName;
    }

    public void setCityName (String CityName)
    {
        this.CityName = CityName;
    }

    public String getCityCode ()
    {
        return CityCode;
    }

    public void setCityCode (String CityCode)
    {
        this.CityCode = CityCode;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [CityId = "+CityId+", CityName = "+CityName+", CityCode = "+CityCode+"]";
    }*/
}