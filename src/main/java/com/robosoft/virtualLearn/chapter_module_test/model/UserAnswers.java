package com.robosoft.virtualLearn.chapter_module_test.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserAnswers {

        private Integer testId;
        List<Answers> userAnswers;

        UserAnswers(){
                userAnswers = new ArrayList<>();
        }
}
