
package javafxapplication2;

import java.util.ArrayList;
import javafx.scene.shape.Line;
public class NumBuilder {
    //ArrayList que continen el numero compuesto
    public ArrayList<ArrayList<Line>> digitos = new ArrayList<>();
    //ArrayList de las lineas que componen el/los numeros
    private ArrayList<Line> digitosLines = new ArrayList<>();
    
    //variables globales para estandar de dimensiones
    
    //Altura de los numeros
    double AltNum;
    //Espacio entre numeros
    double space;
    
    public NumBuilder(int num, float x, float y, double ancho) {
        y -= 10;
        //Clean para ocupar el "molde"
        digitosLines.clear();
        //Calculo de espacio y ancho
        this.space = (ancho*10)/100;
        this.AltNum = ancho-20;
        ancho = (ancho*90)/100;
        
        //Caso de un solo digito
        if (num < 10){

            Dibujar(num,  x+ancho/2, y, ancho);
            Dibujar(0,  x, y, ancho);

        }
        
        //Caso de 2 digitos
        else{
            //aux para tener la decena
            int aux = num/10;
            
            //Dibuja el primer digito
            Dibujar(aux, x, y, ancho);
            //Dibuja el siguiente, pero respetando el espacio del anterios
            Dibujar(num-(aux*10), x+ancho/2 , y, ancho);
            
        }
        
   
    }

    private void Dibujar(int op, double posX, double posY, double AnchNum){
        //posibles lineas a ocupar, se llaman aca, para no repetir las llamadas
        Line linea;
        Line linea2;
        Line linea3;
        Line linea4;
        Line linea5;
        Line linea6;
        Line linea7;
        Line linea8;
        Line linea9;
        Line linea10;
        Line linea11;
        Line linea12;
        //Contenedor de los Lines que forman un digito
        digitosLines = new ArrayList<>();
        
        //switch en base al digito necesitado
        switch(op){
            
            case 1:
                //Lineas que componen el numero
                linea = new Line(posX+10, (posY+AltNum/2)+5, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                //
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                
                
                this.digitos.add(this.digitosLines);
                

                
                break;
                
            case 2:
                linea = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum/2+5);
                linea3 = new Line(posX+AnchNum/2-space, posY+AltNum/2+5, posX+5, posY+AltNum/2+5);                
                linea4 = new Line(posX+5, (posY+AltNum/2)+5, posX+5, posY+AltNum-5);
                linea5 = new Line(posX+5, posY+AltNum-5, posX+AnchNum/2-space, posY+AltNum-5);

                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 3:
                
                linea = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+5, (posY+AltNum/2)+5, posX+AnchNum/2-space, (posY+AltNum/2)+5);
                linea3 = new Line(posX+5, posY+AltNum-5, posX+AnchNum/2-space, posY+AltNum-5);
                linea4 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 4: 
                
                linea = new Line(posX+5, posY+15, posX+5, (posY+AltNum/2)+5);
                linea2 = new Line(posX+5, (posY+AltNum/2)+5, posX+AnchNum/2-space, (posY+AltNum/2)+5);
                linea3 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitos.add(this.digitosLines);
                break;
                                
            case 5: 
                
                linea = new Line(posX+AnchNum/2-space, posY+15, posX+5, posY+15);
                linea2 = new Line(posX+5, posY+15, posX+5, posY+AltNum/2+5);
                linea3 = new Line(posX+5, (posY+AltNum/2)+5, posX+AnchNum/2-space, (posY+AltNum/2)+5);
                linea4 = new Line(posX+AnchNum/2-space, posY+AltNum/2+5, posX+AnchNum/2-space, posY+AltNum-5);
                linea5 = new Line(posX+AnchNum/2-space, posY+AltNum-5, posX+5, posY+AltNum-5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                this.digitos.add(this.digitosLines);
                break;
                                
            case 6: 
                
                linea = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+5, posY+15, posX+5, posY+AltNum-5);
                linea3 = new Line(posX+5, posY+AltNum/2+5, posX+AnchNum/2-space, posY+AltNum/2+5);
                linea4 = new Line(posX+5, posY+AltNum-5, posX+AnchNum/2-space, posY+AltNum-5);
                linea5 = new Line(posX+AnchNum/2-space, posY+AltNum/2+5, posX+AnchNum/2-space, posY+AltNum-5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 7:
                
                linea = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitos.add(this.digitosLines);
                break;

            case 8:
                
                linea = new Line(posX+5, posY+15, posX+5, posY+AltNum-5);
                linea2 = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea3 = new Line(posX+5, posY+AltNum/2+5, posX+AnchNum/2-space, posY+AltNum/2+5);
                linea4 = new Line(posX+5, posY+AltNum-5, posX+AnchNum/2-space, posY+AltNum-5);
                linea5 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                this.digitos.add(this.digitosLines);
                break;
                
            case 9:
                
                linea = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+5, posY+15, posX+5, posY+AltNum/2+5);
                linea3 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                linea4 = new Line(posX+5, posY+AltNum/2+5, posX+AnchNum/2-space, posY+AltNum/2+5);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitos.add(this.digitosLines);
                break;
                
            case 0:
                
                linea = new Line(posX+5, posY+15, posX+AnchNum/2-space, posY+15);
                linea2 = new Line(posX+AnchNum/2-space, posY+15, posX+AnchNum/2-space, posY+AltNum-5);
                linea3 = new Line(posX+AnchNum/2-space, posY+AltNum-5, posX+5, posY+AltNum-5);
                linea4 = new Line(posX+5, posY+AltNum-5, posX+5, posY+15);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitos.add(this.digitosLines);
                break;
                
        }

        
    }
    
    public ArrayList<ArrayList<Line>> getDigitos() {
        return digitos;
    }

}