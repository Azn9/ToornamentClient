package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.ToornamentClient.LoggerLevel;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.Game;
import com.brentonpoke.toornamentclient.model.Match;
import com.brentonpoke.toornamentclient.model.enums.Scope;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.LoggerFactory;

public class MatchGames extends Concept {

    private final String tournamentID;

    private String          matchID;
    private HttpUrl.Builder urlBuilder;

    public MatchGames(ToornamentClient client, String tournamentId, String matchID) {
        super(client);
        this.matchID = matchID;
        this.tournamentID = tournamentId;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public MatchGames(ToornamentClient client, Match match) {
        super(client);
        this.matchID = match.getId();
        this.tournamentID = match.getTournamentId();
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public Game getGame(String number) {
        getURL(number);
        return getGameHelper(urlBuilder);
    }

    public List<Game> getGames(String headers) {
        urlBuilder = new HttpUrl.Builder();

        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addPathSegment("viewer")
                .addPathSegment("v2")
                .addPathSegment("tournaments")
                .addPathSegment(tournamentID)
                .addPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("games");
        }

        if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
            logger.debug("url: {}", urlBuilder.build().toString());

        Request request = client.getRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("Range", headers)
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Game.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException getting games");
        }
    }

    private Game getGameHelper(Builder urlBuilder) {
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOExcption getting game");
        }
    }

    public Game updateGame(String number, Game game) {
        getURL(number);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), game.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(requestBody)
            .url(urlBuilder.build())
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(Game.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException updating game");
        }
    }

    private void getURL(String number) {
        urlBuilder = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {

            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addPathSegment("viewer")
                .addPathSegment("v2")
                .addPathSegment("tournaments")
                .addPathSegment(tournamentID)
                .addPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("games")
                .addPathSegment(number);
        }
    }

}
