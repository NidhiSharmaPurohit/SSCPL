
using System;

namespace Consignment_Track_SSCPL
{
    public class CNNote
    {
        public int CenterID { get; set; }
        public string CNNumber { get; set; }
        public DateTime BookingDate { get; set; }
        public int PackageNo { get; set; }
        public int ModeID { get; set; }
        public int? ActualWeight { get; set; }
        public int? ConsignmentWeight { get; set; }
        public string MaterialDesc { get; set; }
        public int ShipperCompId { get; set; }
        public int ConsigneeCompId { get; set; }
        public int OriginCityID { get; set; }
        public int DestCityID { get; set; }
        public int ToPayMode { get; set; }
        public double? ServiceTax { get; set; }
        public double? TOTAL { get; set; }
        public string Remarks { get; set; }
        public string Status { get; set; }
        public string HandedBy { get; set; }
        public string ReceivedBy { get; set; }
    }
}
