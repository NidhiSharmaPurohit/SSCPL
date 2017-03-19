/**
 * http://usejsdoc.org/
 */
exports.listCNNOtes = function(pool,request, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from cnnote",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
	
};

exports.listCNNOtesDetails = function(pool,request, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("call consignmentmanagement.GetCNNoteDetaiils();",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
	
};


exports.createCNNOtes = function(pool, requestBody, response)
{
	var cnote = {
			CenterID: requestBody.CenterID, CNNumber: requestBody.CNNumber, BookingDate: requestBody.BookingDate , PackageNo:requestBody.PackageNo, 
			ModeID: requestBody.ModeID, ActualWeight: requestBody.ActualWeight, ConsignmentWeight: requestBody.ConsignmentWeight,
			MaterialDesc: requestBody.MaterialDesc, ShipperCompId: requestBody.ShipperCompId, ConsigneeCompId: requestBody.ConsigneeCompId,
			OriginCityID: requestBody.OriginCityID, DestCityID: requestBody.DestCityID, ToPayMode: requestBody.ToPayMode, 
			ServiceTax: requestBody.ServiceTax, TOTAL: requestBody.TOTAL, Remarks: requestBody.Remarks, Status: requestBody.Status,
			HandedBy: requestBody.HandedBy, ReceivedBy: requestBody.ReceivedBy, FlightId: requestBody.FlightId
			};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database" , "CNNNumber" : requestBody.CNNumber });
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into cnnote SET ?", cnote, function(err,resp){
            
            if(!err) {
            	connection.release();
            	response.json({"code" : 200, "status" : "CNNOteData Created Successfully" , "CNNNumber" : requestBody.CNNumber });
            }  
            else
            	{
            	console.log(err);
            	connection.query("select * from cnnote where CNNumber = '" + requestBody.CNNumber + "'" ,function(error,rows){
                    connection.release();
                    if(!error) {
                    	if(rows !==null && rows.length >= 1)
                    		{
                    		 response.json({"code" : 102, "status" : "Duplicate CNNOTe Number. CNNOte Already Exits with Error " + err , "CNNNumber" : requestBody.CNNumber});
                    		}
                    	else
                    		{
                    		response.json({"code" : 101, "status" : "Error in creating CNNOteData with Error " + err , "CNNNumber" : requestBody.CNNumber});
                    		}
                    }
                    else
                    	{
                    	  response.json({"code" : 101, "status" : "Error in creating CNNOteData with Error " + err, "CNNNumber" : requestBody.CNNumber});
                    	}
                });
            	
            	}
            	
        }); 
  });
};

exports.updateCNNOtes = function(pool, requestBody, response)
{
	var cnote = {
			CenterID: requestBody.CenterID, CNNumber: requestBody.CNNumber, BookingDate: requestBody.BookingDate , PackageNo:requestBody.PackageNo, 
			ModeID: requestBody.ModeID, ActualWeight: requestBody.ActualWeight, ConsignmentWeight: requestBody.ConsignmentWeight,
			MaterialDesc: requestBody.MaterialDesc, ShipperCompId: requestBody.ShipperCompId, ConsigneeCompId: requestBody.ConsigneeCompId,
			OriginCityID: requestBody.OriginCityID, DestCityID: requestBody.DestCityID, ToPayMode: requestBody.ToPayMode, 
			ServiceTax: requestBody.ServiceTax, TOTAL: requestBody.TOTAL, Remarks: requestBody.Remarks, Status: requestBody.Status,
			HandedBy: requestBody.HandedBy, ReceivedBy: requestBody.ReceivedBy, FlightId: requestBody.FlightId
			};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database" , "CNNNumber" : requestBody.CNNumber});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("Update cnnote SET ? where CNNumber = ?", [cnote,requestBody.CNNumber], function(err,resp){
            connection.release();
            if(!err) {
            	response.json({"code" : 200, "status" : "CNNOteData Updated Successfully" , "CNNNumber" : requestBody.CNNumber});
            }  
            else
            	{
            	   console.log(err);
            	   response.json({"code" : 101, "status" : "Error in Updating CNNOteData with Error " + err , "CNNNumber" : requestBody.CNNumber});
            	 }
            	
        }); 
  });
};

