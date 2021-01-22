package com.brentonpoke.toornamentclient.model.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class StandingsQuery {

    @Singular
    private final List<String> tournamentIds;

    @Singular
    private final List<String> participantIds;
}
