//package fr.openclassrooms.medilabo.site.controller;
//
//import fr.openclassrooms.medilabo.site.domain.PatientDTO;
//import fr.openclassrooms.medilabo.site.domain.UserDTO;
//import fr.openclassrooms.medilabo.site.service.UserDtoService;
//import fr.openclassrooms.medilabo.site.webclient.UserWebClient;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//@RequiredArgsConstructor
//public class LoginController
//{
//    private final UserDtoService userService;
//    private final UserWebClient userWebClient;
//
//    @GetMapping("/patient/login")
//    public String getLoginPage( )
//    {
//        return "login";
//    }
//
//    @PostMapping("/patient/login")
//    public void authUser( @RequestBody MultiValueMap<String, String> formData )
//    {
//        UserDTO authUser = userService.authUserInfo( formData );
//        userWebClient.authUser( authUser );
//    }
//}
