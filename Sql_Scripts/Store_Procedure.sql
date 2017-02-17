CREATE DEFINER=`root`@`localhost` PROCEDURE `GetActiveInvoicesforCompany`(in companyid INT, in centerId INT)
BEGIN

select Inv.* from invoice Inv where (Inv.InvoiceNum not in (select InvoiceNum from payment) 
or TotalAmount > (select Sum(Amount) from payment where InvoiceNum = Inv.InvoiceNum) ) 
And Inv.CompanyId = companyid And Inv.CenterId = centerId

 ;


END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetActiveInvoicesforCompanyInDateRange`(in fromDate Date, in toDate Date, in companyid INT, in centerId INT)
BEGIN

select Inv.* from invoice Inv where (Inv.InvoiceNum not in (select InvoiceNum from payment) 
or TotalAmount > (select Sum(Amount) from payment where InvoiceNum = Inv.InvoiceNum) ) 
And Inv.FromDate <= toDate And Inv.ToDate >= fromDate And Inv.CompanyId = companyid And Inv.CenterId = centerId

 ;


END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetaiils`()
BEGIN

select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID;

END


CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailByDestCityAndInDateRange`(in destCityId INT, in StartDate Date, in EndDate Date )
BEGIN
select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND cn.DestCityID = destCityId;
  
END


CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailForInvoiceByDestCityAndInDateRange`(in destCityId INT, in StartDate Date, in EndDate Date )
BEGIN
select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND cn.DestCityID = destCityId
AND (cn.CNNumber not in (select CNoteNo from manifestitems));
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailForManifest`(in ManifestIdd varchar(100))
BEGIN

select cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress, sc.CityName as SourceCity, 
sc.CityCode as SourceCityCode, dc.CityName AS DestCity, dc.CityCode as DestCityCode, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, shipc.CompanyCCode as ShipperCompanyCode, shipc.CompanyAddress As ShipperCompanyAddress,
shipc.CompanyContactPerson as ShipperContactPerson, shipc.CompanyEmailId as ShipperEmailID, shipc.CompanyPrimaryContactNumber as ShipperPrimaryContactNumber, shipc.CompanySecondaryContactNumber As ShipperSecondarycontactNumber,
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity, conc.CompanyCCode as ConsigneeCompanyCode, conc.CompanyAddress as ConsigneeCompanyAddress,
conc.CompanyContactPerson as ConsigneeContactPerson, conc.CompanyEmailId as ConsigneeEmailID, conc.CompanyPrimaryContactNumber as ConsigneePrimaryContactNumber, conc.CompanySecondaryContactNumber As ConsigneeSecondaryContactNumber,
cn.*, 'SignImageURL' as SignImageURL, 'SignImageDeliveredURL' as SignImageDeliveredURL from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.CNNumber in (select CNoteNo from ManifestItems where ManifestId=ManifestIdd);


END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsForComapanyInDateRange`(in StartDate Date, in EndDate Date, in ShipperCompId INT)
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate  AND cn.ShipperCompId = ShipperCompId;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsForDateRange`(in StartDate Date, in EndDate Date)
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate ;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsForInvoiceComapanyInDateRange`(in StartDate Date, in EndDate Date, in ShipperCompId INT)
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate  AND cn.ShipperCompId = ShipperCompId
AND (cn.CNNumber not in (select CNNumber from invoiceitems));

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsForManifestWithMultipleCityInDateRange`(in StartDate Date, in EndDate Date, IN City_Array_String VARCHAR(100))
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
mi.LoadedQuantity as ManifestItemLoadedQuantity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join manifestitems mi on mi.CNoteNo = cn.CNNumber
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND FIND_IN_SET(cn.DestCityID, City_Array_String)
AND (cn.CNNumber not in (select CNoteNo from manifestitems) OR cn.PackageNo > mi.LoadedQuantity) 
;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsForModeInDateRange`(in StartDate Date, in EndDate Date, in ModeID int)
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND cn.ModeID = ModeID ;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsForPaymentModeInDateRange`(in StartDate Date, in EndDate Date, in ToPayMode int)
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND cn.ToPayMode = ToPayMode ;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsOfDate`(in DateofCNNote Date)
BEGIN
   select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate =DateofCNNote ;    
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsWithMultipleCityInDateRange`(in StartDate Date, in EndDate Date, IN City_Array_String VARCHAR(100))
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND FIND_IN_SET(cn.DestCityID, City_Array_String);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteDetailsWithMultipleParameter`(in destCityId INT, in StartDate Date, in EndDate Date, in ToPayMode int, in ModeId int)
BEGIN
  select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.BookingDate >=StartDate  AND cn.BookingDate <=EndDate AND cn.DestCityID = destCityId AND cn.ToPayMode AND cn.ModeID = ModeId ;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNOteforShipperCompany`(in ShipperCompId INT)
BEGIN
select cm.Name as CenterName , cm.City as CenterCity, sc.CityName as SourceCity, dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,
cn.* from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.ShipperCompId = ShipperCompId ;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCNNoteWithMultipleCityInDateRange`(in StartDate Date, in EndDate Date, IN City_Array_String VARCHAR(100))
BEGIN
  select * from cnnote 
 where BookingDate >=StartDate  AND BookingDate <=EndDate AND FIND_IN_SET(DestCityID, City_Array_String);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetFullCNNDetail`(in CnoteNumber varchar(20))
BEGIN
  
select cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress, sc.CityName as SourceCity, 
sc.CityCode as SourceCityCode, dc.CityName AS DestCity, dc.CityCode as DestCityCode, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, shipc.CompanyCCode as ShipperCompanyCode, shipc.CompanyAddress As ShipperCompanyAddress,
shipc.CompanyContactPerson as ShipperContactPerson, shipc.CompanyEmailId as ShipperEmailID, shipc.CompanyPrimaryContactNumber as ShipperPrimaryContactNumber, shipc.CompanySecondaryContactNumber As ShipperSecondarycontactNumber,
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity, conc.CompanyCCode as ConsigneeCompanyCode, conc.CompanyAddress as ConsigneeCompanyAddress,
conc.CompanyContactPerson as ConsigneeContactPerson, conc.CompanyEmailId as ConsigneeEmailID, conc.CompanyPrimaryContactNumber as ConsigneePrimaryContactNumber, conc.CompanySecondaryContactNumber As ConsigneeSecondaryContactNumber,
cn.*, 'SignImageURL' as SignImageURL, 'SignImageDeliveredURL' as SignImageDeliveredURL from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.CNNumber = CnoteNumber;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetInvoiceDetailsForInvoiceNum`(IN InvoiceNum varchar(70))
BEGIN

