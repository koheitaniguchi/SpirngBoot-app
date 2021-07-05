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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.cybermissions.itspj.java.gymapp1.model.InquiryUser;
import jp.co.cybermissions.itspj.java.gymapp1.model.InquiryUserRepository;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUser;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserDetailsImpl;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sec")
public class SecretController {

    private final NewUserRepository uRep;
    private final InquiryUserRepository iRep;

    @GetMapping("")
    public String index(@AuthenticationPrincipal NewUserDetailsImpl userDetails, Model model) {
        System.out.println("user_id:" + userDetails.getUserId());
        System.out.println("email:" + userDetails.getEmail());
        System.out.println("birth:" + userDetails.getBirth());
        System.out.println("email:" + userDetails.getEmail());
        System.out.println("address:" + userDetails.getAddress());
        System.out.println("name:" + userDetails.getName());
        System.out.println("gender:" + userDetails.getGender());
        model.addAttribute("list", !iRep.findByCommentsIsNull().isEmpty());
        //↓
        model.addAttribute("user_question_notice", hasReply(userDetails.getUserId()));
        //↑
        return "sec/index";
    }

    //ユーザの質問にコメントが返信されているかどうかを取得する
    private boolean hasReply(long userId) {
        NewUser user = uRep.findById(userId).get();
        for (InquiryUser iu : iRep.findByNewuser(user)) {
            String comments = iu.getComments();
            if (comments !=null) {
                return true;//1件でも返信があればtrueとする
            }
        }
        return false;//該当データがなければfalse
    }

    @GetMapping("/news")
    public String news() {
        return "sec/news";
    }

    // 質問リスト
    @GetMapping("/question")
    public String question(Model model) {
        model.addAttribute("questionlist", iRep.findAll());
        return "sec/question";
    }

    // 質問新規作成
    @GetMapping("/new")
    public String newForm(@ModelAttribute InquiryUser inquiryUser) {
        return "sec/new";
    }

    // 質問データ保存
    @PostMapping("/new")
    private String create(@AuthenticationPrincipal NewUserDetailsImpl userDetails,@Validated @ModelAttribute InquiryUser inquiryuser, BindingResult result) {
        if (result.hasErrors()) {
            return "sec/new";
        }
        //newuser取得
        NewUser user = uRep.findById(userDetails.getUserId()).get();
        //newuserセット
        inquiryuser.setNewuser(user);
        iRep.save(inquiryuser);
        return "redirect:/sec/question";
    }

    //質問編集
    @GetMapping("/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("inquiryUser", iRep.findById(id).get());
        return "sec/edit";
    }

    // 質問更新
    @PatchMapping("/{id}")
    public String update(@PathVariable long id, @Validated @ModelAttribute InquiryUser inquiryUser,
            BindingResult result) {
        if (result.hasErrors()) {
            return "sec/edit";
        }
        inquiryUser.setId(id);
        iRep.save(inquiryUser);
        return "redirect:/sec/question";
    }

    // 質問削除
    @DeleteMapping("/{id}")
    public String destroy(@PathVariable long id) {
        iRep.deleteById(id);
        return "redirect:/sec/question";
    }


}
