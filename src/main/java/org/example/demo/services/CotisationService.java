package org.example.demo.services;

import org.example.demo.models.Assure;
import org.example.demo.models.Cotisation;

public class CotisationService {
    private static final double TAUX_SALARIAL = 0.0448;
    private static final double TAUX_PATRONAL = 0.0898;
    private static final double PLAFOND_CSS = 6000.0;

    public Cotisation calculerCotisations(Assure assure , double salaireDeclare){
        Cotisation cotisation = new Cotisation();
        cotisation.setAssure(assure);
        cotisation.setSalaireDeclare(salaireDeclare);

        double baseCalcule = Math.min(salaireDeclare , PLAFOND_CSS);
        double partSalariale = baseCalcule * TAUX_SALARIAL;
        double partPatronale = baseCalcule * TAUX_PATRONAL;

        cotisation.setCotisationPatronale(partPatronale);
        cotisation.setCotisationSalariale(partSalariale);

        return cotisation;
    }
}
