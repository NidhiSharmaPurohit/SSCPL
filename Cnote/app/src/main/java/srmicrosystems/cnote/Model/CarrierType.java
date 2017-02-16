package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 18-06-2016.
 */
public class CarrierType {
    private String Model;

    private String Typee;

    private int Id;

    private String ModelID;

    private double Capacity;

    private String CNumber;

    public String getModel ()
    {
        return Model;
    }

    public void setModel (String Model)
    {
        this.Model = Model;
    }

    public String getTypee ()
    {
        return Typee;
    }

    public void setTypee (String Typee)
    {
        this.Typee = Typee;
    }

    public int getId ()
    {
        return Id;
    }

    public void setId (int Id)
    {
        this.Id = Id;
    }

    public String getModelID ()
    {
        return ModelID;
    }

    public void setModelID (String ModelID)
    {
        this.ModelID = ModelID;
    }

    public double getCapacity ()
    {
        return Capacity;
    }

    public void setCapacity (double Capacity)
    {
        this.Capacity = Capacity;
    }

    public String getCNumber ()
    {
        return CNumber;
    }

    public void setCNumber (String CNumber)
    {
        this.CNumber = CNumber;
    }
}