exports.deleteCNNOtes = function(pool, _cnnumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("delete from cnnote where CNNumber = '" + _cnnumber + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.deleteCNNOtesOfOlderDates = function(pool, _date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("delete from cnnote where BookingDate <= '" + _date + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });

};

exports.listTodaysCNNotes = function(pool, _date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        console.log("select * from cnnote where BookingDate = '" + _date + "'");
        connection.query("select * from cnnote where BookingDate = '" + _date + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
};

exports.listTodaysCNNotesDetails = function(pool, _date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetCNNoteDetailsOfDate(?)";
        
        
        connection.query(spquery, [_date]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.listCNNOtesForComany = function(pool, _shippercompanyId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where ShipperCompId = " + _shippercompanyId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });

};

exports.listCNNOtesDetailForComany = function(pool, _shippercompanyId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNOteforShipperCompany(?)";
        
        
        connection.query(spquery, [_shippercompanyId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });

};

exports.listCNNOtesDetailForManifestId = function(pool, _ManifestId, request, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailForManifest(?)";
        
        
        connection.query(spquery, [_ManifestId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	if(rows[0] !== null && rows[0].length > 0)
            		{
            		   var cncount = 0;
             	        for(cncount=0; cncount <rows[0].length ;cncount++)
             		     {
             		        if(rows[0][cncount].SignImageURL !==undefined && rows[0][cncount].CNNumber !==undefined)
             		    	 {
             		    	   
             		    	  rows[0][cncount].SignImageURL = request.protocol + '://' + request.get('host') + "/uploadCNNOtesSignImage/" + rows[0][cncount].CNNumber +".jpg";
             		    	  rows[0][cncount].SignImageDeliveredURL = request.protocol + '://' + request.get('host') + "/uploadCNNOtesSignImage/" + "D" +  rows[0][cncount].CNNumber +".jpg";
             		    	  
             		    	 }
             		      }
            		 }
            	response.json(rows[0]);
            }          
        });
        
  });

};

exports.listCNNOtesForDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + " AND  BookingDate <= '" + requestBody.EndDate + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.getCNNOtesForDateRange = function(pool, StartDate, EndDate , response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + StartDate + "'" + " AND  BookingDate <= '" + EndDate + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.listCNNOtesForCompanyInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + 
        		" AND  BookingDate <= '" + requestBody.EndDate + "'" +
        		" AND  ShipperCompId = " + requestBody.ShipperCompId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.getCNNotesbyCnoteNumber = function(pool, _cnnumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        console.log("select * from cnnote where CNNumber = '" + _cnnumber + "'");
        connection.query("select * from cnnote where CNNumber = '" + _cnnumber + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
};

exports.getCNNotesFullDetailsbyCnoteNumber = function(pool, _cnnumber, request, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetFullCNNDetail(?)";
        
       
        connection.query(spquery, [_cnnumber]   ,function(err,rows){
            connection.release();
            if(!err) {
            	if(rows[0] !== null && rows[0].length > 0)
        		{
        		   var cncount = 0;
         	        for(cncount=0; cncount <rows[0].length ;cncount++)
         		     {
         		        if(rows[0][cncount].SignImageURL !==undefined && rows[0][cncount].CNNumber !==undefined)
         		    	 {
         		    	   
         		    	  rows[0][cncount].SignImageURL = request.protocol + '://' + request.get('host') + "/uploadCNNOtesSignImage/" + rows[0][cncount].CNNumber +".jpg";
         		    	  rows[0][cncount].SignImageDeliveredURL = request.protocol + '://' + request.get('host') + "/uploadCNNOtesSignImage/" + "D" +  rows[0][cncount].CNNumber +".jpg";
         		    	  
         		    	 }
         		      }
        		 }
            	response.json(rows[0][0]);
            }          
        });
        
  });
};

exports.getCNNotesFullDetailsAltbyCnoteNumber = function(pool, _cnnumber, request, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetFullCNNDetail(?)";
        
       
        connection.query(spquery, [_cnnumber]   ,function(err,rows){
            connection.release();
            if(!err) {
            	if(rows[0] !== null && rows[0].length > 0)
        		{
        		   var cncount = 0;
         	        for(cncount=0; cncount <rows[0].length ;cncount++)
         		     {
         		        if(rows[0][cncount].SignImageURL !==undefined && rows[0][cncount].CNNumber !==undefined)
         		    	 {
         		    	   
         		    	  rows[0][cncount].SignImageURL = request.protocol + '://' + request.get('host') + "/uploadCNNOtesSignImage/" + rows[0][cncount].CNNumber +".jpg";
         		    	  rows[0][cncount].SignImageDeliveredURL = request.protocol + '://' + request.get('host') + "/uploadCNNOtesSignImage/" + "D" +  rows[0][cncount].CNNumber +".jpg";
         		    	  
         		    	 }
         		      }
        		 }
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.listCNNOtesForDestCityInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + 
        		" AND  BookingDate <= '" + requestBody.EndDate + "'" +
        		" AND  DestCityID = " + requestBody.DestCityID ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.listCNNOtesDetailsForDestCityInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailByDestCityAndInDateRange(?,?,?)";
        connection.query(spquery,[requestBody.DestCityID, requestBody.StartDate, requestBody.EndDate] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	console.log(err);
            	}
        });
        
  });
	
};

