package GameJava;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.awt.image.ImageWatched.Link;

public class GameMenu extends JPanel
{
    private JProgressBar loadbar;
    private final JSlider select=new JSlider();
    
    private int i=0;
    private int selecti=0;
    private int songchoice=-1;
    
    private Image icon;
    private JFrame gui;
    private JFrame howtoframe;
    private JFrame creditframe;
    
    //Instance variables for the images
    private ImageIcon transformation;
    private ImageIcon grayedoutbg;
    private ImageIcon vantablackbg;
    private ImageIcon oshamabg;
    private ImageIcon bigblackbg;
    private ImageIcon yellowsplashbg;
    private ImageIcon alephbg;
    private ImageIcon calamitybg;
    private ImageIcon borderpicture;
    private ImageIcon songbgpicture;
    private ImageIcon mikugif;
    private ImageIcon howtobg;
    private ImageIcon creditbg;
    
    private JLabel freedomdivej;
    private JLabel grayedoutbgj;
    private JLabel vantablackj;
    private JLabel oshamaj;
    private JLabel bigblack;
    private JLabel yellowsplash;
    private JLabel aleph;
    private JLabel calamity;
    private JLabel border;
    private JLabel loading;
    private JLabel loaddone;
    private JLabel miku;
    private JLabel songbackground;
    private JLabel howtolabel;
    private JLabel creditlabel;
    
    private JButton howto;
    private JButton credit;
    private JButton play;
    
    private JTextField sname;
    
    private URL link;
    private AudioInputStream audioinput;
    private Clip clip;
    
    public GameMenu()
    {
        loadbar=new JProgressBar();
        gui=new JFrame();
        loading=new JLabel();
        loaddone=new JLabel();
        mikugif=new ImageIcon(GameMenu.class.getResource("/Image Source/miku.gif"));
        miku=new JLabel(mikugif);
        icon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Image Source/icon.png"));
        songbgpicture=new ImageIcon(GameMenu.class.getResource("/Image Source/songbackground.png"));
        songbackground=new JLabel(songbgpicture);
        howtoframe=new JFrame();
        creditframe=new JFrame();
        
        transformation=new ImageIcon(GameMenu.class.getResource("/Image Source/transformation.png"));
        grayedoutbg=new ImageIcon(GameMenu.class.getResource("/Image Source/grayedoutbg.png"));
        vantablackbg=new ImageIcon(GameMenu.class.getResource("/Image Source/vantablackbg.png"));
        oshamabg=new ImageIcon(GameMenu.class.getResource("/Image Source/oshamabg.png"));
        bigblackbg=new ImageIcon(GameMenu.class.getResource("/Image Source/bigblack.png"));
        yellowsplashbg=new ImageIcon(GameMenu.class.getResource("/Image Source/yellowsplash.png"));
        alephbg=new ImageIcon(GameMenu.class.getResource("/Image Source/aleph.png"));
        calamitybg=new ImageIcon(GameMenu.class.getResource("/Image Source/calamity.png"));
        howtobg=new ImageIcon(GameMenu.class.getResource("/Image Source/how.png"));
        creditbg=new ImageIcon(GameMenu.class.getResource("/Image Source/credit.png"));
        
        freedomdivej=new JLabel(transformation);
        grayedoutbgj=new JLabel(grayedoutbg);
        vantablackj=new JLabel(vantablackbg);
        oshamaj=new JLabel(oshamabg);
        bigblack=new JLabel(bigblackbg);
        yellowsplash=new JLabel(yellowsplashbg);
        aleph=new JLabel(alephbg);
        calamity=new JLabel(calamitybg);
        howtolabel=new JLabel(howtobg);
        creditlabel=new JLabel(creditbg);
        
        howto=new JButton();
        credit=new JButton();
        play=new JButton();
        
        howto.setVisible(true);
        credit.setVisible(true);
        play.setVisible(true);
        howtolabel.setVisible(true);
        creditlabel.setVisible(true);
        
        howtoframe.getContentPane().setPreferredSize(new Dimension(710,800));
        howtoframe.pack();
        howtoframe.setLocationRelativeTo(null);
        howtoframe.add(howtolabel);
        howtoframe.setIconImage(icon);
        howtolabel.setBounds(0,0,800,756);
        
        creditframe.getContentPane().setPreferredSize(new Dimension(800,756));
        creditframe.pack();
        creditframe.setLocationRelativeTo(null);
        creditframe.add(creditlabel);
        creditframe.setIconImage(icon);
        creditlabel.setBounds(0,0,710,800);
        
        sname=new JTextField();
        sname.setVisible(true);
        
        borderpicture=new ImageIcon(GameMenu.class.getResource("/Image Source/border.png"));
        border=new JLabel(borderpicture);
        
        gui.setResizable(false);

        gui.setIconImage(icon);

        this.setLayout(null);

        gui.setLayout(null);

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui.setVisible(true);

        gui.getContentPane().setPreferredSize(new Dimension(700,500));

        gui.getContentPane().add(this);

        this.setBounds(0,0,700,500);

        gui.pack();

        gui.setLocationRelativeTo(null);

        this.add(loaddone);
        this.add(loading);
        this.add(miku);

        miku.setVisible(true);
        miku.setBounds(210,8,300,250);


        loaddone.setVisible(true);
        loading.setVisible(true);

        loaddone.setBounds(260,0,500,600);
        loading.setBounds(550,350,200,200);

        loading.setForeground(Color.WHITE);
        loading.setFont(new Font("Kristen ITC",Font.PLAIN,25));

        loading.setText("Loading.....");

        secondThread();

        while(i!=700)
        {
            loop();

            i++;

            try
            {
                Thread.sleep(4);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        loading.setBounds(500,350,200,200);
        loading.setText("Load Complete");

        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        gui.dispose();
        
        playIntroSong();      
        final JFrame songpane=new JFrame();
        songpane.setVisible(true);
        songpane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        songpane.setLayout(null);
        songpane.setLocationRelativeTo(null);
        songpane.getContentPane().setPreferredSize(new Dimension(700,600));
        songpane.setIconImage(icon);
        songpane.pack();

        //Adding all the buttons into the songpane
        songpane.add(howto);
        songpane.add(credit);
        songpane.add(play);
        songpane.add(sname);

        //Settin the location for each button
        howto.setBounds(12,10,100,50);
        howto.setText("Learn Play");

        credit.setBounds(12,60,100,50);
        credit.setText("Credit");

        play.setBounds(520,550,160,50);
        play.setText("Play!");

        //Adding the textfield to songpane for displaying the select song name
        songpane.add(sname);
        sname.setEditable(false);
        sname.setHorizontalAlignment(JTextField.CENTER);

        //Setting the placement for the sname
        sname.setBounds(200,470,300,70);

        howto.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                howtoframe.setVisible(true);
            }
        });

