

package pruebajavafx;
import java.util.*;

public class PruebaJavaFX {

    public static void main(String[] args) {
        ArrayList<Numero> numeros = new ArrayList<>();
        Numero numero = new Numero(16);

        for (int i = 0; i < numeros.size(); i++) {
            int num = (int) (Math.random() * 99) + 1;
            numeros[i] = new Numero(num);
        }

        for (int i = 1; i < numeros.length; i++) {
            int key = numeros[i].getNum();
            int j = i - 1;

            while (j >= 0 && numeros[j].getNum() > key) {
                numeros[j+1] = numeros[j];
                j--;
            }
            numeros[j+1] = new Numero(key);
        }

    }

}
