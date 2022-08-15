import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.layout.StackPane;

/**
 * Center class to render the Center of the game
 */
public class Center extends VBox {
    VBox mainCenter; 
    static Text[][] t; 
    static Rectangle[][] r;
    
    public Center() {
        mainCenter = new VBox();
        mainCenter.setBackground(new Background(new BackgroundFill(Color.web("#202020"), null, null)));
        mainCenter.setPadding(new Insets(10, 10, 10, 10));
        mainCenter.setAlignment(Pos.CENTER);
        mainCenter.setSpacing(20);

        // Make the table 
        // Setup GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        // Setup the unit cells of the table
        r = new Rectangle[6][5];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                r[i][j] = new Rectangle(80, 80, Color.WHITE);
                r[i][j].setStroke(Color.BLACK); 
            }
        }

        // Setup the texts for unit cells 
        t = new Text[6][5];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                t[i][j] = new Text();
                t[i][j].setFill(Color.BLACK);
                t[i][j].setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
            }
        }

        // Setup the StackPane to load content for unit cells 
        StackPane[][] s = new StackPane[6][5];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                s[i][j] = new StackPane(r[i][j], t[i][j]);
            }
        }
        // Load content on grid 
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                grid.add(s[i][j], j, i);
            }
        }

        // Add grid to mainCenter 
        mainCenter.getChildren().add(grid);
        
        // Set the key events handle method for the unit cells
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                t[i][j].setOnKeyPressed(new GuessWord(t, r));
            }
        }

    }
}
