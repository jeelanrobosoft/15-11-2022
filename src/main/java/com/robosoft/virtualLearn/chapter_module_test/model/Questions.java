package com.robosoft.virtualLearn.chapter_module_test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questions {
    private Integer questionId;
    private String questionName;
    private String Option_1;
    private String Option_2;
    private String Option_3;
    private String Option_4;
}
