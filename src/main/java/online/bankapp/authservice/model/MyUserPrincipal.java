package online.bankapp.authservice.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialUserEntity;

import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails, PublicKeyCredentialUserEntity {

    @Getter
    private final User user;

    private List<GrantedAuthority> roles;

    public MyUserPrincipal(User user) {
        this.user = user;
        roles = List.of((new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getEmail().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getName() {
        return this.getUsername();
    }

    @Override
    public Bytes getId() {
        return new Bytes(user.getId().toString().getBytes());
    }

    @Override
    public String getDisplayName() {
        return this.getUsername();
    }
}