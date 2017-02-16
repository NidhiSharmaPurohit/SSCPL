using System;

namespace Consignment_Track_SSCPL
{
    public class Manifest
    {
        public string ManifestId { get; set; }
        public int CenterID { get; set; }
        public DateTime? ManifestDate { get; set; }
        public int SourceCityID { get; set; }
        public int TargetCityID { get; set; }
        public int CarrierTypeID { get; set; }
    }

}