select cm.Name as CenterName , cm.City as CenterCity, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
ra.Rate as Rate , ra.KG as RateforKGMax,
Inv.* from Invoice  Inv 
left join centermaster cm on cm.centerid = Inv.CenterId

left join company shipc on shipc.CompanyId = Inv.CompanyId

left join rate ra on ra.RateId = Inv.RateId
where Inv.InvoiceNum = InvoiceNum ;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetInvoiceDetailsForInvoiceNum`(IN InvoiceNum varchar(70))
BEGIN

select cm.Name as CenterName , cm.City as CenterCity, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, 
ra.Rate as Rate , ra.KG as RateforKGMax,
Inv.* from Invoice  Inv 
left join centermaster cm on cm.centerid = Inv.CenterId

left join company shipc on shipc.CompanyId = Inv.CompanyId

left join rate ra on ra.RateId = Inv.RateId
where Inv.InvoiceNum = InvoiceNum ;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetInvoiceItemDetailsForInvoiceNum`(IN InvoiceNum varchar(70))
BEGIN

select cn.* ,
ra.Rate as Rate , ra.KG as RateforKGMax,
Invt.* from InvoiceItems  Invt 
left join cnnote cn on cn.CNNumber = Invt.CNNumber

left join rate ra on ra.RateId = Invt.RateId
where Invt.InvoiceNum = InvoiceNum ;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestDetail`()
BEGIN
  
select sc.CityName as SourceCity, 
dc.CityName AS DestCity,
ct.Typee AS CarrierType, ct.Capacity, ct.Model , ct.ModelID, ct.CNumber As CarrierNumber, 
cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress,
mf.* from Manifest mf 
left join centermaster cm on cm.centerid = mf.CenterID
left join city sc on sc.cityid = mf.SourceCityID
left join city dc on dc.cityid = mf.TargetCityID
left join carriertype ct on ct.Id = mf.CarrierTypeID ;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestDetailByDateAndofSourceCity`(IN ManifestDate Date, IN SourceCityID INT )
BEGIN
  
