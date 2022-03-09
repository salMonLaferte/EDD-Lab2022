import java.util.Currency;
import java.util.Iterator;
import java.util.NoSuchElementException;
// iterador
//next
import java.util.Stack;

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
     * Metodo que invierte el orden de la lista .
     * 
     */
    public void reverse() {
        // Tu codigo aqui
        if( cabeza == null)
            return;
        //Put the list elements on a stack so the first element is at the bottom and the last element is at the top
        Stack<Nodo> stack = new Stack<>();
        Nodo current = cabeza;
        while(current !=null){
            stack.push(current);
            current = current.siguiente;
        }
        //Pop up the top element of the stack, which now is the head of the list
        cabeza = stack.peek();
        current = cabeza;
        stack.pop();
        while( !stack.empty() ){
            //pop up the top element of the stack and set siguiente reference to the element at the top of the remaining stack 
            current.siguiente = stack.peek();
            current = current.siguiente;
            stack.pop();
        }
        //set last element in the list to null, and ultimo to the last element of the list
        current.siguiente = null;
        ultimo = current;
    }

    /**
     * Regresa una representación en cadena de la coleccion.
     * 
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
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
     * 
     */
    public void append(Lista<T> lista) {
        // Tu codigo aqui
        if(lista == null || lista.longi == 0)//if list is null or empty don't do anything
            return; 
        ultimo.siguiente = lista.cabeza;
        ultimo = lista.ultimo;
        longi += lista.longi;
        return ;
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * 
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
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
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

    // Tu comentario
    /**
     * Inserts all the elements of lista alternating between elements of this list and elements 
     * of lista until is possible. 
     * If lista size is bigger, just the first longi elements are alternated and the rest of the elements in lista
     * are appended to this list.
     * If lista size is shorter, just the first lista.longi are alternated and the rest of the elements stay in the same
     * order.
     * @param lista
     */
    public void mezclaAlternada(Lista<T> lista){
        if(lista== null)
            return;
        Nodo current = cabeza;
        while(lista.cabeza != null || current == null){//if lista cabeza is null we got out of nodes to add
            Nodo remainingRefA = current.siguiente;//keep a reference from the rest of THIS list
            current.siguiente = lista.cabeza;//point current element of THIS list to the cabeza element of lista
            Nodo remainingRefB = lista.cabeza.siguiente;//keep a reference from the rest of lista
            lista.cabeza.siguiente = remainingRefA;//point lista.cabeza to the rest of THIS list
            if(remainingRefA == null)
                ultimo = current;
            lista.cabeza = remainingRefB;//update cabeza to the next element
            lista.longi--;//update size of lista
            current = remainingRefA;//update current to the next element of THIS lista
        }
        append(lista);
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
