package com.brentonpoke.toornamentclient.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.brentonpoke.toornamentclient.model.enums.MatchFormat;
import com.brentonpoke.toornamentclient.model.enums.MatchType;
import com.brentonpoke.toornamentclient.model.enums.ParticipantType;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class TournamentDetails extends Tournament {

    @JsonProperty("participant_type")
    private ParticipantType participantType;

    @JsonProperty("match_type")
    private MatchType matchType;

    @JsonProperty("team_min_size")
    private Integer teamSizeMin;

    @JsonProperty("team_max_size")
    private Integer teamSizeMax;

    @JsonProperty("check_in")
    private Boolean checkIn;

    @JsonProperty("participant_nationality")
    private Boolean participantNationality;

    @JsonProperty("match_format")
    private MatchFormat matchFormat;

    private String                organization;
    private String                website;
    private String                description;
    private String                rules;
    private String                prize;
    private Map<String, Object[]> disciplineFields = new HashMap<>();

    // Capture all other fields that Jackson do not match other members
    @JsonAnyGetter
    public Map<String, Object[]> getOtherFields() {
        return disciplineFields;
    }

    @JsonAnySetter
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public void setOtherField(String name, Object[] value) {
        disciplineFields.put(name, value);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer(new SimpleDateFormat("yyyy-mm-dd")).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
