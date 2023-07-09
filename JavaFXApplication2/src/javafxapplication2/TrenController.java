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
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
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
    //ESPACIO PARA MODIFICAR 
    String apagado = "-fx-background-color: white;";
    String encendido = "-fx-background-color: blue;";    
    int sangria = 32;
    int entre_espacio = 2;
    int posX= 0;
    int posY = 360;
    int duracion_animacion = 20;
    int punto_interseccion = 800;    
    int tamaño_vagon=30;
    int CORD_Y_LOC = 14;
    int CORD_X_LOC = 1140;
    //ESPACIO PARA MODIFICAR 
    
    int CORD_Y_VAGON = CORD_Y_LOC+(entre_espacio+tamaño_vagon);
    int CORD_X_VAGON = CORD_X_LOC-(entre_espacio+tamaño_vagon);
    
    Random rand = new Random();
    
    boolean lista_ordenada = false;
    boolean existencia_Vagones = false;  
    
    
    ArrayList<Vagon> contenido = new ArrayList();
    ArrayList<Vagon> contenidoC = new ArrayList();
    ArrayList               indices = new ArrayList();
    ArrayList               indicesSub = new ArrayList();
    
    SequentialTransition ANIMACIONES = new SequentialTransition ();
    
    Locomotora loc1;
    Locomotora loc2;
    Locomotora loc3;
    
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
        try{
            String regex = "^(1[6-9]|[2-5][0-9]|6[0-4])$";
            int cantidad = Integer.parseInt(Texto_Usuario.getText());
            if(Texto_Usuario.getText().matches(regex)){ 
                if(existencia_Vagones==false){                
                    Crear_Vagones(cantidad);
                    existencia_Vagones = true;
                }else{
                    borrar();
                    borrarLocomotora();
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
        }catch(NumberFormatException e){
            ventanaERROR("Deberia ingresar un numero");
        }        
    }
    
    public void borrar(){
        for (int i = 0; i < contenido.size(); i++) {
            AnchorPane.getChildren().removeAll(contenido.get(i).canvas);
        }
    }
    
    public Locomotora crearLocmotora(double X, double Y, int angulo){
        Locomotora Loc = new Locomotora(X,Y, angulo);
        return Loc;
    }
    
    public void añadirLocomotora(){
        AnchorPane.getChildren().addAll(loc1.getCanvas(), loc2.getCanvas(), loc3.getCanvas());
    }
    
    public void borrarLocomotora(){
        AnchorPane.getChildren().removeAll(loc1.getCanvas(), loc2.getCanvas(), loc3.getCanvas());
    }
    
    public void Crear_Vagones(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            int random = rand.nextInt(100);
            Vagon vagon = new Vagon((sangria+(tamaño_vagon+entre_espacio)*i), posY, random);
            contenido.add(vagon);
            contenidoC.add(vagon);
            indices.add(i);
            indicesSub.add(i);
            AnchorPane.getChildren().add(vagon.getCanvas());
            
        }
        loc1 = crearLocmotora(0, posY, 180);            
        loc2 = crearLocmotora(CORD_X_LOC, CORD_Y_LOC, -45);
        loc3 = crearLocmotora((punto_interseccion+32), posY, 0);
        añadirLocomotora();
        lista_ordenada=false;
    }
    
    @FXML
    public void seleccion(){
        if(!lista_ordenada){            
            for (int i = contenidoC.size()-1; i > 0; i--) {
                int indiceMaximo = i;
                //ANIMACION de los labels
                ANIMACION_for1();
                for (int j = i-1; j >= 0; j--) {
                    //ANIMACION de los labels
                    ANIMACION_for2();
                    if((int) contenidoC.get(j).getValor()>(int) contenidoC.get(indiceMaximo).getValor()){                   
                        indiceMaximo = j;
                        //ANIMACION de los labels
                        ANIMACION_if1();                        
                    }
                }
                //ANIMACION de los labels
                ANIMACION_if2();
                if(!(indiceMaximo==i)){                    
                    Vagon temp = contenidoC.get(indiceMaximo);
                    //ANIMACION de los labels
                    ANIMACION_linea9();
                    int tempInd = (int) indicesSub.get(indiceMaximo);
                    contenidoC.remove(indiceMaximo);
                    indicesSub.remove(indiceMaximo);
                    //ANIMACION    
                    ANIMACION_REMOVE();
                    ANIMACION_SEPARAR(indiceMaximo);                      
                    ANIMACION_INCORPORACION(indiceMaximo, i);                    
                    //ANIMACION 
                    contenidoC.add(i, temp);
                    indicesSub.add(i, tempInd);                    
                    //ANIMACION de los labels
                    ANIMACION_linea11();
                    ANIMACION_ENCABEZAR(i, indiceMaximo);
                    //ANIMACION de los labels
                }            
            }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
            ventanaERROR("ordenado");
        }
    }
    
    public void ANIMACION_for1(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea1_for1));
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea2));
    }
    
    public void ANIMACION_for2(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea3_for2));
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea4_if1));
    }
    
    public void ANIMACION_if1(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea5));
    }
    
    public void ANIMACION_if2(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea8_if2));
    }
    
    public void ANIMACION_linea9(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea9));
    }
    
    public void ANIMACION_REMOVE(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea10));
    }
    
    public void ANIMACION_linea11(){
        ANIMACIONES.getChildren().add(ANIMACION_COLOR(linea11));
    }
    
    
    public Timeline ANIMACION_COLOR(Label label){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), new KeyValue(label.styleProperty(), encendido)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(label.styleProperty(), apagado))

                
        );
        return timeline;
        
    }
    
    public void ANIMACION_SEPARAR(int i){
        int indiceI = (int) indices.get(i);
        double encabezado = (contenido.get(contenido.size()-1).getCorX())+entre_espacio+tamaño_vagon;
        
        TranslateTransition H = loc3.ANIMACION(encabezado, Duration.millis(duracion_animacion*100));
        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();            
        int contador = 0;
        for (int j = i; j < indices.size(); j++) {            
            double desplazamientoH = punto_interseccion+((tamaño_vagon+entre_espacio)*(contador));
            int actual = (int) indices.get(j);
            TranslateTransition H1 = contenido.get(actual).ANIMACION(desplazamientoH, Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTO.getChildren().add(H1);
            contador++;
        }
        
        double temp =punto_interseccion+((tamaño_vagon+entre_espacio)*(contador));
        TranslateTransition H2 = loc3.ANIMACION(temp, Duration.millis(duracion_animacion*100));
        DESPLAZAMIENTO.getChildren().add(H2);
        
        double separado = entre_espacio+tamaño_vagon;
        TranslateTransition H3 = loc2.ANIMACION2C((punto_interseccion+separado), (posY-separado), Duration.millis(duracion_animacion*100));
        
        RotateTransition R1 = contenido.get(indiceI).ANIMACION_ROTAR(-45, Duration.millis(duracion_animacion*100));
        
        ParallelTransition  DESPLAZAMIENTO_VIASEC = new ParallelTransition ();
        TranslateTransition H4 = contenido.get(indiceI).ANIMACION2C(CORD_X_VAGON, CORD_Y_VAGON, Duration.millis(duracion_animacion*100));
        TranslateTransition H5 = loc2.ANIMACION2C(CORD_X_LOC, CORD_Y_LOC, Duration.millis(duracion_animacion*100));
        DESPLAZAMIENTO_VIASEC.getChildren().addAll(H4, H5);
        
        ANIMACIONES.getChildren().addAll(H, DESPLAZAMIENTO, H3, R1, DESPLAZAMIENTO_VIASEC);
        
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
        
        double encabezado = punto_interseccion-(contador*(entre_espacio+tamaño_vagon));
        TranslateTransition H = loc1.ANIMACION(encabezado, Duration.millis(duracion_animacion*100));
        DESPLAZAMIENTO.getChildren().add(H);
        
        ParallelTransition  DESPLAZAMIENTO2 = new ParallelTransition ();
        
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
        
        TranslateTransition H3 = loc1.ANIMACION(0, Duration.millis(duracion_animacion*100));
        DESPLAZAMIENTO2.getChildren().add(H3);
        
        ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO, DESPLAZAMIENTO2);
    }
    
    public void ANIMACION_ENCABEZAR(int i, int indiceMaximo){
        int indexMax = (int) indices.get(indiceMaximo);       
        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();
        
        double separado = entre_espacio+tamaño_vagon;
        TranslateTransition H = loc2.ANIMACION2C((punto_interseccion+separado), (posY-separado), Duration.millis(duracion_animacion*100));
        TranslateTransition H1 = contenido.get(indexMax).ANIMACION2C(punto_interseccion, posY, Duration.millis(duracion_animacion*100));
        
        DESPLAZAMIENTO.getChildren().addAll(H, H1);
        
        RotateTransition R2 = contenido.get(indexMax).ANIMACION_ROTAR(0, Duration.millis(duracion_animacion*100));
        

        TranslateTransition H3 = contenido.get(indexMax).ANIMACION((contenido.get(i).getCorX()), Duration.millis(duracion_animacion*100));
        
        
        if(!(i==contenido.size()-1)){
            ParallelTransition  DESPLAZAMIENTOH = new ParallelTransition ();
            int contador=1;
            for (int j = i+1; j < indices.size(); j++) {
                int actual = (int) indices.get(j);
                
                TranslateTransition H4 = contenido.get(actual).ANIMACION((punto_interseccion+((entre_espacio+tamaño_vagon)*contador)), Duration.millis(duracion_animacion*100));

                DESPLAZAMIENTOH.getChildren().add(H4);
                contador++;
            }
            
            double encabezado = punto_interseccion+((entre_espacio+tamaño_vagon)*contador);
            TranslateTransition H2 = loc3.ANIMACION(encabezado, Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTOH.getChildren().add(H2);
            
            ParallelTransition  DESPLAZAMIENTOH2 = new ParallelTransition ();
            for (int j = i+1; j < indices.size(); j++) {
                int actual = (int) indices.get(j);
                
                TranslateTransition H5 = contenido.get(actual).ANIMACION((contenido.get(j).getCorX()), Duration.millis(duracion_animacion*100));

                DESPLAZAMIENTOH2.getChildren().add(H5);                
            }
            
            double v = contenido.get(contenido.size()-1).corX;
            
            TranslateTransition H6 = loc3.ANIMACION((v+separado), Duration.millis(duracion_animacion*100));
            
            DESPLAZAMIENTOH2.getChildren().addAll(H3, H6);
            
            ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO, R2, DESPLAZAMIENTOH, DESPLAZAMIENTOH2);
        
        }else{            
            TranslateTransition H7 = loc3.ANIMACION((punto_interseccion+separado), Duration.millis(duracion_animacion*100));
            
            ParallelTransition incorporado = new ParallelTransition();
            TranslateTransition H8 = loc3.ANIMACION((contenido.get(i).getCorX()+separado), Duration.millis(duracion_animacion*100));
            incorporado.getChildren().addAll(H3, H8);
            ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO, R2, H7,incorporado);
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
