package com.thisaster.weblab.database;

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
public class UserDB {

    @PersistenceContext(name="postgres")
    private EntityManager em;
    @Resource
    private UserTransaction userTransaction;

    public void saveUserDB(User user) throws Exception {
            userTransaction.begin();
            em.persist(user);
            userTransaction.commit();
    }

    public void deleteUserDB(User user) throws Exception {
        userTransaction.begin();
        em.remove(em.merge(user));
        userTransaction.commit();
    }

    public User findUserDB(String username) {
        User user;
        String sqlText = "select u from User u where u.username = :username";
        String sqlParamText = "username";

        user = (User) em.createQuery(sqlText)
                    .setParameter(sqlParamText, username).getResultList().stream().findFirst().orElse(null);
        return user;
    }

    public List<User> findAllUsersDB() {
        String sqlText = "select u from User u";

        Query query = em.createQuery(sqlText);
        return query.getResultList();
    }

}
