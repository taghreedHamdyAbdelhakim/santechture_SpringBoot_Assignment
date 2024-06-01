package com.santechture.api.service;

import com.santechture.api.entity.Admin;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class AdminService implements UserDetailsService {


    private final AdminRepository adminRepository;


    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public UserDetails login(String username) throws BusinessExceptions {

        log.info("login");
        Admin admin = adminRepository.findByUsernameIgnoreCase(username);

        log.info("loadUserByUsername");
        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), new ArrayList<>());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsernameIgnoreCase(username);

        log.info("loadUserByUsername");
        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), new ArrayList<>());

    }
}
