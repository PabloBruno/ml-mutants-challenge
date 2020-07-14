package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalisisSecuenciaVerticalTest {

    @Test
    public void testSecuenciaVerticalAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaVertical analisisSecuenciaVertical = new AnalisisSecuenciaVertical();

      Boolean isAnalizable = analisisSecuenciaVertical.isAnalizable(dnaSecuencia,1,1,4);
      assertTrue(isAnalizable);
    }

    @Test
    public void testSecuenciaVerticalNotAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaVertical analisisSecuenciaVertical = new AnalisisSecuenciaVertical();

        Boolean isAnalizable = analisisSecuenciaVertical.isAnalizable(dnaSecuencia,1,6,4);
        assertFalse(isAnalizable);
    }


}
