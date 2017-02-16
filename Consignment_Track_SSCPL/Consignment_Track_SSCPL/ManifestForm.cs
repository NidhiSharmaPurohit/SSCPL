using iTextSharp.text;
using iTextSharp.text.pdf;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Consignment_Track_SSCPL
{
    public partial class ManifestForm : Form
    {
        private CenterMaster[] _CenterMasterList;
        private City[] _CityList;
        private CarrierType[] _VehicleList;

        private String _ManifestId;

        private BindingSource ManifestItemBindingSource = new BindingSource();

        public ManifestForm()
        {
            InitializeComponent();
        }

        private void ManifestForm_Load(object sender, EventArgs e)
        {
            _CenterMasterList = CachedDataManager.GetCachedCenterMaster();
            _CityList = CachedDataManager.GetCachedCity();
            _VehicleList = CachedDataManager.GetCachedCarrierType();


            InitControlsValues();

            setManifestItemGridProperty();
            dataGridView2.Visible = false;
        }

        private void InitControlsValues()
        {
            Dictionary<int, string> CenterListcomboSource = new Dictionary<int, string>();

            foreach (CenterMaster cm in _CenterMasterList)
            {
                CenterListcomboSource.Add(cm.CenterId, cm.Name + "  " + cm.City);
            }
            this.centerlist.DataSource = new BindingSource(CenterListcomboSource, null);
            this.centerlist.DisplayMember = "Value";
            this.centerlist.ValueMember = "Key";
            this.centerlist.SelectedIndex = 0;
            this.centerlist.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.centerlist.AutoCompleteSource = AutoCompleteSource.ListItems;




            Dictionary<int, string> FromCitySource = new Dictionary<int, string>();
            Dictionary<int, string> ToCityDestination = new Dictionary<int, string>();
            foreach (City city in _CityList)
            {
                FromCitySource.Add(city.CityId, city.CityName + "  " + city.CityCode);
                ToCityDestination.Add(city.CityId, city.CityName + "  " + city.CityCode);

            }

            this.frmCityCbox.DataSource = new BindingSource(FromCitySource, null);
            this.frmCityCbox.DisplayMember = "Value";
            this.frmCityCbox.ValueMember = "Key";
            this.frmCityCbox.SelectedIndex = -1;
            this.frmCityCbox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.frmCityCbox.AutoCompleteSource = AutoCompleteSource.ListItems;

            this.ToCityCbox.DataSource = new BindingSource(ToCityDestination, null);
            this.ToCityCbox.DisplayMember = "Value";
            this.ToCityCbox.ValueMember = "Key";
            this.ToCityCbox.SelectedIndex = -1;
            this.ToCityCbox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.ToCityCbox.AutoCompleteSource = AutoCompleteSource.ListItems;

            Dictionary<int, string> vehicelsource = new Dictionary<int, string>();
            foreach (CarrierType v in _VehicleList)
            {
                vehicelsource.Add(v.Id, v.CNumber + "  " + v.Typee + "  " + v.Model);

            }

            this.VehicleCBox.DataSource = new BindingSource(vehicelsource, null);
            this.VehicleCBox.DisplayMember = "Value";
            this.VehicleCBox.ValueMember = "Key";
            this.VehicleCBox.SelectedIndex = 0;
            this.VehicleCBox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.VehicleCBox.AutoCompleteSource = AutoCompleteSource.ListItems;

            
            int i = 0;
            
            foreach (City city in _CityList)
            {
                i = i + 1;
                checkedListBox1.Items.Add(city.CityName);
                if(i % 10 ==0)
                {
                    adjustListSize(checkedListBox1);
                }
            }
        }

        private static void adjustListSize(ListBox lst)
        {
            lst.Height += 20;
        }

        private void setManifestItemGridProperty()
        {
            dataGridView2.AutoGenerateColumns = false;
            dataGridView2.ColumnCount = 12;

            dataGridView2.Columns[0].Name = "CNNumber";
            dataGridView2.Columns[0].HeaderText = "CNNumber";
            dataGridView2.Columns[0].DataPropertyName = "CNNumber";
            dataGridView2.Columns[0].ReadOnly = true;
            

            dataGridView2.Columns[1].Name = "SourceCity";
            dataGridView2.Columns[1].HeaderText = "SourceCity";
            dataGridView2.Columns[1].DataPropertyName = "SourceCity";
            dataGridView2.Columns[1].Width = 70;
            dataGridView2.Columns[1].ReadOnly = true;

            dataGridView2.Columns[2].Name = "DestCity";
            dataGridView2.Columns[2].HeaderText = "DesitnationCity";
            dataGridView2.Columns[2].DataPropertyName = "DestCity";
            dataGridView2.Columns[2].ReadOnly = true;
            dataGridView2.Columns[2].Width = 85;

            dataGridView2.Columns[3].Name = "PackageNo";
            dataGridView2.Columns[3].HeaderText = "NumOfPackages";
            dataGridView2.Columns[3].DataPropertyName = "PackageNo";
            dataGridView2.Columns[3].ReadOnly = true;

            dataGridView2.Columns[4].Name = "PayMode";
            dataGridView2.Columns[4].HeaderText = "PayMode";
            dataGridView2.Columns[4].DataPropertyName = "PayMode";
            dataGridView2.Columns[4].ReadOnly = true;
            dataGridView2.Columns[4].Width = 65;

            dataGridView2.Columns[5].Name = "TransportMode";
            dataGridView2.Columns[5].HeaderText = "TransportMode";
            dataGridView2.Columns[5].DataPropertyName = "TransportMode";
            dataGridView2.Columns[5].ReadOnly = true;
            dataGridView2.Columns[5].Width = 85;


            dataGridView2.Columns[6].Name = "ShipperCompany";
            dataGridView2.Columns[6].HeaderText = "ShipperCompany";
            dataGridView2.Columns[6].DataPropertyName = "ShipperCompany";
            dataGridView2.Columns[6].ReadOnly = true;

            dataGridView2.Columns[7].Name = "ShipperCity";
            dataGridView2.Columns[7].HeaderText = "ShipperCity";
            dataGridView2.Columns[7].DataPropertyName = "ShipperCity";
            dataGridView2.Columns[7].ReadOnly = true;
            dataGridView2.Columns[7].Width = 77;


            dataGridView2.Columns[8].Name = "ConsigneeCompany";
            dataGridView2.Columns[8].HeaderText = "ConsigneeCompany";
            dataGridView2.Columns[8].DataPropertyName = "ConsigneeCompany";
            dataGridView2.Columns[8].ReadOnly = true;
            dataGridView2.Columns[8].Width = 110;


            dataGridView2.Columns[9].Name = "ConsigneeCity";
            dataGridView2.Columns[9].HeaderText = "ConsigneeCity";
            dataGridView2.Columns[9].DataPropertyName = "ConsigneeCity";
            dataGridView2.Columns[9].ReadOnly = true;
            dataGridView2.Columns[9].Width = 87;

            dataGridView2.Columns[10].Name = "BookingDate";
            dataGridView2.Columns[10].HeaderText = "BookingDate";
            dataGridView2.Columns[10].DataPropertyName = "BookingDate";
            dataGridView2.Columns[10].ReadOnly = true;
            dataGridView2.Columns[10].Width = 78;

            dataGridView2.Columns[11].Name = "ManifestItemLoadedQuantity";
            dataGridView2.Columns[11].HeaderText = "LoadedQuantity";
            dataGridView2.Columns[11].DataPropertyName = "ManifestItemLoadedQuantity";
            dataGridView2.Columns[11].Width = 90;
            dataGridView2.Columns[11].ReadOnly = true;

        }

        private void button1_Click(object sender, EventArgs e)
        {
            button1.Enabled = false;
            dataGridView2.Visible = true;
            string s = string.Empty;

           int checkeditemcount = 0;
           foreach(object o in checkedListBox1.CheckedItems)
            {
                checkeditemcount = checkeditemcount + 1;
                int i = (from city in _CityList
                         where city.CityName == o.ToString()
                         select city.CityId).FirstOrDefault();

                
                    s = s + i.ToString() + ",";   
            }

            s = s + ((KeyValuePair<int, string>)ToCityCbox.SelectedItem).Key;

            Manifest mf = new Manifest();
            mf.CarrierTypeID = ((KeyValuePair<int, string>)VehicleCBox.SelectedItem).Key;
            mf.CenterID = ((KeyValuePair<int, string>)centerlist.SelectedItem).Key;
            mf.ManifestDate = new DateTime(dateTimePicker1.Value.Date.Year, dateTimePicker1.Value.Date.Month, dateTimePicker1.Value.Date.Day);
            mf.SourceCityID = ((KeyValuePair<int, string>)frmCityCbox.SelectedItem).Key;
            mf.TargetCityID = ((KeyValuePair<int, string>)ToCityCbox.SelectedItem).Key;
            mf.ManifestId = DateTime.Now.Date.Day.ToString().PadLeft(2, '0') + DateTime.Now.Date.Month.ToString().PadLeft(2, '0') + DateTime.Now.Date.Year.ToString().PadLeft(4, '0')
                + DateTime.Now.Hour.ToString().PadLeft(2, '0') + DateTime.Now.Minute.ToString().PadLeft(2, '0') + DateTime.Now.Second.ToString().PadLeft(2, '0')
                + (from city in _CityList
                   where city.CityId == ((KeyValuePair<int, string>)ToCityCbox.SelectedItem).Key
                   select city.CityName).FirstOrDefault()

                   + (from vehicle in _VehicleList
                      where vehicle.Id == ((KeyValuePair<int, string>)VehicleCBox.SelectedItem).Key
                      select vehicle.Model + " " + vehicle.Typee).FirstOrDefault();

            new DataManager().AddManifest(mf);
            _ManifestId = mf.ManifestId;
            ManifestDetail[] mfd = new DataManager().GetManifestDetailById(mf.ManifestId);
            dataGridView1.DataSource = mfd.Select(m => new { ManifestId = m.ManifestId, SourceCity = m.SourceCity, DestinationCity = m.DestCity, CarrierType  = m.CarrierType , Capacity  = m.Capacity,
                Model = m.Model, ModelID = m.ModelID, CarrierNumber = m.CarrierNumber, ManifestDate = m.ManifestDate, CenterName = m.CenterName, CenterCity = m.CenterCity
            }).ToArray();

            dataGridView1.Columns[0].Width = 200;

            Application.DoEvents();
            CNNotesDetailsForManifest[] result = new DataManager().GetCNNotesForManifest(frmdateTimePicker.Value, TodateTimePicker.Value, s);

            

            ManifestItemBindingSource.DataSource = result;

            dataGridView2.DataSource = ManifestItemBindingSource;

            if (dataGridView2.ColumnCount == 12)
            {
                DataGridViewTextBoxColumn textboxColumn = new DataGridViewTextBoxColumn();
                textboxColumn.HeaderText = "UploadedQuantity";
                textboxColumn.Name = "UploadedQuantity";
                textboxColumn.DataPropertyName = "UploadedQuantity";
                textboxColumn.Width = 100;
                dataGridView2.Columns.Add(textboxColumn);

                DataGridViewButtonColumn UpdateButton = new DataGridViewButtonColumn();
                UpdateButton.HeaderText = "Update";
                UpdateButton.DataPropertyName = "Update";
                UpdateButton.Text = "Update";
                UpdateButton.Width = 60;
                UpdateButton.UseColumnTextForButtonValue = true;
                dataGridView2.Columns.Add(UpdateButton);

                DataGridViewButtonColumn DoneAndLoaded = new DataGridViewButtonColumn();
                DoneAndLoaded.HeaderText = "Loaded";
                DoneAndLoaded.DataPropertyName = "Loaded";
                DoneAndLoaded.Text = "Loaded";
                DoneAndLoaded.UseColumnTextForButtonValue = true;
                DoneAndLoaded.Width = 60;
                dataGridView2.Columns.Add(DoneAndLoaded);
            }
            Application.DoEvents();

        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

            if (e.ColumnIndex == 13)
            {
                MessageBox.Show("Update is clicked");

                ManifestItem mi = new ManifestItem();
                mi.ManifestId = _ManifestId;

                mi.CNoteNo = dataGridView2["CNNumber", e.RowIndex].Value.ToString();
                mi.ItemDate = dateTimePicker1.Value;
                if (dataGridView2["UploadedQuantity", e.RowIndex].Value == null)
                {
                    mi.LoadedQuantity = 0;
                    if (dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value == null)
                    {
                        dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value = 0;
                    }
                }
                else
                {
                    mi.LoadedQuantity = int.Parse(dataGridView2["UploadedQuantity", e.RowIndex].Value.ToString());
                    if (dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value == null)
                    {
                        dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value = mi.LoadedQuantity;
                    }
                    else
                    {
                        dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value = mi.LoadedQuantity + int.Parse(dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value.ToString());
                    }

                }

                new DataManager().AddManifestItem(mi);

                Application.DoEvents();
                dataGridView2["UploadedQuantity", e.RowIndex].Value = null;
            }
            else
            {
                MessageBox.Show("Loaded is clicked");

                ManifestItem mi = new ManifestItem();
                mi.ManifestId = _ManifestId;

                mi.CNoteNo = dataGridView2["CNNumber", e.RowIndex].Value.ToString();
                mi.ItemDate = dateTimePicker1.Value;
                
                mi.LoadedQuantity = int.Parse(dataGridView2["PackageNo", e.RowIndex].Value.ToString());
                    
                dataGridView2["ManifestItemLoadedQuantity", e.RowIndex].Value = mi.LoadedQuantity;
                dataGridView2["UploadedQuantity", e.RowIndex].Value = null;
                    
                 new DataManager().AddManifestItem(mi);

                Application.DoEvents();
                
            }


            

        }

        private void button2_Click(object sender, EventArgs e)
        {
            ManifestDetail[] manifest = new DataManager().GetManifestDetailById("27112016022433MUMBAITata Truck");
            ManifestItemDetail[] manifestitems = new DataManager().GetManifestItemDetailByManifestId("27112016022433MUMBAITata Truck");

            Document doc = new Document();

            PdfWriter.GetInstance(doc, new FileStream("Manifest "  + manifest[0].ManifestId + ".pdf", FileMode.Create, FileAccess.ReadWrite));
            doc.Open();

            iTextSharp.text.Image Img = iTextSharp.text.Image.GetInstance("sscpl.jpg");

            doc.Add(Img);

            Paragraph p = new Paragraph("Manifest Details", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.BOLD, iTextSharp.text.Font.BOLD, BaseColor.CYAN));
            doc.Add(p);

            Paragraph p1 = new Paragraph("Manifest Number : " + manifest[0].ManifestId, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL));
            doc.Add(p1);

            Paragraph p2 = new Paragraph("Manifest Date : " + manifest[0].ManifestDate, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p2);

            Paragraph p3 = new Paragraph("CariierType : " + manifest[0].CarrierType, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p3);

            Paragraph p4 = new Paragraph("VehicleNumber : " + manifest[0].CarrierNumber, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p4);

            Paragraph p5 = new Paragraph("Center Name  : " + manifest[0].CenterName + "  " + manifest[0].CenterCity, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p5);

            Paragraph p6 = new Paragraph("FromCity : " + manifest[0].SourceCity, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p6);

            Paragraph p7 = new Paragraph("ToCity : " + manifest[0].DestCity, new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, iTextSharp.text.Font.DEFAULTSIZE, iTextSharp.text.Font.NORMAL, BaseColor.BLUE));
            doc.Add(p7);

            PdfPTable table = new PdfPTable(8);
            PdfPCell cell = new PdfPCell(new Paragraph("Manifest Items", new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 12, iTextSharp.text.Font.NORMAL, BaseColor.BLUE)));
            cell.Colspan = 8;
            table.AddCell(cell);
            iTextSharp.text.Font tablefont = new iTextSharp.text.Font(iTextSharp.text.Font.FontFamily.COURIER, 7, iTextSharp.text.Font.NORMAL, BaseColor.BLACK);

            table.AddCell(new PdfPCell(new Paragraph("BookingDate", tablefont)));
            PdfPCell p11 = new PdfPCell(new Paragraph("AwbNo", tablefont));
            table.AddCell(p11);
            table.AddCell(new PdfPCell(new Paragraph("SourceCity", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("DestinationCity", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Pcs", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Weight", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Shipper Company", tablefont)));
            table.AddCell(new PdfPCell(new Paragraph("Consignee Company", tablefont)));

            foreach (ManifestItemDetail it in manifestitems)
            {
                table.AddCell(new PdfPCell(new Paragraph(it.BookingDate.ToShortDateString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.CNNumber, tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.SourceCity, tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.DestCity, tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.PackageNo.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.ConsignmentWeight.Value.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.ShipperCompany.ToString(), tablefont)));
                table.AddCell(new PdfPCell(new Paragraph(it.ConsigneeCompany.ToString(), tablefont)));
            }


            table.WidthPercentage = 100;

            table.SpacingBefore = 15f;
            table.SpacingAfter = 15f;

            


            doc.Add(table);
           

            doc.Close();

            

            try
            {
                System.Diagnostics.Process.Start("Manifest " + manifest[0].ManifestId + ".pdf");
            }
            catch (Exception exp)
            {

            }

            
        }
    }

    public class CityListItem
    {
        public int CityId { get; set; }
        public string CityName { get; set; }
        public string CityCode { get; set; }
    }
}
