package org.example.demo.services;

import org.example.demo.DAO.DeclarationDAO;
import org.example.demo.models.Declaration;

import java.util.List;

public class DeclarationService {

    private DeclarationDAO declarationDao;

    public DeclarationService() {
        this.declarationDao = new DeclarationDAO();
    }

    public void creerDeclaration(Declaration declaration) throws Exception {
        boolean isUnique = declarationDao.isUnique(
                declaration.getEmployer().getId(),
                declaration.getMois(),
                declaration.getAnnee()
        );

        if (!isUnique) {
            throw new Exception("Une déclaration existe déjà pour cet employeur pour ce mois et cette année.");
        }

        declarationDao.save(declaration);
    }

    public List<Declaration> listerDeclarations() {
        return declarationDao.findAll();
    }
}