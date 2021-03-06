package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.ToornamentClient.LoggerLevel;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.Reports;
import com.brentonpoke.toornamentclient.model.Reports.Report;
import com.brentonpoke.toornamentclient.model.enums.Scope;
import com.brentonpoke.toornamentclient.model.request.ReportsQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.LoggerFactory;

public class MatchReports extends Concept {

    private final String tournamentID;

    public MatchReports(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Reports> getReports(String matchID, Map<String, String> header, Map<String, String> paramsMap) {
        Builder urlBuilder = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("reports");
        }
        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            urlBuilder.addQueryParameter(params.getKey(), params.getValue());
        }

        if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
            logger.debug("url: {}", urlBuilder.build().toString());

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("range", header.get("range"))
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, Reports.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException getting Reports");
        }

    }

    public Report getReport(String matchID, String reportID) {
        Builder url = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("reports")
                .addEncodedPathSegment(reportID);
        }

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Reports.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOExcption getting Report");
        }
    }

    public Report createReport(ReportsQuery query, String matchID) {
        Builder url = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("reports");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .post(body)
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Reports.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException creating Report");
        }
    }

    public Report updateReport(ReportsQuery query, String matchID, String reportID) {
        Builder url = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("matches")
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("reports")
                .addEncodedPathSegment(reportID);
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Reports.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException updating Report");
        }
    }
}
