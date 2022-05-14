/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2_bddm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author TT
 */
public class splitAndMerge extends javax.swing.JFrame {

    
    
    public splitAndMerge() {
        initComponents();
        
        
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tp2_bddm/splitmerge.JPG"))); // NOI18N

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("jLabel3");

        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel4.setText("jLabel4");

        jSlider2.setMaximum(255);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(375, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(55, 55, 55)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(333, 333, 333))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public int noAlfa(int x)
    {
        Color c = new Color(x);
        int v = 0;
        v |= (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue();
        return v;
    }
    public double Sigma (int[][] pix, int sn, double n, int sm, double m)
    {
        double sum = 0;
        double avg;
        for(int i = sn ; i < n ; i++)
        {
            for(int j = sm ; j < m ; j++)
            {
                sum += pix[i][j];
            }
        }
        avg = (sum) / ((n-sn)*(m-sm));
        
        double var = 0;
        for(int i = sn ; i < n ; i++)
        {
            for(int j = sm ; j < m ; j++)
            {
                var += Math.pow(pix[i][j] - avg, 2);
            }
        }
        
        var = ((n-sn)*(m-sm) == 0) ? 0 : var/((n-sn)*(m-sm));
        //var /= ((n-sn)*(m-sm));
        double sigma = Math.sqrt(var);
        return sigma;
    }
    
    public double Sigma(ArrayList<Integer> a)
    {
        double average = a.stream().mapToInt(val -> val).average().orElse(0.0);
        double sum = 0;
        for(int i = 0 ; i < a.size() ; i++)
        {
            sum += Math.pow(Math.abs(a.get(i) - average),2);
        }
        double var = sum / a.size();
        //System.out.println(var);
        return Math.sqrt(var);
        
    }
    
   
    
 
    
    int regionCount = 0;
    public void split(int sn, int n, int sm, int m)
    {
        double var = Sigma(pixImg, sn, n, sm, m);
        regionCount++;
        if(var <= jSlider1.getValue())
        {
            for(int i = sn ; i < n ; i++)
            {
                for(int j = sm ; j < m ; j++)
                {
                    regions[i][j] = regionCount;
                    vars[i][j] = var;
                }
            }
            return;
        }
        
        
    

        for(int i = sn ; i < n; i++)
        {
            for(int j = sm ; j < m ; j++)
            {
                values[i][j]++;
            }
        }
        split(sn, sn + (n-sn)/2, sm, sm + (m - sm)/2);
        split(sn, sn + (n-sn)/2, sm + (m-sm)/2, m);
        split(sn + (n-sn)/2, n, sm + (m-sm)/2, m);
        split(sn + (n-sn)/2, n, sm, sm + (m-sm)/2);
    }
    
    public void merge(int h, int w)
    {
        int i = 0;
        int j = 0;
        boolean changed = false;
        while(i < h)
        {
            j = 0;
            while(j < w-1)
            {
                
                //System.out.println(i + " " + j + "==============");
                if(regions[i][j] != regions[i][j+1])
                {
                    //System.out.println(Math.abs(vars[i][j] - vars[i][j+1]));
                    ArrayList<Integer> aaa = new ArrayList<Integer>();
                    for(int q = 0 ; q < h ; q++)
                    {
                        for(int e = 0 ; e < w ; e++)
                        {
                            if(regions[q][e] == regions[i][j] || regions[q][e] == regions[i][j+1])
                                aaa.add(pixImg[q][e]);
                        }
                    }
                    if(Sigma(aaa) < jSlider2.getValue())
                    {
                        ArrayList<Integer> listOfColor = new ArrayList<>();
                        int oldR = regions[i][j+1];
                        int newR = regions[i][j];
                        for(int i1 = 0 ; i1 < h; i1++)
                        {
                            for(int i2 = 0 ; i2 < w ; i2++)
                            {
                                if(regions[i1][i2] == oldR)
                                {
                                    regions[i1][i2] = newR;
                                    listOfColor.add(pixImg[i1][i2]);
                                }
                            }
                        }
                        
                        double sigma = Sigma(listOfColor);
                        for(int i1 = 0 ; i1 < h; i1++)
                        {
                            for(int i2 = 0 ; i2 < w ; i2++)
                            {
                                if(regions[i1][i2] == newR)
                                {
                                    vars[i1][i2] = sigma;
                                }
                            }
                        }
                        
                        
                        
                    }
                }
                
            
                j++;
            }
            i++;
        }
        
        
        
        
        
    }
    
    
    public int[][] pixImg;
    public int[][] values;
    public int[][] regions;
    public double[][] vars;
    
    public int iniVal = 0;
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        regionCount = 0;
        iniVal = 0;
        try {
            BufferedImage img = ImageIO.read(new File("D:\\splitmerge.JPG"));
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            //System.out.println(w + " " + h);
            pixImg = new int[h][];
            values = new int[h][];
            regions = new int[h][];
            vars = new double[h][];
            
            for(int i = 0 ; i < h ; i++)
            {
                pixImg[i] = new int[w];
                values[i] = new int[w];
                regions[i] = new int[w];
                vars[i] = new double[w];
            }
            for(int i = 0 ; i < h ; i++)
            {
                for(int j = 0 ; j < h; j++)
                {
                    pixImg[i][j] = new Color(img.getRGB(i, j)).getRed(); //noAlfa(img.getRGB(i, j));
                    values[i][j] = iniVal;
                }
            }
            //System.out.println(variance(pixImg, 0, 1, 0, 1));
            split(0, h, 0, w);
            
            
            BufferedImage bimg = new BufferedImage(h, w, 1);
            
            Color[] colors = {Color.WHITE, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
            
            for(int i = 0 ; i < h ; i++)
            {
                for(int j = 0 ; j < w ; j++)
                {
                   //System.out.print(values[i][j] + " ");
                    int index = (values[i][j] < colors.length) ? values[i][j] : 0;
                    bimg.setRGB(i, j, colors[index].getRGB());
                }
                //System.out.println();
            }
            
            jLabel3.setIcon(new ImageIcon(bimg));
            System.out.println(regionCount);
            /*merge(h, w);
            BufferedImage bimg2 = new BufferedImage(h, w, 1);
            ArrayList<Integer> rnb = new ArrayList<>();
            for(int i = 0 ; i < h ; i++)
            {
                for(int j = 0 ; j < w ; j++)
                {
                   //System.out.print(vars[i][j] + " ");
                   if(!rnb.contains(regions[i][j]))
                   {
                        rnb.add(regions[i][j]);
                   }
                    int index = 0;
                    for(index = 0 ; index < rnb.size() ; index++)
                    {
                        if(rnb.get(index) == regions[i][j])
                            break;
                        
                    }
                    index = (index < colors.length) ? index : 0;
                    bimg2.setRGB(i, j, colors[index].getRGB());
                   
                }
                //System.out.println();
            }
            System.out.println(rnb.size());
            jLabel4.setIcon(new ImageIcon(bimg2));*/
            
        } catch (IOException ex) {
            Logger.getLogger(splitAndMerge.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        //System.out.println(jSlider1.getValue());
    }//GEN-LAST:event_jSlider1StateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(splitAndMerge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(splitAndMerge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(splitAndMerge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(splitAndMerge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new splitAndMerge().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables
}
