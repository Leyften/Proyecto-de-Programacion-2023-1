
package javafxapplication2;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class grua {

    private ArrayList<Node> creargrua;

    public grua() {
        // Crear un círculo para el gancho
        Circle gancho = new Circle(50, 700, 10);

        // Crear una línea para la cuerda
        Line cuerda = new Line(50, 60, 50, 700);

        // Crear una instancia de ArrayList
        creargrua = new ArrayList<>();

        // Agregar el círculo y la línea al ArrayList
        creargrua.add(gancho);
        creargrua.add(cuerda);
    }

    public Pane getPane() {
        // Crear un pane y agregar los elementos del ArrayList
        Pane pane = new Pane();
        pane.getChildren().addAll(creargrua);

        return pane;
    }
}