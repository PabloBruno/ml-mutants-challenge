package com.mlmutants.mlmutants.controller;


import com.mlmutants.mlmutants.enums.DnaTypeEnum;
import com.mlmutants.mlmutants.model.DnaResult;
import com.mlmutants.mlmutants.payloads.DnaRequestPayload;
import com.mlmutants.mlmutants.payloads.ResultRecord;
import com.mlmutants.mlmutants.service.DetectorDeMutantesService;
import com.mlmutants.mlmutants.service.KinesisRecordService;
import com.mlmutants.mlmutants.service.ValidacionAdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutants")
public class DetectorDeMutantesController {

    @Autowired
    DetectorDeMutantesService detectorDeMutantesService;

    @Autowired
    ValidacionAdnService validacionAdnService;

@Autowired
    KinesisRecordService kinesisRecordService;


    @PostMapping
    public ResponseEntity esMutante (@RequestBody DnaRequestPayload dnaRequest){


        //Si el adn es invalido se envia un 422
        if (!validacionAdnService.esAdnValido(dnaRequest.getDna()))
           return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        //Reviso si el adn se encuentra en la base de datos
       try {
           DnaResult dnaResult = detectorDeMutantesService.getDnaResult(dnaRequest);

           //Si el adn ya se encuentra almacenado retorno el resultado de dicho analisis
           if (dnaResult != null) {
               return dnaResult.getMutant() ? ResponseEntity.status(HttpStatus.OK).build()
                       : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
           }
       }catch (Exception e){
           e.printStackTrace();
       }

        //Si el adn no se encuentra almacenado se analiza
        Boolean isMutant = detectorDeMutantesService.isMutant(dnaRequest);

//         //Guardo el adn analizado y el resultado en la base de datos
        Boolean bdSinError =true;
        Boolean nuevoSave=false;

        try {
         nuevoSave = detectorDeMutantesService.storeDnaResult(dnaRequest, isMutant);
         }
        catch (Exception e)
        {
            bdSinError=false;
            e.printStackTrace();
        }


        //Por defecto se espera que sea un humano
        // se enviara un response FORBIDDEN (403)
        DnaTypeEnum tipoAdnResult = DnaTypeEnum.HUMAN;
        HttpStatus statusResult = HttpStatus.FORBIDDEN;

        //En caso de ser mutante
        // se enviara un response OK (200)
        if (isMutant) {
            statusResult = HttpStatus.OK;
            tipoAdnResult= DnaTypeEnum.MUTANT;
        }

        //Si se hizo efectivamente el save se envia un mensaje a kinesis
        if (nuevoSave && bdSinError){
            ResultRecord recordResult =  new ResultRecord(String.valueOf(dnaRequest.hashCode()),tipoAdnResult);
            try {
                kinesisRecordService.putRecordIntoKinesis(recordResult);
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        return ResponseEntity.status(statusResult).build();

    }


}
