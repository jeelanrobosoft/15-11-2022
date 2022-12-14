package com.robosoft.virtualLearn.course_final_test.dao;


import com.robosoft.virtualLearn.chapter_module_test.model.Answers;
import com.robosoft.virtualLearn.chapter_module_test.model.Questions;
import com.robosoft.virtualLearn.chapter_module_test.model.UserAnswers;
import com.robosoft.virtualLearn.course_final_test.model.FinalTest;
import com.robosoft.virtualLearn.course_final_test.model.FinalTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FinalTestDataAccess {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public FinalTest getFinalTestS(FinalTestRequest request) {
        List<Questions> questions;
        FinalTest finalTest;
        String query = "select questionId,questionName,option_1,option_2,option_3,option_4 from question where testId=?";
        try {
            questions = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Questions.class), request.getTestId());
            finalTest = jdbcTemplate.queryForObject("select testId,testName,testDuration,questionsCount from test where testId=" + request.getTestId(), new BeanPropertyRowMapper<>(FinalTest.class));
        } catch (Exception e) {
            return null;
        }
        finalTest.setQuestions(questions);
        return finalTest;
    }

    public Float getFinalTestResult(FinalTestRequest request) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return jdbcTemplate.queryForObject("select coursePercentage from courseProgress where userName='" + userName + "' and courseId=(select courseId from chapterProgress where testId=" + request.getTestId() + ")", Float.class);
    }

    public float userAnswers(UserAnswers userAnswers) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        float chapterTestPercentage = updateUserAnswerTable(userAnswers);
        jdbcTemplate.update("update chapterProgress set chapterTestPercentage=" + chapterTestPercentage + ",testCompletedStatus=true where testId=" + userAnswers.getTestId());
        int courseId = jdbcTemplate.queryForObject("select courseId from chapterProgress where testId=" + userAnswers.getTestId(), Integer.class);
        float sumOfChapterPercentage = jdbcTemplate.queryForObject("select sum(chapterTestPercentage) from chapterProgress where courseId=" + courseId + " and userName='" + userName + "'", Float.class);
        float totalPercentage = Integer.parseInt(((String.valueOf(jdbcTemplate.queryForObject("select count(chapterNumber) from chapter where courseId=" + courseId, Integer.class))) + "00"));
        float coursePercentage = (sumOfChapterPercentage/totalPercentage) * 100;
                                 /* Updating in notification*/
        String coursePhoto = jdbcTemplate.queryForObject("select coursePhoto from course where courseId=(select courseId from chapterProgress where testId=" + userAnswers.getTestId() + ")", String.class);
        String description = "Completed course" + " - " +jdbcTemplate.queryForObject("select courseName from course where courseId=(select courseId from chapter where chapterId=(select chapterId from chapterProgress where testId=" + userAnswers.getTestId() + "))", String.class);
        String description1 = "You Scored " + jdbcTemplate.queryForObject("select chapterTestPercentage from chapterProgress where chapterId=(select chapterId from chapterProgress where testId=" + userAnswers.getTestId() + ")", String.class) + "% in course " + jdbcTemplate.queryForObject("select courseName from course where courseId=(select courseId from chapter where chapterId=(select chapterId from chapterProgress where testId=" + userAnswers.getTestId() + "))", String.class);
        //        String photoUrl = String.format(DOWNLOAD_URL, URLEncoder.encode("password_change_success.png"));
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = dateTime.format(format);
        jdbcTemplate.update("insert into notification(userName,description,timeStamp,notificationUrl) values(?,?,?,?)",userName,description,formatDateTime,coursePhoto);
        jdbcTemplate.update("insert into notification(userName,description,timeStamp,notificationUrl) values(?,?,?,?)",userName,description1,formatDateTime,coursePhoto);
                                /* Done */
        jdbcTemplate.update("update courseProgress set coursePercentage=" + coursePercentage + ",courseCompletedStatus=true where courseId=" + courseId);
        LocalDate courseCompletedDate = LocalDate.now();
        jdbcTemplate.update("update enrollment set completedDate='" + courseCompletedDate + "', courseScore=" + coursePercentage);
        return chapterTestPercentage;
    }

    public float updateUserAnswerTable(UserAnswers userAnswers) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String query = "select chapterId from test where testId=" + userAnswers.getTestId();
        int chapterId = jdbcTemplate.queryForObject(query, Integer.class);
        query = "select courseId from chapter where chapterId=" + chapterId;
        int courseId = jdbcTemplate.queryForObject(query, Integer.class);
        for (Answers uAnswers : userAnswers.getUserAnswers()) {
            query = "insert into userAnswer values('" + userName + "'" + "," + courseId + "," + chapterId + "," + userAnswers.getTestId() + "," + uAnswers.getQuestionId() + "," + "'" + uAnswers.getCorrectAnswer() + "'" + "," + "(select if((select correctAnswer from question where questionId=" + uAnswers.getQuestionId() + ") ='" + uAnswers.getCorrectAnswer() + "'" + ",true,false)))";
            jdbcTemplate.update(query);
        }
        int correctAnswerCount = jdbcTemplate.queryForObject("select count(*) from userAnswer where userAnswerStatus=true and testId=" + userAnswers.getTestId(), Integer.class);
        int questionCount = jdbcTemplate.queryForObject("select questionsCount from test where testId=" + userAnswers.getTestId(), Integer.class);
        float chapterTestPercentage = (correctAnswerCount / (float) questionCount) * 100;
        return chapterTestPercentage;
    }
}
