package DAO;

import Model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface Mapper {

    public UserModel getUser(@Param("id") String id, @Param("password") String Password);

    public void insertUser(@Param("username") String username, @Param("id") String id, @Param("password") String password);

    public void deleteUser(@Param("id") String id);

    public UserModel checkUser(@Param("id") String id);

}
