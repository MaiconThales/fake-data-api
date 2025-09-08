package com.project.fake_data.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.fake_data.dto.ResultDTO;
import com.project.fake_data.service.FakeDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/fake-data", produces = "application/json")
public class FakeDataController {

    private final ObjectMapper objectMapper;
    private final FakeDataService fakeDataService;

    @PostMapping("/send")
    public ResponseEntity<ResultDTO> sendFakeData(
            @RequestParam String url,
            @RequestParam(defaultValue = "1") int quantity,
            @RequestBody String jsonTemplate) throws JsonProcessingException {
        JsonNode template = objectMapper.readTree(jsonTemplate);
        return ResponseEntity.ok(this.fakeDataService.actionFakeData(template, quantity, url));
    }

}