/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionariatoyota.Persistencia;

import concesionariatoyota.Logica.AutoToyota;
import concesionariatoyota.Persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author diego
 */
public class AutoToyotaJpaController implements Serializable {

    public AutoToyotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AutoToyotaJpaController(){
        emf = Persistence.createEntityManagerFactory("ConcesionariaToyotaPU");
    }
    private EntityManagerFactory emf = null;
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    public void create(AutoToyota autoToyota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(autoToyota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AutoToyota autoToyota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            autoToyota = em.merge(autoToyota);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = autoToyota.getId();
                if (findAutoToyota(id) == null) {
                    throw new NonexistentEntityException("The autoToyota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutoToyota autoToyota;
            try {
                autoToyota = em.getReference(AutoToyota.class, id);
                autoToyota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autoToyota with id " + id + " no longer exists.", enfe);
            }
            em.remove(autoToyota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AutoToyota> findAutoToyotaEntities() {
        return findAutoToyotaEntities(true, -1, -1);
    }

    public List<AutoToyota> findAutoToyotaEntities(int maxResults, int firstResult) {
        return findAutoToyotaEntities(false, maxResults, firstResult);
    }

    private List<AutoToyota> findAutoToyotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutoToyota.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AutoToyota findAutoToyota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutoToyota.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoToyotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutoToyota> rt = cq.from(AutoToyota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
