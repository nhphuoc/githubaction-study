package ch.cleoag.lxp.middleware_commons.dto.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RealmAccess {
    private Set<String> roles;

    public Set<String> getRoles() {
        if(roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }
}
