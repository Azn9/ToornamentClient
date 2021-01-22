package com.brentonpoke.toornamentclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Group {

    @JsonProperty("stage_id")
    private String stageID;

    private String   id;
    private String   name;
    private Integer  number;
    private Boolean  closed;
    private Settings settings;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer(new SimpleDateFormat("yyyy-mm-dd")).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Setter
    @Getter
    private class Settings {

        String                    size;
        List<List<List<Integer>>> pairing_values;

        public Settings() {
        }

    }
}
