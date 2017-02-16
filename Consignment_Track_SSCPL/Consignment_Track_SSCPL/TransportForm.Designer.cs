namespace Consignment_Track_SSCPL
{
    partial class TransportForm
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
            this.getTransportbtn = new System.Windows.Forms.Button();
            this.TransportView = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.Modetxt = new System.Windows.Forms.TextBox();
            this.addtransportbtn = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.txtModeId = new System.Windows.Forms.TextBox();
            this.txtMode = new System.Windows.Forms.TextBox();
            this.btnupdate = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.TransportView)).BeginInit();
            this.SuspendLayout();
            // 
            // getTransportbtn
            // 
            this.getTransportbtn.Location = new System.Drawing.Point(13, 22);
            this.getTransportbtn.Name = "getTransportbtn";
            this.getTransportbtn.Size = new System.Drawing.Size(117, 23);
            this.getTransportbtn.TabIndex = 0;
            this.getTransportbtn.Text = "ListTransportMode";
            this.getTransportbtn.UseVisualStyleBackColor = true;
            this.getTransportbtn.Click += new System.EventHandler(this.getTransportbtn_Click);
            // 
            // TransportView
            // 
            this.TransportView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.TransportView.Location = new System.Drawing.Point(13, 63);
            this.TransportView.Name = "TransportView";
            this.TransportView.Size = new System.Drawing.Size(346, 222);
            this.TransportView.TabIndex = 1;
            this.TransportView.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.TransportView_CellContentClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(85, 342);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(104, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Add Transport Mode";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(28, 394);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(37, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "Mode:";
            // 
            // Modetxt
            // 
            this.Modetxt.Location = new System.Drawing.Point(88, 387);
            this.Modetxt.Name = "Modetxt";
            this.Modetxt.Size = new System.Drawing.Size(186, 20);
            this.Modetxt.TabIndex = 6;
            // 
            // addtransportbtn
            // 
            this.addtransportbtn.Location = new System.Drawing.Point(125, 429);
            this.addtransportbtn.Name = "addtransportbtn";
            this.addtransportbtn.Size = new System.Drawing.Size(106, 23);
            this.addtransportbtn.TabIndex = 7;
            this.addtransportbtn.Text = "AddTransport";
            this.addtransportbtn.UseVisualStyleBackColor = true;
            this.addtransportbtn.Click += new System.EventHandler(this.addtransportbtn_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(386, 95);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(46, 13);
            this.label2.TabIndex = 8;
            this.label2.Text = "ModeId:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(389, 129);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(40, 13);
            this.label4.TabIndex = 9;
            this.label4.Text = "Mode: ";
            // 
            // txtModeId
            // 
            this.txtModeId.Location = new System.Drawing.Point(438, 92);
            this.txtModeId.Name = "txtModeId";
            this.txtModeId.Size = new System.Drawing.Size(148, 20);
            this.txtModeId.TabIndex = 10;
            // 
            // txtMode
            // 
            this.txtMode.Location = new System.Drawing.Point(438, 126);
            this.txtMode.Name = "txtMode";
            this.txtMode.Size = new System.Drawing.Size(148, 20);
            this.txtMode.TabIndex = 11;
            // 
            // btnupdate
            // 
            this.btnupdate.Location = new System.Drawing.Point(392, 169);
            this.btnupdate.Name = "btnupdate";
            this.btnupdate.Size = new System.Drawing.Size(75, 23);
            this.btnupdate.TabIndex = 12;
            this.btnupdate.Text = "Update";
            this.btnupdate.UseVisualStyleBackColor = true;
            this.btnupdate.Click += new System.EventHandler(this.btnupdate_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(491, 169);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(75, 23);
            this.btnDelete.TabIndex = 13;
            this.btnDelete.Text = "Delete";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // TransportForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(658, 498);
            this.Controls.Add(this.btnDelete);
            this.Controls.Add(this.btnupdate);
            this.Controls.Add(this.txtMode);
            this.Controls.Add(this.txtModeId);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.addtransportbtn);
            this.Controls.Add(this.Modetxt);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.TransportView);
            this.Controls.Add(this.getTransportbtn);
            this.Name = "TransportForm";
            this.Text = "TransportForm";
            ((System.ComponentModel.ISupportInitialize)(this.TransportView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button getTransportbtn;
        private System.Windows.Forms.DataGridView TransportView;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox Modetxt;
        private System.Windows.Forms.Button addtransportbtn;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtModeId;
        private System.Windows.Forms.TextBox txtMode;
        private System.Windows.Forms.Button btnupdate;
        private System.Windows.Forms.Button btnDelete;
    }
}