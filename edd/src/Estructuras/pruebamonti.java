package edd.src.Estructuras;

import java.util.Arrays;
import java.util.Comparator;

public class pruebamonti{

    public static void main(String[] args) {
        Pokemon poke1 = new Pokemon("a", "Planta", 1, 110);
        Pokemon poke2 = new Pokemon("b", "Fuego", 2, 100);
        Pokemon poke3 = new Pokemon("c", "Electrico", 3, 100);
        Pokemon poke4 = new Pokemon("d", "Agua", 4, 100);
        Pokemon poke5 = new Pokemon("e", "Volador", 5, 100);
        
        MonticuloMinimo<Pokemon> monticulo = new MonticuloMinimo<Pokemon>();
        // Agregamos elementos
        monticulo.add(poke1);
        monticulo.add(poke2);
        monticulo.add(poke3);
        monticulo.add(poke4);
        monticulo.add(poke5);
    
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        System.out.println(monticulo.contains(poke5));
        
        monticulo.empty();
        System.out.println("########");

        System.out.println(monticulo);
        System.out.println(monticulo.size());



    }


}
