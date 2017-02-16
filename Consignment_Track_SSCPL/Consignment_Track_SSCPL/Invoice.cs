using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class Invoice
    {
        public string InvoiceNum { get; set; }
        public int CompanyId { get; set; }
        public int CenterId { get; set; }
        public double RateId { get; set; }
        public string Statuss { get; set; }
        public double Amount { get; set; }
        public double? Discount { get; set; }
        public double TotalAmount { get; set; }
        public DateTime InvoiceDate { get; set; }
        public DateTime FromDate { get; set; }
        public DateTime ToDate { get; set; }
    }

}
