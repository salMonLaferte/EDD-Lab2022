import java.util.Set;
import java.util.HashSet;
public class Practica1 {
    


    /**
     * Adds the element nuevo to the list in the first index such that nuevo is greater than or equal to 
     * every other element before and less than or equal to the element after. 
     * If nuevo is less than or equal to the first element it will be added at the beginning. 
     * If nuevo is greater than or equal to every other element of the list it will be added at the end.
     * if lista is ordered it will stay ordered after adding the new element.
     * Time complexity: Worst case is O(n) in which nuevo is inserted at the end of the list and is compared to
     * every other element on the list.
     * Space complexity is O(1) because lista is modified in-place and just an extra element for the list is created.
     */
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {
        if( lista.size() == 0)//if lista is empty, add nuevo
            lista.add(nuevo);
        IteradorLista<Integer> it = lista.iteradorLista();
        it.start();
        int previous = (int)it.next();//set previous to the first element of the list
        int count = 1;//set count to 1 to track the index of the iterator
        if( nuevo <= previous ){// if nuevo is less than or equal to the first element add nuevo at the beginning
            lista.agregaInicio(nuevo);
            return lista;
        }
        while( it.hasNext() ){
            //move to the next element in the list
            int current = (int)it.next();
            count++;
            if( previous <= nuevo && nuevo <= current){
                //add nuevo before the position that iterator is pointing to
                lista.insert(count-1, nuevo);
                return lista;
            }
            previous = current;
        }
        return null;
    }

    /**
     * Performs union operation, lista1 is modified so it contains all the elements 
     * from lista1 and lista2 without repeated elements. 
     * if lista1 contains repeated elements those elements will stay untouched, so in this case
     * the result will contain the repeated elements from lista1.
     * lista2 is not modified.
     * Time complexity: O(n*m) where n and m are the sizes of lista1 and lista2 respectively. This is
     * because for every element x in lista2, x is searched in lista1 to add it if is not there,
     * this can be done in O(m) time, since we do this for every element in lista2, then
     * time execution is O(n*m).
     * Space complexity: O(n+m) since the resulting list has at most n+m elements.
     * Respuesta a la pregunta de la práctica.
     * 
     * Podemos mejorar el tiempo de ejecución para que sea O( n + m )
     * Para ello podemos hacer uso de un set "s". Podemos recorrer las dos listas
     * y añadir los elementos de cada una a s, lo cual se puede hacer en tiempo O(n + m) ya que añadir un elemento 
     * a un set es O(1). Como s es un set no tendremos elementos repetidos, luego
     * solo tenemos que construir una lista añadiendo cada elemento de s, lo cuál tambien se hace en O(n+m).
     */
    public static void Union(Lista<Integer> lista1,Lista<Integer> lista2) {
        IteradorLista<Integer> it2 = lista2.iteradorLista();
        IteradorLista<Integer> it1 = lista1.iteradorLista();
        it2.start();
        while(it2.hasNext()){//for every element on lista2
            int current = (int)it2.next();
            it1.start();
            boolean repeatedElement = false;
            while(it1.hasNext()){//look if current element of lista2 is already in lista1
                if( current == (int)it1.next() ){
                    repeatedElement = true;
                    break;
                }
            }
            if(!repeatedElement){//if current element is not in lista 1, add it
                lista1.add(current);
            }
        }
        return ;
    }

    /**
     * Performs intersection operation and lista is modified so it contains the result.
     * Calling this method will modify lista so it contains all the elements in both
     * lista and lista2 without repeated elements.
     * lista2 is not modified.
     * 
     * Time complexity: O(n+m) where n is the size of the smallest list and m is the size of the biggest list. This
     * is because the method traverse the smallest list just once to create a set with its elements, since
     * adding elements to a set is O(1), creating the set is O(n). Then every element in the biggest list is 
     * searched in the set in O(1) and if is in the set it is added to the resulting list in O(1),
     * so this takes O(m). Therefore time complexity is O(n+m).
     * Space complexity: O(n) where n is the size of the smallest list. This is because a set with the elements
     * of the smallest list is created to get rid of repeated elements.The resulting list has at most
     * the size of the smallest of both lists.
     * 
     * 
     * Pregunta a la respuesta de la practica:
     * No sé como se podría mejorar el algoritmo, la practica pide un tiempo O(n*m) el algoritmo que implementé
     * es O(n+m) que es mejor, el algoritmo O(n*m) no se me ocurrió.
     * @param lista
     * @param lista2
     */
    private static void Interseccion(Lista<Integer> lista, Lista<Integer> lista2){
        Set<Integer> s = new HashSet<>();
        IteradorLista<Integer> itBigger;
        IteradorLista<Integer> itSmaller;
        //set the iterators of the smallest and biggest list
        if(lista.size() <= lista2.size()){
            itBigger = lista2.iteradorLista();
            itSmaller = lista.iteradorLista();
        }
        else{
            itBigger = lista.iteradorLista();
            itSmaller = lista2.iteradorLista();
        }
        //create a set with the elements of the smallest list
        itSmaller.start();
        while(itSmaller.hasNext()){
            s.add((int)itSmaller.next());
        }
        //look which elements of the biggest list are in the smallest list
        itBigger.start();
        Lista<Integer> result = new Lista<Integer>();
        while(itBigger.hasNext()){
            int current = (int)itBigger.next();
            if(s.contains(current)){
                result.add(current);//add the common elements to the resulting list
            }
        }
        //save resulting list in lista
        lista.empty();
        lista.append(result);
        return;
    }



    public static void main(String[] args) {
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();
        
        
        // Tests toString
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }
        
        String test = "0 -> 1 -> 2 -> 3 -> 4 -> 5";
        if (!primera.toString().equals(test)) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }
            
        // Tests Reverse
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();

        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }
      
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");    
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        // Tests Append
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());

        
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        // Tests IndexOf
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        // Tests Insert
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            
        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);

        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);

        }

        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        // Tests Mezcla Alternada
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }   
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }

        }
        for (int i = 0; i <= 11; i++) {
            
                tercera.add(i);

        }


        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }


        // Tests Agrega Ordenado
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);
        
        
        tercera = AgregaOrdenado(primera,9);
        if (!tercera.toString().equals(segunda.toString())) {
            System.out.println("1 el agregaOrdenado no funciona!");
        }
        
        // Tests Union
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);

        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union no funciona!");
        }
        
        // Tests interseccion
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Interseccion(primera, segunda);

        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }
        
        



    }   
   

    


}
