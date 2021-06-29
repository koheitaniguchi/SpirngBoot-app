package jp.co.cybermissions.itspj.java.gymapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PublicController {
    
    @GetMapping("")
    public String index() {
        return "all/index";
    }

    @GetMapping("/news")
    public String news() {
        return "all/news";
    }

}
