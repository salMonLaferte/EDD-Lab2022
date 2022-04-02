package edd.src.Estructuras;

import java.util.Scanner;

public class Practica2 {
    
    public static void torresHanoi(int cantidadDiscos,Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        //Inicializa la pila de origen con la cantidad de discos especificados e imprime estado inicial
        for(int i=cantidadDiscos; i>= 1; i--){
            origen.push(i);
        }
        imprimeEstadoHanoi(origen, auxiliar, destino);

        int turno = 0;// variable para saber si en este turno se mueve el disco más pequeño o no
        int ciclo = 0;// variable para saber en que pila está el disco mas pequeño
        int count = 0;
        while(destino.size() != cantidadDiscos){
            if(turno % 2 == 0){//mueve el disco mas pequeño
                switch(ciclo){
                    case 0:
                    origen.pop();
                    destino.push(1);
                    break;
                    case 1:
                    auxiliar.pop();
                    origen.push(1);
                    break;
                    case 2:
                    destino.pop();
                    auxiliar.push(1);
                }
                ciclo--;
                if(ciclo < 0)
                    ciclo =2;
            }else{//realiza el movimiento disponible en las pilas que no contienen al disco mas pequeño
                 switch(ciclo){
                    case 0:
                    hanoiMove(auxiliar, destino);
                    break;
                    case 1:
                    hanoiMove(origen, destino);
                    break;
                    case 2:
                    hanoiMove(origen, auxiliar);
                    break;
                }
            }
            turno = (turno + 1) % 2;
            imprimeEstadoHanoi(origen, auxiliar, destino);
            count++;
        }
        System.out.println("Movimientos : " + count);
    }

    /**
     * Realiza el movimiento disponible dadas dos pilas
     * @param a
     * @param b
     */
    public static void hanoiMove(Pila<Integer> a, Pila<Integer> b){
        if(a.isEmpty() || b.isEmpty()){
            //Si ambas pilas estàn vacias no se hace nada
            if(a.isEmpty() && b.isEmpty()){
                return;
            }
            //Si una pila está vacía se mueve el disco de la otra pila hacia la pila vacía
            if(a.isEmpty()){
                a.push(b.peek());
                b.pop();
            }else{
                b.push(a.peek());
                a.pop();
            }
        }else{
            //Si ninguna pila está vacía se quita un disco en la pila que tenga el disco mas chico en la cima y se mete en la otra pila.
            if(a.peek() < b.peek() ){
                b.push(a.peek());
                a.pop();
            }else{
                a.push(b.peek());
                b.pop();
            }
        }
    }

    
    /**
     * Imprime el estado actual de las pilas 
     * @param origen
     * @param auxiliar
     * @param destino
     */
    public static void imprimeEstadoHanoi(Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        String[] pilares = new String[3];
        pilares[0] = origen.toString();
        pilares[1] = auxiliar.toString() ;
        pilares[2] = destino.toString();
        for(int i=0; i<3; i++){
            pilares[i] = pilares[i].replaceAll(", ", "-");
            System.out.println("*" + pilares[i]);
        }
        System.out.println("----------------");
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
        System.out.println("Los numeros del 1 al " + n + " en binario son : ");
        binarioColas(n);
        System.out.println("__________________________________________________");
        System.out.println("Introduce el numero de discos en la torre de Hanoi");
        n = scanner.nextInt();        
        System.out.println("Hanoi: ");
        System.out.println("---------------------------");
        Pila<Integer> origen = new Pila<Integer>();
        Pila<Integer> auxiliar = new Pila<Integer>();
        Pila<Integer> destino = new Pila<Integer>();
        torresHanoi(5, origen, auxiliar, destino);
        scanner.close();

    }

}
