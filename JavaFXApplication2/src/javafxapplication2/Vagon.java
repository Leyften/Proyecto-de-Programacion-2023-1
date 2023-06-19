/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication2;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author nikol
 */
public class Vagon {
    int corX;
    int corY;
    
    double ancho = 60;
    double alto = 50;
    int valor;
    
    Canvas canvas = new Canvas(ancho, alto);
    
    NumBuilder numBuilder;
    
    ArrayList<Line> lines = new ArrayList<Line>();
    

    public Vagon(int corX, int corY, int valor) {
        this.corX = corX;
        this.corY = corY; 
        this.valor = valor;
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.ORANGE);        
        gc.fillRect(0, 0, ancho, alto);
        
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, ancho, alto);
        
        numBuilder = new NumBuilder(valor, 0, 0, ancho);
        
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
    
    public int getCorX() {
        return corX;
    }

    public void setCorX(int corX) {
        this.corX = corX;
    }

    public int getCorY() {
        return corY;
    }

    public void setCorY(int corY) {
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
