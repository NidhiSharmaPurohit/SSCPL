namespace Consignment_Track_SSCPL
{
    partial class PackageModeForm
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
            this.btnDelete = new System.Windows.Forms.Button();
            this.btnupdate = new System.Windows.Forms.Button();
            this.txtType = new System.Windows.Forms.TextBox();
            this.txtId = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.addpackagebtn = new System.Windows.Forms.Button();
            this.Modetxt = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.PackageModeView = new System.Windows.Forms.DataGridView();
            this.getPackageModebtn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.PackageModeView)).BeginInit();
            this.SuspendLayout();
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(494, 182);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(75, 23);
            this.btnDelete.TabIndex = 25;
            this.btnDelete.Text = "Delete";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // btnupdate
            // 
            this.btnupdate.Location = new System.Drawing.Point(395, 182);
            this.btnupdate.Name = "btnupdate";
            this.btnupdate.Size = new System.Drawing.Size(75, 23);
            this.btnupdate.TabIndex = 24;
            this.btnupdate.Text = "Update";
            this.btnupdate.UseVisualStyleBackColor = true;
            this.btnupdate.Click += new System.EventHandler(this.btnupdate_Click);
            // 
            // txtType
            // 
            this.txtType.Location = new System.Drawing.Point(441, 139);
            this.txtType.Name = "txtType";
            this.txtType.Size = new System.Drawing.Size(148, 20);
            this.txtType.TabIndex = 23;
            // 
            // txtId
            // 
            this.txtId.Location = new System.Drawing.Point(441, 105);
            this.txtId.Name = "txtId";
            this.txtId.Size = new System.Drawing.Size(148, 20);
            this.txtId.TabIndex = 22;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(392, 142);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(37, 13);
            this.label4.TabIndex = 21;
            this.label4.Text = "Type: ";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(401, 108);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(19, 13);
            this.label2.TabIndex = 20;
            this.label2.Text = "Id:";
            // 
            // addpackagebtn
            // 
            this.addpackagebtn.Location = new System.Drawing.Point(179, 440);
            this.addpackagebtn.Name = "addpackagebtn";
            this.addpackagebtn.Size = new System.Drawing.Size(128, 23);
            this.addpackagebtn.TabIndex = 19;
            this.addpackagebtn.Text = "Add PackageMode";
            this.addpackagebtn.UseVisualStyleBackColor = true;
            this.addpackagebtn.Click += new System.EventHandler(this.addpackagebtn_Click);
            // 
            // Modetxt
            // 
            this.Modetxt.Location = new System.Drawing.Point(144, 404);
            this.Modetxt.Name = "Modetxt";
            this.Modetxt.Size = new System.Drawing.Size(186, 20);
            this.Modetxt.TabIndex = 18;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(31, 407);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(107, 13);
            this.label3.TabIndex = 17;
            this.label3.Text = "PackageMode Type:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(88, 355);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(102, 13);
            this.label1.TabIndex = 16;
            this.label1.Text = "Add Package Mode";
            // 
            // PackageModeView
            // 
            this.PackageModeView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.PackageModeView.Location = new System.Drawing.Point(16, 76);
            this.PackageModeView.Name = "PackageModeView";
            this.PackageModeView.Size = new System.Drawing.Size(346, 222);
            this.PackageModeView.TabIndex = 15;
            this.PackageModeView.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.PackageModeView_CellContentClick);
            // 
            // getPackageModebtn
            // 
            this.getPackageModebtn.Location = new System.Drawing.Point(16, 35);
            this.getPackageModebtn.Name = "getPackageModebtn";
            this.getPackageModebtn.Size = new System.Drawing.Size(117, 23);
            this.getPackageModebtn.TabIndex = 14;
            this.getPackageModebtn.Text = "ListPackageMode";
            this.getPackageModebtn.UseVisualStyleBackColor = true;
            this.getPackageModebtn.Click += new System.EventHandler(this.getPackageModebtn_Click);
            // 
            // PackageModeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(604, 501);
            this.Controls.Add(this.btnDelete);
            this.Controls.Add(this.btnupdate);
            this.Controls.Add(this.txtType);
            this.Controls.Add(this.txtId);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.addpackagebtn);
            this.Controls.Add(this.Modetxt);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.PackageModeView);
            this.Controls.Add(this.getPackageModebtn);
            this.Name = "PackageModeForm";
            this.Text = "PackageModeForm";
            ((System.ComponentModel.ISupportInitialize)(this.PackageModeView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Button btnupdate;
        private System.Windows.Forms.TextBox txtType;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button addpackagebtn;
        private System.Windows.Forms.TextBox Modetxt;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView PackageModeView;
        private System.Windows.Forms.Button getPackageModebtn;
    }
}