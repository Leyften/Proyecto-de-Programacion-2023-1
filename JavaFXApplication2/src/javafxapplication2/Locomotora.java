package javafxapplication2;

import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class Locomotora {
    double corX;
    double corY;
    
    double ancho = 30;
    double alto = 20;
    double escala = 1.1; // Factor de escala
    
    Canvas canvas = new Canvas(ancho * escala, alto * escala);
    Scale scale = new Scale(escala, escala);

    public Locomotora(double corX, double corY, int angulo) {
        this.corX = corX;
        this.corY = corY; 
        
        canvas.setTranslateX(this.corX); 
        canvas.setTranslateY(this.corY);
        canvas.getTransforms().add(scale); // Aplicar escala al Canvas
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.RED);        
        gc.fillRect(0, 0, ancho, alto);
        
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, ancho, alto);
        
        //Venta del conductor
        GraphicsContext ventana = canvas.getGraphicsContext2D();
        ventana.setFill(Color.BLUE);
        ventana.fillRect(22, 2, 6, 16);
        
        ventana.setStroke(Color.BLACK);
        ventana.setLineWidth(1);
        ventana.strokeRect(22, 2, 6, 16);
        //Venta del conductor
        
        //Chimenea
        GraphicsContext chimenea = canvas.getGraphicsContext2D();
        chimenea.setFill(Color.GREY);
        chimenea.fillOval(2, 2, 16, 16);
        //Chimenea
        
        //Humo
        GraphicsContext HUMO = canvas.getGraphicsContext2D();
        HUMO.setFill(Color.BLACK);
        HUMO.fillOval(4, 4, 12, 12);
        //Humo
        
        canvas.getTransforms().add(new javafx.scene.transform.Rotate(angulo, 15, 10));
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

    public double getCorX() {
        return corX;
    }

    public void setCorX(double corX) {
        this.corX = corX;
    }

    public double getCorY() {
        return corY;
    }

    public void setCorY(double corY) {
        this.corY = corY;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
