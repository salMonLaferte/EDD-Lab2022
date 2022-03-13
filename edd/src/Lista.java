import java.util.Iterator;
import java.util.NoSuchElementException;
// iterador
//next


public class Lista<T> implements Collection<T> {

    // Clase Nodo
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }
    }

    // Iterador
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente; 

        public Iterador(){
            siguiente = cabeza;
        }

        @Override public boolean hasNext(){
            return siguiente != null;
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;
            
            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;

        }
        
        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }
        
        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start(){
            this.anterior = null;
            this.siguiente = cabeza;
        }
        
        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }
        
    }

    private Nodo cabeza;
    private Nodo ultimo;
    private int longi;
    
    /**
     * Agrega un elemento a la lista.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }
    
    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.ultimo = nuevo;
        }
        else{
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n !=null){
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista.
     * 
     * @param elemento el elemento a eliminar.
     */ 
    public boolean delete(T elemento){
        if(elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);
        if(n==null){
            return false;
        }
        if(longi == 1){
            empty();
            return true;
        }
        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longi --;
            return true;
        }
        if (n == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            longi --;
            return true;
        }
        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longi --;
        return true;
    }    



    /**
     * Regresa un elemento de la lista. (Ultimo)
     * y lo elimina.
     * 
     * @return El elemento a sacar.
     */
    public T pop(){
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi --;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     * 
     * @return el número de elementos en la lista.
     */
    public int size(){
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     * 
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento){
        if(buscaElemento(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     * 
     */
    public void empty(){
        cabeza =ultimo= null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return longi == 0;
    }

    

    /**
     * Regresa una copia de la lista.
     * 
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion){
        // lo vemos en clase
        if(coleccion instanceof Lista) {
            return true;
        }
        return false;
    }


    
    /**
     * Revert list order.
     * Time complexity: O(n) because the list is traversed once and changing node.siguiente
     * reference takes O(1)
     * Space complexity: O(1) because list is modified in-place and no new nodes are
     * created.
     */
    public void reverse() {
        if( longi== 0 )
            return;
        ultimo = cabeza;
        Nodo previous = cabeza;
        Nodo current = cabeza.siguiente;
        cabeza.siguiente = null;
        while(current != null ){
            Nodo next = current.siguiente;
            current.siguiente = previous;
            previous = current;
            if(next == null)
                cabeza = current;
            current = next;
        }
    }

    /**
     * Regresa una representación en cadena de la coleccion.
     * 
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     * Time complexity: O(n) since list is traversed once as resulting string is created.
     * Space complexity: O(n) since resulting string size depends of the number of elements in the list.
     */
    public String toString(){
        // Tu codigo aqui
        String s = "";
        if(cabeza == null)//if list is empty print an empty string
            return s;
        Nodo current = cabeza;
        //Traverse the list until penultimate element and write every element to s
        while(current.siguiente != null){
            s += current.elemento.toString() + " -> "; 
            current = current.siguiente;
        }
        s+= current.elemento.toString(); //last element is added to s without an arrow
        return s;
    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     * Time complexity: O(1)
     * Space complexity: O(1) since no new nodes or lists are created.
     */
    public void append(Lista<T> lista) {
        // Tu codigo aqui
        if(lista == null || lista.longi == 0)//if lista is null or empty don't do anything
            return; 
        if(longi == 0)//if this list is empty point cabeza to lista.cabeza else just append the list at the end of this list
            cabeza = lista.cabeza;
        else
            ultimo.siguiente = lista.cabeza;
        ultimo = lista.ultimo;
        longi += lista.longi;
        return ;
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * Time complexity: O(n) since list is traversed once to find the element.
     * Space complexity: O(1) since no new nodes or lists are created.
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public int indexOf(T elemento) {
        // Tu codigo aqui
        if(elemento == null){
            throw new IllegalArgumentException();
        }
        if(cabeza == null)//if list is empty return -1
            return -1;
        Nodo current = cabeza;
        int count = 0;
        while(current !=null){//traverse the list
            //if  current element is equal to elemento, return the index
            if(elemento.equals(current.elemento))
                return count;
            //else move to the next one
            count++;
            current = current.siguiente;
        }
        return -1;//if element is not found return -1
    }
    
    /**
     * Inserta un elemento en un índice explícito.
     * Time complexity: O(n) since list is traversed up to the index of insertion which is n in worst case.
     * Space complexity: O(1) since just a new node is created to insert it in the list.
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        // Tu codigo aqui
        if(elemento==null)
            throw new IllegalArgumentException();
        Nodo node = new Nodo(elemento);
        if(longi == 0){//handle the case when the list is empty
            cabeza = node;
            ultimo = node;
            node.siguiente = null;
        }
        else if( i <= 0){//insert element at the beginning
            node.siguiente = cabeza;
            cabeza = node;
        }
        else if( i >= longi ){//insert element at the end
            ultimo.siguiente = node;
            ultimo = node;
            node.siguiente = null;
        }else{
            int count = 0;
            Nodo current = cabeza;
            while ( count < i-1){//get the reference of the node prior to the position of insertion
                current = current.siguiente;
                count++;
            }
            Nodo remaining = current.siguiente;//keep a reference to the rest of the list
            //insert node
            current.siguiente = node;
            node.siguiente = remaining;
        }
        longi++;
        return ;
    }

   
    /**
     * Inserts all the elements of lista alternating between elements of this list and elements 
     * of lista until is possible. 
     * If lista size is bigger, just the first THIS.size elements are alternated and the rest of the elements in lista
     * are appended to this list.
     * If lista size is shorter, just the first lista.size are alternated and the rest of the elements of this list stay in the same
     * order.
     * Calling this method will modify lista. 
     * If lista size is shorter  or equal than THIS list calling this method will left lista empty.
     * If lista size is bigger than THIS list calling this method will delete the first (lista.size - THIS.size) elements
     * from lista.
     * 
     * Time complexity: O(m) with m being the size of lista. Since method traverse lista at the same time that 
     * traverse THIS list, the element insertion  between elements of this THIS list takes O(1) time and this is done
     * m times, therefore complexity is O(m). The append of the rest of the elements takes O(1), so in total we have O(m).
     * Space complexity: O(1) since THIS list and lista are modified in-place and just some extra nodes are created to maintain references
     * @param lista
     */
    public void mezclaAlternada(Lista<T> lista){
        if(lista== null)
            return;
        Nodo current = cabeza;
        while(lista.cabeza != null && current != null){//if lista cabeza is null we got out of nodes to add, if current is null we cant alternate elements anymore
            Nodo remainingRefA = current.siguiente;//keep a reference from the rest of THIS list
            current.siguiente = lista.cabeza;//point current element of THIS list to the cabeza element of lista
            Nodo remainingRefB = lista.cabeza.siguiente;//keep a reference from the rest of lista
            lista.cabeza.siguiente = remainingRefA;//point lista.cabeza to the rest of THIS list
            if(remainingRefA == null)//if the remaining is null then lista.cabeza is the last element of the list
                ultimo = lista.cabeza;
            lista.cabeza = remainingRefB;//update cabeza to the next element
            lista.longi--;//update size of lista
            current = remainingRefA;//update current to the next element of THIS lista
        }
        append(lista);//append the rest of the elements
        return;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
