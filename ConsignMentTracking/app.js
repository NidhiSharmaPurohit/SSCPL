
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path')
  , mysql = require('mysql')
  , cnotedataservice = require('./modules/CNNoteDataService')
  , manifestdataservice = require('./modules/ManifestDataService')
  , vehicledataservice = require('./modules/VehicleDataService')
  , paymentdataservice = require('./modules/PaymentDataService')
  , fs = require('fs');

var app = express();

// all environments
app.set('port', process.env.PORT || 80);
app.set('views', __dirname + '/views');
app.set('view engine', 'jade');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

var pool      =    mysql.createPool({
    connectionLimit : 100, //important
    host     : 'localhost',
    user     : 'root',
    password : 'ashu1234',
    database : 'ConsignmentManagement',
    debug    :  false
});



// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}



app.get("/CNNotes", function(request, response) {
	cnotedataservice.listCNNOtes(pool,request, response);
});

app.get("/CNNotesDetails", function(request, response) {
	cnotedataservice.listCNNOtesDetails(pool,request, response);
});

app.put('/CNNotes', function(request, response) {
	cnotedataservice.createCNNOtes(pool, request.body, response);
	});

app.post('/CNNotes', function(request, response) {
	cnotedataservice.updateCNNOtes(pool, request.body, response);
	});

app.get('/CNNotes/:cnnumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.cnnumber);
	cnotedataservice.getCNNotesbyCnoteNumber(pool,request.params.cnnumber, response);
	});

app.get('/CNNotesFullDetails/:cnnumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.cnnumber);
	cnotedataservice.getCNNotesFullDetailsbyCnoteNumber(pool,request.params.cnnumber, request, response);
	});

app.get('/CNNotesFullDetailsAlt/:cnnumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.cnnumber);
	cnotedataservice.getCNNotesFullDetailsAltbyCnoteNumber(pool,request.params.cnnumber, request, response);
	});

app.del('/CNNotes/:cnnumber', function(request, response) {
	
			cnotedataservice.deleteCNNOtes(pool,request.params.cnnumber, response);
		});

app.del('/CNNotes/DeleteOlderBookingDate/:BookingDate', function(request, response) {
	
	cnotedataservice.deleteCNNOtesOfOlderDates(pool,request.params.BookingDate, response);
	});

app.get('/CNNotes/BookingDate/:BookingDate', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.BookingDate);
	cnotedataservice.listTodaysCNNotes(pool,request.params.BookingDate, response);
	});

app.get('/CNNotesDetail/BookingDate/:BookingDate', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.BookingDate);
	cnotedataservice.listTodaysCNNotesDetails(pool,request.params.BookingDate, response);
	});


app.get('/CNNotes/ShipperCompany/:shippercompanyId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.shippercompanyId);
	cnotedataservice.listCNNOtesForComany(pool,request.params.shippercompanyId, response);
	});

app.get('/CNNotesDetail/ShipperCompany/:shippercompanyId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.shippercompanyId);
	cnotedataservice.listCNNOtesDetailForComany(pool,request.params.shippercompanyId, response);
	});

app.get('/GetCNNoteDetailForManifest/:ManifestId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ManifestId);
	cnotedataservice.listCNNOtesDetailForManifestId(pool,request.params.ManifestId,request, response);
	});

app.put('/CNNotes/ForDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesForDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesForDateRange/ForDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesForDateRange(pool,request.param('StartDate'), request.param('EndDate'), response);
	});

app.put('/CNNotesDetail/ForDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesDetailsForDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesDetailForDateRange/ForDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesDetailsForDateRange(pool,request.param('StartDate'), request.param('EndDate'), response);
	});

app.put('/CNNotes/ForDateRangeofCompany', function(request, response) {
	
	cnotedataservice.listCNNOtesForCompanyInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesForDateRangeofCompany/ForDateRangeofCompany', function(request, response) {
	
	cnotedataservice.getCNNOtesForCompanyInDateRange(pool,request.param('StartDate'), request.param('EndDate'),request.param('ShipperCompId'), response);
	});

app.put('/CNNotesDetail/ForDateRangeofCompany', function(request, response) {
	
	cnotedataservice.listCNNOtesDetailForCompanyInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesDetailForDateRangeofCompany/ForDateRangeofCompany', function(request, response) {
	
	cnotedataservice.getCNNOtesDetailForCompanyInDateRange(pool,request.param('StartDate'), request.param('EndDate'),request.param('ShipperCompId'), response);
	});

