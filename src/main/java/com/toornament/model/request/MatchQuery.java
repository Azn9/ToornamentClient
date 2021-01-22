package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toornament.model.enums.MatchSort;
import com.toornament.model.enums.MatchStatus;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class MatchQuery {

    private Boolean hasResult;

    @Singular
    private List<Long> stageIds;

    @Singular
    private List<Long> groupIds;

    @Singular
    private List<Long> roundIds;

    @Builder.Default
    private MatchSort sort = MatchSort.STRUCTURE;

    @Singular
    private List<Long> participantIds;

    @JsonProperty("is_scheduled")
    private Boolean scheduled;

    @JsonProperty("scheduled_before")
    private ZonedDateTime scheduledBefore;

    @JsonProperty("scheduled_after")
    private ZonedDateTime scheduledAfter;

    @Singular
    private List<MatchStatus> statuses;

    @JsonProperty("custom_user_identifier")
    private String customUserIdentifier;

}
