using System;

namespace Consignment_Track_SSCPL
{
    public class CNNotesFullDetails
    {
        public string CenterName { get; set; }
        public string CenterCity { get; set; }
        public string CenterAddress { get; set; }
        public string SourceCity { get; set; }
        public string SourceCityCode { get; set; }
        public string DestCity { get; set; }
        public string DestCityCode { get; set; }
        public string PayMode { get; set; }
        public string TransportMode { get; set; }
        public string ShipperCompany { get; set; }
        public string ShipperCity { get; set; }
        public string ShipperCompanyCode { get; set; }
        public string ShipperCompanyAddress { get; set; }
        public string ShipperContactPerson { get; set; }
        public string ShipperEmailID { get; set; }
        public string ShipperPrimaryContactNumber { get; set; }
        public string ShipperSecondarycontactNumber { get; set; }
        public string ConsigneeCompany { get; set; }
        public string ConsigneeCity { get; set; }
        public string ConsigneeCompanyCode { get; set; }
        public string ConsigneeCompanyAddress { get; set; }
        public string ConsigneeContactPerson { get; set; }
        public string ConsigneeEmailID { get; set; }
        public string ConsigneePrimaryContactNumber { get; set; }
        public string ConsigneeSecondaryContactNumber { get; set; }
        public int CenterID { get; set; }
        public string CNNumber { get; set; }
        public DateTime BookingDate { get; set; }
        public int PackageNo { get; set; }
        public int ModeID { get; set; }
        public double? ActualWeight { get; set; }
        public double? ConsignmentWeight { get; set; }
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
