import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

/**
 * Top class to render the Top of the game
 */
public class Top extends StackPane {
    StackPane mainTop = new StackPane();

    public Top() {    
        Text name = new Text("Jordle");
        name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 35));
        name.setFill(Color.WHITE); 

        mainTop.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        mainTop.setStyle("-fx-border-color: gray");
        mainTop.setPadding(new Insets(10, 10, 10, 10));
        mainTop.setAlignment(Pos.CENTER);
        mainTop.getChildren().add(name);
    }
}