select sc.CityName as SourceCity, 
dc.CityName AS DestCity,
ct.Typee AS CarrierType, ct.Capacity, ct.Model , ct.ModelID, ct.CNumber As CarrierNumber, 
cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress,
mf.* from Manifest mf 
left join centermaster cm on cm.centerid = mf.CenterID
left join city sc on sc.cityid = mf.SourceCityID
left join city dc on dc.cityid = mf.TargetCityID
left join carriertype ct on ct.Id = mf.CarrierTypeID where mf.ManifestDate = ManifestDate AND mf.SourceCityID = SourceCityID;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestDetailByDateAndofTargetCity`(IN ManifestDate Date, IN TargetCityID INT )
BEGIN
  
select sc.CityName as SourceCity, 
dc.CityName AS DestCity,
ct.Typee AS CarrierType, ct.Capacity, ct.Model , ct.ModelID, ct.CNumber As CarrierNumber, 
cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress,
mf.* from Manifest mf 
left join centermaster cm on cm.centerid = mf.CenterID
left join city sc on sc.cityid = mf.SourceCityID
left join city dc on dc.cityid = mf.TargetCityID
left join carriertype ct on ct.Id = mf.CarrierTypeID where mf.ManifestDate = ManifestDate AND mf.TargetCityID = TargetCityID;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestDetailByDateRange`(IN ManifestStartDate Date, IN ManifestEndDate Date )
BEGIN
  
select sc.CityName as SourceCity, 
dc.CityName AS DestCity,
ct.Typee AS CarrierType, ct.Capacity, ct.Model , ct.ModelID, ct.CNumber As CarrierNumber, 
cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress,
mf.* from Manifest mf 
left join centermaster cm on cm.centerid = mf.CenterID
left join city sc on sc.cityid = mf.SourceCityID
left join city dc on dc.cityid = mf.TargetCityID
left join carriertype ct on ct.Id = mf.CarrierTypeID where mf.ManifestDate >= ManifestStartDate AND mf.ManifestDate <= ManifestEndDate;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestDetailByManifestId`(IN ManifestId varchar(100))
BEGIN
  
select sc.CityName as SourceCity, sc.CityCode as SourceCityCode,
dc.CityName AS DestCity, dc.CityCode as DestCityCode,
ct.Typee AS CarrierType, ct.Capacity, ct.Model , ct.ModelID, ct.CNumber As CarrierNumber, 
cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress,
mf.* from Manifest mf 
left join centermaster cm on cm.centerid = mf.CenterID
left join city sc on sc.cityid = mf.SourceCityID
left join city dc on dc.cityid = mf.TargetCityID
left join carriertype ct on ct.Id = mf.CarrierTypeID where mf.ManifestId = ManifestId;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestDetailOfDate`(IN ManifestDate Date)
BEGIN
  
select sc.CityName as SourceCity, 
dc.CityName AS DestCity,
ct.Typee AS CarrierType, ct.Capacity, ct.Model , ct.ModelID, ct.CNumber As CarrierNumber, 
cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress,
mf.* from Manifest mf 
left join centermaster cm on cm.centerid = mf.CenterID
left join city sc on sc.cityid = mf.SourceCityID
left join city dc on dc.cityid = mf.TargetCityID
left join carriertype ct on ct.Id = mf.CarrierTypeID where mf.ManifestDate = ManifestDate;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestForVehicleNuminDateRange`(in RegistrationNum varchar(40), in StartDate Date, in EndDate Date )
BEGIN

select * from Manifest where CarrierTypeID in 
(select ID from CarrierType where CNumber=RegistrationNum) and ManifestDate >= StartDate And ManifestDate <=EndDate ;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetManifestItemDetailForManifest`(in ManifestId varchar(50))
BEGIN
  
