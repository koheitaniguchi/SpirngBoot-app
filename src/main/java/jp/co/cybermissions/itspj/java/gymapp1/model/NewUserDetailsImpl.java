package jp.co.cybermissions.itspj.java.gymapp1.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class NewUserDetailsImpl extends User {

    private final NewUser user;

    public NewUserDetailsImpl(NewUser user) {
        super(user.getUsername(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(NewUser user) {
        Set<GrantedAuthority> authSet = new HashSet<>();
        authSet.add(new SimpleGrantedAuthority(user.getRole()));
        return authSet;
    }

    public long getUserId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getUserName() {
        return user.getUsername();
    }

    public LocalDate getBirth() {
        return user.getBirth();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getName() {
        return user.getName();
    }


}
