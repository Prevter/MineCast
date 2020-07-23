using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;
using Newtonsoft.Json;
using System.IO;
using System.Security.Cryptography.X509Certificates;

namespace MinecraftScreencast
{
    public partial class Form1 : Form
    {

        public class MineImage
        {
            public List<List<int>> image;
            public MineImage(List<List<int>> pix)
            {
                image = pix;
            }
        }

        public List<Color> pallete;

        bool run = false;
        public Form1()
        {
            InitializeComponent();
            pallete = new List<Color>();
            pallete.Add(Color.FromArgb(255, 125, 125, 125)); //stone
            pallete.Add(Color.FromArgb(255, 133, 96, 96)); //dirt
            pallete.Add(Color.FromArgb(255, 117, 117, 117)); //cobblestone
            pallete.Add(Color.FromArgb(255, 156, 127, 78)); //planks
            pallete.Add(Color.FromArgb(255, 83, 83, 83)); //bedrock
            pallete.Add(Color.FromArgb(255, 143, 139, 124)); //gold ore
            pallete.Add(Color.FromArgb(255, 135, 130, 126)); //iron ore
            pallete.Add(Color.FromArgb(255, 115, 115, 115)); //coal ore
            pallete.Add(Color.FromArgb(255, 154, 125, 77)); //log
            pallete.Add(Color.FromArgb(255, 182, 182, 57)); //sponge
            //wool
            pallete.Add(Color.FromArgb(255, 221, 221, 221)); //white
            pallete.Add(Color.FromArgb(255, 233, 126, 55)); //orange
            pallete.Add(Color.FromArgb(255, 179, 75, 200)); //magenta
            pallete.Add(Color.FromArgb(255, 103, 137, 211)); //light blue
            pallete.Add(Color.FromArgb(255, 192, 179, 28)); //yellow
            pallete.Add(Color.FromArgb(255, 59, 187, 47)); //lime
            pallete.Add(Color.FromArgb(255, 217, 132, 153)); //pink
            pallete.Add(Color.FromArgb(255, 66, 67, 67)); //gray
            pallete.Add(Color.FromArgb(255, 157, 164, 165)); //light gray
            pallete.Add(Color.FromArgb(255, 39, 116, 148)); //cyan
            pallete.Add(Color.FromArgb(255, 128, 53, 195)); //purple
            pallete.Add(Color.FromArgb(255, 39, 51, 153)); //blue
            pallete.Add(Color.FromArgb(255, 85, 51, 27)); //brown
            pallete.Add(Color.FromArgb(255, 55, 76, 24)); //green
            pallete.Add(Color.FromArgb(255, 162, 44, 42)); //red
            pallete.Add(Color.FromArgb(255, 26, 23, 23)); //black
            
            pallete.Add(Color.FromArgb(255, 249, 236, 77)); //gold block
            pallete.Add(Color.FromArgb(255, 230, 230, 230)); //iron block
            pallete.Add(Color.FromArgb(255, 155, 110, 97)); //bricks
            pallete.Add(Color.FromArgb(255, 90, 108, 90)); //mossy cobble
            pallete.Add(Color.FromArgb(255, 20, 18, 29)); //obsidian
            pallete.Add(Color.FromArgb(255, 129, 140, 143)); //diamond ore
            pallete.Add(Color.FromArgb(255, 99, 219, 213)); //diamond block
            pallete.Add(Color.FromArgb(255, 107, 71, 42)); //workbench
            pallete.Add(Color.FromArgb(255, 132, 107, 107)); //redstone ore
            pallete.Add(Color.FromArgb(255, 239, 251, 251)); //snow block
            pallete.Add(Color.FromArgb(255, 158, 164, 176)); //clay
            pallete.Add(Color.FromArgb(255, 107, 73, 55)); //jukebox
            pallete.Add(Color.FromArgb(255, 110, 53, 51)); //netherrack
            pallete.Add(Color.FromArgb(255, 84, 64, 51)); //soul sand
            pallete.Add(Color.FromArgb(255, 137, 112, 64)); //glowstone
            pallete.Add(Color.FromArgb(255, 104, 85, 56)); //bookshelf
            //concrete
            pallete.Add(Color.FromArgb(255, 207, 213, 214)); //white
            pallete.Add(Color.FromArgb(255, 224, 97, 1)); //orange
            pallete.Add(Color.FromArgb(255, 169, 48, 159)); //magenta
            pallete.Add(Color.FromArgb(255, 36, 137, 199)); //light blue
            pallete.Add(Color.FromArgb(255, 241, 175, 21)); //yellow
            pallete.Add(Color.FromArgb(255, 94, 169, 25)); //lime
            pallete.Add(Color.FromArgb(255, 217, 132, 153)); //pink
            pallete.Add(Color.FromArgb(255, 55, 58, 62)); //gray
            pallete.Add(Color.FromArgb(255, 125, 125, 115)); //light gray
            pallete.Add(Color.FromArgb(255, 21, 119, 136)); //cyan
            pallete.Add(Color.FromArgb(255, 128, 53, 195)); //purple
            pallete.Add(Color.FromArgb(255, 45, 47, 143)); //blue
            pallete.Add(Color.FromArgb(255, 96, 60, 32)); //brown
            pallete.Add(Color.FromArgb(255, 73, 91, 36)); //green
            pallete.Add(Color.FromArgb(255, 142, 33, 33)); //red
            pallete.Add(Color.FromArgb(255, 8, 10, 15)); //black
        }

