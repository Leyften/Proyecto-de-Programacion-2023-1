package javafxapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Crear el TabPane
        tabPane = new TabPane();

        // Crear las pestañas
        createSampleTab();
        createFerrocarrilesTab();

        // Crear el botón para generar las pestañas nuevamente
        Button regenerateButton = new Button("Regenerar pestañas");
        regenerateButton.setOnAction(event -> regeneracion());

        // Crear el contenedor para el botón
        VBox root = new VBox(tabPane, regenerateButton);

        // Crear la escena
        Scene scene = new Scene(root);

        primaryStage.setTitle("duramos+");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createSampleTab() throws Exception {
        Tab sampleTab = new Tab("gruas");
        Parent sampleRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));

        InputStream stream = new FileInputStream("C:\\Users\\augus\\Documents\\GitHub\\Proyecto-de-Programacion-2023-1\\JavaFXApplication2\\src\\fondo\\fondomario2.png");
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
    }

    private void createFerrocarrilesTab() throws Exception {
        Tab ferrocarrilesTab = new Tab("trenes");
        Parent ferrocarrilesRoot = FXMLLoader.load(getClass().getResource("tren.fxml"));

        // Crear un objeto ImageView con la imagen de fondo
        InputStream stream = new FileInputStream("C:\\Users\\augus\\Documents\\GitHub\\Proyecto-de-Programacion-2023-1\\JavaFXApplication2\\src\\fondo\\fond3.jpg");
        Image image = new Image(stream);
        ImageView imageView = new ImageView(image);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(ferrocarrilesRoot);
        AnchorPane.setTopAnchor(ferrocarrilesRoot, 0.0);
        AnchorPane.setBottomAnchor(ferrocarrilesRoot, 0.0);
        AnchorPane.setLeftAnchor(ferrocarrilesRoot, 0.0);
        AnchorPane.setRightAnchor(ferrocarrilesRoot, 0.0);

        ferrocarrilesTab.setContent(anchorPane);
        tabPane.getTabs().add(ferrocarrilesTab);
    }

    private void regeneracion() {
        tabPane.getTabs().clear();
        try {
            createSampleTab();
            createFerrocarrilesTab();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
