/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2_bddm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static tp2_bddm.Morphologique.inp;


/**
 *
 * @author TT
 */
public class TP2_BDDM {

    /**
     * @param args the command line arguments
     */
    
    static void grayscale(String in, String out)
    {
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            int q = 0;
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0; y < h; y++)
                {
                    int rgb = bimg.getRGB(x,y);
                    int r = ((rgb & 0x00ff0000) >> 16);
                    int g = ((rgb & 0x0000ff00) >> 8);
                    int b = (rgb & 0x0000ff);
                   
                    q = pixels[x][y] = (int)Math.round(0.299*r + 0.587*g + 0.114*b);
                    //q = pixels[x][y] = (int)Math.round((r+g+b)/3);
                    //q = pixels[x][y] = (int)Math.round((Math.max(r, Math.max(g, b)) + Math.min(r, Math.min(g,b)))/2);
                    
                    Color t = new Color(q,q,q);
                    rgb = t.getRGB();
                    bimg.setRGB(x, y, rgb);
                    
                }
            }
            ImageIO.write(bimg, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void grayscaleRaster(String in, String out)
    {
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            int q = 0;
            Raster ras = bimg.getRaster();
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0; y < h; y++)
                {
     
                    int r = ras.getSample(x, y, 0);
                    int g = ras.getSample(x, y, 1);
                    int b = ras.getSample(x, y, 2);
                    
                    q = pixels[x][y] = (int)Math.round(0.299*r + 0.587*g + 0.114*b);
                    //q = pixels[x][y] = (int)Math.round((r+g+b)/3);
                    //q = pixels[x][y] = (int)Math.round((Math.max(r, Math.max(g, b)) + Math.min(r, Math.min(g,b)))/2);
                    
                    Color t = new Color(q,q,q);
                    int rgb = t.getRGB();
                    bimg.setRGB(x, y, rgb);
                 
                }
            }
            ImageIO.write(bimg, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void negative(String in, String out)
    {
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int q = 0;
            Raster ras = bimg.getRaster();
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0; y < h; y++)
                {
      
                   
                    int r = ras.getSample(x, y, 0);
                    int g = ras.getSample(x, y, 1);
                    int b = ras.getSample(x, y, 2);
                    
                    int r1 = 255 - r;
                    int g1 = 255 - g;
                    int b1 = 255 - b;
                    
                    Color t = new Color(r1, g1, b1);
                    int rgb = t.getRGB();
                    bimg.setRGB(x, y, rgb);
                    
                }
            }
            ImageIO.write(bimg, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void Mirror(String in, String out)
    {
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            int q = 0;
            Raster ras = bimg.getRaster();
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 1; y < h; y++)
                {
                    
                    int r = ras.getSample(x, y, 0);
                    int g = ras.getSample(x, y, 1);
                    int b = ras.getSample(x, y, 2);
                
                    //int r2 = ras.getSample(x, h - y - 1, 0);
                    //int g2 = ras.getSample(x, h - y - 1, 1);
                    //int b2 = ras.getSample(x, h - y - 1, 2);
                    
                    
                    
                    Color t = new Color(r, g, b);
                    int rgb = t.getRGB();
                    bimg.setRGB(x, h - y, rgb);
                    
                    //Color t2 = new Color(r2, g2, b2);
                    //rgb = t2.getRGB();
                    //bimg.setRGB(x, y, rgb);
                }
                
            }
            ImageIO.write(bimg, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     static void FlipV(String in, String out)
    {
        BufferedImage bimg, bimgF;
        try {
            bimg = ImageIO.read(new File(in));
            bimgF = ImageIO.read(new File(in));
            
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            int q = 0;
            Raster ras = bimg.getRaster();
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 1; y < h; y++)
                {
  
                    
                    int r = ras.getSample(x, y, 0);
                    int g = ras.getSample(x, y, 1);
                    int b = ras.getSample(x, y, 2);
                
                    //int r2 = ras.getSample(x, h - y - 1, 0);
                    //int g2 = ras.getSample(x, h - y - 1, 1);
                    //int b2 = ras.getSample(x, h - y - 1, 2);
                    
                    
                    
                    Color t = new Color(r, g, b);
                    int rgb = t.getRGB();
                    bimgF.setRGB(x, h - y, rgb);
                    
                    //Color t2 = new Color(r2, g2, b2);
                    //rgb = t2.getRGB();
                    //bimg.setRGB(x, y, rgb);
                }
                
            }
            ImageIO.write(bimgF, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void FlipH(String in, String out)
    {
        BufferedImage bimg, bimgF;
        try {
            bimg = ImageIO.read(new File(in));
            bimgF = ImageIO.read(new File(in));
            
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            int q = 0;
            Raster ras = bimg.getRaster();
            for(int y = 0 ; y < h ; y++)
            {
                for(int x = 1; x < w; x++)
                {
                    int rgb = bimg.getRGB(x,y);
                    
                    int r = ras.getSample(x, y, 0);
                    int g = ras.getSample(x, y, 1);
                    int b = ras.getSample(x, y, 2);
                
                    //int r2 = ras.getSample(x, h - y - 1, 0);
                    //int g2 = ras.getSample(x, h - y - 1, 1);
                    //int b2 = ras.getSample(x, h - y - 1, 2);
                    
                    
                    
                    Color t = new Color(r, g, b);
                    rgb = t.getRGB();
                    bimgF.setRGB(w - x, y, rgb);
                    
                    //Color t2 = new Color(r2, g2, b2);
                    //rgb = t2.getRGB();
                    //bimg.setRGB(x, y, rgb);
                }
                
            }
            ImageIO.write(bimgF, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void Rotate(String in, String out)
    {
        BufferedImage bimg, bimgF;
        try {
            bimg = ImageIO.read(new File(in));
            
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            bimgF = new BufferedImage(h, w, 1);
            
            int q = 0;
            Raster ras = bimg.getRaster();
            for(int y = 0 ; y < h ; y++)
            {
                for(int x = 1; x < w; x++)
                {
                    int rgb;
                    
                    int r = ras.getSample(x, y, 0);
                    int g = ras.getSample(x, y, 1);
                    int b = ras.getSample(x, y, 2);
                
                    Color t = new Color(r, g, b);
                    rgb = t.getRGB();
                    bimgF.setRGB(y, x, rgb);
                }
                
            }
            ImageIO.write(bimgF, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void RotateG(String in, String out, double angle)
    {
        try {
            BufferedImage img = ImageIO.read(new File(in));
            
            double sin = Math.abs(Math.sin(Math.toRadians(angle))),
			cos = Math.abs(Math.cos(Math.toRadians(angle)));

            int w = img.getWidth(null), h = img.getHeight(null);

            int neww = (int) Math.floor(w*cos + h*sin),
                            newh = (int) Math.floor(h*cos + w*sin);

            BufferedImage bimg = new BufferedImage(neww, newh, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = bimg.createGraphics();

            g.translate((neww-w)/2, (newh-h)/2);
            g.rotate(Math.toRadians(angle), w/2, h/2);
            g.drawRenderedImage(img, null);
            g.dispose();

	
      
            
            
            ImageIO.write(bimg, "jpg",  new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void toHSB(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0 ; y <  h ; y++)
                {
                    int rgb = bimg.getRGB(x, y);
                    Color c = new Color(rgb);
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    
                     
                    float HSV[];
                    HSV = Color.RGBtoHSB(r, g, b, null);
//                    /System.out.println(HSV[0] + " " + HSV[1] + " " + HSV[2]);
                    
                    Color hsv = new Color(HSV[0],HSV[1],HSV[2]);
                    //Color hsv2 = Color.getHSBColor(HSV[0], HSV[1], HSV[2]);
                    
                    int t = hsv.getRGB();
                    
                    bimg.setRGB(x, y, t);
                    
                    //int rgb2 = Color.HSBtoRGB(HSV[0], HSV[1], HSV[2]);
                    //bimg.setRGB(x,y,rgb2);
                }
            }
            ImageIO.write(bimg, "jpg",  new File(out));
            
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void toRGB(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                    float HSV[] = new float[3];
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                    HSV = c.getColorComponents(HSV);
                    
                    //int rgb = Color.HSBtoRGB(h_hsv, s_hsv, v_hsv);
                    int c2 = Color.HSBtoRGB(HSV[0], HSV[1], HSV[2]);
                    
                    bimg.setRGB(x,y,c2);
                    
                    
                }
            }
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void toYCbCr(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            BufferedImage ycb =  new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                    
                    int co = bimg.getRGB(x, y);
                    Color c = new Color(co);
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    
                    int Y = (int)(0.299*r+0.587*g+0.114*b);
                    int Cb=(int)(128-0.169*r-0.331*g+0.500*b);
                    int Cr =(int)(128+0.500*r-0.419*g-0.081*b);

                    int val = (Y<<16) | (Cb<<8) | Cr;
                        ycb.setRGB(x,y,val);

                    
                }
            }
            ImageIO.write(ycb,"jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void toRGB2(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                  
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                  
                    
        
                    double Y = c.getRed();
                    double Cb = c.getGreen(); 
                    double Cr = c.getBlue(); 

                    int r = (int) (Y + 1.40200 * (Cr - 0x80));
                    int g = (int) (Y - 0.34414 * (Cb - 0x80) - 0.71414 * (Cr - 0x80));
                    int b = (int) (Y + 1.77200 * (Cb - 0x80));

                    r = Math.max(0, Math.min(255, r));
                    g = Math.max(0, Math.min(255, g));
                    b = Math.max(0, Math.min(255, b));

                    int val = (r<<16) | (g<<8) | b;
                    bimg.setRGB(x,y,val);
             }
            }
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void YtoRGB2(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                  
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                  
                    
        
                    double Y = c.getRed();
                    double Cb = c.getGreen(); 
                    double Cr = c.getBlue(); 
                    //Y = Cb = 128;
                    Cb = Cr = 128;
                    int r = (int) (Y + 1.40200 * (Cr - 0x80));
                    int g = (int) (Y - 0.34414 * (Cb - 0x80) - 0.71414 * (Cr - 0x80));
                    int b = (int) (Y + 1.77200 * (Cb - 0x80));

                    r = Math.max(0, Math.min(255, r));
                    g = Math.max(0, Math.min(255, g));
                    b = Math.max(0, Math.min(255, b));

                    int val = (r<<16) | (g<<8) | b;
                    bimg.setRGB(x,y,val);
             }
            }
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void CbtoRGB2(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                  
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                  
                    
        
                    double Y = c.getRed();
                    double Cb = c.getGreen(); 
                    double Cr = c.getBlue(); 
                    //Y = Cb = 128;
                    Y = Cr = 128;
                    int r = (int) (Y + 1.40200 * (Cr - 0x80));
                    int g = (int) (Y - 0.34414 * (Cb - 0x80) - 0.71414 * (Cr - 0x80));
                    int b = (int) (Y + 1.77200 * (Cb - 0x80));

                    r = Math.max(0, Math.min(255, r));
                    g = Math.max(0, Math.min(255, g));
                    b = Math.max(0, Math.min(255, b));

                    int val = (r<<16) | (g<<8) | b;
                    bimg.setRGB(x,y,val);
             }
            }
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void CrtoRGB2(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                  
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                  
                    
        
                    double Y = c.getRed();
                    double Cb = c.getGreen(); 
                    double Cr = c.getBlue(); 
                    //Y = Cb = 128;
                    Y = Cb = 128;
                    int r = (int) (Y + 1.40200 * (Cr - 0x80));
                    int g = (int) (Y - 0.34414 * (Cb - 0x80) - 0.71414 * (Cr - 0x80));
                    int b = (int) (Y + 1.77200 * (Cb - 0x80));

                    r = Math.max(0, Math.min(255, r));
                    g = Math.max(0, Math.min(255, g));
                    b = Math.max(0, Math.min(255, b));

                    int val = (r<<16) | (g<<8) | b;
                    bimg.setRGB(x,y,val);
             }
            }
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public static void toHSBv2(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0 ; y <  h ; y++)
                {
                    int rgb = bimg.getRGB(x, y);
                    Color c = new Color(rgb);
                    double r = c.getRed();
                    double g = c.getGreen();
                    double b = c.getBlue();
                    
                    
                    
                    r=r/255; g=g/255; b=b/255;
                    double minRGB = Math.min(r,Math.min(g,b));
                    double maxRGB = Math.max(r,Math.max(g,b));

                     double computedH = 0;
                     double computedS = 0;
                     double computedV = 0;

                
                    double d = (r==minRGB) ? g-b : ((b==minRGB) ? r-g : b-r);
                    double h2 = (r==minRGB) ? 3 : ((b==minRGB) ? 1 : 5);
                    computedH = 60*(h2 - d/(maxRGB - minRGB));
                    //computedS = (maxRGB - minRGB)/maxRGB;
                    //computedV = maxRGB;
                    
                    
//                    /System.out.println(HSV[0] + " " + HSV[1] + " " + HSV[2]);

                    //System.out.println(computedH);
                   if(computedH >= 200 && computedH <= 260)
                   {
                    Color hsv = new Color(0,0,0);
                    int t = hsv.getRGB();
                    bimg.setRGB(x, y, t);
                   }
                   else
                   {
                    Color hsv = new Color(255,255,255);
                    int t = hsv.getRGB();
                    bimg.setRGB(x, y, t);
                   
                   }
                   
                    //int rgb2 = Color.HSBtoRGB(HSV[0], HSV[1], HSV[2]);
                    //bimg.setRGB(x,y,rgb2);
                }
            }
            ImageIO.write(bimg, "jpg",  new File(out));
            
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
  
public static void etirementV(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0 ; y <  h ; y++)
                {
                   /* int rgb = bimg.getRGB(x, y);
                    Color c = new Color(rgb);
                    double r = c.getRed();
                    double g = c.getGreen();
                    double b = c.getBlue();
                    
                    
                    
                    r=r/255; g=g/255; b=b/255;
                    double minRGB = Math.min(r,Math.min(g,b));
                    double maxRGB = Math.max(r,Math.max(g,b));

                    double computedH = 0;
                    double computedS = 0;
                    double computedV = 0;

                
                    double d = (r==minRGB) ? g-b : ((b==minRGB) ? r-g : b-r);
                    double h2 = (r==minRGB) ? 3 : ((b==minRGB) ? 1 : 5);
                    //computedH = 60*(h2 - d/(maxRGB - minRGB));
                    //computedS = (maxRGB - minRGB)/maxRGB;
                    computedV = maxRGB;
                    */
                    
                    float HSV[] = new float[3];
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                    HSV = c.getColorComponents(HSV);
                   
                    //int rgb = Color.HSBtoRGB(h_hsv, s_hsv, v_hsv);
                    HSV[2] = (Math.round(HSV[2]*1.5) >= 1 ? 1 : Math.round(HSV[2]*1.5));
                    int c2 = Color.HSBtoRGB(HSV[0], HSV[1], HSV[2]);
                    
                    bimg.setRGB(x,y,c2);
                    
             
                    
//                    /System.out.println(HSV[0] + " " + HSV[1] + " " + HSV[2]);

                    //System.out.println(computedH);
                   /*if(computedH >= 200 && computedH <= 260)
                   {
                    Color hsv = new Color(0,0,0);
                    int t = hsv.getRGB();
                    bimg.setRGB(x, y, t);
                   }
                   else
                   {
                    Color hsv = new Color(255,255,255);
                    int t = hsv.getRGB();
                    bimg.setRGB(x, y, t);
                   
                   }*/
                   
            
                   
                    //int rgb2 = Color.HSBtoRGB(HSV[0], HSV[1], HSV[2]);
                    //bimg.setRGB(x,y,rgb2);
                }
            }
            ImageIO.write(bimg, "jpg",  new File(out));
            
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   

     public static void etirementY(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                  
                    int hsv = bimg.getRGB(x, y);
                    Color c = new Color(hsv);
                  
                    
        
                    double Y = (c.getRed()*2 >= 255 ? 255 : c.getRed()*2);
                    //System.out.println(Y);
                    double Cb = c.getGreen(); 
                    double Cr = c.getBlue(); 

                    int r = (int) (Y + 1.40200 * (Cr - 0x80));
                    int g = (int) (Y - 0.34414 * (Cb - 0x80) - 0.71414 * (Cr - 0x80));
                    int b = (int) (Y + 1.77200 * (Cb - 0x80));

                    r = Math.max(0, Math.min(255, r));
                    g = Math.max(0, Math.min(255, g));
                    b = Math.max(0, Math.min(255, b));

                    int val = (r<<16) | (g<<8) | b;
                    bimg.setRGB(x,y,val);
             }
            }
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ImpulsionelNoiseAdd(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            double p = 0.05;
            int times = (int) Math.round(w*h*p);
            Random r = new Random();
            Color blackC = new Color(0,0,0);
            Color whiteC = new Color(255,255,255);
            int black = blackC.getRGB();
            int white = whiteC.getRGB();
            
            while(times-- > 0)
            {
                int x = r.nextInt(w);
                int y = r.nextInt(h);
                int val = r.nextInt(2);
                bimg.setRGB(x,y,val == 1 ? black : white);
            }
            
            
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
       public static void GaussianNoiseAdd(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            double p = 0.05;
            int times = (int) Math.round(w*h*p);
            Random r = new Random();
            
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                  
                    int num = bimg.getRGB(x, y);
                    Color c = new Color(num);
                  
                    int red  = c.getRed();
                    int green  = c.getGreen();
                    int blue  = c.getBlue();
                    
                    double g = r.nextGaussian();
                    g = (g >= 1 ? 1 : (g <= -1 ? -1 : g));
                    //System.out.println(g);
                    g *= 255;
                    
                    red += g;
                    red = (red >= 255 ? 255 : ((red <= 0) ? 0 : red));
                    
                    blue += g;
                    blue = (blue >= 255 ? 255 : ((blue <= 0) ? 0 : blue));
                    
                    green += g;
                    green = (green >= 255 ? 255 : ((green <= 0) ? 0 : green));
                    
                    
                    int val = (red<<16) | (green<<8) | blue;
                    bimg.setRGB(x,y,val);
                }
            }
            
            
            
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
    public static void MoyenNoiseFilter(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            int p = 1;
            for(int x = p; x < w-p; x++)
            {
                for(int y = p ; y < h-p ; y++)
                {
                    int sommeR = 0, sommeG = 0, sommeB = 0;
                    for(int i = -p ; i <= p ; i++)
                    {
                        for(int j = -p ; j<=p ; j++)
                        {
                            int pix = bimg.getRGB(x+i, y+j);
                            Color pixc = new Color(pix);
                            
                            sommeR += pixc.getRed();
                            sommeG += pixc.getGreen();
                            sommeB += pixc.getBlue();
                            
                        }
                    }
                    
                    sommeR /= 9;
                    sommeG /= 9;
                    sommeB /= 9;
                    
                    
                    int val = (sommeR<<16) | (sommeG<<8) | sommeB;
                    bimg.setRGB(x,y,val);
                }
            }
            
            
            
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
    public static void MoyenNoiseFilter_iterative(String in, String out, int pp, int times, int[][] conv)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            while(times-- > 0)
            {
            int p = pp;
            for(int x = p; x < w-p; x++)
            {
                for(int y = p ; y < h-p ; y++)
                {
                    int sommeR = 0, sommeG = 0, sommeB = 0;
                    for(int i = -p ; i <= p ; i++)
                    {
                        for(int j = -p ; j<=p ; j++)
                        {
                            int pix = bimg.getRGB(x+i, y+j);
                            Color pixc = new Color(pix);
                            
                            sommeR += (pixc.getRed() * conv[p-i][p-j]);
                            sommeG += (pixc.getGreen() * conv[p-i][p-j]);
                            sommeB += (pixc.getBlue() * conv[p-i][p-j]);
                        }
                    }
                    
                    int div = 0;
                    for(int i = -p ; i <= p ; i++)
                    {
                        for(int j = -p ; j <= p ; j++)
                        {
                            //System.out.print(conv[p-i][p-j]);
                            div += conv[p-i][p-j];
                        }
                        //System.out.println();
                    }
                    
                    //sommeR /= Math.pow(2*p+1,2);
                    //sommeG /= Math.pow(2*p+1,2);
                    //sommeB /= Math.pow(2*p+1,2);
                    
                    sommeR /= div;
                    sommeG /= div;
                    sommeB /= div;
                    
                    //System.out.println(div);
                    
                    int val = (sommeR<<16) | (sommeG<<8) | sommeB;
                    bimg.setRGB(x,y,val);
                }
            }
        }
        ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
       
         public static void MedianNoiseFilter_iterative(String in, String out, int pp, int times)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            while(times-- > 0)
            {
            int p = pp;
            for(int x = p; x < w-p; x++)
            {
                for(int y = p ; y < h-p ; y++)
                {
                    int medianR = 0, medianG = 0, medianB = 0;
                    ArrayList<Integer> lr = new ArrayList<Integer>();
                    ArrayList<Integer> lg = new ArrayList<Integer>();
                    ArrayList<Integer> lb = new ArrayList<Integer>();
                    
                    for(int i = -p ; i <= p ; i++)
                    {
                        for(int j = -p ; j<=p ; j++)
                        {
                            int pix = bimg.getRGB(x+i, y+j);
                            Color pixc = new Color(pix);
                            
                            lr.add(pixc.getRed());
                            lg.add(pixc.getGreen());
                            lb.add(pixc.getBlue());
                            
                        }
                    }
                    
                    Collections.sort(lr);
                    Collections.sort(lg);
                    Collections.sort(lb);
                    
                    
                    medianR = lr.get(((lr.size()+1)/2)-1);
                    medianG = lg.get(((lr.size()+1)/2)-1);
                    medianB = lb.get(((lr.size()+1)/2)-1);
                    
                    
                    int val = (medianR<<16) | (medianG<<8) | medianB;
                    bimg.setRGB(x,y,val);
                }
            }
            }
            
            
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
       
        public static void MoyenNoiseFilter_update(String in, String out, int pp, int times, int[][] conv)
        {
            int countNoise = 0;
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            while(times-- > 0)
            {
            int p = pp;
            
            for(int x = p; x < w-p; x++)
            {
                for(int y = p ; y < h-p ; y++)
                {
                    int ccPix = bimg.getRGB(x, y);
                    Color cPix = new Color(ccPix);
                    if(((cPix.getRed() >= 240) && (cPix.getGreen() >= 240) && (cPix.getBlue() >= 240)) || ((cPix.getRed() <= 15) && (cPix.getGreen() <= 15) && (cPix.getBlue() <= 15)))
                    {
                        countNoise++;
                        int sommeR = 0, sommeG = 0, sommeB = 0;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j<=p ; j++)
                            {
                                int pix = bimg.getRGB(x+i, y+j);
                                Color pixc = new Color(pix);
                            
                                sommeR += (pixc.getRed() * conv[p-i][p-j]);
                                sommeG += (pixc.getGreen() * conv[p-i][p-j]);
                                sommeB += (pixc.getBlue() * conv[p-i][p-j]);
                            }
                        }
                    
                        int div = 0;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j <= p ; j++)
                            {
                                //System.out.print(conv[p-i][p-j]);
                                div += conv[p-i][p-j];
                            }
                            //System.out.println();
                        }

                        //sommeR /= Math.pow(2*p+1,2);
                        //sommeG /= Math.pow(2*p+1,2);
                        //sommeB /= Math.pow(2*p+1,2);

                        sommeR /= div;
                        sommeG /= div;
                        sommeB /= div;

                        //System.out.println(div);

                        int val = (sommeR<<16) | (sommeG<<8) | sommeB;
                        
                        bimg.setRGB(x,y,val);
                    }
                }
                
            }
        }
            System.out.println(countNoise);
        ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
         
        
        
        
    static void binary(String in, String out, int seuil, int direct)
    {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(in));
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            int[][] pixels = new int[w][h];
            int q = 0;
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0; y < h; y++)
                {
                    int pix = bimg.getRGB(x, y);
                    Color c = new Color(pix);
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                   
                    q = pixels[x][y] = (int)Math.round(0.299*r + 0.587*g + 0.114*b);
                    //q = pixels[x][y] = (int)Math.round((r+g+b)/3);
                    //q = pixels[x][y] = (int)Math.round((Math.max(r, Math.max(g, b)) + Math.min(r, Math.min(g,b)))/2);
                    
                    q = ((direct == 1) ? ((q>seuil)? 255 : 0) : ((q>seuil) ? 0 : 255));
                    
                    Color t = new Color(q,q,q);
                    int newC = t.getRGB();
                    bimg.setRGB(x, y, newC);
                }
            }
            //Graphics g = bimg.getGraphics();
            //g.drawImage(bimg, 0, 0, null);
            ImageIO.write(bimg, "jpg", new File(out));
            
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    static void dilatation(String in, String out, int p)
    {
        try {
            BufferedImage bimg = null;
            
            bimg = ImageIO.read(new File(in));
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            for(int x = p; x < bimg.getWidth() - p ; x++)
            {
                for(int y = p ; y < bimg.getHeight() - p ; y++)
                {
                    int iniPix = bimg.getRGB(x,y);
                    Color iniC = new Color(iniPix);
                    if(iniC.getRed() == 0) // eza kenit sawda
                    {
                        boolean existW = false;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j <= p ; j++)
                            {
                                int pix = bimg.getRGB(x+i, y+j);
                                Color c = new Color(pix);

                                if(c.getRed() == 255) // eza bayda ... alors
                                {
                                    existW = true;
                                }
                            }
                        }
                        Color t = (existW ? new Color(255,255,255) : new Color(0,0,0)); 
                        int newC = t.getRGB();
                        //System.out.println(newC);
                        b.setRGB(x, y, newC);
                    }
                    else
                    {
                        b.setRGB(x, y, iniPix);
                    }
                    
                }
            }
            ImageIO.write(b, "jpg", new File(out));  
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    static void erosion(String in, String out, int p)
    {
        try {
            BufferedImage bimg = null;
            
            bimg = ImageIO.read(new File(in));
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            for(int x = p; x < bimg.getWidth() - p ; x++)
            {
                for(int y = p ; y < bimg.getHeight() - p ; y++)
                {
                     int iniPix = bimg.getRGB(x,y);
                    Color iniC = new Color(iniPix);
                    if(iniC.getRed() == 255) // eza kenit bayda
                    {
                        boolean existB = false;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j <= p ; j++)
                            {
                                int pix = bimg.getRGB(x+i, y+j);
                                Color c = new Color(pix);

                                if(c.getRed() == 0) // eza sawda ... alors
                                {
                                    existB = true;
                                }
                            }
                        }
                        Color t = (existB ? new Color(0,0,0) : new Color(255,255,255)); 
                        int newC = t.getRGB();
                        //System.out.println(newC);
                        b.setRGB(x, y, newC);
                    }
                    else
                    {
                        b.setRGB(x, y, iniPix);
                    }
                }
            }
            ImageIO.write(b, "jpg", new File(out));  
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    static void contour(String in, String out, int p)
    {
        try {
            BufferedImage bimg = ImageIO.read(new File(in));
            BufferedImage bDila = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage bErro = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            for(int x = p; x < bimg.getWidth() - p ; x++)
            {
                for(int y = p ; y < bimg.getHeight() - p ; y++)
                {
                    int iniPix = bimg.getRGB(x,y);
                    Color iniC = new Color(iniPix);
                    if(iniC.getRed() == 255) // eza kenit bayda
                    {
                        boolean existB = false;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j <= p ; j++)
                            {
                                int pix = bimg.getRGB(x+i, y+j);
                                Color c = new Color(pix);

                                if(c.getRed() == 0) // eza sawda ... alors
                                {
                                    existB = true;
                                }
                            }
                        }
                        Color t = (existB ? new Color(0,0,0) : new Color(255,255,255)); 
                        int newC = t.getRGB();
                        //System.out.println(newC);
                        bErro.setRGB(x, y, newC);
                    }
                    else
                    {
                        bErro.setRGB(x, y, iniPix);
                    }
                }
            }
           for(int x = p; x < bimg.getWidth() - p ; x++)
            {
                for(int y = p ; y < bimg.getHeight() - p ; y++)
                {
                    int iniPix = bimg.getRGB(x,y);
                    Color iniC = new Color(iniPix);
                    if(iniC.getRed() == 0) // eza kenit sawda
                    {
                        boolean existW = false;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j <= p ; j++)
                            {
                                int pix = bimg.getRGB(x+i, y+j);
                                Color c = new Color(pix);

                                if(c.getRed() == 255) // eza bayda ... alors
                                {
                                    existW = true;
                                }
                            }
                        }
                        Color t = (existW ? new Color(255,255,255) : new Color(0,0,0)); 
                        int newC = t.getRGB();
                        //System.out.println(newC);
                        bDila.setRGB(x, y, newC);
                    }
                    else
                    {
                        bDila.setRGB(x, y, iniPix);
                    }
                    
                }
                
            }
            for(int x = p; x < bimg.getWidth() - p ; x++)
            {
                for(int y = p ; y < bimg.getHeight() - p ; y++)
                {
                     int dilPix = bDila.getRGB(x,y);
                     int errPix = bErro.getRGB(x,y);
                     
                     Color dilCol = new Color(dilPix);
                     Color errCol = new Color(errPix);
                     
                     int dil = dilCol.getRed();
                     int err = errCol.getRed();
                     //System.out.print(dil + " - " + err + " = \n");
                     int diff = dil - err;
                     
                     diff = (diff < 0) ? 255 : diff;
                     
                     Color diffCol = new Color(diff, diff, diff);
                     int diffPix = diffCol.getRGB();
                     b.setRGB(x, y, diffPix);
                     
                }
            }
            
            ImageIO.write(b, "jpg", new File(out));  
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
            
    static void errosion_cross(String in, String out, int p)
    {
        try {
            BufferedImage bimg = null;
            
            bimg = ImageIO.read(new File(in));
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            for(int x = p; x < bimg.getWidth() - p ; x++)
            {
                for(int y = p ; y < bimg.getHeight() - p ; y++)
                {
                    int iniPix = bimg.getRGB(x,y);
                    Color iniC = new Color(iniPix);
                    if(iniC.getRed() == 255)
                    {
                        boolean existB = false;
                        for(int i = -p ; i <= p ; i++)
                        {
                            int pix = bimg.getRGB(x+i, y);
                            Color c = new Color(pix);

                            if(c.getRed() == 0) // eza bayda ... alors
                            {
                                existB = true;
                            }

                        }
                        for(int j = -p ; j <= p ; j++)
                        {
                            int pix = bimg.getRGB(x, y+j);
                            Color c = new Color(pix);

                            if(c.getRed() == 0) // eza bayda ... alors
                            {
                                existB = true;
                            }
                        }
                        Color t = (existB ? new Color(0,0,0) : new Color(255,255,255)); 
                        int newC = t.getRGB();
                        //System.out.println(newC);
                        b.setRGB(x, y, newC);
                    }
                    else
                    {
                        b.setRGB(x, y, iniPix);
                    }
                    
                }
            }
            ImageIO.write(b, "jpg", new File(out));  
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
        public static void soble(String in, String out, int pp, int times, int[][] kgx, int[][] kgy)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            while(times-- > 0)
            {
            int p = pp;
            for(int x = p; x < w-p; x++)
            {
                for(int y = p ; y < h-p ; y++)
                {
                    int sommeX = 0, sommeY = 0;
                    for(int i = -p ; i <= p ; i++)
                    {
                        for(int j = -p ; j<=p ; j++)
                        {
                            int pix = bimg.getRGB(x+i, y+j);
                            Color pixc = new Color(pix);
                            
                            sommeX += (pixc.getRed() * kgx[p-i][p-j]);
                            sommeY += (pixc.getRed() * kgy[p-i][p-j]);
                            
                        }
                    }
                    
                    int somme =  (int) Math.sqrt(sommeX*sommeX*1.0 + sommeY*sommeY*1.0);
                    somme = (somme > 255) ? 255 : somme;
                    //System.out.println(div);
                    
                    int val = (somme<<16) | (somme<<8) | somme;
                    b.setRGB(x,y,val);
                }
            }
            }
            
            
            ImageIO.write(b, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        
        
           public static void gradient(String in, String out)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            
            int p = 0;
            for(int x = p; x < w-p-1; x++)
            {
                for(int y = p ; y < h-p-1 ; y++)
                {
                    int pix = bimg.getRGB(x, y);
                    int pixx1 = bimg.getRGB(x+1, y);
                    int pixy1 = bimg.getRGB(x, y+1);
                    
                    Color pixc = new Color(pix);
                    Color pixcx = new Color(pixx1);
                    Color pixcy = new Color(pixy1);
                   
                    int diffX = pixcx.getRed() - pixc.getRed();
                    int diffY = pixcy.getRed() - pixc.getRed();
                    
                    //sommeR /= Math.pow(2*p+1,2);
                    //sommeG /= Math.pow(2*p+1,2);
                    //sommeB /= Math.pow(2*p+1,2);
                    
                    int somme =  (int) Math.sqrt(diffX*diffX*1.0 + diffY*diffY*1.0);
                    somme = (somme > 255) ? 255 : somme;
                    //System.out.println(div);
                    
                    int val = (somme<<16) | (somme<<8) | somme;
                    b.setRGB(x,y,val);
                
            }
            }
            
            
            ImageIO.write(b, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
         
              public static void laplace(String in, String out, int pp, int times, int[][] carre)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            while(times-- > 0)
            {
            int p = pp;
            for(int x = p; x < w-p; x++)
            {
                for(int y = p ; y < h-p ; y++)
                {
                    int sommeX = 0;
                    for(int i = -p ; i <= p ; i++)
                    {
                        for(int j = -p ; j<=p ; j++)
                        {
                            int pix = bimg.getRGB(x+i, y+j);
                            Color pixc = new Color(pix);
                            
                            sommeX += (pixc.getRed() * carre[p-i][p-j]);
                            
                        }
                    }
                    
                   
                    //sommeR /= Math.pow(2*p+1,2);
                    //sommeG /= Math.pow(2*p+1,2);
                    //sommeB /= Math.pow(2*p+1,2);
                    
                    int somme =  sommeX;
                    somme = (somme > 255) ? 255 : ((somme < 0) ? 0 : somme);

                    //System.out.println(div);
                    
                    int val = (somme<<16) | (somme<<8) | somme;
                    b.setRGB(x,y,val);
                    
                    
                    
                }
            }
            }
            
            
            ImageIO.write(b, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        
 
    public static void laplace_etirement(String in, String out, int pp, int times, int[][] carre)
    {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            
            BufferedImage b = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            int[][] ls = new int[w][h];
            int[] g = new int[256];
            
            while(times-- > 0)
            {
                int p = pp;
                for(int x = p; x < w-p; x++)
                {
                    for(int y = p ; y < h-p ; y++)
                    {
                        
                        int sommeX = 0;
                        for(int i = -p ; i <= p ; i++)
                        {
                            for(int j = -p ; j<=p ; j++)
                            {
                                int pix = bimg.getRGB(x+i, y+j);
                                Color pixc = new Color(pix);
                                sommeX += (pixc.getRed() * carre[p-i][p-j]);
                            }
                        }
                        ls[x][y] = sommeX;
                        
                    }
                }
            }
            
            int pmin = Integer.MAX_VALUE;
            int pmax = Integer.MIN_VALUE;
            
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                    if(ls[x][y] < pmin)
                        pmin = ls[x][y];
                    if(ls[x][y] > pmax)
                        pmax = ls[x][y];
                }
            }
            //System.out.println(pmin + " " + pmax);
            for(int x = 0 ; x < w; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                    int Y = ls[x][y];
                    Y = (Y - pmin) * 255 / (pmax - pmin);

                    Y = Y > 255 ? 255 : (Y < 0 ? 0 : Y);
                    
                    
                    Color t = new Color(Y,Y,Y);
                    int rgbNew = t.getRGB();
                    b.setRGB(x, y, rgbNew);
                }
            }
            
            ImageIO.write(b, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

              
    public static class pix
    {
        public int x;
        public int y;
        public int val;
        
        public pix(int x, int y, int val)
        {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
   public static void kCluster(String in, String out)
   {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            
            ArrayList<pix> cluster1 = new ArrayList<pix>();
            ArrayList<pix> cluster2 = new ArrayList<pix>();
            ArrayList<pix> cluster3 = new ArrayList<pix>();
            ArrayList<pix> cluster4 = new ArrayList<pix>();
            double cm1, cm2, cm3, cm4;
            
            for(int x = 0 ; x < w/2 ; x++)
            {
                for(int y = 0 ; y < h/2 ; y++)
                {
                    int val = bimg.getRGB(x,y);
                    Color c = new Color(val);
                    int v = 0;
                    v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                    cluster1.add(new pix(x, y, v));
                }
            }
            
            
            for(int x = w/2 ; x < w ; x++)
            {
                for(int y = 0 ; y < h/2 ; y++)
                {
                    int val = bimg.getRGB(x,y);
                    Color c = new Color(val);
                    int v = 0;
                    v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                    cluster2.add(new pix(x, y, v));
                }
            }
            
            for(int x = 0 ; x < w/2 ; x++)
            {
                for(int y = h/2 ; y < h ; y++)
                {
                    int val = bimg.getRGB(x,y);
                    Color c = new Color(val);
                    int v = 0;
                    v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                    cluster3.add(new pix(x, y, v));
                }
            }
            
            
            for(int x = w/2 ; x < w ; x++)
            {
                for(int y = h/2 ; y < h ; y++)
                {
                    int val = bimg.getRGB(x,y);
                    Color c = new Color(val);
                    int v = 0;
                    v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                    //cluster4.add(new pix(x, y, Math.abs(val)));
                    //System.out.println(v);
                    cluster4.add(new pix(x, y, v));
                }
            }
            //System.out.println(Integer.MAX_VALUE);
            /* int sum = 0;
            for(int i = 0 ; i < cluster1.size() ; i++)
            {
                sum += cluster1.get(i).val;
                
                System.out.println(cluster1.get(i).val);
            }
            System.out.println(sum/(cluster1.size()));*/
            
           cm1 = cluster1.stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
           
           cm2 = cluster2.stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
           
           cm3 = cluster3.stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
           
           cm4 = cluster4.stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
           
           
           
           for(int times = 0 ; times < 50 ; times++)
           {
               System.out.println(cm1 + " " + cm2 + " " + cm3 + " " + cm4 );
               cluster1.clear();
               cluster2.clear();
               cluster3.clear();
               cluster4.clear();
               
               for(int x = 0 ; x < w ; x++)
               {
                    for(int y = 0 ; y < h ; y++)
                    {
                        int val = bimg.getRGB(x,y);
                        Color c = new Color(val);
                        int v = 0;
                        v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                        
                        int diff1 = (int) Math.abs(v - cm1);
                        int diff2 = (int) Math.abs(v - cm2);
                        int diff3 = (int) Math.abs(v - cm3);
                        int diff4 = (int) Math.abs(v - cm4);
                        
                        int min = Math.min(diff1, (Math.min(diff2, Math.min(diff3, diff4))));
                        if(min == diff1)
                            cluster1.add(new pix(x, y, v));
                        else if(min == diff2)
                            cluster2.add(new pix(x, y, v));
                        else if(min == diff3)
                            cluster3.add(new pix(x, y, v));
                        else 
                            cluster4.add(new pix(x, y, v));
                        
                    }
               }
               
               cm1 = cluster1.stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
           
                cm2 = cluster2.stream()
                     .mapToInt(p -> p.val)
                     .average()
                     .orElse(0);

                cm3 = cluster3.stream()
                     .mapToInt(p -> p.val)
                     .average()
                     .orElse(0);

                cm4 = cluster4.stream()
                     .mapToInt(p -> p.val)
                     .average()
                     .orElse(0);
               
           }
           
           for(int i = 0 ; i < cluster1.size() ; i++)
           {
               bimg.setRGB(cluster1.get(i).x , cluster1.get(i).y , Color.RED.getRGB());
           }
           for(int i = 0 ; i < cluster2.size() ; i++)
           {
               bimg.setRGB(cluster2.get(i).x , cluster2.get(i).y , Color.BLUE.getRGB());
           }
           for(int i = 0 ; i < cluster3.size() ; i++)
           {
               bimg.setRGB(cluster3.get(i).x , cluster3.get(i).y , Color.YELLOW.getRGB());
           }
           for(int i = 0 ; i < cluster4.size() ; i++)
           {
               bimg.setRGB(cluster4.get(i).x , cluster4.get(i).y , Color.ORANGE.getRGB());
           }
           
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
    public static void kCluster2(String in, String out, int k, int it)
   {
        try {
            BufferedImage bimg;
            bimg = ImageIO.read(new File(in));
            
            int w = bimg.getWidth(null);
            int h = bimg.getHeight(null);
            
            ArrayList<pix>[] cluster = new ArrayList[k];
            for(int i = 0; i < k ; i++)
            {
                cluster[i] = new ArrayList<pix>();
            }
            
            double[] cm = new double[k];
            
            for(int x = 0 ; x < w ; x++)
            {
                for(int y = 0 ; y < h ; y++)
                {
                    int val = bimg.getRGB(x,y);
                    Color c = new Color(val);
                    int v = 0;
                    v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                    
                    cluster[(x*y) % k].add(new pix(x, y, v));
                    
                }
            }
            
            
            for(int i = 0 ; i < k ; i++)
            {
                cm[i] = cluster[i].stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
            }
          
           
           boolean stop = false;
           int times = 0;
           for(times = 0 ; times < it ; times++)
           {
               if(stop)
                   break;
               for(int i = 0 ; i < k ; i++)
               {
                   cluster[i].clear();
               }
              
               
               for(int x = 0 ; x < w ; x++)
               {
                    for(int y = 0 ; y < h ; y++)
                    {
                        int val = bimg.getRGB(x,y);
                        Color c = new Color(val);
                        int v = 0;
                        v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
                        
                        int[] diff = new int[k];
                        for(int i = 0 ; i < k ; i++)
                        {
                            diff[i] = (int) Math.abs(v - cm[i]);
                        }
                        
                        int minI = 0;
                        for(int i = 1 ; i < k ; i++)
                        {
                            if(diff[i] < diff[minI])
                                minI = i;
                        }
                        
                        cluster[minI].add(new pix(x, y, v));
                        
                    }
               }
               
            double[] temp_cm = new double[k];
            for(int i = 0; i < k ; i++)
            {
                temp_cm[i] = cm[i];
            }
            for(int i = 0 ; i < k ; i++)
            {
                cm[i] = cluster[i].stream()
                .mapToInt(p -> p.val)
                .average()
                .orElse(0);
            }
            boolean test = true;
            for(int i = 0 ; i < k ; i++)
            {
                //System.out.print((cm[i] - temp_cm[i]) + " ");
                if( cm[i] - temp_cm[i] != 0.0)
                    test = false;
            }
            //System.out.println(test);
            if(test)
                stop = true;
            //System.out.println(stop);
           }
           
           System.out.println(times);
           Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,  Color.PINK, Color.WHITE, Color.BLACK};
           for(int i = 0 ; i < k ; i++)
           {
               if(cluster[i].size() == 0)
               {
                   System.out.println("Cluster " + i + ": Empty");
               }
               else
               {
                   System.out.println("Cluster " + i + ": " + cluster[i].size());
                    for(int j = 0 ; j < cluster[i].size() ; j++)
                    {
                        bimg.setRGB(cluster[i].get(j).x, cluster[i].get(j).y, colors[i].getRGB());
                    }
               }
           }
           
           
           
            ImageIO.write(bimg, "jpg", new File(out));
        } catch (IOException ex) {
            Logger.getLogger(TP2_BDDM.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
   
    public static void main(String[] args) {
        //negative("D:\\icone.jpg", "D:\\icone5.jpg");
        //grayscale("D:\\icone.jpg", "D:\\icone5.jpg");
        //grayscaleRaster("D:\\icone.jpg", "D:\\icone5.jpg");
        //FlipV("D:\\icone.jpg", "D:\\icone8.jpg");
        //FlipH("D:\\icone.jpg", "D:\\icone9.jpg");
        //Rotate("D:\\icone.jpg", "D:\\icone10.jpg");
        //RotateG("D:\\icone.jpg" , "D:\\icone11.jpg", 32);
        //toHSB("D:\\icone.jpg" , "D:\\icone12.jpg");
        //toRGB("D:\\icone12.jpg", "D:\\icone13.jpg");
        //toYCbCr("D:\\icone.jpg", "D:\\icone15.jpg");
        //toRGB2("D:\\icone15.jpg", "D:\\icone16.jpg");
        //YtoRGB2("D:\\icone15.jpg", "D:\\icone17.jpg");
        //CbtoRGB2("D:\\icone15.jpg", "D:\\icone18.jpg");
        //CrtoRGB2("D:\\icone15.jpg", "D:\\icone19.jpg");
        //toHSBv2("D:\\icone.jpg", "D:\\icone20.jpg");
        
        //toYCbCr("D:\\firee.jpg", "D:\\firee1.jpg");
        //CbtoRGB2("D:\\firee1.jpg", "D:\\firee2.jpg");
        //toHSBv2("D:\\firee.jpg", "D:\\firee3.jpg");

        //toHSB("D:\\dark.jpg" , "D:\\dark2.jpg");
        //etirementV("D:\\dark2.jpg", "D:\\dark3.jpg");
        
        //toYCbCr("D:\\dark.jpg", "D:\\dark4.jpg");
        //etirementY("D:\\dark4.jpg", "D:\\dark5.jpg");
        //etirementY("D:\\icone15.jpg", "D:\\icone22.jpg");
        
        //ImpulsionelNoiseAdd("D:\\icone.jpg" , "D:\\icone24.jpg");
        //GaussianNoiseAdd("D:\\icone.jpg" , "D:\\icone25.jpg");
        
      
        //MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone26_1.jpg", 1, 1);
        //MoyenNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\icone27_1.jpg", 1, 1);
        
       // MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone26.jpg", 1, 50);
       // MoyenNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\icone27.jpg", 1, 50);
       
        //MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone26_2.jpg", 2, 1);
        //MoyenNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\icone27_2.jpg", 2, 1);
        
        //l soura 3al 200 iterations 7a teghma2, leiii?? cz ana 3m 2e2soum 3ala 9 bi une seule iteration... bi 200 iterations 3m 2e2soum 3ala 9^200... alors on tend vers zero ktiir aktar... alors l pixel taba3e 3m y2arib 3al 0 => bada teghma2 aktar
        //  MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone26_4.jpg", 1, 200);
        //  MoyenNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\icone27_3.jpg", 1, 100);
        //   MoyenNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\icone27_4.jpg", 1, 200);
       
        //int[][] conv = {{1,1,1},{1,1,1},{1,1,1}};
        //int[][] conv5 = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        
        //MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone26_2_1.jpg", 1, 1, conv);
        //MoyenNoiseFilter_update("D:\\icone24.jpg" , "D:\\icone26_2_2.jpg", 2, 1, conv5);
        //MedianNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone28_1.jpg", 2, 1);
        //MedianNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone28_2.jpg", 1, 50);
        //MedianNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\icone29_1.jpg", 2, 1);
        
        
        /*System.out.println(System.currentTimeMillis());
        MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\test123.jpg", 1, 1, conv);
        System.out.println(System.currentTimeMillis());
        MedianNoiseFilter_iterative("D:\\icone25.jpg" , "D:\\test124.jpg", 1, 1);
        System.out.println(System.currentTimeMillis());*/
        
       /* int[][] conv_gauss = {{1,2,1},{2,4,2},{1,2,1}};
        int[][] conv5_gauss = {{1,2,3,2,1},{2,4,6,4,2},{3,6,9,6,3},{2,4,6,4,2},{1,2,3,2,1}};
        MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone24_gauss3.jpg", 1, 1, conv_gauss);
        MoyenNoiseFilter_iterative("D:\\icone24.jpg" , "D:\\icone24_gauss5.jpg", 2, 1, conv5_gauss);*/
       
       //binary("D:\\icone.jpg", "D:\\iconeb1.jpg", 105, 1);
       //errosion("D:\\iconeb1.jpg", "D:\\iconeb2.jpg", 1);
       //dilatation("D:\\iconeb1.jpg", "D:\\iconeb3.jpg", 1);
        
       //errosion("D:\\iconeb1.jpg", "D:\\iconeb2_1.jpg", 2);
       //dilatation("D:\\iconeb1.jpg", "D:\\iconeb3_1.jpg", 2);
       
       //contour("D:\\iconeb1.jpg", "D:\\iconeb4.jpg", 1);
       //errosion("D:\\Picture1.jpg", "D:\\sites1.jpg", 1);
       //dilatation("D:\\Picture1.jpg", "D:\\sites2.jpg", 1);
       //contour("D:\\Picture1.jpg", "D:\\sites3.jpg", 1);
       
       //errosion_cross("D:\\iconeb1.jpg", "D:\\iconeb2_3.jpg", 2);
       
       
       //filtre gauss 5x5
       int[][] conv5_gauss = {{2,4,5,4,2},{4,9,12,9,4},{5,12,15,12,5},{4,9,12,9,4},{2,4,5,4,2}};
       int[][] conv51_gauss = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
       
       int[][] kgx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
       int[][] kgy = {{1, 2, 1}, {0,0,0}, {-1, -2, -1}};
       
       //grayscale("D:\\icone24.jpg", "D:\\icone24g.jpg");
       //MoyenNoiseFilter_iterative("D:\\icone24g.jpg" , "D:\\icone_z1.jpg", 2, 1, conv51_gauss);
       
       //MoyenNoiseFilter_iterative("D:\\icone24g.jpg" , "D:\\icone_z11.jpg", 2, 1, conv51_gauss);
       
       //soble("D:\\icone_z1.jpg" , "D:\\icone_z2.jpg", 1, 1, kgx, kgy);
       //soble("D:\\icone_z11.jpg" , "D:\\icone_z22.jpg", 1, 1, kgx, kgy);
       
       //gradient("D:\\icone_z11.jpg" , "D:\\icone_z222.jpg");
       
       /*grayscale("D:\\IMAGEContour.jpg", "D:\\IMAGEContourg.jpg");
       MoyenNoiseFilter_iterative("D:\\IMAGEContourg.jpg" , "D:\\IMAGEContour_z1.jpg", 2, 1, conv5_gauss);
       soble("D:\\IMAGEContour_z1.jpg" , "D:\\IMAGEContour_z2.jpg", 1, 1, kgx, kgy);*/
       
       int[][] carre = {{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
       //laplace("D:\\icone24g.jpg" , "D:\\icone24gcarre.jpg", 1, 1, carre);
       
       //sharpen
       int[][] sharp = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
       //laplace("D:\\icone24g.jpg" , "D:\\icone24sharp.jpg", 1, 1, sharp);
       //laplace("D:\\girl.bmp" , "D:\\girl22.bmp", 1, 1, sharp);
       
       int[][] octo = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
       //laplace("D:\\icone24g.jpg" , "D:\\icone24gocto_sansFiltrage.jpg", 1, 1, octo);
       //laplace("D:\\icone_z11.jpg" , "D:\\icone24gocto_avecFiltrage.jpg", 1, 1, octo);
       
       //apres filtrage
       //laplace_etirement("D:\\icone_z11.jpg" , "D:\\icone24gcarre_etirement.jpg", 1, 1, carre);
       //laplace_etirement("D:\\icone_z11.jpg" , "D:\\icone24gocto_etirement.jpg", 1, 1, octo);
       
       //MoyenNoiseFilter_iterative("D:\\girl.bmp" , "D:\\girl1.bmp", 2, 1, conv5_gauss);
       //laplace_etirement("D:\\girl1.bmp" , "D:\\girl2.bmp", 1, 1, octo);
       //laplace("D:\\girl1.bmp" , "D:\\girl23.bmp", 1, 1, octo);
       
       //kCluster("D:\\segmentation1.JPG", "D:\\segmentation4.JPG");
       
       kCluster2("D:\\segmentation1.JPG", "D:\\segmentation5.JPG", 5, 100);
       //kCluster("D:\\icone.jpg", "D:\\iconeK.jpg");
       
    }
}

 