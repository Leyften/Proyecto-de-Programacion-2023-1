import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimacionImagen extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Cargar imágenes
        Image imagen1 = new Image("imagen1.png");
        Image imagen2 = new Image("imagen2.png");
        
        // Crear ImageView para cada imagen
        ImageView imageView1 = new ImageView(imagen1);
        ImageView imageView2 = new ImageView(imagen2);
        imageView2.setVisible(false); // Ocultar imagen2 inicialmente
        
        // Crear Pane para contener las imágenes
        Pane pane = new Pane();
        pane.getChildren().addAll(imageView1, imageView2);
        
        // Crear TranslateTransition para animar la imagen hacia abajo
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), imageView1);
        translateTransition.setToY(500);
        translateTransition.setOnFinished(event -> {
            // Cambiar a la segunda imagen cuando la primera haya alcanzado la parte inferior de la ventana
            imageView1.setVisible(false);
            imageView2.setVisible(true);
        });
        
        // Iniciar la animación
        translateTransition.play();
        
        // Crear escena y mostrarla en la ventana
        Scene scene = new Scene(pane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}