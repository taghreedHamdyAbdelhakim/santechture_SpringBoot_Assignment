package com.santechture.api.controller;


import com.santechture.api.configuration.JwtUtil;
import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.dto.jwt.JwtResponse;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.service.AdminService;
import com.santechture.api.validation.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "admin")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest request) throws BusinessExceptions {
        log.info(AdminController.log.getName() + " login");
        try
        {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );}catch (Exception e){
            throw new BusinessExceptions("login.credentials.not.match");
        }

        UserDetails userDetails  = adminService.login(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new GeneralResponse().response(new JwtResponse(jwt));

    }
}
