package com.mili.app.rest.Controller;

import com.mili.app.rest.Models.User;
import com.mili.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping(value = "/") //"/" is the api end point
    public String getPage(){
        return "Welcome";
    }
    @GetMapping(value="/users")  //api end point
    public List<User> getUsers(){
        return userRepo.findAll(); //find all the user of user repo
    }
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){  // @RequestBody annotation maps the HttpRequest body to a transfer or domain object,
        userRepo.save(user);
        return "Data saved successfully";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User UpdatedUser=userRepo.findById(id).get();
        UpdatedUser.setFirstName(user.getFirstName());
        UpdatedUser.setLastName(user.getLastName());
        UpdatedUser.setOccupation(user.getOccupation());
        UpdatedUser.setAge(user.getAge());
        userRepo.save(UpdatedUser);
        return "Update Successful";
    }
    @DeleteMapping(value="/delete/{id}")
    public String deleteuser(@PathVariable long id){
        User deleteUser= userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with the id: "+id;
    }

}
