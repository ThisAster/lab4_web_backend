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

    public Integer clearAttemptsDB(User user) throws Exception {
        String sqlText = "delete from PointAttempt p where p.user = :user";
        String sqlParamText = "user";

        userTransaction.begin();
        Query query = em.createQuery(sqlText)
                .setParameter(sqlParamText, user);

        Integer deletedCount =  query.executeUpdate();
        userTransaction.commit();

        return deletedCount;
    }

    public List<PointAttempt> findAttemptByUserDB(User user, int skip) {
        String sqlText = "select p from PointAttempt p where p.user = :user";
        String sqlParamText = "user";

        Query query = em.createQuery(sqlText, PointAttempt.class)
                .setParameter(sqlParamText, user)
                .setFirstResult(skip)
                .setMaxResults(9);

        return query.getResultList();
    }

    public List<PointAttempt> findAllAttemptsDB() {
        String sqlText = "select p from PointAttempt p";

        Query query = em.createQuery(sqlText);
        return query.getResultList();
    }

}
