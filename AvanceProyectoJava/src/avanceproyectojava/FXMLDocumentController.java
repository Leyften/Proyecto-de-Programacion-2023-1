/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package avanceproyectojava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//ingresados despues
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 *
 * @author nikol
 */
public class FXMLDocumentController implements Initializable {
    
    int cantidad;
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField cantidadDeCajas;
    @FXML
    private Label textoDefault;
    //mio
    @FXML
    private AnchorPane anchorPane;
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        this.cantidad = Integer.parseInt(this.cantidadDeCajas.getText());
        System.out.println(this.cantidad);
        crearCajas();
        
        
        
        
    }
    
    public void crearCajas(){
        
        Rectangle caja = new Rectangle(30,550,100,100);
        Rectangle caja_2 = new Rectangle(100,350,100,100);
        //System.out.println("Antes");
        this.anchorPane.getChildren().addAll(caja);
        //anchorPane.getChildren().addAll(caja, caja_2);
        //System.out.println("despues");
        caja.setFill(Color.web("#2191FB"));
        caja_2.setFill(Color.web("#2191FB"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //crearCajas(0);
    }    
    
}
