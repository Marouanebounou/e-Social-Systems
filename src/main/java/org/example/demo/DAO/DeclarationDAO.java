package org.example.demo.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.demo.models.Assure;
import org.example.demo.models.Cotisation;
import org.example.demo.models.Declaration;
import org.example.demo.utils.JPAUtil;

import java.util.List;

public class DeclarationDAO {
    public void save(Declaration declaration){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(declaration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public List<Declaration> findAll(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Declaration a" , Declaration.class).getResultList();
        }finally {
            em.close();
        }
    }

    public List<Declaration> findByEmployeur(long id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return  em.createQuery("SELECT D FROM Declaration D WHERE D.employer.id = :id",Declaration.class )
                    .setParameter("id" , id)
                    .getResultList();
        }finally {
            em.close();
        }
    }

    public List<Declaration> findByAnne(long id ,int anne){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return  em.createQuery("SELECT D FROM Declaration D WHERE D.annee = :anne AND D.employer.id = :id",Declaration.class )
                    .setParameter("anne" , anne)
                    .setParameter("id" , id)
                    .getResultList();
        }finally {
            em.close();
        }
    }

    public List<Declaration> findByMois(long id ,int mois){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return  em.createQuery("SELECT D FROM Declaration D WHERE D.mois = :mois AND D.employer.id = :id",Declaration.class )
                    .setParameter("mois" , mois)
                    .setParameter("id" , id)
                    .getResultList();
        }finally {
            em.close();
        }
    }

    public Declaration findOne(long employeurId , int mois , int anne){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return  em.createQuery("SELECT D FROM Declaration D WHERE D.mois = :mois AND D.annee = :anne  AND D.employer.id = :id",Declaration.class )
                    .setParameter("mois" , mois)
                    .setParameter("anne" , anne)
                    .setParameter("id" , employeurId)
                    .getSingleResult();
        }finally {
            em.close();
        }
    }

    public List<Cotisation> findAllCotisation(long declarationId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cotisation c WHERE c.declaration.id = :id", Cotisation.class)
                    .setParameter("id", declarationId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
