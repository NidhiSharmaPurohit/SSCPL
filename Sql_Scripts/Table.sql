CREATE TABLE `carriertype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Typee` varchar(100) DEFAULT NULL,
  `Capacity` double NOT NULL,
  `Model` varchar(50) DEFAULT NULL,
  `ModelID` varchar(50) DEFAULT NULL,
  `CNumber` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `CNumber` (`CNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `centermaster` (
  `CenterId` int(11) NOT NULL AUTO_INCREMENT,
  `Status` varchar(30) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Name` varchar(150) DEFAULT NULL,
  `Address` varchar(400) DEFAULT NULL,
  `City` varchar(100) DEFAULT NULL,
  `State` varchar(100) DEFAULT NULL,
  `Sector` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CenterId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `city` (
  `CityId` int(11) NOT NULL AUTO_INCREMENT,
  `CityName` varchar(200) DEFAULT NULL,
  `CityCode` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`CityId`),
  UNIQUE KEY `CityName` (`CityName`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


CREATE TABLE `cnnote` (
  `CenterID` int(11) DEFAULT NULL,
  `CNNumber` varchar(15) NOT NULL,
  `BookingDate` date NOT NULL,
  `PackageNo` int(11) DEFAULT NULL,
  `ModeID` int(11) NOT NULL,
  `ActualWeight` double DEFAULT NULL,
  `ConsignmentWeight` double DEFAULT NULL,
  `MaterialDesc` varchar(200) DEFAULT NULL,
  `ShipperCompId` int(11) DEFAULT NULL,
  `ConsigneeCompId` int(11) DEFAULT NULL,
  `OriginCityID` int(11) DEFAULT NULL,
  `DestCityID` int(11) DEFAULT NULL,
  `ToPayMode` int(11) DEFAULT NULL,
  `ServiceTax` double DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `Remarks` varchar(40) DEFAULT NULL,
  `Status` varchar(40) DEFAULT NULL,
  `HandedBy` varchar(100) DEFAULT NULL,
  `ReceivedBy` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CNNumber`),
  KEY `transportmode_ModelId_fk` (`ModeID`),
  KEY `centermaster_CenterId_fk` (`CenterID`),
  KEY `packagingmode_Id_fk` (`ToPayMode`),
  KEY `city_CityId_fk` (`OriginCityID`),
  KEY `city_CityIdDest_fk` (`DestCityID`),
  KEY `shippercompany_ShipperCompId_fk` (`ShipperCompId`),
  KEY `consigneecompany_ConsigneeCompId_fk` (`ConsigneeCompId`),
  CONSTRAINT `centermaster_CenterId_fk` FOREIGN KEY (`CenterID`) REFERENCES `centermaster` (`CenterId`),
  CONSTRAINT `city_CityIdDest_fk` FOREIGN KEY (`DestCityID`) REFERENCES `city` (`CityId`),
  CONSTRAINT `city_CityId_fk` FOREIGN KEY (`OriginCityID`) REFERENCES `city` (`CityId`),
  CONSTRAINT `consigneecompany_ConsigneeCompId_fk` FOREIGN KEY (`ConsigneeCompId`) REFERENCES `company` (`CompanyId`),
  CONSTRAINT `packagingmode_Id_fk` FOREIGN KEY (`ToPayMode`) REFERENCES `packagingmode` (`Id`),
  CONSTRAINT `shippercompany_ShipperCompId_fk` FOREIGN KEY (`ShipperCompId`) REFERENCES `company` (`CompanyId`),
  CONSTRAINT `transportmode_ModelId_fk` FOREIGN KEY (`ModeID`) REFERENCES `transportmode` (`ModelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `company` (
  `CompanyId` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(500) DEFAULT NULL,
  `CompanyAddress` varchar(1000) DEFAULT NULL,
  `CompanyCity` varchar(200) DEFAULT NULL,
  `CompanyState` varchar(150) DEFAULT NULL,
  `CompanyCCode` varchar(8) DEFAULT NULL,
  `CompanyContactPerson` varchar(100) DEFAULT NULL,
  `CompanyEmailId` varchar(100) DEFAULT NULL,
  `CompanyPrimaryContactNumber` varchar(20) DEFAULT NULL,
  `CompanySecondaryContactNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CompanyId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


CREATE TABLE `drsitems` (
  `DRSItemNumber` int(11) NOT NULL AUTO_INCREMENT,
  `DRSNumber` varchar(100) DEFAULT NULL,
  `CompanyId` int(11) DEFAULT NULL,
  `NoOfBag` int(11) NOT NULL,
  `Weight` double NOT NULL,
  PRIMARY KEY (`DRSItemNumber`),
  KEY `DRunSheet_DRSNumber_fk` (`DRSNumber`),
  KEY `company_CompId_fk` (`CompanyId`),
  CONSTRAINT `DRunSheet_DRSNumber_fk` FOREIGN KEY (`DRSNumber`) REFERENCES `drunsheet` (`DRSNumber`),
  CONSTRAINT `company_CompId_fk` FOREIGN KEY (`CompanyId`) REFERENCES `company` (`CompanyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `drunsheet` (
  `DRSNumber` varchar(100) NOT NULL,
  `CenterID` int(11) DEFAULT NULL,
  `DRDate` date NOT NULL,
  `FromCityID` int(11) DEFAULT NULL,
  `ToCityID` int(11) DEFAULT NULL,
  `CarrierTypeID` int(11) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DRSNumber`),
  KEY `cemaster_CenterId_fk` (`CenterID`),
  KEY `Fromcity_SourceCityID_fk` (`FromCityID`),
  KEY `Tocity_TargetCityID_fk` (`ToCityID`),
  KEY `CarrierType_Id_fk` (`CarrierTypeID`),
  CONSTRAINT `CarrierType_Id_fk` FOREIGN KEY (`CarrierTypeID`) REFERENCES `carriertype` (`Id`),
  CONSTRAINT `Fromcity_SourceCityID_fk` FOREIGN KEY (`FromCityID`) REFERENCES `city` (`CityId`),
  CONSTRAINT `Tocity_TargetCityID_fk` FOREIGN KEY (`ToCityID`) REFERENCES `city` (`CityId`),
  CONSTRAINT `cemaster_CenterId_fk` FOREIGN KEY (`CenterID`) REFERENCES `centermaster` (`CenterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `expenses` (
  `ExpenseId` int(11) NOT NULL AUTO_INCREMENT,
  `CenterId` int(11) DEFAULT NULL,
  `Typee` varchar(100) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Modee` varchar(100) DEFAULT NULL,
  `ExpenseDate` date NOT NULL,
  PRIMARY KEY (`ExpenseId`),
  KEY `CentreE_CentreId_fk` (`CenterId`),
  CONSTRAINT `CentreE_CentreId_fk` FOREIGN KEY (`CenterId`) REFERENCES `centermaster` (`CenterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `invoice` (
  `InvoiceNum` varchar(70) NOT NULL,
  `CompanyId` int(11) DEFAULT NULL,
  `CenterId` int(11) DEFAULT NULL,
  `RateId` int(11) NOT NULL,
  `Statuss` varchar(50) DEFAULT NULL,
  `Amount` double NOT NULL,
  `Discount` double DEFAULT '0',
  `TotalAmount` double NOT NULL,
  `InvoiceDate` date DEFAULT NULL,
  `FromDate` date DEFAULT NULL,
  `ToDate` date DEFAULT NULL,
  PRIMARY KEY (`InvoiceNum`),
  KEY `companyIn_CompId_fk` (`CompanyId`),
  KEY `CentreIn_CentreId_fk` (`CenterId`),
  KEY `RateIn_RateId_fk` (`RateId`),
  CONSTRAINT `CentreIn_CentreId_fk` FOREIGN KEY (`CenterId`) REFERENCES `centermaster` (`CenterId`),
  CONSTRAINT `RateIn_RateId_fk` FOREIGN KEY (`RateId`) REFERENCES `rate` (`RateId`),
  CONSTRAINT `companyIn_CompId_fk` FOREIGN KEY (`CompanyId`) REFERENCES `company` (`CompanyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `invoiceitems` (
  `InvoiceItemId` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceNum` varchar(70) DEFAULT NULL,
  `RateId` int(11) DEFAULT NULL,
  `CNNumber` varchar(15) NOT NULL,
  `Amount` double NOT NULL,
  `Weight` double NOT NULL,
  PRIMARY KEY (`InvoiceItemId`),
  KEY `InvoiceInvt_InvoiceNumber_fk` (`InvoiceNum`),
  KEY `CNNoteInvt_CNNumber_fk` (`CNNumber`),
  KEY `RateInvt__RateId_fk` (`RateId`),
  CONSTRAINT `CNNoteInvt_CNNumber_fk` FOREIGN KEY (`CNNumber`) REFERENCES `cnnote` (`CNNumber`),
  CONSTRAINT `InvoiceInvt_InvoiceNumber_fk` FOREIGN KEY (`InvoiceNum`) REFERENCES `invoice` (`InvoiceNum`),
  CONSTRAINT `RateInvt__RateId_fk` FOREIGN KEY (`RateId`) REFERENCES `rate` (`RateId`)
) ENGINE=InnoDB AUTO_INCREMENT=292 DEFAULT CHARSET=utf8;


CREATE TABLE `manifest` (
  `ManifestId` varchar(100) NOT NULL,
  `CenterID` int(11) DEFAULT NULL,
  `ManifestDate` date NOT NULL,
  `SourceCityID` int(11) DEFAULT NULL,
  `TargetCityID` int(11) DEFAULT NULL,
  `CarrierTypeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ManifestId`),
  KEY `cmaster_CenterId_fk` (`CenterID`),
  KEY `city_SourceCityID_fk` (`SourceCityID`),
  KEY `city_TargetCityID_fk` (`TargetCityID`),
  CONSTRAINT `city_SourceCityID_fk` FOREIGN KEY (`SourceCityID`) REFERENCES `city` (`CityId`),
  CONSTRAINT `city_TargetCityID_fk` FOREIGN KEY (`TargetCityID`) REFERENCES `city` (`CityId`),
  CONSTRAINT `cmaster_CenterId_fk` FOREIGN KEY (`CenterID`) REFERENCES `centermaster` (`CenterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `manifestitems` (
  `MItemID` int(11) NOT NULL AUTO_INCREMENT,
  `ManifestId` varchar(100) DEFAULT NULL,
  `CNoteNo` varchar(15) DEFAULT NULL,
  `LoadedQuantity` int(11) DEFAULT NULL,
  `ItemDate` date DEFAULT NULL,
  PRIMARY KEY (`MItemID`),
  KEY `Manifest_ManifestId_fk` (`ManifestId`),
  KEY `CNNote_CNNumber_fk` (`CNoteNo`),
  CONSTRAINT `CNNote_CNNumber_fk` FOREIGN KEY (`CNoteNo`) REFERENCES `cnnote` (`CNNumber`),
  CONSTRAINT `Manifest_ManifestId_fk` FOREIGN KEY (`ManifestId`) REFERENCES `manifest` (`ManifestId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


CREATE TABLE `packagingmode` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Typee` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Typee` (`Typee`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


CREATE TABLE `payment` (
  `PaymentId` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceNum` varchar(70) DEFAULT NULL,
  `PaymentMode` int(11) NOT NULL,
  `CenterId` int(11) DEFAULT NULL,
  `Statuss` varchar(50) DEFAULT NULL,
  `Amount` double NOT NULL,
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` date DEFAULT NULL,
  PRIMARY KEY (`PaymentId`),
  KEY `CentreP_CentreId_fk` (`CenterId`),
  KEY `InvoiceP_InvoiceNum_fk` (`InvoiceNum`),
  KEY `packagingmodeP_Mode_fk` (`PaymentMode`),
  CONSTRAINT `CentreP_CentreId_fk` FOREIGN KEY (`CenterId`) REFERENCES `centermaster` (`CenterId`),
  CONSTRAINT `InvoiceP_InvoiceNum_fk` FOREIGN KEY (`InvoiceNum`) REFERENCES `invoice` (`InvoiceNum`),
  CONSTRAINT `packagingmodeP_Mode_fk` FOREIGN KEY (`PaymentMode`) REFERENCES `packagingmode` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;


CREATE TABLE `paymentinvoice` (
  `PaymentInvoiceId` int(11) NOT NULL AUTO_INCREMENT,
  `PaymentId` int(11) DEFAULT NULL,
  `InvoiceNum` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`PaymentInvoiceId`),
  KEY `PaymentPI_PaymentId_fk` (`PaymentId`),
  KEY `InvoicePI_InvoiceNum_fk` (`InvoiceNum`),
  CONSTRAINT `InvoicePI_InvoiceNum_fk` FOREIGN KEY (`InvoiceNum`) REFERENCES `invoice` (`InvoiceNum`),
  CONSTRAINT `PaymentPI_PaymentId_fk` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`PaymentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `rate` (
  `RateId` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyId` int(11) DEFAULT NULL,
  `CenterId` int(11) DEFAULT NULL,
  `Rate` double NOT NULL,
  `KG` int(11) NOT NULL,
  `Statuss` tinyint(1) DEFAULT NULL,
  `ModeID` int(11) NOT NULL,
  PRIMARY KEY (`RateId`),
  KEY `companyy_CompId_fk` (`CompanyId`),
  KEY `CentreR_CentreId_fk` (`CenterId`),
  KEY `transportmodeR_ModelId_fk` (`ModeID`),
  CONSTRAINT `CentreR_CentreId_fk` FOREIGN KEY (`CenterId`) REFERENCES `centermaster` (`CenterId`),
  CONSTRAINT `companyy_CompId_fk` FOREIGN KEY (`CompanyId`) REFERENCES `company` (`CompanyId`),
  CONSTRAINT `transportmodeR_ModelId_fk` FOREIGN KEY (`ModeID`) REFERENCES `transportmode` (`ModelId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;


CREATE TABLE `smartmeeting` (
  `Nam` varchar(50) DEFAULT NULL,
  `EmailAddress` varchar(60) DEFAULT NULL,
  `Passwords` varchar(100) DEFAULT NULL,
  `BTAdress` varchar(50) DEFAULT NULL,
  `NFCId` varchar(100) DEFAULT NULL,
  `DeviceType` varchar(50) DEFAULT NULL,
  `DevicePrefrance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tax` (
  `TaxId` int(11) NOT NULL AUTO_INCREMENT,
  `TaxName` varchar(200) DEFAULT NULL,
  `Percentage` double NOT NULL,
  `Statuss` tinyint(1) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `CenterId` int(11) DEFAULT NULL,
  PRIMARY KEY (`TaxId`),
  KEY `CentreT_CentreId_fk` (`CenterId`),
  CONSTRAINT `CentreT_CentreId_fk` FOREIGN KEY (`CenterId`) REFERENCES `centermaster` (`CenterId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `taxinvoice` (
  `TaxInvoiceId` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceNum` varchar(50) DEFAULT NULL,
  `TaxId` int(11) DEFAULT NULL,
  `TaxAmount` double DEFAULT NULL,
  PRIMARY KEY (`TaxInvoiceId`),
  KEY `InvoiceTI_InvoiceNumber_fk` (`InvoiceNum`),
  KEY `Tax_TaxId_fk` (`TaxId`),
  CONSTRAINT `InvoiceTI_InvoiceNumber_fk` FOREIGN KEY (`InvoiceNum`) REFERENCES `invoice` (`InvoiceNum`),
  CONSTRAINT `Tax_TaxId_fk` FOREIGN KEY (`TaxId`) REFERENCES `tax` (`TaxId`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;


CREATE TABLE `transportmode` (
  `ModelId` int(11) NOT NULL AUTO_INCREMENT,
  `Modee` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ModelId`),
  UNIQUE KEY `Modee` (`Modee`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


