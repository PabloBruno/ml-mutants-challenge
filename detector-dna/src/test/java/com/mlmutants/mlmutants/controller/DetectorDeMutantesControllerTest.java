package com.mlmutants.mlmutants.controller;

import com.mlmutants.mlmutants.payloads.DnaRequestPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DetectorDeMutantesControllerTest {
    @Autowired
    DetectorDeMutantesController detectorDeMutantesController;

    @Test
    public void testIsMutante() {
        DnaRequestPayload dnaRequest =new DnaRequestPayload();
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};
        dnaRequest.setDna(dnaSecuencia);
        ResponseEntity response= detectorDeMutantesController.esMutante(dnaRequest);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDnaInvalidoNoProcesable() {
        DnaRequestPayload dnaRequest =new DnaRequestPayload();
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACT4"};
        dnaRequest.setDna(dnaSecuencia);
        ResponseEntity response= detectorDeMutantesController.esMutante(dnaRequest);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY,response.getStatusCode());
    }

}
