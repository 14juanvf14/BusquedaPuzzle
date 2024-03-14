import AAsterisk.AAsterisk;
import AAsterisk.NodoAA;
import DepthFirstSearch.DepthFirst;
import DepthFirstSearch.NodoBPP;
import Genetic.Genetic;
import Genetic.NodoGen;
import HillClimbSearch.HillClimb;
import HillClimbSearch.NodoHC;
import View.PrincipalLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new PrincipalLayout();
            frame.setSize(800,400);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
        new PrincipalLayout();

        /**
         *



         NodoGen nodoGen = new NodoGen();
         nodoGen.numNodo = 0;
         nodoGen.fitness = 0;

         nodoGen.cuerpoNodo.add(List.of("2", "0", "3"));
         nodoGen.cuerpoNodo.add(List.of("1", "8", "4"));
         nodoGen.cuerpoNodo.add(List.of("7", "6", "5"));

         List<List<String>> finalState = new ArrayList<>();
         finalState.add(List.of("0", "2", "3"));
         finalState.add(List.of("1", "8", "4"));
         finalState.add(List.of("7", "6", "5"));

         NodoHC nodoHC = new NodoHC();
         NodoBPP nodoBPP = new NodoBPP();
         NodoAA nodoAA = new NodoAA();

         nodoAA.numNodo=1;
         nodoAA.heuristica=0;
         nodoAA.nodoPadre=0;
         nodoAA.operacion="NO";
         nodoAA.distanciaAcumulada=0;
         nodoAA.costoTotal=0;
         nodoAA.cuerpoNodo.add(List.of("2", "0", "3"));
         nodoAA.cuerpoNodo.add(List.of("1", "8", "4"));
         nodoAA.cuerpoNodo.add(List.of("7", "6", "5"));

         nodoHC.numNodo=1;
         nodoHC.heuristica=0;
         nodoHC.nodoPadre=0;
         nodoHC.operacion="NO";
         nodoHC.cuerpoNodo.add(List.of("2", "0", "3"));
         nodoHC.cuerpoNodo.add(List.of("1", "8", "4"));
         nodoHC.cuerpoNodo.add(List.of("7", "6", "5"));

         nodoBPP.numNodo=1;
         nodoBPP.nivel=0;
         nodoBPP.nodoPadre=0;
         nodoBPP.operacion="NO";
         nodoBPP.cuerpoNodo.add(List.of("2", "0", "3"));
         nodoBPP.cuerpoNodo.add(List.of("1", "8", "4"));
         nodoBPP.cuerpoNodo.add(List.of("7", "6", "5"));


         DepthFirst bpp = new DepthFirst();
         HillClimb hc = new HillClimb();
         AAsterisk aAsterisk = new AAsterisk();
         aAsterisk.aAsterisk(nodoAA, finalState);
         bpp.profundidadPrimero(nodoBPP, finalState, 5);
         hc.hillClimb(nodoHC, finalState);

         Genetic genetic = new Genetic();
         genetic.geneticAlg(nodoGen,finalState,2, 4);
        */
    }
}



