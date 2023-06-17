package javafxapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Crear el TabPane
        TabPane tabPane = new TabPane();

        // Cargar la imagen en la pestaña "Sample"
        Tab sampleTab = new Tab("Unidad 1 y 2");
        Parent sampleRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        sampleTab.setContent(sampleRoot);

        InputStream stream = new FileInputStream("C:\\Users\\nikol\\Desktop\\trabajando\\JavaFXApplication2\\src\\fondo\\fondomario2.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView(image);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(sampleRoot);
        AnchorPane.setTopAnchor(sampleRoot, 0.0);
        AnchorPane.setBottomAnchor(sampleRoot, 0.0);
        AnchorPane.setLeftAnchor(sampleRoot, 0.0);
        AnchorPane.setRightAnchor(sampleRoot, 0.0);

        sampleTab.setContent(anchorPane);

        tabPane.getTabs().add(sampleTab);

        // Agregar la pestaña "Ferrocarriles" sin imagen
        Tab ferrocarrilesTab = new Tab("Unidad 3");
        Parent ferrocarrilesRoot = FXMLLoader.load(getClass().getResource("tren.fxml"));
        ferrocarrilesTab.setContent(ferrocarrilesRoot);
        tabPane.getTabs().add(ferrocarrilesTab);

        // Crear la escena con el TabPane
        Scene scene = new Scene(tabPane);

        primaryStage.setTitle("Duramos+");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
