package online.bankapp.authservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.model.MyUserPrincipal;
import online.bankapp.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialUserEntity;
import org.springframework.security.web.webauthn.management.PublicKeyCredentialUserEntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaPublicKeyCredentialUserEntityRepository implements PublicKeyCredentialUserEntityRepository {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public PublicKeyCredentialUserEntity findById(Bytes id) {
        log.info("Finding user by ID: {}", id);
        var userId = getIdFromBytes(id);
        log.debug("Converted bytes to user ID: {}", userId);

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            log.debug("User found with ID {}: {}", userId, userOptional.get());
            return new MyUserPrincipal(userOptional.get());
        }

        log.debug("No user found with ID: {}", userId);
        return null;
    }

    @Override
    public PublicKeyCredentialUserEntity findByUsername(String username) {
        log.debug("Finding user by username: {}", username);
        PublicKeyCredentialUserEntity userEntity = (MyUserPrincipal) userDetailsService.loadUserByUsername(username);
        log.debug("User found for username {}: {}", username, userEntity);
        return userEntity;
    }

    @Transactional
    @Override
    public void save(PublicKeyCredentialUserEntity userEntity) {
        log.info("Saving user entity: {}", userEntity);
        if (userEntity instanceof MyUserPrincipal myUserPrincipal) {
            User user = myUserPrincipal.getUser();
            log.debug("Extracted user from principal: {}", user);

            // If user has no ID, it's a new user - save it
            if (user.getId() == null) {
                log.debug("User has no ID, creating new user: {}", user);
                userRepository.save(user);
                log.debug("New user saved with ID: {}", user.getId());
                return;
            }

            // If user exists, verify it's still valid
            Optional<User> existingUser = userRepository.findById(user.getId());
            if (existingUser.isEmpty()) {
                // User was deleted somehow - recreate it
                log.debug("User with ID {} does not exist in database, recreating: {}", user.getId(), user);
                userRepository.save(user);
                log.debug("User recreated with ID: {}", user.getId());
            } else {
                log.debug("Existing user found, no action needed: {}", existingUser.get());
            }
        } else {
            log.error("Invalid user entity type: expected MyUserPrincipal but got {}", userEntity.getClass().getSimpleName());
            throw new IllegalArgumentException("Expected MyUserPrincipal but got: " + userEntity.getClass().getSimpleName());
        }

        // If user exists and is valid, no action needed
        // Core user properties (ID, email, createdAt) are immutable
    }

    @Transactional
    @Override
    public void delete(Bytes id) {
        log.info("Deleting user with byte ID: {}", id);
        var userId = getIdFromBytes(id);
        log.debug("Converted bytes to user ID: {}", userId);
        userRepository.deleteUserById(userId);
        log.debug("User with ID {} deleted", userId);
    }

    private Long getIdFromBytes(Bytes id) {
        log.trace("Converting Bytes to Long ID: {}", id);
        byte[] idBytes = id.getBytes();
        Long userId = ByteBuffer.wrap(idBytes).getLong();
        log.trace("Converted byte array of length {} to user ID: {}", idBytes.length, userId);
        return userId;
    }
}