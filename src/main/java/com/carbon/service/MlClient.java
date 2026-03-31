package com.carbon.service;

import com.carbon.entity.PredictionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MlClient {

    @Value("${ml.url}")
    private String mlUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public PredictionResponse predict(double electricity, double petrol, double wastage) {
        String url = mlUrl +"?electricity="
                + electricity +
                "&petrol=" + petrol +
                "&wastage=" + wastage;

        return restTemplate.getForObject(url, PredictionResponse.class);

    }
}
