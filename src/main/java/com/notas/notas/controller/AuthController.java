package com.notas.notas.controller;

import com.notas.notas.model.AppUser;
import com.notas.notas.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class AuthController {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder ) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new RegisterDTO("",""));
        System.out.println("register view");
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDTO") @Validated RegisterDTO registerDTO,Model model){
        System.out.println("register REGISTRO");
        if(appUserRepository.existsByUserName(registerDTO.userName)){
            model.addAttribute("error","El usuario ya existe");
            return "register";
        }
        AppUser appUser = new AppUser();
        appUser.setUserName(registerDTO.userName);
        appUser.setPassword(passwordEncoder.encode(registerDTO.password));
        appUser.setRol("ROLE_USER");
        appUserRepository.save(appUser);

        return "redirect:/login";
    }

    public record RegisterDTO(@NotBlank String userName, @NotBlank String password){}

}
