package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.ToornamentClient.LoggerLevel;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.Standings;
import com.brentonpoke.toornamentclient.model.enums.Scope;
import com.brentonpoke.toornamentclient.model.request.StandingsQuery;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class FinalStandings extends Concept {

    public FinalStandings(ToornamentClient client) {
        super(client);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Standings> getFinalStandings(String range, StandingsQuery query) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        Request.Builder requestBuilder;
        String scope = "viewer";

        if (client.getScope().contains(Scope.ORGANIZER_RESULT))
            scope = "organizer";

        urlBuilder
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment(scope)
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("standings");

        if (scope.equals("viewer")) {
            if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
                logger.debug("url: {}", urlBuilder.build().toString());
            requestBuilder = client.getRequestBuilder();
        } else {
            if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
                logger.debug("url: {}", urlBuilder.build().toString());
            requestBuilder = client.getAuthenticatedRequestBuilder();
        }

        if (!query.getParticipantIds().isEmpty())
            urlBuilder.addQueryParameter("participant_ids", StringUtils.join(query.getParticipantIds(), ","));
        if (!query.getTournamentIds().isEmpty())
            urlBuilder.addQueryParameter("tournament_ids", StringUtils.join(query.getTournamentIds(), ","));

        Request request = requestBuilder
            .get()
            .url(urlBuilder.build())
            .addHeader("range", range)
            .build();
        String responseBody;
        try {
            responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Standings.class));
        } catch (IOException e) {
            ToornamentException exception = new ToornamentException("Couldn't retrieve standings");
            exception.setError(e.getMessage());
            throw exception;
        }
    }
}
