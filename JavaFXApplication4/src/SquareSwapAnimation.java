import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SquareSwapAnimation extends Application {

    private static final int tamano = 50;
    private static final int separacion = 20;
    private static final Duration duracion = Duration.seconds(1);

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create two rectangles
        Rectangle rectangle1 = new Rectangle(tamano, tamano, Color.RED);
        Rectangle rectangle2 = new Rectangle(tamano, tamano, Color.BLUE);

        // Set initial positions of the rectangles
        rectangle1.setLayoutX(540 - tamano - separacion / 2);
        rectangle1.setLayoutY(500);
        rectangle2.setLayoutX(540 + separacion / 2);
        rectangle2.setLayoutY(500);

        // Create a Group to hold the rectangles
        Group root = new Group(rectangle1, rectangle2);

        // Create a TranslateTransition to move the rectangles up
        TranslateTransition translateUp1 = new TranslateTransition(duracion, rectangle1);
        translateUp1.setToY(-tamano);

        TranslateTransition translateUp2 = new TranslateTransition(duracion, rectangle2);
        translateUp2.setToY(-tamano);

        // Swap the positions of the rectangles after the first animation finishes
        translateUp2.setOnFinished(event -> {
            double tempX = rectangle1.getLayoutX();
            double tempY = rectangle1.getLayoutY();
            rectangle1.setLayoutX(rectangle2.getLayoutX());
            rectangle1.setLayoutY(rectangle2.getLayoutY());
            rectangle2.setLayoutX(tempX);
            rectangle2.setLayoutY(tempY);
        });

        // Create a TranslateTransition to move the rectangles down
        TranslateTransition translateDown1 = new TranslateTransition(duracion, rectangle1);
        translateDown1.setToY(0);

        TranslateTransition translateDown2 = new TranslateTransition(duracion, rectangle2);
        translateDown2.setToY(0);

        // Create a ParallelTransition to play both animations simultaneously
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translateUp1, translateUp2);

        // Create a SequentialTransition to play the animations in sequence
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(parallelTransition, new PauseTransition(Duration.seconds(0.5)), new ParallelTransition(translateDown1, translateDown2));

        // Create a scene and display it
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the animation
        sequentialTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}