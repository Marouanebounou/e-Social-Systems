package org.example.demo.services;

import org.example.demo.DAO.CotisationDAO;
import org.example.demo.models.Assure;
import org.example.demo.models.Cotisation;
import org.example.demo.models.Declaration;

import java.util.List;

public class CotisationService {

    private CotisationDAO cotisationDao;

    private static final double TAUX_SALARIAL = 0.0448;
    private static final double TAUX_PATRONAL = 0.0898;
    private static final double PLAFOND_SALAIRE = 6000.00;

    public CotisationService() {
        this.cotisationDao = new CotisationDAO();
    }

    public Cotisation calculerCotisation(Assure assure, Declaration declaration) {
        double salaireDeBase = assure.getSalaireMensuel();
        double salairePlafonne = Math.min(salaireDeBase, PLAFOND_SALAIRE);

        double cotisationSalariale = salairePlafonne * TAUX_SALARIAL;
        double cotisationPatronale = salairePlafonne * TAUX_PATRONAL;

        Cotisation cotisation = new Cotisation();
        cotisation.setSalaireDeclare(salaireDeBase);
        cotisation.setAssure(assure);
        cotisation.setDeclaration(declaration);
        cotisation.setCotisationSalariale(cotisationSalariale);
        cotisation.setCotisationPatronale(cotisationPatronale);


        return cotisation;
    }

    public void enregistrerCotisations(List<Cotisation> cotisations) {
        for (Cotisation cot : cotisations) {
            cotisationDao.save(cot);
        }
    }

    public double calculerTotalParEmployeur(Declaration declaration) {
        double total = 0;
        for (Cotisation cot : declaration.getCotisation()) {
            total += (cot.getCotisationPatronale() + cot.getCotisationSalariale());
        }
        return total;
    }
}