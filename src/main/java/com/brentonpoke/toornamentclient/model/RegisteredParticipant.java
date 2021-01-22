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

/**
 * This class is part of a workaround related to the customFields value.
 * There is for some reason an inconsistency regarding how participants are returned by the
 * registrations endpoint versus the participants endpoint. A goal would be to not need this, but
 * it's not up to the author (Brenton Poke).
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisteredParticipant {

    @JsonProperty("custom_fields")
    private ArrayList<CustomFields> customFields;

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
    private ArrayList<RegisteredParticipant> lineup;

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
            System.out.println(e.getMessage());
        }
        return null;
    }
}
