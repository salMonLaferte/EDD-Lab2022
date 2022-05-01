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

    private void buildUnsorted( Lista<T> lista) {
        //buildPreOrder(0, 3 , raiz , lista.iteradorLista());
        //buildEmptyTree(raiz, 2);
        //fillPreOrder(raiz, lista.iteradorLista());
        buildPreOrder(2, raiz, lista.iteradorLista());
    }

    /*private void buildPreOrder(int depth, Vertice root, IteradorLista<T> it){
        if(depth == 0)
            return;
        if(it.hasNext())
            root.izquierdo = new Vertice(it.next());
        buildEmptyTree(root.izquierdo, depth-1);
        if(it.hasNext())
            root.derecho = new Vertice(it.next());
        buildEmptyTree(root.derecho, depth-1);  
    }*/

    private void buildPreOrder(int depth, Vertice raiz, IteradorLista<T> it){
        //at max depth set value for this node
        if(depth == 0){
            if(it.hasNext())
                raiz.elemento = it.next();
            return;
        }
         //build left tree
        Vertice iz = new Vertice(null);
        raiz.izquierdo = iz;
        buildPreOrder(depth-1, raiz.izquierdo, it);
        if(it.hasNext()){
            //set value for this node
            raiz.elemento = it.next();
            //build right tree
            Vertice der = new Vertice(null);
            raiz.derecho = der;
            buildPreOrder(depth-1, raiz.derecho, it);
        }
    }


    
    private void buildEmptyTree(Vertice root, int depth){
        if(depth == 0)
            return;
        root.izquierdo = new Vertice(null);
        buildEmptyTree(root.izquierdo, depth-1);
        root.derecho = new Vertice(null);
        buildEmptyTree(root.derecho, depth-1);
    }

    private void fillPreOrder(Vertice root, IteradorLista<T> it){
        if(root.hayIzquierdo())
            fillPreOrder(root.izquierdo, it);
        if(it.hasNext())
            root.elemento = it.next();
        if(root.hayDerecho())
            fillPreOrder(root.derecho, it);
    }

    private void buildSorted( Lista<T> lista) {
 
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
