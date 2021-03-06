package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.ToornamentClient.LoggerLevel;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.RankingItem;
import com.brentonpoke.toornamentclient.model.enums.Scope;
import com.brentonpoke.toornamentclient.model.request.RankingItemQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl.Builder;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Rankings extends Concept {

    private final String tournamentID;

    public Rankings(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    List<RankingItem> getRankingItems(RankingItemQuery query, Map<String, String> header, String stageID) {
        Builder urlBuilder = new Builder();
        if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("stages")
                .addEncodedPathSegment(stageID)
                .addEncodedPathSegment("ranking-items")
                .addQueryParameter("group_ids", StringUtils.join(query.getGroupIDs(), ","))
                .addQueryParameter("group_numbers", StringUtils.join(query.getGroupNumbers(), ","))
                .addQueryParameter("participant_ids", StringUtils.join(query.getParticipantIDs(), ","))
                .addQueryParameter("custom_user_identifiers", StringUtils.join(query.getCustomUserIdentifiers(), ","));
        }

        if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
            logger.debug("url: {}", urlBuilder.build().toString());

        Request request = client.getRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("range", header.get("range"))
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                RankingItem.class));
        } catch (IOException | NullPointerException e) {
            throw new ToornamentException("Couldn't retrieve ranking items from tournament with id " + tournamentID);
        }
    }
}
