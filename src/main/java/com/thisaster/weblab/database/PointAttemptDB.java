package com.thisaster.weblab.database;

import com.thisaster.weblab.models.PointAttempt;
import com.thisaster.weblab.models.User;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.UserTransaction;

import java.util.List;

@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class PointAttemptDB {

    @PersistenceContext(unitName = "postgres")
    private EntityManager em;
    @Resource
    private UserTransaction userTransaction;

    public void saveAttemptDB(PointAttempt attempt) throws Exception {
        userTransaction.begin();
        em.persist(attempt);
        userTransaction.commit();
    }

    public void deleteAttemptDB(PointAttempt attempt) throws Exception {
        userTransaction.begin();
        em.remove(em.merge(attempt));
        userTransaction.commit();
    }

    public Integer resetDB(User user) throws Exception{
        userTransaction.begin();
        Query query = em.createQuery("delete from PointAttempt p where p.user = :user")
                .setParameter("user", user);
        Integer deletedCount =  query.executeUpdate();
        userTransaction.commit();
        return deletedCount;
    }

    public List<PointAttempt> findAttemptByUserDB(User user, int skip) {
        Query query = em.createQuery("select p from PointAttempt p where p.user = :user", PointAttempt.class)
                .setParameter("user", user).setFirstResult(skip).setMaxResults(3);
        return query.getResultList();
    }

    public List<PointAttempt> findAllAttemptsDB() {
        Query query = em.createQuery("select p from PointAttempt p");
        return query.getResultList();
    }

}
