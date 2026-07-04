package com.jungbauer.securitytest.controller;

import com.jungbauer.securitytest.exception.UserAlreadyExistsException;
import com.jungbauer.securitytest.model.dto.UserRegDto;
import com.jungbauer.securitytest.model.entity.TestUser;
import com.jungbauer.securitytest.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegDto", new UserRegDto());
        return "user/userRegistration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@Valid UserRegDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/userRegistration";
        }

        try {
            TestUser registered = userRegistrationService.registerNewUserAccount(userDto);
            model.addAttribute("regUsername", registered.getFirstName() + " " + registered.getLastName());
            model.addAttribute("regEmail", registered.getEmail());
        } catch (UserAlreadyExistsException uaeEx) {
            System.out.println("User already exists!");
            bindingResult.addError(new ObjectError("user", "An account for that username/email already exists."));
            return "user/userRegistration";
        }
        catch (Exception ex) {
            System.out.println("Other exception happened!" +  ex.getMessage());
            model.addAttribute("message", "reg exception happened!");
            return "error";
        }

        return "user/userRegistrationSuccess";
    }
}
