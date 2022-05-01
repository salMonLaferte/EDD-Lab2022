package edd.src.Estructuras;

import java.util.Comparator;

public class Practica3 {
    
    /**
     * Imprime la pareja de numeros en la lista tal que su suma es la mas cercana a N
     * @param lista
     * @param N
     */
    public static void sumaCercana(Lista<Integer> lista, int N){
        //Ordenamos la lista en O(n log n)
        Lista<Integer> sorted = lista.mergeSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }  
        });

        //Inicializamos un puntero al inicio y otro al final de la lista
        IteradorLista<Integer> leftIt = sorted.iteradorLista();
        IteradorLista<Integer> rightIt = sorted.iteradorLista();
        rightIt.end();
        int r = rightIt.previous();
        int l = leftIt.next();
        int goodL = l;
        int goodR = r;
        int lowestDifference = Math.abs(N-(r+l));
        while(r+l != N){
            if( r + l < N){//Si la suma de los punteros es menor movemos el puntero izquierdo a la derecha
                l = leftIt.next();
            }
            else{// Si la suma de los punteros es mayor movemos el puntero derecho a la izquierda
                r = rightIt.previous();
            }
            if( r == l)//Si ambos apuntadores apuntan al mismo valor terminamos
                break;
            if( lowestDifference > Math.abs(N-(r+l))){//Si la nueva diferencia es menor la guardanos, asi como los numeros que la consiguen
                goodL = l;
                goodR = r;
                lowestDifference =Math.abs(N-(r+l));
            }
        }
        System.out.print(goodL + ", " + goodR + "\n");
    }


    /**Imprime las permutaciones de una cadena de caracteres
     * 
     */
    public static void permutacionesCadena(String cadena){
        Lista<Integer> perm = new Lista<Integer>();
        permBackTrack(perm, cadena);
    }

    /**
     * Funcion recursiva que utiliza backtracking para imprimir permutaciones
     * @param perm
     * @param cadena
     */
    public static void permBackTrack(Lista<Integer> perm, String cadena){
        if(perm.size() == cadena.length()){//Si la lista tiene tantos elementos como letras en la cadena imprime la permutacion
            System.out.println( indexToPermutation(perm, cadena) );
            return;
        }
        for(int i=0; i< cadena.length(); i++){
            if(!perm.contains(i)){//Si la lista de numeros no contiene a i, se agrega
                perm.add(i);
                permBackTrack(perm, cadena);//Siguiente paso
                perm.pop(); //Regresa un paso
            }
        }
    }

    /**
     * Regresa una permutación de la cadena con el indices especificados
     * @param perm
     * @param cadena
     * @return
     */
    public static String indexToPermutation(Lista<Integer> perm, String cadena){
        IteradorLista<Integer> it = perm.iteradorLista();
        String s = "";
        while(it.hasNext()){
            s += cadena.charAt(it.next());
        }
        return s;
    }


    /**
     * Imprime las soluciones posibles que cumplen ser N numeros primos tales que  cada uno es mayor que P y su suma es S.
     * @param S
     * @param P
     * @param N
     */
    public static void primosQueSuman(int S, int P, int N){
        int[] primos = criba(P, S);
        Lista<Integer> solution = new Lista<Integer>();
        primosBackTrack(primos, solution, -1, 0, S, N);
    }

    /***
     * Funcion recursiva que utiliza backtracking para imprimir las soluciones de N primos distintos tales que estan en el arreglo 
     * primos y su suma es S
     * @param primos arreglo con los numeros primos entre P y S
     * @param solucion solucion parcial
     * @param peekIndex indice en el arreglo de primos del ultimo primo que se agregó
     * @param suma suma  de los numeros en la lista solucion
     * @param S suma objetivo
     * @param N tamaño que debe tener la solucion
     */
    static void primosBackTrack(int[] primos, Lista<Integer> solucion, int peekIndex, int suma, int S, int N){
        if(solucion.size() == N &&  suma == S){
            System.out.println(solucion);
        }else if( solucion.size() == N)
            return;
        for(int i=peekIndex+1; i<primos.length; i++){
            if(suma + primos[i] <= S ){
                solucion.add(primos[i]);
                primosBackTrack(primos, solucion, i, suma + primos[i], S, N);
                solucion.pop();
            }
        }
    }

    /**
     * Regresa los numeros primos mayores o iguales que m y menores o iguales que  n
     * @param n
     * @return
     */
    public static int[] criba(int m, int n){
        boolean[] criba = new boolean[n+1];
        int[] primos;
        int i =2;
        //Marca los nùmeros que nos on primos
        while(true){   
            if( !criba[i] ){
                if(i*i > n)
                    break;
            }
            for(int j=2; i*j<=n; j++){
                criba[i*j] = true;
            }
            i++;
        }
        //Cuenta los numeros primos
        int count = 0;
        for(int j=m+1; j<=n; j++){
            if(!criba[j]){
                count++;
            }
        }
        //Crea el arreglo con los numeros primos
        primos = new int[count];
        int k= 0;
        for(int j=m+1; j<=n; j++){
            if(!criba[j]){
                primos[k] = j;
                k++;
            }
        }

        return primos;
    }

    public static void N_Reinas(int N){
        Lista<Integer> solucion = new Lista<Integer>();
        reinasBackTrack(solucion, N);
    }

    /**
     * Función recursiva que imprime las soluciones del problema de las n reinas usando backtracking
     * @param solucion Solucion parcial, en el que el i esimo elemento de la lista representa la posiciob de la reina en la i-esima columna 
     * @param N
     */
    public static void reinasBackTrack(Lista<Integer> solucion, int N){
        if(solucion.size() == N){
            imprimirSolucionReinas(solucion);
            System.out.println("_____________________");
        }
        for(int i=0; i<N; i++){
            if( esValidoAgregar(solucion, i)){
                solucion.add(i);
                reinasBackTrack(solucion, N);
                solucion.pop();
            }
        }
    }
    
    /***
     * Regresa verdadero si es valido agregar una reina en la fila k, de la siguiente columna
     * @param solucion 
     * @param k
     * @return
     */
    public static boolean esValidoAgregar(Lista<Integer> solucion, int k){
        if(solucion.isEmpty())
            return true;
        IteradorLista<Integer> it = solucion.iteradorLista();
        int count = 0;
        while(it.hasNext()){
            int current = it.next();
            if(current == k || Math.abs(count - solucion.size()) == Math.abs(current - k))
                return false;
            count++;
        }
        return true;
    }

    /**
     * Imprime una soluciòn al problema de las n reinas en forma de tablero
     * @param solucion
     */
    public static void imprimirSolucionReinas(Lista<Integer> solucion){
        IteradorLista<Integer> it = solucion.iteradorLista();
        String[] lines =  new String[solucion.size()];
        int k = 0; 
        while(it.hasNext()){
            int current = it.next();
            String line = "|";
            for(int i=0; i<solucion.size(); i++){
                if(i == k)
                    line+= "X|";
                else
                    line+= " |";
            }
            lines[current] = line;
            k++;
        }

        for(int i=0; i<solucion.size(); i++){
            System.out.println(lines[i]);
        }

    }

    /**
     * Devuelve la raiz cuadrada de x con un error maximo de 1e-5
     * @param x
     * @return
     */
    public static double sqrtBusqBin(int x){
        double l = 1;
        double r = x;
        double a = (l+r)/2;;
        while( Math.abs(a*a - x) > 0.00001f){
            a = (l+r)/2;
            if( a*a > x){
                r = a; 
            }
            else
                l = a;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Permutaciones de: ABC");
        permutacionesCadena("ABC");
        System.out.println("Primos que suman P=7, S=100, N=4");
        primosQueSuman(100, 7, 4);
        System.out.println("N reinas para  N=4");
        N_Reinas(4);
        System.out.println("Suma cercana");
        Lista<Integer> lista = new Lista<>();
        lista.add(25);
        lista.add(12);
        lista.add(17);
        lista.add(6);
        lista.add(38);
        lista.add(19);
        lista.add(1);
        System.out.println("La lista es: " + lista.toString());
        System.out.println("Los numeros cuya suma mas cercana es 29 son : ");
        sumaCercana(lista, 29);
        System.out.println("Los numeros cuya suma mas cercana es 4 son : ");
        sumaCercana(lista, 4);
        System.out.println("Los numeros cuya suma mas cercana es 86 son : ");
        sumaCercana(lista, 83);
        System.out.println("Los numeros cuya suma mas cercana es 24 son : ");
        sumaCercana(lista, 24);
        System.out.println("La raiz cuadrada aproximada de 37 es: ");
        System.out.println(sqrtBusqBin(37));
        System.out.println("La raiz cuadrada aproximada de 263 es: ");
        System.out.println(sqrtBusqBin(263));
        System.out.println("La raiz cuadrada aproximada de 400 es: ");
        System.out.println(sqrtBusqBin(400));

        lista.empty();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);

        ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>(lista, false) ;
        System.out.println(arbol);
    }

}
