package com.ensat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.User;
import com.ensat.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

    public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
     * List all Users.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {
    	try{
    		model.addAttribute("users", userService.listAllUsers());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}        
        return "users";
    }

    /**
     * View a specific user by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("user/{id}")
    public String showVille(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "usershow";
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("user/new")
    public String newVille(Model model) {
        model.addAttribute("user", new User());
        return "userform";
    }

    /**
     * Save product to database.
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveVille(User user) {
    	userService.saveUser(user);
        return "redirect:/user/" + user.getIduser();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("user/delete/{id}")
    public String delete(@PathVariable Integer id) {
    	userService.deleteUser(id);
        return "redirect:/users";
    }
}
