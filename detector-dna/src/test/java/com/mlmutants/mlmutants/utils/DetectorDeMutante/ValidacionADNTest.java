package com.mlmutants.mlmutants.utils.DetectorDeMutante;

import com.mlmutants.mlmutants.service.ValidacionAdnService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidacionADNTest {
    @Autowired
    ValidacionAdnService validacionAdnService;

    @Test
    public void testIsAdnValido() {
      String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};
    Boolean esValido = validacionAdnService.esAdnValido(dnaSecuencia);
        assertTrue(esValido);
    }

    @Test
    public void testIsAdnInvalido() {
        String[] dnaSecuencia = {"ATG","CAGTGCY","TTATGT","AGAAGG","CCCCTA","TCACTT"};
        Boolean esValido = validacionAdnService.esAdnValido(dnaSecuencia);
        assertFalse(esValido);
    }

}
