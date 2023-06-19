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
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    
    int sangria = 2;
    int entre_espacio = 2;
    int posX= 0;
    int posY = 360;
    int duracion_animacion = 20;
    int punto_interseccion = 800;
    
    Random rand = new Random();
    
    boolean lista_ordenada = false;
    boolean existencia_Vagones = false;  
    
    
    ArrayList<Vagon> contenido = new ArrayList();
    ArrayList<Vagon> contenidoC = new ArrayList();
    ArrayList<Vagon> via_Principal = new ArrayList();
    ArrayList<Vagon> via_Superior = new ArrayList();
    ArrayList<Vagon> via_Inferior = new ArrayList();
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
                //Crear_Vagones(cantidad);
                pruebas();
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
                //Crear_Vagones(cantidad);
                pruebas();
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
    
    public void pruebas(){
        ArrayList numeros = new ArrayList();
        numeros.add(2);
        numeros.add(5);
        numeros.add(3);
        numeros.add(1);
        numeros.add(4);
        
        for (int i = 0; i < 5; i++) {
            Vagon vagon = new Vagon((sangria+(30+entre_espacio)*i), posY, (int) numeros.get(i));
            contenido.add(vagon);
            contenidoC.add(vagon);
            via_Principal.add(vagon);
            indices.add(i);
            indicesSub.add(i);
            AnchorPane.getChildren().add(vagon.getCanvas());
            
        }        
        lista_ordenada=false;
    }
    
    public void Crear_Vagones(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            int random = rand.nextInt(100);
            Vagon vagon = new Vagon((sangria+(30+entre_espacio)*i), posY, random);
            contenido.add(vagon);
            contenidoC.add(vagon);
            via_Principal.add(vagon);
            indices.add(i);
            indicesSub.add(i);
            AnchorPane.getChildren().add(vagon.getCanvas());
            
        }        
        lista_ordenada=false;
    }
    
    @FXML
    public void seleccion(){
        if(!lista_ordenada){
            System.out.println("Insertion");
            for (int i = contenidoC.size()-1; i > 0; i--) {
            int indiceMaximo = i;
                for (int j = i-1; j >= 0; j--) {
                    if((int) contenidoC.get(j).getValor()>(int) contenidoC.get(indiceMaximo).getValor()){
                        indiceMaximo = j;
                    }
                }
            Vagon vagonTemp = contenidoC.get(indiceMaximo);
            //animacion de separacion
            animacionSeparacion(indiceMaximo, i);
            contenidoC.set(indiceMaximo, contenidoC.get(i));
            //animacion de cambio
            animacionCambio(indiceMaximo,i);
            contenidoC.set(i, vagonTemp);
            //animacion de insercion
        }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
            ventanaERROR("ordenado");
        }
    }
    
    public void animacionSeparacion(int indiceMaximo, int i){
        int indexM = (int) indices.get(indiceMaximo);
        int indiceI = (int) indices.get(i);
        
        ParallelTransition  desplazamiento = new ParallelTransition ();
        
        for (int indice = indiceMaximo+1; indice <=i; indice++) {
            int actual = (int) indices.get(indice);
            
            TranslateTransition H1 = new TranslateTransition();
        
            H1.setNode(contenido.get(actual).getCanvas());
            H1.setDuration(Duration.millis(duracion_animacion*100));

                H1.setByX(punto_interseccion);
            
            desplazamiento.getChildren().add(H1);
        }
        
        TranslateTransition IndexMH = new TranslateTransition();
        
            IndexMH.setNode(contenido.get(indexM).getCanvas());
            IndexMH.setDuration(Duration.millis(duracion_animacion*100));

                IndexMH.setToX(punto_interseccion);
            
        TranslateTransition IndexMVH = new TranslateTransition();
        
            IndexMVH.setNode(contenido.get(indexM).getCanvas());
            IndexMVH.setDuration(Duration.millis(duracion_animacion*100));

                IndexMVH.setToX(1140);
                IndexMVH.setToY(14);
                contenido.get(indexM).setCorX(1140);
                contenido.get(indexM).setCorY(14);
        
        
       ANIMACIONES.getChildren().addAll(desplazamiento, IndexMH, IndexMVH);
    }
    
    public void animacionCambio(int indiceMaximo, int i){
        int indexM = (int) indices.get(indiceMaximo);
        int indiceI = (int) indices.get(i);
        
        ParallelTransition  carrierIZQ = new ParallelTransition ();        
        
        for (int indice = indiceMaximo+1; indice <i; indice++) {
            int actual = (int) indices.get(indice);
            
            TranslateTransition H1 = new TranslateTransition();
        
            H1.setNode(contenido.get(actual).getCanvas());
            H1.setDuration(Duration.millis(duracion_animacion*100));

                //H1.setToX(punto_interseccion-(30*(indice-i-1)));
                H1.setToX(punto_interseccion-(30*(i-actual-1)));
            
            carrierIZQ.getChildren().add(H1);
        }
        
        ParallelTransition  reiconporado = new ParallelTransition ();
        for (int indice = indiceMaximo+1; indice <i; indice++) {
            int actual = (int) indices.get(indice);
            
            TranslateTransition H1 = new TranslateTransition();
        
            H1.setNode(contenido.get(actual).getCanvas());
            H1.setDuration(Duration.millis(duracion_animacion*100));

                //H1.setToX(punto_interseccion-(30*(indice-i-1)));
                H1.setToX(contenido.get(indexM).getCorX()-(30*(i-actual-1)));
                H1.setToY(contenido.get(indexM).getCorY()-(30*(i-actual-1)));
            
            reiconporado.getChildren().add(H1);
        }
        ANIMACIONES.getChildren().addAll(carrierIZQ, reiconporado);
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

    @FXML
    private void insertion(ActionEvent event) {
    }
    
}
