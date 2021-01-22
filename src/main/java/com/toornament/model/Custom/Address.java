package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {

    @JsonProperty("postal_code")
    private String postalCode;

    private String address;
    private String city;
    private String country;

}
