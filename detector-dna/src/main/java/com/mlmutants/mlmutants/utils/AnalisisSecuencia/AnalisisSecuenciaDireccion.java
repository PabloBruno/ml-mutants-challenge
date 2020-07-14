package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

public interface AnalisisSecuenciaDireccion {

    /**
     * Se hace el analisis de una secuencia de adn en una determinada direccion
     *
     * @param dna   matriz que representa el adn a analizar
     * @param col   columna en la que se encuentra el valor inicial de la secuencia de analisis
     * @param row   fila en la que se encuentra el valor inicial de la secuencia de analisis
     * @return  el numero 1 si la secuencia analizada posee todas letras iguales y 0 en caso contrario
     */
    public abstract int analizarSecuenciaADN(String[] dna, int col, int row);


    /**
     * Se verifican las condiciones que requiere el analisis de una secuencia en una determinada
     * direccion para poder ser realizado.
     *
     * @param dna   matriz que representa el adn a analizar
     * @param col   columna en la que se encuentra el valor inicial de la secuencia de analisis
     * @param row   fila en la que se encuentra el valor inicial de la secuencia de analisis
     * @param numSecuenciaAdn   la cantidad de valores a analizar de una secuencia
     * @return  TRUE si la secuencia se puede analizar FALSE en caso contrario
     */
    public abstract Boolean isAnalizable(String[] dna, int col, int row, int numSecuenciaAdn);
}
