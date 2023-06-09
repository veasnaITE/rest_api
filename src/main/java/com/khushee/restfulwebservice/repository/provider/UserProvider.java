package com.khushee.restfulwebservice.repository.provider;
import org.apache.ibatis.jdbc.SQL;
public class UserProvider {
    public static String getAllUser(String filterName){
        return new SQL(){{
            SELECT("*");
            FROM("users_tb");
            if(!filterName.isEmpty()){
                WHERE("upper(username) like upper('%'||#{filterName}||'%')");
            }
        }}.toString();
    }
}
