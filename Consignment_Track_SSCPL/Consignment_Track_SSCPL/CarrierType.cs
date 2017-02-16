using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Consignment_Track_SSCPL
{
    public class CarrierType
    {
        public int Id { get; set; }
        public string Typee { get; set; }
        public double? Capacity { get; set; }
        public string Model { get; set; }
        public string ModelID { get; set; }
        public string CNumber { get; set; }
    }
}
