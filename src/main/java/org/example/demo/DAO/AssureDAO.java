package org.example.demo.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.demo.models.Assure;
import org.example.demo.utils.JPAUtil;

import java.util.List;

public class AssureDAO {
    public void save(Assure assure){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(assure);
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

    public List<Assure> findAll(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Assure a" , Assure.class).getResultList();
        }finally {
            em.close();
        }
    }

    public Assure findById(long id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Assure.class , id);
        } finally {
            em.close();
        }
    }

    public void update(long id, Double salaireMensuel){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Assure assure = em.find(Assure.class , id);
            if (assure != null){
                assure.setSalaireMensuel(salaireMensuel);
                em.merge(assure);
            }
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
            Assure assure = em.find(Assure.class , id);
            if (assure != null){
                em.remove(assure);
            }
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

    public List<Assure> getByEmployeur(long employeurId){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Assure a where a.employer.id = :id" , Assure.class)
                    .setParameter("id" , employeurId)
                    .getResultList();
        }finally {
            em.close();
        }
    }

}
