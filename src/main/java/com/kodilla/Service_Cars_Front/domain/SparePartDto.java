package com.kodilla.Service_Cars_Front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SparePartDto {
    @JsonProperty
    private long id;
    @JsonProperty
    private String carBrand;
    @JsonProperty
    private String model;
    @JsonProperty
    private String manufacturer;
    @JsonProperty
    private double price;
}