app.get('/GetCNNotesDetailForInvoiceInDateRangeofCompany/ForDateRangeofCompany', function(request, response) {
	
	cnotedataservice.getCNNOtesDetailForCompanyInvoiceInDateRange(pool,request.param('StartDate'), request.param('EndDate'),request.param('ShipperCompId'), response);
	});

app.put('/CNNotes/ForDestCityInDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesForDestCityInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesForDestCityInDateRange/ForDestCityInDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesForDestCityInDateRange(pool, request.param('StartDate'), request.param('EndDate') , request.param('DestCityId') , response);
	});



app.put('/CNNotesDetails/ForDestCityInDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesDetailsForDestCityInDateRange(pool,request.body, response);
	});



app.get('/GetCNNotesDetailsForDestCityInDateRange/ForDestCityInDateRange', function(request, response) {
	
	cnotedataservice.GetCNNOtesDetailsForDestCityInDateRange(pool,request.param('DestCityID'),request.param('StartDate'),request.param('EndDate'), response);
	});


app.put('/CNNotes/ForModeInDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesForModeInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesForModeInDateRange/ForModeInDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesForModeInDateRange(pool,request.param('StartDate'),request.param('EndDate'), request.param('ModeId'), response);
	});

app.put('/CNNotesDetail/ForModeInDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesDetailForModeInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesDetailForModeInDateRange/ForModeInDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesDetailForModeInDateRange(pool,request.param('StartDate'),request.param('EndDate'), request.param('ModeId'), response);
	});

app.get('/GetCNNotesForPaymentModeInDateRange/ForPaymentModeInDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesForPaymentModeInDateRange(pool, request.param('StartDate'),request.param('EndDate'), request.param('ToPayMode') , response);
	});

app.put('/CNNotes/ForPaymentModeInDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesForPaymentModeInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesDetailForPaymentModeInDateRange/ForPaymentModeInDateRange', function(request, response) {
	
	cnotedataservice.getCNNOtesDetailForPaymentModeInDateRange(pool, request.param('StartDate'),request.param('EndDate'), request.param('ToPayMode') , response);
	});

app.put('/CNNotesDetail/ForPaymentModeInDateRange', function(request, response) {
	
	cnotedataservice.listCNNOtesDetailForPaymentModeInDateRange(pool,request.body, response);
	});



app.put('/CNNotes/listCNNotesForMultipleInDateRange', function(request, response) {
	
	cnotedataservice.listCNNotesForMultipleInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesForMultipleInDateRange/listCNNotesForMultipleInDateRange', function(request, response) {
	
	cnotedataservice.getlistCNNotesForMultipleInDateRange(pool,request.param('DestCityID'),request.param('StartDate'),request.param('EndDate'),request.param('ToPayMode'), request.param('ModeId'), response);
	});

app.put('/CNNotesDetail/listCNNotesForMultipleInDateRange', function(request, response) {
	
	cnotedataservice.listCNNotesDetailsForMultipleInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesDetailForMultipleInDateRange/GetCNNotesForMultipleInDateRange', function(request, response) {
	
	cnotedataservice.getCNNotesDetailsForMultipleInDateRange(pool,request.param('DestCityID'),request.param('StartDate'),request.param('EndDate'),request.param('ToPayMode'), request.param('ModeId'), response);
	});

app.put('/CNNotesDetail/listCNNotesForMultipleCitiesInDateRange', function(request, response) {
	
	cnotedataservice.listCNNotesDetailsForMultipleCitiesInDateRange(pool,request.body, response);
	});

app.put('/CNNotes/listCNNotesForMultipleCitiesInDateRange', function(request, response) {
	
	cnotedataservice.listCNNotesForMultipleCitiesInDateRange(pool,request.body, response);
	});

app.get('/GetCNNotesForMultipleCitiesInDateRange/listCNNotesForMultipleCitiesInDateRange', function(request, response) {
	
	cnotedataservice.getCNNoteForMultipleCitiesInDateRange(pool, request.param('StartDate'),request.param('EndDate'),request.param('Cities') , response);
	});

app.get('/CNNotesDetail/listCNNotesForMultipleCitiesInDateRange', function(request, response) {
	
	cnotedataservice.getCNNotesDetailsForMultipleCitiesInDateRange(pool, request.param('StartDate'),request.param('EndDate'),request.param('Cities') , response);
	});

