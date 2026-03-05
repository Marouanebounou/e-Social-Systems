package org.example.demo.services;

import org.example.demo.DAO.AssureDAO;
import org.example.demo.DAO.EmployerDao;
import org.example.demo.models.Assure;
import org.example.demo.models.Employer;

import java.util.List;

public class AssureService {
    private final AssureDAO assureDAO;
    private final EmployerDao employerDao;

    public AssureService(){
        assureDAO = new AssureDAO();
        employerDao = new EmployerDao();
    }

    public List<Assure> listerAssures() {
        return assureDAO.findAll();
    }

    public void ajouterAssure(Assure assure){
        if (assure != null){
            assureDAO.save(assure);
        }else {
            System.err.println("Erreur : Employeur introuvable !");
        }
    }

    public void updateSalaire(long id , double salaire){
        if (salaire > 0){
            assureDAO.update(id , salaire);
        }
    }

    public List<Assure> consulterAssuresParEmployeur(Long employeurId) {
        return assureDAO.getByEmployeur(employeurId);
    }

    public List<Assure> listerAssuresParEmployeur(long id){
        return assureDAO.getByEmployeur(id);
    }

}
