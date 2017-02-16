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
    public partial class CNotesForm : Form
    {
        private CenterMaster[] _CenterMasterList;
        private PackagingMode[] _PackageModeList;
        private Company[] _CompanyLsit;
        private TransportMode[] _TransportModeList;

        public CNotesForm()
        {
            InitializeComponent();
        }

        private void CNotesForm_Load(object sender, EventArgs e)
        {
            _CenterMasterList = CachedDataManager.GetCachedCenterMaster();
            _PackageModeList = CachedDataManager.GetCachedPackagingMode();
            _CompanyLsit = CachedDataManager.GetCachedCompany();
            _TransportModeList = CachedDataManager.GetCachedTransportMode();

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


            Dictionary<int, string> PayModecomboSource = new Dictionary<int, string>();
            foreach (PackagingMode pm in _PackageModeList)
            {
                PayModecomboSource.Add(pm.Id, pm.Typee);
            }

            this.ToPaycomboBox.DataSource = new BindingSource(PayModecomboSource, null);
            this.ToPaycomboBox.DisplayMember = "Value";
            this.ToPaycomboBox.ValueMember = "Key";
            this.ToPaycomboBox.SelectedIndex = 0;
            this.ToPaycomboBox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.ToPaycomboBox.AutoCompleteSource = AutoCompleteSource.ListItems;

            Dictionary<int, string> ShippercompanySource = new Dictionary<int, string>();
            Dictionary<int, string> ConsignercompanySource = new Dictionary<int, string>();
            foreach (Company cmp in _CompanyLsit)
            {
                ShippercompanySource.Add(cmp.CompanyId, cmp.CompanyName + "  " + cmp.CompanyCity);
                ConsignercompanySource.Add(cmp.CompanyId, cmp.CompanyName + "  " + cmp.CompanyCity);

            }

            this.ShipComCbox.DataSource = new BindingSource(ShippercompanySource, null);
            this.ShipComCbox.DisplayMember = "Value";
            this.ShipComCbox.ValueMember = "Key";
            this.ShipComCbox.SelectedIndex = -1;
            this.ShipComCbox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.ShipComCbox.AutoCompleteSource = AutoCompleteSource.ListItems;

            this.ConsigneCompanyCombo.DataSource = new BindingSource(ConsignercompanySource, null);
            this.ConsigneCompanyCombo.DisplayMember = "Value";
            this.ConsigneCompanyCombo.ValueMember = "Key";
            this.ConsigneCompanyCombo.SelectedIndex = -1;
            this.ConsigneCompanyCombo.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.ConsigneCompanyCombo.AutoCompleteSource = AutoCompleteSource.ListItems;

            Dictionary<int, string> tarnsportmodecomboSource = new Dictionary<int, string>();
            foreach (TransportMode tm in _TransportModeList)
            {
                tarnsportmodecomboSource.Add(tm.ModelId, tm.Modee);

            }

            this.modecombobox.DataSource = new BindingSource(tarnsportmodecomboSource, null);
            this.modecombobox.DisplayMember = "Value";
            this.modecombobox.ValueMember = "Key";
            this.modecombobox.SelectedIndex = 0;
            this.modecombobox.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            this.modecombobox.AutoCompleteSource = AutoCompleteSource.ListItems;

        }

        private void button1_Click(object sender, EventArgs e)
        {
            richTextBox1.Text = String.Empty;
            if (string.IsNullOrEmpty(this.CnWeighttxt.Text))
            {
                this.CnWeighttxt.Text = this.ActWeighttxt.Text;
            }

            CNNote cnote = new CNNote();
            cnote.ActualWeight = int.Parse(this.ActWeighttxt.Text);
            cnote.BookingDate = new DateTime(dateTimePicker1.Value.Date.Year, dateTimePicker1.Value.Date.Month, dateTimePicker1.Value.Date.Day);
            cnote.CenterID = ((KeyValuePair<int, string>)centerlist.SelectedItem).Key;

            cnote.CNNumber = DateTime.Now.Date.Day.ToString().PadLeft(2, '0') + DateTime.Now.Date.Month.ToString().PadLeft(2, '0') + DateTime.Now.Date.Year.ToString().PadLeft(4, '0')
                + DateTime.Now.Hour.ToString().PadLeft(2,'0') + DateTime.Now.Minute.ToString().PadLeft(2,'0') + DateTime.Now.Second.ToString().PadLeft(2,'0');

            cnote.ConsigneeCompId = ((KeyValuePair<int, string>)ConsigneCompanyCombo.SelectedItem).Key;
            cnote.ConsignmentWeight = int.Parse(this.CnWeighttxt.Text);
            cnote.DestCityID = (from comp in _CompanyLsit
                               where comp.CompanyId == ((KeyValuePair<int, string>)ConsigneCompanyCombo.SelectedItem).Key
                               select comp.CityId).FirstOrDefault();

            cnote.MaterialDesc = MaterialDesctxt.Text;
            cnote.ModeID = ((KeyValuePair<int, string>)modecombobox.SelectedItem).Key;
            cnote.OriginCityID = (from comp in _CompanyLsit
                                  where comp.CompanyId == ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key
                                  select comp.CityId).FirstOrDefault();
            cnote.PackageNo = int.Parse(Packagetxt.Text);
            cnote.Remarks = RemarksTextBox.Text;
            cnote.ServiceTax = double.Parse(ServiceTaxtxt.Text);
            cnote.ShipperCompId = ((KeyValuePair<int, string>)ShipComCbox.SelectedItem).Key;
            cnote.Status = textBox1.Text;
            cnote.ToPayMode = ((KeyValuePair<int, string>)ToPaycomboBox.SelectedItem).Key;
            cnote.TOTAL = double.Parse(Totaltextbox.Text);
            //this.signature1.signaturecapture = new SignatureCapture();
            this.signature1.CNNumber = cnote.CNNumber;
            try
            {
               string Handedby =  this.signature1.SaveSignature();
                cnote.HandedBy = Handedby;
            }
            catch(Exception ex)
            {
                MessageBox.Show("Exception in Saving Images " + ex.Message + ex.StackTrace);
            }

            try
            {
                string cnnumber = cnote.CNNumber;
                CnnoteResponse result = new DataManager().AddCNNotes(cnote);
                if (result.code == 200)
                {
                    System.Threading.ThreadPool.QueueUserWorkItem(CNNOtePrintCallBack, cnnumber);
                    MessageBox.Show("Cnnote Record Created Successfully...");
                }
                richTextBox1.Text = result.status;
            }
            catch(Exception exp)
            {
                MessageBox.Show("Exception in Creating CNNote " + exp.Message);
                richTextBox1.Text = exp.Message + "  " + exp.Source + "  " + exp.StackTrace;
            }



        }

        public void CNNOtePrintCallBack(object state)
        {
            string cnnumber = state as string;
            
            CNNoteUtility utility = new CNNoteUtility();
            utility._CNNumber = cnnumber;
            utility.GenerateCNotePdfAndPrint();

        }
    }
}