        public static Bitmap ResizeImage(Image image, int width, int height)
        {
            var destRect = new Rectangle(0, 0, width, height);
            var destImage = new Bitmap(width, height);

            destImage.SetResolution(image.HorizontalResolution, image.VerticalResolution);

            using (var graphics = Graphics.FromImage(destImage))
            {
                graphics.CompositingMode = CompositingMode.SourceCopy;
                graphics.CompositingQuality = CompositingQuality.HighSpeed;
                graphics.InterpolationMode = InterpolationMode.NearestNeighbor;
                graphics.SmoothingMode = SmoothingMode.HighSpeed;
                graphics.PixelOffsetMode = PixelOffsetMode.HighSpeed;

                using (var wrapMode = new ImageAttributes())
                {
                    wrapMode.SetWrapMode(WrapMode.TileFlipXY);
                    graphics.DrawImage(image, destRect, 0, 0, image.Width, image.Height, GraphicsUnit.Pixel, wrapMode);
                }
            }

            return destImage;
        }

        private Bitmap CaptureMyScreen()
        {
            try
            {
                Bitmap captureBitmap = new Bitmap(1920, 1080, PixelFormat.Format32bppArgb);
                Rectangle captureRectangle = Screen.AllScreens[0].Bounds;
                Graphics captureGraphics = Graphics.FromImage(captureBitmap);
                captureGraphics.CopyFromScreen(captureRectangle.Left, captureRectangle.Top, 0, 0, captureRectangle.Size);
                return ResizeImage(captureBitmap,160,90);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                return null;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }
        int ColorDiff(Color c1, Color c2)
        {
            return (int)Math.Sqrt((c1.R - c2.R) * (c1.R - c2.R)
                                   + (c1.G - c2.G) * (c1.G - c2.G)
                                   + (c1.B - c2.B) * (c1.B - c2.B));
        }
        int closestColor(List<Color> colors, Color target)
        {
            var colorDiffs = colors.Select(n => ColorDiff(n, target)).Min(n => n);
            return colors.FindIndex(n => ColorDiff(n, target) == colorDiffs);
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            Bitmap img = CaptureMyScreen();
            pictureBox1.Image = img;
            List<List<int>> pix = new List<List<int>>();
            for(int y = 0; y < 90; y++)
            {
                List<int> line = new List<int>();
                for (int x = 0; x < 160; x++)
                {
                    Color clr = img.GetPixel(x, y);
                    line.Add(closestColor(pallete, clr));
                    //Console.WriteLine("X: " + x + " Y: " + y + "\nClosest color: " + closestColor(pallete,clr));
                }
                pix.Add(line);
            }
            using (StreamWriter file = File.CreateText(@"image\image.json"))
            {
                JsonSerializer serializer = new JsonSerializer();
                //serialize object directly into file stream
                serializer.Serialize(file, new MineImage(pix));
            }
        }

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            timer1.Interval = trackBar1.Value;
            label6.Text = "Current speed:\n" + trackBar1.Value + " ms";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (run)
            {
                button1.Text = "Start";
                run = false;
                timer1.Stop();
            }
            else
            {
                button1.Text = "Pause";
                run = true;
                timer1.Start();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            OpenFileDialog open = new OpenFileDialog();
            // image filters  
            open.Filter = "Image Files(*.jpg; *.jpeg; *.gif; *.bmp; *.png)|*.jpg; *.jpeg; *.gif; *.bmp; *.png";
            if (open.ShowDialog() == DialogResult.OK)
            {
                // display image in picture box  
                Bitmap img = ResizeImage(new Bitmap(open.FileName), 160, 90);
                pictureBox1.Image = img;

                List<List<int>> pix = new List<List<int>>();
                for (int y = 0; y < 90; y++)
                {
                    List<int> line = new List<int>();
                    for (int x = 0; x < 160; x++)
                    {
                        Color clr = img.GetPixel(x, y);
                        line.Add(closestColor(pallete, clr));
                        //Console.WriteLine("X: " + x + " Y: " + y + "\nClosest color: " + closestColor(pallete,clr));
                    }
                    pix.Add(line);
                }
                using (StreamWriter file = File.CreateText(@"image\image.json"))
                {
                    JsonSerializer serializer = new JsonSerializer();
                    //serialize object directly into file stream
                    serializer.Serialize(file, new MineImage(pix));
                }
            }
        }
    }
}
