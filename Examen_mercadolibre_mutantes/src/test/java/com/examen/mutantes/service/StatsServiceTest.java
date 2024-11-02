package com.examen.mutantes.service;
import com.examen.mutantes.model.Stats;
import com.examen.mutantes.repository.ADNRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class StatsServiceTest {
    @Mock
    private ADNRepository adnRepository; // Mock the repository

    @InjectMocks
    private StatsService statsService; // Inject the mock into the service
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }
    @Test
    public void testGetStatsWithHumanAndMutantADN() {
        long expectedMutants = 40;
        long expectedHumans = 100;
        double expectedRatio = 0.4;
        when(adnRepository.countByEsMutante(true)).thenReturn(expectedMutants);
        when(adnRepository.countByEsMutante(false)).thenReturn(expectedHumans);
        Stats result = statsService.getStats();
        assertEquals(expectedMutants, result.getContadorADNMutante(), "El contador de ADN mutante debería coincidir");
        assertEquals(expectedHumans, result.getContadorADNHumano(), "El contador de ADN humano debería coincidir");
        assertEquals(expectedRatio, result.getRatio(), 0.001, "El ratio debería ser correcto");
    }
    @Test
    public void testGetStatsWithNoHumans() {
        long expectedMutants = 10;
        long expectedHumans = 0;
        double expectedRatio = 0.0;
        when(adnRepository.countByEsMutante(true)).thenReturn(expectedMutants);
        when(adnRepository.countByEsMutante(false)).thenReturn(expectedHumans);
        Stats result = statsService.getStats();
        assertEquals(expectedMutants, result.getContadorADNMutante(), "El contador de ADN mutante debería coincidir");
        assertEquals(expectedHumans, result.getContadorADNHumano(), "El contador de ADN humano debería coincidir");
        assertEquals(expectedRatio, result.getRatio(), "Ratio debería ser 0 porque no hay humanos");
    }
}