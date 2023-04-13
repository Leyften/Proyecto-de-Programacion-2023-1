
package intento.de.fondo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PestanaImagenFondo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Crear TabPane para contener las pestañas
        TabPane tabPane = new TabPane();
        
        // Crear pestaña con imagen de fondo
        Tab tab = new Tab("Pestaña con imagen de fondo");
        StackPane stackPane = new StackPane();
        ImageView imageView = new ImageView(new Image("\"C:\\Users\\augus\\Downloads\\fondomario.jpg\""));
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("fondo.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        pane.setBackground(new Background(backgroundImage));
        stackPane.getChildren().add(pane);
        tab.setContent(stackPane);
        tabPane.getTabs().add(tab);
        
        // Crear pestaña de ejemplo sin imagen de fondo
        Tab tab2 = new Tab("Otra pestaña");
        BorderPane borderPane = new BorderPane();
        Label label = new Label("Esta es otra pestaña");
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(20));
        borderPane.setCenter(label);
        tab2.setContent(borderPane);
        tabPane.getTabs().add(tab2);
        
        // Crear escena y mostrarla en la ventana
        Scene scene = new Scene(tabPane, 800, 600, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}