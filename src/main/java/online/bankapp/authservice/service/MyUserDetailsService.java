package online.bankapp.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.model.EmailAddress;
import online.bankapp.authservice.model.MyUserPrincipal;
import online.bankapp.authservice.model.User;
import online.bankapp.authservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(new EmailAddress(username));
        if (userOptional.isEmpty()) {
            //TODO or just create new user in this case.
            throw new UsernameNotFoundException("There is no account with given email.");
        }
        return new MyUserPrincipal(userOptional.get());
    }
}
