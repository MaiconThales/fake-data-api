package com.project.fake_data.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.fake_data.service.HttpSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class HttpSenderServiceImpl implements HttpSenderService {

    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> send(String url, JsonNode body) {
        return restTemplate.postForEntity(url, body, String.class);
    }

}