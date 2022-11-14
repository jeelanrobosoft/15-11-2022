package com.robosoft.virtualLearn.chapter_module_test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleTest {
    private Integer testId;
    private String testName;
    private String testDuration;
    private Integer questionsCount;
    private List<Questions> questions;
}
