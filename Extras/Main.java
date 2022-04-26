import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
                          new InputStreamReader(System.in));
    String numero_cadena = br.readLine();

    int cantProductos = Integer.parseInt(numero_cadena);
    int[] precios = new int[cantProductos];
    String[] a_ordenar = new String[cantProductos];

    for (int i = 0; i < cantProductos; i++) {
        String linea = br.readLine();
        String[] producto = linea.split(" ");
        //precios[i] = Integer.parseInt(producto[1]);

        a_ordenar[i] = producto[1] + " " + producto[0];

    }

    // Ordenamos el arreglo.
    
    Arrays.sort(a_ordenar);
    
    // Imprimimos el ticket
    int j = 1;
    int precioFinal =0;
    for (String i : a_ordenar) {
        System.out.println(j +" | " + i);

        //precioFinal = precioFinal + preciosActual;
        j = j + 1;
    }

    /*
      1. crear un arreglo original ->  nombre, precio
      2. crear un arreglo de precios- > precio
      ordenar el de precios O(nlogn)
      buscamos para cada precio el nombre original. (n^2).
      imprimimos.


    */



  }
}