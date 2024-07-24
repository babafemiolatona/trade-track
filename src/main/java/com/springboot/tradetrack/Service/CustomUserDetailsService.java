package com.springboot.tradetrack.Service;

import com.springboot.tradetrack.Models.CustomUserDetails;
import com.springboot.tradetrack.Models.User;
import com.springboot.tradetrack.Dao.UserDao;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     User user = userDao.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    //     return org.springframework.security.core.userdetails.User.builder()
    //             .username(user.getUsername())
    //             .password(user.getPassword())
    //             .roles(user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new))
    //             .build();
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert roles to GrantedAuthority
        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> (GrantedAuthority) () -> role.getName())
                .collect(Collectors.toList());

        // Return an instance of your custom UserDetails
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getId() // Assuming User entity has getId() method
        );
    }
}