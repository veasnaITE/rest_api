package com.khushee.restfulwebservice.service;

import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;
import com.khushee.restfulwebservice.model.resquest.UserRequest;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    User findUserByID(int id);
    int createNewUser(UserRequest user);
    int updateUser(UserRequest user,int id);
    int removeUser(int id);

    List<UserAccount> getAllUserAccounts();
}
