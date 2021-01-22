package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.enums.MatchFormat;
import com.toornament.model.enums.ParticipantType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentRequest {

    @JsonProperty("public")
    private Boolean isPublic;

    @JsonProperty("participant_type")
    private ParticipantType participantType;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("scheduled_date_start")
    private LocalDate dateStart;

    @JsonProperty("scheduled_date_end")
    private LocalDate dateEnd;

    @JsonProperty("check_in")
    private Boolean checkIn;

    @JsonProperty("participant_nationality")
    private Boolean participantNationality;

    @JsonProperty("match_format")
    private MatchFormat matchFormat;

    @JsonAlias("match_report_enabled")
    private Boolean matchReportEnabled;

    @JsonAlias("registration_enabled")
    private Boolean registrationEnabled;

    @JsonAlias("registration_opening_datetime")
    private LocalDateTime registrationOpeningDatetime;

    @JsonAlias("registration_closing_datetime")
    private LocalDateTime registrationClosingDatetime;

    @JsonAlias("registration_notification_enabled")
    private Boolean registrationNotificationEnabled;

    @JsonAlias("registration_request_message")
    private String registrationRequestMessage;

    @JsonAlias("registration_accept_message")
    private String registrationAcceptMessage;

    @JsonAlias("registration_refuse_message")
    private String registrationRefuseMessage;

    @JsonAlias("check_in_enabled")
    private Boolean checkInEnabled;

    @JsonAlias("check_in_participant_enabled")
    private Boolean checkInParticipantEnabled;

    @JsonAlias("check_in_participant_start_datetime")
    private LocalDateTime checkInParticipantStartDatetime;

    @JsonAlias("check_in_participant_end_datetime")
    private LocalDateTime checkInParticipantEndDatetime;

    private String  discipline;
    private String  name;
    private Integer size;
    private String  organization;
    private String  website;
    private String  timezone;
    private Boolean online;
    private String  location;
    private String  country;
    private String  description;
    private String  rules;
    private String  prize;
    private Boolean archived;

    public TournamentRequest(String discipline, String name, Integer size, ParticipantType participantType) {
        this(discipline, name, size, participantType, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public TournamentRequest(String discipline, String name, Integer size, ParticipantType participantType, String fullName, String organization, String website, LocalDate dateStart, LocalDate dateEnd, String timezone, Boolean online, Boolean isPublic, String location, String country, String description, String rules, String prize, Boolean checkIn, Boolean participantNationality, MatchFormat matchFormat) {
        this.discipline = discipline;
        this.name = name;
        this.size = size;
        this.participantType = participantType;
        this.fullName = fullName;
        this.organization = organization;
        this.website = website;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timezone = timezone;
        this.online = online;
        this.isPublic = isPublic;
        this.location = location;
        this.country = country;
        this.description = description;
        this.rules = rules;
        this.prize = prize;
        this.checkIn = checkIn;
        this.participantNationality = participantNationality;
        this.matchFormat = matchFormat;
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
