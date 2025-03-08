package fr.openclassrooms.medilabo.site.repo;

import fr.openclassrooms.medilabo.site.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository
{
    private List<User> userList = new ArrayList<>( );

    public List<User> getAllUsers( )
    {
        return userList;
    }

    public void saveUser(String username, String password )
    {
        User user = new User( );
        user.setUsername( username );
        user.setPassword( password );
        userList.add( user );
    }
}
