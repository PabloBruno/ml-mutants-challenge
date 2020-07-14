package com.mlmutants.mlmutants.utils.DetectorDeMutante;


import com.mlmutants.mlmutants.utils.AnalisisSecuencia.AnalisisSecuenciaDireccion;

import java.util.ArrayList;
import java.util.List;

public class DetectorDeMutante {

    private static final int CANT_MIN_SECUENCIA_MUTANTE = 2;

    private static final int NUM_SECUENCIA_ADN = 4;

    private List<AnalisisSecuenciaDireccion> analisisSecuenciaDireccionList = new ArrayList<AnalisisSecuenciaDireccion>();

    /**
     * Realiza el analisis de un adn enviado como array de String para verificar si corresponde a un mutante o no
     * @param dna array de string a analizar
     * @return True si el adn corresponde a un mutante, False en caso contrario.
     */
    public Boolean isMutant(String[] dna){
        Boolean isMutant=false;

        int cantSecuenciasHalladas = 0;

        int valorN = dna.length;

        int row = 0, col = 0;

        while (!isMutant && row < valorN){
            while (!isMutant && col < valorN){

                for (AnalisisSecuenciaDireccion analisisSecuenciaDireccion : this.analisisSecuenciaDireccionList) {
                    cantSecuenciasHalladas += analisisSecuenciaDireccion.isAnalizable(dna,col,row,NUM_SECUENCIA_ADN) ?
                                              analisisSecuenciaDireccion.analizarSecuenciaADN(dna,col,row) : 0;
                }

                isMutant = cantSecuenciasHalladas >= CANT_MIN_SECUENCIA_MUTANTE;
                col++;
            }
            row++;
            col=0;
        }
        return isMutant;
    }

    /**
     * Añade a la lista de secuencias de analisis, una posiblidad de analisis de cadenas del adn.
     * @param analisisSecuenciaDireccion Nueva secuencia de analisis.
     */
    public void addAnalisisSecuenciaDireccion(AnalisisSecuenciaDireccion analisisSecuenciaDireccion){
            this.analisisSecuenciaDireccionList.add(analisisSecuenciaDireccion);
    }
}
