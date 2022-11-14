package com.robosoft.virtualLearn.course_final_test.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robosoft.virtualLearn.chapter_module_test.model.UserAnswers;
import com.robosoft.virtualLearn.course_final_test.model.FinalTest;
import com.robosoft.virtualLearn.course_final_test.model.FinalTestRequest;
import com.robosoft.virtualLearn.course_final_test.service.FinalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class FinalTestController {


    @Autowired
    FinalTestService finalTestService;


    @GetMapping("FinalTest")
    public ResponseEntity<?> getFinalTest(@RequestBody FinalTestRequest request){
        FinalTest moduleTest = finalTestService.finalTestService(request);
        if(moduleTest == null)
            return new ResponseEntity<>("Invalid Test Id", HttpStatus.NOT_FOUND);
        return ResponseEntity.of(Optional.of(moduleTest));
    }

    @PostMapping("/FinalSubmit")
    public Map submitUserAnswers(@RequestBody UserAnswers userAnswers){
        return Collections.singletonMap("Test Percentage",finalTestService.userAnswers(userAnswers));
    }

    @GetMapping("/Result")
    public Map getFinalTestResult(@RequestBody FinalTestRequest request) throws IOException {
        return Collections.singletonMap("Approval Rate",finalTestService.getFinalTestResult(request));
    }




}
