
package pruebajavafx;
import java.util.*;
/**
 *
 * @author pablo
 */
public class Embarque {
    ArrayList<Caja> cajas;
    
    public Embarque(int tamano){
        //Creacion array de cajas
        cajas = new ArrayList<>(); 
        //Relleno de cajas con randoms
        Random rand = new Random();
        
        for(int i = 0; i < tamano; i++){
            Caja caja = new Caja(rand.nextInt(100));
            cajas.add(caja);
        }
        printArreglo();
        BubbleSort();
        printArreglo();
          
    }
     
    public void printArreglo(){
        System.out.print("Arreglo: [ " );
        for(int i = 0; i < cajas.size() - 1; i++){
            System.out.print(cajas.get(i).getNum() + ", ");
        }
        System.out.println(cajas.get(cajas.size()-1).getNum() + "]");
    }
    
    public void BubbleSort(){
        for(int i = 1; i < cajas.size(); i++){
            int valorActual = cajas.get(i).getNum();
            int j = i - 1;
            while(j >= 0 && cajas.get(j).getNum() > valorActual){
                cajas.get(j+1).setNum(cajas.get(j).getNum());
                j--;
            }
            cajas.get(j+1).setNum(valorActual);
        }
    }

}
