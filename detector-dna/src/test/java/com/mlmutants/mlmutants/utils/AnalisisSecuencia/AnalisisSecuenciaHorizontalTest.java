package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalisisSecuenciaHorizontalTest {

    @Test
    public void testSecuenciaHorizontalAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaHorizontal analisisSecuenciaHorizontal = new AnalisisSecuenciaHorizontal();

      Boolean isAnalizable = analisisSecuenciaHorizontal.isAnalizable(dnaSecuencia,1,1,4);
      assertTrue(isAnalizable);
    }

    @Test
    public void testSecuenciaHorizontalNotAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaHorizontal analisisSecuenciaHorizontal = new AnalisisSecuenciaHorizontal();

        Boolean isAnalizable = analisisSecuenciaHorizontal.isAnalizable(dnaSecuencia,4,1,4);
        assertFalse(isAnalizable);
    }


    @Test
    public void testSecuenciaHorizontalAnalizable2(){
        String[] dnaSecuencia = {"CATTAA","ATGTAC","ATTAGT","AGATGG","TCCCCA","TCTCTG"};

        AnalisisSecuenciaHorizontal analisisSecuenciaHorizontal = new AnalisisSecuenciaHorizontal();

        Boolean isAnalizable = analisisSecuenciaHorizontal.isAnalizable(dnaSecuencia,1,4,4);
        assertTrue(isAnalizable);
    }
}
