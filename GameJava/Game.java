package GameJava;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sun.audio.AudioStream;


//Ricky Lu
public class Game extends JPanel
{
    //The grid instance variable will hld the images that display on the game
    private Grid grid;

    //The userRow instance variable will keep track of which row does the user appear in
    private int userRow;

    //The msElapsed instance variable will keep track of the total milisecond pass since the game started
    private int msElapsed;

    //The timesGet will keep track of the number of things that the user have got
    private int timesGet;
    
    private int combo;
    private int score;
    private int health;
    private int totalhits;
    private int totalnotes;
    
    private final int DECREASE_RATE=2;
    private final int INCREASE_RATE=7;
    private final int penalty=5;
    
    //The timesAvoid will keep track of the total number of times that theuser have been hit by avoid
    private int timesAvoid;

    //Creating a instance varaible that will be controlling the maximum number of avoids that is spawned in grid
    private int maxavoid;

    //Creating a instance variable that will be controlling the maximum number of gets that is spawned in grid
    private int maxitem;

    //Creating a instance variable of URL
    private URL link;

    //Creating a instance variable of Clip
    private Clip clip;

    //Creating a instance varaiable of AudioStream
    private AudioInputStream audioinput;

    //Creating instance variables of String for playing song1 and giving it a value here
    private String oshama="--------DDDDFJJKDFJKKKJKJJDFKKJDFJKDFJKDF-DJFKDJFKKKJKJKKKJKDDFJKJF"
            + "DKJDFJKDFKJDF-DJ=--FDFJJJKJJKDFJKKJKKJJJKFJDKFJJJKKDKFJDJDFDFDJDFJDJDJDJDFKDJFKDJF"
            + "KKKDJFKKKK---DDDDDDJDJDJJJJJFKFFFFFFFKFKKKKKKDFJKDDDDDDJDJDDDDDDDKFKKKKKKKKKKKDJFK"
            + "DFJKDFJKDFJKJDFKJDKFJKKKJKDFJJJDKFJDFJKKJDFJKDFKJDFJKDFKJDFKDJFKDJFKD-------------"
            + "-----------JKKKDJFKDDFJDJFJDJFKDJJJFKKDJFKKDJFKKDJFKKKDFJJDKKKKDJFKDJFKKKJDKDJFKKD--"
            + "FJKDFJJDJDKFKDJFDJDKKKFJDKKFKDJJFKDJFDDFDKDFJKDFKDKddkfDFJDKDDJKKFJKKDFJKKKDFKDJFDFJK"
            + "DFKDDKFJJJJDKKKKKKDDFJK--------------";
    private String yellowsplash="----DDDJJJDDDJJJJJDJJJDDDDJDJD----DJDJJJJDDDDJDDDJDJJJDJDJJHFHFFJJDJ"
            + "FHDJFFFHDFHDJJJDHFJDHFHFFHFHHHDJFDDJFHDHD-DF-F-F-F-FJJJKKK---FFF---DJFKFKDJFKKKJFDF"
            + "JJJJKJKDFFJKFJDKFJDJJJFKDKKKDJFJFKKKJFKFJJJDJFKKKFJDFFFFJDJFKFFFJDKFFJFKFJFJJJFKDJF"
            + "KKKDFJKDFJKDFJKDFJKDFJKD-----------------DD-----FF---JKKK----DDFFJJKK----------DFJK"
            + "DJJJFKKKKDFFFJDKFKKKDFJKKJFDKDFJKDJFKDJFKDJFKDDDFFFDJKKJFDKFJKKJDFDKKKDJFKKKDJFKKDJ"
            + "FKKDJFFFFDKDJFKDJFKDJKDFKKKDJF--F---D--FJDKFJJJJJK----------";
    private String vantablack="----D-----J-------D------K---------D-DDFJJJJDDDDFJJKKJKD-F---F---K----K-D"
            + "---F----D----F----J----K-K-K-K-DD-D-DFKJ---D-F-D-J-K-J-K-D-D-F-F-F-F-F-F-F-F-F-F-F-F-F-F-"
            + "F-FDFJKDFJDFKJDKDJDFJKJDKKJFDJFJJDKFDFJJJDKFJJDKFKKKDJFKKDF--D-FD-D-D-D-FJDKFJJDKFKDFFKDJ"
            + "FDFFKDJFDFKKKJFKDJFKKKKJDKFKKKDJFKKKDJFKKDJFKKKDJFKKDJFKDJJFKDJFKDJFJF--F-F-F-F-F-F-FF-D-F"
            + "-D-FFF-K-FKD-FJJJ-D-F--DJF---DFJKDJFKKDJFKKDJF-F-F-F-F-D-J-J-J-J-J-K-K-K-K-D-D-D-D---DD---"
            + "D-DDDKFJDKFJDKFJKJDKFJDKKKFJDKFJJDKKKFF-DF---DF---DF-DF-DF-DF-DF-DF-DFD-FKDKFDFJKDFJKDFJKK"
            + "JFDKJDFKJDFKJKFDJKFDKJDFJKFDKJKKFJDKFFFJDFKJFDJJFDFFJKKKFJDDJFKKKJDFJJFKFJJD--D-D-DJKDFKKF"
            + "JFKFJD-----------------";
    private String transformation="------DddDDDDJKKKDJDJDFKDKDKDFKDJFKKKDJFKKKKKKKKDDDDJFJDKFJDJFJDJ"
            + "JJKJKKKFKDJFKDJFKDJFKKKDJFFFDKFJKKKDJFJDKFJDJFKDKDKFJDKFJDFJKDFJKDFJKDFJKDFJKDFJKDFJK"
            + "DFJKDFJKDFJKDFKJFDKJFDKJFDKJDF----DFJFJJJJJDJFJFJFJDKKDKDKDKDJFJFJDKJJFJFJFKDJFJFKKKKD"
            + "FDFDJJDDJDJJJFKKKKDJJJKKKKJDJFJJDKKKFJJJDKFKKKKJDKFFJDKKFJDKF----------DDDDDDJJJJJJFFF"
            + "FFKKKKKK-----------------------DDDFJJKDJFKKDJDFKJKFJKJFDKJFDDFJKDJDKFJDKFJDDDJFKDD---"
            + "---------FJDKKKKDJJJJFKFFFKDDDDJFKKKDJJJJFKFFFKDDDDJFKKKJFDDDJFJJJJFFFFKDFFFKDJJJFKDJJ"
            + "FKDJFKDJJJKJFDFKD-----------------------------";
    
