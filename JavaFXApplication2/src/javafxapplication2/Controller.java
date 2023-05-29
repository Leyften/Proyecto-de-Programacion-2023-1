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
    
    Rectangle base_grua1;
    Rectangle iman_grua1;
    Line cuerda_grua1;
    double Xbase_grua1;
    double Yiman_grua1;
    
    Rectangle base_grua2;
    Rectangle iman_grua2;
    Line cuerda_grua2;
    double Xbase_grua2;
    double Yiman_grua2;
    double vertical;
    double horizontal;
    
    
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
                        this.anchorPane.getChildren().removeAll(this.base_grua1);
                        this.anchorPane.getChildren().removeAll(this.cuerda_grua1);
                        this.anchorPane.getChildren().removeAll(this.iman_grua1);
                        this.anchorPane.getChildren().removeAll(this.base_grua2);
                        this.anchorPane.getChildren().removeAll(this.cuerda_grua2);
                        this.anchorPane.getChildren().removeAll(this.iman_grua2);
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
            base_grua1 = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*0)), (166), ancho, (20));
            Xbase_grua1 = base_grua1.getX();
            anchorPane.getChildren().add(base_grua1);
            iman_grua1 = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*0)), (200), ancho, (20));
            Yiman_grua1 = 200 + 20;
            anchorPane.getChildren().add(iman_grua1);
            cuerda_grua1 = new Line((espacio_reservadoI+(ancho/2)),175,(espacio_reservadoI+(ancho/2)),200);
            anchorPane.getChildren().add(cuerda_grua1);
            
            base_grua2 = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1)), (166), ancho, (20));
            Xbase_grua2 = base_grua2.getX();
            anchorPane.getChildren().add(base_grua2);
            iman_grua2 = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1)), (200), ancho, (20));
            Yiman_grua2 = 200 + 20;
            anchorPane.getChildren().add(iman_grua2);
            cuerda_grua2 = new Line(((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1))+(ancho/2)),175,((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1))+(ancho/2)),200);
            anchorPane.getChildren().add(cuerda_grua2);
            
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
        int indice = (int) indices.get(i);
        V1.setNode(contenido.get(indice));
        V1.setDuration(Duration.millis(duracion_animacion*100));
        
        vertical = contenido.get(indice).getY()-100;
        V1.setByY(-100);
        
        
        TranslateTransition RASTREObase_grua2H = new TranslateTransition(); 
            RASTREObase_grua2H.setNode(base_grua2);
            RASTREObase_grua2H.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoH2 = (espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*i)-Xbase_grua2);
            RASTREObase_grua2H.setByX(desplazamientoH2);
            horizontal= desplazamientoH2;
            
        TranslateTransition RASTREOiman_grua2H = new TranslateTransition(); 
            RASTREOiman_grua2H.setNode(iman_grua2);
            RASTREOiman_grua2H.setDuration(Duration.millis(duracion_animacion*100));
            
            RASTREOiman_grua2H.setByX(desplazamientoH2);
        
        TranslateTransition RASTREOcuerda_grua2H = new TranslateTransition(); 
            RASTREOcuerda_grua2H.setNode(cuerda_grua2);
            RASTREOcuerda_grua2H.setDuration(Duration.millis(duracion_animacion*100));
            
            RASTREOcuerda_grua2H.setByX(desplazamientoH2);
        
        TranslateTransition DESPLAZAMIENTOV_iman_grua2 = new TranslateTransition();
            DESPLAZAMIENTOV_iman_grua2.setNode(iman_grua2);
            DESPLAZAMIENTOV_iman_grua2.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoV = (contenido.get(indice).getY())- (Yiman_grua2);            
            DESPLAZAMIENTOV_iman_grua2.setByY(desplazamientoV);
                Timeline DESPLAZAMIENTOV_cuerda_grua2 = stretchLine(cuerda_grua2, ((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1))+(ancho/2)), posY-contenido.get(indice).getHeight());
                //---------------------------------
            
        TranslateTransition DESPLAZAMIENTOV2_iman_grua2 = new TranslateTransition();
            DESPLAZAMIENTOV2_iman_grua2.setNode(iman_grua2);
            DESPLAZAMIENTOV2_iman_grua2.setDuration(Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTOV2_iman_grua2.setByY(-100);
            //DESPLAZAMIENTOV2_iman_grua2.setByY(-desplazamientoV);
            
            Timeline DESPLAZAMIENTOV2_cuerda_grua2 = stretchLine(cuerda_grua2, (espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1)+(ancho/2)), vertical);
        
        ParallelTransition  RASTREO = new ParallelTransition ();
            RASTREO.getChildren().addAll(RASTREObase_grua2H, RASTREOiman_grua2H, RASTREOcuerda_grua2H);
        
        ParallelTransition  ANIMACIONV1 = new ParallelTransition ();
            ANIMACIONV1.getChildren().addAll(DESPLAZAMIENTOV_iman_grua2, DESPLAZAMIENTOV_cuerda_grua2);
        
        ParallelTransition  ANIMACIONV2 = new ParallelTransition ();
            ANIMACIONV2.getChildren().addAll(V1 ,DESPLAZAMIENTOV2_iman_grua2, DESPLAZAMIENTOV2_cuerda_grua2);
                
        ANIMACIONES.getChildren().add(RASTREO);
        ANIMACIONES.getChildren().add(ANIMACIONV1);
        ANIMACIONES.getChildren().add(ANIMACIONV2);
    }
    
    void animacionV2(int i, int j){
        TranslateTransition transicion = new TranslateTransition();
            transicion.setDuration(Duration.millis(duracion_animacion*100)); 

            int indiceI = (int) indices.get(i);
            int indiceJ = (int) indicesSub.get(j);

            transicion.setNode(contenido.get(indiceI)); 
            double desplazamientoV = (contenido.get(i).getX()-contenido.get(j).getX());

            transicion.setByX(-desplazamientoV);
            
            
        TranslateTransition DESPLAZAMIENTOH_base_grua2 = new TranslateTransition();
            DESPLAZAMIENTOH_base_grua2.setNode(base_grua2);
            DESPLAZAMIENTOH_base_grua2.setDuration(Duration.millis(duracion_animacion*100));            
            DESPLAZAMIENTOH_base_grua2.setByX(-desplazamientoV);
            
        TranslateTransition DESPLAZAMIENTOH_iman_grua2 = new TranslateTransition();
            DESPLAZAMIENTOH_iman_grua2.setNode(iman_grua2);
            DESPLAZAMIENTOH_iman_grua2.setDuration(Duration.millis(duracion_animacion*100));            
            DESPLAZAMIENTOH_iman_grua2.setByX(-desplazamientoV);
            
        TranslateTransition DESPLAZAMIENTOH_cuerda_grua2 = new TranslateTransition();
            DESPLAZAMIENTOH_cuerda_grua2.setNode(cuerda_grua2);
            DESPLAZAMIENTOH_cuerda_grua2.setDuration(Duration.millis(duracion_animacion*100));            
            DESPLAZAMIENTOH_cuerda_grua2.setByX(-desplazamientoV);
        
        
        TranslateTransition transicion2 = new TranslateTransition();
            transicion2.setNode(contenido.get(indiceI));
            transicion2.setDuration(Duration.millis(duracion_animacion*100));

            transicion2.setByY(100);
            indicesSub.set(j, indiceI);
        
        TranslateTransition DESPLAZAMIENTOV_iman_grua2 = new TranslateTransition();
            DESPLAZAMIENTOV_iman_grua2.setNode(iman_grua2);
            DESPLAZAMIENTOV_iman_grua2.setDuration(Duration.millis(duracion_animacion*100));
            //double DESPLAZAMIENTOV_iman_grua2 = (contenido.get(indiceJ).getY())- (Yiman_grua1);
            DESPLAZAMIENTOV_iman_grua2.setByY(100);
                Timeline DESPLAZAMIENTOV_cuerda_grua2 = stretchLine(cuerda_grua2, ((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1))+(ancho/2)), vertical+100);
      
            
        TranslateTransition DESPLAZAMIENTOV2_iman_grua2 = new TranslateTransition();
            DESPLAZAMIENTOV2_iman_grua2.setNode(iman_grua2);
            DESPLAZAMIENTOV2_iman_grua2.setDuration(Duration.millis(duracion_animacion*100));
            double a = -((contenido.get(indiceI).getY())- (Yiman_grua2));
            DESPLAZAMIENTOV2_iman_grua2.setByY(a);            
            Timeline DESPLAZAMIENTOV2_cuerda_grua2= stretchLine(cuerda_grua2, ((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*1))+(ancho/2)), 200);
        
        
        
        //Xbase_grua2 = Xbase_grua2 + desplazamientoV + horizontal;
        
        
        
        reordenar();
        
        ParallelTransition  DESPLAZAMIENTOH = new ParallelTransition ();
            DESPLAZAMIENTOH.getChildren().addAll(transicion, DESPLAZAMIENTOH_base_grua2, DESPLAZAMIENTOH_iman_grua2, DESPLAZAMIENTOH_cuerda_grua2);
        
        ParallelTransition  DESPLAZAMIENTOV = new ParallelTransition ();
            DESPLAZAMIENTOV.getChildren().addAll(transicion2 ,DESPLAZAMIENTOV_iman_grua2, DESPLAZAMIENTOV_cuerda_grua2);
                    
        ParallelTransition  DESPLAZAMIENTOV2 = new ParallelTransition ();
        DESPLAZAMIENTOV2.getChildren().addAll(DESPLAZAMIENTOV2_iman_grua2, DESPLAZAMIENTOV2_cuerda_grua2);
        
        
        Xbase_grua2 = Xbase_grua2 - desplazamientoV + horizontal;
        
        ANIMACIONES.getChildren().add(DESPLAZAMIENTOH);
        ANIMACIONES.getChildren().add(DESPLAZAMIENTOV);
        ANIMACIONES.getChildren().add(DESPLAZAMIENTOV2);
        
        
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
            
            
        TranslateTransition RASTREObase_grua1H = new TranslateTransition(); 
            RASTREObase_grua1H.setNode(base_grua1);
            RASTREObase_grua1H.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoH2 = (espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*j)-Xbase_grua1);
            RASTREObase_grua1H.setByX(desplazamientoH2);
            
        TranslateTransition RASTREOiman_grua1H = new TranslateTransition(); 
            RASTREOiman_grua1H.setNode(iman_grua1);
            RASTREOiman_grua1H.setDuration(Duration.millis(duracion_animacion*100));
            
            RASTREOiman_grua1H.setByX(desplazamientoH2);
        
        TranslateTransition RASTREOcuerda_grua1H = new TranslateTransition(); 
            RASTREOcuerda_grua1H.setNode(cuerda_grua1);
            RASTREOcuerda_grua1H.setDuration(Duration.millis(duracion_animacion*100));
            
            RASTREOcuerda_grua1H.setByX(desplazamientoH2);
            
        TranslateTransition DESPLAZAMIENTOH_base_grua1 = new TranslateTransition();
            DESPLAZAMIENTOH_base_grua1.setNode(base_grua1);
            DESPLAZAMIENTOH_base_grua1.setDuration(Duration.millis(duracion_animacion*100));            
            DESPLAZAMIENTOH_base_grua1.setByX(desplazamientoH);
            
        TranslateTransition DESPLAZAMIENTOH_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOH_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOH_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));            
            DESPLAZAMIENTOH_iman_grua1.setByX(desplazamientoH);
            
        TranslateTransition DESPLAZAMIENTOH_cuerda_grua1 = new TranslateTransition();
            DESPLAZAMIENTOH_cuerda_grua1.setNode(cuerda_grua1);
            DESPLAZAMIENTOH_cuerda_grua1.setDuration(Duration.millis(duracion_animacion*100));            
            DESPLAZAMIENTOH_cuerda_grua1.setByX(desplazamientoH);
        
        TranslateTransition DESPLAZAMIENTOV_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOV_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOV_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoV = (contenido.get(indiceJ).getY())- (Yiman_grua1);
            DESPLAZAMIENTOV_iman_grua1.setByY(desplazamientoV);
                Timeline DESPLAZAMIENTOV_cuerda_grua1 = stretchLine(cuerda_grua1, (espacio_reservadoI+(ancho/2)), posY-contenido.get(indiceJ).getHeight());
      
            
        TranslateTransition DESPLAZAMIENTOV2_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOV2_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOV2_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTOV2_iman_grua1.setByY(-desplazamientoV);            
            Timeline DESPLAZAMIENTOV2_cuerda_grua1 = stretchLine(cuerda_grua1, (espacio_reservadoI+(ancho/2)), 200);
            
            
        ParallelTransition  paralelo = new ParallelTransition ();
        paralelo.getChildren().addAll(H1, DESPLAZAMIENTOH_base_grua1, DESPLAZAMIENTOH_iman_grua1, DESPLAZAMIENTOH_cuerda_grua1);
        
        ParallelTransition  paralelo2 = new ParallelTransition ();
        paralelo2.getChildren().addAll(RASTREObase_grua1H, RASTREOiman_grua1H, RASTREOcuerda_grua1H);
        
        ParallelTransition  paralelo3 = new ParallelTransition ();
        paralelo3.getChildren().addAll(DESPLAZAMIENTOV_iman_grua1, DESPLAZAMIENTOV_cuerda_grua1);
        
        ParallelTransition  paralelo4 = new ParallelTransition ();
        
        paralelo4.getChildren().addAll(DESPLAZAMIENTOV2_iman_grua1, DESPLAZAMIENTOV2_cuerda_grua1 );
        
        Xbase_grua1 = Xbase_grua1 + desplazamientoH2 + desplazamientoH;
        
        ANIMACIONES.getChildren().add(paralelo2);
        ANIMACIONES.getChildren().add(paralelo3);
        ANIMACIONES.getChildren().add(paralelo);
        ANIMACIONES.getChildren().add(paralelo4);
        
        
    }
    
    private Timeline stretchLine(Line line, double targetX, double targetY) {
        double startX = line.getStartX();
        double startY = line.getStartY();

        // Crear animaciones para estirar la línea en las coordenadas x e y
        KeyValue keyValueX = new KeyValue(line.endXProperty(), targetX);
        KeyValue keyValueY = new KeyValue(line.endYProperty(), targetY);

        // Crear keyframes para las animaciones
        KeyFrame keyFrameX = new KeyFrame(Duration.millis(duracion_animacion*100), keyValueX);
        KeyFrame keyFrameY = new KeyFrame(Duration.millis(duracion_animacion*100), keyValueY);
        //Duration.seconds(0.1)
        // Crear una timeline con los keyframes
        Timeline timeline = new Timeline(keyFrameX, keyFrameY);

        // Establecer el punto de partida de la línea
        line.setEndX(startX);
        line.setEndY(startY);

        // Iniciar la animación
        return timeline;
    }
    
    void reordenar(){
        for (int i = 0; i < indicesSub.size(); i++) {
            indices.set(i, indicesSub.get(i));
        }
    }
       
    
    @FXML
    public void burbujaOP(){
        if(!lista_ordenada){
            base_grua1 = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*0)), (166), ancho, (20));
            Xbase_grua1 = base_grua1.getX();
            anchorPane.getChildren().add(base_grua1);
            iman_grua1 = new Rectangle((espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*0)), (200), ancho, (20));
            Yiman_grua1 = 200 + 20;
            anchorPane.getChildren().add(iman_grua1);
            cuerda_grua1 = new Line((espacio_reservadoI+(ancho/2)),175,(espacio_reservadoI+(ancho/2)),200);
            anchorPane.getChildren().add(cuerda_grua1);
            
            for (int i = 1; i < contenidoC.size(); i++) {
                boolean intercambio = false;
                for (int j = 0; (j < ((contenidoC.size()-i))); j++) {
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
            vertical = contenido.get(indice).getY()-100;
            V1.setByY(-100);
            //ANIMACIONES.getChildren().add(V1);
            
        TranslateTransition RASTREObase_grua1H = new TranslateTransition(); 
            RASTREObase_grua1H.setNode(base_grua1);
            RASTREObase_grua1H.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoH2 = (espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*i)-Xbase_grua1);
            RASTREObase_grua1H.setByX(desplazamientoH2);
            
        TranslateTransition RASTREOiman_grua1H = new TranslateTransition(); 
            RASTREOiman_grua1H.setNode(iman_grua1);
            RASTREOiman_grua1H.setDuration(Duration.millis(duracion_animacion*100));
            
            RASTREOiman_grua1H.setByX(desplazamientoH2);
        
        TranslateTransition RASTREOcuerda_grua1H = new TranslateTransition(); 
            RASTREOcuerda_grua1H.setNode(cuerda_grua1);
            RASTREOcuerda_grua1H.setDuration(Duration.millis(duracion_animacion*100));
            
            RASTREOcuerda_grua1H.setByX(desplazamientoH2);
        
        TranslateTransition DESPLAZAMIENTOV_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOV_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOV_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoV = (contenido.get(indice).getY())- (Yiman_grua1);
            DESPLAZAMIENTOV_iman_grua1.setByY(desplazamientoV);
                Timeline DESPLAZAMIENTOV_cuerda_grua1 = stretchLine(cuerda_grua1, (espacio_reservadoI+(ancho/2)), posY-contenido.get(indice).getHeight());
      
            
        TranslateTransition DESPLAZAMIENTOV2_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOV2_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOV2_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTOV2_iman_grua1.setByY(-100);            
            Timeline DESPLAZAMIENTOV2_cuerda_grua1 = stretchLine(cuerda_grua1, (espacio_reservadoI+(ancho/2)), vertical+100);
            
        TranslateTransition DESPLAZAMIENTOH_base_grua1 = new TranslateTransition();
            DESPLAZAMIENTOH_base_grua1.setNode(base_grua1);
            DESPLAZAMIENTOH_base_grua1.setDuration(Duration.millis(duracion_animacion*100));            
            //DESPLAZAMIENTOH_base_grua1.setByX(desplazamientoH);
            
        TranslateTransition DESPLAZAMIENTOH_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOH_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOH_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));            
            //DESPLAZAMIENTOH_iman_grua1.setByX(desplazamientoH);
            
        TranslateTransition DESPLAZAMIENTOH_cuerda_grua1 = new TranslateTransition();
            DESPLAZAMIENTOH_cuerda_grua1.setNode(cuerda_grua1);
            DESPLAZAMIENTOH_cuerda_grua1.setDuration(Duration.millis(duracion_animacion*100));            
            //DESPLAZAMIENTOH_cuerda_grua1.setByX(desplazamientoH); 
        
            
        TranslateTransition H1 = new TranslateTransition();
            H1.setNode(contenido.get(indice));
            H1.setDuration(Duration.millis(duracion_animacion*100));

            int posicion_actual = (espacio_reservadoI/*sangria*/+((entre_espacio+ancho)*i));
            if(!cocktail){
                H1.setByX(espacio_reservado-posicion_actual);
                DESPLAZAMIENTOH_base_grua1.setByX(espacio_reservado-posicion_actual);
                DESPLAZAMIENTOH_iman_grua1.setByX(espacio_reservado-posicion_actual);
                DESPLAZAMIENTOH_cuerda_grua1.setByX(espacio_reservado-posicion_actual); 
                
            }else{
                H1.setByX(sangria-posicion_actual);
            }
            
            //ANIMACIONES.getChildren().add(H1);
        
        
        TranslateTransition V2 = new TranslateTransition();
            V2.setNode(contenido.get(indice));
            V2.setDuration(Duration.millis(duracion_animacion*100));

            V2.setByY(100);
            
        TranslateTransition DESPLAZAMIENTOV3_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOV3_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOV3_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));
            double desplazamientoV3 = (contenido.get(indice).getY())- (Yiman_grua1);
            DESPLAZAMIENTOV_iman_grua1.setByY(100);
                Timeline DESPLAZAMIENTOV3_cuerda_grua1 = stretchLine(cuerda_grua1, (espacio_reservadoI+(ancho/2)), vertical+100);
      
            
        TranslateTransition DESPLAZAMIENTOV4_iman_grua1 = new TranslateTransition();
            DESPLAZAMIENTOV4_iman_grua1.setNode(iman_grua1);
            DESPLAZAMIENTOV4_iman_grua1.setDuration(Duration.millis(duracion_animacion*100));
            DESPLAZAMIENTOV4_iman_grua1.setByY(-desplazamientoV);            
            Timeline DESPLAZAMIENTOV4_cuerda_grua1 = stretchLine(cuerda_grua1, (espacio_reservadoI+(ancho/2)), 200);
        
        ParallelTransition  RASTREO = new ParallelTransition ();
            RASTREO.getChildren().addAll(RASTREObase_grua1H, RASTREOiman_grua1H, RASTREOcuerda_grua1H);
            
        ParallelTransition  VERTICAL1 = new ParallelTransition ();
            VERTICAL1.getChildren().addAll(DESPLAZAMIENTOV_iman_grua1, DESPLAZAMIENTOV_cuerda_grua1);
            
            ParallelTransition  VERTICAL2 = new ParallelTransition ();
                VERTICAL2.getChildren().addAll(V1, DESPLAZAMIENTOV2_iman_grua1, DESPLAZAMIENTOV2_cuerda_grua1 );
        
                        
        ParallelTransition  DESPLAZAMIENTO = new ParallelTransition ();
            DESPLAZAMIENTO.getChildren().addAll(H1, DESPLAZAMIENTOH_base_grua1, DESPLAZAMIENTOH_iman_grua1, DESPLAZAMIENTOH_cuerda_grua1);            
        
            ParallelTransition  VERTICAL3 = new ParallelTransition ();
            VERTICAL3.getChildren().addAll(V2, DESPLAZAMIENTOV3_iman_grua1, DESPLAZAMIENTOV3_cuerda_grua1);
        
            ParallelTransition  VERTICAL4 = new ParallelTransition ();
                VERTICAL4.getChildren().addAll(DESPLAZAMIENTOV4_iman_grua1, DESPLAZAMIENTOV4_cuerda_grua1);
            
            ANIMACIONES.getChildren().add(RASTREO);
            ANIMACIONES.getChildren().add(VERTICAL1);
            ANIMACIONES.getChildren().add(VERTICAL2);            
            ANIMACIONES.getChildren().add(DESPLAZAMIENTO);            
            ANIMACIONES.getChildren().add(VERTICAL3);
            //ANIMACIONES.getChildren().add(VERTICAL4);
            
            
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
                desplazamientoH = (contenido.get(j).getX()-espacio_reservado);
            }else{
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        barra_duracion.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                duracion_animacion = (int) barra_duracion.getValue();
            }
        });
    }

    
}