package Genetic;

import DepthFirstSearch.NodoBPP;

import java.util.*;

public class Genetic {
    List<List<NodoGen>> generaciones = new ArrayList<>();
    int contador = 0;

    public boolean geneticAlg (NodoGen nodoGen, List<List<String>> finalState, int indiceMutacion, int indicePoblacion) {
        boolean terminado = false;
        Random random = new Random();
        generaciones.add(generacionInicial(nodoGen, finalState, indicePoblacion));
        while (!terminado) {
            // Producir nueva generación
            List<NodoGen> nuevaGeneracion = new ArrayList<>();

            for (int i = 0; i < indicePoblacion; i++) {
                // Selección de parejas aleatorias
                NodoGen padre1 = seleccionarPadreAleatorio();

                NodoGen padre2;

                do {
                    padre2 = seleccionarPadreAleatorio();
                } while (padre2.numNodo == padre1.numNodo);

                // Cruce de los padres para obtener los descendientes
                NodoGen descendiente = cruzar(padre1, padre2);


                // Calcular fitness de los descendientes mutados
                descendiente.fitness = calcularDistanciaManhattan(descendiente.cuerpoNodo, finalState);

                // Insertar los descendientes mutados en la nueva generación
                nuevaGeneracion.add(descendiente);
            }
            if (generaciones.size() % indiceMutacion == 0) {
                // Seleccionar un índice aleatorio de la nueva generación
                int indiceDescendiente = random.nextInt(nuevaGeneracion.size());

                mutar(nuevaGeneracion.get(indiceDescendiente));
                nuevaGeneracion.get(indiceDescendiente).fitness = calcularDistanciaManhattan(nuevaGeneracion.get(indiceDescendiente).cuerpoNodo, finalState);
            }

            generaciones.add(nuevaGeneracion);

            // Verificar si la población converge
            if (equalsToFinalState(nuevaGeneracion, finalState)) {
                terminado = true;
                System.out.println("Éxito - Solución encontrada.");
                mostrarTrayectoria(nodoGen);
                return true;
            }
        }

        System.out.println("Fracaso - La población no converge.");
        return false;
    }
    public List<NodoGen> generacionInicial(NodoGen nodoGen, List<List<String>> finalState, int indicePoblacion){
        List<NodoGen> generacion = new ArrayList<>();
        for(int i = 0; i<indicePoblacion; i++){
            NodoGen nuevoGen = new NodoGen();
            contador += 1;
            nuevoGen.numNodo = contador;
            nuevoGen.cuerpoNodo = intercambiar(nodoGen.cuerpoNodo);
            nuevoGen.fitness = calcularDistanciaManhattan(nuevoGen.cuerpoNodo, finalState);
            generacion.add(nuevoGen);
        }

        return generacion;
    }
    public static List<List<String>> intercambiar(List<List<String>> cuerpoNodo) {
        List<List<String>> nuevaLista = new ArrayList<>();

        for (List<String> fila : cuerpoNodo) {
            List<String> nuevaFila = new ArrayList<>(fila);
            nuevaLista.add(nuevaFila);
        }

        Random rand = new Random();

        // Recorre todas las filas
        for (List<String> fila : nuevaLista) {
            // Intercambia elementos aleatoriamente en cada fila
            Collections.shuffle(fila, rand);
        }

        // Intercambia filas aleatoriamente
        Collections.shuffle(nuevaLista, rand);

        return nuevaLista;
    }