app.get('/GetCNNotesDetailsForManifest', function(request, response) {
	
	cnotedataservice.getCNNotesDetailsForManifestWithMultipleCityInDateRange(pool, request.param('StartDate'),request.param('EndDate'),request.param('Cities') , response);
	});

app.put('/GetCNNotesDetailsForManifest', function(request, response) {
	
	cnotedataservice.listCNNotesDetailsForManifestWithMultipleCityInDateRange(pool, request.body , response);
	});

app.get("/City", function(request, response) {
	cnotedataservice.listCity(pool,request, response);
});

app.put('/City', function(request, response) {
	cnotedataservice.createCity(pool, request.body, response);
	});

app.get('/City/:CityId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CityId);
	cnotedataservice.getbyCityId(pool,request.params.CityId, response);
	});

app.del('/City/:CityId', function(request, response) {
	
	cnotedataservice.deletebyCityId(pool,request.params.CityId, response);
	});

app.post('/City', function(request, response) {
	cnotedataservice.updateCity(pool, request.body, response);
	});


app.get("/PackagingMode", function(request, response) {
	cnotedataservice.listpackagingmode(pool,request, response);
});

app.put('/PackagingMode', function(request, response) {
	cnotedataservice.createpackagingmode(pool, request.body, response);
	});

app.post('/PackagingMode', function(request, response) {
	cnotedataservice.updatepackagingmode(pool, request.body, response);
	});

app.get('/PackagingMode/:Id', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.Id);
	cnotedataservice.getbypackagingmodeId(pool,request.params.Id, response);
	});

app.del('/PackagingMode/:Id', function(request, response) {
	
	cnotedataservice.deletebypackagingmodeId(pool,request.params.Id, response);
	});

app.get("/TransportMode", function(request, response) {
	cnotedataservice.listtransportmode(pool,request, response);
});

app.put('/TransportMode', function(request, response) {
	cnotedataservice.createtransportmode(pool, request.body, response);
	});

app.post('/TransportMode', function(request, response) {
	cnotedataservice.updatetransportmode(pool, request.body, response);
	});

app.get('/TransportMode/:ModelId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ModelId);
	cnotedataservice.getbytransportmodeId(pool,request.params.ModelId, response);
	});

app.del('/TransportMode/:ModelId', function(request, response) {
	
	cnotedataservice.deletebytransportmodeId(pool,request.params.ModelId, response);
	});

app.get("/CenterMaster", function(request, response) {
	cnotedataservice.listcentermaster(pool,request, response);
});

app.put('/CenterMaster', function(request, response) {
	cnotedataservice.createCenterMaster(pool, request.body, response);
	});

app.post('/CenterMaster', function(request, response) {
	cnotedataservice.updateCenterMaster(pool, request.body, response);
	});

app.get('/CenterMaster/:CenterId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CenterId);
	cnotedataservice.getCenterMasterById(pool,request.params.CenterId, response);
	});

app.get("/Company", function(request, response) {
	cnotedataservice.listCompany(pool,request, response);
});

app.put('/Company', function(request, response) {
	cnotedataservice.createCompany(pool, request.body, response);
	});

app.post('/Company', function(request, response) {
	cnotedataservice.updateCompany(pool, request.body, response);
	});

app.get('/Company/:CompanyId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CompanyId);
	cnotedataservice.getCompanyById(pool,request.params.CompanyId, response);
	});



app.get("/SmartMeeting", function(request, response) {
	cnotedataservice.listSmartMeeting(pool,request, response);
});

app.put('/SmartMeeting', function(request, response) {
	cnotedataservice.createSmartMeeting(pool, request.body, response);
	});

app.post('/SmartMeeting', function(request, response) {
	cnotedataservice.updateSmartMeeting(pool, request.body, response);
	});

app.get('/SmartMeeting/:EmailId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.EmailId);
	cnotedataservice.getSmartMeetingByEmailId(pool,request.params.EmailId, response);
	});

app.put('/Manifest', function(request, response) {
	manifestdataservice.createManifest(pool, request.body, response);
	});

app.post('/Manifest', function(request, response) {
	manifestdataservice.updateManifest(pool, request.body, response);
	});

app.get("/Manifest", function(request, response) {
	manifestdataservice.listmanifest(pool,request, response);
});

app.get("/ManifestDetail", function(request, response) {
	manifestdataservice.listmanifestDetail(pool,request, response);
});


app.get('/Manifest/:ManifestId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ManifestId);
	manifestdataservice.getmanifestById(pool,request.params.ManifestId, response);
	});

