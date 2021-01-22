package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Logo {

    @JsonAlias({"logo_small", "icon_small"})
    private String logoSmall;

    @JsonAlias({"logo_medium", "icon_medium"})
    private String logoMedium;

    @JsonAlias({"logo_large", "icon_large"})
    private String logoLarge;

    private String original;

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
