package javafxapplication2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.transform.Translate;
import java.util.Collections;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;



public class Controller implements Initializable{
 

    @FXML
    private AnchorPane anchorPane;

    Random rand = new Random();
    
    ArrayList<Rectangle> contenido = new ArrayList<Rectangle>();
    ArrayList<Rectangle> contenidoC = new ArrayList<Rectangle>();
    ArrayList               indices = new ArrayList();
    ArrayList               indicesSub = new ArrayList();
    SequentialTransition ANIMACIONES = new SequentialTransition ();
    
    boolean temp = false;
    boolean lista_ordenada = false;
    boolean en_reversa = false;/**/
    //boolean temp2 = false;
    
    
    int posX = 50;
    int posY = 625;
    int ancho=0;
    int sangria=30;
    int entre_espacio=10;
    int duracion_animacion=1;
            
    
    int espacio_reservado = 0;
    int espacio_reservadoI = 0;
    
    
    @FXML
    private TextField text_usuario;
    @FXML
    private Button Alma;
    @FXML
    private Button burbuja;
    @FXML
    private Button Boton_Pausa;
    @FXML
    private Button Boton_Atras;
    @FXML
    private Slider barra_duracion;
    @FXML
    private Button cocktailsort;
    
    

    @FXML
    void start(ActionEvent event) {
        try{
            //int cantidad = (int) (this.text_usuario.getText());
            double cantidad = Double.parseDouble(this.text_usuario.getText());
            if((cantidad>=16) && (cantidad<=64) && ((cantidad%1)==0)){
                if(this.temp == false){
                      crearRect((int) (cantidad-1));
                      this.temp=true;                                            
                      //this.temp2=false;                      
                  }else{        
                      this.anchorPane.getChildren().removeAll(this.contenido);
                      this.contenido.clear();    
                      this.contenidoC.clear();
                      this.indices.clear();
                      this.indicesSub.clear();
                      this.ANIMACIONES.stop();
                      this.ANIMACIONES.getChildren().remove(0, this.ANIMACIONES.getChildren().size());                      
                      crearRect((int )(cantidad-1));
                  }  
            }else{
                ventanaERROR();
            }
        }catch(NumberFormatException e){
            ventanaERROR();
        }
        
    }
    
    @FXML
    public void LOGICA_Boton_Pausa_Reanudar(){
        String estado = ANIMACIONES.getStatus().name();
        //&&estado==RUNNING && estado==STOPPED
        if(estado=="RUNNING"){
            ANIMACIONES.pause();
        }else if(estado=="PAUSED"){
            ANIMACIONES.play();
        }
        
    }
    
    @FXML
    public void lOGICA_Boton_Atras(){
        
        ANIMACIONES.pause();
        ANIMACIONES.setRate(-1);
        ANIMACIONES.play();
    }
    
    public void ventanaERROR(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText("La cantidad ingresada debe ser un numero entero mayor a cero entre 16 a 64");
        alert.showAndWait();
    }
    
    public void ventanaORDEN(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText("Las cajas se están ordenando o ya han sido ordenadas");
        alert.showAndWait(); 
    }
    
