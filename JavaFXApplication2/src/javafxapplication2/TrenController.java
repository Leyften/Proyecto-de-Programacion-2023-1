
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


public class TrenController implements Initializable {
    
    int sangria = 2;
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
    
    public void ANIMACION_SEPARAR(int i){
        int indiceI = (int) indices.get(i);
        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();            
        int contador = 0;
        for (int j = i; j < indices.size(); j++) {            
            double desplazamientoH = punto_interseccion+((tamaño_vagon+entre_espacio)*(contador));
            int actual = (int) indices.get(j);
            
            TranslateTransition H1 = new TranslateTransition();        
                H1.setNode(contenido.get(actual).getCanvas());
                H1.setDuration(Duration.millis(duracion_animacion*100));

                H1.setToX(desplazamientoH);
            
            DESPLAZAMIENTO.getChildren().add(H1);
            contador++;
        }
        
        TranslateTransition H2 = new TranslateTransition();        
            H2.setNode(contenido.get(indiceI).getCanvas());
            H2.setDuration(Duration.millis(duracion_animacion*100));

            H2.setToX(1140);
            H2.setToY(14);
        
            
        ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO, H2);
        
    }
    
    public void ANIMACION_INCORPORACION(int indiceMaximo, int i){
        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();
        
        for (int j = indiceMaximo+1; j <= i; j++) {
            int actual = (int) indices.get(j);
            
            TranslateTransition H1 = new TranslateTransition();
                H1.setNode(contenido.get(actual).getCanvas());
                H1.setDuration(Duration.millis(duracion_animacion*100));                
                
                H1.setToX(contenido.get(j-1).getCorX());
                
            DESPLAZAMIENTO.getChildren().add(H1);
        }  
        ANIMACIONES.getChildren().addAll(DESPLAZAMIENTO);
    }
    
    public void ANIMACION_ENCABEZAR(int i, int indiceMaximo){
        int indexMax = (int) indices.get(indiceMaximo); 
        
        TranslateTransition H1 = new TranslateTransition();        
                H1.setNode(contenido.get(indexMax).getCanvas());
                H1.setDuration(Duration.millis(duracion_animacion*100));

                H1.setToX(800);
                H1.setToY(posY);
                
                
        
        TranslateTransition H2 = new TranslateTransition();        
                H2.setNode(contenido.get(indexMax).getCanvas());
                H2.setDuration(Duration.millis(duracion_animacion*100));

                H2.setToX(contenido.get(i).getCorX());
                
        ANIMACIONES.getChildren().addAll(H1, H2);
                
        if(!(i==contenido.size()-1)){
            ParallelTransition  DESPLAZAMIENTOH = new ParallelTransition (); 
            for (int j = i+1; j < indices.size(); j++) {
                int actual = (int) indices.get(j);

                TranslateTransition H3 = new TranslateTransition();        
                    H3.setNode(contenido.get(actual).getCanvas());
                    H3.setDuration(Duration.millis(duracion_animacion*100));

                    H3.setToX(contenido.get(j).getCorX());


                DESPLAZAMIENTOH.getChildren().add(H3);
            }
            ANIMACIONES.getChildren().add(DESPLAZAMIENTOH);
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

    @FXML
    private void insertion(ActionEvent event) {
    }
    
}
