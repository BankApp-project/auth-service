package online.bankapp.authservice.repository;

import lombok.RequiredArgsConstructor;
import online.bankapp.authservice.model.MyUserPrincipal;
import online.bankapp.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialUserEntity;
import org.springframework.security.web.webauthn.management.PublicKeyCredentialUserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaPublicKeyCredentialUserEntityRepository implements PublicKeyCredentialUserEntityRepository {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public PublicKeyCredentialUserEntity findById(Bytes id) {
        UUID userId = getUUIDFromBytes(id);

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return new MyUserPrincipal(userOptional.get());
        }

        return null;
    }

    @Override
    public PublicKeyCredentialUserEntity findByUsername(String username) {
        return (MyUserPrincipal) userDetailsService.loadUserByUsername(username);
    }

    @Override
    public void save(PublicKeyCredentialUserEntity userEntity) {
        MyUserPrincipal principal = (MyUserPrincipal) userEntity;
        User user = principal.getUser();

        // If user has no ID, it's a new user - save it
        if (user.getId() == null) {
            userRepository.save(user);
            return;
        }

        // If user exists, verify it's still valid
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty()) {
            // User was deleted somehow - recreate it
            userRepository.save(user);
        }

        // If user exists and is valid, no action needed
        // Core user properties (ID, email, createdAt) are immutable in your design
    }

    @Override
    public void delete(Bytes id) {
        var userId = getUUIDFromBytes(id);
        userRepository.deleteUserById(userId);
    }

    private UUID getUUIDFromBytes(Bytes id) {
        byte[] idBytes = id.getBytes();
        String idString = new String(idBytes);
        return UUID.fromString(idString);
    }
}