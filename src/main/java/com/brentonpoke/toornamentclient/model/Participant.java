package com.brentonpoke.toornamentclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.brentonpoke.toornamentclient.model.Custom.CustomFields;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Participant {

    @JsonProperty("custom_fields")
    private CustomFields customFields;

    @JsonProperty("user_id")
    private String userID;

    @JsonProperty("checked_in")
    private Boolean checkedIn;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("checked_in_at")
    private OffsetDateTime checkedInAt;

    @JsonProperty("custom_user_identifier")
    private String customUserIdentifier;

    @Singular("lineup")
    private ArrayList<Participant> lineup;

    private String id;
    private String name;
    private String email;

    public String toString() {
        try {
            return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
