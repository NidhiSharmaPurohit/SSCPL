using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Consignment_Track_SSCPL
{
    public class ManifestItemDetail
    {
        public string SourceCity { get; set; }
        public string DestCity { get; set; }
        public string PayMode { get; set; }
        public string TransportMode { get; set; }
        public string ShipperCompany { get; set; }
        public string ShipperCity { get; set; }
        public string ShipperCompanyAddress { get; set; }
        public string ShipperContactPerson { get; set; }
        public string ShipperPrimaryContactNumber { get; set; }
        public string ConsigneeCompany { get; set; }
        public string ConsigneeCity { get; set; }
        public string ConsigneeCompanyAddress { get; set; }
        public string ConsigneeContactPerson { get; set; }
        public string ConsigneePrimaryContactNumber { get; set; }
        public string CNNumber { get; set; }
        public DateTime BookingDate { get; set; }
        public int? PackageNo { get; set; }
        public int? ActualWeight { get; set; }
        public int? ConsignmentWeight { get; set; }
        public string MaterialDesc { get; set; }
        public string ManifestId { get; set; }
        public int? LoadedQuantity { get; set; }
    }
}
