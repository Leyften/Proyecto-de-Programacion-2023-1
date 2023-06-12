/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nikol
 */
public class TrenController implements Initializable {
    
    int sangria = 30;
    int entre_espacio = 15;
    int posX= 0;
    int posY = 360;
    
    Random rand = new Random();
    
    boolean lista_ordenada = false;
    boolean existencia_Vagones = false;
    
    Vagon carril1 = new Vagon(20, 20, 5);
    
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
        if(Texto_Usuario.getText().matches(regex)){
            int cantidad = Integer.parseInt(Texto_Usuario.getText());
            if(existencia_Vagones==false){                
                Crear_Vagones(cantidad);
                existencia_Vagones = true;
            }else{
                AnchorPane.getChildren().removeAll(contenido);                      
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
    
    
    
    public void Crear_Vagones(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            int random = rand.nextInt(100);
            System.out.println(random);
            Vagon vagon = new Vagon((sangria+(50+entre_espacio)*i), posY, random);
            contenido.add(vagon);
            contenidoC.add(vagon);
            indices.add(i);
            indicesSub.add(i);
            AnchorPane.getChildren().add(vagon.getCanvas());
            
        }
        lista_ordenada=false;
    }
    
    public void insertion(){
        if(!lista_ordenada){
            System.out.println("Insertion");
            for (int i = 1; i < contenidoC.size(); i++) {
                Vagon vagonActual = contenidoC.get(i);
                int valorActual = (int) contenidoC.get(i).getValor();
                int j = i - 1;
                animacionV1(i);
                while(j >= 0 && contenidoC.get(j).getAncho()>valorActual){
                    animacionH1(j);
                    contenidoC.set((j+1), contenidoC.get(j));
                    j--;
                }
            contenidoC.set((j+1), vagonActual);
            animacionV2(i,(j+1));
            }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
            ventanaERROR("ordenado");
        }
    }
    
    void animacionV1(int i){
        int indice = (int) indices.get(i);
    }
    
    void animacionV2(int i, int j){
        int indiceI = (int) indices.get(i);
        int indiceJ = (int) indicesSub.get(j);
        
        reordenar();
    }
    
    void animacionH1(int j){
        int indiceJ = (int) indicesSub.get(j);
    }
    
    void reordenar(){
        for (int i = 0; i < indicesSub.size(); i++) {
            indices.set(i, indicesSub.get(i));
        }
    }
    
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
