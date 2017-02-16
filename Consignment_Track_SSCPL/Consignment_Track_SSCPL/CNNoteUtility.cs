using iTextSharp.text;
using iTextSharp.text.pdf;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Consignment_Track_SSCPL
{
    public class CNNoteUtility
    {
        public string _CNNumber;

        public void GenerateCNotePdfAndPrint()
        {
            DataManager dm = new DataManager();
            string cnotesignimagefilename = _CNNumber + ".jpg";
            string deliveredcnotesignimagefilename = "d" + _CNNumber + ".jpg";
            string CNoteSignImageUrl = string.Format(dm.getCNNoteSignedImageGetBaseUrl(), cnotesignimagefilename);
            string DeliverdCNoteSignImageUrl = string.Format(dm.getCNNoteSignedImageGetBaseUrl(), deliveredcnotesignimagefilename);
            string cnnotePdfFileName = _CNNumber + ".pdf";
            CNNotesFullDetails[] CnnoteDetails = dm.GetCNNotesFullDetails(_CNNumber);

            Document doc = new Document();

            PdfWriter.GetInstance(doc, new FileStream(cnnotePdfFileName, FileMode.Create, FileAccess.ReadWrite));
            doc.Open();

            iTextSharp.text.Image Img = iTextSharp.text.Image.GetInstance("sscpl.jpg");

            PdfPTable table1 = new PdfPTable(4);

            PdfPCell cell11 = new PdfPCell(Img, true);
            cell11.Colspan = 4;
            table1.AddCell(cell11);
            iTextSharp.text.Font tablefont = new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 7, iTextSharp.text.Font.NORMAL, BaseColor.BLACK);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Handling Information: ", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 9, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell1.Colspan = 4;
            table1.AddCell(cell1);


            table1.AddCell(new PdfPCell(new Paragraph("Origin", tablefont)));
            table1.AddCell(new PdfPCell(new Paragraph("Destination", tablefont)));
            table1.AddCell(new PdfPCell(new Paragraph("Date of Booking", tablefont)));
            table1.AddCell(new PdfPCell(new Paragraph("CNNOTE Number", tablefont)));

            table1.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].SourceCity, tablefont)));
            table1.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].DestCity, tablefont)));
            table1.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].BookingDate.ToShortDateString(), tablefont)));
            table1.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].CNNumber, tablefont)));

            table1.SpacingBefore = 5f;
            table1.SpacingAfter = 5f;

            PdfPTable table2 = new PdfPTable(2);
            table2.AddCell(new PdfPCell(new Paragraph("Name & Address of CONSIGNOR", tablefont)));
            table2.AddCell(new PdfPCell(new Paragraph("Name & Address of CONSIGNEE", tablefont)));
            table2.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].ShipperCompany + "  " + CnnoteDetails[0].ShipperCompanyAddress + "  " + CnnoteDetails[0].ShipperCity, tablefont)));
            table2.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].ConsigneeCompany + "  " + CnnoteDetails[0].ConsigneeCompanyAddress + "  " + CnnoteDetails[0].ConsigneeCity, tablefont)));

            table2.SpacingBefore = 4f;
            table2.SpacingAfter = 4f;

            PdfPTable table3 = new PdfPTable(7);

            table3.AddCell(new PdfPCell(new Paragraph("Num of Packages", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph("Actual Weight", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph("Consignment Weight", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph("Transport Mode", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph("Payment Mode", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph("Tax", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph("Total", tablefont)));

            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].PackageNo.ToString(), tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].ActualWeight.HasValue? CnnoteDetails[0].ActualWeight.Value.ToString(): " ", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].ConsignmentWeight.HasValue? CnnoteDetails[0].ConsignmentWeight.Value.ToString(): " ", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].TransportMode, tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].PayMode, tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].ServiceTax.HasValue? CnnoteDetails[0].ServiceTax.Value.ToString(): " ", tablefont)));
            table3.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].TOTAL.HasValue? CnnoteDetails[0].TOTAL.Value.ToString(): " ", tablefont)));

            table3.SpacingBefore = 4f;
            table3.SpacingAfter = 4f;

            PdfPTable table4 = new PdfPTable(6);
            PdfPCell cell41 = new PdfPCell(new Paragraph("Signature of Consignor: ", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 10, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell41.Colspan = 3;
            table4.AddCell(cell41);
            PdfPCell cell42 = new PdfPCell(new Paragraph("Signature of Consignee: ", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 10, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell42.Colspan = 3;
            table4.AddCell(cell42);

            table4.AddCell(new PdfPCell(new Paragraph("Handed By", tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph("Date", tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph("Signature", tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph("Received By", tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph("Date", tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph("Signature", tablefont)));

            table4.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].HandedBy, tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].BookingDate.ToShortDateString(), tablefont)));
            try
            {
                iTextSharp.text.Image sImage = iTextSharp.text.Image.GetInstance(new Uri(CNoteSignImageUrl));
                table4.AddCell(new PdfPCell(sImage, true));
            }
            catch (Exception)
            {
                table4.AddCell(new PdfPCell());
            }
            table4.AddCell(new PdfPCell(new Paragraph(CnnoteDetails[0].ReceivedBy, tablefont)));
            table4.AddCell(new PdfPCell(new Paragraph(DateTime.Now.ToShortDateString(), tablefont)));
            try
            {
                iTextSharp.text.Image dImage = iTextSharp.text.Image.GetInstance(new Uri(DeliverdCNoteSignImageUrl));
                table4.AddCell(new PdfPCell(dImage, true));
            }
            catch (Exception)
            {
                table4.AddCell(new PdfPCell());
            }

            PdfPCell cell43 = new PdfPCell(new Paragraph(string.Format("Collected By: For {0}", CnnoteDetails[0].CenterName + "  " + CnnoteDetails[0].CenterAddress + "  " + CnnoteDetails[0].CenterCity), new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 9, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell43.Colspan = 6;
            table4.AddCell(cell43);

            doc.Add(table1);
            doc.Add(table2);
            doc.Add(table3);
            doc.Add(table4);
            doc.Close();


        }
    }
}
