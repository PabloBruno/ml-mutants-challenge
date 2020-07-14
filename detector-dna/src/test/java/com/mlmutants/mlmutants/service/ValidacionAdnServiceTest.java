package com.mlmutants.mlmutants.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidacionAdnServiceTest {
    @Autowired
    ValidacionAdnService validacionAdnService;

    @Test
    public void testDnaValido() {

        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        Boolean esValido = validacionAdnService.esAdnValido(dnaSecuencia);
        assertTrue(esValido);
    }

    @Test
    public void testDnaInvalido() {

        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCAC"};

        Boolean esValido = validacionAdnService.esAdnValido(dnaSecuencia);
        assertFalse(esValido);
    }

    @Test
    public void testDnaInvalidoLetrasIncorrectas() {

        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACT4"};

        Boolean esValido = validacionAdnService.esAdnValido(dnaSecuencia);
        assertFalse(esValido);
    }
}
