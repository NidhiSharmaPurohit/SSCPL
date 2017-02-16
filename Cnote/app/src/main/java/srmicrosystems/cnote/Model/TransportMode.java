package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 12-05-2016.
 */
public class TransportMode
{
    private String ModelId;

    private String Modee;

    public String getModelId ()
    {
        return ModelId;
    }

    public void setModelId (String ModelId)
    {
        this.ModelId = ModelId;
    }

    public String getModee ()
    {
        return Modee;
    }

    public void setModee (String Modee)
    {
        this.Modee = Modee;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [ModelId = "+ModelId+", Modee = "+Modee+"]";
    }*/
}