    private String bigblack="--------------------DJDDDDKDJJJDKFKKKDJFKKKDJFKDJFKDJJJFKDJJJDDDKFJJJD"
            + "DDDDDJJJJJJJKFKDJKFKDJFKDJFKKKKJKJDFDFDJFKDJFKDFJKDFKKJJDKFJDFJDKFJKJFKDKJDFKFFFFJDJFK"
            + "DKKKDFJFKD-------------D-----------F------JJ-------KK-----D-------D---"
            + "------J------K--D-DD-------D-D-DDDDDDDJFKKKKKKDFFFFFKJJJDKFJDKFJDJJJJDJJJJDJJJJFKFK"
            + "KKDKKKDDJDDFKFFKDJFKDJFKDJJJFKDJFKKDFJFKKFJJJJDKFJD--------FF------D-D----"
            + "--J-J----KK-----DDD----FFF----DF-J-----DFJDJFKDJFKKKDJFKKKDJFKDJFJJJDKFJJJ"
            + "DKFJJJDKKKKKJKDFDDDDDDDJFFFFFFJKKKKKKJJJJJJJDJFJJJDJJJDKDKFKKKDJFKDFJKDDJFJJDJFKKKDJF"
            + "KKKDJFKJDFKJDFDJFJJJDJFKKKDJFJJDFJKKJFDDK-----";
    private String grayedout="---------D---D---D---D----D---D---JJJKKJDKFDDFJKJD-F-F-F-D-D-D-J-J-K-K"
            + "-KJ-D-FDFJKKKKKJJJJFKKKJDFJKKJFDFJKJFDFDJKDKDFKJDKJDFKJDFKJDKJFDKDKDKDJFJJJJJJJJJJJJDD"
            + "DKFKKKKDJJJJFKKDDDDJFFDDJFKKKKDJFJF-F-F-J-J-J-K-D-D-D-F-K-J-J-D-F-J-K-J-D-F-J-K-J-D-F-"
            + "D-D---D---D-D--F-J---K-K--K-KK-KKK---K--DDFJKDFJKDFJKDFJKDKJFDKJFDKJFDKJFDKJFDDKFJDFDJ"
            + "KDFKJDKFKDJFJJJDFDDFJJJDKKFJDKKKFJJJDKFKKKKJDFFDFJDFDKJFKJKJDFJDFDDDKFJJJJDJFKKKDJFKDJ"
            + "FKDJFKKKDJFKKDJFKDFFD--F----DF----F-F--D--F----D--F-J-J-J-J--F--DF--"
            + "J-J-JFK-----";
    private String calamity="--D-DDDD----D---D--DD-F-F-DFDDFJKJKJKDFD-DFDJKFKDJFKKKKJDKFJDKFJJKJDFKKJ"
            + "KJJD--J-J-K-K-D-F-DFKJKKKDJDFJDDDFKJDFKKJKJKKJFKFJKFJDJDKFKFKFJDJDJFKFKDJFJJJD-D-D-D-"
            + "JFJJKJDFD---D---D---D--DJJFKDJFKKKDJ---------F---------J--------DK-----------D------K-"
            + "----J------F------DFJK-----K----KJ--DF---DKF----DFJD-D-FK--J-K-DJF-DDDKFJDKFJDFJKJFDJDK"
            + "FJDKFJDKFJJJDKKKFJDKFKKKDF--DF-DKDFJJDKFKDKKKDKFJJJJDKFKDF-DF-DF-DF-DFJKKKK-KJ-KJ-JKJ-"
            + "JKK-JK--FD-DF-DF-DF-DF-----JK-JK-JK-JK-JK-JK-JK-DF-DF-DF-DF-DF-DF-DF-DFJKJKKJFDKJFDFDF"
            + "JJDKFKDJJFFFD--D-D--F-D-DD-D---DF-J-K--K-K-K-D-DFJKKFJDKFJDKFJ---------------";
    private String alpeh="----------DFJK---KJFD-----DFJK---DF--J-KKJ-DFKJJKDJFKKKJDFJKKJFDDFJDKFKKK"
            + "DKDDDFDFDDDFJKJKJK--F--J---K--D--F-DFJKDFDFDFDFDF-DF-DFFDFJKDFJKDF--F--D--"
            + "--JJ-JJJ-JJ-J-J-J-KKK-K-K-KKK-KK--KKKK-KK-KD-DDF-FFF-F-DDDDFFD--FD-DDFKFKKKJKDDDKDKDDD"
            + "FJKJDKKDKDKDF--DFDDDDFKKKKJJDKKKDKKDJJJKKKKKDJFKDFJDKFDFJKFDKJDFKJDFJJDFKKKDDFJKDFJ"
            + "KDFKJFDKJFDDFJKDFKJDFJFJDKFJD-D-F-D-DDDDDKDKDKDF-DDD--F-DF--F-D--D--DDD----DF-J-J-J-K-"
            + "-D-D-D---F--KF---DDD-DFDKFJDK---D--F--K--K--J---K--D--F--D--K--J--D"
            + "FFJJJJDKFD-----DFJKDFJDDDDFDKKKKDJFKDJJFKDF----DFFJDKKDKFJDKF-D-FJDKDKKKKFJDKKFJ"
            + "DKKK-------------";
    
