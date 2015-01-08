/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mypayment.paymentgateway.Payment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Komal
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public Payment checkValidation(String cardNumber, String securityNumber, double amount){
        
         Payment f = new Payment();
        try{
        String q = "SELECT e FROM Payment e WHERE e.cardNumber=:cardNumber AND e.securityNumber=:securityNumber AND e.totalAmount >=:amount";
        TypedQuery <Payment> query = getEntityManager().createQuery(q, Payment.class);
        query.setParameter("cardNumber", cardNumber);
        query.setParameter("securityNumber", securityNumber);
        query.setParameter("amount", amount);      
        f=query.getSingleResult();
        if(f!=null){
        return f;
        }
       
          
        }
        catch(Exception exeption)
        {
              return f;
        
        }
         return f;
    }
        public boolean findCardByNumber(String cardNumber){
       
            String q = "SELECT e FROM Payment e WHERE e.cardNumber=:cardNumber";
            TypedQuery <Payment> query = getEntityManager().createQuery(q, Payment.class);
        query.setParameter("cardNumber", cardNumber);
        Payment f = query.getSingleResult();
        if(f!=null){
        return true;
        }
            return false;       
    }
    
}
