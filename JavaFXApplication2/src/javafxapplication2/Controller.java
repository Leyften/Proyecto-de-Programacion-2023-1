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



public class Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    TranslateTransition transicion = new TranslateTransition() ;

    //TranslateTransition transition_1;

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
        double cantidad = Double.parseDouble(this.text_usuario.getText());
        //int cantidad = Integer.parseInt(this.text_usuario.getText());
        if(cantidad>0 && (cantidad%1)==0){
          if(this.temp == false){
            crearRect((int) (cantidad-1));
            this.temp=true;
            }else{
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
        
        
        
        
        //crearM();
        //crearRect(3);
        //resetRectangles();
        /*
        transition.play();
        transition_1.play();*/
    }
    /*
    public void resetRectangles(){
        int recHeightX = rand.nextInt(250);
        int recHeightX_1 = rand.nextInt(250);

        int recHeight = 25 + rand.nextInt(50);
        int recWidth = 25 + rand.nextInt(50);

        int recHeight_1 = 25 + rand.nextInt(50);
        int recWidth_1 = 25 + rand.nextInt(50);

        Rectangle rectangle = new Rectangle(-100,recHeightX,recHeight,recWidth);

        Rectangle rectangle_1 = new Rectangle(600,recHeightX_1,recHeight_1,recWidth_1);

        transition = new TranslateTransition();

        transition_1 = new TranslateTransition();

        anchorPane.getChildren().addAll(rectangle,rectangle_1);
        rectangle.setFill(Color.web("#2191FB"));
        rectangle_1.setFill(Color.web("#BA274A"));

        //Rectangle transition
        transition.setNode(rectangle);
        transition.setDuration(Duration.seconds(5));
        transition.setToX(700);


        //Rectangle_1 transition
        transition_1.setNode(rectangle_1);
        transition_1.setDuration(Duration.seconds(5));
        transition_1.setToX(-700);
    }
    */
    public void crearRect(int cantidad){
        RotateTransition rotacion = new RotateTransition();
        for (int i = 0; i <= cantidad; i++) {
            Rectangle caja = new Rectangle(posX+(i*60), posY, 50, (rand.nextInt(50)) );
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
        
        
        for(int i = 0; i <= this.contenido.size(); i++){
            int valorActual;
            int j = i - 1;
            valorActual = Integer.parseInt(Double.toString(this.contenido.get(i).getHeight()));  
            while(j >= 0 && this.contenido.get(j).getHeight()> valorActual){
                //animacion de movimiento
                this.contenido.get(j-1).setFill(Color.web("#E6FA07"));
                this.contenido.get(j).setFill(Color.web("#FA4439"));
                transicion.setNode(this.contenido.get(j));
                transicion.setDuration(Duration.millis(1000));
                transicion.setByY(posY+100);
                //
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
    
    @FXML
    public void movimientoM(){
        contenido.get(0).setFill(Color.web("#E6FA07"));
                contenido.get(1).setFill(Color.web("#FA4439"));
                System.out.println(contenido.get(1).getHeight());
                
                transicion.setNode(contenido.get(1));
                //System.out.println(transicion.getNode().toString());
                transicion.setDuration(Duration.millis(1000));
                transicion.setByY(posY+30);
                transicion.setByX(-10-posX);
                transicion.play();
                
                transicion.setNode(contenido.get(1));
                transicion.setDuration(Duration.millis(1000));
                transicion.setByY((posY));
                transicion.play();
                
                transicion.setByY(posY+10);
                transicion.setByX(posX);
                
                
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