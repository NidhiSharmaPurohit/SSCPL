using iTextSharp.text;
using iTextSharp.text.pdf;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Printing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace Consignment_Track_SSCPL
{
   
    public partial class ActivePaymentForPrint : Form
    {
        public int PaymentId;
        
        public Payment[] _Payments;
        public InvoiceDetail[] _InvoiceDetails;
        public PackagingMode[] _PackagingMode;
        public CenterMaster[] _CenterMaster;
        public string _paymentreceiptpath;

        public ActivePaymentForPrint()
        {
            InitializeComponent();
        }

        private void ActivePaymentForPrint_Load(object sender, EventArgs e)
        {
            _Payments = new DataManager().GetPaymentById(PaymentId.ToString());
            _InvoiceDetails = new DataManager().GetInvoiceDetailByInvoiceNum(_Payments[0].InvoiceNum);

            _CenterMaster = CachedDataManager.GetCachedCenterMaster();
            _PackagingMode = CachedDataManager.GetCachedPackagingMode();

            string mode = (from pm in _PackagingMode
                           where pm.Id == _Payments[0].PaymentMode
                           select pm.Typee).FirstOrDefault();

            PaymentForActiveInvoice[] paymentinvoices = new PaymentForActiveInvoice[1];
            paymentinvoices[0] = new PaymentForActiveInvoice();

            paymentinvoices[0].Amount = _Payments[0].Amount;
            paymentinvoices[0].InvoiceNumber = _Payments[0].InvoiceNum;
            paymentinvoices[0].PaymentDate = _Payments[0].DateCreated;
            paymentinvoices[0].ShipperCompany = _InvoiceDetails[0].ShipperCompany;
            paymentinvoices[0].ShipperCity = _InvoiceDetails[0].ShipperCity;
            paymentinvoices[0].CenterName = _InvoiceDetails[0].CenterName;
            paymentinvoices[0].CenterCity = _InvoiceDetails[0].CenterCity;
            paymentinvoices[0].InvoiceDate = _InvoiceDetails[0].InvoiceDate;
            paymentinvoices[0].CreatedBy = _Payments[0].CreatedBy;
            paymentinvoices[0].PaymentMode = mode;
            paymentinvoices[0].PaymentId = _Payments[0].PaymentId;
            

            dataGridView1.DataSource = paymentinvoices;
            
            DefaultPrinter.Text = ConfigurationManager.AppSettings["DefaultPrinterName"];

            PrinterSettings printer = new PrinterSettings();
            foreach (var item in PrinterSettings.InstalledPrinters)
            {
                PrinterList.Items.Add(item.ToString());
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Document doc = new Document();
            _paymentreceiptpath = _Payments[0].InvoiceNum + _Payments[0].PaymentId.ToString() + ".pdf";
            PdfWriter.GetInstance(doc, new FileStream(_paymentreceiptpath , FileMode.Create, FileAccess.ReadWrite));
            doc.Open();

            iTextSharp.text.Image Img = iTextSharp.text.Image.GetInstance("sscpl.jpg");

            doc.Add(Img);

            Paragraph p = new Paragraph("Payment Receipt", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.BOLD, iTextSharp.text.Font.BOLD, BaseColor.CYAN));
            doc.Add(p);

            Paragraph p1 = new Paragraph("Payment For Invoice Number : " + _Payments[0].InvoiceNum, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL));
            doc.Add(p1);

            Paragraph p2 = new Paragraph(_InvoiceDetails[0].ShipperCompany + "  " + _InvoiceDetails[0].ShipperCity, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p2);

            Paragraph p3 = new Paragraph("Invoice Date : " + _InvoiceDetails[0].InvoiceDate.ToString(), new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p3);

            Paragraph p4 = new Paragraph(string.Format("Invoice Duration : {0} to {1}", _InvoiceDetails[0].FromDate.ToString(), _InvoiceDetails[0].ToDate.ToString()), new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p4);

            PdfPTable table = new PdfPTable(6);
            PdfPCell cell = new PdfPCell(new Paragraph("Payment Details", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 12, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell.Colspan = 6;
            table.AddCell(cell);
            iTextSharp.text.Font tablefont = new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 7, iTextSharp.text.Font.NORMAL, BaseColor.BLACK);

            table.AddCell(new PdfPCell(new Paragraph("PaymentID", tablefont)));
            PdfPCell p11 = new PdfPCell(new Paragraph("InvoiceNum", tablefont));
            table.AddCell(p11);
            table.AddCell(new PdfPCell(new Paragraph("PaymentMode", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Amount", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("CreatedBy", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("DateCreated", tablefont)));
           
            foreach (Payment py in _Payments)
            {
                table.AddCell(new PdfPCell(new Paragraph(py.PaymentId.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(py.InvoiceNum, tablefont)));

                string mode = (from pm in _PackagingMode
                               where pm.Id == py.PaymentMode
                               select pm.Typee).FirstOrDefault();
                table.AddCell(new PdfPCell(new Paragraph(mode, tablefont)));
                
                table.AddCell(new PdfPCell(new Paragraph(py.Amount.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(py.CreatedBy, tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(py.DateCreated.ToString(), tablefont)));
            }


            table.WidthPercentage = 100;

            table.SpacingBefore = 15f;
            table.SpacingAfter = 15f;

            


            doc.Add(table);
            string centername = (from cm in _CenterMaster
                                 where cm.CenterId == _Payments[0].CenterId
                                 select cm.Name + " " + cm.City).FirstOrDefault();
            Paragraph p5 = new Paragraph("Received with Thanks from : " + centername, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p5);

            doc.Close();

            button2.Enabled = true;

            try
            {
                System.Diagnostics.Process.Start(_paymentreceiptpath);
            }
            catch (Exception exp)
            {

            }

        }

        private void button2_Click(object sender, EventArgs e)
        {
            string pathToExecutable = ConfigurationManager.AppSettings["AcrobatReaderPath"];
            string pdfname = _paymentreceiptpath;
            string printername = DefaultPrinter.Text;
            string args = string.Format((@"/t ""{0}"" ""{1}"""), pdfname, printername);
            RunExecutable(pathToExecutable, args);
        }

        private static void RunExecutable(string executable, string arguments)
        {
            ProcessStartInfo starter = new ProcessStartInfo(executable, arguments);
            starter.CreateNoWindow = true;
            starter.RedirectStandardOutput = true;
            starter.UseShellExecute = false;
            Process process = new Process();
            process.StartInfo = starter;
            process.Start();
            StringBuilder buffer = new StringBuilder();
            using (StreamReader reader = process.StandardOutput)
            {
                string line = reader.ReadLine();
                while (line != null)
                {
                    buffer.Append(line);
                    buffer.Append(Environment.NewLine);
                    line = reader.ReadLine();
                    Thread.Sleep(100);
                }
            }
            if (process.ExitCode != 0)
            {
                MessageBox.Show("Print Not Successful " + string.Format(@"""{0}"" exited with ExitCode {1}. Output: {2}",
            executable, process.ExitCode, buffer.ToString()));
            }
        }
    }

    public class PaymentForActiveInvoice
    {
        public int PaymentId { get; set; }
        public string InvoiceNumber { get; set; }
        public string PaymentMode { get; set; }
        public string CenterName { get; set; }
        public string CenterCity { get; set; }
        public double Amount { get; set; }
        public string CreatedBy { get; set; }
        public DateTime PaymentDate { get; set; }
        public string ShipperCompany { get; set; }
        public string ShipperCity { get; set; }
        public DateTime InvoiceDate { get; set; }


    }

}
