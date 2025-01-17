//package fr.openclassrooms.medilabo.site.service;
//
//import fr.openclassrooms.medilabo.site.domain.UserDTO;
//import org.springframework.stereotype.Service;
//import org.springframework.util.MultiValueMap;
//
//@Service
//public class UserDtoService
//{
//    public UserDTO authUserInfo( MultiValueMap<String, String> formData )
//    {
//        UserDTO authUser = new UserDTO( );
//
//        authUser.setUsername( formData.getFirst( "username" ) );
//        authUser.setPassword( formData.getFirst( "password" ) );
//        authUser.setRole( "admin" );
//
//        return authUser;
//    }
//}