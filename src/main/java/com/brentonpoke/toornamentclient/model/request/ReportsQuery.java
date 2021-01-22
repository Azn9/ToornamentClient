package com.brentonpoke.toornamentclient.model.request;

import com.brentonpoke.toornamentclient.model.Reports.Report;
import com.brentonpoke.toornamentclient.model.enums.ReportsType;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportsQuery {

    String note;
    @JsonAlias("user_id")
    String userID;

    @JsonAlias("custom_user_identifier")
    String customUserIdentifier;
    Report report;

    @JsonAlias("participant_id")
    String participantID;

    ReportsType type;
    Boolean     closed;

    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
