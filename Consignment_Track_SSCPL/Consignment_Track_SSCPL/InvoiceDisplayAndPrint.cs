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
    public partial class InvoiceDisplayAndPrint : Form
    {
        public string InvoiceNum;
        public InvoiceDetail[] _Invoices;
        public InvoiceItemDetail[] _InvoiceItems;
        public TaxInvoiceDetail[] _TaxInvoices;

        private CenterMaster[] _CenterMasterList;
        private PackagingMode[] _PackageModeList;
        private Company[] _CompanyLsit;
        private TransportMode[] _TransportModeList;
        private Rates[] _RateList;
        private Tax[] _TaxList;
        private City[] _CityList;


        public InvoiceDisplayAndPrint()
        {
            InitializeComponent();
        }

        private void InvoiceDisplayAndPrint_Load(object sender, EventArgs e)
        {
            _CenterMasterList = CachedDataManager.GetCachedCenterMaster();
            _PackageModeList = CachedDataManager.GetCachedPackagingMode();
            _CompanyLsit = CachedDataManager.GetCachedCompany();
            _TransportModeList = CachedDataManager.GetCachedTransportMode();
            _RateList = CachedDataManager.GetCachedRate();
            _TaxList = CachedDataManager.GetCachedTax();
            _CityList = CachedDataManager.GetCachedCity();

            DefaultPrinter.Text = ConfigurationManager.AppSettings["DefaultPrinterName"];

            _Invoices = new DataManager().GetInvoiceDetailByInvoiceNum(InvoiceNum);
            _InvoiceItems = new DataManager().GetInvoiceItemsDetailByInvoiceNum(InvoiceNum);
            _TaxInvoices = new DataManager().GetTaxInvoiceDetailRecordByInvoiceNum(InvoiceNum);

            
            PrinterSettings printer = new PrinterSettings();
            foreach (var item in PrinterSettings.InstalledPrinters)
            {
                PrinterList.Items.Add(item.ToString());
            }

            setInvoiceGridProperty();
            setInvoiceItemGridProperty();
            setTaxInvoiceGridProperty();

            dataGridView1.DataSource = _Invoices;
            dataGridView2.DataSource = _InvoiceItems;
            dataGridView3.DataSource = _TaxInvoices;
        }

        private void setInvoiceGridProperty()
        {
            dataGridView1.AutoGenerateColumns = false;
            dataGridView1.ColumnCount = 13;

            dataGridView1.Columns[0].Name = "InvoiceNum";
            dataGridView1.Columns[0].HeaderText = "InvoiceNum";
            dataGridView1.Columns[0].DataPropertyName = "InvoiceNum";
            dataGridView1.Columns[0].ReadOnly = true;
            dataGridView1.Columns[0].Width = 120;


            dataGridView1.Columns[1].Name = "ShipperCompany";
            dataGridView1.Columns[1].HeaderText = "ShipperCompany";
            dataGridView1.Columns[1].DataPropertyName = "ShipperCompany";
            dataGridView1.Columns[1].Width = 95;
            dataGridView1.Columns[1].ReadOnly = true;

            dataGridView1.Columns[2].Name = "ShipperCity";
            dataGridView1.Columns[2].HeaderText = "ShipperCity";
            dataGridView1.Columns[2].DataPropertyName = "ShipperCity";
            dataGridView1.Columns[2].ReadOnly = true;
            dataGridView1.Columns[2].Width = 70;

            dataGridView1.Columns[3].Name = "InvoiceDate";
            dataGridView1.Columns[3].HeaderText = "InvoiceDate";
            dataGridView1.Columns[3].DataPropertyName = "InvoiceDate";
            dataGridView1.Columns[3].ReadOnly = true;
            dataGridView1.Columns[3].Width = 105;

            dataGridView1.Columns[4].Name = "FromDate";
            dataGridView1.Columns[4].HeaderText = "FromDate";
            dataGridView1.Columns[4].DataPropertyName = "FromDate";
            dataGridView1.Columns[4].ReadOnly = true;
            dataGridView1.Columns[4].Width = 105;

            dataGridView1.Columns[5].Name = "ToDate";
            dataGridView1.Columns[5].HeaderText = "ToDate";
            dataGridView1.Columns[5].DataPropertyName = "ToDate";
            dataGridView1.Columns[5].ReadOnly = true;
            dataGridView1.Columns[5].Width = 105;


            dataGridView1.Columns[6].Name = "Amount";
            dataGridView1.Columns[6].HeaderText = "Amount";
            dataGridView1.Columns[6].DataPropertyName = "Amount";
            dataGridView1.Columns[6].ReadOnly = true;
            dataGridView1.Columns[6].Width = 60;

            dataGridView1.Columns[7].Name = "Discount";
            dataGridView1.Columns[7].HeaderText = "Discount";
            dataGridView1.Columns[7].DataPropertyName = "Discount";
            dataGridView1.Columns[7].ReadOnly = true;
            dataGridView1.Columns[7].Width = 60;


            dataGridView1.Columns[8].Name = "TotalAmount";
            dataGridView1.Columns[8].HeaderText = "TotalAmount";
            dataGridView1.Columns[8].DataPropertyName = "TotalAmount";
            dataGridView1.Columns[8].ReadOnly = true;
            dataGridView1.Columns[8].Width = 80;


            dataGridView1.Columns[9].Name = "Rate";
            dataGridView1.Columns[9].HeaderText = "Rate";
            dataGridView1.Columns[9].DataPropertyName = "Rate";
            dataGridView1.Columns[9].ReadOnly = true;
            dataGridView1.Columns[9].Width = 55;

            dataGridView1.Columns[10].Name = "RateforKGMax";
            dataGridView1.Columns[10].HeaderText = "RateforKGMax";
            dataGridView1.Columns[10].DataPropertyName = "RateforKGMax";
            dataGridView1.Columns[10].ReadOnly = true;
            dataGridView1.Columns[10].Width = 85;

            dataGridView1.Columns[11].Name = "CenterName";
            dataGridView1.Columns[11].HeaderText = "CenterName";
            dataGridView1.Columns[11].DataPropertyName = "CenterName";
            dataGridView1.Columns[11].ReadOnly = true;
            dataGridView1.Columns[11].Width = 110;

            dataGridView1.Columns[12].Name = "CenterCity";
            dataGridView1.Columns[12].HeaderText = "CenterCity";
            dataGridView1.Columns[12].DataPropertyName = "CenterCity";
            dataGridView1.Columns[12].Width = 80;

        }

        private void setInvoiceItemGridProperty()
        {
            dataGridView2.AutoGenerateColumns = false;
            dataGridView2.ColumnCount = 11;

            dataGridView2.Columns[0].Name = "CNNumber";
            dataGridView2.Columns[0].HeaderText = "CNNumber";
            dataGridView2.Columns[0].DataPropertyName = "CNNumber";
            dataGridView2.Columns[0].ReadOnly = true;


            dataGridView2.Columns[1].Name = "BookingDate";
            dataGridView2.Columns[1].HeaderText = "BookingDate";
            dataGridView2.Columns[1].DataPropertyName = "BookingDate";
            dataGridView2.Columns[1].ReadOnly = true;
            dataGridView2.Columns[1].Width = 115;

            dataGridView2.Columns[2].Name = "OriginCity";
            dataGridView2.Columns[2].HeaderText = "OriginCity";
            dataGridView2.Columns[2].DataPropertyName = "OriginCity";
            dataGridView2.Columns[2].ReadOnly = true;
            dataGridView2.Columns[2].Width = 85;

            dataGridView2.Columns[3].Name = "DestinationCity";
            dataGridView2.Columns[3].HeaderText = "DestinationCity";
            dataGridView2.Columns[3].DataPropertyName = "DestinationCity";
            dataGridView2.Columns[3].ReadOnly = true;

            dataGridView2.Columns[4].Name = "PackageNo";
            dataGridView2.Columns[4].HeaderText = "PackageNo";
            dataGridView2.Columns[4].DataPropertyName = "PackageNo";
            dataGridView2.Columns[4].ReadOnly = true;
            dataGridView2.Columns[4].Width = 100;

            dataGridView2.Columns[5].Name = "ConsignmentWeight";
            dataGridView2.Columns[5].HeaderText = "ConsignmentWeight";
            dataGridView2.Columns[5].DataPropertyName = "ConsignmentWeight";
            dataGridView2.Columns[5].ReadOnly = true;
            dataGridView2.Columns[5].Width = 100;


            dataGridView2.Columns[6].Name = "Rate";
            dataGridView2.Columns[6].HeaderText = "Rate";
            dataGridView2.Columns[6].DataPropertyName = "Rate";
            dataGridView2.Columns[6].ReadOnly = true;

            dataGridView2.Columns[7].Name = "Amount";
            dataGridView2.Columns[7].HeaderText = "Amount";
            dataGridView2.Columns[7].DataPropertyName = "Amount";
            dataGridView2.Columns[7].ReadOnly = true;
            dataGridView2.Columns[7].Width = 100;


            dataGridView2.Columns[8].Name = "Weight";
            dataGridView2.Columns[8].HeaderText = "Weight";
            dataGridView2.Columns[8].DataPropertyName = "Weight";
            dataGridView2.Columns[8].ReadOnly = true;
            dataGridView2.Columns[8].Width = 110;


            dataGridView2.Columns[9].Name = "Mode";
            dataGridView2.Columns[9].HeaderText = "Mode";
            dataGridView2.Columns[9].DataPropertyName = "Mode";
            dataGridView2.Columns[9].ReadOnly = true;
            dataGridView2.Columns[9].Width = 87;

            dataGridView2.Columns[10].Name = "PayMode";
            dataGridView2.Columns[10].HeaderText = "PayMode";
            dataGridView2.Columns[10].DataPropertyName = "PayMode";
            dataGridView2.Columns[10].ReadOnly = true;
            dataGridView2.Columns[10].Width = 78;

           
        }

        private void setTaxInvoiceGridProperty()
        {
            dataGridView3.AutoGenerateColumns = false;
            dataGridView3.ColumnCount = 3;

            dataGridView3.Columns[0].Name = "TaxName";
            dataGridView3.Columns[0].HeaderText = "TaxName";
            dataGridView3.Columns[0].DataPropertyName = "TaxName";
            dataGridView3.Columns[0].ReadOnly = true;
            dataGridView3.Columns[0].Width = 150;

            dataGridView3.Columns[1].Name = "Percentage";
            dataGridView3.Columns[1].HeaderText = "Percentage";
            dataGridView3.Columns[1].DataPropertyName = "Percentage";
            dataGridView3.Columns[1].ReadOnly = true;
            dataGridView3.Columns[1].Width = 150;

            dataGridView3.Columns[2].Name = "TaxAmount";
            dataGridView3.Columns[2].HeaderText = "TaxAmount";
            dataGridView3.Columns[2].DataPropertyName = "TaxAmount";
            dataGridView3.Columns[2].ReadOnly = true;
            dataGridView3.Columns[2].Width = 200;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Document doc = new Document();
            
            PdfWriter.GetInstance(doc, new FileStream(_Invoices[0].InvoiceNum + ".pdf", FileMode.Create, FileAccess.ReadWrite));
            doc.Open();

            iTextSharp.text.Image Img = iTextSharp.text.Image.GetInstance("sscpl.jpg");

            doc.Add(Img);

            Paragraph p = new Paragraph("Invoice",new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.BOLD, iTextSharp.text.Font.BOLD,BaseColor.CYAN));
            doc.Add(p);

            Paragraph p1 = new Paragraph("Invoice Number : " + _Invoices[0].InvoiceNum, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL));
            doc.Add(p1);

            Paragraph p2 = new Paragraph(_Invoices[0].ShipperCompany + "  " + _Invoices[0].ShipperCity, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p2);

            Paragraph p3 = new Paragraph("Invoice Date : " + _Invoices[0].InvoiceDate.ToString(), new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p3);

            Paragraph p4 = new Paragraph(string.Format("Invoice Duration : {0} to {1}",_Invoices[0].FromDate.ToString(), _Invoices[0].ToDate.ToString()), new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p4);

            PdfPTable table = new PdfPTable(8);
            PdfPCell cell = new PdfPCell(new Paragraph("Invoice Items", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 12, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell.Colspan = 8;
            table.AddCell(cell);
            iTextSharp.text.Font tablefont = new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 7, iTextSharp.text.Font.NORMAL, BaseColor.BLACK);

            table.AddCell(new PdfPCell(new Paragraph("BookingDate", tablefont)));
            PdfPCell p11 = new PdfPCell(new Paragraph("AwbNo", tablefont));
            table.AddCell(p11);
            table.AddCell(new PdfPCell(new Paragraph("Destination", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("FlightNo", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Pcs", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Weight", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Rate", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Amount", tablefont)));

            foreach(InvoiceItemDetail it in _InvoiceItems)
            {
                table.AddCell(new PdfPCell(new Paragraph(it.BookingDate.ToShortDateString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.CNNumber, tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.DestinationCity, tablefont)));
                table.AddCell(new PdfPCell(new Paragraph("IC617W", tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.PackageNo.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.Weight.Value.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.Rate.Value.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.Amount.Value.ToString(), tablefont)));
            }
            

            table.WidthPercentage = 100;

            table.SpacingBefore = 15f;
            table.SpacingAfter = 15f;

            PdfPTable taxtable = new PdfPTable(3);
            PdfPCell taxcell = new PdfPCell(new Paragraph("Taxable Information"));
            taxcell.Colspan = 3;
            taxtable.AddCell(taxcell);
            

            taxtable.AddCell("TaxName");
            taxtable.AddCell("Percentage");
            taxtable.AddCell("TaxableAmount");

            foreach (TaxInvoiceDetail ti in _TaxInvoices)
            {
                taxtable.AddCell(ti.TaxName.ToString());
                taxtable.AddCell(ti.Percentage.ToString());
                taxtable.AddCell(ti.TaxAmount.ToString());
            }

           

            taxtable.SpacingBefore = 15f;
            taxtable.SpacingAfter = 15f;


            doc.Add(table);
            doc.Add(taxtable);

            doc.Close();

            button2.Enabled = true;

            try
            {
                System.Diagnostics.Process.Start(_Invoices[0].InvoiceNum + ".pdf");
            }
            catch(Exception exp)
            {

            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            string pathToExecutable = ConfigurationManager.AppSettings["AcrobatReaderPath"];
            string pdfname = _Invoices[0].InvoiceNum + ".pdf";
            string printername = DefaultPrinter.Text;
            string args = string.Format((@"/t ""{0}"" ""{1}"""), pdfname ,printername);
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
}
