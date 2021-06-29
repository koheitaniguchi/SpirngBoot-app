package jp.co.cybermissions.itspj.java.gymapp1.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.cybermissions.itspj.java.gymapp1.model.NewUser;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserDetailsImpl;
import jp.co.cybermissions.itspj.java.gymapp1.model.NewUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final NewUserRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        NewUser user = rep.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username + "not fond.");
        }
        return new NewUserDetailsImpl(user);
    }

}
