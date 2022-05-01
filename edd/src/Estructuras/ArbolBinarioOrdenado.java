package edd.src.Estructuras;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T> {
    private class Iterador implements Iterator<T>{
        private Pila<Vertice> pila;

        public Iterador(){
            pila = new Pila<Vertice>();
            Vertice p = raiz;
            while (p!= null) {
                pila.push(p);
                p = p.izquierdo;    
            }
        }
        // falta hasNext
        public T next(){
            if(pila.isEmpty()){
                throw new NoSuchElementException("vacio");
            }
            Vertice v = pila.pop();
            if(v.derecho != null){
                Vertice u = v.derecho;
                while (u!=null) {
                    pila.push(u);
                    u=u.izquierdo;
                }
            }

            return v.elemento;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }



    }

    public ArbolBinarioOrdenado(Lista<T> lista, boolean isSorted ){
        raiz = new Vertice(null);
        if (isSorted) {
            buildSorted(lista);
        }
        else{
            buildUnsorted(lista);
        }

    }

    /**
     * Construye el arbol a partir de una lista no ordenada en O(n logn)
     * @param lista
     */
    private void buildUnsorted( Lista<T> lista) {
        Comparator<T> comp = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
            
        };
        Lista<T> sorted = lista.mergeSort(comp);
        buildSorted(sorted);
    }

    /**
     * Construye el arbol a partir de una lista ordenada
     * @param lista
     */
    private void buildSorted( Lista<T> lista) {
        raiz = buildFromTo(0, lista.size()-1, lista.iteradorLista());
    }

    /**
     * Construye el arbol de manera in-order con los valores de la lista a la cual apunta it
     */
    private Vertice buildFromTo(int start, int end, IteradorLista<T> it){
        if(start > end)
            return null;
        int mid = (start+ end)/2;
        Vertice izq = buildFromTo(start, mid-1, it);
        Vertice v = new Vertice(it.next());
        v.izquierdo = izq;
        v.derecho = buildFromTo(mid+1, end, it);
        return v;
    }

    
    /**
     * Busca un elemento el el arbol
     * @param elemento
     * @param root
     * @return true si el elemento está en el arbol
     */
    public boolean search(T elemento, Vertice root){
        if(root == null)
            return false;
        if(elemento.compareTo(root.elemento) == 0)
            return true;
        if(elemento.compareTo(root.elemento) > 0 )
            return search(elemento, root.derecho);
        else
            return search(elemento, root.izquierdo);
    }



    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    @Override
    public void add(T elemento) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean delete(T elemento) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString(){
        return inOrderTraversal( raiz);
    }
    
    /**
     * Funcion recursiva auxiliar que regresa un string con el recorrido in-order del arbol
     * @param root
     * @return
     */
    private String inOrderTraversal( Vertice root){
        if(root == null)
            return "";
        String res = "";
        res += inOrderTraversal( root.izquierdo);
        res += root.elemento + ", ";
        res += inOrderTraversal( root.derecho);
        
        return res;
    }

}
