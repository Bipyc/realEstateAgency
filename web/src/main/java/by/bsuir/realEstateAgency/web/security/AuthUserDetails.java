package by.bsuir.realEstateAgency.web.security;

import by.bsuir.realEstateAgency.core.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public class AuthUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public AuthUserDetails(User user) {
        super(user.getLogin(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getClass().getSimpleName().toUpperCase())));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
