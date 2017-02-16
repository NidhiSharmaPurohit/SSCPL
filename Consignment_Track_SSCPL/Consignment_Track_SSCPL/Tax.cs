using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class Tax
    {
        public int TaxId { get; set; }
        public string TaxName { get; set; }
        public double Percentage { get; set; }
        public int Statuss { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public int CenterId { get; set; }
    }
}
