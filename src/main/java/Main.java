import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<NodoBPP> ABIERTO = new LinkedList<>();
        LinkedList<NodoBPP> CERRADO = new LinkedList<>();

        NodoBPP nodoBPPInicial = new NodoBPP();
        nodoBPPInicial.numNodo = 1;
        nodoBPPInicial.nodoPadre = 0;
        nodoBPPInicial.operacion = "NO";
        nodoBPPInicial.nivel = 0;

        nodoBPPInicial.cuerpoNodo.add(List.of("2", "8", "3"));
        nodoBPPInicial.cuerpoNodo.add(List.of("1", "0", "4"));
        nodoBPPInicial.cuerpoNodo.add(List.of("7", "6", "5"));

        List<List<String>> finalState = new ArrayList<>();
        finalState.add(List.of("1", "2", "3"));
        finalState.add(List.of("8", "0", "4"));
        finalState.add(List.of("7", "6", "5"));

        DepthFirst bpp = new DepthFirst();
    }
}



