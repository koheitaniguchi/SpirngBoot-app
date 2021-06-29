package jp.co.cybermissions.itspj.java.gymapp1.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.cybermissions.itspj.java.gymapp1.model.NewUser;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserDetailsImpl;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserRepository;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/sec")
public class SecretController {
    
    private final NewUserRepository rep;

    @GetMapping("")
    public String index(@AuthenticationPrincipal NewUserDetailsImpl userDetails) {
        System.out.println("user_id:" + userDetails.getUserId());
        System.out.println("email:" + userDetails.getEmail());
        return "sec/index";
    }

    @GetMapping("/news")
    public String news() {
        return "sec/news";
    }



    @GetMapping("/form")
    private String form(@ModelAttribute NewUser newuser) {
        return "sec/form";
    }

    @PostMapping("")
    private String create(@Validated @ModelAttribute NewUser newuser, BindingResult result) {
        if (result.hasErrors()) {
            return "sec/form";
        }
        rep.save(newuser);
        return "redirect:/question";
    }


}
