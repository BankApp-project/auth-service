package online.bankapp.authservice.repository;

import online.bankapp.authservice.model.User;
import online.bankapp.authservice.model.vo.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(EmailAddress email);

    void deleteUserById(UUID id);
}
