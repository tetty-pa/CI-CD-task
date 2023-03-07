package com.epam.esm.service.security;

import com.epam.esm.model.entity.Role;
import com.epam.esm.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class PersonUserDetails implements UserDetails {

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public PersonUserDetails() {
    }

    public PersonUserDetails(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.authorities =
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_"+
                                Role.getRole(user.getRoleId().getId()-1)
                                        .name()
                                        .toUpperCase(Locale.ROOT)));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
