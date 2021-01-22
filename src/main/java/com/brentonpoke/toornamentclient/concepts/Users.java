package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.ToornamentClient.LoggerLevel;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.User;
import com.brentonpoke.toornamentclient.model.enums.Scope;
import java.io.IOException;
import java.util.Objects;
import okhttp3.HttpUrl.Builder;
import okhttp3.Request;

public class Users extends Concept {

    public Users(ToornamentClient client) {
        super(client);
    }

    public User getUser() {
        Builder url = new Builder();

        if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
            logger.debug("Scopes: {}", client.getScope().toString());

        if (client.getScope().contains(Scope.USER_INFO)) {
            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("account")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("me")
                .addEncodedPathSegment("info");
        }

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't get user");
        }
    }

}
