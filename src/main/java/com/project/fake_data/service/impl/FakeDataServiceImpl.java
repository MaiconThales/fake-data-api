package com.project.fake_data.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.github.javafaker.Faker;
import com.project.fake_data.dto.IndividualResult;
import com.project.fake_data.dto.ResultDTO;
import com.project.fake_data.service.FakeDataService;
import com.project.fake_data.service.HttpSenderService;
import com.project.fake_data.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FakeDataServiceImpl implements FakeDataService {

    private final Faker faker = new Faker();
    private final ObjectMapper mapper;
    private final HttpSenderService httpSenderService;

    @Autowired
    public FakeDataServiceImpl(ObjectMapper mapper, HttpSenderService httpSenderService) {
        this.mapper = mapper;
        this.httpSenderService = httpSenderService;
    }

    @Override
    public ResultDTO actionFakeData(JsonNode template, int quantity, String url) {
        List<IndividualResult> individualResult = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            JsonNode fakeJson = this.generateFakeJson(template);
            LocalDateTime init = LocalDateTime.now();
            this.httpSenderService.send(url, fakeJson);
            LocalDateTime end = LocalDateTime.now();

            individualResult.add(
                    IndividualResult.builder()
                            .callTime(Duration.between(init, end).getSeconds())
                            .build()
            );
        }

        return ResultDTO.builder()
                .path(url)
                .totalTime(
                        individualResult.stream().mapToLong(IndividualResult::getCallTime).sum()
                )
                .results(individualResult)
                .build();
    }

    @Override
    public JsonNode generateFakeJson(JsonNode template) {
        if (template.isObject()) {
            return workWithObjects(template);
        } else if (template.isArray()) {
            return workWithArrays(template);
        } else if (template.asText().startsWith("@")) {
            return placeHolderJson(template.asText());
        } else if (template.isTextual()) {
            return workWithText(template);
        } else if (template.isInt()) {
            return new IntNode(faker.number().numberBetween(1, 999));
        } else if (template.isDouble() || template.isFloat()) {
            return new DoubleNode(faker.number().randomDouble(2, 1, 1000));
        } else if (template.isBoolean()) {
            return BooleanNode.valueOf(faker.bool().bool());
        }

        return NullNode.getInstance();
    }

    private TextNode workWithText(JsonNode template) {
        String field = template.asText().toLowerCase();

        if (field.contains("name")) return new TextNode(faker.name().fullName());
        if (field.contains("email")) return new TextNode(faker.internet().emailAddress());
        if (field.contains("phone")) return new TextNode(faker.phoneNumber().cellPhone());
        if (field.contains("city")) return new TextNode(faker.address().city());
        if (field.contains("street")) return new TextNode(faker.address().streetAddress());

        return new TextNode(faker.lorem().word());
    }

    private ObjectNode workWithObjects(JsonNode template) {
        ObjectNode obj = mapper.createObjectNode();
        Iterator<Map.Entry<String, JsonNode>> fields = template.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            obj.set(entry.getKey(), generateFakeJson(entry.getValue()));
        }
        return obj;
    }

    private ArrayNode workWithArrays(JsonNode template) {
        ArrayNode arr = mapper.createArrayNode();
        for (JsonNode item : template) {
            arr.add(generateFakeJson(item));
        }
        return arr;
    }

    private TextNode placeHolderJson(String field) {
        return switch (field) {
            case "@name" -> new TextNode(faker.name().fullName());
            case "@plate" -> new TextNode(faker.bothify("???-####"));
            case "@email" -> new TextNode(faker.internet().emailAddress());
            case "@phone" -> new TextNode(faker.phoneNumber().cellPhone());
            case "@city" -> new TextNode(faker.address().city());
            case "@street" -> new TextNode(faker.address().streetAddress());
            case "@date" -> new TextNode(DateUtils.randomDate().toString());
            case "@dateTime" -> new TextNode(DateUtils.randomDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            case "@time" -> new TextNode(LocalTime.now().toString());
            case "@uuid" -> new TextNode(faker.internet().uuid());
            case "@ip" -> new TextNode(faker.internet().ipV4Address());
            case "@url" -> new TextNode(faker.internet().url());
            case "@company" -> new TextNode(faker.company().name());
            case "@jobTitle" -> new TextNode(faker.job().title());
            case "@country" -> new TextNode(faker.address().country());
            case "@zipCode" -> new TextNode(faker.address().zipCode());
            case "@latitude" -> new TextNode(String.valueOf(faker.address().latitude()));
            case "@longitude" -> new TextNode(String.valueOf(faker.address().longitude()));
            case "@creditCard" -> new TextNode(faker.finance().creditCard());
            case "@currency" -> new TextNode(faker.currency().code());
            case "@color" -> new TextNode(faker.color().name());
            case "@boolean" -> new TextNode(String.valueOf(faker.bool().bool()));
            case "@number" -> new TextNode(String.valueOf(faker.number().numberBetween(1, 999)));
            case "@double" -> new TextNode(String.valueOf(faker.number().randomDouble(2, 1, 1000)));
            case "@word" -> new TextNode(faker.lorem().word());
            case "@sentence" -> new TextNode(faker.lorem().sentence());
            default -> new TextNode("");
        };
    }

}