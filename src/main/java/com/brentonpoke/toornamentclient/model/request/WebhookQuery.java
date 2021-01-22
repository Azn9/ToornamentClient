package com.brentonpoke.toornamentclient.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebhookQuery {

    Boolean enabled;
    String  url, name;

}
