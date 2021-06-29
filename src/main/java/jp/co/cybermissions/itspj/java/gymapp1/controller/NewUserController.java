package jp.co.cybermissions.itspj.java.gymapp1.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.cybermissions.itspj.java.gymapp1.model.NewUser;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserRepository;
import jp.co.cybermissions.itspj.java.gymapp1.model.Role;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NewUserController {
    
    private final BCryptPasswordEncoder passwordEncoder;
    private final NewUserRepository userRep;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") NewUser user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String creataUser(@Validated @ModelAttribute("user") NewUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.isAdmin() ? Role.ROLE_ADMIN.name() : Role.ROLE_USER.name());
        userRep.save(user);
        return "redirect:/login?register";
    }
}
