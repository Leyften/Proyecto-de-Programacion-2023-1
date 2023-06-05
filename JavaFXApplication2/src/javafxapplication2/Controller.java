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
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Line;



public class Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    Random rand = new Random();
    
    ArrayList<Rectangle> contenido = new ArrayList<Rectangle>();
    ArrayList<Rectangle> contenidoC = new ArrayList<Rectangle>();
    ArrayList               indices = new ArrayList();
    ArrayList               indicesSub = new ArrayList();
    
    SequentialTransition ANIMACIONES = new SequentialTransition ();
    
    Grua grua1; 
    Grua grua2;
    
    
    boolean temp = false;
    boolean lista_ordenada = false;
    boolean en_reversa = false;
    boolean cantidadGruas2 = false;
    boolean cantidadGruas1 = false;
    
    
    int posX = 50;
    int posY = 625;
    int ancho=0;
    int sangria=30;
    int entre_espacio=10;
    int duracion_animacion=3;
            
    
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
    private Label seudocodigo;
    
    

    @FXML
    void start(ActionEvent event) {
        try{
            //int cantidad = (int) (this.text_usuario.getText());
            double cantidad = Double.parseDouble(this.text_usuario.getText());
            if((cantidad>=16) && (cantidad<=64) && ((cantidad%1)==0)){
                if(this.temp == false){
                      crearRect((int) (cantidad-1));
                      this.temp=true;                          
                  }else{
                    if(this.cantidadGruas2){
                        borrarGrua(grua1);
                        borrarGrua(grua2);
                    }else if(this.cantidadGruas1){
                        borrarGrua(grua1);
                    }
                      this.cantidadGruas2=false;
                      this.cantidadGruas1=false;
                      this.seudocodigo.setText("");
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
        ANIMACIONES.setRate(ANIMACIONES.getRate()*-1);
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
    
    Grua crearGrua(int i){
        Rectangle base_grua = new Rectangle((espacio_reservadoI+((entre_espacio+ancho)*i)), (166), ancho, (20));
        Rectangle iman_grua = new Rectangle((espacio_reservadoI+((entre_espacio+ancho)*i)), (200), ancho, (20));
        Line cuerda_grua = new Line(((espacio_reservadoI+((entre_espacio+ancho)*i))+(ancho/2)),175,(espacio_reservadoI+(ancho/2)+((entre_espacio+ancho)*i)),200);
        double Xbase_grua = base_grua.getX();        
        double Yiman_grua = 200 + 20;
        
        Grua grua = new Grua(base_grua, iman_grua, cuerda_grua, Xbase_grua, Yiman_grua);
        return grua;
    }
    
    void añadirGrua(Grua grua){
        anchorPane.getChildren().addAll(grua.getBase_grua());
        anchorPane.getChildren().addAll(grua.getIman_grua());
        anchorPane.getChildren().addAll(grua.getCuerda_grua());
    }
    
    void borrarGrua(Grua grua){
        anchorPane.getChildren().removeAll(grua.getBase_grua());
        anchorPane.getChildren().removeAll(grua.getIman_grua());
        anchorPane.getChildren().removeAll(grua.getCuerda_grua());
    }
    
    String insertion(){        
        seudocodigo.setAlignment(Pos.TOP_LEFT);
        String texto="insertion(ArrayList n){\n"
                        +"for (int i = 1; i < n.size(); i++){\n"
                            +"  int key = arr[i];\n"
                            +"  int j = i - 1\n"
                            +"  while (j >= 0 && arr[j] > key) {\n"
                            +"    arr[j + 1] = arr[j]\n"
                            +"    j = j - 1;\n"
                            +"  }\n"
                            +"arr[j + 1] = key;\n"
                        +"}\n";
           
        return texto;
    }
    
    String burbuja(){
        seudocodigo.setAlignment(Pos.TOP_LEFT);
        String texto = "burbuja(ArrayList n){\n"
                            +"  for (int i = 0; i < n.size(); i++) {\n"
                            +"    boolean intercambio = false;\n"
                            +"    for (int j = 0; j < n.size()-i; j++) {\n"
                            +"      if (n[j] > n[j+1]) {\n"
                            +"        int temp = arr[j];\n"
                            +"        n[j] = n[j+1];\n"
                            +"        n[j+1] = temp;\n"
                            +"        intercambio;\n"
                            +"       }\n"
                            +"     }\n"
                            +"     if(!intercambio){\n"
                            +"       break;\n"
                            +"   }\n";
                                
        return texto;
    }
    
    String cocktail(){
        seudocodigo.setAlignment(Pos.TOP_LEFT);
        String texto = "cocktail(ArrayList n){\n"
                       +"   boolean intercambio = true;\n"
                       +"   int star = 0;\n"
                       +"   int end = n.size()-1;\n"
                       +"   while(intercambio){\n"
                       +"       intercambio = false;\n"
                       +"       for(int j = start; j<end; j++){"
                       +"           if(n[j]>n[j+1]){\n"
                       +"               int temp = n[j];\n"
                       +"               n[j]=n[j+1];\n"
                       +"               n[j+1]=temp;\n"
                       +"               intercambio=true;\n"
                       +"           }\n"
                       +"       }\n"
                       +"       if(!intercambio){\n"
                       +"           break;\n"
                       +"       }\n"
                       +"       intercambio=false;\n"
                       +"       end--;\n"
                       +"       \n"
                       +"       for(int j = end; j>=start; j--){"
                       +"           if(n[j]>n[j+1]){\n"
                       +"               int temp = n[j];\n"
                       +"               n[j]=n[j+1];\n"
                       +"               n[j+1]=temp;\n"
                       +"               intercambio=true;\n"
                       +"           }\n"
                       +"       }\n"
                       +"       start++\n"
                       +"   }\n"
                       +" }\n";
        return texto;
    }
    
    
    @FXML
    void insertionSort3() {
        if(!lista_ordenada){
            seudocodigo.setText(insertion());
            this.cantidadGruas2=true;
            Grua grua1 = crearGrua(0);
            añadirGrua(grua1);
            Grua grua2 = crearGrua(1);
            añadirGrua(grua2);
            for (int i = 1; i < contenidoC.size(); i++) {
                Rectangle cajaActual = contenidoC.get(i);
                int valorActual=(int) contenidoC.get(i).getHeight();
                int j = i - 1;
                animacionV1(i, grua2);
                while(j >= 0 && contenidoC.get(j).getHeight()> valorActual){
                    animacionH1(j, grua1);
                    contenidoC.set((j+1), contenidoC.get(j));
                    j--;
                }
            contenidoC.set((j+1), cajaActual);
            animacionV2(i,(j+1), grua2);
            }
            ANIMACIONES.play();
            lista_ordenada=true;
            this.grua1=grua1;
            this.grua2=grua2;
        }else{
            ventanaORDEN();
        }   
    }
    
    void animacionV1(int i, Grua grua){
        int indice = (int) indices.get(i);
        double rastreo = (espacio_reservadoI+((entre_espacio+ancho)*i)-grua.getXbase_grua());
        double desplazamientoV = (contenido.get(indice).getY())- (grua.getYiman_grua());
        double punto_cajaY = posY-contenido.get(indice).getHeight();
        double iman_sujeto = contenido.get(indice).getY()-200;
        TranslateTransition V1 = new TranslateTransition();
        
            V1.setNode(contenido.get(indice));
            V1.setDuration(Duration.millis(duracion_animacion*100));

            V1.setByY(-200);
        
        grua.setXbase_grua(grua.getXbase_grua()+rastreo);
        
        ParallelTransition  ANIMACIONV = new ParallelTransition ();
            ANIMACIONV.getChildren().addAll(V1 ,grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), -200, iman_sujeto));
                
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), rastreo));
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), desplazamientoV, punto_cajaY));
        ANIMACIONES.getChildren().add(ANIMACIONV);
        
        
    }
    
    void animacionV2(int i, int j, Grua grua){
        int indiceI = (int) indices.get(i);
        int indiceJ = (int) indicesSub.get(j);
        double desplazamientoX = (contenido.get(i).getX()-contenido.get(j).getX());
        double punto_cajaY = posY-contenido.get(indiceJ).getHeight();
        double desplazaminetoV = -((contenido.get(indiceI).getY())- (grua.getYiman_grua()));
        
        TranslateTransition transicion = new TranslateTransition();
            transicion.setDuration(Duration.millis(duracion_animacion*100));
            transicion.setNode(contenido.get(indiceI));            
            transicion.setByX(-desplazamientoX); 
       
        TranslateTransition transicion2 = new TranslateTransition();
            transicion2.setNode(contenido.get(indiceI));
            transicion2.setDuration(Duration.millis(duracion_animacion*100));
            transicion2.setByY(200);
            
        indicesSub.set(j, indiceI);
        reordenar();
        
        
        grua.setXbase_grua(grua.getXbase_grua()-desplazamientoX);
        
        ParallelTransition  ANIMACIONH = new ParallelTransition ();
            ANIMACIONH.getChildren().addAll(transicion, grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), (-desplazamientoX)));
        
        ParallelTransition  DESPLAZAMIENTOV = new ParallelTransition ();
            DESPLAZAMIENTOV.getChildren().addAll(transicion2, grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), 200, punto_cajaY));
               
        ANIMACIONES.getChildren().add(ANIMACIONH);
        ANIMACIONES.getChildren().add(DESPLAZAMIENTOV);
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), desplazaminetoV, 200));
        
        
        
    }
    
    void animacionH1(int j, Grua grua){
        int indiceJ = (int) indicesSub.get(j);
        double desplazamientoH = (ancho+10);
        double rastreo = (espacio_reservadoI+((entre_espacio+ancho)*j)-grua.getXbase_grua());
        double desplazamientoV = (contenido.get(indiceJ).getY())- (grua.getYiman_grua());
        double punto_cajaY = posY-contenido.get(indiceJ).getHeight();
        double iman_originalY = 200;
        
        
        TranslateTransition H1 = new TranslateTransition();       
            H1.setNode(contenido.get(indiceJ));
            H1.setDuration(Duration.millis(duracion_animacion*100));           
            H1.setByX(desplazamientoH);            

            indicesSub.set((j+1),indiceJ);
            grua.setXbase_grua(grua.getXbase_grua()+ rastreo + desplazamientoH);
            
        ParallelTransition  ANIMACIONH = new ParallelTransition ();
            ANIMACIONH.getChildren().addAll(H1 ,grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), desplazamientoH));    
            
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), rastreo));
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), desplazamientoV, punto_cajaY));
        ANIMACIONES.getChildren().add(ANIMACIONH);
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), -desplazamientoV, iman_originalY));
    }
    
    
    
    void reordenar(){
        for (int i = 0; i < indicesSub.size(); i++) {
            indices.set(i, indicesSub.get(i));
        }
    }
       
    
    @FXML
    public void burbujaOP(){
        if(!lista_ordenada){
            seudocodigo.setText(burbuja());
            Grua grua1 = crearGrua(0);
            añadirGrua(grua1);
            this.cantidadGruas1 = true;
            for (int i = 1; i < contenidoC.size(); i++) {
                boolean intercambio = false;
                for (int j = 0; (j < ((contenidoC.size()-i))); j++) {
                    if((contenidoC.get(j).getHeight())>(contenidoC.get(j+1).getHeight())){
                        
                        BURBUJA_animacionVH((j+1), grua1, false);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j, grua1);
                        contenidoC.set((j), contenidoC.get(j+1));
                        
                        BURBUJA_animacionVH2(j+1,(j), grua1,false);
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
            this.grua1=grua1;
        }else{
            ventanaORDEN();
        }
            
    }
    
    public void BURBUJA_animacionVH(int i, Grua grua,boolean cocktail){
        int indice = (int) indices.get(i);
        double rastreoGrua = (espacio_reservadoI+((entre_espacio+ancho)*i)-grua.getXbase_grua());
        double desplazamientoV = ((contenido.get(indice).getY())-(grua.getYiman_grua()));
        double punto_cajaY = posY - contenido.get(indice).getHeight();
        double iman_sujeto = contenido.get(indice).getY()-200;
       
        
        TranslateTransition V1 = new TranslateTransition();            
            V1.setNode(contenido.get(indice));
            V1.setDuration(Duration.millis(duracion_animacion*100));
            V1.setByY(-200);
          
            
        TranslateTransition H1 = new TranslateTransition();
            H1.setNode(contenido.get(indice));
            H1.setDuration(Duration.millis(duracion_animacion*100));

            int posicion_actual = (espacio_reservadoI+((entre_espacio+ancho)*i));
            double desplazamientoH;
            if(!cocktail){
                desplazamientoH = espacio_reservado-posicion_actual;
                
            }else{
                desplazamientoH = sangria-posicion_actual;
            }
            H1.setByX(desplazamientoH);
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setNode(contenido.get(indice));
            V2.setDuration(Duration.millis(duracion_animacion*100));

            V2.setByY(200);
        
       
        
        ParallelTransition  VERTICAL2 = new ParallelTransition ();
            VERTICAL2.getChildren().addAll(V1, grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), -200, iman_sujeto));                
                        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();
            DESPLAZAMIENTO.getChildren().addAll(H1, grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), desplazamientoH));                   

        ParallelTransition  VERTICAL3 = new ParallelTransition ();
            VERTICAL3.getChildren().addAll(V2, grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), 200, punto_cajaY));
            
           
                
            ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), rastreoGrua));
            grua.setXbase_grua(grua.getXbase_grua()+rastreoGrua);
            ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), desplazamientoV, punto_cajaY));
            ANIMACIONES.getChildren().add(VERTICAL2);            
            ANIMACIONES.getChildren().add(DESPLAZAMIENTO); 
            grua.setXbase_grua(grua.getXbase_grua()+desplazamientoH);
            ANIMACIONES.getChildren().add(VERTICAL3);
           ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), -desplazamientoV, 200));
            
            
    }
    
    public void BURBUJA_animacionVH2(int i, int j, Grua grua,boolean cocktail){
        int indiceI = (int) indices.get(i);
        int indiceJ = (int) indicesSub.get(j);
        double rastreoGrua = 0;        
        double desplazamientoV = ((contenido.get(indiceI).getY())-(grua.getYiman_grua()));
        double punto_cajaY = posY - contenido.get(indiceI).getHeight();
        double iman_sujeto = contenido.get(indiceI).getY()-200;
        
        
        TranslateTransition V1 = new TranslateTransition();
            V1.setDuration(Duration.millis(duracion_animacion*100)); 
            V1.setNode(contenido.get(indiceI)); 

            V1.setByY(-200);
            ParallelTransition  VERTICALV2 = new ParallelTransition ();
                VERTICALV2.getChildren().addAll(V1, grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), -200, iman_sujeto));
        
        
        TranslateTransition H1 = new TranslateTransition();
            H1.setDuration(Duration.millis(duracion_animacion*100)); 
            H1.setNode(contenido.get(indiceI)); 
            
            double desplazamientoH=0;
            if(!cocktail){
                rastreoGrua = espacio_reservado-grua.getXbase_grua(); 
                desplazamientoH = (contenido.get(j).getX()-espacio_reservado);
            }else{
                desplazamientoH =(contenido.get(j).getX()-sangria);
                rastreoGrua = sangria-grua.getXbase_grua(); 
                
            }
            H1.setByX(desplazamientoH);
            
             ParallelTransition  DESPLAZAMIENTOH = new ParallelTransition ();
                DESPLAZAMIENTOH.getChildren().addAll(H1,grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), desplazamientoH));
                
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setDuration(Duration.millis(duracion_animacion*100)); 
            V2.setNode(contenido.get(indiceI)); 

            V2.setByY(200);
            
            ParallelTransition  VERTICAL3 = new ParallelTransition ();
                VERTICAL3.getChildren().addAll(V2,grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), 200, punto_cajaY));
            
        
        
        indicesSub.set(j, indiceI);
        reordenar();
        
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOH(Duration.millis(duracion_animacion*100), rastreoGrua));
        grua.setXbase_grua(grua.getXbase_grua()+rastreoGrua);
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), desplazamientoV, punto_cajaY));
        ANIMACIONES.getChildren().add(VERTICALV2);
        ANIMACIONES.getChildren().add(DESPLAZAMIENTOH);
        grua.setXbase_grua(grua.getXbase_grua()+desplazamientoH);
        
        ANIMACIONES.getChildren().add(VERTICAL3);
        ANIMACIONES.getChildren().add(grua.DESPLAZAMIENTOV(Duration.millis(duracion_animacion*100), -desplazamientoV, 200));
        
        
    }   
    
    
    @FXML
    public void cocktailSort(){
        if(!lista_ordenada){
            seudocodigo.setText(cocktail());
            Grua grua1 = crearGrua(0);
            añadirGrua(grua1);
            this.cantidadGruas1 = true;
            boolean intercambio = true;
            int start = 0;
            int end = contenido.size() - 1;
            while (intercambio) {
                intercambio = false;

                for (int j = start; j < end; j++) {
                    if((contenidoC.get(j).getHeight())>(contenidoC.get(j+1).getHeight())){
                        BURBUJA_animacionVH((j+1), grua1, false);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j, grua1);
                        contenidoC.set((j), contenidoC.get(j+1));
                        BURBUJA_animacionVH2(j+1,(j), grua1, false);
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
                        BURBUJA_animacionVH((j+1), grua1, true);
                        Rectangle actual = contenidoC.get(j);
                        
                        animacionH1(j, grua1);
                        contenidoC.set((j), contenidoC.get(j+1));
                        BURBUJA_animacionVH2(j+1,(j), grua1, true);
                        contenidoC.set((j+1), actual);
                        intercambio = true;
                        
                    }
                }

                start++;
            
            
            }
            ANIMACIONES.play();
            lista_ordenada=true;
            this.grua1=grua1;
        }else{
          ventanaORDEN();  
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        barra_duracion.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //duracion_animacion = (int) barra_duracion.getValue();
                ANIMACIONES.setRate((int) barra_duracion.getValue());
            }
        });
    }

    
}