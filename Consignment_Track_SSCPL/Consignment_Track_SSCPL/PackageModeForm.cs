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
    public partial class PackageModeForm : Form
    {
        public PackageModeForm()
        {
            InitializeComponent();
        }

        private void getPackageModebtn_Click(object sender, EventArgs e)
        {
            PackageModeView.DataSource = new DataManager().GetPackageMode();

            if (PackageModeView.ColumnCount == 2)
            {
                DataGridViewLinkColumn Editlink = new DataGridViewLinkColumn();
                Editlink.UseColumnTextForLinkValue = true;
                Editlink.HeaderText = "Edit";
                Editlink.DataPropertyName = "lnkColumn";
                Editlink.LinkBehavior = LinkBehavior.SystemDefault;
                Editlink.Text = "Edit";
                PackageModeView.Columns.Add(Editlink);
            }
        }

        private void PackageModeView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (PackageModeView != null)
            {

                DataGridViewTextBoxCell pid = PackageModeView["Id", e.RowIndex] as DataGridViewTextBoxCell;
                if (pid != null)
                {
                    txtId.Text = pid.Value.ToString();
                    txtId.Enabled = false;
                }
                DataGridViewTextBoxCell ptype = PackageModeView["Typee", e.RowIndex] as DataGridViewTextBoxCell;
                if (ptype != null)
                {
                    txtType.Text = ptype.Value.ToString();
                }
            }
        }

        private void btnupdate_Click(object sender, EventArgs e)
        {
            PackagingMode pm = new PackagingMode();
            pm.Id = int.Parse(txtId.Text);
            pm.Typee = txtType.Text;
            new DataManager().UpdatePackageMode(pm);
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            new DataManager().DeletePackageMode(txtId.Text);
        }

        private void addpackagebtn_Click(object sender, EventArgs e)
        {
            PackagingMode pm = new PackagingMode();
            pm.Typee = Modetxt.Text;

            new DataManager().AddPackageMode(pm);
        }
    }
}