        credit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                creditframe.setVisible(true);
            }
        });
        
        play.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectvalue=select.getValue();
                System.out.println("The song choice is "+selectvalue);
                songchoice=selectvalue;
            }
        });

        select.setVisible(true);
        select.setPaintLabels(false);
        select.setPaintTicks(true);

        songpane.getContentPane().add(select);
        songpane.add(songbackground);
        songbackground.setVisible(true);
        songbackground.setBounds(0,0,700,600);

        songbackground.add(border);
        border.setVisible(true);
        border.setBounds(125,10,450,450);

        select.setBounds(200,550,300,50);
        select.setOpaque(false);

        //select.setMajorTickSpacing(5);
        select.setMinorTickSpacing(1);
        select.setMinimum(0);
        select.setMaximum(7);
        select.setSnapToTicks(true);
        
        sname.setFont(new Font("Arial Black",Font.PLAIN,25));
        
        //Adding a slider listener
        select.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                selecti=(select).getValue();

                border.add(oshamaj);
                border.add(vantablackj);
                border.add(freedomdivej);
                border.add(grayedoutbgj);
                border.add(bigblack);
                border.add(yellowsplash);
                border.add(aleph);
                border.add(calamity);

                //This one for oshama
                if(selecti==0)
                {
                    oshamaj.setBounds(0,0,450,450);
                    oshamaj.setVisible(true);

                    vantablackj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Oshama Scramble");
                }
                //This one is for vantablack
                else if(selecti==1)
                {
                    vantablackj.setBounds(0,0,450,450);
                    vantablackj.setVisible(true);

                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Vantablack");
                }
                //This one is for freedomdive
                else if(selecti==2)
                {
                    freedomdivej.setBounds(0,0,450,450);
                    freedomdivej.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Transformation");
                }
                //This one is for grayedout
                else if(selecti==3)
                {
                    grayedoutbgj.setBounds(0,0,450,450);
                    grayedoutbgj.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    bigblack.setVisible(false);
                    yellowsplash.setVisible(false);
                    aleph.setVisible(false);
                    calamity.setVisible(false);

                    sname.setText("Grayed out");
                }
                //This one is for big black
                else if(selecti==4)
                {
                    bigblack.setBounds(0,0,450,450);
                    bigblack.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    yellowsplash.setVisible(false);
                    aleph.setVisible(false);
                    calamity.setVisible(false);

                    sname.setText("Big Black");
                }
                //This one is for yellow splash
                else if(selecti==5)
                {
                    yellowsplash.setBounds(0,0,450,450);
                    yellowsplash.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);
                    aleph.setVisible(false);
                    calamity.setVisible(false);

                    sname.setText("Yellow Splash");
                }
                //This one is for aleph null
                else if(selecti==6)
                {
                    aleph.setBounds(0,0,450,450);
                    aleph.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    calamity.setVisible(false);
                    bigblack.setVisible(false);
                    yellowsplash.setVisible(false);

                    sname.setText("Aleph null");
                }
                //This one is for calamity fortune
                else if(selecti==7)
                {
                    calamity.setBounds(0,0,450,450);
                    calamity.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    aleph.setVisible(false);
                    yellowsplash.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Calamity Fortune");
                }
            }
        });

        songpane.setLocationRelativeTo(null);
        
        
        select.setValue(0);
        
        while(songchoice==-1)
        {
            System.out.println("Still in picking");
        }
        
        stopSong();
        
        songpane.dispose();
    }
    
    public GameMenu(boolean discardload)
    {
        loadbar=new JProgressBar();
        gui=new JFrame();
        loading=new JLabel();
        loaddone=new JLabel();
        mikugif=new ImageIcon(GameMenu.class.getResource("/Image Source/miku.gif"));
        miku=new JLabel(mikugif);
        icon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Image Source/icon.png"));
        songbgpicture=new ImageIcon(GameMenu.class.getResource("/Image Source/songbackground.png"));
        songbackground=new JLabel(songbgpicture);
        howtoframe=new JFrame();
        creditframe=new JFrame();
        
        transformation=new ImageIcon(GameMenu.class.getResource("/Image Source/transformation.png"));
        grayedoutbg=new ImageIcon(GameMenu.class.getResource("/Image Source/grayedoutbg.png"));
        vantablackbg=new ImageIcon(GameMenu.class.getResource("/Image Source/vantablackbg.png"));
        oshamabg=new ImageIcon(GameMenu.class.getResource("/Image Source/oshamabg.png"));
        bigblackbg=new ImageIcon(GameMenu.class.getResource("/Image Source/bigblack.png"));
        yellowsplashbg=new ImageIcon(GameMenu.class.getResource("/Image Source/yellowsplash.png"));
        alephbg=new ImageIcon(GameMenu.class.getResource("/Image Source/aleph.png"));
        calamitybg=new ImageIcon(GameMenu.class.getResource("/Image Source/calamity.png"));
        howtobg=new ImageIcon(GameMenu.class.getResource("/Image Source/how.png"));
        creditbg=new ImageIcon(GameMenu.class.getResource("/Image Source/credit.png"));
        
        freedomdivej=new JLabel(transformation);
        grayedoutbgj=new JLabel(grayedoutbg);
        vantablackj=new JLabel(vantablackbg);
        oshamaj=new JLabel(oshamabg);
        bigblack=new JLabel(bigblackbg);
        yellowsplash=new JLabel(yellowsplashbg);
        aleph=new JLabel(alephbg);
        calamity=new JLabel(calamitybg);
        howtolabel=new JLabel(howtobg);
        creditlabel=new JLabel(creditbg);
        
        howto=new JButton();
        credit=new JButton();
        play=new JButton();
        
        howto.setVisible(true);
        credit.setVisible(true);
        play.setVisible(true);
        howtolabel.setVisible(true);
        creditlabel.setVisible(true);
        
        howtoframe.getContentPane().setPreferredSize(new Dimension(710,800));
        howtoframe.pack();
        howtoframe.setLocationRelativeTo(null);
        howtoframe.add(howtolabel);
        howtolabel.setBounds(0,0,800,756);
        
        creditframe.getContentPane().setPreferredSize(new Dimension(800,756));
        creditframe.pack();
        creditframe.setLocationRelativeTo(null);
        creditframe.add(creditlabel);
        creditlabel.setBounds(0,0,710,800);
        
        sname=new JTextField();
        sname.setVisible(true);
        
        borderpicture=new ImageIcon(GameMenu.class.getResource("/Image Source/border.png"));
        border=new JLabel(borderpicture);
        
        if(!discardload)
        {
            gui.setResizable(false);

            gui.setIconImage(icon);

            this.setLayout(null);

            gui.setLayout(null);

            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            gui.setVisible(true);

            gui.getContentPane().setPreferredSize(new Dimension(700,500));

            gui.getContentPane().add(this);

            this.setBounds(0,0,700,500);

            gui.pack();

            gui.setLocationRelativeTo(null);

            this.add(loaddone);
            this.add(loading);
            this.add(miku);

            miku.setVisible(true);
            miku.setBounds(210,8,300,250);


            loaddone.setVisible(true);
            loading.setVisible(true);

            loaddone.setBounds(260,0,500,600);
            loading.setBounds(550,350,200,200);

            loading.setForeground(Color.WHITE);
            loading.setFont(new Font("Kristen ITC",Font.PLAIN,25));

            loading.setText("Loading.....");

            secondThread();

            while(i!=700)
            {
                loop();

                i++;

                try
                {
                    Thread.sleep(4);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            loading.setBounds(500,350,200,200);
            loading.setText("Load Complete");

            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            gui.dispose();
        }
        
        playIntroSong();      
        final JFrame songpane=new JFrame();
        songpane.setVisible(true);
        songpane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        songpane.setLayout(null);
        songpane.setLocationRelativeTo(null);
        songpane.getContentPane().setPreferredSize(new Dimension(700,600));
        songpane.setIconImage(icon);
        songpane.pack();
        
        //Adding all the buttons into the songpane
        songpane.add(howto);
        songpane.add(credit);
        songpane.add(play);
        songpane.add(sname);

        //Settin the location for each button
        howto.setBounds(12,10,100,50);
        howto.setText("Learn Play!");

        credit.setBounds(12,60,100,50);
        credit.setText("Credit");
        
        play.setBounds(520,550,160,50);
        play.setText("Play!");

        //Adding the textfield to songpane for displaying the select song name
        songpane.add(sname);
        sname.setEditable(false);
        sname.setHorizontalAlignment(JTextField.CENTER);

        //Setting the placement for the sname
        sname.setBounds(200,470,300,70);

        howto.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                howtoframe.setVisible(true);
                
            }
        });

        credit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                creditframe.setVisible(true);
            }
        });
        
        play.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectvalue=select.getValue();
                System.out.println("The song choice is "+selectvalue);
                songchoice=selectvalue;
            }
        });

        select.setVisible(true);
        select.setPaintLabels(false);
        select.setPaintTicks(true);

        songpane.getContentPane().add(select);
        songpane.add(songbackground);
        songbackground.setVisible(true);
        songbackground.setBounds(0,0,700,600);

        songbackground.add(border);
        border.setVisible(true);
        border.setBounds(125,10,450,450);

        select.setBounds(200,550,300,50);
        select.setOpaque(false);

        //select.setMajorTickSpacing(5);
        select.setMinorTickSpacing(1);
        select.setMinimum(0);
        select.setMaximum(7);
        select.setSnapToTicks(true);
        
        sname.setFont(new Font("Arial Black",Font.PLAIN,25));
        
        //Adding a slider listener
        select.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                selecti=(select).getValue();

                border.add(oshamaj);
                border.add(vantablackj);
                border.add(freedomdivej);
                border.add(grayedoutbgj);
                border.add(bigblack);
                border.add(yellowsplash);
                border.add(aleph);
                border.add(calamity);

                //This one for oshama
                if(selecti==0)
                {
                    oshamaj.setBounds(0,0,450,450);
                    oshamaj.setVisible(true);

                    vantablackj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Oshama Scramble");
                }
                //This one is for vantablack
                else if(selecti==1)
                {
                    vantablackj.setBounds(0,0,450,450);
                    vantablackj.setVisible(true);

                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Vantablack");
                }
                //This one is for freedomdive
                else if(selecti==2)
                {
                    freedomdivej.setBounds(0,0,450,450);
                    freedomdivej.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Transformation");
                }
                //This one is for grayedout
                else if(selecti==3)
                {
                    grayedoutbgj.setBounds(0,0,450,450);
                    grayedoutbgj.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    bigblack.setVisible(false);
                    yellowsplash.setVisible(false);
                    aleph.setVisible(false);
                    calamity.setVisible(false);

                    sname.setText("Grayed out");
                }
                //This one is for big black
                else if(selecti==4)
                {
                    bigblack.setBounds(0,0,450,450);
                    bigblack.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    yellowsplash.setVisible(false);
                    aleph.setVisible(false);
                    calamity.setVisible(false);

                    sname.setText("Big Black");
                }
                //This one is for yellow splash
                else if(selecti==5)
                {
                    yellowsplash.setBounds(0,0,450,450);
                    yellowsplash.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    bigblack.setVisible(false);
                    aleph.setVisible(false);
                    calamity.setVisible(false);

                    sname.setText("Yellow Splash");
                }
                //This one is for aleph null
                else if(selecti==6)
                {
                    aleph.setBounds(0,0,450,450);
                    aleph.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    calamity.setVisible(false);
                    bigblack.setVisible(false);
                    yellowsplash.setVisible(false);

                    sname.setText("Aleph null");
                }
                //This one is for calamity fortune
                else if(selecti==7)
                {
                    calamity.setBounds(0,0,450,450);
                    calamity.setVisible(true);

                    vantablackj.setVisible(false);
                    oshamaj.setVisible(false);
                    freedomdivej.setVisible(false);
                    grayedoutbgj.setVisible(false);
                    aleph.setVisible(false);
                    yellowsplash.setVisible(false);
                    bigblack.setVisible(false);

                    sname.setText("Calamity Fortune");
                }
            }
        });

        songpane.setLocationRelativeTo(null);
        
        
        select.setValue(0);
        
        while(songchoice==-1)
        {
            System.out.println("Still in picking");
        }
        
        stopSong();
        
        songpane.dispose();
    }
    
    public void secondThread()
    {
        Thread thread2=new Thread()
        {
            public void run()
            {
                while(i!=700)
                {
                    loaddone.setForeground(Color.green);
                    loaddone.setFont(new Font("Agency FB",Font.BOLD,30));
                    
                    loaddone.setText(randomText());

                   try
                   {
                       Thread.sleep(290);
                   }
                   catch(Exception e)
                   {
                       e.printStackTrace();
                   }
                }
                
               
            }
        };
        thread2.start();
    }
    
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);

        g.fillRect(0,0,700,500);

        g.setColor(Color.CYAN);

        g.fillRect(0,240,i,20);

        g.setColor(new Color(178,255,102));


        g.fillRect(0,0,700,10);
        g.fillRect(0,0,10,500);
        g.fillRect(690,0,700,500);
        g.fillRect(0,490,700,500);   

    }
    
    public void loop()
    {
        repaint();
    }
    
    public void init()
    {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Setting the layout manager to null
        gui.setLayout(null);
        
        //Make the GUI visible
        gui.setVisible(true);
        
        //Make the loadbar to have percentage
        loadbar.setStringPainted(true);
        
        System.out.println("hi");
        
        loop();
        
        //Giving the gui a preferfered size
        gui.setPreferredSize(new Dimension(500,500));
        
        //Setting loadbar to visbile
        loadbar.setVisible(true);
        
        //Giving the loadbar a preferred size   
        
        //Adding loadbar to gui
        gui.add(loadbar);
        
        loadbar.setLocation(new Point(0,0));
        
        //Packing the gui together
        gui.pack();
        
        //Setting the frame to the middle of the screen
        gui.setLocationRelativeTo(null);
        
        
        for(int i=0;i<=100;i++)
        {
            loadbar.setValue(i);
            
            try
            {
                Thread.sleep(15);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
    
    public void stayOn(boolean set)
    {
        if(!set)
        {
            gui.dispose();
        }
    }
    
    public static String randomText()
    {
        String output;
        
        ArrayList<String> select=new ArrayList<String>();
        
        select.add("/Loading assets");
        select.add("/Adding music");
        select.add("/Freedom_Diver");
        select.add("/Tiny easter egg");
        select.add("/Loading nothing");
        select.add("/Did you see that?");
        select.add("/Loading...Loading...Error");
        select.add("/Exceptions thrown?");
        select.add("/Fix your program");
        select.add("/Adding picture");
        select.add("/Loading key number 1");
        select.add("/Loading key number 2");
        select.add("/Loading key number 3");
        select.add("/Loading key number 4");
        select.add("/Loading highlight key");
        select.add("/Loading mapsets");
        select.add("/Loading music");
        select.add("/Salts");
        select.add("/Scoring");
        select.add("/Adding scoreboard");
        select.add("/Adding accuracy bar");
        select.add("/Not OSU mania!");
        select.add("/Shhh");
        select.add("/Sinsssss");
        select.add("/Nothing is meant to last forever");
        select.add("/Adding String e");
        select.add("/Common cold");
        select.add("/You may not pass");
        select.add("/More variety!");
        select.add("/BFG");
        select.add("/I'm a monster");
        select.add("/I can feel it. I'm afriad");
        select.add("/Orientation sequence hardware damaged");
        select.add("/Combatant sighted");
        select.add("/Target secured");
        select.add("/NOO!");
        select.add("/Poke");
        select.add("/Stay in your lane");
        select.add("/Welcome to my crosshairs");
        select.add("/That's enough!");
        select.add("/If you see me it's too late");
        select.add("/Get off my lawn");
        select.add("/In my vocabulary, quiver is a noun");
        select.add("/Wink wink next update?");
        select.add("/Totally not copied");
        select.add("/BUTTER!!!!");
        select.add("/WE NEED MOAR!");
        select.add("/Heh I'm not doing my job loading");
        select.add("/End my suffering");
        select.add("/Aleph null");
        select.add("/Freedom dive");
        select.add("/Non existance");
        select.add("/Calamity fortune");
        select.add("/THE BIG BLACK");
        select.add("/SKILL SHOTS");
        select.add("/I'm too full");
        select.add("/HoPe");
        select.add("/One for me and one for you ^ ^");
        select.add("/I LUV YOU");
        select.add("/YAYAYAYAYA");
        select.add("/str1.length()");
        select.add("/No escape sequence needed");
        select.add("/Loading is unncessary");
        select.add("/There is nothing much to load");
        select.add("/Can your CPU handle this?");
        select.add("/BLBUBHUBHBLBUHB");
        select.add("/Misanthrope");
        select.add("/Riposte-a quick and clever reply");
        select.add("/Not enough!");
        select.add("/Next hot deal is within 9999 years");
        select.add("/The loading bar done in 1x10^99 seconds");
        select.add("/What is inheritances?");
        select.add("/How many lines to cross road?");
        select.add("/Oh mah gud");
        select.add("/Best farmer");
        select.add("/Best waifu above");
        select.add("/Loading Denumerable set");
        select.add("/Converge?");
        select.add("/Diverge?");
        select.add("Summation notation");
        select.add("/Linear regression");
        select.add("/Exponential regression");
        select.add("/Sinisudal regression");
        select.add("/GameMenu loaded");
        select.add("/PaintComponent!");
        select.add("/Container may explode");
        select.add("/Plastic bag");
        select.add("/Oh noses");
        select.add("/SILENCES!");
        select.add("/WWW.------.COM");
        select.add("/HEHEHEHEH");
        
        int length=select.size();
        
        int randomindex=(int)(Math.random()*length);
        
        output=select.get(randomindex);
        
        return output;
    }
    
    public int getSongSelect()
    {
        return songchoice;
    }
    
    public void playIntroSong()
    {
        try
        {
            link=GameMenu.class.getResource("/Song Source/intro.wav");
        
            audioinput=AudioSystem.getAudioInputStream(link);
            
            clip=AudioSystem.getClip();
            
            clip.open(audioinput);
            
            clip.start();
            
            clip.loop(10);
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void stopSong()
    {
        clip.stop();
    }
}