namespace Consignment_Track_SSCPL
{
    partial class CityForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.citynametxt = new System.Windows.Forms.TextBox();
            this.citycodetxt = new System.Windows.Forms.TextBox();
            this.createcitybtn = new System.Windows.Forms.Button();
            this.getcitybtn = new System.Windows.Forms.Button();
            this.CityView = new System.Windows.Forms.DataGridView();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.cidtxt = new System.Windows.Forms.TextBox();
            this.ccodetxt = new System.Windows.Forms.TextBox();
            this.btnUpdate = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            this.cnametxt = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.CityView)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(23, 434);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(58, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "City Name:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(26, 474);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(55, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "City Code:";
            // 
            // citynametxt
            // 
            this.citynametxt.Location = new System.Drawing.Point(97, 431);
            this.citynametxt.Name = "citynametxt";
            this.citynametxt.Size = new System.Drawing.Size(213, 20);
            this.citynametxt.TabIndex = 2;
            // 
            // citycodetxt
            // 
            this.citycodetxt.Location = new System.Drawing.Point(97, 467);
            this.citycodetxt.Name = "citycodetxt";
            this.citycodetxt.Size = new System.Drawing.Size(213, 20);
            this.citycodetxt.TabIndex = 3;
            // 
            // createcitybtn
            // 
            this.createcitybtn.Location = new System.Drawing.Point(170, 509);
            this.createcitybtn.Name = "createcitybtn";
            this.createcitybtn.Size = new System.Drawing.Size(75, 23);
            this.createcitybtn.TabIndex = 4;
            this.createcitybtn.Text = "Create City";
            this.createcitybtn.UseVisualStyleBackColor = true;
            this.createcitybtn.Click += new System.EventHandler(this.createcitybtn_Click);
            // 
            // getcitybtn
            // 
            this.getcitybtn.Location = new System.Drawing.Point(29, 12);
            this.getcitybtn.Name = "getcitybtn";
            this.getcitybtn.Size = new System.Drawing.Size(121, 23);
            this.getcitybtn.TabIndex = 5;
            this.getcitybtn.Text = "Find All Cites";
            this.getcitybtn.UseVisualStyleBackColor = true;
            this.getcitybtn.Click += new System.EventHandler(this.getcitybtn_Click);
            // 
            // CityView
            // 
            this.CityView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.CityView.Location = new System.Drawing.Point(29, 41);
            this.CityView.Name = "CityView";
            this.CityView.Size = new System.Drawing.Size(407, 371);
            this.CityView.TabIndex = 6;
            this.CityView.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.CityView_CellContentClick);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(442, 68);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(38, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "CityID:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(442, 137);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(52, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "CityCode:";
            // 
            // cidtxt
            // 
            this.cidtxt.Location = new System.Drawing.Point(503, 61);
            this.cidtxt.Name = "cidtxt";
            this.cidtxt.Size = new System.Drawing.Size(155, 20);
            this.cidtxt.TabIndex = 10;
            // 
            // ccodetxt
            // 
            this.ccodetxt.Location = new System.Drawing.Point(503, 130);
            this.ccodetxt.Name = "ccodetxt";
            this.ccodetxt.Size = new System.Drawing.Size(155, 20);
            this.ccodetxt.TabIndex = 11;
            // 
            // btnUpdate
            // 
            this.btnUpdate.Location = new System.Drawing.Point(478, 175);
            this.btnUpdate.Name = "btnUpdate";
            this.btnUpdate.Size = new System.Drawing.Size(75, 23);
            this.btnUpdate.TabIndex = 13;
            this.btnUpdate.Text = "Update";
            this.btnUpdate.UseVisualStyleBackColor = true;
            this.btnUpdate.Click += new System.EventHandler(this.btnUpdate_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(583, 175);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(75, 23);
            this.btnDelete.TabIndex = 14;
            this.btnDelete.Text = "Delete";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // cnametxt
            // 
            this.cnametxt.Location = new System.Drawing.Point(503, 100);
            this.cnametxt.Name = "cnametxt";
            this.cnametxt.Size = new System.Drawing.Size(155, 20);
            this.cnametxt.TabIndex = 16;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(439, 103);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(55, 13);
            this.label5.TabIndex = 15;
            this.label5.Text = "CityName:";
            // 
            // CityForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(806, 567);
            this.Controls.Add(this.cnametxt);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.btnDelete);
            this.Controls.Add(this.btnUpdate);
            this.Controls.Add(this.ccodetxt);
            this.Controls.Add(this.cidtxt);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.CityView);
            this.Controls.Add(this.getcitybtn);
            this.Controls.Add(this.createcitybtn);
            this.Controls.Add(this.citycodetxt);
            this.Controls.Add(this.citynametxt);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "CityForm";
            this.Text = "CityForm";
            ((System.ComponentModel.ISupportInitialize)(this.CityView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox citynametxt;
        private System.Windows.Forms.TextBox citycodetxt;
        private System.Windows.Forms.Button createcitybtn;
        private System.Windows.Forms.Button getcitybtn;
        private System.Windows.Forms.DataGridView CityView;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox cidtxt;
        private System.Windows.Forms.TextBox ccodetxt;
        private System.Windows.Forms.Button btnUpdate;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.TextBox cnametxt;
        private System.Windows.Forms.Label label5;
    }
}