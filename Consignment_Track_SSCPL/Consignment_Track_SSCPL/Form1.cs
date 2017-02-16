using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Consignment_Track_SSCPL
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            CityForm cf = new CityForm();
            cf.ShowDialog();
            
        }

        private void button2_Click(object sender, EventArgs e)
        {
            TransportForm tf = new TransportForm();
            tf.ShowDialog();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            PackageModeForm pmf = new PackageModeForm();
            pmf.ShowDialog();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            CenterMasterForm cmf = new CenterMasterForm();
            cmf.ShowDialog();
        }

        private void btnCompany_Click(object sender, EventArgs e)
        {
            CompanyForm comform = new CompanyForm();
            comform.ShowDialog();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            CNotesForm cform = new CNotesForm();
            cform.ShowDialog();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            GetCNNotesFullDetail frm = new GetCNNotesFullDetail();
            frm.ShowDialog(); 
        }

        private void button7_Click(object sender, EventArgs e)
        {
            ManifestForm mf = new ManifestForm();
            mf.ShowDialog();
        }

        private void button8_Click(object sender, EventArgs e)
        {
            InvoiceForm gif = new InvoiceForm();
            gif.ShowDialog();
        }

        private void button9_Click(object sender, EventArgs e)
        {
            CashMemo cm = new CashMemo();
            cm.ShowDialog();
        }

        private void button10_Click(object sender, EventArgs e)
        {
            PaymentForInvoice payinv = new PaymentForInvoice();
            payinv.ShowDialog();
        }

        private void button11_Click(object sender, EventArgs e)
        {
            ActivePaymentForInvoice frm = new ActivePaymentForInvoice();
            frm.ShowDialog();
        }

        private void button12_Click(object sender, EventArgs e)
        {
            CNNoteDeliveryUpdate frm = new CNNoteDeliveryUpdate();
            frm.ShowDialog();
        }
    }
}
