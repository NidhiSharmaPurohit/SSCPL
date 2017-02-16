using Microsoft.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    

    public class DataManager
    {
        public const string CityUrl = "City";
        public const string CityUrlById = "City/{0}";
        public const string PackagingModeUrl = "PackagingMode";
        public const string PackagingModeUrlById = "PackagingMode/{0}";
        public const string TransportModeUrl = "TransportMode";
        public const string TransportModeUrlById = "TransportMode/{0}";
        public const string CenterMasterUrl = "CenterMaster";
        public const string CenterMasterUrlById = "CenterMaster/{0}";
        public const string CompanyUrl = "Company";
        public const string CompanyUrlById = "Company/{0}";
        public const string CariierTypeUrl = "CarrierType";
        public const string CariierTypeUrlById = "CarrierType/{0}";
        public const string CarrierTypeByCNNumber = "CarrierTypeByCNumber/{0}";
        public const string CarrierTypeById = "CarrierTypeById/{0}";
        public const string DeleteCarrierTypeByCNumber = "DeleteCarrierType/CNumber/{0}";
        public const string CNNoteUrl = "CNNotes";
        public const string CNNotesFullDetailsUrl = "CNNotesFullDetailsAlt/{0}";
        public const string CNNotessUrlByCNNum = "CNNotes/{0}";
        public const string ManifestUrl = "Manifest";
        public const string ManifestDetailUrl = "ManifestDetail";
        public const string ManifestByIDUrl = "Manifest/{0}";
        public const string ManifestDetailByIDUrl = "ManifestDetailById/{0}";
        public const string ManifestItemDetailByManifestIdUrl = "ManifestItemDetail/{0}";
        public const string GetCNNotesDetailsForManifestUrl = "GetCNNotesDetailsForManifest?StartDate={0}&EndDate={1}&Cities={2}";
        public const string ManifestItemUrl = "ManifestItem";
        public const string GetCNNotesDetailForDateRangeofCompany = "GetCNNotesDetailForInvoiceInDateRangeofCompany/ForDateRangeofCompany?StartDate={0}&EndDate={1}&ShipperCompId={2}";
        public const string RatesUrl = "Rate";
        public const string TaxUrl = "Tax";
        public const string InvoiceUrl = "Invoice";
        public const string InvoiceItemUrl = "InvoiceItem";
        public const string InvoiceByInvoiceNumUrl = "Invoice/{0}";
        public const string InvoiceDetailByInvoiceNumUrl = "InvoiceDetail/{0}";
        public const string InvoiceItemByInvoiceNumUrl = "GetInvoiceItemByInvoiceNum/{0}";
        public const string InvoiceItemDetailByInvoiceNumUrl = "GetInvoiceItemDetailByInvoiceNum/{0}";
        public const string TaxInvoiceUrl = "TaxInvoice";
        public const string GetTaxInvoiceByInvoiceNum = "GetTaxInvoiceByInvoiceNum/{0}";
        public const string GetTaxInvoiceDetailByInvoiceNum = "GetTaxInvoiceDetailByInvoiceNum/{0}";
        public const string GetInvoicesinDateRangeForCompany = "GetInvoicesinDateRangeForCompany?FromDate={0}&ToDate={1}&CompanyId={2}&CenterId={3}";
        public const string GetActiveInvoicesForCompanyInDateRangeUrl = "GetActiveInvoicesForCompanyInDateRange?FromDate={0}&ToDate={1}&CompanyId={2}&CenterId={3}";
        public const string GetActiveInvoicesForCompanyUrl = "GetActiveInvoicesForCompany?CompanyId={0}&CenterId={1}";
        public const string GetPaymentForInvoiceNumAndCenterIdUrl = "GetPaymentForInvoiceNumAndCenterId?CenterId={0}&InvoiceNum={1}";
        public const string PaymentUrl = "Payment";
        public const string PaymentUrlById = "Payment/{0}";
        public const string CNNoteSignedImageGetUrl = "uploadCNNOtesSignImage/{0}";
        public const string CNNoteSignedImageUploadUrl = "uploadCNNOtesSignImage";

        private RestAPIManager _apimanager;
        
        public DataManager()
        {
            _apimanager = new RestAPIManager();
        }

        private string GetBaseURL()
        {
            return _apimanager.GetBaseAddress();
        }

        public string getCNNoteSignedImageGetBaseUrl()
        {
            return GetBaseURL() + CNNoteSignedImageGetUrl;
        }

        public string getCNNoteSignedImageUploadUrl()
        {
            return GetBaseURL() + CNNoteSignedImageUploadUrl;
        }

        public City[] GetCities()
        {
            City[] cities;
            string cityuri =  GetBaseURL() + CityUrl;
            cities =  _apimanager.Get<City[]>(cityuri);
            _apimanager.CloseOrDispose();
            return cities;
        }

        public void AddCity(City city)
        {
            string cityuri = GetBaseURL() + CityUrl;
            _apimanager.Put<String, City>(cityuri, city);
            _apimanager.CloseOrDispose();
        }

        public void UpdateCity(City city)
        {
            string cityuri = GetBaseURL() + CityUrl;
            _apimanager.Post<String, City>(cityuri, city);
            _apimanager.CloseOrDispose();
        }

        public void DeleteCity(string cityid)
        {
            string cityuri = string.Format(GetBaseURL() + CityUrlById, cityid);
            _apimanager.Delete(cityuri);
            _apimanager.CloseOrDispose();

        }

        public TransportMode[] GetTransportMode()
        {
            TransportMode[] transportmodes;
            string transporturi = GetBaseURL() + TransportModeUrl;
            
             transportmodes =  _apimanager.Get<TransportMode[]>(transporturi);
            _apimanager.CloseOrDispose();
            return transportmodes;


        }

        public void AddTransport(TransportMode tm)
        {
            string transporturi = GetBaseURL() + TransportModeUrl;
            _apimanager.Put<String, TransportMode>(transporturi, tm);
            _apimanager.CloseOrDispose();
        }

        public void UpdateTransport(TransportMode tm)
        {
            string transporturi = GetBaseURL() + TransportModeUrl;
            _apimanager.Post<String, TransportMode>(transporturi, tm);
            _apimanager.CloseOrDispose();
        }

        public void DeleteTransport(string ModeId)
        {
            string transporturi = string.Format(GetBaseURL() + TransportModeUrlById, ModeId);
            _apimanager.Delete(transporturi);
            _apimanager.CloseOrDispose();

        }

        public PackagingMode[] GetPackageMode()
        {
            PackagingMode[] packagemodes;
            string packagingmodeuri = GetBaseURL() + PackagingModeUrl;
            packagemodes =  _apimanager.Get<PackagingMode[]>(packagingmodeuri);

            _apimanager.CloseOrDispose();
            return packagemodes;
        }

        public void AddPackageMode(PackagingMode pm)
        {
            string packagingmodeuri = GetBaseURL() + PackagingModeUrl;
            _apimanager.Put<String, PackagingMode>(packagingmodeuri, pm);
            _apimanager.CloseOrDispose();
        }

        public void UpdatePackageMode(PackagingMode pm)
        {
            string packagingmodeuri = GetBaseURL() + PackagingModeUrl;
            _apimanager.Post<String, PackagingMode>(packagingmodeuri, pm);
            _apimanager.CloseOrDispose();
        }

        public void DeletePackageMode(string PackageModeId)
        {
            string packagingmodeuri = string.Format(GetBaseURL() + PackagingModeUrlById, PackageModeId);
            _apimanager.Delete(packagingmodeuri);
            _apimanager.CloseOrDispose();

        }

        public CenterMaster[] GetCenterMaster()
        {
            CenterMaster[] centermaster;
            string centermasteruri = GetBaseURL() + CenterMasterUrl;
            centermaster =  _apimanager.Get<CenterMaster[]>(centermasteruri);
            _apimanager.CloseOrDispose();
            return centermaster;
        }

        public void AddCenterMaster(CenterMaster cm)
        {
            string centermasteruri = GetBaseURL() + CenterMasterUrl;
            _apimanager.Put<String, CenterMaster>(centermasteruri, cm);
            _apimanager.CloseOrDispose();
        }

        public void UpdateCenterMaster(CenterMaster cm)
        {
            string centermasteruri = GetBaseURL() + CenterMasterUrl;
            _apimanager.Post<String, CenterMaster>(centermasteruri, cm);
            _apimanager.CloseOrDispose();
        }

        public void DeleteCenterMaster(string Id)
        {
            string centermasteruri = string.Format(GetBaseURL() + CenterMasterUrlById, Id);
            _apimanager.Delete(centermasteruri);
            _apimanager.CloseOrDispose();

        }

        public Company[] GetCompany()
        {
            Company[] company;
            string companyuri = GetBaseURL() + CompanyUrl;
            company =  _apimanager.Get<Company[]>(companyuri);
            _apimanager.CloseOrDispose();
            return company;
        }

        public void AddCompany(Company cm)
        {
            string companyuri = GetBaseURL() + CompanyUrl;
            _apimanager.Put<String, Company>(companyuri, cm);
            _apimanager.CloseOrDispose();
        }

        public void UpdateCompany(Company cm)
        {
            string companyuri = GetBaseURL() + CompanyUrl;
            _apimanager.Post<String, Company>(companyuri, cm);
            _apimanager.CloseOrDispose();
        }

        public void DeleteCompany(string Id)
        {
            string companyuri = string.Format(GetBaseURL() + CompanyUrl, Id);
            _apimanager.Delete(companyuri);
            _apimanager.CloseOrDispose();

        }

        public CarrierType[] GetCarrierTypes()
        {
            CarrierType[] carriertypes;
            string carriertypeuri = GetBaseURL() + CariierTypeUrl;
            carriertypes =  _apimanager.Get<CarrierType[]>(carriertypeuri);
            _apimanager.CloseOrDispose();
            return carriertypes;
        }

        public void AddCarrierType(CarrierType ct)
        {
            string carriertypeuri = GetBaseURL() + CariierTypeUrl;
            _apimanager.Put<String, CarrierType>(carriertypeuri, ct);
            _apimanager.CloseOrDispose();
        }

        public void UpdateCarrierType(CarrierType ct)
        {
            string carriertypeuri = GetBaseURL() + CariierTypeUrl;
            _apimanager.Post<String, CarrierType>(carriertypeuri, ct);
            _apimanager.CloseOrDispose();
        }

        public void DeleteCarrierType(string Id)
        {
            string companyuri = string.Format(GetBaseURL() + CarrierTypeById, Id);
            _apimanager.Delete(companyuri);
            _apimanager.CloseOrDispose();

        }

        public CnnoteResponse AddCNNotes(CNNote cnote)
        {
            string CNoteuri = GetBaseURL() + CNNoteUrl;
            HttpResponseMessage Httpresp = _apimanager.Put<String, CNNote>(CNoteuri, cnote);
            CnnoteResponse result = _apimanager.Unwrap<CnnoteResponse>(Httpresp);
            _apimanager.CloseOrDispose();
            return result;
        }

        public CnnoteResponse UpdateCNNotes(CNNote cnote)
        {
            string CNoteuri = GetBaseURL() + CNNoteUrl;
            HttpResponseMessage Httpresp = _apimanager.Post<String, CNNote>(CNoteuri, cnote);
            CnnoteResponse result = _apimanager.Unwrap<CnnoteResponse>(Httpresp);
            _apimanager.CloseOrDispose();
            return result;
        }

        public CNNotesFullDetails[] GetCNNotesFullDetails(string cnnotenumber)
        {
            CNNotesFullDetails[] cnnotesfulldetails;
            string CNoteuri = string.Format(GetBaseURL() + CNNotesFullDetailsUrl, cnnotenumber);
            cnnotesfulldetails =  _apimanager.Get<CNNotesFullDetails[]>(CNoteuri);
            _apimanager.CloseOrDispose();
            return cnnotesfulldetails;
        }

        public CNNote[] GetCNNoteByCnnNumber(string cnnotenumber)
        {
            CNNote[] cnnotes;
            string CNoteuri = string.Format(GetBaseURL() + CNNotessUrlByCNNum, cnnotenumber);
            cnnotes = _apimanager.Get<CNNote[]>(CNoteuri);
            _apimanager.CloseOrDispose();
            return cnnotes;
        }

        public void AddManifest(Manifest mf)
        {
            string ManifestUri = GetBaseURL() + ManifestUrl;
            _apimanager.Put<String, Manifest>(ManifestUri, mf);
            _apimanager.CloseOrDispose();
        }

        public ManifestDetail[] GetManifestDetailById(string manifestId)
        {
            ManifestDetail[] manifestdetail;
            string ManifestUri = string.Format(GetBaseURL() + ManifestDetailByIDUrl, manifestId);
            manifestdetail =  _apimanager.Get<ManifestDetail[]>(ManifestUri);
            _apimanager.CloseOrDispose();
            return manifestdetail;
        }

        public ManifestItemDetail[] GetManifestItemDetailByManifestId(string manifestId)
        {
            ManifestItemDetail[] manifestitemdetail;
            string ManifestItemDetailUri = string.Format(GetBaseURL() + ManifestItemDetailByManifestIdUrl, manifestId);
            manifestitemdetail = _apimanager.Get<ManifestItemDetail[]>(ManifestItemDetailUri);
            _apimanager.CloseOrDispose();
            return manifestitemdetail;
        }

        public CNNotesDetailsForManifest[] GetCNNotesForManifest(DateTime StartDate, DateTime EndDate, string cities )
        {
            CNNotesDetailsForManifest[] cnnotesdetail;
            string SDate = String.Format(StartDate.Year.ToString() + "-" + StartDate.Month.ToString().PadLeft(2, '0') + '-' + StartDate.Day.ToString().PadLeft(2, '0'));
            string EDate = String.Format(EndDate.Year.ToString() + "-" + EndDate.Month.ToString().PadLeft(2, '0') + '-' + EndDate.Day.ToString().PadLeft(2, '0'));
            string CNnotesForManifestUri = string.Format(GetBaseURL() + GetCNNotesDetailsForManifestUrl, SDate, EDate, cities);
            cnnotesdetail =  _apimanager.Get<CNNotesDetailsForManifest[]>(CNnotesForManifestUri);
            _apimanager.CloseOrDispose();
            return cnnotesdetail;
        }

        public void AddManifestItem(ManifestItem mi)
        {
            string ManifestItemUri = GetBaseURL() + ManifestItemUrl;
            _apimanager.Post<String, ManifestItem>(ManifestItemUri, mi);
            _apimanager.CloseOrDispose();

        }

        public CNNotesDetails[] GetCNNoteDetailForComapnyInDateRange(DateTime startDate, DateTime EndDate, string ShipperCompanyId)
        {
            CNNotesDetails[] result;
            string SDate = String.Format(startDate.Year.ToString() + "-" + startDate.Month.ToString().PadLeft(2, '0') + '-' + startDate.Day.ToString().PadLeft(2, '0'));
            string EDate = String.Format(EndDate.Year.ToString() + "-" + EndDate.Month.ToString().PadLeft(2, '0') + '-' + EndDate.Day.ToString().PadLeft(2, '0'));
            string CNnotesDetailsForCompanyInDateRangeUri = string.Format(GetBaseURL() + GetCNNotesDetailForDateRangeofCompany, SDate, EDate, ShipperCompanyId);
            result = _apimanager.Get<CNNotesDetails[]>(CNnotesDetailsForCompanyInDateRangeUri);
            _apimanager.CloseOrDispose();
            return result;

        }

        public Rates[] GetRates()
        {
            Rates[] rates;
            string rateuri = GetBaseURL() + RatesUrl;
            rates = _apimanager.Get<Rates[]>(rateuri);
            _apimanager.CloseOrDispose();
            return rates;
        }

        public Tax[] GetTax()
        {
            Tax[] tax;
            string taxuri = GetBaseURL() + TaxUrl;
            tax = _apimanager.Get<Tax[]>(taxuri);
            _apimanager.CloseOrDispose();
            return tax;
        }

        public void AddInvoiceRecord(Invoice invoice)
        {
            string InvoiceUri = GetBaseURL() + InvoiceUrl;
            _apimanager.Put<String, Invoice>(InvoiceUri, invoice);
            _apimanager.CloseOrDispose();
        }

        public void UpdateInvoiceRecord(Invoice invoice)
        {
            string InvoiceUri = GetBaseURL() + InvoiceUrl;
            _apimanager.Post<String, Invoice>(InvoiceUri, invoice);
            _apimanager.CloseOrDispose();
        }

        public Invoice[] GetInvoiceByInvoiceNum(string InvoiceNumber)
        {
            Invoice[] invoices;
            string InvoiceUri = string.Format(GetBaseURL() + InvoiceByInvoiceNumUrl, InvoiceNumber);
            invoices = _apimanager.Get<Invoice[]>(InvoiceUri);
            _apimanager.CloseOrDispose();
            return invoices;
        }

        public InvoiceDetail[] GetInvoiceDetailByInvoiceNum(string InvoiceNumber)
        {
            InvoiceDetail[] invoices;
            string InvoiceUri = string.Format(GetBaseURL() + InvoiceDetailByInvoiceNumUrl, InvoiceNumber);
            invoices = _apimanager.Get<InvoiceDetail[]>(InvoiceUri);
            _apimanager.CloseOrDispose();
            return invoices;
        }

        public void AddInvoiceItemRecord(InvoiceItem invoiceitem)
        {
            string InvoiceItemUri = GetBaseURL() + InvoiceItemUrl;
            _apimanager.Put<String, InvoiceItem>(InvoiceItemUri, invoiceitem);
            _apimanager.CloseOrDispose();
        }

        public void UpdateInvoiceItemRecord(InvoiceItem invoiceitem)
        {
            string InvoiceItemUri = GetBaseURL() + InvoiceItemUrl;
            _apimanager.Post<String, InvoiceItem>(InvoiceItemUri, invoiceitem);
            _apimanager.CloseOrDispose();
        }

        public InvoiceItem[] GetInvoiceItemsByInvoiceNum(string InvoiceNumber)
        {
            InvoiceItem[] invoicesitems;
            string InvoiceItemUri = string.Format(GetBaseURL() + InvoiceItemByInvoiceNumUrl, InvoiceNumber);
            invoicesitems = _apimanager.Get<InvoiceItem[]>(InvoiceItemUri);
            _apimanager.CloseOrDispose();
            return invoicesitems;
        }

        public InvoiceItemDetail[] GetInvoiceItemsDetailByInvoiceNum(string InvoiceNumber)
        {
            InvoiceItemDetail[] invoicesitems;
            string InvoiceItemUri = string.Format(GetBaseURL() + InvoiceItemDetailByInvoiceNumUrl, InvoiceNumber);
            invoicesitems = _apimanager.Get<InvoiceItemDetail[]>(InvoiceItemUri);
            _apimanager.CloseOrDispose();
            return invoicesitems;
        }

        public void AddTaxInvoiceItemRecord(TaxInvoice invoiceitem)
        {
            string InvoiceItemUri = GetBaseURL() + TaxInvoiceUrl;
            _apimanager.Put<String, TaxInvoice>(InvoiceItemUri, invoiceitem);
            _apimanager.CloseOrDispose();
        }

        public void UpdateTaxInvoiceItemRecord(TaxInvoice invoiceitem)
        {
            string InvoiceItemUri = GetBaseURL() + TaxInvoiceUrl;
            _apimanager.Post<String, TaxInvoice>(InvoiceItemUri, invoiceitem);
            _apimanager.CloseOrDispose();
        }

        public TaxInvoice[] GetTaxInvoiceItemRecordByInvoiceNum(string InvoiceNumber)
        {
            TaxInvoice[] taxinvoicesitems;
            string TaxInvoiceItemUri = string.Format(GetBaseURL() + GetTaxInvoiceByInvoiceNum, InvoiceNumber);
            taxinvoicesitems = _apimanager.Get<TaxInvoice[]>(TaxInvoiceItemUri);
            _apimanager.CloseOrDispose();
            return taxinvoicesitems;
        }

        public TaxInvoiceDetail[] GetTaxInvoiceDetailRecordByInvoiceNum(string InvoiceNumber)
        {
            TaxInvoiceDetail[] taxinvoicesitems;
            string TaxInvoiceItemUri = string.Format(GetBaseURL() + GetTaxInvoiceDetailByInvoiceNum, InvoiceNumber);
            taxinvoicesitems = _apimanager.Get<TaxInvoiceDetail[]>(TaxInvoiceItemUri);
            _apimanager.CloseOrDispose();
            return taxinvoicesitems;
        }

        public Invoice[] GetInvoiceForCompanyInDateRange(DateTime FromDate, DateTime ToDate, string companyid, string centerid)
        {
            Invoice[] invoices;
            string SDate = String.Format(FromDate.Year.ToString() + "-" + FromDate.Month.ToString().PadLeft(2, '0') + '-' + FromDate.Day.ToString().PadLeft(2, '0'));
            string EDate = String.Format(ToDate.Year.ToString() + "-" + ToDate.Month.ToString().PadLeft(2, '0') + '-' + ToDate.Day.ToString().PadLeft(2, '0'));
            string InvoiceUri = string.Format(GetBaseURL() + GetInvoicesinDateRangeForCompany, SDate, EDate, companyid, centerid);
            invoices = _apimanager.Get<Invoice[]>(InvoiceUri);
            _apimanager.CloseOrDispose();
            return invoices;

        }

        public Invoice[] GetActiveInvoicesForCompanyInDateRange(DateTime FromDate, DateTime ToDate, string companyid, string centerid)
        {
            Invoice[] invoices;
            string SDate = String.Format(FromDate.Year.ToString() + "-" + FromDate.Month.ToString().PadLeft(2, '0') + '-' + FromDate.Day.ToString().PadLeft(2, '0'));
            string EDate = String.Format(ToDate.Year.ToString() + "-" + ToDate.Month.ToString().PadLeft(2, '0') + '-' + ToDate.Day.ToString().PadLeft(2, '0'));
            string InvoiceUri = string.Format(GetBaseURL() + GetActiveInvoicesForCompanyInDateRangeUrl, SDate, EDate, companyid, centerid);
            invoices = _apimanager.Get<Invoice[]>(InvoiceUri);
            _apimanager.CloseOrDispose();
            return invoices;
        }

        public Invoice[] GetActiveInvoicesForCompany(string companyid, string centerid)
        {
            Invoice[] invoices;
            string InvoiceUri = string.Format(GetBaseURL() + GetActiveInvoicesForCompanyUrl, companyid, centerid);
            invoices = _apimanager.Get<Invoice[]>(InvoiceUri);
            _apimanager.CloseOrDispose();
            return invoices;
        }

        public Payment[] GetPaymentsForInvoice(string centerid, string InvoiceNum)
        {
            Payment[] payments;
            string PaymentUri = string.Format(GetBaseURL() + GetPaymentForInvoiceNumAndCenterIdUrl, centerid, InvoiceNum);
            payments = _apimanager.Get<Payment[]>(PaymentUri);
            _apimanager.CloseOrDispose();
            return payments;
        }

        public Payment[] GetPayments()
        {
            Payment[] payments;
            string paymenturi = GetBaseURL() + PaymentUrl;
            payments = _apimanager.Get<Payment[]>(paymenturi);
            _apimanager.CloseOrDispose();
            return payments;
        }

        public PaymentResponse AddPayment(Payment payment)
        {
            string paymenturi = GetBaseURL() + PaymentUrl;
            HttpResponseMessage resp =  _apimanager.Put<String, Payment>(paymenturi, payment);

            PaymentResponse result = _apimanager.Unwrap<PaymentResponse>(resp);
            _apimanager.CloseOrDispose();
            return result;
        }

        public void UpdatePayment(Payment city)
        {
            string paymenturi = GetBaseURL() + PaymentUrl;
            _apimanager.Post<String, Payment>(paymenturi, city);
            _apimanager.CloseOrDispose();
        }

        public void DeletePayment(string paymentid)
        {
            string paymenturi = string.Format(GetBaseURL() + PaymentUrlById, paymentid);
            _apimanager.Delete(paymenturi);
            _apimanager.CloseOrDispose();
           
        }

        public Payment[] GetPaymentById(string paymentid)
        {
            string paymenturi = string.Format(GetBaseURL() + PaymentUrlById, paymentid);
            Payment[]  result =_apimanager.Get<Payment[]>(paymenturi);
            _apimanager.CloseOrDispose();
            return result;
        }

        
    }
}