exports.GetCNNOtesDetailsForDestCityInDateRange = function(pool, DestCityID, StartDate, EndDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailByDestCityAndInDateRange(?,?,?)";
        connection.query(spquery,[DestCityID, StartDate, EndDate] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	console.log(err);
            	}
        });
        
  });
	
};

exports.listCNNOtesForModeInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + 
        		" AND  BookingDate <= '" + requestBody.EndDate + "'" +
        		" AND  ModeID = " + requestBody.ModeID ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.listCNNOtesForPaymentModeInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + 
        		" AND  BookingDate <= '" + requestBody.EndDate + "'" +
        		" AND  ToPayMode = " + requestBody.ToPayMode ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.listCNNotesDetailsForMultipleInDateRange = function(pool, requestBody, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetCNNoteDetailsWithMultipleParameter(?,?,?,?,?)";
        connection.query(spquery,[requestBody.DestCityID, requestBody.StartDate, requestBody.EndDate, requestBody.ToPayMode, requestBody.ModeId ] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.getCNNotesDetailsForMultipleInDateRange = function(pool, DestCityID, StartDate, EndDate, ToPayMode, ModeId, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetCNNoteDetailsWithMultipleParameter(?,?,?,?,?)";
        connection.query(spquery,[DestCityID, StartDate, EndDate, ToPayMode, ModeId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.listCity = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from city",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getbyCityId = function(pool, _cityId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from city where CityId = " + _cityId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.deletebyCityId = function(pool, _cityId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete from city where CityId = " + _cityId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createCity = function(pool, requestbody, response)
{

	var city = {CityName: requestbody.CityName, CityCode: requestbody.CityCode };
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into city SET ?", city, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('City Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  getting city " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateCity = function(pool, requestbody, response)
{

	var city = {CityId: requestbody.CityId, CityName: requestbody.CityName, CityCode: requestbody.CityCode };
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("Update city SET ? where CityId = ?", [city,requestbody.CityId] , function(err,resp){
            connection.release();
            if(!err) {
            	response.end('City Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  getting city " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listpackagingmode = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from packagingmode",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getbypackagingmodeId = function(pool, _Id, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from packagingmode where Id = " + _Id ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.deletebypackagingmodeId = function(pool, _Id, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete from packagingmode where Id = " + _Id ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createpackagingmode = function(pool, requestbody, response)
{

	var packagingmode = {Typee: requestbody.Typee };
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into packagingmode SET ?", packagingmode, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('packagingmode Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  getting packagingmode " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updatepackagingmode = function(pool, requestbody, response)
{

	var packagingmode = {Id: requestbody.Id, Typee: requestbody.Typee };
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("update packagingmode SET ? where Id = ?", [packagingmode, requestbody.Id], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('packagingmode Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  updating packagingmode " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listtransportmode = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from transportmode",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getbytransportmodeId = function(pool, _ModelId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from transportmode where ModelId = " + _ModelId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createtransportmode = function(pool, requestbody, response)
{

	var transportmode = {Modee: requestbody.Modee };
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into transportmode SET ?", transportmode, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('transportmode Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating transportmode " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updatetransportmode = function(pool, requestbody, response)
{

	var transportmode = {ModelId: requestbody.ModelId, Modee: requestbody.Modee };
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("update transportmode SET ? where ModelId = ? ", [transportmode,requestbody.ModelId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('transportmode Record updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  updating transportmode " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.deletebytransportmodeId = function(pool, _ModelId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from transportmode where ModelId = " + _ModelId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};



exports.listcentermaster = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from CenterMaster",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getCenterMasterById = function(pool, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from CenterMaster where CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createCenterMaster = function(pool, requestbody, response)
{

	var centermaster = {
			Status: requestbody.Status , StartDate: requestbody.StartDate ,EndDate: requestbody.EndDate,
			Name: requestbody.Name, Address: requestbody.Address, City: requestbody.City, State: requestbody.State, Sector: requestbody.Sector            	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into CenterMaster SET ?", centermaster, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('CenterMaster Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating CenterMaster " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateCenterMaster = function(pool, requestbody, response)
{

	var centermaster = {
			Status: requestbody.Status , StartDate: requestbody.StartDate ,EndDate: requestbody.EndDate,
			Name: requestbody.Name, Address: requestbody.Address, City: requestbody.City, State: requestbody.State, Sector: requestbody.Sector            	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update CenterMaster SET ? where CenterId = ?", [centermaster,requestbody.CenterId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('CenterMaster Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating CenterMaster " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listCompany = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from company",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getCompanyById = function(pool, _CompId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from company where CompanyId = " + _CompId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createCompany = function(pool, requestbody, response)
{

	var company = {
			CompanyName: requestbody.CompanyName , CompanyAddress: requestbody.CompanyAddress ,CompanyCity: requestbody.CompanyCity,
			CompanyState: requestbody.CompanyState, CompanyCCode: requestbody.CompanyCCode, CompanyContactPerson: requestbody.CompanyContactPerson, 
			CompanyEmailId: requestbody.CompanyEmailId, CompanyPrimaryContactNumber: requestbody.CompanyPrimaryContactNumber, CompanySecondaryContactNumber: requestbody.CompanySecondaryContactNumber           	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into company SET ?", company, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('company Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating company " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateCompany = function(pool, requestbody, response)
{

	var company = {
			CompanyName: requestbody.CompanyName , CompanyAddress: requestbody.CompanyAddress ,CompanyCity: requestbody.CompanyCity,
			CompanyState: requestbody.CompanyState, CompanyCCode: requestbody.CompanyCCode, CompanyContactPerson: requestbody.CompanyContactPerson, 
			CompanyEmailId: requestbody.CompanyEmailId, CompanyPrimaryContactNumber: requestbody.CompanyPrimaryContactNumber, CompanySecondaryContactNumber: requestbody.CompanySecondaryContactNumber           	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update company SET ? where CompanyId = ?", [company,requestbody.CompanyId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('company Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating company " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};


exports.listSmartMeeting = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from SmartMeeting",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getSmartMeetingByEmailId = function(pool, _EmailId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from SmartMeeting where EmailAddress = '" + _EmailId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createSmartMeeting = function(pool, requestbody, response)
{

	var smartmeeting = {
			Nam: requestbody.Nam , EmailAddress: requestbody.EmailAddress ,Passwords: requestbody.Passwords,
			BTAdress: requestbody.BTAdress, NFCId: requestbody.NFCId, DeviceType: requestbody.DeviceType, 
			DevicePrefrance: requestbody.DevicePrefrance        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into SmartMeeting SET ?", smartmeeting, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('smartmeeting Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating smartmeeting " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateSmartMeeting = function(pool, requestbody, response)
{

	var smartmeeting = {
			Nam: requestbody.Nam , EmailAddress: requestbody.EmailAddress ,Passwords: requestbody.Passwords,
			BTAdress: requestbody.BTAdress, NFCId: requestbody.NFCId, DeviceType: requestbody.DeviceType, 
			DevicePrefrance: requestbody.DevicePrefrance        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update SmartMeeting SET ? where Nam = ?", [smartmeeting,requestbody.Nam], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('ShipperCompany Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating smartmeeting " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};


exports.listCNNOtesDetailsForDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForDateRange(?,?)";
        connection.query(spquery,[requestBody.StartDate, requestBody.EndDate] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.getCNNOtesDetailsForDateRange = function(pool, StartDate, EndDate , response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForDateRange(?,?)";
        connection.query(spquery,[StartDate, EndDate] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};


exports.getCNNOtesForCompanyInDateRange = function(pool, StartDate, EndDate, ShipperCompId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + StartDate + "'" + 
        		" AND  BookingDate <= '" + EndDate + "'" +
        		" AND  ShipperCompId = " + ShipperCompId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.listCNNOtesDetailForCompanyInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForComapanyInDateRange(?,?,?)";
        connection.query(spquery,[requestBody.StartDate, requestBody.EndDate, requestBody.ShipperCompId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.getCNNOtesDetailForCompanyInDateRange = function(pool, StartDate, EndDate, ShipperCompId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForComapanyInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, ShipperCompId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.getCNNOtesDetailForCompanyInvoiceInDateRange = function(pool, StartDate, EndDate, ShipperCompId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForInvoiceComapanyInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, ShipperCompId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.getCNNOtesForDestCityInDateRange = function(pool, StartDate, EndDate, DestCityId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + StartDate + "'" + 
        		" AND  BookingDate <= '" + EndDate + "'" +
        		" AND  DestCityID = " + DestCityId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.getCNNOtesForModeInDateRange = function(pool, StartDate, EndDate, ModeID, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + StartDate + "'" + 
        		" AND  BookingDate <= '" + EndDate + "'" +
        		" AND  ModeID = " + ModeID ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.getCNNOtesDetailForModeInDateRange = function(pool, StartDate, EndDate, ModeID, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForModeInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, ModeID] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.listCNNOtesDetailForModeInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForModeInDateRange(?,?,?)";
        connection.query(spquery,[requestBody.StartDate, requestBody.EndDate, requestBody.ModeId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.getCNNOtesForPaymentModeInDateRange = function(pool, StartDate, EndDate, ToPayMode, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + StartDate + "'" + 
        		" AND  BookingDate <= '" + EndDate + "'" +
        		" AND  ToPayMode = " + ToPayMode ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.getCNNOtesDetailForPaymentModeInDateRange = function(pool, StartDate, EndDate, ToPayMode, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForPaymentModeInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, ToPayMode] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.listCNNOtesDetailForPaymentModeInDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForPaymentModeInDateRange(?,?,?)";
        connection.query(spquery,[requestBody.StartDate, requestBody.EndDate, requestBody.ToPayMode] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
	
};

exports.listCNNotesForMultipleInDateRange = function(pool, requestBody, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + 
        		" AND  BookingDate <= '" + requestBody.EndDate + "'" +
        		" AND  ToPayMode = " + requestBody.ToPayMode + " AND  ModeID = " + requestBody.ModeID +
        		" AND  DestCityID = " + requestBody.DestCityID ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.getlistCNNotesForMultipleInDateRange = function(pool, DestCityID, StartDate, EndDate, ToPayMode, ModeId, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + StartDate + "'" + 
        		" AND  BookingDate <= '" + EndDate + "'" +
        		" AND  ToPayMode = " + ToPayMode + " AND  ModeID = " + ModeId +
        		" AND  DestCityID = " + DestCityID ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });
        
  });
	
};

exports.listCNNotesForMultipleCitiesInDateRange = function(pool, requestBody, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        } 
        
        var Cities = "(";
        var cy =0;
        for (cy =0; cy < requestBody.Cities.length ; cy++)
        	{
        	  if(cy ===0)
        		  {
        		    Cities = Cities  + requestBody.Cities[cy] ;
        		  }
        	  else
        		  {
        	        Cities =   Cities  + "," +  requestBody.Cities[cy] ;
        		  }
        	}
        
        Cities = Cities + ")";
        
        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from cnnote where BookingDate >= '" + requestBody.StartDate + "'" + 
        		" AND  BookingDate <= '" + requestBody.EndDate + "'" +
        		" AND  DestCityID IN " + Cities   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            } 
            else
            	{
            	 console.log(err);
            	}
        });
        
  });
	
};

exports.getCNNoteForMultipleCitiesInDateRange = function(pool, StartDate, EndDate, Cities, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        } 
        
        
        console.log('connected as id ' + connection.threadId);
        console.log(Cities);
        var spquery = "call consignmentmanagement.GetCNNoteWithMultipleCityInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, Cities] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	 console.log(err);
            	}
        });
        
  });
	
};

exports.listCNNotesDetailsForMultipleCitiesInDateRange = function(pool, requestBody, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        } 
        
        
        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsWithMultipleCityInDateRange(?,?,?)";
        connection.query(spquery,[requestBody.StartDate, requestBody.EndDate, requestBody.Cities] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	 console.log(err);
            	}
        });
        
  });
	
};

exports.getCNNotesDetailsForMultipleCitiesInDateRange = function(pool, StartDate, EndDate, Cities, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        } 
        
        
        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsWithMultipleCityInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, Cities] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	 console.log(err);
            	}
        });
        
  });
	
};


exports.getCNNotesDetailsForManifestWithMultipleCityInDateRange = function(pool, StartDate, EndDate, Cities, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        } 
        
        
        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForManifestWithMultipleCityInDateRange(?,?,?)";
        connection.query(spquery,[StartDate, EndDate, Cities] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	 console.log(err);
            	}
        });
        
  });
	
};

exports.listCNNotesDetailsForManifestWithMultipleCityInDateRange = function(pool, requestBody, response)
{
   
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        } 
        
        
        console.log('connected as id ' + connection.threadId);
        
        var spquery = "call consignmentmanagement.GetCNNoteDetailsForManifestWithMultipleCityInDateRange(?,?,?)";
        connection.query(spquery,[requestBody.StartDate, requestBody.EndDate, requestBody.Cities] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            } 
            else
            	{
            	 console.log(err);
            	}
        });
        
  });
	
};


exports.getPaymentDetailsWithTaxForCnote = function(pool, _cnnumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetPaymentInfoWithTaxForCnnumber(?)";
        
       
        connection.query(spquery, [_cnnumber]   ,function(err,rows){
            
            if(!err) {
            	if(rows[0] !== null && rows[0].length > 0)
        		{
            		
        		       connection.query("select * from rate where CompanyId = " + rows[0][0].ShipperCompId +  " AND  ModeID = " + rows[0][0].ModeID + 
					   " AND SourceCityID = " + rows[0][0].OriginCityID + " AND DestCityID = " + rows[0][0].DestCityID + " AND CenterID = " + rows[0][0].CenterID,
					   
					   function(error,result){
                       
                       if(!error){
            	                      rows[0][0].RatePerKG = result[0].Rate;
            	                      rows[0][0].RateId = result[0].RateId;
									  rows[0][0].Amount = rows[0][0].ConsignmentWeight * result[0].Rate ;
									  
									  connection.query("select * from tax ", function(exc,taxresult){
										  if(!exc)
										  {
											var taxamount = 0;
											var taxdesc = " ";
											var cncount = 0;
             	                               for(cncount=0; cncount <taxresult.length ;cncount++)
             		                            {
             	                            	  
													taxamount = taxamount + taxresult[cncount].Percentage  * (rows[0][0].Amount/100) ;
													
													taxdesc = taxdesc + taxresult[cncount].TaxName  + "  " + taxresult[cncount].Percentage.toString() + " #  ";
												}
												rows[0][0].TaxableAmount = taxamount;
												
												rows[0][0].TaxDetails = taxdesc;
												 
												rows[0][0].TotalAmount = rows[0][0].Amount + rows[0][0].TaxableAmount;
												rows[0][0].Discount = 0;
												rows[0][0].TotalAmountDue = rows[0][0].TotalAmount;
												
												var invoicenum = "Invoice_" + _cnnumber;
												connection.query("select * from payment where InvoiceNum ='" + invoicenum + "'",function(exception, payresponse){
													if(!exception)
														{
														    if(payresponse !==null && payresponse.length >= 1)
														    	{
														    	   var totalpaid = 0;
														    	   var counter = 0;
														    	   for(counter=0; counter <payresponse.length; counter++)
														    		   {
														    		     totalpaid = totalpaid + payresponse[counter].Amount;
														    		   }
														    	   rows[0][0].TotalAmountDue = rows[0][0].TotalAmount - totalpaid;
														    	}
														}
													connection.release();
										            response.json(rows[0][0]);
												});
												 
										  }
										  else
										  {
											connection.release();  
										  }
									  });
                                 } 
                             else{
							        connection.release();
						         }							
                           });
        		}
				
				
            } 
            else
            {
				connection.release();
			}				
        });
        
  });
};


exports.getPaymentDetailsForCnote = function(pool, _cnnumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetPaymentInfoForCnnumber(?)";
        
       
        connection.query(spquery, [_cnnumber]   ,function(err,rows){
            
            if(!err) {
            	if(rows[0] !== null && rows[0].length > 0)
        		{
        		       connection.query("select * from rate where CompanyId = " + rows[0][0].ShipperCompId +  " AND  ModeID = " + rows[0][0].ModeID + 
					   " AND SourceCityID = " + rows[0][0].OriginCityID + " AND DestCityID = " + rows[0][0].DestCityID + " AND CenterID = " + rows[0][0].CenterID,
					   
					   function(error,result){
                       
                       if(!error){
            	                      rows[0][0].RatePerKG = result[0].Rate;
            	                      rows[0][0].RateId = result[0].RateId;
									  rows[0][0].Amount = rows[0][0].ConsignmentWeight * result[0].Rate ;
									  rows[0][0].TotalAmount = rows[0][0].Amount ;
									  rows[0][0].Discount = 0;
									  rows[0][0].TotalAmountDue = rows[0][0].TotalAmount;
									  var invoicenum = "CashMemo_" + _cnnumber;
										connection.query("select * from payment where InvoiceNum ='" + invoicenum + "'",function(exception, payresponse){
											if(!exception)
												{
												    if(payresponse !==null && payresponse.length >= 1)
												    	{
												    	   var totalpaid = 0;
												    	   var counter = 0;
												    	   for(counter=0; counter < payresponse.length; counter++)
												    		   {
												    		     totalpaid = totalpaid + payresponse[counter].Amount;
												    		   }
												    	   rows[0][0].TotalAmountDue = rows[0][0].TotalAmount - totalpaid;
												    	}
												}
											connection.release();
								            response.json(rows[0][0]);
										});
									 
                                 } 
                             else{
							        connection.release();
						         }							
                           });
        		}
								
            } 
            else
            {
				connection.release();
			}				
        });
        
  });
};


exports.updateManifestCNNOtes = function(pool, requestBody, response)
{
	
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database" , "CNNNumber" : requestBody.CNNumber});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("Update cnnote SET ActualWeight = " + requestBody.ActualWeight  + " ,FlightId = " + requestBody.FlightId + " where CNNumber = '" + requestBody.CNNumber + "'" ,  function(err,resp){
            connection.release();
            if(!err) {
            	response.json({"code" : 200, "status" : "CNNOteData Updated Successfully" , "CNNNumber" : requestBody.CNNumber});
            }  
            else
            	{
            	   console.log(err);
            	   response.json({"code" : 101, "status" : "Error in Updating CNNOteData with Error " + err , "CNNNumber" : requestBody.CNNumber});
            	 }
            	
        }); 
  });
};







