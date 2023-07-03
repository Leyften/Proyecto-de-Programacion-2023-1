package javafxapplication2;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Vagon {
    double corX;
    double corY;

    double ancho = 50;
    double alto = 50;
    int valor;

    double escala = 0.6; // Factor de escala

    Canvas canvas = new Canvas(ancho * escala, alto * escala);

    NumBuilder numBuilder;

    ArrayList<Line> lines = new ArrayList<Line>();

    public Vagon(double corX, double corY, int valor) {
        this.corX = corX;
        this.corY = corY;
        this.valor = valor;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.scale(escala, escala); // Aplicar escala al GraphicsContext

        gc.setFill(Color.ORANGE);
        gc.fillRect(0, 0, ancho, alto);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, ancho, alto);

        numBuilder = new NumBuilder(valor, 4, 15, ancho);

        gc.setStroke(Color.BLACK);

        for (int j = 0; j < numBuilder.getDigitos().size(); j++) {
            this.lines.addAll(numBuilder.getDigitos().get(j));
        }

        for (Line line : lines) {
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }

        canvas.setTranslateX(this.corX);
        canvas.setTranslateY(this.corY);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public double getCorX() {
        return corX;
    }

    public void setCorX(double corX) {
        this.corX = corX;
        canvas.setTranslateX(this.corX);
    }

    public double getCorY() {
        return corY;
    }

    public void setCorY(double corY) {
        this.corY = corY;
        canvas.setTranslateY(this.corY);
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
        canvas.setWidth(ancho * escala);
        redraw();
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
        canvas.setHeight(alto * escala);
        redraw();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.ORANGE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);

        for (Line line : lines) {
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }
}
