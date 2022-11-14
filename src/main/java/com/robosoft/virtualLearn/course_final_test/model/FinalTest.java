package com.robosoft.virtualLearn.course_final_test.model;


import com.robosoft.virtualLearn.chapter_module_test.model.Questions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalTest {
    private Integer testId;
    private String testName;
    private String testDuration;
    private Integer questionsCount;
    private List<Questions> questions;
}
