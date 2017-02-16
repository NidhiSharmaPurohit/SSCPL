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
    public partial class ActivePaymentForInvoice : Form
    {
        private CenterMaster[] _CenterMasterList;
        private Company[] _CompanyLsit;
        private PackagingMode[] _PaymentType;

        public ActivePaymentForInvoice()
        {
            InitializeComponent();
        }

        private void ActivePaymentForInvoice_Load(object sender, EventArgs e)
        {
            _CenterMasterList = CachedDataManager.GetCachedCenterMaster();
            _CompanyLsit = CachedDataManager.GetCachedCompany();
            _PaymentType = CachedDataManager.GetCachedPackagingMode();
            InitControlsValues();
            setActiveInvoiceGridProperty();

        }

        private void setActiveInvoiceGridProperty()
        {
            dataGridView1.AutoGenerateColumns = false;
            dataGridView1.ColumnCount = 7;

            dataGridView1.Columns[0].Name = "InvoiceNum";
            dataGridView1.Columns[0].HeaderText = "InvoiceNum";
            dataGridView1.Columns[0].DataPropertyName = "InvoiceNum";
            dataGridView1.Columns[0].ReadOnly = true;
            dataGridView1.Columns[0].Width = 200;

            dataGridView1.Columns[1].Name = "Amount";
            dataGridView1.Columns[1].HeaderText = "Amount";
            dataGridView1.Columns[1].DataPropertyName = "Amount";
            dataGridView1.Columns[1].ReadOnly = true;
            dataGridView1.Columns[1].Width = 100;

            dataGridView1.Columns[2].Name = "Discount";
            dataGridView1.Columns[2].HeaderText = "Discount";
            dataGridView1.Columns[2].DataPropertyName = "Discount";
            dataGridView1.Columns[2].ReadOnly = true;

            dataGridView1.Columns[3].Name = "TotalAmount";
            dataGridView1.Columns[3].HeaderText = "TotalAmount";
            dataGridView1.Columns[3].DataPropertyName = "TotalAmount";
            dataGridView1.Columns[3].ReadOnly = true;
            dataGridView1.Columns[3].Width = 100;


            dataGridView1.Columns[4].Name = "InvoiceDate";
            dataGridView1.Columns[4].HeaderText = "InvoiceDate";
            dataGridView1.Columns[4].DataPropertyName = "InvoiceDate";
            dataGridView1.Columns[4].ReadOnly = true;
            dataGridView1.Columns[4].Width = 100;

            dataGridView1.Columns[5].Name = "FromDate";
            dataGridView1.Columns[5].HeaderText = "FromDate";
            dataGridView1.Columns[5].DataPropertyName = "FromDate";
            dataGridView1.Columns[5].Width = 100;
            dataGridView1.Columns[5].ReadOnly = false;

            dataGridView1.Columns[6].Name = "ToDate";
            dataGridView1.Columns[6].HeaderText = "ToDate";
            dataGridView1.Columns[6].DataPropertyName = "ToDate";
            dataGridView1.Columns[6].Width = 100;
            dataGridView1.Columns[6].ReadOnly = true;

            if (dataGridView1.Columns.Count == 7)
            {
                DataGridViewLinkColumn Editlink = new DataGridViewLinkColumn();
                Editlink.UseColumnTextForLinkValue = true;
                Editlink.HeaderText = "Click to View Payment Details";
                Editlink.DataPropertyName = "lnkColumn";
                Editlink.LinkBehavior = LinkBehavior.SystemDefault;
                Editlink.Text = "Payment Details";
                Editlink.Width = 150;
                dataGridView1.Columns.Add(Editlink);
            }

            

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

            Dictionary<int, string> PaymentModeSource = new Dictionary<int, string>();

            foreach (PackagingMode pm in _PaymentType)
            {
                PaymentModeSource.Add(pm.Id, pm.Typee);
            }

            this.comboBox1.DataSource = new BindingSource(PaymentModeSource, null);
            this.comboBox1.DisplayMember = "Value";
            this.comboBox1.ValueMember = "Key";
            this.comboBox1.SelectedIndex = -1;
            this.comboBox1.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.comboBox1.AutoCompleteSource = AutoCompleteSource.ListItems;



        }

        private void button1_Click(object sender, EventArgs e)
        {
            Invoice[] result;
            result = new DataManager().GetActiveInvoicesForCompany(((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key.ToString(), ((KeyValuePair<int, string>)centerlist.SelectedItem).Key.ToString());

            dataGridView1.DataSource = result;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if(dataGridView1 != null)
            {
                DataGridViewTextBoxCell InvoiceNumber = dataGridView1["InvoiceNum", e.RowIndex] as DataGridViewTextBoxCell;
                DataGridViewTextBoxCell TotalAmount = dataGridView1["TotalAmount", e.RowIndex] as DataGridViewTextBoxCell;

                if (InvoiceNumber != null && TotalAmount !=null)
                {
                    setPaymentdetailsGrid(InvoiceNumber.Value.ToString(),TotalAmount.Value.ToString());
                }
            }
        }

        private void setPaymentdetailsGrid(string Invnum, string TotalAmount)
        {
            Payment[] payment;
            payment = new DataManager().GetPaymentsForInvoice(((KeyValuePair<int, string>)centerlist.SelectedItem).Key.ToString(), Invnum);
            double totalpayment = 0;
            double totalamount = double.Parse(TotalAmount);
            
            totalpayment = payment.Sum((s) => s.Amount);
            double paymentdue = totalamount - totalpayment;

            InvoicePaymentDetails[] pay = new InvoicePaymentDetails[1];
            pay[0] = new InvoicePaymentDetails { InvoiceNum = Invnum, TotalAmount = totalamount, TotalPayment = totalpayment, PaymentDue = paymentdue };

            dataGridView2.DataSource = payment;
            dataGridView3.DataSource = pay;

            InvoiceNumtxt.Text = Invnum;

            Application.DoEvents();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Payment payment = new Payment();
            payment.Amount = double.Parse(textBox1.Text);
            payment.CenterId = ((KeyValuePair<int, string>)centerlist.SelectedItem).Key;
            payment.CreatedBy = textBox2.Text;
            payment.DateCreated = new DateTime(DateTime.Now.Date.Year, DateTime.Now.Date.Month, DateTime.Now.Date.Day);
            payment.InvoiceNum = InvoiceNumtxt.Text;
            payment.PaymentMode = ((KeyValuePair<int, string>)comboBox1.SelectedItem).Key;
            payment.Statuss = "Active";

            PaymentResponse resp = new DataManager().AddPayment(payment);

            ActivePaymentForPrint frm = new ActivePaymentForPrint();
            frm.PaymentId = resp.PaymentId;
            
            frm.ShowDialog();
            

            
        }
    }

    public class InvoicePaymentDetails
    {
        public string InvoiceNum { get; set; }
        public double TotalAmount { get; set; }
        public double TotalPayment { get; set; }
        public double PaymentDue { get; set; }
    }
}
