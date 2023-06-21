package com.kodilla.Service_Cars_Front.service;

import com.kodilla.Service_Cars_Front.config.AppConfiguration;
import com.kodilla.Service_Cars_Front.domain.AppEventDto;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.*;

public class EventService {

    RestTemplate restTemplate = new RestTemplate();
    private static EventService eventService;
    private List<AppEventDto> eventDtos;
    private AppConfiguration appConfiguration = AppConfiguration.getInstance();

    private EventService() {
    }

    public static EventService getInstance() {
        if (eventService == null) {
            eventService = new EventService();
        }
        return eventService;
    }

    public Set<AppEventDto> getEvents () {
        return new HashSet<>(eventDtos);
    }

    public void fetchAll() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfiguration.getBackendEndpoint()+"events")
                .encode()
                .build()
                .toUri();
        Optional<AppEventDto[]> repairs = Optional.ofNullable(restTemplate.getForObject(url,AppEventDto[].class));
        eventDtos =  new ArrayList(repairs
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }
}
