package com.example.NBRBApi.model.service;

import com.example.NBRBApi.model.domain.Rate;
import com.example.NBRBApi.model.domain.RateShort;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class NbrbExchange {
    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(requestFactory);


    public List<Rate> getAllRates() {
        String url = "https://www.nbrb.by/API/ExRates/Rates?Periodicity=0";
        ResponseEntity<List<Rate>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rate>>() {
                }
        );
        return responseEntity.getBody();
    }

    public Rate getRate(String curAbbreviation) {
        String url = "https://www.nbrb.by/API/ExRates/Rates/{curAbbreviation}?ParamMode=2";
        return restTemplate.getForObject(url, Rate.class, curAbbreviation);
    }

    public Rate getRate(String curAbbreviation, LocalDate date) {
        String url = "https://www.nbrb.by/api/exrates/rates/{curAbbreviation}?ondate={Date}&paramMode=2";
        return restTemplate.getForObject(url, Rate.class, curAbbreviation, date);
    }


    public List<RateShort> getRateShorts(int curId,LocalDate startDate,LocalDate endDate){
        String url = "https://www.nbrb.by/api/exrates/rates/dynamics/{curId}?startdate={Date}&enddate={Date}";
        ResponseEntity<List<RateShort>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RateShort>>() {
                },
                curId,
                startDate,
                endDate
        );
        return responseEntity.getBody();
    }

//    public List<Currency> getAllCurrencies() {
//        String url = "https://www.nbrb.by/API/ExRates/Currencies";
//        ResponseEntity<List<Currency>> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Currency>>() {
//                }
//        );
//        return responseEntity.getBody();
//    }
}
