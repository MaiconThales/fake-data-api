package com.project.fake_data.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface HttpSenderService {

    ResponseEntity<String> send(String url, JsonNode body);

}