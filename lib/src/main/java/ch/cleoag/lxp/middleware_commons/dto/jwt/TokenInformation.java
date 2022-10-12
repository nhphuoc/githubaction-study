package ch.cleoag.lxp.middleware_commons.dto.jwt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenInformation {

    @JsonProperty("sub")
    private String userId;

    @JsonProperty("nu-uuid")
    private String nuUserId;

    @JsonProperty("realm_access")
    private RealmAccess realmAccess;

    @JsonProperty("org-uuid")
    private String organisationId;
}
