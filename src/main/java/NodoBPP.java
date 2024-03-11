import java.util.ArrayList;
import java.util.List;

public class NodoBPP {
    int numNodo; // Identificador del NodoBPP
    int nodoPadre; // NodoBPP del padre, si es el 0 es la raiz
    int nivel; // Profundidad del nodo, 0 si es el padre
    String operacion; // NO para el nodo inicial, IZQUIERDA, ARRIBA, DERECHA, ABAJO para demas nodos

    List<List<String>> cuerpoNodo = new ArrayList<>();
    // CuerpoNodo contiene 3 listas, es una lista de listas
    // Ejemplo de puzle
    // (1,2,3)
    // (4,5,6)
    // (7,8,0)

    @Override
    public String toString() {
        return "NodoBPP{" +
                "numNodo=" + numNodo +
                ", nodoPadre=" + nodoPadre +
                ", operacion='" + operacion + '\'' +
                ", cuerpoNodo=" + cuerpoNodo +
                '}';
    }
}
