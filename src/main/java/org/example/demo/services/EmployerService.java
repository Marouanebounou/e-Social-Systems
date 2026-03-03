package org.example.demo.services;

import org.example.demo.DAO.EmployerDao;
import org.example.demo.models.Employer;

import java.util.List;

public class EmployerService {
    private final EmployerDao employerDao;

    public EmployerService(){
        this.employerDao = new EmployerDao();
    }

    public void creerEmployeur(String raisonSocial , String secteurActivite){
        Employer employer = new Employer();
        employer.setSecteur_activite(secteurActivite);
        employer.setRaison_social(raisonSocial);
        employerDao.save(employer);
    }

    public List<Employer> listerEmployeurs(){
        return employerDao.findAll();
    }
}
