package com.example.crud.controller;

import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listUsers(Model theModel) {
        // get users from db
        List<User> theUsers = userService.findAll();

        // add to the spring model
        theModel.addAttribute("users", theUsers);

        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "users/user-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
        // get the user from the service
        User theUser = userService.findById(theId);

        // set user as a model attribute to pre-populate the form
        theModel.addAttribute("user", theUser);

        // send over to our form
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {
        // save the user
        userService.save(theUser);

        // use a redirect to prevent duplicate submissions
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {
        // delete the user
        userService.deleteById(theId);

        // redirect to /users/list
        return "redirect:/users/list";
    }
}
