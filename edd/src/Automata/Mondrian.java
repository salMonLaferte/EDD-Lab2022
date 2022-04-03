package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    

    /*
     * Metodo que pinta una matriz inicial de Blanco y le da valores aleatorios a las
     * casillas.
     *
     */
    @Override
    public int[][] getAutomata() {
        int aux1; 
        //Inicializo dos matrices en blanco
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = 2;
                MauxCopia[i][j] = 2;
            }
        }
        // Modifico cada valor de la matriz Maux de forma aleatoria. Para empezar con un estado random de colores
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {

                aux1 = (int) (Math.random() * 14); // Random del 0 al 12

                if (aux1 <= 1) {
                    Maux2[i][j] = 3; // Color negro
                } else if (aux1 > 3 && aux1 <= 5) {
                    Maux2[i][j] = 1; // Azul
                } else if (aux1 > 5 && aux1 <= 7) {
                    Maux2[i][j] = 0; // amarillo
                } else if (aux1 > 6 && aux1 <= 8) {
                    Maux2[i][j] = 4; // Rojo
                } else {

                    Maux2[i][j] = 2; // Blanco
                }
            }
        }
        return Maux2;
    }

    /*
     * Metodo para evolucionar el automata.
     *
     */
    @Override
    public void evoluciona() {

        // Se crea una matriz copia para reemplazar los Valores.
        int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
        
        // Aqui empieza una evolucion.

        //Creamos una pila y una cola para que te diviertas joven Artista. 
        Pila<Integer> pila = new Pila<Integer>();
        Pila<Integer> cola = new Pila<Integer>();
        

        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores
        // Aqui va tu codigo  

        int[][] indexes = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};//para iterar en la vecindad de Moore
        
        for(int i=0; i<Imagen.numCells; i++){
            for(int j=0; j< Imagen.numCells; j++){
                // Cuenta la frecuencia de colores en la vecindad de MOOre
                int[] freq = {0,0,0,0,0,0};
                for(int k=0; k<8; k++){
                    int x = i + indexes[k][0];
                    int y = j + indexes[k][1];
                    if( x >= 0 && x < Imagen.numCells && y >=0 && y < Imagen.numCells){
                        freq[Maux2[x][y]]++;
                    }
                }


                int maxIndex=0;
                int minIndex= 0;
                for(int k=1; k<5; k++){
                    if(freq[k] > freq[maxIndex] ){
                        maxIndex = k;
                    }
                    else if(freq[k] ==  freq[maxIndex]){
                        if( Math.random() > .5f )
                            maxIndex = k;
                    }
                    if(freq[k] < freq[minIndex]){
                        minIndex = k;
                    }
                    else if(freq[k] == freq[minIndex]){
                        if( Math.random() > .5f )
                            minIndex = k;
                    }
                }

                if(freq[maxIndex] >= 4 ){
                    if( Maux2[i][j] == maxIndex ){
                        if(!cola.isEmpty()){
                            CopiaM[i][j] = cola.peek();
                            cola.pop();
                        }else
                            CopiaM[i][j] = minIndex;
                    }
                    else{
                        CopiaM[i][j] = maxIndex;
                        cola.push(Maux2[i][j]);
                    }
                }
                else{
                    CopiaM[i][j] = Maux2[i][j];
                }

            }
        }
        
        for (int i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
        // System.out.println("Termine");//SOP que ayuda a saber cuando acaba una
        // evolucion.
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}