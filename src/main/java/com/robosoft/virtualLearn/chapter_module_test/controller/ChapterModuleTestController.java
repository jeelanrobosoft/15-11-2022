package com.robosoft.virtualLearn.chapter_module_test.controller;


import com.robosoft.virtualLearn.chapter_module_test.dto.ModuleTestRequest;
import com.robosoft.virtualLearn.chapter_module_test.dto.ResultAnswerRequest;
import com.robosoft.virtualLearn.chapter_module_test.dto.ResultHeaderRequest;
import com.robosoft.virtualLearn.chapter_module_test.model.ModuleTest;
import com.robosoft.virtualLearn.chapter_module_test.model.UserAnswers;
import com.robosoft.virtualLearn.chapter_module_test.service.ModuleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ChapterModuleTestController {


    @Autowired
    ModuleTestService testService;

    @GetMapping("/ModuleTest")
    public ResponseEntity<?> moduleTest(@RequestBody ModuleTestRequest request){
        ModuleTest moduleTest = testService.moduleTestQuestions(request);
        if(moduleTest == null)
            return new ResponseEntity<>("Invalid Test Id", HttpStatus.NOT_FOUND);
        return ResponseEntity.of(Optional.of(moduleTest));
    }

    @PostMapping("/Submit")
    public Map submitUserAnswers(@RequestBody UserAnswers userAnswers){
        return Collections.singletonMap("Test Percentage",testService.userAnswers(userAnswers));
    }

    @GetMapping("Result_Header")
    public ResultHeaderRequest getResultHeader(@RequestBody ModuleTestRequest testRequest){
        return testService.getResultHeader(testRequest);
    }

    @GetMapping("Result_Answers")
    public List<ResultAnswerRequest> getResultAnswers(@RequestBody ModuleTest request){
            return testService.getResultAnswers(request);
    }


}
