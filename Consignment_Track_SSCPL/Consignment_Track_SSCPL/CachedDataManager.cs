using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class CachedDataManager
    {
        public static City[] GetCachedCity()
        {
            City[] cities = null;

            if(File.Exists("cities.json"))
            {
                try
                {
                    cities = JsonConvert.DeserializeObject<City[]>(File.ReadAllText("cities.json"));
                    return cities;
                }
                catch(Exception)
                {
                    return GetCitiesAndUpdateCache();
                }
            }
            else
            {
                return GetCitiesAndUpdateCache();
            }

        }

        public static City[] GetCitiesAndUpdateCache()
        {
            City[] cities = null;

            cities = new DataManager().GetCities();
            if (cities != null)
            {
                try
                {
                    System.IO.File.WriteAllText("cities.json", JsonConvert.SerializeObject(cities));
                }
                catch (Exception) { }
            }

            return cities;
        }

        public static TransportMode[] GetCachedTransportMode()
        {
            TransportMode[] transport = null;

            if (File.Exists("transportmode.json"))
            {
                try
                {
                    transport = JsonConvert.DeserializeObject<TransportMode[]>(File.ReadAllText("transportmode.json"));
                    return transport;
                }
                catch (Exception)
                {
                    return GetTransportModeAndUpdateCache();
                }
            }
            else
            {
                return GetTransportModeAndUpdateCache();
            }

        }

        public static TransportMode[] GetTransportModeAndUpdateCache()
        {
            TransportMode[] transport = null;

            transport = new DataManager().GetTransportMode();
            if (transport != null)
            {
                try
                {
                    System.IO.File.WriteAllText("transportmode.json", JsonConvert.SerializeObject(transport));
                }
                catch (Exception) { }
            }

            return transport;
        }

        public static PackagingMode[] GetCachedPackagingMode()
        {
            PackagingMode[] packagemodes = null;

            if (File.Exists("packagemodes.json"))
            {
                try
                {
                    packagemodes = JsonConvert.DeserializeObject<PackagingMode[]>(File.ReadAllText("packagemodes.json"));
                    return packagemodes;
                }
                catch (Exception)
                {
                    return GetPackagingModeAndUpdateCache();
                }
            }
            else
            {
                return GetPackagingModeAndUpdateCache();
            }

        }

        public static PackagingMode[] GetPackagingModeAndUpdateCache()
        {
            PackagingMode[] packagemodes = null;

            packagemodes = new DataManager().GetPackageMode();
            if (packagemodes != null)
            {
                try
                {
                    System.IO.File.WriteAllText("packagemodes.json", JsonConvert.SerializeObject(packagemodes));
                }
                catch (Exception) { }
            }

            return packagemodes;
        }

        public static Company[] GetCachedCompany()
        {
            Company[] company = null;

            if (File.Exists("company.json"))
            {
                try
                {
                    company = JsonConvert.DeserializeObject<Company[]>(File.ReadAllText("company.json"));
                    return company;
                }
                catch (Exception)
                {
                    return GetCompanyAndUpdateCache();
                }
            }
            else
            {
                return GetCompanyAndUpdateCache();
            }

        }

        public static Company[] GetCompanyAndUpdateCache()
        {
            Company[] company = null;

            company = new DataManager().GetCompany();
            if (company != null)
            {
                try
                {
                    System.IO.File.WriteAllText("company.json", JsonConvert.SerializeObject(company));
                }
                catch (Exception) { }
            }

            return company;
        }

        public static CenterMaster[] GetCachedCenterMaster()
        {
            CenterMaster[] centermaster = null;

            if (File.Exists("centres.json"))
            {
                try
                {
                    centermaster = JsonConvert.DeserializeObject<CenterMaster[]>(File.ReadAllText("centres.json"));
                    return centermaster;
                }
                catch (Exception)
                {
                    return GetCenterMasterAndUpdateCache();
                }
            }
            else
            {
                return GetCenterMasterAndUpdateCache();
            }

        }

        public static CenterMaster[] GetCenterMasterAndUpdateCache()
        {
            CenterMaster[] centermaster = null;

            centermaster = new DataManager().GetCenterMaster();
            if (centermaster != null)
            {
                try
                {
                    System.IO.File.WriteAllText("centres.json", JsonConvert.SerializeObject(centermaster));
                }
                catch (Exception) { }
            }

            return centermaster;
        }

        public static CarrierType[] GetCachedCarrierType()
        {
            CarrierType[] carriertype = null;

            if (File.Exists("carriertype.json"))
            {
                try
                {
                    carriertype = JsonConvert.DeserializeObject<CarrierType[]>(File.ReadAllText("carriertype.json"));
                    return carriertype;
                }
                catch (Exception)
                {
                    return GetCarrierTypeAndUpdateCache();
                }
            }
            else
            {
                return GetCarrierTypeAndUpdateCache();
            }

        }

        public static CarrierType[] GetCarrierTypeAndUpdateCache()
        {
            CarrierType[] carriertype = null;

            carriertype = new DataManager().GetCarrierTypes();
            if (carriertype != null)
            {
                try
                {
                    System.IO.File.WriteAllText("carriertype.json", JsonConvert.SerializeObject(carriertype));
                }
                catch (Exception) { }
            }

            return carriertype;
        }

        public static Rates[] GetCachedRate()
        {
           Rates[] rates = null;

            if (File.Exists("rates.json"))
            {
                try
                {
                    rates = JsonConvert.DeserializeObject<Rates[]>(File.ReadAllText("rates.json"));
                    return rates;
                }
                catch (Exception)
                {
                    return GetRatesAndUpdateCache();
                }
            }
            else
            {
                return GetRatesAndUpdateCache();
            }

        }

        public static Rates[] GetRatesAndUpdateCache()
        {
            Rates[] rates = null;

            rates = new DataManager().GetRates();
            if (rates != null)
            {
                try
                {
                    System.IO.File.WriteAllText("rates.json", JsonConvert.SerializeObject(rates));
                }
                catch (Exception) { }
            }

            return rates;
        }

        public static Tax[] GetCachedTax()
        {
            Tax[] tax = null;

            if (File.Exists("tax.json"))
            {
                try
                {
                    tax = JsonConvert.DeserializeObject<Tax[]>(File.ReadAllText("tax.json"));
                    return tax;
                }
                catch (Exception)
                {
                    return GetTaxAndUpdateCache();
                }
            }
            else
            {
                return GetTaxAndUpdateCache();
            }

        }

        public static Tax[] GetTaxAndUpdateCache()
        {
            Tax[] tax = null;

            tax = new DataManager().GetTax();
            if (tax != null)
            {
                try
                {
                    System.IO.File.WriteAllText("tax.json", JsonConvert.SerializeObject(tax));
                }
                catch (Exception) { }
            }

            return tax;
        }

    }
}
