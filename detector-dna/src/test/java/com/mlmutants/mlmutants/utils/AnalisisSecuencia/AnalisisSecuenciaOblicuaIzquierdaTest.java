package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalisisSecuenciaOblicuaIzquierdaTest {

    @Test
    public void testSecuenciaOblicuaIzquierdaAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaOblicuaIzquierda analisisSecuenciaOblicuaIzquierda = new AnalisisSecuenciaOblicuaIzquierda();

      Boolean isAnalizable = analisisSecuenciaOblicuaIzquierda.isAnalizable(dnaSecuencia,4,1,4);
      assertTrue(isAnalizable);
    }

    @Test
    public void testSecuenciaOblicuaIzquierdaNotAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaOblicuaIzquierda analisisSecuenciaOblicuaIzquierda = new AnalisisSecuenciaOblicuaIzquierda();

        Boolean isAnalizable = analisisSecuenciaOblicuaIzquierda.isAnalizable(dnaSecuencia,1,1,4);
        assertFalse(isAnalizable);
    }

    @Test
    public void testSecuenciaOblicuaIzquierdaAnalizable2(){
        String[] dnaSecuencia = {"CATTAA","ATGTAC","ATTAGT","AGATGG","TCCCCA","TCTCTG"};

        AnalisisSecuenciaOblicuaIzquierda analisisSecuenciaOblicuaIzquierda = new AnalisisSecuenciaOblicuaIzquierda();

        Boolean isAnalizable = analisisSecuenciaOblicuaIzquierda.isAnalizable(dnaSecuencia,5,1,4);
        assertTrue(isAnalizable);
    }


}
