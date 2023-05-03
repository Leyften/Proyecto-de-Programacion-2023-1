/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    float AltNum;
    //Espacio entre numeros
    float space;
    
    public NumBuilder(int num, float x, float y, int ancho) {
        y -= 10;
        //Clean para ocupar el "molde"
        digitosLines.clear();
        //Calculo de espacio y ancho
        this.space = (ancho*5)/100;
        this.AltNum = ancho;
        ancho = (ancho*90)/100;
        
        //Caso de un solo digito
        if (num < 10){

            Dibujar(num, x, y, ancho);

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

    private void Dibujar(int op, float posX, float posY, float AnchNum){
        //posibles lineas a ocupar, se llaman aca, para no repetir las llamadas
        Line linea;
        Line linea2;
        Line linea3;
        Line linea4;
        Line linea5;
        
        //Contenedor de los Lines que forman un digito
        digitosLines = new ArrayList<>();
        
        //switch en base al digito necesitado
        switch(op){
            
            case 1:
                //Lineas que componen el numero
                linea = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                //
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                
                
                this.digitos.add(this.digitosLines);
                

                
                break;
                
            case 2:
                linea = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum/2);
                linea3 = new Line(posX+AnchNum/2-space, posY+AltNum/2, posX, posY+AltNum/2);
                linea4 = new Line(posX, posY+AltNum/2, posX, posY+AltNum);
                linea5 = new Line(posX, posY+AltNum, posX+AnchNum/2-space, posY+AltNum);

                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 3:
                
                linea = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum/2);
                linea3 = new Line(posX, posY+AltNum, posX+AnchNum/2-space, posY+AltNum);
                linea4 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 4: 
                
                linea = new Line(posX, posY, posX, posY+AltNum/2);
                linea2 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum/2);
                linea3 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitos.add(this.digitosLines);
                break;
                                
            case 5: 
                
                linea = new Line(posX+AnchNum/2-space, posY, posX, posY);
                linea2 = new Line(posX, posY, posX, posY+AltNum/2);
                linea3 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum/2);
                linea4 = new Line(posX+AnchNum/2-space, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum);
                linea5 = new Line(posX+AnchNum/2-space, posY+AltNum, posX, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                this.digitos.add(this.digitosLines);
                break;
                                
            case 6: 
                
                linea = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX, posY, posX, posY+AltNum);
                linea3 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum/2);
                linea4 = new Line(posX, posY+AltNum, posX+AnchNum/2-space, posY+AltNum);
                linea5 = new Line(posX+AnchNum/2-space, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 7:
                
                linea = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitos.add(this.digitosLines);
                break;

            case 8:
                
                linea = new Line(posX, posY, posX, posY+AltNum);
                linea2 = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea3 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum/2);
                linea4 = new Line(posX, posY+AltNum, posX+AnchNum/2-space, posY+AltNum);
                linea5 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                this.digitos.add(this.digitosLines);
                break;
                
            case 9:
                
                linea = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX, posY, posX, posY+AltNum/2);
                linea3 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                linea4 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-space, posY+AltNum/2);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitos.add(this.digitosLines);
                break;
                
            case 0:
                
                linea = new Line(posX, posY, posX+AnchNum/2-space, posY);
                linea2 = new Line(posX+AnchNum/2-space, posY, posX+AnchNum/2-space, posY+AltNum);
                linea3 = new Line(posX+AnchNum/2-space, posY+AltNum, posX, posY+AltNum);
                linea4 = new Line(posX, posY+AltNum, posX, posY);
                
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
