package com.mlmutants.mlmutants.utils.DetectorDeMutante;

public class ValidacionADN {
    public static String LETRAS_VALIDAS = "ACGT";

    /**
     * Revisa  si el array de string es adn o no.
     *
     * @param dna
     * @return
     */
    public static Boolean isAdnValido(String[] dna){
        Boolean valido = true;
        int dnaLength = dna.length;

        int n=0;

        while (valido && n < dnaLength){
            valido = (dnaLength == dna[n].length()) && tieneLetrasValidas(dna[n]);
            n++;
        }
        return valido;
    }


    /**
     * Analiza si una secuencia posee unicamente caracteres que esten dentro del array LETRAS_VALIDAS.
     *
     * @param secuencia
     * @return
     */
    public static Boolean tieneLetrasValidas(String secuencia){
        int secuenciaLength = secuencia.length();
        Boolean secuenciaValida = true;

        int n=0;
        while (secuenciaValida && n < secuenciaLength) {
           secuenciaValida = LETRAS_VALIDAS.indexOf((String.valueOf(secuencia.charAt(n)))) > -1;
           n++;
        }

        return secuenciaValida;
    }


}
