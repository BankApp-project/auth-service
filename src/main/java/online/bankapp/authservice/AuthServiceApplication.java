package online.bankapp.authservice;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import online.bankapp.authservice.model.User;
import online.bankapp.authservice.model.vo.EmailAddress;
import online.bankapp.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AuthServiceApplication {

    @Autowired
    private UserRepository repo;

    public static void main(String[] args) {
        log.info("App starting...");
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @PostConstruct
    public void addDefaultUser() {
        if (repo.existsByEmail(new EmailAddress("test@bankapp.online"))) {
            log.info("default user already existed in DB");
            return;
        }
        var user = new User(new EmailAddress("test@bankapp.online"));
        user.setPassword("pass");
        repo.save(user);
        log.info("User added!");
    }
}
