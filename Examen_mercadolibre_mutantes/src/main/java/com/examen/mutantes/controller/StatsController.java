package com.examen.mutantes.controller;
import com.examen.mutantes.modelo.Stats;
import com.examen.mutantes.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;
    @GetMapping
    public ResponseEntity<Stats> getStats() {
        Stats stats = statsService.getStats();
        if (stats != null) {
            System.out.println("Se encontraron stats");
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } else {
            System.out.println("No se encontraron stats");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