    public void crearRect(int cantidad){
        ancho = ((1025-sangria-((cantidad+4)*entre_espacio))/(cantidad+3));
        espacio_reservado = 1025-(ancho);
        espacio_reservadoI = sangria+ancho+10;     
        for (int i = 0; i <= cantidad; i++) {
            int random = rand.nextInt(100);
            Rectangle caja = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*i)), (posY-random), ancho, random);
            caja.setFill(Color.web("#2191FB"));
            this.contenido.add(caja);
            this.contenidoC.add(caja);
            anchorPane.getChildren().addAll(this.contenido.get(i));
            
            indices.add(i);
            indicesSub.add(i);
        }
        this.lista_ordenada=false;
    }
    

    
    
    @FXML
    void insertionSort3() {
        if(!lista_ordenada){
            for (int i = 1; i < contenidoC.size(); i++) {
                Rectangle cajaActual = contenidoC.get(i);
                int valorActual=(int) contenidoC.get(i).getHeight();
                int j = i - 1;
                animacionV1(i);
                while(j >= 0 && contenidoC.get(j).getHeight()> valorActual){
                    animacionH1(j);
                    contenidoC.set((j+1), contenidoC.get(j));
                    j--;
                }
            contenidoC.set((j+1), cajaActual);
            animacionV2(i,(j+1));
            }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
            ventanaORDEN();
        }   
    }
    
    void animacionV1(int i){
        TranslateTransition V1 = new TranslateTransition();
        //contenido.get(i).setFill(Color.web("#FA4439"));
        int indice = (int) indices.get(i);
        //System.out.println(indices);
        //System.out.println("V1 "+indice);
        V1.setNode(contenido.get(indice));
        V1.setDuration(Duration.millis(duracion_animacion*100));
        
        V1.setByY(-100);
        //V1.setByY(100);
        ANIMACIONES.getChildren().add(V1);
    }
    
    void animacionV2(int i, int j){
        TranslateTransition transicion = new TranslateTransition();
        transicion.setDuration(Duration.millis(duracion_animacion*100)); 
        
        int indiceI = (int) indices.get(i);
        //System.out.println(indices);
        int indiceJ = (int) indicesSub.get(j);
        //System.out.println(indicesSub);
        
        //System.out.println("V2 "+indiceI);
        transicion.setNode(contenido.get(indiceI)); 
        double desplazamientoV = (contenido.get(i).getX()-contenido.get(j).getX());
        //System.out.println(i+"---"+j);
        
        //System.out.println("V2"+contenido.get(i).getX());
        //System.out.println("V2"+contenido.get(j).getX());
        transicion.setByX(-desplazamientoV);
        
        
        TranslateTransition transicion2 = new TranslateTransition();
        transicion2.setNode(contenido.get(indiceI));
        transicion2.setDuration(Duration.millis(duracion_animacion*100));
        
        transicion2.setByY(100);
        //transicion2.setByY(-100);
        //contenido.get(i).setFill(Color.web("#2191FB"));
        indicesSub.set(j, indiceI);
        
        
        
        reordenar();
        
        ANIMACIONES.getChildren().add(transicion);
        ANIMACIONES.getChildren().add(transicion2);
        
        
    }
    
    void animacionH1(int j){
        
        TranslateTransition H1 = new TranslateTransition();
        int indiceJ = (int) indicesSub.get(j);
        H1.setNode(contenido.get(indiceJ));
        H1.setDuration(Duration.millis(duracion_animacion*100));
        
        contenido.get(j).setFill(Color.web("#E6FA07"));
        double desplazamientoH = (ancho+10);
        H1.setByX(desplazamientoH);
        contenido.get(j).setFill(Color.web("#2191FB"));
        
        indicesSub.set((j+1),indiceJ);
        
        ANIMACIONES.getChildren().add(H1);
    }
    
    void reordenar(){
        for (int i = 0; i < indicesSub.size(); i++) {
            indices.set(i, indicesSub.get(i));
        }
    }
       
    
    @FXML
    public void burbujaOP(){
        if(!lista_ordenada){
            for (int i = 1; i < contenidoC.size(); i++) {
                boolean intercambio = false;
                for (int j = 0; (j < ((contenidoC.size()-i))); j++) {
                    if((contenidoC.get(j).getHeight())>(contenidoC.get(j+1).getHeight())){
                        //animacionV1(j+1);
                        BURBUJA_animacionVH((j+1), false);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j);
                        contenidoC.set((j), contenidoC.get(j+1));
                        
                        //animacionV2(j+1,(j));
                        BURBUJA_animacionVH2(j+1,(j), false);
                        contenidoC.set((j+1), actual);
                        intercambio = true;
                        
                    }
                }
                if(!intercambio){
                    break;
                }
            }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
            ventanaORDEN();
        }
            
    }
    
    public void BURBUJA_animacionVH(int i, boolean cocktail){
        TranslateTransition V1 = new TranslateTransition();
            int indice = (int) indices.get(i);
            V1.setNode(contenido.get(indice));
            V1.setDuration(Duration.millis(duracion_animacion*100));

            V1.setByY(-100);
            ANIMACIONES.getChildren().add(V1);
        
        
        TranslateTransition H1 = new TranslateTransition();
            H1.setNode(contenido.get(indice));
            H1.setDuration(Duration.millis(duracion_animacion*100));

            int posicion_actual = (espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*i));
            if(!cocktail){
                //verdad ->falso
                //codigo hacia la derecha NORMAL
                H1.setByX(espacio_reservado-posicion_actual);
            }else{
                //falso
                //codigo hacia la izquierda para cocktail
                H1.setByX(sangria-posicion_actual);
            }
            
            ANIMACIONES.getChildren().add(H1);
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setNode(contenido.get(indice));
            V2.setDuration(Duration.millis(duracion_animacion*100));

            V2.setByY(100);
            ANIMACIONES.getChildren().add(V2);
    }
    
    public void BURBUJA_animacionVH2(int i, int j, boolean cocktail){
        int indiceI = (int) indices.get(i);
        int indiceJ = (int) indicesSub.get(j);
        
        
        TranslateTransition V1 = new TranslateTransition();
            V1.setDuration(Duration.millis(duracion_animacion*100)); 
            V1.setNode(contenido.get(indiceI)); 

            V1.setByY(-100);
            ANIMACIONES.getChildren().add(V1);
        
        
        TranslateTransition H1 = new TranslateTransition();
            H1.setDuration(Duration.millis(duracion_animacion*100)); 
            H1.setNode(contenido.get(indiceI)); 
            
            double desplazamientoH=0;
            if(!cocktail){
                //verdad
                //derecha a izquierda
                desplazamientoH = (contenido.get(j).getX()-espacio_reservado);
                //double desplazamientoH = (espacio_reservado-contenido.get(j).getX());
            //H1.setByX(-desplazamientoH);
            }else{
                //falso
                //izquierda derecha cocktail
                desplazamientoH =(contenido.get(j).getX()-sangria);
            }
            H1.setByX(desplazamientoH);
            ANIMACIONES.getChildren().add(H1);    
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setDuration(Duration.millis(duracion_animacion*100)); 
            V2.setNode(contenido.get(indiceI)); 

            V2.setByY(100);
            ANIMACIONES.getChildren().add(V2);
        
        
        indicesSub.set(j, indiceI);
        reordenar();
    }
    
    
    @FXML
    public void cocktailSort(){
        if(!lista_ordenada){
            boolean intercambio = true;
            int start = 0;
            int end = contenido.size() - 1;
            while (intercambio) {
                intercambio = false;

                for (int j = start; j < end; j++) {
                    if((contenidoC.get(j).getHeight())>(contenidoC.get(j+1).getHeight())){
                        BURBUJA_animacionVH((j+1), false);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j);
                        contenidoC.set((j), contenidoC.get(j+1));
                        BURBUJA_animacionVH2(j+1,(j), false);
                        contenidoC.set((j+1), actual);
                        intercambio = true;
                        
                    }
                }

                if (!intercambio) {
                    break;
                }

                intercambio = false;
                end--;
                
                
                for (int j = end - 1; j >= start; j--) {
                    if((contenidoC.get(j).getHeight())>(contenidoC.get(j+1).getHeight())){
                        BURBUJA_animacionVH((j+1), true);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j);
                        contenidoC.set((j), contenidoC.get(j+1));
                        BURBUJA_animacionVH2(j+1,(j), true);
                        contenidoC.set((j+1), actual);
                        intercambio = true;
                        
                    }
                }

                start++;
            
            
            }
            ANIMACIONES.play();
            lista_ordenada=true;
        }else{
          ventanaORDEN();  
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {/*
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e->{
            resetRectangles();
            transition.play();
            transition_1.play();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
        
        barra_duracion.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                duracion_animacion = (int) barra_duracion.getValue();
            }
        });
    }

    
}