    private int icounter=0;
    
    //Creating a instance variable that will be holding the key that is pressed
    private int keypressed;
   
    GameMenu startup;
    
    public Game() 
    {
        score=0;
        combo=1;
        health=100;
        totalhits=1;
        totalnotes=1;
        
        startup=new GameMenu();
        
        loadSong(startup.getSongSelect());
        LoadingSection loadupsong=new LoadingSection();
        
        grid = new Grid(10,4);
        
        playSong();
        userRow = 0;
        msElapsed = 0;
        timesGet = 0;
        timesAvoid = 0;
        //grid.setImage(new Location(userRow, 0), "user.gif");
        
        
        updateTitle();
        //This is setting the maximum number of enemies created in a column is 3
        maxavoid=3;

        //This is setting the maximum number of item created in a column is 1
        maxitem=1;
        
        grid.add(this);
        
    }
    
    public Game(boolean discardload)
    {
        score=0;
        combo=1;
        health=100;
        totalhits=1;
        totalnotes=1;
        
        startup=new GameMenu(discardload);
        
        loadSong(startup.getSongSelect());
        LoadingSection loadupsong=new LoadingSection();
        
        grid = new Grid(10,4);
        
        playSong();
        userRow = 0;
        msElapsed = 0;
        timesGet = 0;
        timesAvoid = 0;
        //grid.setImage(new Location(userRow, 0), "user.gif");
        
        
        updateTitle();
        //This is setting the maximum number of enemies created in a column is 3
        maxavoid=3;

        //This is setting the maximum number of item created in a column is 1
        maxitem=1;
        
        grid.add(this);
    }
  