app.get('/ManifestDetailById/:ManifestId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ManifestId);
	manifestdataservice.getmanifestDetailById(pool,request.params.ManifestId, response);
	});

app.get('/ManifestByDate/:Date', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.Date);
	manifestdataservice.getmanifestByDate(pool,request.params.Date, response);
	});

app.get('/ManifestDetailByDate/:Date', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.Date);
	manifestdataservice.getmanifestDetailByDate(pool,request.params.Date, response);
	});

app.get('/Manifest/ManifestByDateRange', function(request, response) {
	
	manifestdataservice.getmanifestByDateRange(pool,request.param('StartDate'),request.param('EndDate'), response);
	});

app.get('/ManifestDetailByDateRange/ManifestByDateRange', function(request, response) {
	
	manifestdataservice.getmanifestDetailByDateRange(pool,request.param('StartDate'),request.param('EndDate'), response);
	});

app.put('/ManifestDetailByDateRange', function(request, response) {
	
	manifestdataservice.listmanifestDetailByDateRange(pool, request.body , response);
	});

app.get('/ManifestDetailByTargetCityOfDate', function(request, response) {
	
	manifestdataservice.getmanifestDetailByTargetCityOfDate(pool,request.param('Date'),request.param('TargetCityId'), response);
	});

app.put('/ManifestDetailByTargetCityOfDate', function(request, response) {
	
	manifestdataservice.listmanifestDetailByTargetCityOfDate(pool, request.body , response);
	});

app.get('/ManifestDetailBySourceCityOfDate', function(request, response) {
	
	manifestdataservice.getmanifestDetailBySourceCityOfDate(pool,request.param('Date'),request.param('SourceCityId'), response);
	});

app.put('/ManifestDetailBySourceCityOfDate', function(request, response) {
	
	manifestdataservice.listmanifestDetailBySourceCityOfDate(pool, request.body , response);
	});

app.get('/GetManifestForVehicleNumInDateRange', function(request, response) {
	
	manifestdataservice.getManifestForVehicleNumInDateRange(pool, request.param('VehicleNumber'), request.param('StartDate'), request.param('EndDate'), response);
	});

app.del('/Manifest/:Id', function(request, response) {
	
	manifestdataservice.DeleteManifestById(pool,request.params.Id, response);
});

app.del('/Manifest/DeleteOlderDate/:Date', function(request, response) {

	manifestdataservice.DeleteOlderManifestOfDate(pool,request.params.Date, response);
});


app.put('/ManifestItem', function(request, response) {
	manifestdataservice.createManifestItem(pool, request.body, response);
	});

app.post('/ManifestItem', function(request, response) {
	manifestdataservice.updateManifestItem(pool, request.body, response);
	});

app.get("/ManifestItem", function(request, response) {
	manifestdataservice.listmanifestItem(pool,request, response);
});

app.get('/ManifestItem/:ManifestId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ManifestId);
	manifestdataservice.getmanifestItemsByManifestId(pool,request.params.ManifestId, response);
	});

app.get('/ManifestItemDetail/:ManifestId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ManifestId);
	manifestdataservice.getmanifestItemsDetailByManifestId(pool,request.params.ManifestId, response);
	});

app.del('/ManifestItem/:ManifestId', function(request, response) {
	
	manifestdataservice.DeleteManifestItemByManifestId(pool,request.params.ManifestId, response);
});

app.del('/ManifestItem/DeleteOlderDate/:Date', function(request, response) {

	manifestdataservice.DeleteOlderManifestItemOfDate(pool,request.params.Date, response);
});

app.put('/CarrierType', function(request, response) {
	manifestdataservice.createCarrierType(pool, request.body, response);
	});

app.post('/CarrierType', function(request, response) {
	manifestdataservice.updateCarrierType(pool, request.body, response);
	});


app.post('/CarrierTypeByCNumber', function(request, response) {
	manifestdataservice.updateCarrierTypeByCNumber(pool, request.body, response);
	});

app.get("/CarrierType", function(request, response) {
	manifestdataservice.listCarrierType(pool,request, response);
});

app.get('/CarrierTypeByCNumber/:CNumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CNumber);
	manifestdataservice.getCarrierTypeByCNumber(pool,request.params.CNumber, response);
	});

app.get('/CarrierTypeById/:Id', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.Id);
	manifestdataservice.getCarrierTypeById(pool,request.params.Id, response);
	});

