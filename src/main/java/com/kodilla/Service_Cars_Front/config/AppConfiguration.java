package com.kodilla.Service_Cars_Front.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppConfiguration {

    private static AppConfiguration appConfiguration;

    private AppConfiguration() {
    }

    public static AppConfiguration getInstance() {
        if (appConfiguration == null) {
            appConfiguration = new AppConfiguration();
        }
        return appConfiguration;
    }

    private String backendEndpoint = "http://localhost:8080/v1/Service_Cars/";
}
