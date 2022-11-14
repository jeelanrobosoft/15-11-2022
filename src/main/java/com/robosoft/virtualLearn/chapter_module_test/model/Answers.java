package com.robosoft.virtualLearn.chapter_module_test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answers {
    private Integer questionId;
    private String correctAnswer;
}
