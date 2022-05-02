package edd.src.Estructuras;

import java.util.Comparator;
import java.util.Iterator;

public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {

    public ArbolBinarioBusqueda(Lista<T> lista, boolean isSorted ){
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
     * Busca revursivamente un elemento el el arbol binario de busqueda
     * @param elemento
     * @param root
     * @return true si el elemento está en el arbol
     */
    private boolean search(T elemento, Vertice root){
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
     * Busca un elemento en el arbol
     * @param elemento
     * @return true si el elemento está en el arbol
     */
    public boolean search(T elemento){
        return search(elemento, raiz);
    }

    /**
     * Genera el arbol binario de busqueda a partir de un arbol binario
     * @param a
     */
    public void convertBST(ArbolBinario<T> a){
        Lista<T> l = new Lista<T>();
        getInOrderList(a.raiz, l);//guarda el arbol en una lista
        buildUnsorted(l);//construye el arbol a partir de la lista no ordenada
    }

    /**
     * Guarda los nodos en la lista list a partir de un recorrido in-order del arbol
     * @param root
     * @param list
     */
    public void getInOrderList(Vertice root, Lista<T> list){
        if( root.hayIzquierdo()){
            getInOrderList(root.izquierdo, list );
        }
        list.add(root.elemento);
        if(root.hayDerecho()){
            getInOrderList(root.derecho, list);
        }
    }

    /**
     * Inserta un elemento en el arbol de busqueda binaria
     * @param elemento
     */
    public void insert(T elemento){
        Vertice current = raiz;
        while(true){
            if(elemento.compareTo(current.elemento) == 0)
                return;
            if(elemento.compareTo(current.elemento) > 0){
                if(current.derecho == null){
                    Vertice toInsert = new Vertice(elemento);
                    current.derecho = toInsert;
                    return;
                }else{
                    current = current.derecho;
                }
            }
            else{
                if(current.izquierdo == null){
                    Vertice toInsert = new Vertice(elemento);
                    current.izquierdo = toInsert;
                    return;
                }else{
                    current = current.izquierdo;
                }
            }
        }
    }

    public void balance(Vertice root){
        
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

    @Override
    public String toString(){
        return inOrderTraversal( raiz);
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(T elemento) {
        insert(elemento);
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
    
}
