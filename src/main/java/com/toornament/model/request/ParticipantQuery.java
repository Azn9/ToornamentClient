package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.Custom.CustomFields;
import com.toornament.model.Participant;
import com.toornament.model.enums.Sort;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class ParticipantQuery {

    private String name;
    private String email;

    @Builder.Default
    private Sort sort = Sort.ASCENDING;

    @JsonAlias("custom_user_identifier")
    private String customUserIdentifier;

    @Singular("lineup")
    private List<Participant> lineup;

    @JsonProperty("custom_fields")
    private CustomFields customFields;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
