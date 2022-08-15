/**
 * @author Ton Quoc Bao Ngo
 * @version 2
 * Started in June 2022
 * 
 * Credits: 
 * Music by ZakharValaha from Pixabay
 * Music by GioeleFazzeri from Pixabay
 * Music by AntipodeanWriter from Pixabay
 * Image by Jay Castello via https://www.polygon.com/22980182/fave-beats-goku-meme
 * Image by Studio Pierrot via https://www.ranker.com/list/best-naruto-fights-all-time/anna-lindwasser
 * Image by https://emojiisland.com/products/omg-iphone-emoji-jpg
 * Timer source code by fabian via https://stackoverflow.com/questions/40821849/creating-simple-stopwatch-javafx
 * 
 */

// Import necessary packages
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Random; 

/**
 * Game class to run the whole game application
 */

public class Game extends Application {
    // Delcare and initialize variables (components)
    static String answerKey;
    static String[] randomArray; 
    static HashSet<String> words; 

    private BorderPane main;
    private Top mainTop = new Top();
    private Center mainCenter = new Center();
    private Bottom mainBottom = new Bottom();
    static Music mainMusic = new Music("./jordleMusic.mp3");
    static Music congratsMusic = new Music("./jordleCongratsMusic.mp3"); 
    static Music byeMusic = new Music("./jordleByeMusic.mp3"); 
    static Time time = new Time();
    static Write writer = new Write();

    public void generateAnswerKey() {
        try {
            words = WordBankSearchSet.getStringSet();
            randomArray = words.toArray(new String[words.size()]); 
        } catch (FileNotFoundException x) {
            System.out.println(x.getMessage()); 
        } 
    }
    
    @Override
    public void start(Stage mainStage) {
        // Make the main window(stage)
        mainStage.setTitle("Jordle");
        main = new BorderPane();

        Scene mainScene = new Scene(main, 800, 800);
        mainStage.setScene(mainScene);
        mainStage.show();

        // Top Section 
        main.setTop(mainTop.mainTop); 

        // Center Section 
        main.setCenter(mainCenter.mainCenter); 
        
        
        // Bottom Section 
        main.setBottom(mainBottom.mainBottom);

        // Play music for the game
        mainMusic.playMusic(); 

        // Start the time
        time.start(); 
        
        // Request focus on the first cell to start the game
        Center.t[0][0].requestFocus();
    }

    /**
    * Main class to launch the game.
    * @param args takes in the arguments of the main method.
    */
    public static void main(String[] args) {
        // Generate a random key word to guess
        Game newGame = new Game();
        newGame.generateAnswerKey(); 
        Random generateKey = new Random(); 
        int index = generateKey.nextInt(Game.words.size()); 
        answerKey = Game.randomArray[index]; 
        System.out.println(answerKey);
        launch(args);
    }

}
