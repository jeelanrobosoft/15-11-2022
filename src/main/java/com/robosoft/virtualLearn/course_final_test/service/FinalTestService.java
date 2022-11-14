package com.robosoft.virtualLearn.course_final_test.service;


import com.robosoft.virtualLearn.chapter_module_test.model.UserAnswers;
import com.robosoft.virtualLearn.course_final_test.dao.FinalTestDataAccess;
import com.robosoft.virtualLearn.course_final_test.model.FinalTest;
import com.robosoft.virtualLearn.course_final_test.model.FinalTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalTestService {

    @Autowired
    FinalTestDataAccess testDataAccess;
    public FinalTest finalTestService(FinalTestRequest request) {
        return testDataAccess.getFinalTestS(request);
    }

    public Float getFinalTestResult(FinalTestRequest request) {
        return testDataAccess.getFinalTestResult(request);
    }

    public float userAnswers(UserAnswers userAnswers) {
        return testDataAccess.userAnswers(userAnswers);
    }
}
