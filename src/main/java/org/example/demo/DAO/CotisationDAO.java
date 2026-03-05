package org.example.demo.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.demo.models.Assure;
import org.example.demo.models.Cotisation;
import org.example.demo.models.Declaration;
import org.example.demo.utils.JPAUtil;

import java.util.List;

public class CotisationDAO {
    public void save(Cotisation cotisation){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(cotisation);
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

    public List<Cotisation> findAll(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Cotisation a" , Cotisation.class).getResultList();
        }finally {
            em.close();
        }
    }

    public void update(Cotisation cotisation) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cotisation);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cotisation c = em.find(Cotisation.class, id);
            if (c != null) em.remove(c);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Cotisation findById(long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Cotisation.class, id);
        } finally {
            em.close();
        }
    }

    public double sumCotisations(Long assureId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(c.salaireDeclare) FROM Cotisation c WHERE c.assure.id = :assureId", Double.class);
            query.setParameter("assureId", assureId);

            Double total = query.getSingleResult();

            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }


    public long countMoisDeclares(Long assureId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM Cotisation c WHERE c.assure.id = :assureId", Long.class);
            query.setParameter("assureId", assureId);

            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Cotisation> findByAssure(long assureId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cotisation c WHERE c.assure.id = :id", Cotisation.class)
                    .setParameter("id", assureId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
