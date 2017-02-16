namespace Consignment_Track_SSCPL
{
    partial class GetCNNotesFullDetail
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
            this.cnnotetextbox = new System.Windows.Forms.TextBox();
            this.GetCNotesbutton = new System.Windows.Forms.Button();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(31, 46);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(108, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Enter CNote Number:";
            // 
            // cnnotetextbox
            // 
            this.cnnotetextbox.Location = new System.Drawing.Point(155, 43);
            this.cnnotetextbox.Name = "cnnotetextbox";
            this.cnnotetextbox.Size = new System.Drawing.Size(293, 20);
            this.cnnotetextbox.TabIndex = 1;
            // 
            // GetCNotesbutton
            // 
            this.GetCNotesbutton.Location = new System.Drawing.Point(474, 43);
            this.GetCNotesbutton.Name = "GetCNotesbutton";
            this.GetCNotesbutton.Size = new System.Drawing.Size(98, 23);
            this.GetCNotesbutton.TabIndex = 2;
            this.GetCNotesbutton.Text = "Get Details";
            this.GetCNotesbutton.UseVisualStyleBackColor = true;
            this.GetCNotesbutton.Click += new System.EventHandler(this.GetCNotesbutton_Click);
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(12, 93);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(1018, 183);
            this.dataGridView1.TabIndex = 3;
            this.dataGridView1.Visible = false;
            // 
            // GetCNNotesFullDetail
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1057, 663);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.GetCNotesbutton);
            this.Controls.Add(this.cnnotetextbox);
            this.Controls.Add(this.label1);
            this.Name = "GetCNNotesFullDetail";
            this.Text = "GetCNNotesFullDetail";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox cnnotetextbox;
        private System.Windows.Forms.Button GetCNotesbutton;
        private System.Windows.Forms.DataGridView dataGridView1;
    }
}