select sc.CityName as SourceCity, 
dc.CityName AS DestCity, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity,  shipc.CompanyAddress As ShipperCompanyAddress,
shipc.CompanyContactPerson as ShipperContactPerson,  shipc.CompanyPrimaryContactNumber as ShipperPrimaryContactNumber, 
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity,  conc.CompanyAddress as ConsigneeCompanyAddress,
conc.CompanyContactPerson as ConsigneeContactPerson, conc.CompanyPrimaryContactNumber as ConsigneePrimaryContactNumber, 
cn.CNNumber, cn.BookingDate, cn.PackageNo, cn.ActualWeight, cn.ConsignmentWeight, cn.MaterialDesc , mf.ManifestId , mi.LoadedQuantity from ManifestItems  mi 
left join cnnote cn on cn.CNNumber = mi.CNoteNo
left join manifest mf on mf.ManifestId = mi.ManifestId
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where mi.ManifestId = ManifestId;
  
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTaxInvoiceDetailsForInvoiceNum`(IN InvoiceNum varchar(70))
BEGIN

select tax.TaxName as TaxName , tax.Percentage as Percentage, tax.Statuss as Statuss, tax.StartDate As StartDate, tax.EndDate As EndDate, tax.CenterId As CenterId,
Invt.* from taxinvoice  Invt 

left join tax tax on tax.TaxId = Invt.TaxId
where Invt.InvoiceNum = InvoiceNum ;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPaymentInfoWithTaxForCnnumber`(in CnoteNumber varchar(20))
BEGIN

select cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress, sc.CityName as SourceCity, 
sc.CityCode as SourceCityCode, dc.CityName AS DestCity, dc.CityCode as DestCityCode, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, shipc.CompanyCCode as ShipperCompanyCode, shipc.CompanyAddress As ShipperCompanyAddress,
shipc.CompanyContactPerson as ShipperContactPerson, shipc.CompanyEmailId as ShipperEmailID, shipc.CompanyPrimaryContactNumber as ShipperPrimaryContactNumber, shipc.CompanySecondaryContactNumber As ShipperSecondarycontactNumber,
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity, conc.CompanyCCode as ConsigneeCompanyCode, conc.CompanyAddress as ConsigneeCompanyAddress,
conc.CompanyContactPerson as ConsigneeContactPerson, conc.CompanyEmailId as ConsigneeEmailID, conc.CompanyPrimaryContactNumber as ConsigneePrimaryContactNumber, conc.CompanySecondaryContactNumber As ConsigneeSecondaryContactNumber,
cn.*, 'RatePerKG' as RatePerKG, 'Amount' as Amount , 'TaxableAmount' as TaxableAmount,
'TotalAmount' as TotalAmount, 'TaxDetails' as TaxDetails from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.CNNumber = CnoteNumber;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPaymentInfoForCnnumber`(in CnoteNumber varchar(20))
BEGIN

select cm.Name as CenterName , cm.City as CenterCity, cm.Address as CenterAddress, sc.CityName as SourceCity, 
sc.CityCode as SourceCityCode, dc.CityName AS DestCity, dc.CityCode as DestCityCode, pm.Typee AS PayMode, 
tm.Modee AS TransportMode, shipc.CompanyName as ShipperCompany, shipc.CompanyCity as ShipperCity, shipc.CompanyCCode as ShipperCompanyCode, shipc.CompanyAddress As ShipperCompanyAddress,
shipc.CompanyContactPerson as ShipperContactPerson, shipc.CompanyEmailId as ShipperEmailID, shipc.CompanyPrimaryContactNumber as ShipperPrimaryContactNumber, shipc.CompanySecondaryContactNumber As ShipperSecondarycontactNumber,
conc.CompanyName as ConsigneeCompany, conc.CompanyCity as ConsigneeCity, conc.CompanyCCode as ConsigneeCompanyCode, conc.CompanyAddress as ConsigneeCompanyAddress,
conc.CompanyContactPerson as ConsigneeContactPerson, conc.CompanyEmailId as ConsigneeEmailID, conc.CompanyPrimaryContactNumber as ConsigneePrimaryContactNumber, conc.CompanySecondaryContactNumber As ConsigneeSecondaryContactNumber,
cn.*, 'RatePerKG' as RatePerKG, 'Amount' as Amount , 'TotalAmount' as TotalAmount from cnnote  cn 
left join centermaster cm on cm.centerid = cn.centerid
left join city sc on sc.cityid = cn.origincityid
left join city dc on dc.cityid = cn.destcityId
left join company shipc on shipc.CompanyId = cn.shippercompid
left join company conc on conc.CompanyId = cn.ConsigneeCompId
left join packagingmode pm on pm.Id = cn.ToPayMode
left join transportmode tm on tm.ModelId = cn.ModeID where cn.CNNumber = CnoteNumber;

END