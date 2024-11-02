package com.examen.mutantes.service;

import com.examen.mutantes.modelo.ADN;
import com.examen.mutantes.repository.ADNRepository;
import com.examen.mutantes.untils.ADNValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ADNService {
    @Autowired
    private ADNRepository adnRepository;
    private final ADNValidator adnValidator = new ADNValidator();
    public void saveADN(String Adn, boolean isMutant) {
        ADN adn = new ADN();
        adn.setSecuencia(String.join(",", Adn));
        adn.setEsMutante(isMutant);
        adnRepository.save(adn);
    }
    public boolean isMutant(String[] adn) {
        if (!adnValidator.isValid(adn)) {
            throw new IllegalArgumentException("ADN Invalido");
        }
        boolean isMutant = checkMutante(adn);
        ADN adnEntity = new ADN();
        adnEntity.setSecuencia(String.join(",", adn));
        adnEntity.setEsMutante(isMutant);
        return isMutant;
    }
    private boolean checkMutante(String[] adn) {
        return checkHorizontal(adn) || checkVertical(adn) || checkDiagonalIzqDer(adn) || checkDiagonalDerIzq(adn);
    }
    private boolean checkHorizontal(String[] adn) {
        int n = adn.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (secuenciaMutante(adn[i].substring(j, j + 4))) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkVertical(String[] adn) {
        int n = adn.length;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= n - 4; i++) {
                if (secuenciaMutante(adn[i].charAt(j), adn[i + 1].charAt(j), adn[i + 2].charAt(j), adn[i + 3].charAt(j))) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkDiagonalIzqDer(String[] adn) {
        int n = adn.length;
        for (int i = 0; i <= n - 4; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (secuenciaMutante(adn[i].charAt(j), adn[i + 1].charAt(j + 1), adn[i + 2].charAt(j + 2), adn[i + 3].charAt(j + 3))) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkDiagonalDerIzq(String[] adn) {
        int n = adn.length;
        for (int i = 0; i <= n - 4; i++) {
            for (int j = 3; j < n; j++) {
                if (secuenciaMutante(adn[i].charAt(j), adn[i + 1].charAt(j - 1), adn[i + 2].charAt(j - 2), adn[i + 3].charAt(j - 3))) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean secuenciaMutante(String secuencia) {
        return secuencia.equals("AAAA") || secuencia.equals("TTTT") || secuencia.equals("CCCC") || secuencia.equals("GGGG");
    }
    private boolean secuenciaMutante(char c1, char c2, char c3, char c4) {
        return c1 == c2 && c2 == c3 && c3 == c4;
    }
}
