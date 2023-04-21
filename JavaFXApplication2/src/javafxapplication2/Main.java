package javafxapplication2;

import java.io.FileInputStream;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    // cargar la vista FXML
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    InputStream stream = new FileInputStream("C:\\Users\\augus\\OneDrive\\Escritorio\\Nueva carpeta (3)\\Proyecto-de-Programacion-2023-1-Prototipo\\JavaFXApplication2\\src\\imagen\\fondomario.png");

    // crear un objeto ImageView con la imagen deseada
    Image image = new Image(stream);
    ImageView imageView = new ImageView(image);
    
    // crear un contenedor para la imagen y la vista FXML
    AnchorPane anchorPane = new AnchorPane();
    anchorPane.getChildren().addAll(imageView, root); // agregar la imagen y la vista al contenedor
    Scene scene = new Scene(anchorPane); // crear una escena con el contenedor como raíz
    
    primaryStage.setTitle("Imagen");
    primaryStage.setScene(scene);
    primaryStage.sizeToScene();
    primaryStage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}
