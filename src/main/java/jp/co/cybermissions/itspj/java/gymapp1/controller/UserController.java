package jp.co.cybermissions.itspj.java.gymapp1.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.cybermissions.itspj.java.gymapp1.model.NewUser;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserDetailsImpl;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final NewUserRepository rep;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal NewUserDetailsImpl userDetailsImpl, Model model) {
        // model.addAttribute("user",userDetailsImpl.getUserId());
        model.addAttribute("user", rep.findById(userDetailsImpl.getUserId()).get());
        return "user/profile";
    }

    //ユーザー編集
    @GetMapping("/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("newUser", rep.findById(id).get());
        return "user/edit";
    }

    //ユーザー更新
    @PatchMapping("/{id}")
    public String update(@PathVariable long id, @Validated @ModelAttribute NewUser newUser, BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit";
        }
        newUser.setId(id);
        rep.save(newUser);
        return "redirect:/user/profile";
    }

    //ユーザー削除
    @DeleteMapping("/{id}")
    public String destroy(@PathVariable long id) {
        rep.deleteById(id);
        return "redirect:/logout";
    }

}