    private float calcularDistanciaManhattan(List<List<String>> estadoActual, List<List<String>> estadoObjetivo) {
        float distanciaTotal = 0;
        float distanciaMaxima = 31;

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


        return (1 - (distanciaTotal / distanciaMaxima)) * 100;
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

    private NodoGen seleccionarPadreAleatorio() {
        Random rand = new Random();
        int indiceGeneracion = 0;
        if(generaciones.size() - 1 > 0){
            indiceGeneracion = generaciones.size() - 1;
        }
        List<NodoGen> generacionActual = generaciones.get(indiceGeneracion);

        // Calcular la suma total de los fitness de la generación actual
        float sumaFitness = 0;
        for (NodoGen nodo : generacionActual) {
            sumaFitness += nodo.fitness;
        }

        // Generar un número aleatorio dentro del rango de sumaFitness
        float puntoAleatorio = rand.nextFloat() * sumaFitness;

        // Seleccionar el padre basado en el rango de fitness acumulado
        float acumulativo = 0;
        for (NodoGen nodo : generacionActual) {
            acumulativo += nodo.fitness;
            if (acumulativo >= puntoAleatorio) {
                return nodo; // Retorna el nodo cuyo fitness supera el punto aleatorio
            }
        }

        // En caso de fallo, retornar un nodo aleatorio
        return generacionActual.get(rand.nextInt(generacionActual.size()));
    }

    private NodoGen cruzar(NodoGen padre1, NodoGen padre2) {
        // Obtener el cuerpoNodo de los padres
        List<List<String>> cuerpoPadre1 = padre1.cuerpoNodo;
        List<List<String>> cuerpoPadre2 = padre2.cuerpoNodo;

        // Convertir los cuerpos de los padres a strings
        String strPadre1 = cuerpoNodoToString(cuerpoPadre1);
        String strPadre2 = cuerpoNodoToString(cuerpoPadre2);

        // Seleccionar tres números aleatorios de strPadre1 y almacenar sus índices
        int[] indicesAleatorios = new int[3]; // Almacenar los índices seleccionados aleatoriamente
        int[] numerosAleatorios = new int[3]; // Almacenar los números correspondientes a los índices seleccionados
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int indice = random.nextInt(strPadre1.length());
            indicesAleatorios[i] = indice;
            numerosAleatorios[i] = Character.getNumericValue(strPadre1.charAt(indice));
        }

        // Ordenar los índices de mayor a menor
        Arrays.sort(indicesAleatorios);
        // Ordenar los números correspondientes en el mismo orden
        Arrays.sort(numerosAleatorios);

        // Eliminar los números seleccionados de strPadre2 e insertar los números de strPadre1 en las posiciones correspondientes
        StringBuilder sbPadre2Actualizado = new StringBuilder(strPadre2);
        for (int i = 0; i < 3; i++) {
            // Obtener el índice de inserción en strPadre2Actualizado
            int indiceInsercion = indicesAleatorios[i];
            // Eliminar el número seleccionado de strPadre2Actualizado
            sbPadre2Actualizado.deleteCharAt(sbPadre2Actualizado.indexOf(String.valueOf(numerosAleatorios[i])));
            // Insertar el número seleccionado de strPadre1 en la posición adecuada de strPadre2Actualizado
            sbPadre2Actualizado.insert(indiceInsercion, numerosAleatorios[i]);
        }

        // Convertir la descendencia a una lista de listas de strings
        List<List<String>> cuerpoDescendiente = stringToCuerpoNodo(sbPadre2Actualizado.toString());

        // Crear el nodo descendiente
        NodoGen descendiente = new NodoGen();
        descendiente.numNodo = ++contador;
        descendiente.cuerpoNodo = cuerpoDescendiente;

        return descendiente;
    }

    // Método para convertir un cuerpoNodo a un string
    private String cuerpoNodoToString(List<List<String>> cuerpoNodo) {
        StringBuilder sb = new StringBuilder();
        for (List<String> fila : cuerpoNodo) {
            for (String elemento : fila) {
                sb.append(elemento);
            }
        }
        return sb.toString();
    }

    // Método para convertir un string a un cuerpoNodo
    private List<List<String>> stringToCuerpoNodo(String str) {
        List<List<String>> cuerpoNodo = new ArrayList<>();
        for (int i = 0; i < str.length(); i += 3) {
            List<String> fila = new ArrayList<>();
            fila.add(str.substring(i, i + 1));
            fila.add(str.substring(i + 1, i + 2));
            fila.add(str.substring(i + 2, i + 3));
            cuerpoNodo.add(fila);
        }
        return cuerpoNodo;
    }




    private void mutar(NodoGen descendiente) {
        List<List<String>> cuerpoMutar = descendiente.cuerpoNodo;

        Random rand = new Random();

        // Recorre todas las filas
        for (List<String> fila : cuerpoMutar) {
            // Intercambia dos elementos aleatoriamente en cada fila
            Collections.shuffle(fila, rand);
        }

        // Intercambia dos filas aleatoriamente
        Collections.shuffle(cuerpoMutar, rand);

        descendiente.cuerpoNodo = cuerpoMutar;
    }

    public boolean equalsToFinalState(List<NodoGen> nodoGenList, List<List<String>> finalState){
        for (NodoGen nodoGen:nodoGenList){
            return finalState.equals(nodoGen.cuerpoNodo);
        }
        return false;
    }

    private NodoGen obtenerMejorIndividuo(List<NodoGen> generacion) {
        NodoGen mejorIndividuo = generacion.getFirst();
        for (NodoGen individuo : generacion) {
            if (individuo.fitness > mejorIndividuo.fitness) {
                mejorIndividuo = individuo;
            }
        }
        return mejorIndividuo;
    }

    public void mostrarTrayectoria(NodoGen cromosoma){
        System.out.println(cromosoma.numNodo +" "+ cromosoma.cuerpoNodo);
        int generacionContador = 0;
        for(List<NodoGen> generacion: generaciones){
            System.out.println("Generacion # "+ generacionContador);
            for(NodoGen nodoAux: generacion){
                System.out.println(nodoAux.toString());
            }
            generacionContador+=1;
        }
    }

}
