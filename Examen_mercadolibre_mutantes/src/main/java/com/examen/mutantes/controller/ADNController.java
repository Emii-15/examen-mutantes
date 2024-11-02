package com.examen.mutantes.controller;
import com.examen.mutantes.untils.secuencia;
import com.examen.mutantes.service.ADNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/mutant")
public class ADNController {
    @Autowired
    private ADNService adnService;
    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody secuencia secuencia) {
        String[] adn = secuencia.getSec();
        boolean esMutante = adnService.isMutant(adn);
        adnService.saveADN(String.join(",", adn), esMutante);
        if (esMutante) {
            System.out.println("ADN Mutante"); //para testear
            return new ResponseEntity<>("AND Mutante", HttpStatus.OK);
        } else {
            System.out.println("ADN Humano"); //para testear
            return new ResponseEntity<>("ADN Humano", HttpStatus.FORBIDDEN);
        }
    }
}

