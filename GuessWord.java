import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import java.util.ArrayList; 
import javafx.scene.image.Image; 
import java.nio.file.Paths; 
 
/**
 * GuessWord class to handle key events  
*/
public class GuessWord implements EventHandler<KeyEvent> {
    Text[][] t; 
    Rectangle[][] rec; 
    static boolean lastCol = false;
    static boolean gameFinished = false;  
    static boolean win = false; 
    static int r = 0;  
    static int c = 0;  
 
    public GuessWord(Text[][] t, Rectangle[][] rec) {
        this.t = t; 
        this.rec = rec; 
    }
    
    @Override
    public void handle(KeyEvent e) {
        // Take input from the keyboard and print on the grid 
        if (!lastCol) {
            if (e.getText().length() > 0 && e.getText().charAt(0) >= 'a' && e.getText().charAt(0) <= 'z') {
                t[r][c].setText(e.getText().toUpperCase());
                ++c;
                if (c <= 4) {
                    t[r][c].requestFocus(); 
                } else {
                    lastCol = true; 
                }
            }
        }
        
        // Disable Tab key
        if (e.getCode() == KeyCode.TAB) {
            e.consume();

        }

        // Setup BackSpace to remove typed letters
        if (e.getCode() == KeyCode.BACK_SPACE) {
            if (c == 0 || gameFinished) {
                return; 
            } else {
                --c; 
                lastCol = false; 
                t[r][c].setText(""); 
                t[r][c].requestFocus(); 
            }
        }

        // Setup Enter to check and move down one line 
        if (e.getCode() == KeyCode.ENTER) {    
            if (!lastCol) {
                return; 
            } 
            
            // Check if the input word is an English word
            String currentWord = ""; 
            for (int i = 0; i < 5; ++i) {
                currentWord += t[r][i].getText(); 
            }
            boolean valid = Game.words.contains(currentWord.toLowerCase()); 
            if (valid) {
                ArrayList<Character> inputArr = new ArrayList<>(5); 
                for (int i = 0; i < 5; ++i) {
                    inputArr.add(Game.answerKey.toLowerCase().charAt(i)); 
                }
                
                for (int i = 0; i < 5; ++i) {
                    // Check to change background color 
                    if (currentWord.toLowerCase().charAt(i) == Game.answerKey.charAt(i)) {
                        rec[r][i].setFill(Color.GREEN); 
                        inputArr.remove(Character.valueOf(currentWord.toLowerCase().charAt(i))); 
                    } else if (Game.answerKey.contains(Character.toString(currentWord.toLowerCase().charAt(i)))) {
                        rec[r][i].setFill(Color.YELLOW); 
                    } else {
                        rec[r][i].setFill(Color.GRAY); 
                    }  
                }

                for (int i = 0; i < 5; ++i) {
                    if (rec[r][i].getFill().equals(Color.YELLOW)) {
                        if (inputArr.contains(Character.valueOf(currentWord.toLowerCase().charAt(i)))) {
                            inputArr.remove(Character.valueOf(currentWord.toLowerCase().charAt(i))); 
                        } else {
                            rec[r][i].setFill(Color.GRAY); 
                        }
                    }
                }
                
                // Check if win
                if (Game.answerKey.equals(currentWord.toLowerCase())) {
                    // Update the status line in the Bottom section
                    Bottom.lblStatus.setText("YOU BEAT THE BIG BOSS!");

                    // Congrats window
                    StackPane congratLayout = new StackPane();
                    // Create the background image 
                    String link = "./jordleCongratsImg.png"; 
                    Image congratsImg = new Image(Paths.get(link).toUri().toString());
                    BackgroundSize sizeImg = new BackgroundSize(100, 100, true, true, true, true);
                    congratLayout.setBackground(new Background(new BackgroundImage(congratsImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, sizeImg))); 

                    Scene congratScene = new Scene(congratLayout, 700, 450);

                    Stage congratStage = new Stage();
                    congratStage.setTitle("URGH.. FINALLY, YOU BEAT ME ఠ_ఠ");
                    congratStage.setScene(congratScene);
                    congratStage.show();
                    gameFinished = true; 
                    win = true; 
                    // Change the background music 
                    Game.mainMusic.stopMusic(); 
                    Game.congratsMusic.playMusic();

                    // Stop the time 
                    Game.time.stop(); 
                    
                    // Record if receive a new High Score
                    try {
                        Game.writer.write(Game.time.timeDisplay);
                        Bottom.highScore.setText("YOUR RECORD: " + String.valueOf(Game.writer.currentScore) + "s"); 
                    } catch (FileNotFoundException x) {
                        System.out.println(x.getMessage()); 
                    }
                    
                    return; 
                }
                
                // Check if lose 
                if (r == 5) {
                    // Update the status line in the Bottom section
                    Bottom.lblStatus.setText("IS THAT ALL YOU HAVE? THE CORRECT WORD WAS \"" + Game.answerKey.toUpperCase() + "\".");

                    // Bye window
                    StackPane byeLayout = new StackPane();
                    // Create the background image 
                    String link2 = "./jordleByeImg.jpeg"; 
                    Image byeImg = new Image(Paths.get(link2).toUri().toString());
                    BackgroundSize sizeImg2 = new BackgroundSize(100, 100, true, true, true, true);
                    byeLayout.setBackground(new Background(new BackgroundImage(byeImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, sizeImg2))); 
                    

                    Scene byeScene = new Scene(byeLayout, 700, 450);

                    Stage byeStage = new Stage();
                    byeStage.setTitle("GO HOME AND TRAIN MORE.");
                    byeStage.setScene(byeScene);
                    byeStage.show();
                    gameFinished = true; 
                    // Change the background music 
                    Game.mainMusic.stopMusic(); 
                    Game.byeMusic.playMusic();

                    // Stop the time 
                    Game.time.stop(); 

                    return; 
                }
                
                // Move to next line 
                ++r; 
                c = 0; 
                if (r <= 5) {
                    // Reset lastCol and requestFocus to the next line
                    lastCol = false; 
                    t[r][c].requestFocus();
                } else {
                    return; 
                }
            } else {
                Alert err = new Alert(Alert.AlertType.ERROR, "Please enter an English word!");
                err.setTitle("OOPS!");
                err.showAndWait();
                System.out.println("Error resolved.");
            }
        } 

        


    }
}

