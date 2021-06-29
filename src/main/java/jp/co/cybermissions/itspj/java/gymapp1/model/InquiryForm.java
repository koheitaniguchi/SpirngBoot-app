package jp.co.cybermissions.itspj.java.gymapp1.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryForm {
    
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String content;
}