    public void play()
    {
        while (!isGameOver())
        {
            //78 for general
            grid.pause(78);
            handleKeyPress();
            
            if (msElapsed % 300 == 0)
            {
                System.out.println(startup.getSongSelect());
                checkError();
                scrollDown();
                populateTopWith(startup.getSongSelect());
                health=health-DECREASE_RATE;
                grid.setHealth(health);
                grid.setAccuracy(totalnotes,totalhits);
                
            }
            msElapsed += 100;
            icounter++;
        }
        
        stopSong();
        grid.close();
    }
  
    
    public void handleKeyPress()
    {
        //Default key pressing will be the letter DFJK within that order
        keypressed=grid.checkLastKeyPressed();
        
        System.out.println("The key pressed is "+keypressed);
        
        //Getting the row of the grid
        int row=grid.getNumRows();
        
        //Note for the numbering of the key
        //Note sweet spot refers to the line where note is actually received
        //D is the int value 68
        //F is the int value 70
        //J is the int value 74
        //K is the int value 75
        //This means that the user have pressed the first key note on the row therefore we must check whether
        //there is a note that is landing on the sweet spot, if there is then we have to remove the note since the user have timed it correctly
        if(keypressed==68)
        {
            //This is getting the image that is on the sweet spot
            String currentimage=grid.getImage(new Location(row-3,0));
            
            //This is making sure that there is actually a note on the sweet spot and not a empty space
            //and because the first column will only contain note1 we know for sure that it is note1 that is on the first column
            if(currentimage!=null)
            {
                //Since we are on the first row then it is for sure that it is a note1 on the sweet spot thus we have to remove it
                //from the sweet spot because the user have timed it correctly
                //grid.setImage(new Location(row-3,0),"/Image Source/vantablack.png");
                grid.setImage(new Location(row-3,0),null);
                score=score+100*combo;
        
                grid.setScore(score);
        
                combo++;
                totalhits++;
                totalnotes++;
                
                if(health<100)
                {
                    health=health+INCREASE_RATE;
                    grid.setHealth(health);
                }
                
            }
            //This means that the player have pressed when there is nothing on the sweetspot thus the combo is breaked
            else
            {
                combo=1;
                grid.setCombo(combo);
                
                health=health-DECREASE_RATE;
                grid.setHealth(health);
            }
        }
        //This means that the user have pressed the second key note on the second row thus we have to check
        //whether there is a note on the sweet spot
        else if(keypressed==70)
        {
            //This is getting the image that is on the sweet spot
            String currentimage=grid.getImage(new Location(row-3,1));
            
            //This means that there is actually a note on the sweet spot, thus we have to remove it from it since the user have timed
            //their presses correctly, and since we know that note2 belongs in the second column we don't need another condition to check
            if(currentimage!=null)
            {
                //Removing note2 from it's sweet spot since the user have timed it correctly
                grid.setImage(new Location(row-3,1),null);
                score=score+100*combo;
        
                grid.setScore(score);
        
                combo++;
                totalhits++;
                totalnotes++;
                
                if(health<100)
                {
                    health=health+INCREASE_RATE;
                    grid.setHealth(health);
                }
            }
            //This means that the player have pressed when there is nothing on the sweetspot thus the combo is breaked
            else
            {
                combo=1;
                grid.setCombo(combo);
                health=health-DECREASE_RATE;
                grid.setHealth(health);
            }
        }
        //Similar to the other ones this means that the user have pressed the third key so we must check
        //on whethere there is actually a note on the sweet spot
        else if(keypressed==74)
        {
            //This is getting the image that is on the sweet spot ON the third column corresponding to note3
            String currentimage=grid.getImage(new Location(row-3,2));
            
            //This is to make sure that there is actually a note3 on the sweet spot so we wouldn't be removing empty spaces
            //but only the notes that is there
            if(currentimage!=null)
            {
                //Removing note3 from third column from it's sweet spot
                grid.setImage(new Location(row-3,2),null);
                score=score+100*combo;
        
                grid.setScore(score);
        
                combo++;
                totalhits++;
                totalnotes++;
                
                if(health<100)
                {
                    health=health+INCREASE_RATE;
                    grid.setHealth(health);
                }
            }
            //This means that the player have pressed when there is nothing on the sweetspot thus the combo is breaked
            else
            {
                combo=1;
                grid.setCombo(combo);
                
                health=health-DECREASE_RATE;
                grid.setHealth(health);
            }
        }
        //This means that the user have pressed the fourth key thus we have to check whether there is a note4 on the sweet spot
        else if(keypressed==75)
        {
            //This is getting theimage that is on the sweet spot ON the fourth column matching with note4
            String currentimage=grid.getImage(new Location(row-3,3));
            
            //This is making sure that there is actually a note4 on it's sweet spot
            if(currentimage!=null)
            {
                //Removing note4 from fourth column from it's sweet spot
                grid.setImage(new Location(row-3,3),null);
                score=score+100*combo;
        
                grid.setScore(score);
        
                combo++;
                totalhits++;
                totalnotes++;
                
                if(health<100)
                {
                    health=health+INCREASE_RATE;
                    grid.setHealth(health);
                }
            }
            //This means that the user have preessed the key when there is nothing on the sweetspot thus the combo is breaked
            else
            {
                combo=1;
                grid.setCombo(combo);
                
                health=health-DECREASE_RATE;
                grid.setHealth(health);
            }
        }
        
        //This next part of series of if-statements will be handling the image highlighting of the key
        //This means that the first key haven't been presssed yet so we keep it as unhighlight
        if(keypressed!=68)
        {
            grid.setImage(new Location(8,0),"/Image Source/key1top.png");
            grid.setImage(new Location(9,0),"/Image Source/key1bot.png");
        }
        //This means that the first key have been pressed thus we have to highlight it
        else if(keypressed==68)
        {
            grid.setImage(new Location(8,0),"/Image Source/key2top.png");
            grid.setImage(new Location(9,0),"/Image Source/key2bot.png");
        }
        
        //This means that the second key haven't been pressed yet thus we keep it unhighlight
        if(keypressed!=70)
        {
            grid.setImage(new Location(8,1),"/Image Source/key1top.png");
            grid.setImage(new Location(9,1),"/Image Source/key1bot.png");
        }
        //Then the else if statement means that the second key have been pressed thus we have to highlight the seond key
        else if(keypressed==70)
        {
            grid.setImage(new Location(8,1),"/Image Source/key2top.png");
            grid.setImage(new Location(9,1),"/Image Source/key2bot.png");
        }
        
        //This means that the third key haven't been pressed yet thus we have to keep it unhighlight
        if(keypressed!=74)
        {
            grid.setImage(new Location(8,2),"/Image Source/key1top.png");
            grid.setImage(new Location(9,2),"/Image Source/key1bot.png");
        }
        //If this statemenet is true then that means that third key have been pressed thus we have to highligh the third key
        else if(keypressed==74)
        {
            grid.setImage(new Location(8,2),"/Image Source/key2top.png");
            grid.setImage(new Location(9,2),"/Image Source/key2bot.png");
        }
        
        //Lastly, this means that the fourth key haven't been pressed yet thus we have to make it unhighlight
        if(keypressed!=75)
        {
            grid.setImage(new Location(8,3),"/Image Source/key1top.png");
            grid.setImage(new Location(9,3),"/Image Source/key1bot.png");
        }
        //Then this means that the fourth key have been pressed thus we have to make it highlight of the fourth key
        else if(keypressed==75)
        {
            grid.setImage(new Location(8,3),"/Image Source/key2top.png");
            grid.setImage(new Location(9,3),"/Image Source/key2bot.png");
        }
        
        grid.setCombo(combo);
    }
    
    public void checkError()
    {
        int row=grid.getNumRows();
        
        if(keypressed!=68)
        {
            String currentimage=grid.getImage(new Location(row-3,0));
            System.out.println(currentimage);
            if(currentimage!=null)
            {
                if(currentimage.equals("/Image Source/note1.png"))
                {
                    totalnotes++;
                    combo=1;
                    grid.setCombo(combo);
                    
                    health=health-penalty;
                    grid.setHealth(health);
                }
            }
        }
        if(keypressed!=70)
        {
            String currentimage=grid.getImage(new Location(row-3,1));
            System.out.println(currentimage);
            if(currentimage!=null)
            {
                if(currentimage.equals("/Image Source/note2.png"))
                {
                    totalnotes++;
                    combo=1;
                    grid.setCombo(combo);
                    
                    health=health-penalty;
                    grid.setHealth(health);
                }
            }
            
        }
        if(keypressed!=74)
        {
            String currentimage=grid.getImage(new Location(row-3,2));
            System.out.println(currentimage);
            if(currentimage!=null)
            {
                if(currentimage.equals("/Image Source/note3.png"))
                {
                    totalnotes++;
                    combo=1;
                    grid.setCombo(combo);
                    
                    health=health-penalty;
                    grid.setHealth(health);
                }
            }
            
        }
        if(keypressed!=75)
        {
            String currentimage=grid.getImage(new Location(row-3,3));
            System.out.println(currentimage);
            if(currentimage!=null)
            {
                if(currentimage.equals("/Image Source/note4.png"))
                {
                    totalnotes++;
                    combo=1;
                    grid.setCombo(combo);
                    
                    health=health-penalty;
                    grid.setHealth(health);
                }
            }
            
        }
        
        
    }
    
