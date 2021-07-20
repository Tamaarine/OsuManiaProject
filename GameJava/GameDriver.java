package GameJava;

public class GameDriver
{
    public static void main(String [] args)
    {
        int gameover=0;
        
        boolean firsttime=true;
        
        while(gameover==0)
        {
            if(firsttime)
            {
                Game game=new Game();
                game.play();

                EndScreen end=new EndScreen(game);
                
                gameover=end.getReplay();
                
                end.close();
                end.stopLastSoundByeBye();
                firsttime=false;
            }
            //This means that is not the firsttime anymore thus we don't need the loading screen
            else
            {
                Game game=new Game(true);
                game.play();
                
                EndScreen end=new EndScreen(game);
                
                gameover=end.getReplay();
                end.close();
                end.stopLastSoundByeBye();
            }
            if(gameover==1)
            {
                System.exit(0);
            }
        }
    }
}
