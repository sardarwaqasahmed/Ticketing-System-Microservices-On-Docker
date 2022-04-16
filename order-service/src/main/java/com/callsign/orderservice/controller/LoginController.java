package com.callsign.orderservice.controller;

import com.callsign.orderservice.entity.UserEntity;
import com.callsign.orderservice.exception.UserUnauthorizedException;
import com.callsign.orderservice.service.UserService;
import com.callsign.orderservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.validation.Valid;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@RestController
@RequestMapping("/v1/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    /** <p>This api will authenticate the username and password. </p>
     *  <p>Upon successful authentication it will make a call to AuthServer for generation of JWT</p>
     * @param userName
     * @param password
     * @return
     * @throws ServletException
     * @throws EntityNotFoundException
     */
    @PostMapping
    @Operation(summary = "Used to do Login, without login you will not be able to access other services")
    public String login(@Valid @RequestParam(required = true) String userName,
                        @RequestParam(required = true) String password) throws ServletException, EntityNotFoundException {
        UserEntity user = userService.getUserEntiy(userName);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
                return jwtUtil.generateJWTToken(userName);
            } else {
                throw new UserUnauthorizedException("Wrong UserName or Password");
            }
        } else {
            throw new UserUnauthorizedException("User does not exist.");
        }
    }
}
