package com.revature.SERVICES;

import com.revature.DAOS.UserDAO;
import com.revature.POJOS.UserPojo;

import java.util.List;

public class UserService {
    private UserDAO dao;

    public UserService() {
        this.dao = new UserDAO();
    }

    public UserPojo saveUser(UserPojo userPojo) {
        //validation logic here
        dao.create(userPojo);
        return userPojo;
    }

    public UserPojo getUser(int id) {
        return dao.read(id);
    }

    public List<UserPojo> getAllUsers() {
        return dao.readAll();
    }

    //this userId right here is the one that inputted into the URL in postman, ALWAYS include it in the body
    //of the request in postman. It's necessary in both URL and BODY.
    public void updateUser(UserPojo userPojo, Integer userId) {
        dao.update(userPojo);
    }

    public void adminUpdate(Integer userId, Boolean administrator){
        dao.administratorUpdate(userId, administrator);
    }

    public UserPojo logIn(String credentials, String password) {
        return dao.logInUser(credentials, password);
    }

    public void deleteUser(int id) {
        dao.delete(id);
    }
}