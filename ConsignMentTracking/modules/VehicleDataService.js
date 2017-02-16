exports.GenerateDRunSheetForVehicle = function(pool, _CarrierTypeID , _Date, response)
{
	Date.prototype.yyyymmdd = function() {
		   var yyyy = this.getFullYear();
		   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
		   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
		   return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
		  };
		  
     Date.prototype.yyyymmddhhmmss = function() {
			   var yyyy = this.getFullYear();
			   var mm = this.getMonth() < 9 ? "0" + (this.getMonth() + 1) : (this.getMonth() + 1); // getMonth() is zero-based
			   var dd  = this.getDate() < 10 ? "0" + this.getDate() : this.getDate();
			   var hh = this.getHours() < 10 ? "0" + this.getHours() : this.getHours();
			   var min = this.getMinutes() < 10 ? "0" + this.getMinutes() : this.getMinutes();
			   var ss = this.getSeconds() < 10 ? "0" + this.getSeconds() : this.getSeconds();
			   return "".concat(yyyy).concat(mm).concat(dd).concat(hh).concat(min).concat(ss);
			  };		  


	
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from Manifest where CarrierTypeID = " + _CarrierTypeID + " AND ManifestDate = '" + _Date + "'" ,function(errmanifest,rowsmanifest){
            
            if(!errmanifest) {
            	
            	if(rowsmanifest!==null && rowsmanifest.length >0)
            		{
            		console.log(rowsmanifest.length);
            		 var manifestcount = 0;
            		 var DRSNumber = "";
            		 for (manifestcount = 0; manifestcount < rowsmanifest.length ; manifestcount++)
            			 {
            			   
            			 DRSNumber = new Date().yyyymmddhhmmss();
            			 var DRunSheet = {
            						DRSNumber: DRSNumber , CenterID: rowsmanifest[manifestcount].CenterID ,DRDate: new Date().yyyymmdd(),
            						FromCityID: rowsmanifest[manifestcount].SourceCityID, ToCityID: rowsmanifest[manifestcount].TargetCityID, CarrierTypeID: rowsmanifest[manifestcount].CarrierTypeID, Status : "Created"
            						        	
            				};
            			 
            			 connection.query("insert into DRunSheet SET ?", DRunSheet, function(errorDR,respDR){
            		           
            		            if(!errorDR) {
            		            	
            		            	console.log('insert is successfull');
									
									console.log('now creating manifest item');
            			 console.log('manifestid');
            			 var manifestid = rowsmanifest[manifestcount].ManifestId;
            			 console.log(manifestid);
            			 connection.query("(select distinct ConsigneeCompId from cnnote where CNNumber IN (select CNoteNo from ManifestItems where ManifestId=?));", [manifestid], function(errormi,respmi){
          		           
         		            if(!errormi) {
         		            	
         		            	if(respmi !=null && respmi.length >0)
         		            		{
         		            		console.log(respmi.length);
         		            		   var compid = "";
         		            		   var compcount = 0;
         		            		   for(compcount =0; compcount <respmi.length; compcount++)
         		            			   {
         		            			     compid = respmi[compcount].ConsigneeCompId;
         		            			     
         		            			    connection.query("select SUM(PackageNo) As TotalPackages , SUM(ActualWeight) AS TotalWeight from (select * from cnnote  where CNNumber IN (select CNoteNo from ManifestItems where ManifestID=?) and ConsigneeCompId=? ) temp", [manifestid,compid], function(error,response){
         		            		            
         		            		            if(!err) {
         		            		            	var DRsItem = {
         		            		           			DRSNumber: DRSNumber , CompanyId: compid ,NoOfBag: response[TotalPackages],
         		            		           			Weight: response[TotalWeight]
         		            		           			        	
         		            		           	};
         		            		            	connection.query("insert into DRsItems SET ?", DRsItem, function(errordi,respdi){
         		            		                   connection.release();
         		            		                   if(!errordi) {
         		            		                   	response.end('DRsItems Record Inserted Successfully');
         		            		                   }  
         		            		                   else
         		            		                   	{
         		            		                   	response.json({"code" : 101, "status" : " Error in  creating DRsItems " + errordi});
         		            		                   	console.log(errordi);
         		            		                   	}
         		            		                   	
         		            		               }); 
         		            		            }  
         		            		            else
         		            		            	{
         		            		            	response.json({"code" : 101, "status" : " Error in  Creating DRItem " + err});
         		            		            	console.log(err);
         		            		            	}
         		            		            	
         		            		        }); 
         		            			     
         		            			   }
         		            		}
         		            }  
         		            else
         		            	{
         		            	response.json({"code" : 101, "status" : " Error in  creating DRunItem " + respmi});
         		            	connection.release();
         		            	console.log(errormi);
         		            	return;
         		            	}
         		            	
         		        }); 
            		            }  
            		            else
            		            	{
            		            	response.json({"code" : 101, "status" : " Error in  creating DRunSheet " + errorDR});
            		            	connection.release();
            		            	console.log(errorDR);
            		            	return;
            		            	}
            		            	
            		        }); 
            			 
            			 
            			 
            			 }
            		
            		
            		}            	
            }  
            else
            	{
            	connection.release();
            	response.json({"code" : 101, "status" : " Error in  getting manifest " + errmanifest});
            	console.log(err);
            	}
            	
        }); 
  });
	
};