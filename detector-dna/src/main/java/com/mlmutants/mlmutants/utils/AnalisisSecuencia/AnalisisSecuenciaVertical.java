package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

public class AnalisisSecuenciaVertical implements AnalisisSecuenciaDireccion{

    @Override
    public int analizarSecuenciaADN(String [] dna, int col, int row) {
        return (dna[row].charAt(col) == dna[row+1].charAt(col)
                && dna[row].charAt(col) == dna[row+2].charAt(col)
                && dna[row].charAt(col) == dna[row+3].charAt(col)) ? 1 : 0;
    }

    @Override
    public Boolean isAnalizable(String [] dna, int col, int row, int numSecuenciaAdn) {
       return (dna.length - row) >= numSecuenciaAdn;
    }
}
