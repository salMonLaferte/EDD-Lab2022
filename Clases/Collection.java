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

    // empty
    public void empty();

    // append
    public void append(Collection<T> elemento);

    // equals
    public boolean equals(Collection<T> object);

    // to string
    public String toString();

    // is empty
    public boolean isEmpty();

    // Sort
    // TODO Interfaz ...Comparator
    //public void sort(Comparator<T> c);

    // contiene
    public boolean contains(T elemento);

    // reverse
    public void reverse();

    // clone
    public Collection<T> clone();

    //Todos van a ser public?


}
