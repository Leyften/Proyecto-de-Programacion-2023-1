package javafxapplication2;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class Grua {

    private ArrayList<Node> creargrua;

    public Grua(int par) {
        // Crear un círculo para el gancho
        Line gancho1 = new Line(par, 500, par+8,505);
        Line gancho2 = new Line(par+8, 505, par+8,513);
        Line gancho3 = new Line(par+8, 513, par,518);
        Line gancho4 = new Line(par, 518, par-8,513);
        Line gancho5 = new Line(par-8, 513, par-8,506);

        // Crear una línea para la cuerda
        Line cuerda = new Line(par, 60, par,500);

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
    
    public void eliminarGrua() {
        creargrua.clear();
    }
    
}