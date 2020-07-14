package com.mlmutants.mlmutants.utils.AnalisisSecuencia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalisisSecuenciaOblicuaDerechaTest {

    @Test
    public void testSecuenciaOblicuaDerechaAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaOblicuaDerecha analisisSecuenciaOblicuaDerecha = new AnalisisSecuenciaOblicuaDerecha();

      Boolean isAnalizable = analisisSecuenciaOblicuaDerecha.isAnalizable(dnaSecuencia,1,1,4);
      assertTrue(isAnalizable);
    }

    @Test
    public void testSecuenciaOblicuaDerechaNotAnalizable(){
        String[] dnaSecuencia = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTT"};

        AnalisisSecuenciaOblicuaDerecha analisisSecuenciaOblicuaDerecha = new AnalisisSecuenciaOblicuaDerecha();

        Boolean isAnalizable = analisisSecuenciaOblicuaDerecha.isAnalizable(dnaSecuencia,6,1,4);
        assertFalse(isAnalizable);
    }


}
