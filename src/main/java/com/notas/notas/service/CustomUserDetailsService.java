//package com.notas.notas.service;
//
//import com.notas.notas.model.AppUser;
//import com.notas.notas.repository.AppUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final AppUserRepository appUserRepository;
//
//    @Autowired
//    public CustomUserDetailsService(AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        AppUser appUser = appUserRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(appUser.getUserName())
//                .password(appUser.getPassword())
//                .authorities(new SimpleGrantedAuthority(appUser.getRol()))
//                .build();
//    }
//}
