package fr.openclassrooms.medilabo.site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController
{
    @GetMapping("/login")
    public String getLoginPage( )
    {
        return "redirect:http://localhost:8080/login";
    }
}
