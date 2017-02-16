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
    public partial class CenterMasterForm : Form
    {
        public CenterMasterForm()
        {
            InitializeComponent();
        }

        private void getCenterMasterbtn_Click(object sender, EventArgs e)
        {
            CenterMasterView.DataSource = new DataManager().GetCenterMaster();

            if (CenterMasterView.ColumnCount == 9)
            {
                DataGridViewLinkColumn Editlink = new DataGridViewLinkColumn();
                Editlink.UseColumnTextForLinkValue = true;
                Editlink.HeaderText = "Edit";
                Editlink.DataPropertyName = "lnkColumn";
                Editlink.LinkBehavior = LinkBehavior.SystemDefault;
                Editlink.Text = "Edit";
                CenterMasterView.Columns.Add(Editlink);
            }
        }
    }
}
