package com.khushee.restfulwebservice.controller;

import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;
import com.khushee.restfulwebservice.service.UserService;
import com.khushee.restfulwebservice.utils.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    //inject UserService

    private final UserService userService;
    UserRestController(UserService userService){
        this.userService=userService;
    }
    private boolean isUserExist(int id) {
        User user = userService.findUserByID(id);
        return user!=null;
    }
    private Response<User>notFondUser(int id){
        return Response.<User>notFound().setMessage("User not Exist..").setSuccess(false).setStatus(Response.Status.NOT_FOUND);
    }

    @GetMapping("/all-users")
    public Response<List<User>>getAllUser(){
        try{
            List<User> users = userService.allUsers();
            return Response.<List<User>>ok().setPayload(users).setMessage("Successfully retried all users!");

        }catch (Exception e){
            return Response.<List<User>>exception().setMessage("Fail to retried users!");
        }
    }
    @GetMapping("/{id}")
    public Response<User> findUserByID(@PathVariable int id){
       try{
           if(isUserExist(id)){
               User user = userService.findUserByID(id);
               return Response.<User>ok().setPayload(user).setMessage("User found!");
           }else {
               return notFondUser(id);
           }
       }catch (Exception e){
           return Response.<User>exception().setMessage("User Does not Exits");
       }
    }
    @PostMapping("/new-user")
    public Response<User> createUser(@RequestBody User user){
        try{
         return Response.<User>createSuccess().setPayload(user).setMessage("User has been Created !");
        }catch (Exception e){
            return Response.<User>exception().setMessage("Cannot create User");
        }
    }

    @GetMapping("/user-accounts")
    public Response<List<UserAccount>>getAllUserAccounts(){
        try{
            List<UserAccount> data= userService.getAllUserAccounts();
            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("successfully retrived all users account");

        }catch (Exception ex){
            return Response.<List<UserAccount>>exception().setMessage("Faild to retried all account users data").setSuccess(false);
        }
    }

    @GetMapping("/update-user/{id}")
    public Response<User> updateUserInfo(@PathVariable int id, @RequestBody User user){
        try{
           if(isUserExist(id)){
               user.setUserId(id);
               userService.updateUser(user, id);
               return Response.<User>updateSuccess().setPayload(user).setMessage("Data has been Update");
           }else{
              return notFondUser(id);
           }

        }catch (Exception ex){
           return Response.<User>exception().setMessage("Can't update Data").setSuccess(false);
        }
    }

    @DeleteMapping("/remove-user/{id}")
    public Response<User> deleteUserById(@PathVariable int id){
        try{
            if(isUserExist(id)){
                userService.removeUser(id);
                return Response.<User>deleteSuccess().setMessage("User has been deleted!");
            }else {
                return notFondUser(id);
            }
        }catch (Exception e){
            return Response.<User>exception().setMessage("Delete not Success!!!");
        }
    }

}
