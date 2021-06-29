package jp.co.cybermissions.itspj.java.gymapp1.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class NewUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "ユーザー名を入力してください")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "パスワードを設定してください")
    @Size(min = 2, max = 200)
    private String password;

    private String role;
    private boolean admin;

    @Email
    private String email;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birth;

    @NotBlank
    private String address;

    @NotBlank(message = "氏名を入力してください")
    private String name;
    private int gender;
    private boolean active = true;

    @NotBlank
    private String content;
}
