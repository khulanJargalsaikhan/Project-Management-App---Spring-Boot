package com.example.pma.controllers;

import com.example.pma.dao.UserAccountRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.UserAccount;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    UserAccountRepository accountRepo;


    @GetMapping("/register")
    public String register(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }

    @PostMapping(path="register/save")
    public String saveUser(@Valid UserAccount user, Errors errors){
        if(errors.hasErrors())
            return "security/register";

        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        accountRepo.save(user);

        return "redirect:/";
    }

    @GetMapping("/userAccounts")
    public String displayUsers(Model model){
        List<UserAccount> userAccounts = accountRepo.findAll();
        model.addAttribute("userAccounts", userAccounts);
        return "security/list-users";
    }

    @GetMapping("/userAccounts/update")
    public String updateUserAccount(@RequestParam("id") long theId, Model model){
        UserAccount theUser = accountRepo.findByUserId(theId);
        model.addAttribute("userAccount", theUser); //this displays the actual form
        return "security/register";
    }

    @GetMapping ("/userAccounts/delete")
    public String deleteUserAccount(@RequestParam("id") Long theId, Model model){
        UserAccount theUser = accountRepo.findByUserId(theId);
        accountRepo.delete(theUser);
        return "redirect:/userAccounts";
    }


}
