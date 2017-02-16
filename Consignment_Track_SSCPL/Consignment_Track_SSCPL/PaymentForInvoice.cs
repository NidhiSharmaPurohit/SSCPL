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
    public partial class PaymentForInvoice : Form
    {
        private CenterMaster[] _CenterMasterList;
        private Company[] _CompanyLsit;
        
        public PaymentForInvoice()
        {
            InitializeComponent();
        }

        private void PaymentForInvoice_Load(object sender, EventArgs e)
        {
            _CenterMasterList = CachedDataManager.GetCachedCenterMaster();
            _CompanyLsit = CachedDataManager.GetCachedCompany();
            InitControlsValues();
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

        
        private void button1_Click_1(object sender, EventArgs e)
        {
            Invoice[] result;
            result = new DataManager().GetInvoiceForCompanyInDateRange(dateTimePicker1.Value, dateTimePicker2.Value, ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key.ToString(), ((KeyValuePair<int, string>)centerlist.SelectedItem).Key.ToString());

            dataGridView1.DataSource = result;
        }
    }
}
