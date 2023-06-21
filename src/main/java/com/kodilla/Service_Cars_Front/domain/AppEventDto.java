package com.kodilla.Service_Cars_Front.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppEventDto {
    private enum EventType {SEND,CREATED,DELETED,UPDATED}

    private long id;
    private String type;
    private String date;
    private String time;
    private String info;
}
