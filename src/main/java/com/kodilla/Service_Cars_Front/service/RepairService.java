package com.kodilla.Service_Cars_Front.service;

import com.kodilla.Service_Cars_Front.config.AppConfiguration;
import com.kodilla.Service_Cars_Front.domain.RepairDto;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class RepairService {

    RestTemplate restTemplate = new RestTemplate();
    private JsonBuilder<RepairDto> jsonBuilder = new JsonBuilder<>();
    private AppConfiguration appConfiguration = AppConfiguration.getInstance();
    private static RepairService repairService;
    private List<RepairDto> repairDtos;
    public enum repairCurrency {CHF, USD, EUR, GBP}

    private RepairService() {
    }

    public static RepairService getInstance() {
        if (repairService == null) {
            repairService = new RepairService();
        }
        return  repairService;
    }

    public Set<RepairDto> getRepairs() {
        return new HashSet<>(repairDtos);
    }

    public void fetchAll() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfiguration.getBackendEndpoint()+"repairs")
                .encode()
                .build()
                .toUri();
        Optional<RepairDto[]> repairs = Optional.ofNullable(restTemplate.getForObject(url,RepairDto[].class));
        repairDtos =  new ArrayList(repairs
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }

    public void save(RepairDto repairDto) {
        String url = appConfiguration.getBackendEndpoint()+"repairs";
        restTemplate.postForObject(url,jsonBuilder.prepareJson(repairDto), Void.class);
    }

    public void update(RepairDto repairDto) {
        String url = appConfiguration.getBackendEndpoint()+"repairs";
        restTemplate.put(url,jsonBuilder.prepareJson(repairDto));
    }

    public List<RepairDto> filterByCarId(long carId) {
        return repairDtos.stream()
                .filter(repair-> Long.valueOf(repair.getCarId()).equals(carId))
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfiguration.getBackendEndpoint()+"repairs/"+id)
                .encode()
                .build()
                .toUri();
        restTemplate.delete(url);
    }

    public double getCurrencyFactorFromNBP(String currencyCode) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfiguration.getBackendEndpoint()+"currency/"+currencyCode)
                .encode()
                .build()
                .toUri();
        try {
            return restTemplate.getForObject(url,Double.class);
        }
        catch (ServerErrorException e) {
            return 0.0;
        }

    }
}
