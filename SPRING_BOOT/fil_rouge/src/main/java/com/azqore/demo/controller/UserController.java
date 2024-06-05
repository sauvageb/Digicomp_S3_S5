package com.azqore.demo.controller;

import com.azqore.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model){
        // mettre un objet User dans le model avec la clé de votre choix
        model.addAttribute("newUser", new User());
        // Affiche la page HTML contenant le formulaire d'ajout d'un utilisateur
        return "user-add";
    }
    // Postmapping qui permettra de recuperer l'objet Task remplit
    @PostMapping("/add")
    public String addUserFormSubmission(User user){
        System.out.println(user);
        return "redirect:/users/add";
    }

}
