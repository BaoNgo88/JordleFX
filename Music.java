import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import java.nio.file.Paths; 
 
/**
 * Music class to generate the main theme Music for the game
 */
public class Music {
    Media gameMusic; 
    MediaPlayer gameMusicPlayer; 
    
    public Music(String link) {
        gameMusic = new Media(Paths.get(link).toUri().toString()); 
        gameMusicPlayer = new MediaPlayer(gameMusic);
    }

    // Method to play music once the game starts
    public void playMusic() {
        gameMusicPlayer.play(); 
        gameMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
    }

    // Method to stop playing music 
    public void stopMusic() {
        gameMusicPlayer.stop(); 
    }
}