using System;

namespace Consignment_Track_SSCPL
{
    public class ManifestDetail
    {
        public string SourceCity { get; set; }
        public string DestCity { get; set; }
        public string CarrierType { get; set; }
        public double? Capacity { get; set; }
        public string Model { get; set; }
        public string ModelID { get; set; }
        public string CarrierNumber { get; set; }
        public string CenterName { get; set; }
        public string CenterCity { get; set; }
        public string CenterAddress { get; set; }
        public string ManifestId { get; set; }
        public int CenterID { get; set; }
        public DateTime? ManifestDate { get; set; }
        public int SourceCityID { get; set; }
        public int TargetCityID { get; set; }
        public int CarrierTypeID { get; set; }
    }
}
