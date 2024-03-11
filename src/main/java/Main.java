import AAsterisk.AAsterisk;
import AAsterisk.NodoAA;
import DepthFirstSearch.DepthFirst;
import DepthFirstSearch.NodoBPP;
import HillClimbSearch.HillClimb;
import HillClimbSearch.NodoHC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        NodoHC nodoHC = new NodoHC();
        NodoBPP nodoBPP = new NodoBPP();
        NodoAA nodoAA = new NodoAA();

        nodoAA.numNodo=1;
        nodoAA.heuristica=0;
        nodoAA.nodoPadre=0;
        nodoAA.operacion="NO";
        nodoAA.distanciaAcumulada=0;
        nodoAA.costoTotal=0;
        nodoAA.cuerpoNodo.add(List.of("2", "8", "3"));
        nodoAA.cuerpoNodo.add(List.of("1", "0", "4"));
        nodoAA.cuerpoNodo.add(List.of("7", "6", "5"));

        nodoHC.numNodo=1;
        nodoHC.heuristica=0;
        nodoHC.nodoPadre=0;
        nodoHC.operacion="NO";
        nodoHC.cuerpoNodo.add(List.of("2", "8", "3"));
        nodoHC.cuerpoNodo.add(List.of("1", "0", "4"));
        nodoHC.cuerpoNodo.add(List.of("7", "6", "5"));

        nodoBPP.numNodo=1;
        nodoBPP.nivel=0;
        nodoBPP.nodoPadre=0;
        nodoBPP.operacion="NO";
        nodoBPP.cuerpoNodo.add(List.of("2", "8", "3"));
        nodoBPP.cuerpoNodo.add(List.of("1", "0", "4"));
        nodoBPP.cuerpoNodo.add(List.of("7", "6", "5"));


        List<List<String>> finalState = new ArrayList<>();
        finalState.add(List.of("1", "2", "3"));
        finalState.add(List.of("8", "0", "4"));
        finalState.add(List.of("7", "6", "5"));

        DepthFirst bpp = new DepthFirst();
        HillClimb hc = new HillClimb();
        AAsterisk aAsterisk = new AAsterisk();
        aAsterisk.aAsterisk(nodoAA, finalState);
        hc.hillClimb(nodoHC, finalState);
        bpp.profundidadPrimero(nodoBPP, finalState, 4);

    }
}



