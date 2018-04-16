package Controller;

import DAO.Mapper;
import Model.UserModel;
import com.fasterxml.jackson.core.JsonParser;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> login(@RequestParam("id") String id, @RequestParam("password") String password){

        Map<String, Object> return_data = new HashMap<String, Object>();

        ResponseEntity res_data;

        UserModel result_data = mapper.getUser(id, password);

        if(result_data == null){
            return_data.put("success", false);
            return_data.put("message", "User Not Founded");
            res_data = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(return_data);
        }
        else{
            return_data.put("username", result_data.getUsername());
            return_data.put("id", result_data.getId());
            return_data.put("password", result_data.getPassword());
            res_data = ResponseEntity.ok(return_data);
        }

        return res_data;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(UserModel r_user){

        String return_msg;

        ResponseEntity code;

        UserModel checkdata = mapper.checkUser(r_user.getId());

        if(checkdata == null){
            mapper.insertUser(r_user.getUsername(), r_user.getId(), r_user.getPassword());
            return_msg = "success";
            code = ResponseEntity.ok(return_msg);
        }
        else{
            return_msg = "fail";
            code = ResponseEntity.status(409).body(return_msg);
        }

        return code;

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){

        String return_msg;

        ResponseEntity code;

        UserModel check = mapper.checkUser(id);

        if(check == null){
            return_msg = "User Not Founded In Database";
            code = ResponseEntity.status(400).body(return_msg);
        }
        else {
            mapper.deleteUser(id);
            return_msg = "Success";
            code = ResponseEntity.ok(return_msg);
        }

        return code;

    }


}
