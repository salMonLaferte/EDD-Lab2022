import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String numero_cadena = br.readLine();
            // System.out.println(numero_cadena);

            int numero = Integer.parseInt(numero_cadena);
            numero = Math.abs(numero);

            System.out.println(numero);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}