/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication2;

import java.util.ArrayList;
import javafx.scene.shape.Line;


public class NumBuilder {
    ArrayList<ArrayList<Line>> digitos = new ArrayList<>();
    ArrayList<Line> digitosLines = new ArrayList<>();
    int AltNum = 20;
    //Ancho num: 10 Altura num: 15 
    
    public NumBuilder(int num, float x, float y, int ancho) {
        digitosLines.clear();
        if (num < 10){
            //En caso de que el numero ingresado sea de solo un digito
            Dibujar(num, x, y, ancho);

        }
        else{

            int aux = num/10;
            System.out.println("a");
            Dibujar(aux, x, y, ancho);


            Dibujar(num-(aux*10), x+ancho/2 , y, ancho);
            System.out.println("c" + digitos.size());
            
        }
        
   
    }

    private void Dibujar(int op, float posX, float posY, int AnchNum){
        
        
        Line linea;
        Line linea2;
        Line linea3;
        Line linea4;
        Line linea5;
        digitosLines = new ArrayList<>();
        switch(op){
            case 1:
                
                linea = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                
                
                this.digitos.add(this.digitosLines);
                

                
                break;
                
            case 2:
                linea = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum/2);
                linea3 = new Line(posX+AnchNum/2-10, posY+AltNum/2, posX, posY+AltNum/2);
                linea4 = new Line(posX, posY+AltNum/2, posX, posY+AltNum);
                linea5 = new Line(posX, posY+AltNum, posX+AnchNum/2-10, posY+AltNum);

                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 3:
                
                linea = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum/2);
                linea3 = new Line(posX, posY+AltNum, posX+AnchNum/2-10, posY+AltNum);
                linea4 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 4: 
                
                linea = new Line(posX, posY, posX, posY+AltNum/2);
                linea2 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum/2);
                linea3 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitos.add(this.digitosLines);
                break;
                                
            case 5: 
                
                linea = new Line(posX+AnchNum/2-10, posY, posX, posY);
                linea2 = new Line(posX, posY, posX, posY+AltNum/2);
                linea3 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum/2);
                linea4 = new Line(posX+AnchNum/2-10, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum);
                linea5 = new Line(posX+AnchNum/2-10, posY+AltNum, posX, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                this.digitos.add(this.digitosLines);
                break;
                                
            case 6: 
                
                linea = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX, posY, posX, posY+AltNum);
                linea3 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum/2);
                linea4 = new Line(posX, posY+AltNum, posX+AnchNum/2-10, posY+AltNum);
                linea5 = new Line(posX+AnchNum/2-10, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                
                this.digitos.add(this.digitosLines);
                
                break;
                
            case 7:
                
                linea = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitos.add(this.digitosLines);
                break;

            case 8:
                
                linea = new Line(posX, posY, posX, posY+AltNum);
                linea2 = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea3 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum/2);
                linea4 = new Line(posX, posY+AltNum, posX+AnchNum/2-10, posY+AltNum);
                linea5 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitosLines.add(linea5);
                this.digitos.add(this.digitosLines);
                break;
                
            case 9:
                
                linea = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX, posY, posX, posY+AltNum/2);
                linea3 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                linea4 = new Line(posX, posY+AltNum/2, posX+AnchNum/2-10, posY+AltNum/2);
                
                this.digitosLines.add(linea);
                this.digitosLines.add(linea2);
                this.digitosLines.add(linea3);
                this.digitosLines.add(linea4);
                this.digitos.add(this.digitosLines);
                break;
                
            case 0:
                
                linea = new Line(posX, posY, posX+AnchNum/2-10, posY);
                linea2 = new Line(posX+AnchNum/2-10, posY, posX+AnchNum/2-10, posY+AltNum);
                linea3 = new Line(posX+AnchNum/2-10, posY+AltNum, posX, posY+AltNum);
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


