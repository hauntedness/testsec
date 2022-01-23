package com.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!Objects.equals("admin", username)) {
            throw new UsernameNotFoundException("user not found");
        }

        String encodedPassword = passwordEncoder.encode("123456");
        List<GrantedAuthority> roleAndAuthorityList = AuthorityUtils
                .commaSeparatedStringToAuthorityList("admin,normal,ROLE_roleabc");
        return new User("admin", encodedPassword, roleAndAuthorityList);
    }

}
