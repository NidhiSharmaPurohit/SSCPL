package srmicrosystems.cnote.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/*import com.itextpdf.io.image.*;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;*/
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/*import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.Codabar;
import com.onbarcode.barcode.android.Code128;
import com.onbarcode.barcode.android.IBarcode;*/
import com.itextpdf.text.pdf.Barcode;

import srmicrosystems.cnote.Model.ManifestDetail;
import srmicrosystems.cnote.Model.ManifestItemDetails;
import srmicrosystems.cnote.R;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by saman_000 on 24-09-2016.
 */
public class ManifestPDF {
    Context context;
    public ManifestPDF(Context c)
    {
        context =c;
    }


    public static final String DOG = "src/main/resources/img/dog.bmp";

    public void PrintManifest(ManifestDetail MD,java.util.List<ManifestItemDetails> MID ){

        try{

            Document document = new Document();
            FileOutputStream filestrm = createPdf( MD);

            PdfWriter writer =  PdfWriter.getInstance(document, filestrm);
            document.setMargins((float) 0.5, (float) 0.5, (float) 0.5, (float) 0.5);
            document.open();
            document.addTitle("My first PDF");
            document.addSubject("Using iText");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Lars Vogel");
            document.addCreator("Lars Vogel");


            ContextCompat.getDrawable(context,R.drawable.sscpllogo1);

            Uri fileUri = Uri.parse("/storage/emulated/0/Download/SSCPL LOGO1.jpg");
            try {
                Image img = Image.getInstance(fileUri.getPath());
                img.setAlignment(Element.ALIGN_TOP);
                img.setWidthPercentage(20);
                img.setScaleToFitHeight(false);
                img.scaleAbsolute(500, 100);
                img.setDpi(50, 50);
                //  document.add(img);
            }
            catch(Exception exp){}

            //document.add(new Paragraph("Manifest"));
            Paragraph p = new Paragraph("");

            Font f=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK);

            p.setFont(f);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);


            p.setFont(f);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            //Paragraph p1 = new Paragraph("MANIFEST No:"+ MD.getManifestId());



            // document.add(new Paragraph("                                                                                                                                                                                                                                                                    "));

            Font f1 =      FontFactory.getFont(FontFactory.TIMES_ROMAN, 8f,Font.NORMAL);


            PdfPTable header = new PdfPTable(5);
            header.setWidthPercentage(100);

            PdfPCell c1 ;// = new PdfPCell(new Phrase("MANIFEST No:"+ MD.getManifestId()));
            // c1.setColspan(2);
//c1.setBorderWidth(0);

            Barcode128 bc = new Barcode128();
            bc.setCodeSet(Barcode128.Barcode128CodeSet.AUTO);
            bc.setCode(MD.getManifestId());
            bc.setStartStopText(true);
            bc.setAltText(MD.getManifestId());
            bc.setTextAlignment(1);


            c1= new PdfPCell(bc.createImageWithBarcode(writer.getDirectContent(),BaseColor.BLACK,BaseColor.GRAY));
            c1.setBorderWidth(0);
            c1.setColspan(2);


            header.addCell(c1);
            PdfPCell c2 = new PdfPCell();
            c2.setBorderWidth(0);
            header.addCell(c2);
            Date d = new Date();

            PdfPCell c3 = new PdfPCell(new Phrase("Date : " +d.toString()));
            c3.setColspan(2);
            c3.setBorderWidth(0);
            header.addCell(c3);

            PdfPCell c4 = new PdfPCell(new Phrase("Brach : " + "Surat"));
            c4.setColspan(2);
            c4.setBorder(0);
            header.addCell(c4);
            Font f2 =      FontFactory.getFont(FontFactory.TIMES_ROMAN, 20f,Font.BOLD);
            p = new Paragraph(new Phrase("Manifest",f2));

            PdfPCell c5 = new PdfPCell(p);
            c5.setFixedHeight(25);
            c5.setColspan(1);
            c5.setBorder(0);
            header.addCell(c5);

            PdfPCell c6 = new PdfPCell();
            c6.setBorder(0);
            c6.setColspan(2);
            header.addCell(c6);

            header.setSpacingBefore(10f);
            header.setSpacingAfter(1f);


            document.add(header);


            Canvas c = new Canvas();



            //MItems.ro;
            //MItems.setHeaderRows(1);
            PdfPTable table = new PdfPTable(12);

            table.setWidthPercentage(100);
            table.setSpacingBefore(1f);
            table.setSpacingAfter(1f);

