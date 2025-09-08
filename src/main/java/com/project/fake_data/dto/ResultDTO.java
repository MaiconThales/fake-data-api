package com.project.fake_data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultDTO {

    private String path;
    private long totalTime;
    List<IndividualResult> results;

}