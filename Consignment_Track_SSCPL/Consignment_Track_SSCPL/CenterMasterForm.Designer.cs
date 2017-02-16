namespace Consignment_Track_SSCPL
{
    partial class CenterMasterForm
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
            this.CenterMasterView = new System.Windows.Forms.DataGridView();
            this.getCenterMasterbtn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.CenterMasterView)).BeginInit();
            this.SuspendLayout();
            // 
            // CenterMasterView
            // 
            this.CenterMasterView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.CenterMasterView.Location = new System.Drawing.Point(12, 53);
            this.CenterMasterView.Name = "CenterMasterView";
            this.CenterMasterView.Size = new System.Drawing.Size(1067, 114);
            this.CenterMasterView.TabIndex = 17;
            // 
            // getCenterMasterbtn
            // 
            this.getCenterMasterbtn.Location = new System.Drawing.Point(433, 24);
            this.getCenterMasterbtn.Name = "getCenterMasterbtn";
            this.getCenterMasterbtn.Size = new System.Drawing.Size(117, 23);
            this.getCenterMasterbtn.TabIndex = 16;
            this.getCenterMasterbtn.Text = "ListCenterMaster";
            this.getCenterMasterbtn.UseVisualStyleBackColor = true;
            this.getCenterMasterbtn.Click += new System.EventHandler(this.getCenterMasterbtn_Click);
            // 
            // CenterMasterForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1100, 463);
            this.Controls.Add(this.CenterMasterView);
            this.Controls.Add(this.getCenterMasterbtn);
            this.Name = "CenterMasterForm";
            this.Text = "CenterMasterForm";
            ((System.ComponentModel.ISupportInitialize)(this.CenterMasterView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView CenterMasterView;
        private System.Windows.Forms.Button getCenterMasterbtn;
    }
}