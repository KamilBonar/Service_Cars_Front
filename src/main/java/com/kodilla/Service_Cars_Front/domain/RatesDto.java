package com.kodilla.Service_Cars_Front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesDto {

    @JsonProperty
    private String effectiveDate;

    @JsonProperty
    private double mid;
}