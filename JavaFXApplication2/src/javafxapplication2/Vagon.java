package javafxapplication2;

import java.util.ArrayList;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

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
    
    public TranslateTransition ANIMACION(double X, Duration time){
        TranslateTransition H = new TranslateTransition();        
                H.setNode(getCanvas());
                H.setDuration(time);
                H.setToX(X);
        return H;
    }
    
    
    public TranslateTransition ANIMACION2C(double X, double Y, Duration time){
        TranslateTransition H = new TranslateTransition();        
                H.setNode(getCanvas());
                H.setDuration(time);
                H.setToX(X);
                H.setToY(Y);
        return H;
    }
    
    public RotateTransition ANIMACION_ROTAR(int angulo, Duration time){
        RotateTransition R = new RotateTransition();
            R.setNode(getCanvas());
            R.setDuration(time);
            R.setToAngle(angulo);
        return R;
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
