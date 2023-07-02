/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nikol
 */
public class TrenController implements Initializable {
    
    int sangria = 32;
    int entre_espacio = 2;
    int posX= 0;
    int posY = 360;
    int duracion_animacion = 20;
    int punto_interseccion = 800;    
    int tamaño_vagon=30;
    
    Random rand = new Random();
    
    boolean lista_ordenada = false;
    boolean existencia_Vagones = false;  
    
    
    ArrayList<Vagon> contenido = new ArrayList();
    ArrayList<Vagon> contenidoC = new ArrayList();
    ArrayList               indices = new ArrayList();
    ArrayList               indicesSub = new ArrayList();
    
    SequentialTransition ANIMACIONES = new SequentialTransition ();
    
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button Boton_Crear;
    @FXML
    private Button Boton_Ordenar;
    @FXML
    private Button Boton_Pausa;
    @FXML
    private TextField Texto_Usuario;
    @FXML
    private Slider Barra_Velocidad;
    
    @FXML
    private Label linea1_for1;
    @FXML
    private Label linea2;
    @FXML
    private Label linea3_for2;
    @FXML
    private Label linea4_if1;
    @FXML
    private Label linea5;
    @FXML
    private Label linea6;
    @FXML
    private Label linea7;
    @FXML
    private Label linea8_if2;
    @FXML
    private Label linea9;
    @FXML
    private Label linea10;
    @FXML
    private Label linea11;
    @FXML
    private Label linea12;
    @FXML
    private Label linea13;
    
    
    public void ventanaERROR(String error){
        String textoError="";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");        
        
        
        switch (error){
            case "cantidad":
                textoError = "La cantidad ingresada debe ser un numero entero mayor a cero entre 16 a 64";
                break;
            case "ordenado":
                textoError = "Los vagones se están ordenando o ya han sido ordenados";
                break;
                
        }
        alert.setContentText(textoError);
        alert.showAndWait();
    }   
   
    
    @FXML
    public void Logica_Boton_Crear(){
        String regex = "^(1[6-9]|[2-5][0-9]|6[0-4])$";
        int cantidad = Integer.parseInt(Texto_Usuario.getText());
        if(Texto_Usuario.getText().matches(regex)){
            //int cantidad = Integer.parseInt(Texto_Usuario.getText());
            if(existencia_Vagones==false){                
                Crear_Vagones(cantidad);
                existencia_Vagones = true;
            }else{
                //AnchorPane.getChildren().removeAll(contenido); 
                borrar();
                contenido.clear();
                contenidoC.clear();
                indices.clear();
                indicesSub.clear();
                ANIMACIONES.stop();
                ANIMACIONES.getChildren().remove(0, ANIMACIONES.getChildren().size());
                Crear_Vagones(cantidad);
            }
            
        }else{
            ventanaERROR("cantidad");
        }
    }
    
    public void borrar(){
        for (int i = 0; i < contenido.size(); i++) {
            AnchorPane.getChildren().removeAll(contenido.get(i).canvas);
        }
    }
    
