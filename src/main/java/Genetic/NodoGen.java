package Genetic;

import java.util.ArrayList;
import java.util.List;

public class NodoGen {
    public int numNodo;
    public float fitness;
    public List<List<String>> cuerpoNodo = new ArrayList<>();

    @Override
    public String toString() {
        return "NodoGen{" +
                "numNodo=" + numNodo +
                ", fitness=" + fitness +
                ", cuerpoNodo=" + cuerpoNodo +
                '}';
    }
}
