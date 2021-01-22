package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reports {

    @JsonAlias("custom_user_identifier")
    String customUserIdentifier;

    @JsonAlias("participant_id")
    String participantID;

    @JsonAlias("closed_author_id")
    String closedAuthorID;

    String        note;
    String        user_id;
    String        type;
    String        id;
    Boolean       closed;
    LocalDateTime closed_at;
    Report        report;

    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public class Report {
        List<Opponent> opponents;
    }

}
