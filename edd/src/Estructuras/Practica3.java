package edd.src.Estructuras;
public class Practica3 {
    
    public static void sumaCercana(Lista<Integer> lista, int N){

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
        primosBackTrack(primos, solution, 0, 0, S, N);
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
        for(int i=peekIndex; i<primos.length; i++){
            if(suma + primos[i] <= S && !solucion.contains(primos[i]) ){
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
     * @param solucion
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

    public static void main(String[] args) {
        System.out.println("Permutaciones de: ABC");
        permutacionesCadena("ABC");
        System.out.println("Primos que suman P=2, S=23, N=3");
        primosQueSuman(23, 2, 3);
        System.out.println("N reinas para  N=8");
        N_Reinas(8);
    }

}
