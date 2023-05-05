package com.khushee.restfulwebservice.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;
import com.khushee.restfulwebservice.model.resquest.UserRequest;
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

    public PageInfo<User> allUsers(int page, int size,String filterName) {
        //pageHelper is here
        PageHelper.startPage(page,size);
        return new PageInfo<>(userRepository.allUsers(filterName));
    }

    @Override
    public User findUserByID(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public int createNewUser(UserRequest user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(UserRequest user, int id) {
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
