package online.bankapp.authservice.model;

import online.bankapp.authservice.model.vo.EmailAddress;

import java.time.Instant;
import java.util.Random;

public class TestUserProvider {
    public static User getTestUser() {
        var random = new Random();
        var user = new User();
        user.setId(random.nextLong());
        var emailString = "test@user" + random.nextInt(1000) + ".org";
        user.setEmail(new EmailAddress(emailString));
        user.setCreatedAt(Instant.now());

        return user;
    }
}
