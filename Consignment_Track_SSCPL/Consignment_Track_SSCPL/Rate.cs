using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class Rates
    {
        public int RateId { get; set; }
        public int CompanyId { get; set; }
        public int CenterId { get; set; }
        public double Rate { get; set; }
        public double KG { get; set; }
        public int Statuss { get; set; }
        public int ModeID { get; set; }
    }
}
