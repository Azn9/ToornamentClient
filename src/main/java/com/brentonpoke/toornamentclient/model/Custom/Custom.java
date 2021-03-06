package com.brentonpoke.toornamentclient.model.Custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Custom {

    @JsonProperty("label")
    public  String  label;

    @JsonProperty("target_type")
    public  String  targetType;

    @JsonProperty("type")
    public  String  type;

    @JsonProperty("default_value")
    public  String  defaultValue;

    @JsonProperty("required")
    public  String  required;

    @JsonProperty("public")
    public  String  _public;

    @JsonProperty("position")
    public  Integer position;

    @JsonProperty("machine_name")
    private String  machineName;

    public String id;

}
