package AAsterisk;

import java.util.ArrayList;
import java.util.List;

public class NodoAA {
    public int numNodo; // Identificador del DepthFirstSearch.NodoBPP
    public int nodoPadre; // Nodo del padre, si es el 0 es la raiz
    public float heuristica; // Profundidad del nodo, 0 si es el padre
    public float distanciaAcumulada;

    public float costoTotal;

    public String operacion; // NO para el nodo inicial, IZQUIERDA, ARRIBA, DERECHA, ABAJO para demas nodos

    public List<List<String>> cuerpoNodo = new ArrayList<>();
    // CuerpoNodo contiene 3 listas, es una lista de listas
    // Ejemplo de puzle
    // (1,2,3)
    // (4,5,6)
    // (7,8,0)


    public float getCostoTotal() {
        return costoTotal;
    }

    @Override
    public String toString() {
        return "A*.NodoAA{" +
                "numNodo=" + numNodo +
                ", nodoPadre=" + nodoPadre +
                ", heuristica=" + heuristica +
                ", distanciaAcumulada=" + distanciaAcumulada +
                ", costoTotal=" + costoTotal +
                ", operacion='" + operacion + '\'' +
                ", cuerpoNodo=" + cuerpoNodo +
                '}';
    }
}
