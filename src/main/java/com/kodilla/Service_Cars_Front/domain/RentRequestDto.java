package com.kodilla.Service_Cars_Front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentRequestDto {
    @JsonProperty
    private long id;
    @JsonProperty
    private String customerName;
    @JsonProperty
    private String reqRentStartDate;
    @JsonProperty
    private String reqRentEndDate;
}