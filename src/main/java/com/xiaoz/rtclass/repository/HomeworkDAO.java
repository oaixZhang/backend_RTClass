package com.xiaoz.rtclass.repository;

import com.xiaoz.rtclass.entity.Homework;
import com.xiaoz.rtclass.entity.QuestionBean;
import com.xiaoz.rtclass.entity.StuQuestionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao
 * on 2017/6/3.
 */
@Repository
public class HomeworkDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Homework> queryHomeworkList(int classId) {
        List<Homework> homeworkList = new ArrayList<>();

        jdbcTemplate.query("select * from homework where targetClass like ?",
                new Object[]{"%" + classId + "%"}, resultSet -> {
                    Homework homework = new Homework();

                    homework.setId(resultSet.getInt("id"));
                    homework.setContent(resultSet.getString("content"));
                    homework.setSubmittedUserIdStr(resultSet.getString("submittedUserId"));
                    homework.setDate(resultSet.getString("date"));
                    homework.setTargetClassStr(resultSet.getString("targetClass"));
                    homeworkList.add(homework);
                });
        return homeworkList;
    }

    public List<Homework> queryHomeworkList(int classId, int userId) {
        List<Homework> homeworkList = new ArrayList<>();

        jdbcTemplate.query("select * from homework where targetClass like ?",
                new Object[]{"%" + classId + "%"}, resultSet -> {
                    Homework homework = new Homework();

                    homework.setId(resultSet.getInt("id"));
                    homework.setContent(resultSet.getString("content"));
                    homework.setSubmittedUserIdStr(resultSet.getString("submittedUserId"));
                    homework.setDate(resultSet.getString("date"));
                    homework.setTargetClassStr(resultSet.getString("targetClass"));
                    homeworkList.add(homework);
                });
//
        for (Homework homework : homeworkList) {
            jdbcTemplate.query("select * from record where userId = ? & homeworkId = ?",
                    new Object[]{userId, homework.getId()}, resultSet -> {
                        StuQuestionRecord record = new StuQuestionRecord();
                        record.setId(resultSet.getInt("id"));
                        record.setQuestionId(resultSet.getInt("questionId"));
                        record.setDate(resultSet.getString("date"));
                        record.setResult(resultSet.getInt("result"));
                        record.setStuAnswer(resultSet.getString("stuAnswer"));
                        homework.addStuQuestionRecords(record);
                    });

        }
        return homeworkList;
    }

    public List<QuestionBean> queryHomework(int[] questionIds) {
        List<QuestionBean> questionBeanList = new ArrayList<>();

        for (int i = 0; i < questionIds.length; i++) {
            QuestionBean question = new QuestionBean();
            jdbcTemplate.query("select * from question where id = ?",
                    new Object[]{questionIds[i]}, resultSet -> {
                        question.setQuestionid(resultSet.getInt("id"));
                        question.setContent(resultSet.getString("content"));
                        question.setAnswer(resultSet.getString("answer"));
                        question.setType(resultSet.getInt("type"));
                        question.setSymbol(resultSet.getString("symbol"));
                        questionBeanList.add(question);
                    });
        }
        return questionBeanList;
    }
}
