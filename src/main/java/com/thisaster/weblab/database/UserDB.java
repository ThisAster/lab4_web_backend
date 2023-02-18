package com.thisaster.weblab.database;

import com.thisaster.weblab.models.User;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.*;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.SystemException;
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
        try {
            user = (User) em.createQuery("select u from User u where u.username = :username")
                    .setParameter("username", username).getResultList().stream().findFirst().orElse(null);;
            return user;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

/*    public User findUserDB(String username) {
        return em.find(User.class, username);
    }*/

    public List<User> findAllUsersDB() {
        Query query = em.createQuery("select u from User u");
        return query.getResultList();
    }

//    public boolean isExist(String username) {
//        try {
//            User user = (User) em.createQuery(" from User u where u.username = :username")
//                    .setParameter("username", username).getSingleResult();
//            if (user == null) {
//                return false;
//            } else {
//                return true;
//            }
//        } catch (NoResultException e) {
//            return false;
//        }
//    }
}
