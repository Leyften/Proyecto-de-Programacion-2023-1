package fpndo;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fpndo extends Application {

    private static final int distancia = 50; // Distancia de movimiento en píxeles
    private static final Duration duracion = Duration.seconds(1); // Duración de la animación de movimiento

    @Override
    public void start(Stage primaryStage) {
        ImageView fondo = new ImageView("imagernes/fondomario.png");
        ImageView imagen = new ImageView("imagernes/grua1.png");
        imagen.setPreserveRatio(true);
        imagen.setFitWidth(50);
        imagen.setLayoutX(50);
        imagen.setLayoutY(50);

        Pane raiz = new Pane();
        raiz.getChildren().addAll(fondo, imagen);

        Scene scene = new Scene(raiz, fondo.getImage().getWidth(), fondo.getImage().getHeight());

        primaryStage.setTitle("fondo mario con grua");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        TranslateTransition translateTransition = new TranslateTransition(duracion, imagen);
        translateTransition.setByY(distancia); // Mover la imagen hacia abajo
        translateTransition.setAutoReverse(true); // Hacer que la animación se repita hacia adelante y hacia atrás
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Repetir la animación indefinidamente

        translateTransition.play(); // Comenzar la animación
    }

    public static void main(String[] args) {
        launch(args);
    }

}