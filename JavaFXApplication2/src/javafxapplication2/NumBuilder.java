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

    public NumBuilder(int num) {
        if (num < 10){
            //En caso de que el numero ingresado sea de solo un digito
            
            digitos.add(Dibujar(num));
            
            for(int i = 0; i < digitos.get(0).size(); i++){
                digitos.get(0).get(i).setStartX((digitos.get(0).get(i).getStartX())-80);
            }
            digitosLines.clear();
        }
        else{
            
            int aux = num/10;
            digitos.add(Dibujar(aux));
            digitosLines.clear();
            
            digitos.add(Dibujar((aux*10)-num));
            digitos.add(Dibujar(aux));
            digitosLines.clear();
            
        }

        

        
        
    }

    private ArrayList<Line> Dibujar(int op){
        Line linea;
        Line linea2;
        Line linea3;
        Line linea4;
        Line linea5;
        
        switch(op){
            case 1:
                linea = new Line(0.0f, 50.0f, 80.0f, 0.0f);
                linea2 = new Line(80.0f, 0.0f, 80.0f, 100.0f);
               
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                
                return digitosLines;
                
            case 2:
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea5 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);
                digitosLines.add(linea5);
                
                return digitosLines; 
                
            case 3:
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);
                
                return digitosLines; 
                
            case 4: 
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                
                return digitosLines; 
                
            case 5: 
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea5 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);
                digitosLines.add(linea5);
                
                return digitosLines;
                
            case 6: 
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea5 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);
                digitosLines.add(linea5);
                
                return digitosLines; 
            case 7:
                
                linea = new Line(0.0f, 0.0f, 80.0f, 0.0f);
                linea2 = new Line(80.0f, 0.0f, 80.0f, 100.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                
                return digitosLines; 
                
            case 8:
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea5 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);
                digitosLines.add(linea5);
                
                return digitosLines; 
            case 9:
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea5 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);
                digitosLines.add(linea5);
                
                return digitosLines; 
            case 0:
                
                linea = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea2 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea3 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                linea4 = new Line(10.0f, 10.0f, 200.0f, 140.0f);
                
                digitosLines.add(linea);
                digitosLines.add(linea2);
                digitosLines.add(linea3);
                digitosLines.add(linea4);

                return digitosLines; 
        }
        return digitosLines;
        
    }
    
    public ArrayList<ArrayList<Line>> getDigitos() {
        return digitos;
    }
    
    
}


