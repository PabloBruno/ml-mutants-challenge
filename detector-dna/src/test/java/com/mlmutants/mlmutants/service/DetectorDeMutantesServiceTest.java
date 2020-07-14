package com.mlmutants.mlmutants.service;

import com.mlmutants.mlmutants.payloads.DnaRequestPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DetectorDeMutantesServiceTest {

    @Autowired
    DetectorDeMutantesService detectorDeMutantesService;

    @Test
    public void testIsMutante() {
        DnaRequestPayload dnaRequest =new DnaRequestPayload();
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};
        dnaRequest.setDna(dnaSecuencia);
        Boolean isMutant = detectorDeMutantesService.isMutant(dnaRequest);
        assertTrue(isMutant);
    }

}
