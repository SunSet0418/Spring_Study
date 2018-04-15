package Controller;

import DAO.Mapper;
import Model.UserModel;
import com.fasterxml.jackson.core.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private Mapper mapper;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("id") String id, @RequestParam("password") String password){

        Map<String, Object> return_data = new HashMap<String, Object>();

        UserModel result_data = mapper.getUser(id, password);

        if(result_data == null){
            return_data.put("success", false);
            return_data.put("message", "User Not Founded");
        }
        else{
            return_data.put("username", result_data.getUsername());
            return_data.put("id", result_data.getId());
            return_data.put("password", result_data.getPassword());
        }

        return return_data;
    }

    @PostMapping("/register")
    public String register(UserModel r_user){

        String return_data;

        UserModel checkdata = mapper.checkUser(r_user.getId());

        if(checkdata == null){
            mapper.insertUser(r_user.getUsername(), r_user.getId(), r_user.getPassword());
            return_data = "success";
        }
        else{
            return_data = "fail";
        }

        return return_data;

    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String body){

        String return_msg;

        String[] r_parse = body.split("=");
        System.out.println("DELETE ID : "+r_parse[1]);
        UserModel check = mapper.checkUser(r_parse[1]);

        if(check == null){
            return_msg = "User Not Founded In Database";
        }
        else {
            mapper.deleteUser(r_parse[1]);
            return_msg = "Success";
        }

        return return_msg;
    }


}
