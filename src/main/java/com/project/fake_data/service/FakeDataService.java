package com.project.fake_data.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.fake_data.dto.ResultDTO;

public interface FakeDataService {

    ResultDTO actionFakeData(JsonNode template, int quantity, int threads, String url);

    JsonNode generateFakeJson(JsonNode template);

}