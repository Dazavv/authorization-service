package org.example.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    GUEST("GUEST"),
    ADMIN("ADMIN"),
    PREMIUM_USER("PREMIUM_USER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
