import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.TextField; 

/**
 * Time class to display and control time. 
 */

public class Time extends AnimationTimer {
    private long timeMark;
    private long leftOver = 0;
    int timeDisplay = 0;
    TextField display = new TextField("0s");
    
    public void start() {
        display.setPrefWidth(50); 
        display.setAlignment(Pos.CENTER); 
        // Adjust timeMark by deducting the unhandled leftOver from current time
        timeMark = System.currentTimeMillis() - leftOver;
        super.start();
    }

    public void stop() {
        super.stop();
        // Save the unhandled time from last handle to leftOver
        leftOver = System.currentTimeMillis() - timeMark;
    }

    public void handle(long now) {
        long currTime = System.currentTimeMillis();
        // Choose 1000 because 1s = 1000ms, which means only update if 1s has passed
        if (timeMark + 1000 <= currTime) {
            long timePassed = (currTime - timeMark) / 1000;
            timeDisplay += timePassed;
            // Multiply by 1000 again because timeMark's unit is ms
            timeMark += 1000 * timePassed;
            display.setText(Long.toString(timeDisplay) + "s");
        }
    }
 
    public void restartTime() {
        timeDisplay = 0; 
        leftOver = 0; 
        display.setText(Long.toString(timeDisplay) + "s");
        start(); 
    }
}
