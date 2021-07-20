package GameJava;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class LoadingSection extends JPanel implements ActionListener
{
    private JFrame gui;
    private ImageIcon icon;
    private Image image;
    private float alpha;
    private Timer timer1;
    private int i;
    private Image guiicon;
    
    public LoadingSection()
    {
        gui=new JFrame();
        
        gui.setLayout(null);
        
        gui.getContentPane().add(this);
        
        icon=new ImageIcon(LoadingSection.class.getResource("/Image Source/prettybackground.jpg"));
        image=icon.getImage();
        guiicon=Toolkit.getDefaultToolkit().getImage(LoadingSection.class.getResource("/Image Source/icon.png"));
        
        i=0;
        
        //timer1=new Timer(20,this);
        alpha=1f;
        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.getContentPane().setPreferredSize(new Dimension(500,500));
        gui.setIconImage(guiicon);
        gui.setResizable(false);
        
        this.setLayout(null);
        this.setBounds(0,0,520,520);
        this.setVisible(true);
        gui.pack();
        gui.setLocationRelativeTo(null);
        
        for(int k=0;k<150;k++)
        {
            alpha += -0.005f;
            if (alpha <= 0) 
            {
              alpha = 0;
            }
            loop();
            i++;
            try
            {
                Thread.sleep(20);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        gui.dispose();
    }
    
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
       
        g.setColor(new Color(13,201,46));
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(image,0,0,null);
        g.fillRect(0,0,100,i*7);
        g.fillRect(0,0,i*7,100);
        g.fillRect(580,690,20,10);
        
       g.setColor(new Color(16,255,217));
        g.setFont(new Font("Centaur",Font.BOLD,50));
        g2d.drawString("Loading Game...",150,400);
       
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        alpha += -0.01f;
        if (alpha <= 0) 
        {
          alpha = 0;
          timer1.stop();
        }
        loop();
    }
    
    public void loop()
    {
        repaint();
    }
    
    
    
}