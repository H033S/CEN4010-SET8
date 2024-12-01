package fiu.cen.menug.service;

import fiu.cen.menug.model.User;
import fiu.cen.menug.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
