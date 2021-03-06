package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.ToornamentClient.LoggerLevel;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.Group;
import com.brentonpoke.toornamentclient.model.request.GroupsQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Groups extends Concept {

    private final String tournamentID;

    public Groups(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Group> getGroups(GroupsQuery parameters, Map<String, String> header) {
        HttpUrl.Builder url =
            new HttpUrl.Builder()
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("viewer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("groups");

        if (!parameters.getStageIds().isEmpty())
            url.addQueryParameter("stage_ids", StringUtils.join(parameters.getStageIds(), ","));
        if (!parameters.getStageNumbers().isEmpty())
            url.addQueryParameter("stage_numbers", StringUtils.join(parameters.getStageNumbers(), ","));

        if (ToornamentClient.loggerLevel == LoggerLevel.DEBUG)
            logger.debug("url: {}", url.build().toString());

        Request request =
            client
                .getRequestBuilder()
                .get()
                .url(url.build())
                .addHeader("range", header.get("range"))
                .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(
                responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Group.class));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException getting Groups");
        }
    }

    public Group getGroup(String groupID) {
        HttpUrl.Builder urlBuilder =
            new HttpUrl.Builder()
                .scheme("https")
                .host("api.toornament.com")
                .addPathSegment("viewer")
                .addPathSegment("v2")
                .addPathSegment("tournaments")
                .addPathSegment(tournamentID)
                .addPathSegment("groups")
                .addPathSegment(groupID);
        Request request = client.getRequestBuilder().get().url(urlBuilder.build()).build();
        try {

            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(Group.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Got IOException getting Group");
        }
    }
}
