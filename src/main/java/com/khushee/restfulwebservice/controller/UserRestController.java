package com.khushee.restfulwebservice.controller;

import com.github.pagehelper.PageInfo;
import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;
import com.khushee.restfulwebservice.model.resquest.UserRequest;
import com.khushee.restfulwebservice.service.UserService;
import com.khushee.restfulwebservice.utils.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public Response<PageInfo<User>>getAllUser(@RequestParam (defaultValue = "1") int page, @RequestParam (defaultValue = "5") int size,@RequestParam (defaultValue = " ") String username){

        try{
            PageInfo<User> users = userService.allUsers(page, size,username);
            return Response.<PageInfo<User>>ok().setPayload(users).setMessage("Successfully Retrieved all users!");

        }catch (Exception e){
            return Response.<PageInfo<User>>exception().setMessage("Fail to retried users!");
        }
    }
    @GetMapping("/{id}")
    public Response<User>findUserByID(@PathVariable int id){
        try{
            User response= userService.findUserByID(id);
            if(response != null){
                return Response.<User>ok().setPayload(response).setSuccess(true).setMessage("Successfully Retrieved user with id= "+id);

            }else {
                return Response.<User>notFound().setMessage("User with id= "+id+ "doesn't exist").setSuccess(false);
            }
        }catch (Exception ex){
            return Response.<User>exception().setMessage("Failed to retrieved user with id: "+id );
        }
    }

//    public Response<User> findUserByID(@PathVariable int id){
//       try{
//           if(isUserExist(id)){
//               User user = userService.findUserByID(id);
//               return Response.<User>ok().setPayload(user).setMessage("User found!");
//           }else {
//               return notFondUser(id);
//           }
//       }catch (Exception e){
//           return Response.<User>exception().setMessage("User Does not Exits");
//       }
//    }
    @PostMapping("/new-user")
    public Response<User> createUser(@Valid  @RequestBody UserRequest request){
       try{
          int userID = userService.createNewUser(request) ;
          if(userID>0){
              User response = new User().setUsername(request.getUsername()).setGender(request.getGender()).setAddress(request.getAddress()).setUserId(userID);
              return Response.<User>createSuccess().setPayload(response).setMessage("Add new User Successfully").setSuccess(true);
          }else {
              return Response.<User>badRequest().setMessage("Bad Request ! failed to create User");
          }
       }catch (Exception e){
           return Response.<User>exception().setMessage("Exception occurs ! failed to create new user").setSuccess(false);
       }
    }

//    @GetMapping("/user-accounts")
//    public Response<List<User>>getAllUserAccounts(){
//        try{
//            List<User> response = userService.allUsers();
//            return Response.<List<User>>ok().setPayload(response).setMessage("Successfully retrieved all users! ");
//        }catch (Exception e){
//            return Response.<List<User>>exception().setMessage("Failed to retrieved the Users Exception occured !");
//        }
//    }


    @PutMapping("/{id}")
    public Response<User> updateUserByID(@PathVariable int id, @RequestBody UserRequest request){
        try{
            int affectedRow = userService.updateUser(request, id);
            if(affectedRow>0){
                User response = new User().setUserId(id).setUsername(request.getUsername()).setGender(request.getGender()).setAddress(request.getAddress());
                return Response.<User>updateSuccess().setMessage("Successfully update the user").setPayload(response).setSuccess(true);
            }else {
                return Response.<User>notFound().setMessage("Cannot Update , user with id = "+id+"doesn't exist !").setSuccess(false);
            }
        }catch (Exception e){
            return Response.<User>exception().setMessage("Failed to update user, Exception Occurred! ");
        }
    }
//    public Response<List<UserAccount>>getAllUserAccounts(){
//        try{
//            List<UserAccount> data= userService.getAllUserAccounts();
//            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("successfully retrived all users account");
//
//        }catch (Exception ex){
//            return Response.<List<UserAccount>>exception().setMessage("Faild to retried all account users data").setSuccess(false);
//        }
//    }

//    @GetMapping("/update-user/{id}")
//    public Response<User> updateUserInfo(@PathVariable int id, @RequestBody User user){
//        try{
//           if(isUserExist(id)){
//               user.setUserId(id);
//               userService.updateUser(user, id);
//               return Response.<User>updateSuccess().setPayload(user).setMessage("Data has been Update");
//           }else{
//              return notFondUser(id);
//           }
//
//        }catch (Exception ex){
//           return Response.<User>exception().setMessage("Can't update Data").setSuccess(false);
//        }
//    }

    @DeleteMapping("/{id}")
    public Response<?>deleteUser(@PathVariable int id){
        try{
            int affectRow = userService.removeUser(id);
            if(affectRow>0){
                return Response.<Object>deleteSuccess().setMessage("Successfully remove the user !").setSuccess(true);
            }else {
                return Response.<Object>notFound().setMessage("User with id ="+id+"doesn't exist int our system");
            }
        }catch (Exception e){
            return Response.<Object>exception().setMessage("Exception occurred! failed to detete the user!").setSuccess(false);
        }
    }

//    public Response<User> deleteUserById(@PathVariable int id){
//        try{
//            if(isUserExist(id)){
//                userService.removeUser(id);
//                return Response.<User>deleteSuccess().setMessage("User has been deleted!");
//            }else {
//                return notFondUser(id);
//            }
//        }catch (Exception e){
//            return Response.<User>exception().setMessage("Delete not Success!!!");
//        }
//    }

}
