package com.khushee.restfulwebservice.repository;

import com.khushee.restfulwebservice.model.Account;
import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {
    @Select("select * from users_tb")
    @Result(column = "id", property = "userId")

    List<User> allUsers();
    @Insert("insert into users_tb (username, gender, address)\n" +
            "values (#{user.username},#{user.gender}, #{user.address})")
    int createNewUser(@Param("user") User user);

    @Update("update users_tb\n" +
            "set username = #{user.username}, gender = #{user.gender}, address = #{user.address}\n" +
            "where id = #{id}")
    int updateUser(@PathVariable  User user,int id);

    @Result(property = "userId", column = "id")
    @Select("select  * from users_tb where id = #{id}")
    User findUserByID(int id );
    @Delete("delete from users_tb\n" +
            "where id = #{id}")
    int removeUser(int id );

    @Results({
            @Result(column = "id", property = "userId"),
            @Result(column = "id", property = "accounts", many = @Many(select = "findAccountsByUserId"))
    })
    @Select("select * from users_tb")
    List<UserAccount> getAllUserAccounts();
    @Results({
            @Result(property = "accountName",column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property ="transferLimit", column = "transfer_limit"),
            @Result(property = "password", column = "passcode"),
            @Result(property = "accountType", column = "account_type",
                    one = @One(select = "com.khushee.restfulwebservice.repository.AccountRepository.getAccountTypeByID"))
    })
    @Select("select * from user_account_tb " +
            "    inner join account_tb " +
            "        a on a.id = user_account_tb.account_id\n" +
            "    where user_id = #{id};")
    List<Account> findAccountsByUserId(int id);
}
