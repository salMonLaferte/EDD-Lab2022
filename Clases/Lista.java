package Clases;

// iterador
//next

public class Lista<T> {

    // Clase Nodo

    // Iterador

    
    // Lo de la interfaz
    
    /**
     * Agrega un elemento a la lista.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void add(T elemento){

    }

    /**
     * Elimina un elemento de la lista.
     * 
     * @param elemento el elemento a eliminar.
     */
    public boolean delete(T elemento){

    }

    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {

    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
    
    }



    /**
     * Regresa un elemento de la lista.
     * y lo elimina.
     * 
     * @return El elemento a sacar.
     */
    public T pop(){

    }

    /**
     * Regresa el número de elementos en la lista.
     * 
     * @return el número de elementos en la lista.
     */
    public int size(){

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

    }

    /**
     * Vacía la lista.
     * 
     */
    public void empty(){

    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){

    }

    

    /**
     * Regresa una copia de la lista.
     * 
     * @return una copia de la lista.
     */
    public Collection<T> clone() {

    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion){

    }


    
    /**
     * Metodo que invierte el orden de la lista .
     * 
     */
    public void reverse() {

    }

    /**
     * Regresa una representación en cadena de la coleccion.
     * 
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     */
    public String toString(){

    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     * 
     */
    public void append(Lista<T> elemento) {

    }




}
