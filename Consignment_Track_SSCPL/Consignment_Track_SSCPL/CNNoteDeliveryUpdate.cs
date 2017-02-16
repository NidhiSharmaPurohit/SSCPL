using Microsoft.Http;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Consignment_Track_SSCPL
{
    public partial class CNNoteDeliveryUpdate : Form
    {
        public CNNoteDeliveryUpdate()
        {
            InitializeComponent();
        }

        private void GetCNotesbutton_Click(object sender, EventArgs e)
        {
           
            dataGridView1.Visible = true;
            dataGridView2.Visible = true;
            dataGridView3.Visible = true;
            dataGridView4.Visible = true;
            CNNotesFullDetails[] result = new DataManager().GetCNNotesFullDetails(this.cnnotetextbox.Text);
            dataGridView1.DataSource = result;
            dataGridView2.DataSource = result;
            dataGridView3.DataSource = result;
            dataGridView4.DataSource = result;

            string imageurl = string.Format(new DataManager().getCNNoteSignedImageGetBaseUrl(), cnnotetextbox.Text + ".jpg");

            Image img = new Bitmap(new HttpClient().Get(imageurl).Content.ReadAsStream());
            Image img2 = new Bitmap(img, 150, 150);
            pictureBox1.Image = img2;
                  
        }

        private void setGridProperty()
        {
            dataGridView1.AutoGenerateColumns = false;
            dataGridView1.ColumnCount = 5;

            dataGridView2.AutoGenerateColumns = false;
            dataGridView2.ColumnCount = 5;

            dataGridView3.AutoGenerateColumns = false;
            dataGridView3.ColumnCount = 5;

            dataGridView4.AutoGenerateColumns = false;
            dataGridView4.ColumnCount = 5;

            dataGridView1.Columns[0].Name = "CNNumber";
            dataGridView1.Columns[0].HeaderText = "CNNumber";
            dataGridView1.Columns[0].DataPropertyName = "CNNumber";
            dataGridView1.Columns[0].ReadOnly = true;

            dataGridView1.Columns[1].Name = "BookingDate";
            dataGridView1.Columns[1].HeaderText = "BookingDate";
            dataGridView1.Columns[1].DataPropertyName = "BookingDate";
            dataGridView1.Columns[1].ReadOnly = true;
            dataGridView1.Columns[1].Width = 100;

            dataGridView1.Columns[2].Name = "PackageNo";
            dataGridView1.Columns[2].HeaderText = "NumOfPackages";
            dataGridView1.Columns[2].DataPropertyName = "PackageNo";
            dataGridView1.Columns[2].ReadOnly = true;

            dataGridView1.Columns[3].Name = "ActualWeight";
            dataGridView1.Columns[3].HeaderText = "ActualWeight";
            dataGridView1.Columns[3].DataPropertyName = "ActualWeight";
            dataGridView1.Columns[3].ReadOnly = true;
            dataGridView1.Columns[3].Width = 90;


            dataGridView1.Columns[4].Name = "ConsignmentWeight";
            dataGridView1.Columns[4].HeaderText = "ConsignmentWeight";
            dataGridView1.Columns[4].DataPropertyName = "ConsignmentWeight";
            dataGridView1.Columns[4].ReadOnly = true;
            dataGridView1.Columns[4].Width = 110;

            dataGridView2.Columns[0].Name = "SourceCity";
            dataGridView2.Columns[0].HeaderText = "SourceCity";
            dataGridView2.Columns[0].DataPropertyName = "SourceCity";
            dataGridView2.Columns[0].ReadOnly = true;
            dataGridView2.Columns[0].Width = 75;

            dataGridView2.Columns[1].Name = "DestCity";
            dataGridView2.Columns[1].HeaderText = "DesitnationCity";
            dataGridView2.Columns[1].DataPropertyName = "DestCity";
            dataGridView2.Columns[1].ReadOnly = true;
            dataGridView2.Columns[1].Width = 85;

            dataGridView2.Columns[2].Name = "PayMode";
            dataGridView2.Columns[2].HeaderText = "PayMode";
            dataGridView2.Columns[2].DataPropertyName = "PayMode";
            dataGridView2.Columns[2].ReadOnly = true;
            dataGridView2.Columns[2].Width = 65;

            dataGridView2.Columns[3].Name = "TransportMode";
            dataGridView2.Columns[3].HeaderText = "TransportMode";
            dataGridView2.Columns[3].DataPropertyName = "TransportMode";
            dataGridView2.Columns[3].ReadOnly = true;
            dataGridView2.Columns[3].Width = 85;


            dataGridView2.Columns[4].Name = "ShipperCompany";
            dataGridView2.Columns[4].HeaderText = "ShipperCompany";
            dataGridView2.Columns[4].DataPropertyName = "ShipperCompany";
            dataGridView2.Columns[4].ReadOnly = true;

            dataGridView3.Columns[0].Name = "ConsigneeCompany";
            dataGridView3.Columns[0].HeaderText = "ConsigneeCompany";
            dataGridView3.Columns[0].DataPropertyName = "ConsigneeCompany";
            dataGridView3.Columns[0].ReadOnly = true;
            dataGridView3.Columns[0].Width = 110;

            dataGridView3.Columns[1].Name = "ServiceTax";
            dataGridView3.Columns[1].HeaderText = "Tax";
            dataGridView3.Columns[1].DataPropertyName = "ServiceTax";
            dataGridView3.Columns[1].Width = 100;

            dataGridView3.Columns[2].Name = "TOTAL";
            dataGridView3.Columns[2].HeaderText = "TOTAL";
            dataGridView3.Columns[2].DataPropertyName = "TOTAL";
            dataGridView3.Columns[2].Width = 100;

            dataGridView3.Columns[3].Name = "ShipperCity";
            dataGridView3.Columns[3].HeaderText = "ShipperCity";
            dataGridView3.Columns[3].DataPropertyName = "ShipperCity";
            dataGridView3.Columns[3].Width = 100;

            dataGridView3.Columns[4].Name = "ConsigneeCity";
            dataGridView3.Columns[4].HeaderText = "ConsigneeCity";
            dataGridView3.Columns[4].DataPropertyName = "ConsigneeCity";
            dataGridView3.Columns[4].Width = 100;

            dataGridView4.Columns[0].Name = "CenterName";
            dataGridView4.Columns[0].HeaderText = "CenterName";
            dataGridView4.Columns[0].DataPropertyName = "CenterName";
            dataGridView4.Columns[0].Width = 100;

            dataGridView4.Columns[1].Name = "CenterCity";
            dataGridView4.Columns[1].HeaderText = "CenterCity";
            dataGridView4.Columns[1].DataPropertyName = "CenterCity";
            dataGridView4.Columns[1].Width = 100;

            dataGridView4.Columns[2].Name = "CenterAddress";
            dataGridView4.Columns[2].HeaderText = "CenterAddress";
            dataGridView4.Columns[2].DataPropertyName = "CenterAddress";
            dataGridView4.Columns[2].Width = 100;

        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void CNNoteDeliveryUpdate_Load(object sender, EventArgs e)
        {
            setGridProperty();
        }
    }
}
