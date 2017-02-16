using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class InvoiceItem
    {
        public int InvoiceItemId { get; set; }
        public string InvoiceNum { get; set; }
        public int RateId { get; set; }
        public string CNNumber { get; set; }
        public double Amount { get; set; }
        public double Weight { get; set; }
    }
}
