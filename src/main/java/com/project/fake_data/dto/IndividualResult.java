package com.project.fake_data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndividualResult {

    private String nameThreads;
    private Long callTime;

}