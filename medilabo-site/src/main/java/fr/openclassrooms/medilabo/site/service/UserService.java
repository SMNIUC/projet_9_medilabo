package fr.openclassrooms.medilabo.site.service;

import fr.openclassrooms.medilabo.site.domain.User;
import fr.openclassrooms.medilabo.site.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public void saveCurrentUser( MultiValueMap<String, String> formData )
    {
        if( formData.getFirst( "username" ) == null || formData.getFirst( "password" ) == null )
        {
            return;
        }
        userRepository.saveUser( formData.getFirst("username"), formData.getFirst("password") );
    }

    public User getLastUser( )
    {
        List<User> userList = userRepository.getAllUsers( );
        return userList.get( userList.size( ) - 1 );
    }
}
