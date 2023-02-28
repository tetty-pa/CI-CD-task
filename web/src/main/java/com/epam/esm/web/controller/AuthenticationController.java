package com.epam.esm.web.controller;



import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.model.entity.User;
import com.epam.esm.model.jwt.AuthenticationRequest;
import com.epam.esm.service.UserService;
import com.epam.esm.web.filter.JwtUtil;
import com.epam.esm.web.link.UserLinkAdder;
import com.epam.esm.web.security.PersonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PersonUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserLinkAdder linkAdder;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, PersonUserDetailsService userDetailsService, JwtUtil jwtUtil, UserService userService, UserLinkAdder linkAdder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.linkAdder = linkAdder;
    }


    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody AuthenticationRequest authenticationRequest) throws EntityNotFoundException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new EntityNotFoundException("user.notfoundById");
        }
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        return jwtUtil.generateToken(userDetails);
    }


    @GetMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Valid User user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(Objects.requireNonNull(bindingResult.
                    getFieldError()).getDefaultMessage());
        }

        userService.create(user);
        linkAdder.addLinks(user);
        return user;
    }

}
