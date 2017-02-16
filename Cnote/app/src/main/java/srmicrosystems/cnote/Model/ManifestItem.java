package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 11-06-2016.
 */
public class ManifestItem {
    private String CNoteNo;

    private int LoadedQuantity;

    private String ManifestId;

    public String getCNoteNo ()
    {
        return CNoteNo;
    }

    public void setCNoteNo (String CNoteNo)
    {
        this.CNoteNo = CNoteNo;
    }

    public int getLoadedQuantity ()
    {
        return LoadedQuantity;
    }

    public void setLoadedQuantity (int LoadedQuantity)
    {
        this.LoadedQuantity = LoadedQuantity;
    }

    public String getManifestId ()
    {
        return ManifestId;
    }

    public void setManifestId (String ManifestId)
    {
        this.ManifestId = ManifestId;
    }
}
