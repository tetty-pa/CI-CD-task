package com.epam.esm.service.security;

import com.epam.esm.model.entity.User;
import com.epam.esm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public PersonUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);

        return new PersonUserDetails
                (user.orElseThrow(() -> new UsernameNotFoundException("")));
    }
}
