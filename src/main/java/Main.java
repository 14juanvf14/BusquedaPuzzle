import DepthFirstSearch.DepthFirst;
import DepthFirstSearch.NodoBPP;
import HillClimbSearch.HillClimb;
import HillClimbSearch.NodoHC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<NodoBPP> ABIERTO = new LinkedList<>();
        LinkedList<NodoBPP> CERRADO = new LinkedList<>();

        NodoHC nodoHC = new NodoHC();
        nodoHC.numNodo=1;
        nodoHC.heuristica=0;
        nodoHC.nodoPadre=0;
        nodoHC.operacion="NO";
        nodoHC.cuerpoNodo.add(List.of("1", "2", "3"));
        nodoHC.cuerpoNodo.add(List.of("8", "0", "4"));
        nodoHC.cuerpoNodo.add(List.of("7", "6", "5"));



        List<List<String>> finalState = new ArrayList<>();
        finalState.add(List.of("6", "8", "3"));
        finalState.add(List.of("2", "1", "4"));
        finalState.add(List.of("7", "0", "5"));

        DepthFirst bpp = new DepthFirst();
        HillClimb hc = new HillClimb();
        hc.hillClimb(nodoHC, finalState);

    }
}



