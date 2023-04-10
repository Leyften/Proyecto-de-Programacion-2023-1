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



public class Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    TranslateTransition transicion = new TranslateTransition();
    TranslateTransition transicion2 = new TranslateTransition();
    TranslateTransition transicion3 = new TranslateTransition();

    //TranslateTransition transition_1;

    Random rand = new Random();
    
    ArrayList<Rectangle> contenido = new ArrayList<Rectangle>();
    ArrayList cont_numeros = new ArrayList();
    
    boolean temp = false;
    
    int posX = 50;
    
    int posY = 250;
    
    @FXML
    private TextField text_usuario;
    @FXML
    private Button Alma;
    
    

    @FXML
    void start(ActionEvent event) {
        try{
            //int cantidad = (int) (this.text_usuario.getText());
            double cantidad = Double.parseDouble(this.text_usuario.getText());
            if(cantidad>0 && (cantidad%1)==0){
          if(this.temp == false){
                crearRect((int) (cantidad-1));
                this.temp=true;
            }else{
                this.anchorPane.getChildren().removeAll(this.contenido);
                this.contenido.clear();               
                crearRect((int )(cantidad-1));
            }  
        }else{
            System.out.println("Valor no valido");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La cantidad deseada debe ser un numero entero mayor a cero");
            alert.showAndWait();
        }
        }catch(NumberFormatException e){
            System.out.println("Valor no valido");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La cantidad deseada debe ser un numero entero mayor a cero");
            alert.showAndWait();
        }
        /*
        double cantidad = Double.parseDouble(this.text_usuario.getText());
        //int cantidad = Integer.parseInt(this.text_usuario.getText());
        if(cantidad>0 && (cantidad%1)==0){
          if(this.temp == false){
                crearRect((int) (cantidad-1));
                this.temp=true;
            }else{
                this.anchorPane.getChildren().removeAll(this.contenido);
                this.contenido.clear();               
                crearRect((int )(cantidad-1));
            }  
        }else{
            System.out.println("Valor no valido");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La cantidad deseada debe ser un numero entero mayor a cero");
            alert.showAndWait();
        }*/
    }
    
    
    public void crearRect(int cantidad){
        RotateTransition rotacion = new RotateTransition();
        for (int i = 0; i <= cantidad; i++) {
            Rectangle caja = new Rectangle(posX+(i*60), posY, 50, rand.nextInt(50) );
            rotacion.setNode(caja);
            rotacion.setDuration(Duration.millis(1));
            rotacion.setByAngle(180);
            rotacion.play();
            this.contenido.add(caja);
            anchorPane.getChildren().addAll(this.contenido.get(i));
            this.contenido.get(i).setFill(Color.web("#2191FB"));
        }
    }
    
    private void insertionSort(){        
        for(int i = 0; i < this.contenido.size(); i++){
            double valorActual;
            int j = i - 1;
            valorActual=this.contenido.get(i).getHeight();
            //valorActual = Integer.parseInt(Double.toString(this.contenido.get(i).getHeight()));  
            while(j >= 0 && this.contenido.get(j).getHeight()> valorActual){
                desplazamiento(i,j);
                
                
                 
               
                //this.contenido.get(j+1).setHeight(this.contenido.get(j).getHeight());
                j--;
            }
            //this.contenido.get(j+1).setHeight(valorActual);
        }    
    }
    @FXML
    private void insertionSort2(){
        for(int i = 0; i < this.contenido.size(); i++){
            //System.out.println("i->"+i);
            int valorActual;
            int j = i - 1;
            valorActual=(int) this.contenido.get(i).getHeight();
            //valorActual = Integer.parseInt(Double.toString(this.contenido.get(i).getHeight()));  
            while(j >= 0 && this.contenido.get(j).getHeight()> valorActual){
                //System.out.println("while");
                //this.numeros.get(j);
                int k =i;
                
                while(k>=0){
                    //System.out.println("vuelta");
                    desplazamiento(k,j);
                    k--;
                }
                
                
                
                
                 
               
                this.contenido.get(j+1).setHeight(this.contenido.get(j).getHeight());
                j--;
            }
            this.contenido.get(j+1).setHeight(valorActual);
        } 
        
        
        
    }
    
    
    public void crearM(){
        Rectangle caja = new Rectangle(50, 250, 50, (50) );
        caja.setFill(Color.web("#2191FB"));
        this.contenido.add(caja);
        Rectangle caja_1 = new Rectangle(110, 250, 50, 100 );
        caja_1.setFill(Color.web("#2191FB"));
        this.contenido.add(caja_1);
        
        anchorPane.getChildren().addAll(this.contenido);
        
    }
    
    public void desplazamiento(/*1*/int i,/*0*/ int j){
        contenido.get(j).setFill(Color.web("#E6FA07"));
        contenido.get(i).setFill(Color.web("#FA4439"));
        transicion.setNode(contenido.get(i));
        transicion.setDuration(Duration.millis(1000));
        double temp = contenido.get(i).getX();
        transicion.setByY(100);
        //contenido.get(i).setY((contenido.get(i).getY())+100);
        transicion.setByX(-60);
        transicion.play();
        
        transicion2.setNode(contenido.get(j));
        transicion2.setDelay(Duration.millis(1000));
        transicion2.setDuration(Duration.millis(1000));
        transicion2.setByX(temp-contenido.get(j).getX());
        transicion2.play(); 
        
        transicion3.setNode(contenido.get(i));
        transicion3.setDelay(Duration.millis(2000));
        transicion3.setDuration(Duration.millis(1000));
        transicion3.setByY(-1);
        //contenido.get(i).setY(contenido.get(i).getY()-100);
        transicion3.play();
        
        contenido.get(i).setFill(Color.web("#2191FB"));
        contenido.get(j).setFill(Color.web("#2191FB"));
        
               
    }
    
    
    //MOVIMIENTO QUE SE HACE MANUAL
    public void movimientoM(){
        contenido.get(0).setFill(Color.web("#E6FA07"));
        contenido.get(1).setFill(Color.web("#FA4439"));
        transicion.setNode(contenido.get(1));
        transicion.setDuration(Duration.millis(1000));
        double temp = contenido.get(1).getX();
        transicion.setByY(100);
        transicion.setByX(-10-posX);
        transicion.play();
        
        transicion2.setNode(contenido.get(0));
        transicion2.setDelay(Duration.millis(1000));
        transicion2.setDuration(Duration.millis(1000));
        transicion2.setByX(temp-contenido.get(0).getX());
        transicion2.play(); 

        transicion3.setNode(contenido.get(1));
        transicion3.setDelay(Duration.millis(2000));
        transicion3.setDuration(Duration.millis(1000));
        transicion3.setByY(-1);
        transicion3.play();
        
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