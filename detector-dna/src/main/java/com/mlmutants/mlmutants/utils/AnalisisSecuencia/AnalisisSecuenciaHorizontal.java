package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

public class AnalisisSecuenciaHorizontal implements AnalisisSecuenciaDireccion{

    @Override
    public int analizarSecuenciaADN(String [] dna, int col, int row) {
        String dnaStr = dna[row];

        return (dnaStr.charAt(col) == dnaStr.charAt(col+1)
                && dnaStr.charAt(col) == dnaStr.charAt(col + 2)
                && dnaStr.charAt(col) == dnaStr.charAt(col + 3))  ? 1 : 0;
    }

    @Override
    public Boolean isAnalizable(String [] dna, int col, int row, int numSecuenciaAdn) {
        return  (dna[row].length() - col) >= numSecuenciaAdn;
    }
}
