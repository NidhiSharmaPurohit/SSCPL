using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class TaxInvoice
    {
        public int TaxInvoiceId { get; set; }
        public string InvoiceNum { get; set; }
        public int TaxId { get; set; }
        public double? TaxAmount { get; set; }
    }
}
