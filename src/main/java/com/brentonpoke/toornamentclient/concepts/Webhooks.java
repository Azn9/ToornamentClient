package com.brentonpoke.toornamentclient.concepts;

import com.brentonpoke.toornamentclient.ToornamentClient;
import com.brentonpoke.toornamentclient.exception.ToornamentException;
import com.brentonpoke.toornamentclient.model.Webhook;
import com.brentonpoke.toornamentclient.model.WebhookSubscription;
import com.brentonpoke.toornamentclient.model.request.WebhookQuery;
import com.brentonpoke.toornamentclient.model.request.WebhookSubscriptionQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.LoggerFactory;

public class Webhooks extends Concept {

    public Webhooks(ToornamentClient client) {
        super(client);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Webhook> getWebhooks(Map<String, String> range) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks");
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", range.get("range"))
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, Webhook.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting webhooks");
        }
    }

    public Webhook createWebhook(WebhookQuery query) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .post(body)
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, Webhook.class);
        } catch (IOException e) {
            throw new ToornamentException("Couldn't create webhook: " + e.getMessage());
        }

    }

    public Webhook getWebhook(String webhookID) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks")
            .addEncodedPathSegment(webhookID);
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, Webhook.class);
        } catch (IOException e) {
            throw new ToornamentException("Couldn't get webhook: " + e.getMessage());
        }
    }

    public Webhook updateWebhook(String webhookID, WebhookQuery query) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, Webhook.class);
        } catch (IOException e) {
            throw new ToornamentException("Couldn't update webhook: " + e.getMessage());
        }
    }

    public Integer deleteWebhook(String webhookID) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks")
            .addEncodedPathSegment(webhookID);
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();
        return client.executeRequest(request).code();
    }

    public List<WebhookSubscription> getWebhookSubscriptions(String webhookID, Map<String, String> range) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks")
            .addEncodedPathSegment(webhookID)
            .addEncodedPathSegment("subscriptions");
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", range.get("range"))
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, WebhookSubscription.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting webhook subscription");
        }
    }

    public WebhookSubscription createWebhookSubscription(String webhookID, WebhookSubscriptionQuery query) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks")
            .addEncodedPathSegment(webhookID)
            .addEncodedPathSegment("subscriptions");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .post(body)
            .url(url.build())
            .build();
        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(WebhookSubscription.class));
        } catch (IOException | NullPointerException e) {

            throw new ToornamentException("Got IOException creating webhook subscription: " + e.getMessage());
        }
    }

    public WebhookSubscription updateWebhookSubscription(String webhookID, String webhookSubID, WebhookSubscriptionQuery query) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks")
            .addEncodedPathSegment(webhookID)
            .addEncodedPathSegment("subscriptions")
            .addEncodedPathSegment(webhookSubID);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody, WebhookSubscription.class);
        } catch (IOException e) {
            throw new ToornamentException("Couldn't update webhook: " + e.getMessage());
        }
    }

    public Integer deleteWebhook(String webhookID, String webSubID) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks")
            .addEncodedPathSegment(webhookID)
            .addEncodedPathSegment("subscriptions")
            .addEncodedPathSegment(webSubID);
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();
        return client.executeRequest(request).code();
    }

}
