package com.toornament.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.enums.MatchFormat;
import com.toornament.model.enums.MatchStatus;
import com.toornament.model.enums.MatchType;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(value = Include.NON_NULL)
public class Match {

    @JsonProperty("tournament_id")
    private String tournamentId;

    @JsonProperty("stage_id")
    private String stageNumber;

    @JsonProperty("group_id")
    private String groupNumber;

    @JsonProperty("round_id")
    private String roundNumber;

    @JsonProperty("scheduled_datetime")
    private String date;

    @JsonProperty("match_format")
    private MatchFormat matchFormat;

    @JsonProperty("played_at")
    private String playedAt;

    @JsonProperty("report_closed")
    private Boolean reportClosed;

    private String         id;
    private MatchType      type;
    private String         discipline;
    private MatchStatus    status;
    private Integer        number;
    private String         timezone;
    private List<Opponent> opponents;
    private Object         settings;

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
