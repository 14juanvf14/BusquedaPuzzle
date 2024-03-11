package DepthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DepthFirst {
    LinkedList<NodoBPP> ABIERTO = new LinkedList<>();
    LinkedList<NodoBPP> CERRADO = new LinkedList<>();

    public boolean profundidadPrimero(NodoBPP nodoBPPInicial, List<List<String>> finalState, int P) {
        int numNodo = nodoBPPInicial.numNodo;
        ABIERTO.add(nodoBPPInicial);


        while (!ABIERTO.isEmpty()) {
            NodoBPP N = ABIERTO.poll();

            if (!CERRADO.contains(N) && N.nivel <= P) {
                CERRADO.add(N);

                if (equalsToFinalState(N, finalState)) {
                    // Éxito: Mostrar la trayectoria del estado inicial al final
                    mostrarTrayectoria(N);
                    return true;
                }

                List<NodoBPP> sucesores = expander(N);

                for(int i = sucesores.size()-1; i >= 0;  i--){
                    NodoBPP sucesor = sucesores.get(i);
                    if (sucesor != null && sucesor.nivel < P + 1) {
                        numNodo += 1;
                        sucesor.numNodo = numNodo;
                    }

                }
                for (NodoBPP sucesor:sucesores){
                    if (sucesor != null && sucesor.nivel<P+1) {
                        ABIERTO.addFirst(sucesor); // Almacenar al inicio de la lista ABIERTO
                    }
                }
            }
        }

        // Fracaso: No se encontró el estado final
        System.out.println("No se encontró el estado final.");
        return false;
    }

    /**
     * Esta función determina si el nodoBPP equivale al estado final
     * @param nodoBPP DepthFirstSearch.NodoBPP a validar y contiene el estado a validar
     * @param finalState Estado final
     * @return  Verdadero si el estado actual coincide con el final, si no falso
     */
    public boolean equalsToFinalState(NodoBPP nodoBPP, List<List<String>> finalState){
        return finalState.equals(nodoBPP.cuerpoNodo);
    }

    /**
     * Esta función se encarga de expandir los nodos sucesores
     * @param n
     * @return
     */
    public List<NodoBPP> expander(NodoBPP n) {
        int filaCero = -1;
        int columnaCero = -1;

        // Encontrar la posición del cero en el tablero
        for (int i = 0; i < n.cuerpoNodo.size(); i++) {
            for (int j = 0; j < n.cuerpoNodo.get(i).size(); j++) {
                if (n.cuerpoNodo.get(i).get(j).equals("0")) {
                    filaCero = i;
                    columnaCero = j;
                    break;
                }
            }
        }

        // Lista para almacenar los sucesores generados
        List<NodoBPP> sucesores = new ArrayList<>();

        // Mover hacia abajo
        if (filaCero < n.cuerpoNodo.size() - 1 && !"ARRIBA".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero + 1, columnaCero, "ABAJO"));
        }


        if (columnaCero < n.cuerpoNodo.get(filaCero).size() - 1 && !"IZQUIERDA".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero, columnaCero + 1, "DERECHA"));
        }

        // Mover hacia arriba
        if (filaCero > 0 && !"ABAJO".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero - 1, columnaCero, "ARRIBA"));
        }

        if (columnaCero > 0 && !"DERECHA".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero, columnaCero - 1, "IZQUIERDA"));
        }





        // Retorna uno de los sucesores al azar (puedes cambiar esto según tus necesidades)
        if (!sucesores.isEmpty()) {
            return sucesores;
        }

        // Si no hay sucesores válidos
        return null;
    }

    // Función para generar un nuevo nodo dado un movimiento
    private NodoBPP generarSucesor(NodoBPP padre, int filaCeroPadre, int columnaCeroPadre, int filaCeroSucesor, int columnaCeroSucesor, String operacion) {
        NodoBPP sucesor = new NodoBPP();
        sucesor.numNodo = padre.numNodo + 1;
        sucesor.nodoPadre = padre.numNodo;
        sucesor.nivel = padre.nivel + 1;
        sucesor.operacion = operacion;

        // Copiar el cuerpo del padre al sucesor
        for (List<String> fila : padre.cuerpoNodo) {
            sucesor.cuerpoNodo.add(new ArrayList<>(fila));
        }

        // Realizar el movimiento
        sucesor.cuerpoNodo.get(filaCeroPadre).set(columnaCeroPadre, sucesor.cuerpoNodo.get(filaCeroSucesor).get(columnaCeroSucesor));
        sucesor.cuerpoNodo.get(filaCeroSucesor).set(columnaCeroSucesor, "0");

        return sucesor;
    }
    public void mostrarTrayectoria(NodoBPP nodoBPPFinal) {
        // Lista para almacenar la trayectoria desde el nodo final hasta el inicial
        List<NodoBPP> trayectoria = new ArrayList<>();

        // Agregar el nodo final a la trayectoria
        trayectoria.add(nodoBPPFinal);

        // Buscar el nodo padre en la lista CERRADO hasta llegar al nodo inicial
        while (nodoBPPFinal.nodoPadre != 0) {
            for (NodoBPP nodoBPP : CERRADO) {
                if (nodoBPP.numNodo == nodoBPPFinal.nodoPadre) {
                    // Agregar el nodoBPP padre a la trayectoria
                    trayectoria.add(nodoBPP);
                    // Actualizar el nodoBPP final para seguir buscando hacia atrás
                    nodoBPPFinal = nodoBPP;
                    break;
                }
            }
        }

        // Imprimir la trayectoria en orden inverso, ya que la construimos desde el objetivo hasta el inicial
        System.out.println("Trayectoria desde el estado inicial al final:");
        for (int i = trayectoria.size() - 1; i >= 0; i--) {
            System.out.println(trayectoria.get(i));
        }
    }
}
