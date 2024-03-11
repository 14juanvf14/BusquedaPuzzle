package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class NodoBPP {
    public int numNodo; // Identificador del DepthFirstSearch.NodoBPP
    public int nodoPadre; // DepthFirstSearch.NodoBPP del padre, si es el 0 es la raiz
    public int nivel; // Profundidad del nodo, 0 si es el padre
    public String operacion; // NO para el nodo inicial, IZQUIERDA, ARRIBA, DERECHA, ABAJO para demas nodos

    public List<List<String>> cuerpoNodo = new ArrayList<>();
    // CuerpoNodo contiene 3 listas, es una lista de listas
    // Ejemplo de puzle
    // (1,2,3)
    // (4,5,6)
    // (7,8,0)

    @Override
    public String toString() {
        return "DepthFirstSearch.NodoBPP{" +
                "numNodo=" + numNodo +
                ", nodoPadre=" + nodoPadre +
                ", operacion='" + operacion + '\'' +
                ", cuerpoNodo=" + cuerpoNodo +
                '}';
    }
}
