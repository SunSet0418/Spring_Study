package DAO;

import Model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface Mapper {

    UserModel getUser(@Param("id") String id, @Param("password") String Password);

    void insertUser(@Param("username") String username, @Param("id") String id, @Param("password") String password);

    void deleteUser(@Param("id") String id);

    UserModel checkUser(@Param("id") String id);

}
