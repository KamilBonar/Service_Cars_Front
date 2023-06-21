package com.kodilla.Service_Cars_Front.service;

import com.kodilla.Service_Cars_Front.config.AppConfiguration;
import com.kodilla.Service_Cars_Front.domain.SparePartDto;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class SparePartService {

    RestTemplate restTemplate = new RestTemplate();
    private JsonBuilder<SparePartDto> jsonBuilder = new JsonBuilder<>();
    AppConfiguration appConfiguration = AppConfiguration.getInstance();
    private static SparePartService sparePartService;
    private List<SparePartDto> sparePartDtos;

    private SparePartService() {
    }

    public static SparePartService getInstance() {
        if (sparePartService==null) {
            sparePartService = new SparePartService();
        }
        return sparePartService;
    }

    public Set<SparePartDto> getSparePartDtos() {
        return new HashSet<>(sparePartDtos);
    }

    public void fetchAll() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfiguration.getBackendEndpoint()+"spares")
                .encode()
                .build()
                .toUri();
        Optional<SparePartDto[]> spareParts = Optional.ofNullable(restTemplate.getForObject(url,SparePartDto[].class));
        sparePartDtos = new ArrayList<>(spareParts
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }

    public void save(SparePartDto sparePartDto) {
        String url = appConfiguration.getBackendEndpoint()+"spares";
        restTemplate.postForObject(url,jsonBuilder.prepareJson(sparePartDto),Void.class);
    }

    public void update(SparePartDto sparePartDto) {
        String url = appConfiguration.getBackendEndpoint()+"spares";
        restTemplate.put(url,jsonBuilder.prepareJson(sparePartDto));
    }

    public void delete(long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfiguration.getBackendEndpoint()+"spares/"+id)
                .encode()
                .build()
                .toUri();
        restTemplate.delete(url);
    }

    public List<SparePartDto> filterByCarBrand (String searchString) {
        return sparePartDtos.stream()
                .filter(s->s.getCarBrand().equals(searchString))
                .collect(Collectors.toList());
    }
}
