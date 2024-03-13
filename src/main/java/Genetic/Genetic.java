package Genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genetic {
    List<List<NodoGen>> generaciones;
    int contador = 0;

    public boolean geneticAlg (NodoGen nodoGen, List<List<String>> FinalState, int indiceMutacion) {
        NodoGen cromosomaInicial = nodoGen;
        boolean Terminado = false;
        generaciones.add(generacionInicial(cromosomaInicial));


        return true;
    }
    public List<NodoGen> generacionInicial(NodoGen nodoGen){
        List<NodoGen> generacion = new ArrayList<>();
        for(int i = 0; i<4; i++){
            NodoGen nuevoGen = new NodoGen();
            nuevoGen.cuerpoNodo = intercambiar(nodoGen.cuerpoNodo);
        }

        return generacion;
    }
    public static List<List<String>> intercambiar(List<List<String>> cuerpoNodo) {
        // Creamos una copia de la lista original para no modificarla directamente
        List<List<String>> nuevaLista = new ArrayList<>(cuerpoNodo);

        // Creamos un objeto Random para generar números aleatorios
        Random rand = new Random();

        // Elegimos una fila aleatoria
        int filaAleatoria = rand.nextInt(3); // 3 filas en total

        // Elegimos dos columnas aleatorias diferentes
        int columnaAleatoria1 = rand.nextInt(3); // 3 columnas en total
        int columnaAleatoria2 = rand.nextInt(3); // 3 columnas en total
        while (columnaAleatoria1 == columnaAleatoria2) {
            columnaAleatoria2 = rand.nextInt(3); // Aseguramos que sean diferentes
        }

        String temp = nuevaLista.get(filaAleatoria).get(columnaAleatoria1);
        nuevaLista.get(filaAleatoria).set(columnaAleatoria1, nuevaLista.get(filaAleatoria).get(columnaAleatoria2));
        nuevaLista.get(filaAleatoria).set(columnaAleatoria2, temp);

        return nuevaLista;
    }
}
