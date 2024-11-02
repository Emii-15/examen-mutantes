package com.examen.mutantes.service;

import com.examen.mutantes.model.ADN;
import com.examen.mutantes.repository.ADNRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class ADNServiceTest {

    @Mock
    private ADNRepository adnRepository;

    @InjectMocks
    private ADNService adnService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testIsMutant_ADNMutante() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería detectar ADN mutante (true)");
    }

    @Test
    public void testIsMutant_ADNHumano() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "CCTCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(false, resultado, "Debería mostrar ADN humano (false)");
    }

    @Test
    public void testIsMutant_SecuenciaHorizontal() {
        String[] adn = {
                "AAAAAA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrarse ADN mutante true horizontalmente");
    }

    @Test
    public void testIsMutant_SecuenciaVertical() {
        String[] adn = {
                "ATGCGA",
                "AAGTGC",
                "ATATGT",
                "AGAAGG",
                "ACCCCT",
                "ATCACT"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrarse ADN mutante true verticalmente");
    }

    @Test
    public void testIsMutant_SecuenciaDiagonalIzqDer() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería detectar ADN mutante true en diagonal");
    }

    @Test
    public void testIsMutant_SecuenciaDiagonalDerIzq() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrar ADN mutante en diagonal");
    }

    @Test
    public void testIsMutant_SecuenciaHorizontal2() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTTTTT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrar ADN mutante true de forma horizontal");
    }

    @Test
    public void testIsMutant_SecuenciaVertical2() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCCC",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrar ADN mutante true de forma vertical");
    }

    @Test
    public void testIsMutant_SecuenciaDiagonalIzqDer2() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrar ADN mutante true en diagonal");
    }

    @Test
    public void testIsMutant_SecuenciaDiagonalDerIzq2() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería mostrar ADN mutante true en diagnoal");
    }

    @Test
    public void testIsMutant_SecuenciaHorizontal3() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "GGGGGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería detectar ADN mutante true");
    }

    @Test
    public void testIsMutant_SecuenciaVertical3() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCCCCC"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería detectar ADN mutante true");
    }

    @Test
    public void testIsMutant_SecuenciaDiagonalIzqDer3() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería detectar ADN mutante true");
    }
    @Test
    public void testIsMutant_SecuenciaDiagonalDerIzq3() {
        String[] adn = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean resultado = adnService.isMutant(adn);
        assertEquals(true, resultado, "Debería detectar ADN mutante true");
    }
    @Test
    public void testSaveADN() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        boolean isMutant = false;
        adnService.saveADN(String.join(",", dna), isMutant);
        ArgumentCaptor<ADN> adnCaptor = ArgumentCaptor.forClass(ADN.class);
        verify(adnRepository).save(adnCaptor.capture());
        ADN capturedADN = adnCaptor.getValue();
        assertEquals(String.join(",", dna), capturedADN.getSecuencia());
        assertEquals(isMutant, capturedADN.isEsMutante());
    }
    @Test
    public void testIsMutant_InvalidDNA() {
        String[] dna = {"ATGCGA","CAGTGC"};
        assertThrows(IllegalArgumentException.class, () -> adnService.isMutant(dna));
    }
    @Test
    public void testIsMutant_InvalidCharacters() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTGX"};
        assertThrows(IllegalArgumentException.class, () -> adnService.isMutant(dna));
    }
}
