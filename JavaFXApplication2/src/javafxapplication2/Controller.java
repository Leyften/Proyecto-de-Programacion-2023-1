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



public class Controller implements Initializable{
    
    double cantidad=0;
    int altura_Maxima=50;
    //int ancho;
    //int espacio;
    int duracion = 500;
    //int ARRAY_SIZE = 10; //cantidad
    
    Rectangle[] Arreglo_rectangulos = new Rectangle[100];
    
    int minimo=0;
    int indice=0;
    
    
    
    @FXML
    private AnchorPane anchorPane;

    

    Random rand = new Random();
    
    ArrayList<Rectangle> contenido = new ArrayList<Rectangle>();
    
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
            cantidad = Double.parseDouble(this.text_usuario.getText());
            if(cantidad>0 && (cantidad%1)==0){
                if(temp == false){
                      crearRect((int) (cantidad-1));
                      temp=true;
                  }else{
                      anchorPane.getChildren().removeAll(this.contenido);
                      contenido.clear();               
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
        
    }
    
    //Aqui se crea los rectangulos, se queria usar rotacion para dejar en buena posicion los rectangulos, al final no lo use
    public void crearRect(int cantidad){       
        for (int i = 0; i <= cantidad; i++) {
            int altura = rand.nextInt(altura_Maxima);
            Rectangle caja = new Rectangle(50,altura);
            caja.setFill(Color.BLUEVIOLET);
            caja.setTranslateX(i*(50+10));
            caja.setTranslateY(altura_Maxima - altura);
            
            contenido.add(caja);
            anchorPane.getChildren().addAll(contenido.get(i));
            //Arreglo_rectangulos[i]=caja;
            
        }
    }
    @FXML
    private void insertionSort(){    
        if (minimo >= cantidad) {
            System.out.println("return");
            return;
        }
        
        Rectangle cajaMinimo = contenido.get(minimo);
        Rectangle cajaIndice = contenido.get(indice);
        
        /*
        Rectangle cajaMinimo = Arreglo_rectangulos[minimo];
        Rectangle cajaIndice = Arreglo_rectangulos[indice];*/
        
        if (cajaIndice.getHeight() < cajaMinimo.getHeight()) {
            minimo = indice;
            
            indice++;
        }
        
        if (indice >= cantidad) {
            mostrar(minimo, minimo, cajaMinimo, duracion);
            minimo++;
            indice = minimo;
        } else {
            mostrar(minimo, indice, cajaIndice, duracion);
        }
        
        cajaMinimo.setFill(Color.BLUEVIOLET);

        cajaIndice.setFill(Color.YELLOW);

        cajaMinimo.toFront();

        cajaIndice.toFront();

        cajaMinimo.setFill(Color.BLUEVIOLET);

        cajaIndice.setFill(Color.BLUEVIOLET);

        cajaMinimo.toBack();

        cajaIndice.toBack();

        cajaMinimo.setFill(Color.RED);

        insertionSort();
        
    }
       
    private void mostrar(int indice1, int indice2, Rectangle caja1, int duracion) {
        Rectangle caja2 = contenido.get(indice2);
        //Rectangle caja2 = Arreglo_rectangulos[indice2];

        TranslateTransition transition1 = new TranslateTransition(Duration.millis(duracion), caja1);
        transition1.setByX((indice2 - indice1) * (50 + 10));
        TranslateTransition transition2 = new TranslateTransition(Duration.millis(duracion), caja2);
        transition2.setByX((indice1 - indice2) * (50 + 10));

        transition1.play();
        transition2.play();
        
        contenido.set(indice1, caja2);
        contenido.set(indice2, caja1);

        /*
        Arreglo_rectangulos[indice1] = caja2;
        Arreglo_rectangulos[indice2] = caja1;*/
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