app.del('/CarrierType/:Id', function(request, response) {
	
	manifestdataservice.DeleteCarrierTypeById(pool,request.params.Id, response);
});

app.del('/DeleteCarrierType/CNumber/:CNumber', function(request, response) {

	manifestdataservice.DeleteCarrierTypeByCNumber(pool,request.params.CNumber, response);
});

app.put('/DRunSheet', function(request, response) {
	manifestdataservice.createDRunSheet(pool, request.body, response);
	});

app.post('/DRunSheet', function(request, response) {
	manifestdataservice.updateDRunSheet(pool, request.body, response);
	});

app.get('/DRunSheet', function(request, response) {
	manifestdataservice.listDRunSheet(pool,request, response);
});

app.get('/DRunSheet/:DRSNumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.DRSNumber);
	manifestdataservice.getDRunSheetByDRSNumber(pool,request.params.DRSNumber, response);
	});

app.get('/DRSNumberByDateTime/:DateTime', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.DateTime);
	manifestdataservice.getDRunSheetByDate(pool,request.params.DateTime, response);
	});

app.get('/GetDRunSheetDateTime/DRunSheetByDateRange', function(request, response) {
	
	manifestdataservice.getDRunSheetByDateRange(pool,request.param('StartDateTime'),request.param('EndDateTime'), response);
	});

app.del('/DRunSheet/:DRSNumber', function(request, response) {
	
	manifestdataservice.DeleteDRunSheetByDRSNumber(pool,request.params.DRSNumber, response);
});

app.del('/DRunSheet/DeleteOlderDate/:DateTime', function(request, response) {

	manifestdataservice.DeleteOlderDRunSheetOfDate(pool,request.params.DateTime, response);
});

app.put('/DRsItems', function(request, response) {
	manifestdataservice.createDRsItems(pool, request.body, response);
	});

app.post('/DRsItems', function(request, response) {
	manifestdataservice.updateDRsItems(pool, request.body, response);
	});


app.get('/DRsItems', function(request, response) {
	manifestdataservice.listDRsItems(pool,request, response);
});

app.get('/DRsItems/:DRSItemNumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.DRSItemNumber);
	manifestdataservice.getDRsItemsByItemNum(pool,request.params.DRSItemNumber, response);
	});

app.get('/DRsItemsByDRNumber/:DRSNumber', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.DRSNumber);
	manifestdataservice.getDRsItemsByDRSNumber(pool,request.params.DRSNumber, response);
	});

app.del('/DeleteDRsItemsByDRNumber/:DRSNumber', function(request, response) {
	
	manifestdataservice.DeleteDRsItemsByDRSNumber(pool,request.params.DRSNumber, response);
});

app.del('/DeleteDRsItems/:DRSItemNumber', function(request, response) {

	manifestdataservice.DeleteDRsItemsByItemNum(pool,request.params.DRSItemNumber, response);
});

app.get('/GetGeneratedDRunSheet', function(request, response) {
	
	vehicledataservice.GenerateDRunSheetForVehicle(pool,request.param('CarrierTypeID'),request.param('Date'), response);
	});


app.put('/Rate', function(request, response) {
	paymentdataservice.createRate(pool, request.body, response);
	});

app.post('/Rate', function(request, response) {
	paymentdataservice.updateRate(pool, request.body, response);
	});


app.get('/Rate', function(request, response) {
	paymentdataservice.listRates(pool,request, response);
});

app.get('/Rate/:RateId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.RateId);
	paymentdataservice.getRateById(pool,request.params.RateId, response);
	});

app.get('/Rate/:CompanyId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CompanyId);
	paymentdataservice.getRateByCompanyId(pool,request.params.CompanyId, response);
	});

app.get('/GetRateForComapnyAndCenterId', function(request, response) {
	
	paymentdataservice.GetRateForComapnyAndCenterId(pool, request.param('CenterId'),request.param('CompanyId') , response);
	});

app.del('/Rate/:RateId', function(request, response) {
	
	paymentdataservice.DeleteRate(pool,request.params.RateId, response);
});


app.put('/Invoice', function(request, response) {
	paymentdataservice.createInvoice(pool, request.body, response);
	});

app.post('/Invoice', function(request, response) {
	paymentdataservice.updateInvoice(pool, request.body, response);
	});


app.get('/Invoice', function(request, response) {
	paymentdataservice.listInvoice(pool,request, response);
});

app.get('/Invoice/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getInvoiceByInvoiceNum(pool,request.params.InvoiceNum, response);
	});

