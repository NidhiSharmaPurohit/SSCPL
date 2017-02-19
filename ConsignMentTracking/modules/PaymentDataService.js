/**
 * http://usejsdoc.org/
 */

exports.createRate = function(pool, requestbody, response)
{

	var rate = {
			CompanyId: requestbody.CompanyId , CenterId: requestbody.CenterId ,Rate: requestbody.Rate,
			KG: requestbody.KG, Statuss: requestbody.Statuss, ModeID: requestbody.ModeID
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into Rate SET ?", rate, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Rate Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating Rate " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateRate = function(pool, requestbody, response)
{

	var rate = {
			RateId: requestbody.RateId, CompanyId: requestbody.CompanyId , CenterId: requestbody.CenterId ,Rate: requestbody.Rate,
			KG: requestbody.KG, Statuss: requestbody.Statuss, ModeID: requestbody.ModeID
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update Rate SET ? where RateId = ?", [rate, requestbody.RateId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Rate Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating Rate " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listRates = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from Rate",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getRateById = function(pool, _RateId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from Rate where RateId = " + _RateId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getRateByCompanyId = function(pool, _CompanyId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from Rate where CompanyId = " + _CompanyId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetRateForComapnyAndCenterId = function(pool,_CenterId, _CompanyId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from Rate where CompanyId = " + _CompanyId + " and CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.DeleteRate = function(pool, _RateId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from Rate where RateId = " + _RateId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.createInvoice = function(pool, requestbody, response)
{

	var invoice = {
			InvoiceNum: requestbody.InvoiceNum , CompanyId: requestbody.CompanyId ,CenterId: requestbody.CenterId,
			RateId: requestbody.RateId, Statuss: requestbody.Statuss, Amount: requestbody.Amount, Discount : requestbody.Discount,
			TotalAmount: requestbody.TotalAmount, InvoiceDate: requestbody.InvoiceDate, FromDate: requestbody.FromDate, ToDate : requestbody.ToDate
			        	
	};
	
	
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into invoice SET ?", invoice, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Invoice Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating Invoice " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateInvoice = function(pool, requestbody, response)
{

	var invoice = {
			InvoiceNum: requestbody.InvoiceNum , CompanyId: requestbody.CompanyId ,CenterId: requestbody.CenterId,
			RateId: requestbody.RateId, Statuss: requestbody.Statuss, Amount: requestbody.Amount, Discount : requestbody.Discount,
			TotalAmount: requestbody.TotalAmount, InvoiceDate: requestbody.InvoiceDate, FromDate: requestbody.FromDate, ToDate : requestbody.ToDate
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update invoice SET ? where InvoiceNum = ?", [invoice, requestbody.InvoiceNum], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Invoice Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating Invoice " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listInvoice = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from invoice",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where InvoiceNum  = '" + _InvoiceNum + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceByCompanyId = function(pool, _CompanyId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where CompanyId  = " + _CompanyId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceByInvoiceDate = function(pool, _InvoiceDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where InvoiceDate  = '" + _InvoiceDate + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceByDateRange = function(pool, _FromDate, _ToDate, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where InvoiceDate  >= '" + _FromDate + "'" + " and InvoiceDate <= '" + _ToDate + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceByCompanyIdAndCenterId = function(pool, _CompanyId, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where CompanyId  = " + _CompanyId + " and CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetInvoiceByInvoiceDateforCompany = function(pool, _InvoiceDate, _CompanyId, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where InvoiceDate  = '" + _InvoiceDate + "'" + " and CompanyId = " + _CompanyId + " and CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetInvoiceByDateRangeForCompany = function(pool, _FromDate, _ToDate,  _CompanyId, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where InvoiceDate  >= '" + _FromDate + "'" + " and InvoiceDate <= '" + _ToDate + "'" + " and CompanyId = " + _CompanyId + " and CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.FindInvoicesinDateRangeForCompany = function(pool, _FromDate, _ToDate,  _CompanyId, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from invoice where FromDate  <= '" + _ToDate + "'" + " and ToDate >= '" + _FromDate + "'" + " and CompanyId = " + _CompanyId + " and CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteInvoice = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from invoice where InvoiceNum = '" + _InvoiceNum + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createTax = function(pool, requestbody, response)
{

	var tax = {
			TaxName: requestbody.TaxName , Percentage: requestbody.Percentage ,Statuss: requestbody.Statuss,
			StartDate: requestbody.StartDate, EndDate: requestbody.EndDate, CenterId: requestbody.CenterId
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into tax SET ?", tax, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Rate Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating Tax " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};


exports.updateTax = function(pool, requestbody, response)
{

	var tax = {
			TaxId: requestbody.TaxId, TaxName: requestbody.TaxName , Percentage: requestbody.Percentage ,Statuss: requestbody.Statuss,
			StartDate: requestbody.StartDate, EndDate: requestbody.EndDate, CenterId: requestbody.CenterId
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update tax SET ? where TaxId = ?", [tax, requestbody.TaxId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Tax Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating Tax " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listTax = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from tax",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getTaxById = function(pool, _TaxId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from tax where TaxId = " + _TaxId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getTaxByCenterId = function(pool, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from tax where CenterId = " + _CenterId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetTaxForTaxIdAndCenterId = function(pool, _CenterId, _TaxId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from tax where TaxId = " + _TaxId + " and CenterId = " + _CenterId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.DeleteTax = function(pool, _TaxId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from Tax where TaxId = " + _TaxId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createInvoiceItems = function(pool, requestbody, response)
{

	var invoiceitem = {
			InvoiceNum: requestbody.InvoiceNum , RateId: requestbody.RateId ,CNNumber: requestbody.CNNumber,
			Amount: requestbody.Amount, Weight: requestbody.Weight
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into invoiceitems SET ?", invoiceitem, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Rate Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating InvoiceItems " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateInvoiceItems = function(pool, requestbody, response)
{

	var invoiceitem = {
			InvoiceItemId: requestbody.InvoiceItemId, InvoiceNum: requestbody.InvoiceNum , RateId: requestbody.RateId ,CNNumber: requestbody.CNNumber,
			Amount: requestbody.Amount, Weight: requestbody.Weight
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update invoiceitems SET ? where InvoiceItemId = ?", [invoiceitem, requestbody.InvoiceItemId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('InvoiceItem Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating InvoiceItem " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listInvoiceItems = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from invoiceitems",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceItemsById = function(pool, _InvoiceItemId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from invoiceitems where InvoiceItemId = " + _InvoiceItemId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceItemsByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from invoiceitems where InvoiceNum = '" + _InvoiceNum + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getInvoiceItemsByCNNNumber = function(pool, _CNNumber, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from invoiceitems where CNNumber = '" + _CNNumber + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.DeleteInvoiceItems = function(pool, _InvoiceItemId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from invoiceitems where InvoiceItemId = " + _InvoiceItemId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteInvoiceItemsByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from invoiceitems where InvoiceNum = '" + _InvoiceNum + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createPayment = function(pool, requestbody, response)
{

	var payment = {
			InvoiceNum: requestbody.InvoiceNum , PaymentMode: requestbody.PaymentMode ,CenterId: requestbody.CenterId,
			Statuss: requestbody.Statuss, Amount: requestbody.Amount, CreatedBy: requestbody.CreatedBy, DateCreated: requestbody.DateCreated
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into payment SET ?", payment, function(err,resp){
            connection.release();
            if(!err) {
            	console.log(resp.insertId);
            	response.json({"code" : 200,"PaymentId" : resp.insertId, "status" : " Successflly Created Payment Record with " + resp.insertId});
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating Payment " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updatePayment = function(pool, requestbody, response)
{

	var payment = {
			PaymentId: requestbody.PaymentId, InvoiceNum: requestbody.InvoiceNum , PaymentMode: requestbody.PaymentMode ,CenterId: requestbody.CenterId,
			Statuss: requestbody.Statuss, Amount: requestbody.Amount, CreatedBy: requestbody.CreatedBy, DateCreated: requestbody.DateCreated
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update payment SET ? where PaymentId = ?", [payment, requestbody.PaymentId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Payment Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating Payment " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listPayment = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from payment",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getPaymentById = function(pool, _PaymentId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from payment where PaymentId = " + _PaymentId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getPaymentByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from payment where InvoiceNum = '" + _InvoiceNum + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetPaymentForInvoiceNumAndCenterId = function(pool,_CenterId, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from payment where CenterId = " + _CenterId + " and InvoiceNum = '" + _InvoiceNum + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.DeletePayment = function(pool, _PaymentId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from payment where PaymentId = " + _PaymentId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.createPaymentInvoice = function(pool, requestbody, response)
{

	var paymentInvoice = {
			PaymentId: requestbody.PaymentId , InvoiceNum: requestbody.InvoiceNum 
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into paymentinvoice SET ?", paymentInvoice, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('PaymentInvoice Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating PaymentInvoice " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updatePaymentInvoice = function(pool, requestbody, response)
{

	var paymentInvoice = {
			PaymentInvoiceId: requestbody.PaymentInvoiceId, PaymentId: requestbody.PaymentId , InvoiceNum: requestbody.InvoiceNum 
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update paymentinvoice SET ? where PaymentInvoiceId = ?", [paymentInvoice, requestbody.PaymentInvoiceId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Paymentinvoice Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating Paymentinvoice " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listPaymentInvoice = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from paymentinvoice",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getPaymentInvoiceById = function(pool, _Paymentinvoice, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from paymentinvoice where PaymentInvoiceId = " + _Paymentinvoice  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getPaymentInvoiceByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from PaymentInvoiceId where InvoiceNum = '" + _InvoiceNum + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeletePaymentInvoice = function(pool, _Paymentinvoice, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from paymentinvoice where PaymentInvoiceId = " + _Paymentinvoice ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeletePaymentInvoiceByPaymentId = function(pool, _PaymentId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from paymentinvoice where PaymentId = " + _PaymentId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createTaxInvoice = function(pool, requestbody, response)
{

	var taxInvoice = {
			InvoiceNum: requestbody.InvoiceNum , TaxId: requestbody.TaxId , TaxAmount : requestbody.TaxAmount
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into TaxInvoice SET ?", taxInvoice, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('TaxInvoice Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating TaxInvoice " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateTaxInvoice = function(pool, requestbody, response)
{

	var taxInvoice = {
			TaxInvoiceId: requestbody.TaxInvoiceId, InvoiceNum: requestbody.InvoiceNum , TaxId: requestbody.TaxId , TaxAmount : requestbody.TaxAmount
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update TaxInvoice SET ? where TaxInvoiceId = ?", [taxInvoice, requestbody.TaxInvoiceId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('TaxInvoice Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating TaxInvoice " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listTaxInvoice = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from TaxInvoice",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getTaxInvoiceById = function(pool, _TaxInvoiceId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from TaxInvoice where TaxInvoiceId = " + _TaxInvoiceId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getTaxInvoiceByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from TaxInvoice where InvoiceNum = '" + _InvoiceNum + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteTaxInvoice = function(pool, _TaxInvoiceId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from TaxInvoice where TaxInvoiceId = " + _TaxInvoiceId ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteTaxInvoiceByInvoiceNum = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from TaxInvoice where InvoiceNum = '" + _InvoiceNum + "'",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.createExpenses = function(pool, requestbody, response)
{

	var expenses = {
			CenterId: requestbody.CenterId , Typee: requestbody.Typee ,Description: requestbody.Description,
			Amount: requestbody.Amount, Modee: requestbody.Modee, ExpenseDate: requestbody.ExpenseDate
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into expenses SET ?", expenses, function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Expenses Record Inserted Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  creating Expenses " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.updateExpenses = function(pool, requestbody, response)
{

	var expenses = {
			ExpenseId: requestbody.ExpenseId, CenterId: requestbody.CenterId , Typee: requestbody.Typee ,Description: requestbody.Description,
			Amount: requestbody.Amount, Modee: requestbody.Modee, ExpenseDate: requestbody.ExpenseDate
			        	
	};
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("Update expenses SET ? where ExpenseId = ?", [expenses, requestbody.ExpenseId], function(err,resp){
            connection.release();
            if(!err) {
            	response.end('Expenses Record Updated Successfully');
            }  
            else
            	{
            	response.json({"code" : 101, "status" : " Error in  Updating Expenses " + err});
            	console.log(err);
            	}
            	
        }); 
  });
	
};

exports.listExpenses = function(pool, request, response)
{

	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from expenses",function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getExpensesById = function(pool, _ExpensesId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from expenses where ExpenseId = " + _ExpensesId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.getExpensesByDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from expenses where ExpenseDate = '" + _Date + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetExpensesForDateRangeAndCenterId = function(pool,_StartDate, _EndDate, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from expenses where CenterId = " + _CenterId + " AND ExpenseDate < = '" + _StartDate + "'" + " AND ExpenseDate >= '" + _EndDate + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.GetExpensesForDateAndCenterId = function(pool,_CenterId, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("select * from expenses where CenterId = " + _CenterId + " AND ExpenseDate = '" + _Date + "'"  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.DeleteExpensesByDate = function(pool, _Date, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from expenses where ExpenseDate = '" + _Date + "'" ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};

exports.DeleteExpensesById = function(pool, _ExpensesId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("delete  from expenses where ExpenseId = " + _ExpensesId  ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows);
            }          
        });       
  });
};


exports.getInvoicelDetailsbyInvoiceNumber = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetInvoiceDetailsForInvoiceNum(?)";
        
       
        connection.query(spquery, [_InvoiceNum]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.getInvoiceDetailsbyInvoiceNumber = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetInvoiceDetailsForInvoiceNum(?)";
        
       
        connection.query(spquery, [_InvoiceNum]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.getInvoiceItemDetailsbyInvoiceNumber = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetInvoiceItemDetailsForInvoiceNum(?)";
        
       
        connection.query(spquery, [_InvoiceNum]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.getTaxInvoiceDetailsbyInvoiceNumber = function(pool, _InvoiceNum, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetTaxInvoiceDetailsForInvoiceNum(?)";
        
       
        connection.query(spquery, [_InvoiceNum]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};


exports.getActiveInvoicesforCompany = function(pool, _CompanyId, _CenterId, response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetActiveInvoicesforCompany(?,?)";
        
       
        connection.query(spquery, [_CompanyId, _CenterId]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};

exports.getActiveInvoicesforCompanyInDateRange = function(pool, _fromDate, _toDate, _CompanyId, _CenterId,  response)
{
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
        var spquery = "call consignmentmanagement.GetActiveInvoicesforCompanyInDateRange(?,?,?,?)";
        
       
        connection.query(spquery, [_fromDate, _toDate, _CompanyId, _CenterId ]   ,function(err,rows){
            connection.release();
            if(!err) {
            	response.json(rows[0]);
            }          
        });
        
  });
};


exports.createPaymentForCnnumber = function(pool, requestbody, response)
{

	var invoice = {
			InvoiceNum: requestbody.InvoiceNum , CompanyId: requestbody.CompanyId ,CenterId: requestbody.CenterId,
			RateId: requestbody.RateId, Statuss: requestbody.Statuss, Amount: requestbody.Amount, Discount : requestbody.Discount,
			TotalAmount: requestbody.TotalAmount, InvoiceDate: requestbody.InvoiceDate, FromDate: requestbody.FromDate, ToDate : requestbody.ToDate,
			TaxAmount: requestbody.TaxAmount
			        	
	};
	
	
	
	pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          response.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }  

        console.log('connected as id ' + connection.threadId);
       
        connection.query("insert into invoice SET ?", invoice, function(err,resp){
            
            if(!err) {
            	           
						   var invoiceitem = {
			                                   InvoiceNum: requestbody.InvoiceNum , RateId: requestbody.RateId ,CNNumber: requestbody.CNNumber,
			                                   Amount: requestbody.TotalAmount, Weight: requestbody.Weight
			        	
	                                         };
											 
											 connection.query("insert into invoiceitems SET ?", invoiceitem, function(error,respp){
                                            
                                             if(!error) {
												 
												           var payment = {
			                                                                 InvoiceNum: requestbody.InvoiceNum , PaymentMode: requestbody.PaymentMode ,CenterId: requestbody.CenterId,
			                                                                 Statuss: requestbody.Statuss, Amount: requestbody.TotalAmount, CreatedBy: requestbody.CreatedBy, 
																			 DateCreated: requestbody.DateCreated
			        	
	                                                                     };
																		 
															connection.query("insert into payment SET ?", payment, function(exp,reso){
                                                            connection.release();
                                                            if(!exp) {
            	                                                       console.log(reso.insertId);
            	                                                       response.json({"code" : 200,"PaymentId" : reso.insertId, "status" : " Successflly Created Payment Record with " + reso.insertId});
                                                                     }  
                                                                  else
            	                                                     {
            	                                                        response.json({"code" : 101, "status" : " Error in  creating Payment " + exp});
            	                                                        console.log(exp);
            	                                                     }
            	
                                                                   }); 			 
																		 
            	                                        
                                                        }  
                                               else
            	                                    {
														connection.release();
            	                                        response.json({"code" : 101, "status" : " Error in  creating InvoiceItems " + error});
            	                                        console.log(error);
            	                                    }
            	
                                                }); 
						   
            }  
            else
            	{
					connection.release();
            	     response.json({"code" : 101, "status" : " Error in  creating Invoice " + err});
            	      console.log(err);
            	}
            	
        }); 
  });
	
};