    /*(Old key press method)
    //This method will be handling the key pressed by the users
    public void handleKeyPress()
    {
        //This is the key pressed by the user as a int
        int keypressed=grid.checkLastKeyPressed();

        //This means that the user have pressed up arrow which means to move the user gif up one unit
        if(keypressed==38)
        {
            //This means that the user is at the second row or more and NOT the very first row because it is impossible
            //for a collision to occur while going up since there is no more space up there anyway
            if(userRow>0)
            {
                //This is getting the image if any that is above the user, due to the previous condition it will always be either null, avoid.gif, or get.gif
                //no error will be occuring
                String aboveuser=grid.getImage(new Location(userRow-1,0));

                //Now after getting the image that is above the user we must take approaite actions if the image is not a null
                if(aboveuser!=null)
                {
                    //This means that the location above the user is a image thus we have to take actions by calling the
                    //handleCollision method because the user is going up now and pass it the location the user is going to be moving to
                    handleCollision(new Location(userRow-1,0));
                }
            }

            //The code below is handling the moving position of user into a new location, the code above is handling the collisions that
            //the user can be having while going up
            //However being pressed by the key we must also acknowledge the fact to not go out of bound therefore we
            //must have a if-statement to ensure that the user being move up one unit won't go out of bound
            //Creating a variable that will be holding the position of future userRow
            int futureuserrow=userRow-1;

            //This means that by being pressed up arrow the user is not out of bound therefore it can be move to the next slot successfully
            if(futureuserrow>=0)
            {
                //This will remove the image that is prior to moving by using null as image
                grid.setImage(new Location(userRow,0),null);

                //This shifts the user's row position upward one unit
                userRow=userRow-1;

                //Then after updating the user's row position we must draw the new position in which the user have been moved up one unit
                grid.setImage(new Location(userRow,0),"user.gif");
            }

        }
        //This means that the user have pressed down arrow which means to move the user gif down one unit
        else if(keypressed==40)
        {
            //This is making sure that the user is at a row where the collision will occur below it, because if it is at the very last row
            //then by moving down it is impossible for a collision to occur since there is next row
            if(userRow<grid.getNumRows()-1)
            {
                //This is getting the image name that is below the user, and due to the last condition there will always be something below
                //it will not be a run time error because we are gurateen to have a row below us
                String belowuser=grid.getImage(new Location(userRow+1,0));

                //This means that the image below the user is not a empty space therefore we must take proper actions for the collision
                if(belowuser!=null)
                {
                    //This is calling the handleCollision method to take actions for the collision
                    handleCollision(new Location(userRow+1,0));
                }
            }

            //The below code is used to move the user into the space below it, while the code above is determine if there is any possible collision
            //will be occuring while moving down
            //Similarly to the key that is being pressed up we also must ensure that by pressing down to move down
            //we have to ensure that the user picture don't go out of bound downward
            //Thus we must create a variable that will be holding the position of future user's row
            int futureuserrow=userRow+1;

            //Getting the row of the grid
            int gridrow=grid.getNumRows();

            //This means that the position of user's row by moving down one unit is not out of bound of the grid's row therefore
            //we are able to perform the moving since it won't go out of bound
            if(futureuserrow<gridrow)
            {
                //This is removing the image that is before moving it so it won't just stay there creating two images
                grid.setImage(new Location(userRow,0),null);

                //This is updating the userRow's position down one unit
                userRow=userRow+1;

                //Drawing the user's picture as being moved down one unit
                grid.setImage(new Location(userRow,0),"user.gif");
            }
        }

    }
    */
  