app.get('/InvoiceDetail/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getInvoiceDetailsbyInvoiceNumber(pool,request.params.InvoiceNum, response);
	});

app.get('/Invoice/:CompanyId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CompanyId);
	paymentdataservice.getInvoiceByCompanyId(pool,request.params.CompanyId, response);
	});

app.get('/Invoice/:InvoiceDate', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceDate);
	paymentdataservice.getInvoiceByInvoiceDate(pool,request.params.InvoiceDate, response);
	});

app.get('/GetInvoiceByDateRange', function(request, response) {
	
	paymentdataservice.getInvoiceByDateRange(pool, request.param('FromDate'),request.param('ToDate') , response);
	});

app.get('/GetInvoiceByCompanyIdAndCenterId', function(request, response) {
	
	paymentdataservice.getInvoiceByCompanyIdAndCenterId(pool, request.param('CompanyId'),request.param('CenterId') , response);
	});

app.get('/GetInvoiceByInvoiceDateforCompany', function(request, response) {
	
	paymentdataservice.GetInvoiceByInvoiceDateforCompany(pool, request.param('InvoiceDate'), request.param('CompanyId'), request.param('CenterId') , response);
	});

app.get('/GetInvoiceByDateRangeForCompany', function(request, response) {
	
	paymentdataservice.GetInvoiceByDateRangeForCompany(pool, request.param('FromDate'), request.param('ToDate') , request.param('CompanyId'), request.param('CenterId') , response);
	});

app.get('/GetInvoicesinDateRangeForCompany', function(request, response) {
	
	paymentdataservice.FindInvoicesinDateRangeForCompany(pool, request.param('FromDate'), request.param('ToDate') , request.param('CompanyId'), request.param('CenterId') , response);
	});

app.del('/Invoice/:InvoiceNum', function(request, response) {
	
	paymentdataservice.DeleteInvoice(pool,request.params.InvoiceNum, response);
});


app.put('/Tax', function(request, response) {
	paymentdataservice.createTax(pool, request.body, response);
	});

app.post('/Tax', function(request, response) {
	paymentdataservice.updateTax(pool, request.body, response);
	});


app.get('/Tax', function(request, response) {
	paymentdataservice.listTax(pool,request, response);
});

app.get('/Tax/:TaxId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.TaxId);
	paymentdataservice.getTaxById(pool,request.params.TaxId, response);
	});


app.get('/GetTaxForCenterId/:CenterId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.CenterId);
	paymentdataservice.getTaxByCenterId(pool,request.params.CenterId, response);
	});


app.get('/GetTaxForTaxIdAndCenterId', function(request, response) {
	
	paymentdataservice.GetTaxForTaxIdAndCenterId(pool, request.param('CenterId'),request.param('TaxId') , response);
	});

app.del('/Tax/:TaxId', function(request, response) {
	
	paymentdataservice.DeleteTax(pool,request.params.TaxId, response);
});

app.put('/InvoiceItem', function(request, response) {
	paymentdataservice.createInvoiceItems(pool, request.body, response);
	});

app.post('/InvoiceItem', function(request, response) {
	paymentdataservice.updateInvoiceItems(pool, request.body, response);
	});


app.get('/InvoiceItem', function(request, response) {
	paymentdataservice.listInvoiceItems(pool,request, response);
});

app.get('/InvoiceItem/:InvoiceItemId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceItemId);
	paymentdataservice.getInvoiceItemsById(pool,request.params.InvoiceItemId, response);
	});

app.get('/GetInvoiceItemByInvoiceNum/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getInvoiceItemsByInvoiceNum(pool,request.params.InvoiceNum, response);
	});

app.get('/GetInvoiceItemDetailByInvoiceNum/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getInvoiceItemDetailsbyInvoiceNumber(pool,request.params.InvoiceNum, response);
	});


app.get('/GetInvoiceItemByCNNumber/:CNNumber', function(request, response) {
	
	console.log(request.url + ' : querying for ' +
			request.params.CNNumber);
			paymentdataservice.getInvoiceItemsByCNNNumber(pool,request.params.CNNumber, response);
	});

app.del('/InvoiceItem/:InvoiceItemId', function(request, response) {
	
	paymentdataservice.DeleteInvoiceItems(pool,request.params.InvoiceItemId, response);
});

app.del('/DeleteInvoiceItemByInvoiceNum/:InvoiceNum', function(request, response) {
	
	paymentdataservice.DeleteInvoiceItemsByInvoiceNum(pool,request.params.InvoiceNum, response);
});

