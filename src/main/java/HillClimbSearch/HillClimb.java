package HillClimbSearch;

import DepthFirstSearch.NodoBPP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class HillClimb {
    LinkedList<NodoHC> ABIERTO = new LinkedList<NodoHC>();
    LinkedList<NodoHC> CERRADO = new LinkedList<NodoHC>();

    public boolean hillClimb(NodoHC nodoInicial, List<List<String>> finalState) {
        int numNodo = nodoInicial.numNodo;
        ABIERTO.add(nodoInicial);

        while (!ABIERTO.isEmpty()) {
            NodoHC N = ABIERTO.poll();

            if (!CERRADO.contains(N)) {
                CERRADO.add(N);

                if (equalsToFinalState(N, finalState)) {
                    // Éxito: Mostrar la trayectoria del estado inicial al final
                    mostrarTrayectoria(N);
                    return true;
                }

                List<NodoHC> sucesores = expander(N, finalState);
                sucesores.sort(Comparator.comparingDouble(NodoHC::getHeuristica).reversed());
                //Calcular
                for(int i = sucesores.size()-1; i >= 0;  i--){
                    NodoHC sucesor = sucesores.get(i);
                    if (sucesor != null) {
                        numNodo += 1;
                        sucesor.numNodo = numNodo;
                    }

                }
                for (NodoHC sucesor:sucesores){
                    if (sucesor != null) {
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
     * Esta función determina si el nodoHC equivale al estado final
     * @param nodoHC nodo a validar
     * @param finalState Estado final
     * @return  Verdadero si el estado actual coincide con el final, si no falso
     */
    public boolean equalsToFinalState(NodoHC nodoHC, List<List<String>> finalState){
        return finalState.equals(nodoHC.cuerpoNodo);
    }

    public List<NodoHC> expander(NodoHC n, List<List<String>> finalState) {
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
        List<NodoHC> sucesores = new ArrayList<>();

        // Mover hacia abajo
        if (filaCero < n.cuerpoNodo.size() - 1 && !"ARRIBA".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero + 1, columnaCero, "ABAJO",finalState));
        }


        if (columnaCero < n.cuerpoNodo.get(filaCero).size() - 1 && !"IZQUIERDA".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero, columnaCero + 1, "DERECHA",finalState));
        }

        // Mover hacia arriba
        if (filaCero > 0 && !"ABAJO".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero - 1, columnaCero, "ARRIBA",finalState));
        }

        if (columnaCero > 0 && !"DERECHA".equals(n.operacion)) {
            sucesores.add(generarSucesor(n, filaCero, columnaCero, filaCero, columnaCero - 1, "IZQUIERDA",finalState));
        }


        // Retorna uno de los sucesores al azar (puedes cambiar esto según tus necesidades)
        if (!sucesores.isEmpty()) {
            return sucesores;
        }

        // Si no hay sucesores válidos
        return null;
    }

    // Función para generar un nuevo nodo dado un movimiento
    private NodoHC generarSucesor(NodoHC padre, int filaCeroPadre, int columnaCeroPadre, int filaCeroSucesor, int columnaCeroSucesor, String operacion, List<List<String>> finalState) {
        NodoHC sucesor = new NodoHC();
        sucesor.numNodo = padre.numNodo + 1;
        sucesor.nodoPadre = padre.numNodo;
        sucesor.operacion = operacion;

        // Copiar el cuerpo del padre al sucesor
        for (List<String> fila : padre.cuerpoNodo) {
            sucesor.cuerpoNodo.add(new ArrayList<>(fila));
        }

        // Realizar el movimiento
        sucesor.cuerpoNodo.get(filaCeroPadre).set(columnaCeroPadre, sucesor.cuerpoNodo.get(filaCeroSucesor).get(columnaCeroSucesor));
        sucesor.cuerpoNodo.get(filaCeroSucesor).set(columnaCeroSucesor, "0");
        sucesor.heuristica = calcularDistanciaManhattan(sucesor.cuerpoNodo, finalState);
        return sucesor;
    }

    private float calcularDistanciaManhattan(List<List<String>> estadoActual, List<List<String>> estadoObjetivo) {
        float distanciaTotal = 0;

        for (int i = 0; i < estadoActual.size(); i++) {
            for (int j = 0; j < estadoActual.get(i).size(); j++) {
                String valor = estadoActual.get(i).get(j);
                if (!valor.equals("0")) {
                    int[] posicionActual = encontrarPosicion(estadoActual, valor);
                    int[] posicionObjetivo = encontrarPosicion(estadoObjetivo, valor);

                    if (posicionActual != null && posicionObjetivo != null) {
                        float distanciaParcial = Math.abs(posicionActual[0] - posicionObjetivo[0]) + Math.abs(posicionActual[1] - posicionObjetivo[1]);
                        distanciaTotal += distanciaParcial;
                    } else {
                        // Manejo de error: alguna posición es null
                        System.out.println("Error: Posición nula encontrada.");
                    }
                }
            }
        }

        return distanciaTotal;
    }


    private int[] encontrarPosicion(List<List<String>> estado, String valor) {
        for (int i = 0; i < estado.size(); i++) {
            for (int j = 0; j < estado.get(i).size(); j++) {
                if (valor.equals(estado.get(i).get(j))) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Retorna null si no se encuentra el valor
    }

    public void mostrarTrayectoria(NodoHC nodoHC) {
        // Lista para almacenar la trayectoria desde el nodo final hasta el inicial
        List<NodoHC> trayectoria = new ArrayList<>();

        // Agregar el nodo final a la trayectoria
        trayectoria.add(nodoHC);

        // Buscar el nodo padre en la lista CERRADO hasta llegar al nodo inicial
        while (nodoHC.nodoPadre != 0) {
            for (NodoHC nodoAux : CERRADO) {
                if (nodoAux.numNodo == nodoHC.nodoPadre) {
                    // Agregar el nodoBPP padre a la trayectoria
                    trayectoria.add(nodoAux);
                    // Actualizar el nodoBPP final para seguir buscando hacia atrás
                    nodoHC = nodoAux;
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
