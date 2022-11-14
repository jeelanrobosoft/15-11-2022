package com.robosoft.virtualLearn.chapter_module_test.service;


import com.robosoft.virtualLearn.chapter_module_test.dao.ModuleTestDataAccess;
import com.robosoft.virtualLearn.chapter_module_test.dto.ModuleTestRequest;
import com.robosoft.virtualLearn.chapter_module_test.dto.ResultAnswerRequest;
import com.robosoft.virtualLearn.chapter_module_test.dto.ResultHeaderRequest;
import com.robosoft.virtualLearn.chapter_module_test.model.ModuleTest;
import com.robosoft.virtualLearn.chapter_module_test.model.UserAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleTestService {

    @Autowired
    ModuleTestDataAccess dataAccess;
    public ModuleTest moduleTestQuestions(ModuleTestRequest request) {
        return dataAccess.moduleTestQuestions(request);
    }

    public float userAnswers(UserAnswers userAnswers) {
        return dataAccess.userAnswers(userAnswers);
    }

    public ResultHeaderRequest getResultHeader(ModuleTestRequest testRequest) {
        return dataAccess.getResultHeader(testRequest);
    }

    public List<ResultAnswerRequest> getResultAnswers(ModuleTest request) {
        return dataAccess.getResultAnswers(request);
    }
}