app.put('/Payment', function(request, response) {
	paymentdataservice.createPayment(pool, request.body, response);
	});

app.post('/Payment', function(request, response) {
	paymentdataservice.updatePayment(pool, request.body, response);
	});


app.get('/Payment', function(request, response) {
	paymentdataservice.listPayment(pool,request, response);
});

app.get('/Payment/:PaymentId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.PaymentId);
	paymentdataservice.getPaymentById(pool,request.params.PaymentId, response);
	});

app.get('/Payment/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getPaymentByInvoiceNum(pool,request.params.InvoiceNum, response);
	});

app.get('/GetPaymentForInvoiceNumAndCenterId', function(request, response) {
	
	paymentdataservice.GetPaymentForInvoiceNumAndCenterId(pool, request.param('CenterId'),request.param('InvoiceNum') , response);
	});

app.del('/Payment/:PaymentId', function(request, response) {
	
	paymentdataservice.DeletePayment(pool,request.params.PaymentId, response);
});

app.put('/Paymentinvoice', function(request, response) {
	paymentdataservice.createPaymentInvoice(pool, request.body, response);
	});

app.post('/Paymentinvoice', function(request, response) {
	paymentdataservice.updatePaymentInvoice(pool, request.body, response);
	});


app.get('/Paymentinvoice', function(request, response) {
	paymentdataservice.listPaymentInvoice(pool,request, response);
});

app.get('/Paymentinvoice/:Paymentinvoice', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.Paymentinvoice);
	paymentdataservice.getPaymentInvoiceById(pool,request.params.Paymentinvoice, response);
	});

app.get('/Paymentinvoice/:PaymentId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.PaymentId);
	paymentdataservice.getPaymentInvoiceByInvoiceNum(pool,request.params.PaymentId, response);
	});

app.del('/Paymentinvoice/:Paymentinvoice', function(request, response) {
	
	paymentdataservice.DeletePaymentInvoice(pool,request.params.Paymentinvoice, response);
});

app.del('/DeletPaymentinvoiceByPaymentId/:PaymentId', function(request, response) {
	
	paymentdataservice.DeletePaymentInvoiceByPaymentId(pool,request.params.PaymentId, response);
});


app.put('/TaxInvoice', function(request, response) {
	paymentdataservice.createTaxInvoice(pool, request.body, response);
	});

app.post('/TaxInvoice', function(request, response) {
	paymentdataservice.updateTaxInvoice(pool, request.body, response);
	});


app.get('/TaxInvoice', function(request, response) {
	paymentdataservice.listTaxInvoice(pool,request, response);
});

app.get('/TaxInvoice/:TaxInvoiceId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.TaxInvoiceId);
	paymentdataservice.getTaxInvoiceById(pool,request.params.TaxInvoiceId, response);
	});

app.get('/GetTaxInvoiceByInvoiceNum/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getTaxInvoiceByInvoiceNum(pool,request.params.InvoiceNum, response);
	});

app.get('/GetTaxInvoiceDetailByInvoiceNum/:InvoiceNum', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.InvoiceNum);
	paymentdataservice.getTaxInvoiceDetailsbyInvoiceNumber(pool,request.params.InvoiceNum, response);
	});

app.del('/TaxInvoice/:TaxInvoiceId', function(request, response) {
	
	paymentdataservice.DeleteTaxInvoice(pool,request.params.TaxInvoiceId, response);
});

app.del('/DeleteTaxInvoiceIdByInvoiceNum/:InvoiceNum', function(request, response) {
	
	paymentdataservice.DeleteTaxInvoiceByInvoiceNum(pool,request.params.InvoiceNum, response);
});


app.put('/Expenses', function(request, response) {
	paymentdataservice.createExpenses(pool, request.body, response);
	});

app.post('/Expenses', function(request, response) {
	paymentdataservice.updateExpenses(pool, request.body, response);
	});


app.get('/Expenses', function(request, response) {
	paymentdataservice.listExpenses(pool,request, response);
});

app.get('/Expenses/:ExpensesId', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.ExpensesId);
	paymentdataservice.getExpensesById(pool,request.params.ExpensesId, response);
	});

app.get('/Expenses/:Date', function(request, response) {
	console.log(request.url + ' : querying for ' +
	request.params.Date);
	paymentdataservice.getExpensesByDate(pool,request.params.Date, response);
	});

