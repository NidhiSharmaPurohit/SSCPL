using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Net;
using System.Collections.Specialized;

namespace Consignment_Track_SSCPL
{
    public partial class Signature : UserControl
    {
        Boolean IsCapturing = false;
        private Point startPoint;
        private Point endPoint;
        Pen pen = new Pen(Color.Black,3.5f);
        Glyph glyph = null;
        public SignatureCapture signaturecapture = new SignatureCapture();
        public string CNNumber = string.Empty;

        public Signature()
        {
            InitializeComponent();
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            using (Graphics graphic = this.panel1.CreateGraphics())
            {
                SolidBrush solidBrush = new SolidBrush(Color.Blue);
                graphic.FillRectangle(solidBrush, 0, 0, panel1.Width, panel1.Height);
            }
        }

        public string SaveSignature()
        {
            string signaturename = this.textBox1.Text;
            using (Bitmap bitmap = new Bitmap(1000, 1000))
            {
                using (Graphics graphics = Graphics.FromImage(bitmap))
                {

                    Pen pen = new Pen(Color.Red, 3.5f);
                    foreach (Glyph glyph in signaturecapture.Glyphs)
                    {
                        foreach (Line line in glyph.Lines)
                        {
                            graphics.DrawLine(pen, line.StartPoint.X, line.StartPoint.Y,
                                line.EndPoint.X, line.EndPoint.Y);
                        }
                    }

                    bitmap.Save(this.CNNumber + ".jpg", System.Drawing.Imaging.ImageFormat.Jpeg);
                }
            }
            this.signaturecapture = new SignatureCapture();

            string fileLocation = this.CNNumber + ".jpg";
            NameValueCollection values = new NameValueCollection();
            NameValueCollection files = new NameValueCollection();
            values.Add("firstName", "Alan");
            files.Add("picture", fileLocation);
            sendHttpRequest(new DataManager().getCNNoteSignedImageUploadUrl(), values, files);

            return signaturename;


        }

        private static string sendHttpRequest(string url, NameValueCollection values, NameValueCollection files = null)
        {
            string boundary = "----------------------------" + DateTime.Now.Ticks.ToString("x");
            // The first boundary
            byte[] boundaryBytes = System.Text.Encoding.UTF8.GetBytes("\r\n--" + boundary + "\r\n");
            // The last boundary
            byte[] trailer = System.Text.Encoding.UTF8.GetBytes("\r\n--" + boundary + "--\r\n");
            // The first time it itereates, we need to make sure it doesn't put too many new paragraphs down or it completely messes up poor webbrick
            byte[] boundaryBytesF = System.Text.Encoding.ASCII.GetBytes("--" + boundary + "\r\n");

            // Create the request and set parameters
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.ContentType = "multipart/form-data; boundary=" + boundary;
            request.Method = "POST";
            request.KeepAlive = true;
            request.Credentials = System.Net.CredentialCache.DefaultCredentials;

            // Get request stream
            Stream requestStream = request.GetRequestStream();

            foreach (string key in values.Keys)
            {
                // Write item to stream
                byte[] formItemBytes = System.Text.Encoding.UTF8.GetBytes(string.Format("Content-Disposition: form-data; name=\"{0}\";\r\n\r\n{1}", key, values[key]));
                requestStream.Write(boundaryBytes, 0, boundaryBytes.Length);
                requestStream.Write(formItemBytes, 0, formItemBytes.Length);
            }

            if (files != null)
            {
                foreach (string key in files.Keys)
                {
                    if (File.Exists(files[key]))
                    {
                        int bytesRead = 0;
                        byte[] buffer = new byte[2048];
                        byte[] formItemBytes = System.Text.Encoding.UTF8.GetBytes(string.Format("Content-Disposition: form-data; name=\"{0}\"; filename=\"{1}\"\r\nContent-Type: application/octet-stream\r\n\r\n", key, files[key]));
                        requestStream.Write(boundaryBytes, 0, boundaryBytes.Length);
                        requestStream.Write(formItemBytes, 0, formItemBytes.Length);

                        using (FileStream fileStream = new FileStream(files[key], FileMode.Open, FileAccess.Read))
                        {
                            while ((bytesRead = fileStream.Read(buffer, 0, buffer.Length)) != 0)
                            {
                                // Write file content to stream, byte by byte
                                requestStream.Write(buffer, 0, bytesRead);
                            }

                            fileStream.Close();
                        }
                    }
                }
            }

            // Write trailer and close stream
            requestStream.Write(trailer, 0, trailer.Length);
            requestStream.Close();

            using (StreamReader reader = new StreamReader(request.GetResponse().GetResponseStream()))
            {
                return reader.ReadToEnd();
            };
        }


        private void Signature_Load(object sender, EventArgs e)
        {
            panel1.ForeColor = Color.Green;
            panel1.BackColor = Color.Blue;
            panel1.BorderStyle = BorderStyle.Fixed3D;
        }

        private void SignaturePanel_MouseMove(object sender, MouseEventArgs e)
        {
            if (IsCapturing)
            {
                if (startPoint.IsEmpty && endPoint.IsEmpty)
                {
                    endPoint = e.Location;
                }
                else
                {
                    startPoint = endPoint;
                    endPoint = e.Location;
                    Line line = new Line(startPoint, endPoint);
                    glyph.Lines.Add(line);
                    DrawLine(line);
                }

            }
        }

        private void SignaturePanel_MouseUp(object sender, MouseEventArgs e)
        {
            IsCapturing = false;
            signaturecapture.Glyphs.Add(glyph);
            startPoint = new Point();
            endPoint = new Point();


        }

        private void SignaturePanel_MouseDown(object sender, MouseEventArgs e)
        {
            IsCapturing = true;
            glyph = new Glyph();

        }

        private void DrawLine(Line line)
        {
            using (Graphics graphic = this.panel1.CreateGraphics())
            {
                graphic.DrawLine(pen, line.StartPoint, line.EndPoint);
            }
        }

        private void DrawSignature()
        {
            foreach (Glyph glyph in signaturecapture.Glyphs)
            {
                foreach (Line line in glyph.Lines)
                {
                    DrawLine(line);
                }
            }

        }
    }

    [Serializable]
    public class Glyph
    {
        public Glyph()
        {
            this.Lines = new List<Line>();
        }
        public List<Line> Lines { get; set; }
    }

    [Serializable]
    public class Line
    {
        public Line()
        {

        }

        public Line(Point startPoint, Point endPoint)
        {
            this.StartPoint = startPoint;
            this.EndPoint = endPoint;
        }

        public Point StartPoint { get; set; }
        public Point EndPoint { get; set; }
    }

    [Serializable]
    public class SignatureCapture
    {
        public SignatureCapture()
        {
            this.Glyphs = new List<Glyph>();
        }

        public List<Glyph> Glyphs { get; set; }
    }
}
