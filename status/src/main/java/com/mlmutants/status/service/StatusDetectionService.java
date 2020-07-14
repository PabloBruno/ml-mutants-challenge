package com.mlmutants.status.service;


import com.mlmutants.status.model.StatusDetection;
import com.mlmutants.status.payload.StatusDetectionPayload;
import com.mlmutants.status.repository.StatusDetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusDetectionService {
    @Autowired
    StatusDetectorRepository statusDetectorRepository;

    /**
     *  Obtiene el status de la deteccion
     * @return un objeto StatusDetectionPayload con la informacion de estado
     */
    public StatusDetectionPayload getStatusDetection(){
        StatusDetectionPayload statusDetectionPayload=null;
        try {
            StatusDetection statusMutante = statusDetectorRepository.getCounter("Mutante");
            StatusDetection statusHumano = statusDetectorRepository.getCounter("Humano");

            long countMutante = statusMutante != null ? statusMutante.getCount() : 0;
            long countHumano = statusHumano != null ? statusHumano.getCount() : 0;

            double ratio = 0;

            if (countHumano > 0) {
                ratio = (double) countMutante / countHumano;
            }

            statusDetectionPayload = new StatusDetectionPayload();
            statusDetectionPayload.setCount_mutant_dna(countMutante);
            statusDetectionPayload.setCount_human_dna(countHumano);
            statusDetectionPayload.setRatio(ratio);
        }catch (Exception e){
            e.printStackTrace();
        }
        return statusDetectionPayload;

    }


}
