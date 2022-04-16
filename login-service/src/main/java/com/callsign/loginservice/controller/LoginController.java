package com.callsign.loginservice.controller;

import com.callsign.loginservice.entity.UserEntity;
import com.callsign.loginservice.exception.UserUnauthorizedException;
import com.callsign.loginservice.service.UserService;
import com.callsign.loginservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.validation.Valid;
import java.util.List;

/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final UserService userService;
    private JwtUtil jwtUtil;

    @GetMapping
    @Operation(summary = "Used to list all available Users", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    List<UserDetails> all() {
        return userService.findAll();
    }


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
                return jwtUtil.generateJWTToken(userName, user.getId());
            } else {
                throw new UserUnauthorizedException("Wrong UserName or Password");
            }
        } else {
            throw new UserUnauthorizedException("User does not exist.");
        }
    }
}
