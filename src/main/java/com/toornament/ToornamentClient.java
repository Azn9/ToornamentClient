package com.toornament;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.toornament.concepts.*;
import com.toornament.model.Match;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.ApiTokenRequest;
import com.toornament.model.response.ApiTokenResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToornamentClient {

    public static LoggerLevel loggerLevel = LoggerLevel.DEBUG;

    private final OkHttpClient httpClient;
    private final String       apiKey;
    private final String       clientId;
    private final String       clientSecret;
    private final Set<Scope>   scope;
    private final ObjectMapper mapper;

    private String oAuthToken;

    public ToornamentClient(
        String apiKey, String clientId, String clientSecret, HashSet<Scope> scope) {
        this.apiKey = apiKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.httpClient = new OkHttpClient();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        authorize();
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Set<Scope> getScope() {
        return this.scope;
    }

    public OkHttpClient getHttpClient() {
        return this.httpClient;
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }

    public Participants getParticipants(String tournamentId) {
        return new Participants(this, tournamentId);
    }

    public Tournaments getTournaments() {
        return new Tournaments(this);
    }

    public Disciplines getDisciplines() {
        return new Disciplines(this);
    }

    public Matches getMatches(TournamentDetails tournament) {
        return new Matches(this, tournament);
    }

    public Groups getGroups(String tournamentId) {
        return new Groups(this, tournamentId);
    }

    public FinalStandings getFinalStandings() {
        return new FinalStandings(this);
    }

    public MatchGames getMatchGames(String tournamentId, String matchId) {
        return new MatchGames(this, tournamentId, matchId);
    }

    public MatchGames getMatchGames(Match match) {
        return new MatchGames(this, match);
    }

    public Permissions getPermissions(String tournamentID) {
        return new Permissions(this, tournamentID);
    }

    public Registrations getRegistrations(String tournamentID) {
        return new Registrations(this, tournamentID);
    }

    public MatchReports getReports(String tournamentID) {
        return new MatchReports(this, tournamentID);
    }

    public Rounds getRounds(String tournamentID) {
        return new Rounds(this, tournamentID);
    }

    public Streams getStreams(String tournamentID) {
        return new Streams(this, tournamentID);
    }

    public Users getUsers() {
        return new Users(this);
    }

    public Webhooks getWebhooks() {
        return new Webhooks(this);
    }

    public void authorize() {
        ApiTokenRequest tokenRequest =
            new ApiTokenRequest("client_credentials", clientId, clientSecret, scope);
        Request.Builder requestBuilder = new Request.Builder();
        try {
            requestBuilder.url(
                "https://api.toornament.com/oauth/v2/token"
                    + "?grant_type="
                    + tokenRequest.getGrantType()
                    + "&"
                    + "client_id="
                    + tokenRequest.getClientId()
                    + "&"
                    + "client_secret="
                    + tokenRequest.getClientSecret()
                    + "&"
                    + "scope="
                    + tokenRequest.getScope());
            Request request = requestBuilder.build();
            Response response = executeRequest(request);
            assert response.body() != null;
            this.oAuthToken =
                mapper.readValue(response.body().string(), ApiTokenResponse.class).getAccessToken();
        } catch (IOException e) {
            logger.error("Issue authorizing client: {}", e.getMessage());
        }
    }

    public Request.Builder getAuthenticatedRequestBuilder() {
        if (this.oAuthToken == null) {
            authorize();
        }
        logger.info(oAuthToken);
        return getRequestBuilder().addHeader("Authorization", "Bearer " + oAuthToken);
    }

    public Request.Builder getRequestBuilder() {
        return new Request.Builder().addHeader("X-Api-Key", this.apiKey);
    }

    public Response executeRequest(Request request) {
        try {
            return this.httpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum LoggerLevel {

        DEBUG,
        NONE;

    }

}
