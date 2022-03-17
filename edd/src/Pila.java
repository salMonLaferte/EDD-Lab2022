package edd.src;

public class Pila<T> extends PushPop<T>{
    
    
    // Agregar al inicio.
    public void push(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("");
        }
        Nodo aux = new Nodo(elemento);
        if(isEmpty()){
            this.cabeza=ultimo=aux;
            longi++;
            return ;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi ++;

    }



    /**
     * Regresa un clon de la estructura.
     * 
     * @return un clon de la estructura.
     */
    public Pila<T> clone(){
        Pila<T> nueva = new Pila<T>();
        nueva.cabeza = this.cabeza;
        return nueva;
    }

    public String toString(){

    }


}
