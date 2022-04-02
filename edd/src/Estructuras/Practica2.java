package edd.src.Estructuras;

import java.util.Scanner;

public class Practica2 {
    
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        // No olvides imprimir cada paso de la solución. 
    }

    /**
     * Imprime los números del 1 al N en binario. Complejidad es Nlog(N)
     * @param N
     */
    public static void binarioColas(int N){
         Cola<String> actual = new Cola<String>();
         Cola<String> aux = new Cola<String>();
         actual.push("0");
         for(int i = 1; i<=N; i++){ 
             //Saca todos los unos al final de actual y mete esa cantidad de ceros en aux             
            while( !actual.isEmpty() && actual.peek() == "1" ){
                actual.pop();
                aux.push("0");
            }//Si la lista no quedó vacia, cambia un cero por un uno en aux y copia el resto de actual en aux
            if( !actual.isEmpty() ){
                actual.pop();
                aux.push("1");
                while(!actual.isEmpty()){
                    aux.push(actual.peek());
                    actual.pop();
                }
            }
            else{//Si la lista quedó vacía agrega un uno 
                aux.push("1");
            }
            //Actualiza actual y aux
            actual = aux.clone();
            aux.empty();
            //Quita comas y espacios del string de la cola e imprimir
            String actualStr = actual.toString();
            actualStr = actualStr.replaceAll(" ", "");
            actualStr = actualStr.replaceAll(",", "");
            System.out.println(actualStr);
         }
    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme. 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un numero: ");
        int n = scanner.nextInt();
        System.out.println("Los números del uno al " + n + " son : ");
        binarioColas(n);
    }

}
