
package javafxapplication2;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class grua {
    public Pane creargrua() {
        // Crear un círculo para el gancho
        Circle gancho = new Circle(50, 600, 10);

        // Crear una línea para la cuerda
        Line cuerda = new Line(50, 60, 50, 600);

        // Crear un panel y agregar el gancho y la cuerda
        Pane panel = new Pane();
        panel.getChildren().addAll(gancho, cuerda);

        return panel;
    }
}