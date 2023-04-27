package com.khushee.restfulwebservice.service.serviceImpl;

import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;
import com.khushee.restfulwebservice.repository.UserRepository;
import com.khushee.restfulwebservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public User findUserByID(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public int createNewUser(User user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(User user, int id) {
        return userRepository.updateUser(user,id);
    }

    @Override
    public int removeUser(int id) {
        return userRepository.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userRepository.getAllUserAccounts();
    }
}
