package com.example.movies.controller;

import com.example.movies.dto.OwnerDto;
import com.example.movies.entity.Owner;
import com.example.movies.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SecurityController {
    private OwnerService ownerService;

    public SecurityController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        OwnerDto ownerDto = new OwnerDto();
        model.addAttribute("owner", ownerDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("owner") OwnerDto ownerDto, BindingResult result, Model model)	{
        Owner existingOwner = ownerService.findOwnerByEmail(ownerDto.getEmail());
        if(existingOwner != null && existingOwner.getEmail() != null && !existingOwner.getEmail().isEmpty())	{
            result.rejectValue("email", null, "На этот адрес электронной почты уже зарегистрирована учетная запись");
        }
        if(result.hasErrors())	{
            model.addAttribute("owner", ownerDto);
            return "/register";
        }
        ownerService.saveOwner(ownerDto);
        return "redirect:/register?success";
    }

    @GetMapping("/owners")
    public String owners(Model model)	{
        List<OwnerDto> owners = ownerService.findAllOwners();
        model.addAttribute("owners", owners);
        return "owner";
    }
}

