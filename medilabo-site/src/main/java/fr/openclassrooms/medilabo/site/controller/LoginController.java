package fr.openclassrooms.medilabo.site.controller;

import fr.openclassrooms.medilabo.site.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class LoginController
{
    private final RestTemplate restTemplate;
    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage( )
    {
        return "/login";
    }

    @PostMapping("/login")
    public String postLoginPage( @RequestBody MultiValueMap<String, String> formData, Model model )
    {
        String url = "http://localhost:8080/login";

        userService.saveCurrentUser( formData );

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>( formData );

        try
        {
            ResponseEntity<String> response = restTemplate.postForEntity( url, requestEntity, String.class );

            if ( response.getStatusCode( ).is3xxRedirection( ) )
            {
                return "redirect:/patients/list";
            }
            else
            {
                model.addAttribute("error", "Invalid username or password");
                return "/login";
            }
        } catch (Exception ex)
        {
            // Log and handle exceptions (e.g., server unreachable, bad request)
            model.addAttribute("error", "Server error");
            return "/login";
        }
    }
}
