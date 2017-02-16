using System;

namespace Consignment_Track_SSCPL
{
    public class ManifestItem
    {
        public int MItemID { get; set; }
        public string ManifestId { get; set; }
        public string CNoteNo { get; set; }
        public int? LoadedQuantity { get; set; }
        public DateTime? ItemDate { get; set; }
    }
}
