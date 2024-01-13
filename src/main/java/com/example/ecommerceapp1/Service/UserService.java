package com.example.ecommerceapp1.Service;

import com.example.ecommerceapp1.Entity.User;
import com.example.ecommerceapp1.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private TokenService tokenService;
    @Autowired
    public  UserService(UserRepository userRepository, TokenService tokenService){
        this.userRepository=userRepository;
        this.tokenService=tokenService;
    }

    //Get an User
    public User getUser(ObjectId id){
        Optional<User> optionalUser=userRepository.findById(id);
        return optionalUser.orElseGet(optionalUser::get);
    }

    //Signup an User
    public String signup(User user){
        User savedUser=userRepository.save(user);
        return
                "{"+
                        "\"message\":"+"Succesfully Created User\",\n"+
                        "\"data\":"+savedUser+",\n"+
                        "}";

    }
    //Login an User
    public  String login(String username, String password){
        List<User> foundUsers=userRepository.getUserByUsername(username);
        if(foundUsers.isEmpty()){
            return "Authentication Failed User Not Found";
        }else if(!foundUsers.get(0).getPassword1().equals(password) && !foundUsers.get(0).getPassword2().equals(password)){

            return "Password Incorrect";
        }
        return "{\n" +
                "\"message\":"+"\" Successfully Logged-in\",\n"+
                "\"data\": {\n"+" Name : "+foundUsers.get(0).getUsername()+",\n"+
                "Email : "+foundUsers.get(0).getEmail()+"\n"+
                "\"token\":\""+tokenService.createToken(foundUsers.get(0).getId())+"\"" +
                "}";
    }
}
