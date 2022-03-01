package Clases;

//import java.util.Comparator;

public interface Collection<T> {

    // agregar
    public void add(T elemento);

    // eliminar
    public boolean delete(T object);

    // pop
    public T pop();

    // size
    public int size();

    // contiene
    public boolean contains(T elemento);

    // empty
    public void empty();

    // is empty
    public boolean isEmpty();
    
    // equals
    public boolean equals(Collection<T> object);

    // append
    public void append(Collection<T> elemento);

    // to string
    public String toString();
    
    // reverse
    public void reverse();

    // clone
    public Collection<T> clone();

    // Sort
    // TODO Interfaz ...Comparator
    // public void sort(Comparator<T> c)


}
