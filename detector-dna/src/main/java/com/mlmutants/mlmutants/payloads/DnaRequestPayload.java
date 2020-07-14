package com.mlmutants.mlmutants.payloads;

public class DnaRequestPayload {
    public String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna){
        this.dna = dna;
    }

    /**
     * Implementa la obtencion del hashcode del arreglo
     * @return un int con un codigo hash.
     */
    @Override
    public int hashCode(){
      return  (String.join("",this.dna)).hashCode();
    }

    /**
     * Convierte a string el array de dna. Cada elemento esta delimitado por una coma ","
     * @return el array convertido en String
     */
    @Override
    public String toString(){

        if (this.dna == null)
            return "";
        int dnaLength = this.dna.length;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i< dnaLength; i++) {
            stringBuilder.append(this.dna[i]);
            if (i < dnaLength - 1)
                stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }
}
