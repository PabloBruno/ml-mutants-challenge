package com.mlmutants.mlmutants.service;


import com.mlmutants.mlmutants.model.DnaResult;
import com.mlmutants.mlmutants.payloads.DnaRequestPayload;
import com.mlmutants.mlmutants.repository.DnaResultRepository;
import com.mlmutants.mlmutants.utils.AnalisisSecuencia.AnalisisSecuenciaHorizontal;
import com.mlmutants.mlmutants.utils.AnalisisSecuencia.AnalisisSecuenciaOblicuaDerecha;
import com.mlmutants.mlmutants.utils.AnalisisSecuencia.AnalisisSecuenciaOblicuaIzquierda;
import com.mlmutants.mlmutants.utils.AnalisisSecuencia.AnalisisSecuenciaVertical;
import com.mlmutants.mlmutants.utils.DetectorDeMutante.DetectorDeMutante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetectorDeMutantesService {

    private DetectorDeMutante detectorDeMutante;

    @Autowired
    private DnaResultRepository dnaResultRepository;

    /**
     * Determina si el adn corresponde a un mutante o a un ser humano.
     *
     * @param dna Arreglo de String que corresponde a una secuencia de adn NxN
     * @return TRUE si es mutante FALSE si es humano
     */
    public Boolean isMutant(DnaRequestPayload dna) {
        //Se crea el detector de mutantes
        detectorDeMutante = new DetectorDeMutante();

        //Se le añaden direcciones de analisis del adn
        detectorDeMutante.addAnalisisSecuenciaDireccion(new AnalisisSecuenciaHorizontal());
        detectorDeMutante.addAnalisisSecuenciaDireccion(new AnalisisSecuenciaVertical());
        detectorDeMutante.addAnalisisSecuenciaDireccion(new AnalisisSecuenciaOblicuaDerecha());
        detectorDeMutante.addAnalisisSecuenciaDireccion(new AnalisisSecuenciaOblicuaIzquierda());

        //Se ejecuta el detector de mutante y se retorna su resultado
        return detectorDeMutante.isMutant(dna.getDna());
    }

    /**
     * Almacena un resultado de analisis
     * @param dna el adn analizado
     * @param result el resultado del analisis
     */
    public Boolean storeDnaResult(DnaRequestPayload dna, Boolean result){
        //Obtencion de un hashcode
        int hash=dna.hashCode();

        DnaResult dnaResult = new DnaResult();
        dnaResult.setDnaId(hash);
        dnaResult.setDnaArray(dna.toString());
        dnaResult.setMutant(result);

      Boolean saveExists =  dnaResultRepository.insertDnaResult(dnaResult);
      return saveExists;
    }

    public DnaResult getDnaResult(DnaRequestPayload dna){
        DnaResult dnaResult = dnaResultRepository.getDnaResult(dna.hashCode());
        return dnaResult;
    }

}
