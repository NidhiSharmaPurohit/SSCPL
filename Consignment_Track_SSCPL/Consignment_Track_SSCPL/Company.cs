using System;

namespace Consignment_Track_SSCPL
{
    public class Company
    {
        public int CompanyId { get; set; }
        public string CompanyName { get; set; }
        public string CompanyAddress { get; set; }
        public string CompanyCity { get; set; }
        public string CompanyState { get; set; }
        public string CompanyCCode { get; set; }
        public string CompanyContactPerson { get; set; }
        public string CompanyEmailId { get; set; }
        public string CompanyPrimaryContactNumber { get; set; }
        public string CompanySecondaryContactNumber { get; set; }
        public int CityId { get; set; }
    }
}