app.get('/GetExpensesForDateRangeAndCenterId', function(request, response) {
	
	paymentdataservice.GetExpensesForDateRangeAndCenterId(pool, request.param('StartDate'), request.param('EndDate'), request.param('CenterId') , response);
	});

app.get('/GetExpensesForDateAndCenterId', function(request, response) {
	
	paymentdataservice.GetExpensesForDateAndCenterId(pool, request.param('CenterId'),request.param('Date') , response);
	});

app.del('/Expenses/:Date', function(request, response) {
	
	paymentdataservice.DeleteExpensesByDate(pool,request.params.Date, response);
});

app.del('/Expenses/:ExpensesId', function(request, response) {
	
	paymentdataservice.DeleteExpensesById(pool,request.params.ExpensesId, response);
});

app.get('/GetActiveInvoicesForCompany', function(request, response) {
	
	paymentdataservice.getActiveInvoicesforCompany(pool, request.param('CompanyId'),request.param('CenterId') , response);
	});

app.get('/GetActiveInvoicesForCompanyInDateRange', function(request, response) {
	
	paymentdataservice.getActiveInvoicesforCompanyInDateRange(pool, request.param('FromDate'),request.param('ToDate') ,request.param('CompanyId'), request.param('CenterId'), response);
	});


app.get('/GetPaymentDetailsWithTaxForCnote/:cnnumber', function(request, response){
	
	console.log(request.url + ' : querying for ' +
			request.params.cnnumber);
			cnotedataservice.getPaymentDetailsWithTaxForCnote(pool,request.params.cnnumber, response);
	
});


app.get('/GetPaymentDetailsForCnote/:cnnumber', function(request, response){
	
	console.log(request.url + ' : querying for ' +
			request.params.cnnumber);
			cnotedataservice.getPaymentDetailsForCnote(pool,request.params.cnnumber, response);
	
});

app.put('/PaymentForCnnote', function(request, response) {
	paymentdataservice.createPaymentForCnnumber(pool, request.body, response);
	});

app.put('/AirFlight', function(request, response) {
	vehicledataservice.createAirFlight(pool, request.body, response);
	});

app.post('/AirFlight', function(request, response) {
	vehicledataservice.updateAirFlight(pool, request.body, response);
	});

app.get('/AirFlight', function(request, response) {
	vehicledataservice.listAirFlight(pool, request, response);
	});

app.get('/GetAirFlight/:FlightId', function(request, response) {
	vehicledataservice.getAirFlightById(pool, request.params.FlightId, response);
	});

app.get('/GetAirFlightByCenterIdandFlightId', function(request, response) {
	vehicledataservice.GetAirFlightForComapnyAndCenterId(pool, request.param('FlightId'), request.param('CenterId'), response);
	});

app.del('/AirFlight/:FlightId', function(request, response) {
	vehicledataservice.DeleteAirFlight(pool, request.params.FlightId, response);
	});

app.del('/uploadCNNOtesSignImage/:file', function(req, res) {
	console.log('delete');
	var file = req.params.file;
    var filename = __dirname + "/uploadsCNNSignFiles/" + file;
    if(fs.existsSync(filename))
    	{
    	  console.log('exists');
    	  fs.unlink(filename);
    	}
    res.end('Done deleting the file');
});


//Post CNN Signed Image files
app.post('/uploadCNNOtesSignImage', function(req, res) {
	
  
  fs.readFile(req.files.picture.path, function (err, data) {
	  
    var imageName = req.files.picture.name;
    // If there's an error
    if(!imageName){
      console.log("There was an error");
      res.end();
    } else {
      var newPath = __dirname + "/uploadsCNNSignFiles/" + imageName;
      // write file to uploadsCNNSignFiles/fullsize folder
      fs.writeFile(newPath, data, function (err) {
        // let's see it
    	  if(!err)
    		  {
    		     fs.unlink(req.files.picture.path);
                 res.redirect("/uploadCNNOtesSignImage/" + imageName);
    		  }
    	  else
    		  {
    		    res.end('Error in uploading file. Please try again or work offline');
    		  }
      });
    }
  });
});
    
 // Show files
app.get('/uploadCNNOtesSignImage/:file', function (req, res){
      var file = req.params.file;
      var img = fs.readFileSync(__dirname + "/uploadsCNNSignFiles/" + file);
      res.writeHead(200, {'Content-Type': 'image/jpg' });
      res.end(img, 'binary');
});
 

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
