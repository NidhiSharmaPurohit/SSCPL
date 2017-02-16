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
    public partial class CashMemo : Form
    {
        private CenterMaster[] _CenterMasterList;
        private Company[] _CompanyLsit;
        private Rates[] _RateList;
        

        public CashMemo()
        {
            InitializeComponent();
        }

        private void CashMemo_Load(object sender, EventArgs e)
        {
            _CenterMasterList = CachedDataManager.GetCachedCenterMaster();
            _CompanyLsit = CachedDataManager.GetCachedCompany();
            _RateList = CachedDataManager.GetCachedRate();
           

            InitControlsValues();
            setManifestItemGridProperty();
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




            Dictionary<int, string> ShippercompanySource = new Dictionary<int, string>();

            foreach (Company cmp in _CompanyLsit)
            {
                ShippercompanySource.Add(cmp.CompanyId, cmp.CompanyName + "  " + cmp.CompanyCity);
            }

            this.ShipComCbox.DataSource = new BindingSource(ShippercompanySource, null);
            this.ShipComCbox.DisplayMember = "Value";
            this.ShipComCbox.ValueMember = "Key";
            this.ShipComCbox.SelectedIndex = -1;
            this.ShipComCbox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.ShipComCbox.AutoCompleteSource = AutoCompleteSource.ListItems;

        }

        private void setManifestItemGridProperty()
        {
            dataGridView1.AutoGenerateColumns = false;
            dataGridView1.ColumnCount = 14;

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

            dataGridView1.Columns[5].Name = "Rate";
            dataGridView1.Columns[5].HeaderText = "Rate per KG";
            dataGridView1.Columns[5].DataPropertyName = "Rate";
            dataGridView1.Columns[5].Width = 90;
            dataGridView1.Columns[5].ReadOnly = false;

            dataGridView1.Columns[6].Name = "Total";
            dataGridView1.Columns[6].HeaderText = "Total";
            dataGridView1.Columns[6].DataPropertyName = "Total";
            dataGridView1.Columns[6].Width = 70;
            dataGridView1.Columns[6].ReadOnly = true;

            dataGridView1.Columns[7].Name = "SourceCity";
            dataGridView1.Columns[7].HeaderText = "SourceCity";
            dataGridView1.Columns[7].DataPropertyName = "SourceCity";
            dataGridView1.Columns[7].ReadOnly = true;
            dataGridView1.Columns[7].Width = 75;

            dataGridView1.Columns[8].Name = "DestCity";
            dataGridView1.Columns[8].HeaderText = "DesitnationCity";
            dataGridView1.Columns[8].DataPropertyName = "DestCity";
            dataGridView1.Columns[8].ReadOnly = true;
            dataGridView1.Columns[8].Width = 85;

            dataGridView1.Columns[9].Name = "PayMode";
            dataGridView1.Columns[9].HeaderText = "PayMode";
            dataGridView1.Columns[9].DataPropertyName = "PayMode";
            dataGridView1.Columns[9].ReadOnly = true;
            dataGridView1.Columns[9].Width = 65;

            dataGridView1.Columns[10].Name = "TransportMode";
            dataGridView1.Columns[10].HeaderText = "TransportMode";
            dataGridView1.Columns[10].DataPropertyName = "TransportMode";
            dataGridView1.Columns[10].ReadOnly = true;
            dataGridView1.Columns[10].Width = 85;


            dataGridView1.Columns[11].Name = "ShipperCompany";
            dataGridView1.Columns[11].HeaderText = "ShipperCompany";
            dataGridView1.Columns[11].DataPropertyName = "ShipperCompany";
            dataGridView1.Columns[11].ReadOnly = true;

            dataGridView1.Columns[12].Name = "ConsigneeCompany";
            dataGridView1.Columns[12].HeaderText = "ConsigneeCompany";
            dataGridView1.Columns[12].DataPropertyName = "ConsigneeCompany";
            dataGridView1.Columns[12].ReadOnly = true;
            dataGridView1.Columns[12].Width = 110;

            dataGridView1.Columns[13].Name = "Status";
            dataGridView1.Columns[13].HeaderText = "Status";
            dataGridView1.Columns[13].DataPropertyName = "Status";
            dataGridView1.Columns[13].Width = 110;

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void DataGridView1_CellValueChanged(object sender, DataGridViewCellEventArgs e)
        {
           
        }

        private void button1_Click(object sender, EventArgs e)
        {
            double total = 0;
            CNNotesDetails[] result;
            result = new DataManager().GetCNNoteDetailForComapnyInDateRange(dateTimePicker1.Value, dateTimePicker2.Value, ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key.ToString());
            foreach (CNNotesDetails cdn in result)
            {
                var rates = from rate in _RateList
                            where (cdn.ShipperCompId == rate.CompanyId) && (cdn.CenterID == rate.CenterId) && (cdn.ModeID == rate.ModeID)
                            select rate;

                if (rates != null)
                {
                    if (rates.Count() == 1)
                    {
                        cdn.Rate = rates.ElementAt(0).Rate;
                        Ratetxt.Text = rates.ElementAt(0).Rate.ToString();
                    }
                    else
                    {
                        foreach (Rates r in rates)
                        {

                        }
                    }
                }

                cdn.TOTAL = cdn.ConsignmentWeight * cdn.Rate;
                total = total + cdn.TOTAL.Value;
            }
            TotalAmounttxt.Text = total.ToString();
            finalamounttxt.Text = total.ToString();
            dataGridView1.DataSource = result;
            
            button1.Enabled = false;
        }

        private void button2_Click(object sender, EventArgs e)
        {

            //Inserting Invoice Record First
            Invoice inv = new Invoice();
            inv.Amount = double.Parse(TotalAmounttxt.Text);
            inv.CenterId = ((KeyValuePair<int, string>)centerlist.SelectedItem).Key;
            inv.CompanyId = ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key;
            if (string.IsNullOrEmpty(Discounttxt.Text))
            {
                inv.Discount = 0;
            }
            else
            {
                inv.Discount = double.Parse(Discounttxt.Text);
            }
            finalamounttxt.Text = TotalAmounttxt.Text;
            inv.FromDate = new DateTime(dateTimePicker1.Value.Date.Year, dateTimePicker1.Value.Date.Month, dateTimePicker1.Value.Date.Day);
            inv.ToDate = new DateTime(dateTimePicker2.Value.Date.Year, dateTimePicker2.Value.Date.Month, dateTimePicker2.Value.Date.Day);

            inv.InvoiceDate = new DateTime(DateTime.Now.Date.Year, DateTime.Now.Date.Month, DateTime.Now.Date.Day).Date;

            inv.InvoiceNum = DateTime.Now.Date.Day.ToString().PadLeft(2, '0') + DateTime.Now.Date.Month.ToString().PadLeft(2, '0') + DateTime.Now.Date.Year.ToString().PadLeft(4, '0')
                + DateTime.Now.Hour.ToString().PadLeft(2, '0') + DateTime.Now.Minute.ToString().PadLeft(2, '0') + DateTime.Now.Second.ToString().PadLeft(2, '0')
                + (from company in _CompanyLsit
                   where company.CompanyId == ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key
                   select company.CompanyName + " " + company.CompanyCity).FirstOrDefault();
            int companyid = ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key;
            int centerid = ((KeyValuePair<int, string>)centerlist.SelectedItem).Key;
            inv.RateId = (from rte in _RateList
                          where companyid == rte.CompanyId && centerid == rte.CenterId
                          select rte.RateId).FirstOrDefault();
            inv.Statuss = "Active";
            inv.TotalAmount = double.Parse(finalamounttxt.Text);

            new DataManager().AddInvoiceRecord(inv);





            int numOfRows = dataGridView1.Rows.Count;
            for (int row = 0; row < numOfRows; row++)
            {
                //Inserting InvoiceItems Now
                InvoiceItem invoiceitem = new InvoiceItem();
                invoiceitem.InvoiceNum = inv.InvoiceNum;
                invoiceitem.Amount = (double)(dataGridView1["Total", row].Value);
                invoiceitem.CNNumber = (dataGridView1["CNNumber", row].Value).ToString();
                invoiceitem.RateId = (from rte in _RateList
                                      where companyid == rte.CompanyId && centerid == rte.CenterId
                                      select rte.RateId).FirstOrDefault();
                invoiceitem.Weight = (double)(dataGridView1["ConsignmentWeight", row].Value);

                new DataManager().AddInvoiceItemRecord(invoiceitem);

            }



            CashMemoDisplayAndPrint frm = new CashMemoDisplayAndPrint();
            frm.InvoiceNum = inv.InvoiceNum;
            frm.Show();

        }
    }
}
