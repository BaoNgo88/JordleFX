import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.nio.file.Paths; 
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Write class to write new record into the high-score text file. 
 */

public class Write {
    long currentScore; 
    public void write(int highScore) throws FileNotFoundException {
        File f = new File("high-score.txt"); 
        PrintWriter write = new PrintWriter(f); 
        Scanner sc = new Scanner(f); 

        if (sc.hasNextLine()) {
            currentScore = sc.nextInt(); 
            if (highScore > currentScore) {
                write.print(highScore);
                write.flush(); 

                // New record window
                Pane newRecordLayout = new Pane();
                newRecordLayout.setStyle("-fx-background-color: beige"); 
                
                // Create the background image 
                String link = "./newRecord.png"; 
                Image newRecordImg = new Image(Paths.get(link).toUri().toString());
                ImageView recordView = new ImageView();
                recordView.setImage(newRecordImg);
                recordView.setX(400);
                recordView.setY(100); 
                recordView.setFitWidth(200);
                recordView.setPreserveRatio(true); 

                // Text to display new record
                Label displayRecord = new Label("NEW RECORD: " + Integer.toString(highScore) + "s");
                displayRecord.setMinWidth(400); 
                displayRecord.setMinHeight(400); 
                displayRecord.setTextAlignment(TextAlignment.CENTER);
                displayRecord.setPadding(new Insets(80)); 
                displayRecord.setFont(Font.font("Impact", FontWeight.BOLD, 32));
                
                newRecordLayout.getChildren().addAll(displayRecord, recordView);
                
                Scene newRecordScene = new Scene(newRecordLayout, 700, 450);
                Stage newRecordStage = new Stage();
                newRecordStage.setTitle("YOU SET A NEW RECORD, GENIUS!!");
                newRecordStage.setScene(newRecordScene);
                newRecordStage.show();
            }
        } else {
            // New record window
            Pane newRecordLayout = new Pane();
            newRecordLayout.setStyle("-fx-background-color: beige"); 
            
            // Create the background image 
            String link = "./newRecord.png"; 
            Image newRecordImg = new Image(Paths.get(link).toUri().toString());
            ImageView recordView = new ImageView();
            recordView.setImage(newRecordImg);
            recordView.setX(400);
            recordView.setY(100); 
            recordView.setFitWidth(200);
            recordView.setPreserveRatio(true); 

            // Text to display new record
            Label displayRecord = new Label("NEW RECORD: " + Integer.toString(highScore) + "s");
            displayRecord.setMinWidth(400); 
            displayRecord.setMinHeight(400); 
            displayRecord.setTextAlignment(TextAlignment.CENTER);
            displayRecord.setPadding(new Insets(80)); 
            displayRecord.setFont(Font.font("Impact", FontWeight.BOLD, 32));
            
            newRecordLayout.getChildren().addAll(displayRecord, recordView);
            
            Scene newRecordScene = new Scene(newRecordLayout, 700, 450);
            Stage newRecordStage = new Stage();
            newRecordStage.setTitle("YOU SET A NEW RECORD, GENIUS!!");
            newRecordStage.setScene(newRecordScene);
            newRecordStage.show();
            
            write.print(highScore);
            currentScore = highScore; 
            write.flush();
        }
        
        write.close(); 
        sc.close(); 
    }  
    
    public long readScore() throws FileNotFoundException {
        File f = new File("high-score.txt"); 
        Scanner sc = new Scanner(f); 
        if (sc.hasNextInt()) {
            currentScore = sc.nextInt();
            sc.close(); 
            return currentScore; 
        } 
        sc.close(); 
        return currentScore; 
    }
}
