package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.enums.TournamentStatus;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimeZone;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Tournament {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("scheduled_date_start")
    private LocalDate scheduledDateStart;

    @JsonProperty("scheduled_date_end")
    private LocalDate scheduledDateEnd;

    @JsonProperty("date_start")
    private LocalDate dateStart;

    @JsonProperty("date_end")
    private LocalDate dateEnd;

    @JsonProperty("public")
    private Boolean isPublic;

    @JsonProperty("registration_enabled")
    private String registrationEnabled;

    @JsonProperty("registration_opening_datetime")
    private ZonedDateTime registrationOpeningDatetime;

    @JsonProperty("registration_closing_datetime")
    private ZonedDateTime registrationClosingDatetime;

    private String            id;
    private String            discipline;
    private String            name;
    private TournamentStatus  status;
    private Boolean           online;
    private Boolean           archived;
    private String            location;
    private String            country;
    private Integer           size;
    private Logo              logo;
    private ArrayList<String> platforms;
    private TimeZone          timezone;

    public Tournament() {
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer(new SimpleDateFormat("yyyy-mm-dd")).withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
