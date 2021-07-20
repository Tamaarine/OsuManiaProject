package GameJava;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class EndScreen extends JPanel
{
    private int continueornot;
    private JFrame gui;
    private JLabel flyingscore;
    private JLabel numscore;
    private int scounter;
    private int score;
    private JButton yes;
    private JButton no;
    private ImageIcon endbg;
    private JLabel background;
    private ImageIcon icon;
    
    private URL link;
    private AudioInputStream audioinput;
    private Clip clip;
    
    public EndScreen(Game game)
    {
        loadLastSoundByeBye();
        score=game.getActualScore();
        
        continueornot=-1;
        
        gui=new JFrame("Thank you for playing!!!!");
        
        //Instanating all the JLabels
        flyingscore=new JLabel("Score:");
        
        String scorestring=score+"";
        
        numscore=new JLabel(scorestring);
        yes=new JButton();
        no=new JButton();
        endbg=new ImageIcon(EndScreen.class.getResource("/Image Source/end.png"));
        background=new JLabel(endbg);
        icon=new ImageIcon(EndScreen.class.getResource("/Image Source/icon.png"));
        
        gui.setIconImage(icon.getImage());
        
        scounter=0;
        
        gui.setResizable(false);
        
        gui.setVisible(true);
        
        gui.setLayout(null);
        
        gui.getContentPane().add(this);
        
        gui.setSize(new Dimension(500,500));
        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        this.setVisible(true);
        
        this.setSize(new Dimension(500,500));
        
        this.setLayout(null);
        
        gui.setLocationRelativeTo(null);
        
        //Adding in all the JLabels into this
        this.add(background);
        background.add(flyingscore);
        background.add(numscore);
        background.add(yes);
        background.add(no);
        
        background.setBounds(0,0,500,500);
        //Setting all the JLabel to visible
        flyingscore.setVisible(true);
        yes.setVisible(true);
        no.setVisible(true);
        numscore.setVisible(true);
        background.setVisible(true);
        
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        while(scounter!=100)
        {
            flyingscore.setForeground(new Color(220,5,55));
            flyingscore.setFont(new Font("Candara",Font.PLAIN,40));
            flyingscore.setBounds(scounter,0,400,200);
            scounter++;
            
            try
            {
                Thread.sleep(10);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        numscore.setForeground(new Color(34,23,243));
        numscore.setFont(new Font("Candara",Font.PLAIN,40));
        numscore.setBounds(220,0,200,200);
        playLastSoundByeBye();
        
        yes.setBounds(100,400,100,50);
        no.setBounds(300,400,100,50);
        
        yes.setText("Play again!");
        no.setText("I'm leaving!");
        
        yes.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                continueornot=0;
            }
        });
        
        no.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                continueornot=1;
            }
        });
        
        while(continueornot==-1)
        {
            System.out.println(continueornot);
            System.out.println("Still choosing");
        }
    }
    
    public void playLastSoundByeBye()
    {
        clip.start();
    }
    
    public void stopLastSoundByeBye()
    {
        clip.stop();
    }
    
    public void loadLastSoundByeBye()
    {
        try
        {
                link=EndScreen.class.getResource("/Song source/phew.wav");

            audioinput=AudioSystem.getAudioInputStream(link);

            clip=AudioSystem.getClip();

            clip.open(audioinput);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int getReplay()
    {
        return continueornot;
    }
    
    public void close()
    {
        gui.dispose();
    }
    
    
}