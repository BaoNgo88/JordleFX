import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;

/**
 * Bottom class to render the Bottom of the game
 */
public class Bottom extends HBox {
    HBox mainBottom; 
    static Label lblStatus; 
    static Label timeStatus; 
    static Label highScore; 
    
    public Bottom() {
        mainBottom = new HBox(); 
        mainBottom.setStyle("-fx-border-color: gray");
        mainBottom.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        mainBottom.setAlignment(Pos.CENTER);
        mainBottom.setPadding(new Insets(20, 20, 25, 10));

        // Content for the bottom 
        // Status text
        lblStatus = new Label("LET'S SEE WHAT YOU HAVE KID!");
        lblStatus.setStyle("-fx-text-fill: white");

        // Time text
        timeStatus = new Label("TIME: ");
        timeStatus.setStyle("-fx-text-fill: white");

        // Restart button 
        Button restart = new Button();
        restart.setText("Restart");
        restart.setPadding(new Insets(10, 10, 10, 10));

        // Instruct button 
        Button instruct = new Button();
        instruct.setText("Instructions");
        instruct.setPadding(new Insets(10, 10, 10, 10));

        // High Score text
        try {
            long currentRecord = Game.writer.readScore(); 
            highScore = new Label();
            highScore.setStyle("-fx-text-fill: white");
            if (currentRecord != 0L) {
                highScore.setText("YOUR RECORD: " + currentRecord + "s"); 
            } else {
                highScore.setText("YOUR RECORD: N/A"); 
            }
        } catch (FileNotFoundException x) {
            System.out.println(x.getMessage()); 
        } 
        
        mainBottom.setSpacing(10);
        mainBottom.getChildren().addAll(lblStatus, restart, instruct, timeStatus, Game.time.display, highScore);

        // Make the function for Instructions button
        instruct.setOnAction(e -> {
            Label instrLabel = new Label("1. Your GOAL is to guess the hidden word\n\n"
                + "2. Click Instructions to show instructions\n\n"
                + "3. Guess and type a letter to each box below\n\n"
                + "4. After finishing a line, press ENTER to check\n\n"
                + "5. The correct letter in correct position will change to GREEN,"
                + " the correct letter in wrong position will change to YELLOW"
                + ", the wrong letter in wrong position will change to GRAY\n\n"
                + "6. Repeat steps 3 & 4 for the remaining lines\n\n"
                + "7. Press BACKSPACE to remove a typed letter\n\n9. Click Restart to restart the game\n\n"
                + "8. IMPORTANT: DON'T use CAPS LOCK");
            instrLabel.setFont(Font.font("Times New Roman", 15)); 
            instrLabel.setWrapText(true);
            instrLabel.setAlignment(Pos.CENTER);
            instrLabel.setPadding(new Insets(10, 5, 5, 10));

            StackPane instrLayout = new StackPane();
            instrLayout.getChildren().add(instrLabel);

            Scene instrScene = new Scene(instrLayout, 450, 450);

            Stage instrStage = new Stage();
            instrStage.setTitle("Instructions");
            instrStage.setScene(instrScene);
            instrStage.show();

            // Request focus for the current cell again after clicking instructions
            if (GuessWord.c <= 4){
                Center.t[GuessWord.r][GuessWord.c].requestFocus(); 
            } else if (GuessWord.c == 5) {
                Center.t[GuessWord.r][GuessWord.c - 1].requestFocus(); 
            }
        });

        // Make the function for Restart button
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (int i = 0; i < 6; ++i) {
                    for (int j = 0; j < 5; ++j) {
                        Center.t[i][j].setText("");
                        Center.r[i][j].setFill(Color.WHITE);
                    }
                }
                // Regenerate an answerKey
                Random generateKey = new Random(); 
                int index = generateKey.nextInt(Game.words.size()); 
                Game.answerKey = Game.randomArray[index]; 
                System.out.println(Game.answerKey);
                // Reset all related variables
                GuessWord.lastCol = false; 
                GuessWord.r = 0; 
                GuessWord.c = 0; 
                GuessWord.gameFinished = false; 
                GuessWord.win = false; 
                Center.t[0][0].requestFocus();
                lblStatus.setText("LET'S SEE WHAT YOU HAVE KID!");
                // Restart the background music
                Game.congratsMusic.stopMusic(); 
                Game.byeMusic.stopMusic(); 
                Game.mainMusic.stopMusic(); 
                Game.mainMusic.playMusic(); 
                //Restart the time
                Game.time.restartTime();
            }
        });



        
    }
}
