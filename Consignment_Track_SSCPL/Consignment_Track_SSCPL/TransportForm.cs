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
    public partial class TransportForm : Form
    {
        public TransportForm()
        {
            InitializeComponent();
        }

        private void getTransportbtn_Click(object sender, EventArgs e)
        {
            TransportView.DataSource = new DataManager().GetTransportMode();

            if (TransportView.ColumnCount == 2)
            {
                DataGridViewLinkColumn Editlink = new DataGridViewLinkColumn();
                Editlink.UseColumnTextForLinkValue = true;
                Editlink.HeaderText = "Edit";
                Editlink.DataPropertyName = "lnkColumn";
                Editlink.LinkBehavior = LinkBehavior.SystemDefault;
                Editlink.Text = "Edit";
                TransportView.Columns.Add(Editlink);
            }
        }

        private void addtransportbtn_Click(object sender, EventArgs e)
        {
            TransportMode tm = new TransportMode();
            tm.Modee = Modetxt.Text;

            new DataManager().AddTransport(tm);
        }

        private void TransportView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

            if (TransportView != null)
            {

                DataGridViewTextBoxCell modeid = TransportView["ModelId", e.RowIndex] as DataGridViewTextBoxCell;
                if (modeid != null)
                {
                    txtModeId.Text = modeid.Value.ToString();
                    txtModeId.Enabled = false;
                }
                DataGridViewTextBoxCell mode = TransportView["Modee", e.RowIndex] as DataGridViewTextBoxCell;
                if (mode != null)
                {
                    txtMode.Text = mode.Value.ToString();
                }
            }

        }

        private void btnupdate_Click(object sender, EventArgs e)
        {
            TransportMode tm = new TransportMode();
            tm.Modee = txtMode.Text;
            tm.ModelId = int.Parse(txtModeId.Text);

            new DataManager().UpdateTransport(tm);
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            new DataManager().DeleteTransport(txtModeId.Text);
        }
    }
}
