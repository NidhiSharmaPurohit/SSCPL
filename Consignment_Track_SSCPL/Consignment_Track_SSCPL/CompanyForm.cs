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
    public partial class CompanyForm : Form
    {
        public CompanyForm()
        {
            InitializeComponent();
        }

        private void getCompany_Click(object sender, EventArgs e)
        {
            CompanyView.DataSource =  new DataManager().GetCompany();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            ConstantData.GetStates();
        }

        private void CompanyForm_Load(object sender, EventArgs e)
        {
           
            comboBox1.Items.AddRange(ConstantData.GetStates().ToArray());

            comboBox1.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            comboBox1.AutoCompleteSource = AutoCompleteSource.ListItems;

            City[] cities = CachedDataManager.GetCachedCity();

            foreach(City c in cities)
            {
                comboBox2.Items.Add(c.CityName);
            }
            comboBox2.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            comboBox2.AutoCompleteSource = AutoCompleteSource.ListItems;
        }
    }
}
