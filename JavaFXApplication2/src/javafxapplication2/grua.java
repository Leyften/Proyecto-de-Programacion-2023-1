package javafxapplication2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Grua {
    Rectangle base_grua;
    Rectangle iman_grua;
    Line cuerda_grua;
    double Xbase_grua;
    double Yiman_grua;
    double tempX = 0;
    double tempY = 0;

    public Grua(Rectangle base_grua, Rectangle iman_grua, Line cuerda_grua, double Xbase_grua, double Yiman_grua) {
        this.base_grua = base_grua;
        this.iman_grua = iman_grua;
        this.cuerda_grua = cuerda_grua;
        this.Xbase_grua = Xbase_grua;
        this.Yiman_grua = Yiman_grua;
    }

    public Grua() {
    }
    
    
    public ParallelTransition  DESPLAZAMIENTOH(Duration time ,double desplazamientoH){
        TranslateTransition DESPLAZAMIENTOH_base_grua = new TranslateTransition();
            DESPLAZAMIENTOH_base_grua.setNode(base_grua);
            DESPLAZAMIENTOH_base_grua.setDuration(time);            
            DESPLAZAMIENTOH_base_grua.setByX(desplazamientoH);
            
        TranslateTransition DESPLAZAMIENTOH_iman_grua = new TranslateTransition();
            DESPLAZAMIENTOH_iman_grua.setNode(iman_grua);
            DESPLAZAMIENTOH_iman_grua.setDuration(time);            
            DESPLAZAMIENTOH_iman_grua.setByX(desplazamientoH);
            
        TranslateTransition DESPLAZAMIENTOH_cuerda_grua = new TranslateTransition();
            DESPLAZAMIENTOH_cuerda_grua.setNode(cuerda_grua);
            DESPLAZAMIENTOH_cuerda_grua.setDuration(time);            
            DESPLAZAMIENTOH_cuerda_grua.setByX(desplazamientoH);       
        
        
        ParallelTransition  DESPLAZAMIENTOH = new ParallelTransition ();
        DESPLAZAMIENTOH.getChildren().addAll(DESPLAZAMIENTOH_base_grua, DESPLAZAMIENTOH_iman_grua, DESPLAZAMIENTOH_cuerda_grua);
        return DESPLAZAMIENTOH;
    }
    
    public ParallelTransition  DESPLAZAMIENTOV(Duration time, double desplazamientoV, double caja_posY){
        TranslateTransition DESPLAZAMIENTOV_iman_grua = new TranslateTransition();
            DESPLAZAMIENTOV_iman_grua.setNode(iman_grua);
            DESPLAZAMIENTOV_iman_grua.setDuration(time);
            DESPLAZAMIENTOV_iman_grua.setByY(desplazamientoV);
                Timeline DESPLAZAMIENTOV_cuerda_grua = stretchLine(time, caja_posY);
        
        
        ParallelTransition  DESPLAZAMIENTOV = new ParallelTransition ();
            DESPLAZAMIENTOV.getChildren().addAll(DESPLAZAMIENTOV_iman_grua, DESPLAZAMIENTOV_cuerda_grua);
        return DESPLAZAMIENTOV;
    }    
    
    
    private Timeline stretchLine(Duration time, double targetY) {
        double startY = cuerda_grua.getStartY();        
        KeyValue keyValueY = new KeyValue(cuerda_grua.endYProperty(), targetY);
        KeyFrame keyFrameY = new KeyFrame(time, keyValueY);
        Timeline timeline = new Timeline(keyFrameY);
        cuerda_grua.setEndY(startY);
        
        return timeline;
    }

    public Rectangle getBase_grua() {
        return base_grua;
    }

    public void setBase_grua(Rectangle base_grua) {
        this.base_grua = base_grua;
    }

    public Rectangle getIman_grua() {
        return iman_grua;
    }

    public void setIman_grua(Rectangle iman_grua) {
        this.iman_grua = iman_grua;
    }

    public Line getCuerda_grua() {
        return cuerda_grua;
    }

    public void setCuerda_grua(Line cuerda_grua) {
        this.cuerda_grua = cuerda_grua;
    }

    public double getXbase_grua() {
        return Xbase_grua;
    }

    public void setXbase_grua(double Xbase_grua) {
        this.Xbase_grua = Xbase_grua;
    }

    public double getYiman_grua() {
        return Yiman_grua;
    }

    public void setYiman_grua(double Yiman_grua) {
        this.Yiman_grua = Yiman_grua;
    }

    public double getTempX() {
        return tempX;
    }

    public void setTempX(double tempX) {
        this.tempX = tempX;
    }

    public double getTempY() {
        return tempY;
    }

    public void setTempY(double tempY) {
        this.tempY = tempY;
    }
    
    
    
    
    
    
    
    
    
}