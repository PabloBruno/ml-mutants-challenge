package com.mlmutants.mlmutants.service;

import com.mlmutants.mlmutants.utils.DetectorDeMutante.ValidacionADN;
import org.springframework.stereotype.Service;

@Service
public class ValidacionAdnService {
    public static ValidacionADN validacionADN;

    /**
     * Llama a un objeto que implementa la validacion del adn
     * @param dna un array de string con el adn a verificar
     * @return True si el Array enviado corresponde a un adn valido, False en caso contrario
     */
    public Boolean esAdnValido(String[] dna){

        return validacionADN.isAdnValido(dna);
    }

}
