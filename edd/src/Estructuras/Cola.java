package edd.src.Estructuras;

public class Cola<T> extends PushPop<T>{
    @Override
    /**
     * Agregar al final
     */
    public void push(T elemento) {
        Nodo nuevoElemento = new Nodo(elemento);
        nuevoElemento.siguiente = null;
        //Si la cola esta vacia entonces la cabeza es el nuevo elemento, si no se inserta despues de ultimo.
        if(isEmpty()){
            cabeza = nuevoElemento;
        }else{
            ultimo.siguiente = nuevoElemento;
        }
        //El nuevo elemento es el ùltimo y la cola aumenta su tamaño 
        ultimo = nuevoElemento;
        longi++;
    }

    @Override
    public PushPop<T> clone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
