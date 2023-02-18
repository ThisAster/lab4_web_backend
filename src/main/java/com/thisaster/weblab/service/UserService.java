package com.thisaster.weblab.service;

import com.thisaster.weblab.database.UserDB;
import com.thisaster.weblab.models.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;

import java.util.ArrayList;

@Singleton
public class UserService {
    @EJB
    private UserDB userDB;

//    public User createUser(String username, String password) {
//        try {
//            final User user = new User();
//            user.setUsername(username);
//            user.setPassword(password);
//
//            return user;
//        } catch (PersistenceException e) {
//            return null;
//        }
//    }

    public void saveUser(User user) throws Exception {
        userDB.saveUserDB(user);
    }

    public void deleteUser(User user) throws Exception {
        userDB.deleteUserDB(user);
    }

    public User findByUsername(String username) {
        return userDB.findUserDB(username);
    }

    public ArrayList<User> findAllUsers() throws Exception {
        return (ArrayList<User>) userDB.findAllUsersDB();
    }

}