    //Making a method named populateTopWith that will accept a String as a placemenet for notes that will work along with scrollDown
    //to place each notes
    public void populateTopWith(int songchoice)
    {
        //First of all we must establish the rule of which letter represent which notes
        //A will equal to first note which is blue
        //B will equal to second note which is green
        //C will equal to third note which is purple
        //D will equal to fourth note which is red
        //Initally we will only be able to place one note but later we will implement to place mutiple notes at one time with special character
        //Getting the length of str
        int length=-1;
        
        //This means that the user have picked oshama thus update oshama
        if(songchoice==0)
        {
            length=oshama.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=oshama.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                oshama=oshama.substring(1);
            }    
        }
        //Vantablack song
        else if(songchoice==1)
        {
            length=vantablack.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=vantablack.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                vantablack=vantablack.substring(1);
            }
        }
        //Transformation song
        else if(songchoice==2)
        {
            length=transformation.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=transformation.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                transformation=transformation.substring(1);
            }
        }
        //Grayed out song
        else if(songchoice==3)
        {
            length=grayedout.length();

                //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=grayedout.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                grayedout=grayedout.substring(1);
            }
        }
        //Big black song
        else if(songchoice==4)
        {
            length=bigblack.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=bigblack.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                bigblack=bigblack.substring(1);
            }
        }
        //Yellow splash song
        else if(songchoice==5)
        {
            length=yellowsplash.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=yellowsplash.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                yellowsplash=yellowsplash.substring(1);
            }
        }
        else if(songchoice==6)
        {
            length=alpeh.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=alpeh.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                alpeh=alpeh.substring(1);
            }
        }
        else if(songchoice==7)
        {
            length=calamity.length();

            //We must check that the length of str to make sure that there is at least one char within it before using charAt to prevent out of bound error
            //This means that we will at least have one char within str
            if(length>0)
            {
                //This is getting the note to place as a char
                char notetoplace=calamity.charAt(0);

                //This means that we have to put the note1 on the very top of the first row and very first column
                if(notetoplace=='D')
                {
                    grid.setImage(new Location(0,0),"/Image Source/note1.png");
                }
                //This means that we have to put the note2 on the very top of the first row and second column
                else if(notetoplace=='F')
                {
                    grid.setImage(new Location(0,1),"/Image Source/note2.png");
                }
                //This means that we have to put the note3 on the very top of the first row and third column
                else if(notetoplace=='J')
                {
                    grid.setImage(new Location(0,2),"/Image Source/note3.png");
                }
                //This means that we have to put the note4 on the very top of the first row and last column
                else if(notetoplace=='K')
                {
                    grid.setImage(new Location(0,3),"/Image Source/note4.png");
                }

                //After placing the note we must substringing out the note we have just placed by subtring at index of 1 to remove the char we have just placed note with
                calamity=calamity.substring(1);
            }
        }

    }
    //The method scrollDown will be responsible for scrolling down everything which will be working with populateTopWith
    public void scrollDown()
    {
        //Getting the row of the grid
        int row=grid.getNumRows();

        //Getting the column of the grid
        int column=grid.getNumCols();

        //Using two for loop to cycle through each section of the grid in reverse column major order to prevent
        //the notes being shifted as it is going along until it is the very end. That is a problem if we are going column major order not reverse order
        //The outer loop will be controlling the column of the grid
        for(int c=column-1;c>=0;c--)
        {
            //The inner loop will be controlling the row of the grid
            for(int r=row-1;r>=0;r--)
            {
                //Getting the image that the for loop is at right now
                String currentimage=grid.getImage(new Location(r,c));
                
                //This means that the image the for loop on is on right now is not a empty space thus it is a note that is going to be shiftede
                //thus inside of this if-statemenet we have to shift the notes down one row from their position
                if(currentimage!=null)
                {
                    if(r==row-3)
                    {
                        grid.setImage(new Location(r,c),null);
                    }
                    else
                    {
                        //This means that the eleement we are at right now is on the very first column which is where note1 exist thus
                        //we have to move that note down one row
                        if(c==0&&currentimage.equals("/Image Source/note1.png"))
                        {
                          //Before we shift the note down one unit, we must delete the note that is in the orignal position or else
                          //there will be duplication of image
                          grid.setImage(new Location(r,c),null);

                          //This is setting the note on to it's next position
                          grid.setImage(new Location(r+1,c),"/Image Source/note1.png");
                        }
                            //This means that the element we are at right now is on the second column which is where note2 exist so
                            //we have to move that note down one row
                        else if(c==1&&currentimage.equals("/Image Source/note2.png"))
                        {
                            //Before we shift the note down one unit we must delete the note that is on it's old position to prevent
                            //duplications
                            grid.setImage(new Location(r,c),null);

                            //This is setting then ote onto it's next position
                            grid.setImage(new Location(r+1,c),"/Image Source/note2.png");
                        }
                        //This means that the element we are at right now is on the third column which is where note3 exist
                        //thus we have to move the note down one row also
                        else if(c==2&&currentimage.equals("/Image Source/note3.png"))
                        {
                            //Prior to shifting we have to remove the note on the old position
                            grid.setImage(new Location(r,c),null);

                            //This is setting the note to its next state
                            grid.setImage(new Location(r+1,c),"/Image Source/note3.png");
                        }
                        //Just like the other four note this means that the element we are on right now is where fourth column where note4 exist
                        //so we have to move that note down one row if there is a note
                        else if(c==3&&currentimage.equals("/Image Source/note4.png"))
                        {
                            //Similar to other notes we have to remove the image on it's old position before shifting
                            grid.setImage(new Location(r,c),null);

                            //This is the shifting of the next into it's next position
                            grid.setImage(new Location(r+1,c),"/Image Source/note4.png");
                        }
                    }
                    
                }
            }
        }
    }
    
    public void populateRightEdge()
    {
        //Generating a random int from 0 to 3 inclusive which will tell the program how many
        //enemies will be generated at the rightmost column of the grid
        int numberavoid=(int)(Math.random()*maxavoid+1);

        //Getting the row of grid
        int row=grid.getNumRows();

        //Getting the column of grid
        int column=grid.getNumCols();

        int avoidcounter=0;

        while(avoidcounter!=maxavoid)
        {
            //This generating a random index for the numberavoid of enemies
            //This might overlap some avoids since the avoidindex might be the same
            int avoidindex=(int)(Math.random()*row);

            //This is setting the enemy at the given index at the last column
            grid.setImage(new Location(avoidindex,column-1),"avoid.gif");

            avoidcounter++;
        }

        //Generating a random int from 0 to 2 inclusive which will represeent the number of items
        //that is generated at the rightmsot column of the grid
        int numberitem=(int)(Math.random()*1);

        //Using a for loop to generate the indexes in which the itme will spawn at the rightmost column of the grid
        //the for loop must go 0 to numbertime inclusive so it will generate the amount of 0 to 2 items
        //The items might replaces the enemies that is spawned
        for(int i=0;i<=numberitem;i++)
        {
            //Similar to the indexes that the avoid might spawn in itemindex can also spawn in the same range of indexes
            int itemindex=(int)(Math.random()*row);

            //This is setting the item at the generated index at the last column
            grid.setImage(new Location(itemindex,column-1),"get.gif");
        }
    }
  
    public void scrollLeft()
    {
        //Rememebr to use the getImage method which returns the name of the file that is at the given location
        //In order to shift everything backward we are going to do this in column major order thus we are able to handle
        //the case when the item or avoid is at column 0 they should be removed and they are able to be handle all at once
        //Getting the row of grid
        int row=grid.getNumRows();

        //Getting the column of grid
        int column=grid.getNumCols();

        //Getting the possible picture that is one ahead of the user we will be using this to check for collisions
        String imageahead=grid.getImage(new Location(userRow,1));

        //This means that the image that is ahead of the user is a picture and not a empty space thus we have to handle this situation accordingly
        //by calling the handleCollision method
        if(imageahead!=null)
        {
            //Calling the handleCollision method to take approaite actions when there is a collision occuring in front of user
            handleCollision(new Location(userRow,1));
        }

        //This part is where the scrolling occurs, the code above is prior to the scrolling
        //Using a for loop to cycle through grid in column major order
        //Since we are doing it in column major order column must be on the outer loop rather than the inner loop
        for(int c=0;c<column;c++)
        {
            //The inner loop will be controlling the row
            for(int r=0;r<row;r++)
            {
                //We will be handling the case in which when the element we are at right now is at the very first column
                //or when c is at 0. For this case, we would just have to remove the image from grid since it will dissapperar when it moves to the left
                if(c==0)
                {
                    //Gettging the name of the element that the loop is at right now
                    String currentimage=grid.getImage(new Location(r,c));

                    //This is making sure that the element that the loop is at right now is not at a null object so nullpointerexception error will not error
                    if(currentimage!=null)
                    {
                        //This means that the element the loop is at right now is a item picture thus we have to remove the item picture
                        //because it is at the very first column, with moving to the left it will dissapperar
                        if(currentimage.equals("get.gif"))
                        {
                            //This will remove the item picture that the loop is at right now
                            grid.setImage(new Location(r,c),null);
                        }
                        //This means that the element the loop is at right now is a avoid picture therefore we have to remove the avoid picture
                        //since it is also in the very first column
                        else if(currentimage.equals("avoid.gif"))
                        {
                            //This will remove the avoid picture that the loop is at right now
                            grid.setImage(new Location(r,c),null);
                        }
                    }
                }
                //This means that the element we are at right now is at other columns and not the first column
                //Keep in mind that for all of the new Location r is remained constant just the column subtracted once
                else
                {
                    //This is getting the name of the element that the loop is at right now
                    String currentimage=grid.getImage(new Location(r,c));

                    //We must check that the element we are at right now is not a null image or else without this statemenets the inner if-statemenet will be a
                    //nullpointerexceptions since there is no element at that location
                    if(currentimage!=null)
                    {
                        //This means that the image that the loop is at right now is a item picture thus we have to the the item picture to the left one unit
                        if(currentimage.equals("get.gif"))
                        {
                            //However before moving we must remove the image we are at right now before movign or else it will create two get pictures
                            grid.setImage(new Location(r,c),null);

                            //Now since we have removed the image we have to set the new image that is moved back one unit
                            //or the column is subtracted once
                            grid.setImage(new Location(r,c-1),currentimage);
                        }
                        //This means that the image that the loop is at right now is a avoid picture thus we have to move the avoid picture to the left one unit
                        else if(currentimage.equals("avoid.gif"))
                        {
                              //Similar to the item picture we must remove the image before shifting it
                              grid.setImage(new Location(r,c),null);

                              //After removing the pictures now we can move the picture back one unit
                              grid.setImage(new Location(r,c-1),currentimage);
                        }
                    }
                }
            }
        }
    }
  
    public void handleCollision(Location loc)
    {
        //The given variable loc is representing a place where a get or avoid might be thus we need to
        //handle the situation accordingly if it is at the same place where our user picture is
        //We must get the image name at loc first to check whether there is actually a image or not
        String imagename=grid.getImage(loc);

        //This means that there is a picture either avoid or get image at the given location
        //Then inside of the if-statement we must handle the cases for hitting a avoid and hitting a get case
        if(imagename!=null)
        {
            //This means that the user is hitting a get image therefore we have to update timesGet representing that the user have got a item
            if(imagename.equals("get.gif"))
            {
                //Updating the timesGet instance variable by one for getting a get picture
                //each get picture will be worthing of 100 points
                timesGet=timesGet+100;

                //In addition to updating the instance variabeles we also have to remove the respective image because
                //the user is collecting it, rememeber to use null since we are removing the image
                grid.setImage(loc,null);
            }
            //This means that the user is hitting a avoid image therefore we have to update timesAvoid meaning that the user have hit a avoid picture
            else if(imagename.equals("avoid.gif"))
            {
                //Updating the timesAvoid instance variable by one for hitting a avoid object
                //timesAvoid will be subtracting 1 every time it is true because it will be more convient for adding it for the total score
                //and each avoid object will be worthing of deducting 10 points each
                timesAvoid=timesAvoid-10;

                //Similarly if we are hitting a avoid image we also have to remove the picture since the user is hitting it
                grid.setImage(loc,null);
            }
        }
    }
  
    public int getScore()
    {
        //This is updating the score that is display on the title with the score counting system being
        //the number of timesGet adding to timesAvoid
        return timesGet+timesAvoid;
    }
    
    public void updateTitle()
    {
        Thread newthread=new Thread()
        {
            public void run()
            {
                while(!isGameOver())
                {
                    grid.setTitle(randomText());

                    try
                    {
                        Thread.sleep(8000);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        
        newthread.start();
    }
    
    public static String randomText()
    {
        
        ArrayList<String> list=new ArrayList<String>();
        
        list.add("ARE YOU HAVING FUN?");
        list.add("MAY 25TH IS A IMPORTANT DATE");
        list.add("Soft core mu3ic");
        list.add("Housy housy");
        list.add("You score is -99999999999999");
        list.add("Error out of bound");
        list.add("This is not interesting");
        list.add("End my suffering");
        list.add("Is there anything cool to do?");
        list.add("System.out.println(\":O\")");
        list.add("Escape sequence");
        list.add("What are the chances of getting me?");
        list.add("Bananas :3");
        list.add("Wanna know the forumla for calculating the score?");
        list.add("Heheheheheh");
        list.add("Flit");
        list.add("Comformists");
        list.add("Catcher");
        list.add("Just in time");
        list.add("List");
        list.add("Of");
        list.add("References");
        list.add("I");
        list.add("Don't");
        list.add("Get");
        list.add("Sin wave");
        list.add("Cosine wave");
        list.add("Tangent line");
        list.add("f(x)=5x+5");
        list.add("schwarzschild raidus");
        list.add("Did you know that gravitational acceleartion is 9.81?");
        list.add("Fg=mg");
        list.add("Loops all around");
        list.add("How do I find the index?");
        list.add("Binary search requires recusion");
        list.add("House house");
        list.add("Drug facts");
        list.add("Medievalish");
        list.add("Disclaimer: I can't spell");
        list.add("There is no light");
        list.add("My coolest move");
        list.add("You can't catch me I'm made of ginger bread");
        list.add("Water in every direction is a terrifying luxrey");
        list.add("Huh?");
        list.add("Totally not copedi");
        list.add("Made in 2018");
        list.add("I can list my wish-list here");
        list.add("OSU!");
        list.add("Easter Egg!");
        list.add("Skippy");
        list.add("DYI");
        list.add("OsU MaNiA");
        list.add("[READACTED]");
        list.add("[CENSORED]");
        list.add("SCP-[REDACTED]");
        list.add("FreezyCold");
        list.add("Update 2.0?");
        list.add("Saving score might come later");
        list.add("Or never");
        list.add("100% Natural");
        list.add("1% Darkness");
        list.add("This is ENOUGHT");
        list.add("的");
        list.add("Wew");
        list.add("Power of String");
        list.add("Enough variety?");
        list.add("Long method");
        list.add("Why are you still here?");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("JKING JKING JKING");
        list.add("Iconic of sins");
        list.add("Nothing gold can stay");
        list.add("Desierata");
        list.add("Shin Noodle Cup");
        list.add("I SCREAM");
        list.add("Its 6:01 PM");
        list.add("5/26/2018");
        list.add("I endure");
        list.add("I survive");
        list.add("She attacc");
        list.add("She protecc");
        list.add("But most important");
        list.add("She resurrec");
        list.add("Crusader quest");
        list.add("Vainglory");
        list.add("This won't stackoverflow?");
        list.add("Recipe of 30 thousand dollars");
        list.add("Pain gets worse or lasts more than 10 days");
        list.add("do not take more than directed");
        list.add("the smallest effective dose should be used");
        list.add("adults and children 12 years and over take one");
        list.add("From base 2 to base 10: 1111011101111");
        list.add("From base 10 to base 2: 7211");
        list.add("How many iterations are there?");
        list.add("Over soon....");
        
        int length=list.size();
        
        int randomindex=(int)(Math.random()*length);
        
        String output=list.get(randomindex);
        
        return output;
        
        
    }
    
    public boolean isGameOver()
    {
        //The isGameOver method will return true if either the score reach over 10000 the total score and not just get alone
        //or the user have gotten 500 avoid deductions
        
        System.out.println(clip.isRunning());
        if(health<0||!clip.isRunning())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void test()
    {
        Game game = new Game();
        game.play();
    }

    public static void main(String[] args)
    {
        Game.test();
    }
    
    public void loadSong(int givensong)
    {
        if(givensong==0)
        {
            try
            {
                link=Game.class.getResource("/Song Source/oshama.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else if(givensong==1)
        {
            try
            {
                link=Game.class.getResource("/Song Source/vantablack.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else if(givensong==2)
        {
            try
            {
                link=Game.class.getResource("/Song Source/transformation.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else if(givensong==3)
        {
            try
            {
                link=Game.class.getResource("/Song Source/grayedout.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else if(givensong==4)
        {
            try
            {
                link=Game.class.getResource("/Song Source/bigblack.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else if(givensong==5)
        {
            try
            {
                link=Game.class.getResource("/Song Source/yellowsplash.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else if(givensong==6)
        {
            try
            {
                link=Game.class.getResource("/Song Source/aleph.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                link=Game.class.getResource("/Song Source/calamity.wav");

                audioinput=AudioSystem.getAudioInputStream(link);

                clip=AudioSystem.getClip();

                clip.open(audioinput);
            }
            catch(UnsupportedAudioFileException e)
            {
              e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(LineUnavailableException e)
            {
                e.printStackTrace();
            }
        }
    }

    //This method will be responsible for playing the Song Oshama Scramble
    public void playSong()
    {
        clip.start();
    }
    
    public void stopSong()
    {
        clip.stop();
    }
    
    //This method will save the scores to a text file
    public static void saveScore()
    {
        try
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter("Scores"));
            
            bw.write("This is a place of storage oh nose this is so good it can save files and note into a textnote");
            bw.newLine();
            bw.write("new line woah!");
            bw.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int getHit()
    {
        return totalhits;
    }
    
    public int getCombo()
    {
        return combo;
    }
    
    public int getNotes()
    {
        return totalnotes;
    }
    
    public int getActualScore()
    {
        return score;
    }
    
    
}