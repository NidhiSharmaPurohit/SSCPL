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
    public partial class CityForm : Form
    {
        public CityForm()
        {
            InitializeComponent();
        }

        private void createcitybtn_Click(object sender, EventArgs e)
        {
            City city = new City();
            city.CityCode = citycodetxt.Text;
            city.CityName = citynametxt.Text;
            DataManager dm = new DataManager();
            dm.AddCity(city);
        }

        private void getcitybtn_Click(object sender, EventArgs e)
        {

            City[] cities = new DataManager().GetCities();

            CityView.DataSource = cities;
           
            if (CityView.ColumnCount == 3)
            {  
              DataGridViewLinkColumn Editlink = new DataGridViewLinkColumn();
              Editlink.UseColumnTextForLinkValue = true;
              Editlink.HeaderText = "Edit";
              Editlink.DataPropertyName = "lnkColumn";
              Editlink.LinkBehavior = LinkBehavior.SystemDefault;
              Editlink.Text = "Edit";
              CityView.Columns.Add(Editlink);
           }

        }

        private void CityView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (CityView != null)
            {
                
                   DataGridViewTextBoxCell citid = CityView["CityId", e.RowIndex] as DataGridViewTextBoxCell;
                if (citid != null)
                {
                    cidtxt.Text = citid.Value.ToString();
                    cidtxt.Enabled = false;
                }
                DataGridViewTextBoxCell citname = CityView["CityName", e.RowIndex] as DataGridViewTextBoxCell;
                if (citname != null)
                {
                    cnametxt.Text = citname.Value.ToString();
                }

                DataGridViewTextBoxCell citcd = CityView["CityCode", e.RowIndex] as DataGridViewTextBoxCell;
                if (citcd != null)
                {
                    ccodetxt.Text = citcd.Value.ToString();
                }

            }  
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            City city = new City();
            city.CityId = int.Parse(cidtxt.Text);
            city.CityCode = ccodetxt.Text;
            city.CityName = cnametxt.Text;
            new DataManager().UpdateCity(city);
            
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            new DataManager().DeleteCity(cidtxt.Text);
        }
    }
}
