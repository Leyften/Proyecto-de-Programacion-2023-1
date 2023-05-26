package javafxapplication2;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class Grua {

    private ArrayList<Node> creargrua;

    public Grua() {
        // Crear un círculo para el gancho
        Line gancho1 = new Line(50, 500, 58,505);
        Line gancho2 = new Line(58, 505, 58,513);
        Line gancho3 = new Line(58, 513, 50,518);
        Line gancho4 = new Line(50, 518, 42,513);
        Line gancho5 = new Line(42, 513, 42,506);

        // Crear una línea para la cuerda
        Line cuerda = new Line(50, 60, 50,500);

        // Crear una instancia de ArrayList
        creargrua = new ArrayList<>();

        // Agregar el círculo y la línea al ArrayList
        creargrua.add(gancho5);
        creargrua.add(gancho4);
        creargrua.add(gancho3);
        creargrua.add(gancho2);
        creargrua.add(gancho1);
        creargrua.add(cuerda);
    }

    public Pane getPane() {
        // Crear un pane y agregar los elementos del ArrayList
        Pane pane = new Pane();
        pane.getChildren().addAll(creargrua);

        return pane;
    }
}