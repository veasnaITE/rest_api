package com.khushee.restfulwebservice.service;

import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    User findUserByID(int id);
    int createNewUser(User user);
    int updateUser(User user,int id);
    int removeUser(int id);

    List<UserAccount> getAllUserAccounts();
}