            // first row
    /*PdfPCell cell = new PdfPCell(new Phrase("Manifest Details"));
    cell.setColspan(10);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(5.0f);
    cell.setBackgroundColor(new BaseColor(128,128,128));

    table.addCell(cell);*/
            Font hf =      FontFactory.getFont(FontFactory.TIMES_ROMAN, 8f,Font.NORMAL);
            hf.setColor(BaseColor.WHITE);
            PdfPCell cellSRNO = new PdfPCell(new Phrase("No",hf));
            cellSRNO.setBackgroundColor(BaseColor.BLACK);

            table.addCell(cellSRNO);

            PdfPCell cellCN= new PdfPCell(new Phrase("C Note No",hf));
            cellCN.setColspan(2);

            table.addCell(cellCN);

            PdfPCell cellshipper= new PdfPCell(new Phrase("Shipper",hf));
            cellshipper.setColspan(2);
            table.addCell(cellshipper);
            table.addCell("Origin");

            PdfPCell cellConsignee= new PdfPCell(new Phrase("Consignee",hf));
            cellConsignee.setColspan(2);
            table.addCell(cellConsignee);
            PdfPCell dest = new PdfPCell(new Phrase("Destination",hf));
            dest.setBackgroundColor(BaseColor.BLACK);
            table.addCell( dest );
            PdfPCell pcs =  new PdfPCell(new Phrase("PCS",hf));
            pcs.setBackgroundColor(BaseColor.BLACK);
            table.addCell(pcs);
            PdfPCell wgt =new PdfPCell(new Phrase("WGT",hf));
            wgt.setBackgroundColor(BaseColor.BLACK);;
            table.addCell(wgt);
            PdfPCell remarks = new PdfPCell(new Phrase("Remarks",hf));
            remarks.setBackgroundColor(BaseColor.BLACK);
            table.addCell(remarks);
            int Count =1;
            for (ManifestItemDetails tmp : MID        ) {

                table.addCell(getNormalCell( String.valueOf( Count),8));
                Count = Count +1;
                PdfPCell m1 ;//= new PdfPCell(new Phrase(tmp.getCNNumber()));
       /* testCODE128(c,tmp.getCNNumber());
        Bitmap b =Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565);
        c.setBitmap(b);*/
                Barcode128 bc1 = new Barcode128();
                bc1.setCodeSet(Barcode128.Barcode128CodeSet.AUTO);
                bc1.setCode(tmp.getCNNumber());
                bc1.setStartStopText(true);
                bc1.setAltText(tmp.getCNNumber());
                bc1.setTextAlignment(1);


                m1= new PdfPCell(bc1.createImageWithBarcode(writer.getDirectContent(),BaseColor.BLACK,BaseColor.GRAY));
                m1.setBorder(0);
                m1.setColspan(2);
                table.addCell(m1);


                PdfPCell m2 = getNormalCell(tmp.getShipperCompany(),8);
                m2.setColspan(2);
                table.addCell(m2);


                table.addCell(getNormalCell(tmp.getSourceCity(),8));
                PdfPCell m3 = getNormalCell(tmp.getConsigneeCompany(),8);
                m3.setColspan(2);

                table.addCell(m3);

                table.addCell(getNormalCell(tmp.getDestCity(),8));
                if (tmp.getPackageNo() >0 ){
                    table.addCell(getNormalCell(tmp.getPackageNo().toString(),8));}
                if (tmp.getConsignmentWeight()>0){
                    table.addCell(getNormalCell(tmp.getConsignmentWeight().toString(),8));}
                table.addCell("");
            }
    /*for (int i = 0; i < MID.size(); i++) {
        table.addCell(MID.);
        table.addCell("time" + i);
        table.addCell("source" + i);
        table.addCell("destination" + i);
        table.addCell("extension" + i);
        table.addCell("trunk" + i);
        table.addCell("dur" + i);
        table.addCell("toc" + i);
        table.addCell("callcost" + i);

    }*/
            document.add(table);

            document.close();

            PrintADP printADP = new PrintADP();
            printADP.Print(context,GetPath(MD),MD.getManifestId());


        }
        catch (Exception ex)
        {

            String s= ex.getMessage();
            //throw ex;
        }

    }

    private FileOutputStream  createPdf(ManifestDetail MD) throws FileNotFoundException {



        File myFile = new File(GetPath(MD));

        FileOutputStream output = new FileOutputStream(myFile);
        return  output;

    }

    private String GetPath(ManifestDetail MD)
    {
        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i("sr", "Pdf Directory created");
        }

        //Create time stamp
        String path =       pdfFolder +  MD.getManifestId() + ".pdf";
        return  path;

    }

    public static PdfPCell getNormalCell(String string,  float size)
    {

        Font f1 =      FontFactory.getFont(FontFactory.TIMES_ROMAN, 8f,Font.NORMAL);

        return new PdfPCell(new Phrase(string,f1));
    }

}