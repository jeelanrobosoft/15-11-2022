package com.robosoft.virtualLearn.chapter_module_test.dto;

import lombok.Data;

@Data
public class ResultHeaderRequest {
    private String chapterName;
    private float chapterTestPercentage;
    private String courseName;
    private Integer passingGrade = 75;
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Integer totalNumberOfQuestions;

    public ResultHeaderRequest(String chapterName, float chapterTestPercentage, String courseName, int correctAnswers, int wrongAnswers, int totalNumberOfQuestions) {
        this.chapterName = chapterName;
        this.chapterTestPercentage = chapterTestPercentage;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.totalNumberOfQuestions = totalNumberOfQuestions;
        this.courseName = courseName;
    }
}
