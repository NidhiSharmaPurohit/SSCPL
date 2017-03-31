/**
 * http://usejsdoc.org/
 */


exports.listmanifest = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from manifest",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.listmanifestDetail = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("call consignmentmanagement.GetManifestDetail();",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.getmanifestById = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from manifest where ManifestId = '" + _ManifestId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getmanifestDetailById = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
         var spquery = "call consignmentmanagement.GetManifestDetailByManifestId(?)";
         
        connection.query(spquery, [_ManifestId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};


exports.DeleteManifestById = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from manifest where ManifestId = '" + _ManifestId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteOlderManifestOfDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from manifest where ManifestDate <= '" + _Date +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getmanifestByDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from manifest where ManifestDate = '" + _Date +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getmanifestDetailByDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        var spquery = "call consignmentmanagement.GetManifestDetailOfDate(?)";
        
        connection.query(spquery, [_Date]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};


exports.getmanifestByDateRange = function(pool, _StartDate, _EndDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from manifest where ManifestDate >= '" + _StartDate +"'" + "and ManifestDate <= '" + _EndDate + "'",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getmanifestDetailByDateRange = function(pool, _StartDate, _EndDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
         var spquery = "call consignmentmanagement.GetManifestDetailByDateRange(?,?)";
        
        connection.query(spquery, [_StartDate, _EndDate]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.listmanifestDetailByDateRange = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        var spquery = "call consignmentmanagement.GetManifestDetailByDateRange(?,?)";
        
        connection.query(spquery, [requestBody.StartDate, requestBody.EndDate]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.getmanifestDetailByTargetCityOfDate = function(pool, _Date, _TargetCityId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
         var spquery = "call consignmentmanagement.GetManifestDetailByDateAndofTargetCity(?,?)";
        
        connection.query(spquery, [_Date, _TargetCityId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.listmanifestDetailByTargetCityOfDate = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        var spquery = "call consignmentmanagement.GetManifestDetailByDateAndofTargetCity(?,?)";
        
        connection.query(spquery, [requestBody.Date, requestBody.TargetCityId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.getmanifestDetailBySourceCityOfDate = function(pool, _Date, _SourceCityId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
         var spquery = "call consignmentmanagement.GetManifestDetailByDateAndofSourceCity(?,?)";
        
        connection.query(spquery, [_Date, _SourceCityId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.listmanifestDetailBySourceCityOfDate = function(pool, requestBody, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        var spquery = "call consignmentmanagement.GetManifestDetailByDateAndofSourceCity(?,?)";
        
        connection.query(spquery, [requestBody.Date, requestBody.SourceCityId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.getManifestForVehicleNumInDateRange = function(pool,_VehicleNumber, _StartDate, _EndDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
         var spquery = "call consignmentmanagement.GetManifestForVehicleNuminDateRange(?,?,?)";
        
        connection.query(spquery, [_VehicleNumber, _StartDate, _EndDate]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.createManifest = function(pool, requestbody, response)
{

	var manifest = {
			ManifestId: requestbody.ManifestId , CenterID: requestbody.CenterID ,ManifestDate: requestbody.ManifestDate,
			SourceCityID: requestbody.SourceCityID, TargetCityID: requestbody.TargetCityID, CarrierTypeID: requestbody.CarrierTypeID
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into manifest SET ?", manifest, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('manifest Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating manifest " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateManifest = function(pool, requestbody, response)
{

	var manifest = {
			ManifestId: requestbody.ManifestId , CenterID: requestbody.CenterID ,ManifestDate: requestbody.ManifestDate,
			SourceCityID: requestbody.SourceCityID, TargetCityID: requestbody.TargetCityID, CarrierTypeID: requestbody.CarrierTypeID
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update manifest SET ? where ManifestId = ?", [manifest,requestbody.ManifestId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('manifest Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating manifest " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.createManifestItem = function(pool, requestbody, response)
{
	Date.prototype.yyyymmdd = function() {
		   var yyyy = this.getFullYear();
		   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
		   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
		   return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
		  };


	var ManifestItems = {
			ManifestId: requestbody.ManifestId ,CNoteNo: requestbody.CNoteNo,
			LoadedQuantity: requestbody.LoadedQuantity, ItemDate : new Date().yyyymmdd()       	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into ManifestItems SET ?", ManifestItems, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('ManifestItems Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating ManifestItems " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateManifestItem = function(pool, requestbody, response)
{
	Date.prototype.yyyymmdd = function() {
		   var yyyy = this.getFullYear();
		   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
		   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
		   return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
		  };


	var ManifestItems = {
			ManifestId: requestbody.ManifestId ,CNoteNo: requestbody.CNoteNo,
			LoadedQuantity: parseInt(requestbody.LoadedQuantity) , ItemDate : new Date().yyyymmdd()      	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from ManifestItems where CNoteNo = '" + requestbody.CNoteNo + "'" ,function(err,rows){
            
            if(!err) {
            	
            	if(rows!==null && rows.length >0)
            		{
            		ManifestItems.LoadedQuantity = parseInt(requestbody.LoadedQuantity) + parseInt(rows[0].LoadedQuantity);
            		connection.query("Update ManifestItems SET ? where CNoteNo = ?", [ManifestItems,requestbody.CNoteNo], function(err,resp){
                        connection.release();
                        if(!err) {
                        	response.end('manifest Record Updated Successfully');
                        }  
                        else
                        	{
                        	response.json({"code" : 101, "status" : " Error in  Updating manifest " + err});
                        	console.log(err);
                        	}
                        	
                    }); 
            		}
            	else
            		{
            		
            		connection.query("insert into ManifestItems SET ?", ManifestItems, function(error,resp2){
                        connection.release();
                        if(!error) {
                        	response.end('ManifestItems Record Inserted Successfully');
                        }  
                        else
                        	{
                        	response.json({"code" : 101, "status" : " Error in  creating ManifestItems " + error});
                        	console.log(error);
                        	}
                        	
                    }); 
            		}
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating manifest " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};


exports.updateManifestItemFinal = function(pool, requestbody, response)
{
	Date.prototype.yyyymmdd = function() {
		   var yyyy = this.getFullYear();
		   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
		   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
		   return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
		  };


	var ManifestItems = {
			ManifestId: requestbody.ManifestId ,CNoteNo: requestbody.CNoteNo,
			LoadedQuantity: parseInt(requestbody.LoadedQuantity) , ItemDate : new Date().yyyymmdd()      	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from ManifestItems where CNoteNo = '" + requestbody.CNoteNo + "'" ,function(err,rows){
            
            if(!err) {
            	
            	if(rows!==null && rows.length >0)
            		{
            		
            		connection.query("Update ManifestItems SET ? where CNoteNo = ?", [ManifestItems,requestbody.CNoteNo], function(err,resp){
                        connection.release();
                        if(!err) {
                        	response.end('manifest Record Updated Successfully');
                        }  
                        else
                        	{
                        	response.json({"code" : 101, "status" : " Error in  Updating manifest " + err});
                        	console.log(err);
                        	}
                        	
                    }); 
            		}
            	else
            		{
            		
            		connection.query("insert into ManifestItems SET ?", ManifestItems, function(error,resp2){
                        connection.release();
                        if(!error) {
                        	response.end('ManifestItems Record Inserted Successfully');
                        }  
                        else
                        	{
                        	response.json({"code" : 101, "status" : " Error in  creating ManifestItems " + error});
                        	console.log(error);
                        	}
                        	
                    }); 
            		}
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating manifest " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};


exports.listmanifestItem = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from ManifestItems",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getmanifestItemsByManifestId = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from ManifestItems where ManifestId = '" + _ManifestId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.getmanifestItemsDetailByManifestId = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        var spquery = "call consignmentmanagement.GetManifestItemDetailForManifest(?)";
        connection.query(spquery,[_ManifestId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.DeleteManifestItemByManifestId = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from ManifestItems where ManifestId = '" + _ManifestId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteOlderManifestItemOfDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from ManifestItems where ItemDate <= '" + _Date +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.listCarrierType = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from carriertype",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getCarrierTypeByCNumber = function(pool, _CNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from carriertype where CNumber = '" + _CNumber +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getCarrierTypeById = function(pool, _Id, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from carriertype where Id = " + _Id  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteCarrierTypeById = function(pool, _Id, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from carriertype where Id = " + _Id ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json({"code" : 200, "status" : "CarrierType Record Deleted Successfully"});
            }          
        });       
  });
};

exports.DeleteCarrierTypeByCNumber = function(pool, _CNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from carriertype where CNumber = '" + _CNumber +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json({"code" : 200, "status" : "CarrierType Record Deleted Successfully"});
            }          
        });       
  });
};


exports.createCarrierType = function(pool, requestbody, response)
{

	var carriertype = {
			Typee: requestbody.Typee , Capacity: requestbody.Capacity , Model: requestbody.Model,
			ModelID: requestbody.ModelID, CNumber: requestbody.CNumber
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into carriertype SET ?", carriertype, function(err,resp){
            connection.release();
            if(!err) {
            	response.json({"code" : 200, "status" : "CarrierType Record Inserted Successfully"});
            	
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating carriertype " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateCarrierType = function(pool, requestbody, response)
{

	var carriertype = {
			Id: requestbody.Id , Typee: requestbody.Typee ,Capacity: requestbody.Capacity,
			Model: requestbody.Model, ModelID: requestbody.ModelID, CNumber: requestbody.CNumber
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update carriertype SET ? where Id = ?", [carriertype,requestbody.Id], function(err,resp){
            connection.release();
            if(!err) {
            	response.json({"code" : 200, "status" : "CarrierType Record Updated Successfully"});
            	
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating carriertype " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateCarrierTypeByCNumber = function(pool, requestbody, response)
{

	var carriertype = {
			Typee: requestbody.Typee ,Capacity: requestbody.Capacity,
			Model: requestbody.Model, ModelID: requestbody.ModelID, CNumber: requestbody.CNumber
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update carriertype SET ? where CNumber = ?", [carriertype,requestbody.CNumber], function(err,resp){
            connection.release();
            if(!err) {
            	
            	response.json({"code" : 200, "status" : "CarrierType Record Updated Successfully"});
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating carriertype " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listDRunSheet = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRunSheet",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getDRunSheetByDRSNumber = function(pool, _DRSNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRunSheet where DRSNumber = '" + _DRSNumber +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteDRunSheetByDRSNumber = function(pool, _DRSNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from DRunSheet where DRSNumber = '" + _DRSNumber +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteOlderDRunSheetOfDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from DRunSheet where DRDate <= '" + _Date +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getDRunSheetByDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRunSheet where DRDate = '" + _Date +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.getDRunSheetByDateRange = function(pool, _StartDate, _EndDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRunSheet where DRDate >= '" + _StartDate +"'" + "and DRDate <= '" + _EndDate + "'",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createDRunSheet = function(pool, requestbody, response)
{

	var DRunSheet = {
			DRSNumber: requestbody.DRSNumber , CenterID: requestbody.CenterID ,DRDate: requestbody.DRDate,
			FromCityID: requestbody.FromCityID, ToCityID: requestbody.ToCityID, CarrierTypeID: requestbody.CarrierTypeID, Status : requestbody.Status
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into DRunSheet SET ?", DRunSheet, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('DRunSheet Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating DRunSheet " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateDRunSheet = function(pool, requestbody, response)
{

	var DRunSheet = {
			DRSNumber: requestbody.DRSNumber , CenterID: requestbody.CenterID ,DRDate: requestbody.DRDate,
			FromCityID: requestbody.FromCityID, ToCityID: requestbody.ToCityID, CarrierTypeID: requestbody.CarrierTypeID, Status : requestbody.Status
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update DRunSheet SET ? where DRSNumber = ?", [DRunSheet,requestbody.DRSNumber], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('DRunSheet Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating DRunSheet " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listDRsItems = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRsItems",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getDRsItemsByItemNum = function(pool, _ItemNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRsItems where DRSItemNumber = " + _ItemNum  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getDRsItemsByDRSNumber = function(pool, _DRNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from DRsItems where DRSNumber = '" + _DRNum  + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteDRsItemsByDRSNumber = function(pool, _DRSNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from DRsItems where DRSNumber = '" + _DRSNumber +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteDRsItemsByItemNum = function(pool, _DRSItemNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from DRsItems where DRDate =" + _DRSItemNumber  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};



exports.createDRsItems = function(pool, requestbody, response)
{

	var DRsItem = {
			DRSNumber: requestbody.DRSNumber , CompanyId: requestbody.CompanyId ,NoOfBag: requestbody.NoOfBag,
			Weight: requestbody.Weight
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into DRsItems SET ?", DRsItem, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('DRsItems Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating DRsItems " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateDRsItems = function(pool, requestbody, response)
{

	var DRsItem = {
			DRSItemNumber: requestbody.DRSItemNumber, DRSNumber: requestbody.DRSNumber , CompanyId: requestbody.CompanyId ,NoOfBag: requestbody.NoOfBag,
			Weight: requestbody.Weight
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update DRsItems SET ? where DRSItemNumber = ?", [DRsItem,requestbody.DRSItemNumber], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('DRsItems Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating DRsItems " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};




exports.createMixedBagManifestItem = function(pool, requestbody, response)
{
	Date.prototype.yyyymmdd = function() {
		   var yyyy = this.getFullYear();
		   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
		   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
		   return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
		  };


	var MixedbagManifestItems = {
			ManifestId: requestbody.ManifestId ,CNoteNo: requestbody.CNoteNo,MixedBagLabel: requestbody.MixedBagLabel,
			BagsQuantity: parseInt(requestbody.BagsQuantity), ItemDate : new Date().yyyymmdd()       	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into mixedbagmanifestitems SET ?", MixedbagManifestItems, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('MixedbagManifestItems Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating MixedbagManifestItems " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateMixedBagManifestItem = function(pool, requestbody, response)
{
	Date.prototype.yyyymmdd = function() {
		   var yyyy = this.getFullYear();
		   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
		   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
		   return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
		  };


	var MixedbagManifestItems = {
			ManifestId: requestbody.ManifestId ,CNoteNo: requestbody.CNoteNo,MixedBagLabel: requestbody.MixedBagLabel,
			BagsQuantity: parseInt(requestbody.BagsQuantity), ItemDate : new Date().yyyymmdd()       	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from mixedbagmanifestitems where CNoteNo = '" + requestbody.CNoteNo + "'" + " and ManifestId = '" + 
		                  requestbody.ManifestId +"'" + " and MixedBagLabel = '" + requestbody.MixedBagLabel + "'"

		,function(err,rows){
            
            if(!err) {
            	
            	if(rows!==null && rows.length >0)
            		{
            		MixedbagManifestItems.BagsQuantity = parseInt(requestbody.BagsQuantity) + parseInt(rows[0].BagsQuantity);
            		connection.query("Update mixedbagmanifestitems SET ? where CNoteNo = ? and ManifestId = ? and MixedBagLabel = ?", [MixedbagManifestItems,requestbody.CNoteNo, requestbody.ManifestId, requestbody.MixedBagLabel ], 
					function(err,resp){
                        connection.release();
                        if(!err) {
                        	response.end('mixedbagmanifestitems Record Updated Successfully');
                        }  
                        else
                        	{
                        	response.json({"code" : 101, "status" : " Error in  Updating mixedbagmanifestitems " + err});
                        	console.log(err);
                        	}
                        	
                    }); 
            		}
            	else
            		{
            		
            		connection.query("insert into mixedbagmanifestitems SET ?", MixedbagManifestItems, function(error,resp2){
                        connection.release();
                        if(!error) {
                        	response.end('MixedbagManifestItems Record Inserted Successfully');
                        }  
                        else
                        	{
                        	response.json({"code" : 101, "status" : " Error in  creating MixedbagManifestItems " + error});
                        	console.log(error);
                        	}
                        	
                    }); 
            		}
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating MixedbagManifestItems " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listmixedbagmanifestItem = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from mixedbagmanifestitems",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getmixedbagmanifestItemsByManifestId = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from mixedbagmanifestitems where ManifestId = '" + _ManifestId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.getmixedbagmanifestItemsDetailByManifestId = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        var spquery = "call consignmentmanagement.GetMixedBagManifestItemDetailForManifest(?)";
        connection.query(spquery,[_ManifestId] ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });       
  });
};

exports.DeleteMixedBagManifestItemByManifestId = function(pool, _ManifestId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from mixedbagmanifestitems where ManifestId = '" + _ManifestId +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteMixedBagOlderManifestItemOfDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from mixedbagmanifestitems where ItemDate <= '" + _Date +"'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};
