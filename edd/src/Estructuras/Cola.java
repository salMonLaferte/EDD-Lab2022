package edd.src.Estructuras;

public class Cola<T> extends PushPop<T>{
    @Override
    public void push(T elemento) {
        Nodo nuevoElemento = new Nodo(elemento);
        nuevoElemento.siguiente = null;
        //Si la cola esta vacia entonces la cabeza es el nuevo elemento, si no se inserta despues de ultimo.
        if(this.isEmpty()){
            cabeza = nuevoElemento;
        }else{
            ultimo.siguiente = nuevoElemento;
        }
        //El nuevo elemento es el ùltimo y la cola aumenta su tamaño 
        ultimo = nuevoElemento;
        longi++;
    }

    @Override
    public Cola<T> clone() {
        Cola<T> nueva = new Cola<T>();
        Cola<T> aux = new Cola<T>();
        if (this.isEmpty()) {
            return nueva;
        }
        Nodo actual = this.cabeza;
        while(actual != null){
            aux.push(actual.elemento);
            actual = actual.siguiente;
        }
        return nueva;  
    }

    @Override
    public String toString() {
        if(this.isEmpty()){
            return "";
        }
        //Crea una pila y mete las representaciones de los elementos en cadenas de caracteres de la lista.
        Pila<String> pila = new Pila<String>();
        Nodo actual = cabeza;
        while(actual != null){
            pila.push(actual.toString());
            actual = actual.siguiente;
        }
        //Va sacando los elementos en cadenas de caracteres para guardarlos en la cadena resultado con el orden inverso al de la lista.
        String resultado = "";
        while(pila.size() > 1){
            resultado += pila.peek() + ", ";
            pila.pop(); 
        }
        resultado += pila.peek();
        return resultado;
    }
    
}
