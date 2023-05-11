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
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;



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
    boolean temp2 = false;
    
    int posX = 50;
    int posY = 550;
    int ancho=0;
    
    int espacio_reservado = 0;
    
    
    @FXML
    private TextField text_usuario;
    @FXML
    private Button Alma;
    @FXML
    private Button burbuja;
    
    

    @FXML
    void start(ActionEvent event) {
        try{
            //int cantidad = (int) (this.text_usuario.getText());
            double cantidad = Double.parseDouble(this.text_usuario.getText());
            if((cantidad>=16) && (cantidad<=64) && ((cantidad%1)==0)){
                if(this.temp == false){
                      crearRect((int) (cantidad-1));
                      this.temp=true;                                            
                      this.temp2=false;
                  }else{
                      this.anchorPane.getChildren().removeAll(this.contenido);
                      this.contenido.clear();    
                      this.contenidoC.clear();
                      this.indices.clear();
                      this.indicesSub.clear();
                      crearRect((int )(cantidad-1));
                  }  
            }else{
                ventanaERROR();
            }
        }catch(NumberFormatException e){
            ventanaERROR();
        }
        
    }
    
    public void ventanaERROR(){
        System.out.println("Valor no valido");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText("La cantidad ingresada debe ser un numero entero mayor a cero entre 16 a 64");
        alert.showAndWait();
    }
    
    //Aqui se crea los rectangulos, se queria usar rotacion para dejar en buena posicion los rectangulos, al final no lo use
    public void crearRect(int cantidad){
        ancho = ((700-((cantidad+1)*10))/(cantidad+1));
        for (int i = 0; i <= cantidad; i++) {
            int random = rand.nextInt(100);
            Rectangle caja = new Rectangle((20+((10+ancho)*i)), (posY-random), ancho, random);
            caja.setFill(Color.web("#2191FB"));
            this.contenido.add(caja);
            this.contenidoC.add(caja);
            anchorPane.getChildren().addAll(this.contenido.get(i));
            indices.add(i);
            indicesSub.add(i);
        }
        espacio_reservado = 800-ancho;
    }
    

    
    
    @FXML
    void insertionSort3() {
        for (int i = 1; i < contenidoC.size(); i++) {
              Rectangle cajaActual = contenidoC.get(i);
              int valorActual=(int) contenidoC.get(i).getHeight();
              int j = i - 1;
              animacionV1(i);
              while(j >= 0 && contenidoC.get(j).getHeight()> valorActual){
                  //System.out.println("I-> "+i+" J-> "+j+" J+1-> "+(j+1)+" J-1->"+(j-1));
                  animacionH1(j);
                  contenidoC.set((j+1), contenidoC.get(j));
                  j--;
              }
              //animacionV2(i,(j+1));
              contenidoC.set((j+1), cajaActual);
              /*->*///contenidoC.get(j+1).setHeight(valorActual);
              //System.out.println(i+"--"+(j+1));
              animacionV2(i,(j+1));
              //System.out.println("I-->"+i+"j+1-->"+(j+1));
          }
        ANIMACIONES.play(); 
        
        
         
    }
    
    
    
    void animacionV1(int i){
        TranslateTransition V1 = new TranslateTransition();
        //contenido.get(i).setFill(Color.web("#FA4439"));
        int indice = (int) indices.get(i);
        //System.out.println(indices);
        //System.out.println("V1 "+indice);
        V1.setNode(contenido.get(indice));
        V1.setDuration(Duration.millis(100));
        
        V1.setByY(-100);
        //V1.setByY(100);
        ANIMACIONES.getChildren().add(V1);
    }
    
    
            
            
    
    void animacionV2(int i, int j){
        TranslateTransition transicion = new TranslateTransition();
        transicion.setDuration(Duration.millis(100)); 
        
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
        transicion2.setDuration(Duration.millis(100));
        
        transicion2.setByY(100);
        //transicion2.setByY(-100);
        //contenido.get(i).setFill(Color.web("#2191FB"));
        indicesSub.set(j, indiceI);
        
        
        
        reordenar();
        
        ANIMACIONES.getChildren().add(transicion);
        ANIMACIONES.getChildren().add(transicion2);
        
        
    }
    
    void animacionH1(int j){
        //System.out.println("H1 indices "+indices);
        
        TranslateTransition H1 = new TranslateTransition();
        int indiceJ = (int) indicesSub.get(j);
        //System.out.println("H1 indicesSub "+indicesSub);
        H1.setNode(contenido.get(indiceJ));
        H1.setDuration(Duration.millis(100));
        
        contenido.get(j).setFill(Color.web("#E6FA07"));
        //double desplazamientoH= ((contenidoC.get(j+1).getX())-(contenidoC.get(j).getX()));
        double desplazamientoH = (ancho+10);
        //System.out.println("DesplazamientoH "+desplazamientoH);
        H1.setByX(desplazamientoH);
        contenido.get(j).setFill(Color.web("#2191FB"));
        
        //System.out.println("H1 indices "+indices);
        indicesSub.set((j+1),indiceJ);
        //System.out.println("H1 indicesSub "+indicesSub);
        //System.out.println("H1 indices "+indices);
        
        ANIMACIONES.getChildren().add(H1);
    }
    
    
    
    void reordenar(){
        for (int i = 0; i < indicesSub.size(); i++) {
            indices.set(i, indicesSub.get(i));
        }
    }
       
    @FXML
    public void burbujaOP(){
            for (int i = 1; i < contenidoC.size(); i++) {
                boolean intercambio = false;
                for (int j = 0; (j < ((contenidoC.size()-i))); j++) {
                    if((contenidoC.get(j).getHeight())>(contenidoC.get(j+1).getHeight())){
                        //animacionV1(j+1);
                        BURBUJA_animacionVH(j+1);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j);
                        contenidoC.set((j), contenidoC.get(j+1));
                        
                        //animacionV2(j+1,(j));
                        BURBUJA_animacionVH2(j+1,(j));
                        contenidoC.set((j+1), actual);
                        intercambio = true;
                        
                    }
                }
                if(!intercambio){
                    break;
                }
            }
        ANIMACIONES.play();
    }
    
    
    public void BURBUJA_animacionVH(int i){
        TranslateTransition V1 = new TranslateTransition();
            int indice = (int) indices.get(i);
            V1.setNode(contenido.get(indice));
            V1.setDuration(Duration.millis(100));

            V1.setByY(-100);
            ANIMACIONES.getChildren().add(V1);
        
        
        TranslateTransition H1 = new TranslateTransition();
            H1.setNode(contenido.get(indice));
            H1.setDuration(Duration.millis(100));

            int posicion_actual = (20+((10+ancho)*i));
            H1.setByX(espacio_reservado-posicion_actual);
            ANIMACIONES.getChildren().add(H1);
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setNode(contenido.get(indice));
            V2.setDuration(Duration.millis(100));

            V2.setByY(100);
            ANIMACIONES.getChildren().add(V2);
    }
    
    
    public void BURBUJA_animacionVH2(int i, int j){
        int indiceI = (int) indices.get(i);
        int indiceJ = (int) indicesSub.get(j);
        
        
        TranslateTransition V1 = new TranslateTransition();
            V1.setDuration(Duration.millis(100)); 
            V1.setNode(contenido.get(indiceI)); 

            V1.setByY(-100);
            ANIMACIONES.getChildren().add(V1);
        
        
        TranslateTransition H1 = new TranslateTransition();
            H1.setDuration(Duration.millis(100)); 
            H1.setNode(contenido.get(indiceI)); 

            double desplazamientoH = (espacio_reservado-contenido.get(j).getX());
            H1.setByX(-desplazamientoH);
            ANIMACIONES.getChildren().add(H1);    
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setDuration(Duration.millis(100)); 
            V2.setNode(contenido.get(indiceI)); 

            V2.setByY(100);
            ANIMACIONES.getChildren().add(V2);
        
        
        indicesSub.set(j, indiceI);
        reordenar();
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
    }

    
}