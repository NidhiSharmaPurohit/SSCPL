using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class InvoiceItemDetail
    {
        public int CenterID { get; set; }
        public string CNNumber { get; set; }
        public DateTime BookingDate { get; set; }
        public int PackageNo { get; set; }
        public int ModeID { get; set; }
        public string Mode
        {
            get
            {
               return (from mode in CachedDataManager.GetCachedTransportMode()
                 where mode.ModelId == this.ModeID
                 select mode.Modee).FirstOrDefault();
            }
            set
            {
                Mode = value;
            }
        }
        public int? ActualWeight { get; set; }
        public int? ConsignmentWeight { get; set; }
        public string MaterialDesc { get; set; }
        public int ShipperCompId { get; set; }
        public int ConsigneeCompId { get; set; }
        public int OriginCityID { get; set; }
        public string OriginCity
        {
            get
            {
                return (from city in CachedDataManager.GetCachedCity()
                              where city.CityId == this.OriginCityID
                              select city.CityName).FirstOrDefault();
            }
            set
            {
                OriginCity = value;
            }
        }
        public int DestCityID { get; set; }
        public string DestinationCity
        {
            get
            {
                return (from city in CachedDataManager.GetCachedCity()
                        where city.CityId == this.DestCityID
                        select city.CityName).FirstOrDefault();
            }
            set
            {
                DestinationCity = value;
            }
        }
        public int ToPayMode { get; set; }
        public string PayMode
        {
            get
            {
                return (from pay in CachedDataManager.GetCachedPackagingMode()
                 where pay.Id == this.ToPayMode
                 select pay.Typee).FirstOrDefault();
            }
            set
            {
                PayMode = value;
            }
        }

        public int? ServiceTax { get; set; }
        public int? TOTAL { get; set; }
        public string Remarks { get; set; }
        public string Status { get; set; }
        public string HandedBy { get; set; }
        public string ReceivedBy { get; set; }
        public int? Rate { get; set; }
        public int? RateforKGMax { get; set; }
        public int InvoiceItemId { get; set; }
        public string InvoiceNum { get; set; }
        public int RateId { get; set; }
        public int? Amount { get; set; }
        public int? Weight { get; set; }
    }

}
