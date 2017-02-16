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
    public partial class GetCNNotesFullDetail : Form
    {
        public GetCNNotesFullDetail()
        {
            InitializeComponent();
        }

        private void GetCNotesbutton_Click(object sender, EventArgs e)
        {
            dataGridView1.Visible = true;
            dataGridView1.DataSource = new DataManager().GetCNNotesFullDetails(this.cnnotetextbox.Text);
        }

    }
}
