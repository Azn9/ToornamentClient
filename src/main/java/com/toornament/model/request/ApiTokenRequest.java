package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toornament.model.enums.Scope;
import java.util.Set;
import lombok.Getter;

@Getter
public class ApiTokenRequest {

    @JsonProperty("grant_type")
    String grantType;

    @JsonProperty("client_id")
    String clientId;

    @JsonProperty("client_secret")
    String clientSecret;

    @JsonProperty("scope")
    String scope;

    public ApiTokenRequest(String grantType, String clientId, String clientSecret, Set<Scope> scopes) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = stringify(scopes);
    }

    private String stringify(Set<Scope> scopeHashSet) {
        for (Scope s : scopeHashSet)
            scope = scope.concat(s.toString() + " ");
        return scope;
    }

}
