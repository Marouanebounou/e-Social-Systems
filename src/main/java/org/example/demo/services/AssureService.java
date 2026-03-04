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

    public void ajouterAssure(String nom, double salaireMensuel, Long employeurId){
        Employer employer = employerDao.findById(employeurId);

        if (employer != null){
            Assure assure = new Assure();
            assure.setName(nom);
            assure.setSalaireMensuel(salaireMensuel);
            assure.setEmployer(employer);

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

    public List<Assure> listerAssuresParEmployeur(long id){
        return assureDAO.getByEmployeur(id);
    }

}