    public void Crear_Vagones(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            int random = rand.nextInt(100);
            Vagon vagon = new Vagon((sangria+(30+entre_espacio)*i), posY, random);
            contenido.add(vagon);
            contenidoC.add(vagon);
            indices.add(i);
            indicesSub.add(i);
            AnchorPane.getChildren().add(vagon.getCanvas());
            
        }        
        lista_ordenada=false;
    }
    
    @FXML
    public void seleccion(){
        if(!lista_ordenada){
            for (int i = contenidoC.size()-1; i > 0; i--) {
                int indiceMaximo = i;
                for (int j = i-1; j >= 0; j--) {
                    if((int) contenidoC.get(j).getValor()>(int) contenidoC.get(indiceMaximo).getValor()){
                        indiceMaximo = j;
                    }
                }
                if(!(indiceMaximo==i)){
                    Vagon temp = contenidoC.get(indiceMaximo); 
                    int tempInd = (int) indicesSub.get(indiceMaximo);
                    contenidoC.remove(indiceMaximo);
                    indicesSub.remove(indiceMaximo);
                    //animacion de separacion                    
                    ANIMACION_SEPARAR(indiceMaximo);                    
                    //animacion para juntar
                    ANIMACION_INCORPORACION(indiceMaximo, i);
                    contenidoC.add(i, temp);
                    indicesSub.add(i, tempInd);
                    //animacion de encabezar
                    ANIMACION_ENCABEZAR(i, indiceMaximo);
                }            
            }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
            ventanaERROR("ordenado");
        }
    }
    
    public void ANIMACION_COLOR(Label label, Duration time){
        Timeline timeline = new Timeline(
                //new KeyFrame(Duration.ZERO, new KeyValue(label.textFillProperty(), Color.WHITE)),
                //new KeyFrame(Duration.seconds(3), new KeyValue(label.textFillProperty(), Color.BLUE))
                new KeyFrame(Duration.seconds(0), new KeyValue(label.styleProperty(), "-fx-background-color: blue;")),
                new KeyFrame(time, new KeyValue(label.styleProperty(), "-fx-background-color: lightgray;"))

                
        );
        
        ANIMACIONES.getChildren().add(timeline);
        
        
    }
    
    public void ANIMACION_SEPARAR(int i){
        int indiceI = (int) indices.get(i);
        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();            
        int contador = 0;
        for (int j = i; j < indices.size(); j++) {            
            double desplazamientoH = punto_interseccion+((tamaño_vagon+entre_espacio)*(contador));
            int actual = (int) indices.get(j);
            TranslateTransition H1 = contenido.get(actual).ANIMACION(desplazamientoH, Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTO.getChildren().add(H1);
            contador++;
        }
        
        TranslateTransition H2 = contenido.get(indiceI).ANIMACION2C(1140, 14, Duration.millis(duracion_animacion*100));
            
        
        
        ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO, H2);
        
    }
    
    public void ANIMACION_INCORPORACION(int indiceMaximo, int i){
        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();
        
        int contador=0;
        for (int j = indiceMaximo-1; j >= 0; j--) {
            int actual = (int) indices.get(j);
            
            TranslateTransition H1 = contenido.get(actual).ANIMACION((punto_interseccion-(contador*(entre_espacio+tamaño_vagon))), Duration.millis(duracion_animacion*100)); 
            DESPLAZAMIENTO.getChildren().add(H1);
            contador++;
        }
        
        ParallelTransition  DESPLAZAMIENTO2 = new ParallelTransition ();
        
        /*
        for (int j = indiceMaximo+1; j <= i; j++) {
            int actual = (int) indices.get(j);
            
            TranslateTransition H1 = contenido.get(actual).ANIMACION((contenido.get(j-1).getCorX()), Duration.millis(duracion_animacion*100)); 
            
            DESPLAZAMIENTO2.getChildren().add(H1);
            
        } */
        for (int j = 0; j <= i; j++) {
            int actual = (int) indices.get(j);
            TranslateTransition H1 = new TranslateTransition();
            if(j<indiceMaximo){
                
            
               H1 = contenido.get(actual).ANIMACION((contenido.get(j).getCorX()), Duration.millis(duracion_animacion*100)); 
            
                
            }else if(j>indiceMaximo){
                H1 = contenido.get(actual).ANIMACION((contenido.get(j-1).getCorX()), Duration.millis(duracion_animacion*100)); 
            }
            DESPLAZAMIENTO2.getChildren().add(H1);
            
        }
        
        ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO, DESPLAZAMIENTO2);
    }
    
    public void ANIMACION_ENCABEZAR(int i, int indiceMaximo){
        int indexMax = (int) indices.get(indiceMaximo);       
               
        TranslateTransition H1 = contenido.get(indexMax).ANIMACION2C(punto_interseccion, posY, Duration.millis(duracion_animacion*100));
        
        
        TranslateTransition H2 = contenido.get(indexMax).ANIMACION((contenido.get(i).getCorX()), Duration.millis(duracion_animacion*100));
        
        if(!(i==contenido.size()-1)){
            ParallelTransition  DESPLAZAMIENTOH = new ParallelTransition ();
            int contador=1;
            for (int j = i+1; j < indices.size(); j++) {
                int actual = (int) indices.get(j);
                
                TranslateTransition H3 = contenido.get(actual).ANIMACION((punto_interseccion+((entre_espacio+tamaño_vagon)*contador)), Duration.millis(duracion_animacion*100));

                DESPLAZAMIENTOH.getChildren().add(H3);
                contador++;
            }
            
            
            ParallelTransition  DESPLAZAMIENTOH2 = new ParallelTransition ();
            for (int j = i+1; j < indices.size(); j++) {
                int actual = (int) indices.get(j);
                
                TranslateTransition H4 = contenido.get(actual).ANIMACION((contenido.get(j).getCorX()), Duration.millis(duracion_animacion*100));

                DESPLAZAMIENTOH2.getChildren().add(H4);                
            }
            DESPLAZAMIENTOH2.getChildren().add(H2);
            
            ANIMACIONES.getChildren().addAll(H1, DESPLAZAMIENTOH, DESPLAZAMIENTOH2);
        
        }else{
            ANIMACIONES.getChildren().addAll(H1, H2);
        }
        
        reordenar();
    }
    
    void reordenar(){
        for (int i = 0; i < indicesSub.size(); i++) {
            indices.set(i, indicesSub.get(i));
        }
    }
    
    @FXML
    public void LOGICA_Boton_Pausa_Reanudar(){
        String estado = ANIMACIONES.getStatus().name();
        if(estado=="RUNNING"){
            ANIMACIONES.pause();
        }else if(estado=="PAUSED"){
            ANIMACIONES.play();
        }        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Barra_Velocidad.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ANIMACIONES.setRate((int) Barra_Velocidad.getValue());
            }
        });        
    }
    
}
