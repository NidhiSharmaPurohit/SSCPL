using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class Payment
    {
        public int PaymentId { get; set; }
        public string InvoiceNum { get; set; }
        public int PaymentMode { get; set; }
        public int CenterId { get; set; }
        public string Statuss { get; set; }
        public double Amount { get; set; }
        public string CreatedBy { get; set; }
        public DateTime DateCreated { get; set; }
    }
}
