/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package avanceproyectojava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author nikol
 */
public class AvanceProyectoJava extends Application {
    
    @Override
    public void start(/*Stage stage,*/ Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        /*
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
https://gist.github.com/Da9el00/73adff8130cbabf49bc12f0c168c4134
https://www.youtube.com/watch?v=wkm82FxgRyc&ab_channel=Randomcode
https://www.youtube.com/watch?v=-MUthrrarzw&t=88s&ab_channel=Randomcode
*/