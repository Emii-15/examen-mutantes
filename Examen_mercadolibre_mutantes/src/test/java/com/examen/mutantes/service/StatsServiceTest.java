package com.examen.mutantes.service;
import com.examen.mutantes.modelo.Stats;
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
        long expectedMutants = 50;
        long expectedHumans = 120;
        double expectedRatio = 0.4;
        when(adnRepository.countByEsMutante(true)).thenReturn(expectedMutants);
        when(adnRepository.countByEsMutante(false)).thenReturn(expectedHumans);
        Stats result = statsService.getStats();
        assertEquals(expectedMutants, result.getContadorADNMutante(), "ADN Mutante coincidente");
        assertEquals(expectedHumans, result.getContadorADNHumano(), "ADN Humano coincidente");
        assertEquals(expectedRatio, result.getRatio(), 0.001, "Ratio = True");
    }
    @Test
    public void testGetStatsWithNoHumans() {
        long expectedMutants = 10;
        long expectedHumans = 0;
        double expectedRatio = 0.0;
        when(adnRepository.countByEsMutante(true)).thenReturn(expectedMutants);
        when(adnRepository.countByEsMutante(false)).thenReturn(expectedHumans);
        Stats result = statsService.getStats();
        assertEquals(expectedMutants, result.getContadorADNMutante(), "El contador de mutantes es coincidente");
        assertEquals(expectedHumans, result.getContadorADNHumano(), "No coincide el contador de humanos");
        assertEquals(expectedRatio, result.getRatio(), "Ratio = 0");
    }
}