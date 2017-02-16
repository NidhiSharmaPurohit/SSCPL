package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 12-05-2016.
 */
public class Company
{
    private String CompanySecondaryContactNumber;

    private String CompanyState;

    private String CompanyCity;

    private String CompanyPrimaryContactNumber;

    private Integer CompanyId;

    private Integer CityId;

    private String CompanyCCode;

    private String CompanyAddress;

    private String CompanyEmailId;

    private String CompanyContactPerson;

    private String CompanyName;

    public void SetCityId(Integer id )
    {
        CityId =id;
    }

    public Integer GetCityId( )
    {
     return   CityId ;
    }

    public String getCompanySecondaryContactNumber ()
{
    return CompanySecondaryContactNumber;
}

    public void setCompanySecondaryContactNumber (String CompanySecondaryContactNumber)
    {
        this.CompanySecondaryContactNumber = CompanySecondaryContactNumber;
    }

    public String getCompanyState ()
    {
        return CompanyState;
    }

    public void setCompanyState (String CompanyState)
    {
        this.CompanyState = CompanyState;
    }

    public String getCompanyCity ()
    {
        return CompanyCity;
    }

    public void setCompanyCity (String CompanyCity)
    {
        this.CompanyCity = CompanyCity;
    }

    public String getCompanyPrimaryContactNumber ()
{
    return CompanyPrimaryContactNumber;
}

    public void setCompanyPrimaryContactNumber (String CompanyPrimaryContactNumber)
    {
        this.CompanyPrimaryContactNumber = CompanyPrimaryContactNumber;
    }

    public Integer getCompanyId ()
    {
        return CompanyId;
    }

    public void setCompanyId (Integer CompanyId)
    {
        this.CompanyId = CompanyId;
    }

    public String getCompanyCCode ()
    {
        return CompanyCCode;
    }

    public void setCompanyCCode (String CompanyCCode)
    {
        this.CompanyCCode = CompanyCCode;
    }

    public String getCompanyAddress ()
    {
        return CompanyAddress;
    }

    public void setCompanyAddress (String CompanyAddress)
    {
        this.CompanyAddress = CompanyAddress;
    }

    public String getCompanyEmailId ()
    {
        return CompanyEmailId;
    }

    public void setCompanyEmailId (String CompanyEmailId)
    {
        this.CompanyEmailId = CompanyEmailId;
    }

    public String getCompanyContactPerson ()
    {
        return CompanyContactPerson;
    }

    public void setCompanyContactPerson (String CompanyContactPerson)
    {
        this.CompanyContactPerson = CompanyContactPerson;
    }

    public String getCompanyName ()
    {
        return CompanyName;
    }

    public void setCompanyName (String CompanyName)
    {
        this.CompanyName = CompanyName;
    }

}

