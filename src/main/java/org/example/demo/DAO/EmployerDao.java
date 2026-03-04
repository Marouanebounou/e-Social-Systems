package org.example.demo.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.demo.models.Employer;
import org.example.demo.utils.JPAUtil;

import java.util.List;

public class EmployerDao {
    public void save(Employer employer){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(employer);
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

    public List<Employer> findAll(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Employer e", Employer.class).getResultList();
        }finally {
            em.close();
        }
    }

    public void update(long id  , String raison_social , String secteur_activite){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employer employer = em.find(Employer.class , id);
            employer.setRaison_social(raison_social);
            employer.setSecteur_activite(secteur_activite);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
    }

    public void delete(long id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employer employer = em.find(Employer.class , id);
            em.remove(employer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
    }

    public Employer findById(long id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Employer.class,id);
        }finally {
            em.close();
        }
    }
}
