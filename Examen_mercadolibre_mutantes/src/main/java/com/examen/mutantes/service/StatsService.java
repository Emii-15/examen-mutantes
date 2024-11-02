package com.examen.mutantes.service;
import com.examen.mutantes.modelo.Stats;
import com.examen.mutantes.repository.ADNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StatsService {
    @Autowired
    private ADNRepository adnRepository;

    public Stats getStats() {
        long contADNMutante = adnRepository.countByEsMutante(true);
        long contADNHumano = adnRepository.countByEsMutante(false);
        double ratio;
        if (contADNHumano > 0) {
            ratio = (double)contADNMutante / contADNHumano;
        } else{
            ratio = 0;
        }
        return new Stats(contADNMutante, contADNHumano, ratio);
    }
}














// h2 anda porfavor