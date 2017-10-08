package com.rtc.service;

import com.rtc.entity.Homework;
import com.rtc.entity.MyResponse;
import com.rtc.entity.StuQuestionRecord;
import com.rtc.repository.HomeworkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao
 * on 2017/6/3.
 */
@Service
public class HomeworkService {
    @Autowired
    private HomeworkDAO homeworkDAO;

    public MyResponse queryHomework(int classId) {
        return new MyResponse(homeworkDAO.queryHomeworkList(classId));
    }

    public List<Homework> queryHomework(int classId, int userId) {
        List<Homework> list = homeworkDAO.queryHomeworkList(classId, userId);
        for (Homework homework : list) {
            List<StuQuestionRecord> records = homework.getStuQuestionRecords();
            List<Integer> rightIds = new ArrayList<>();
            for (StuQuestionRecord record : records) {
                if (record.getResult() == 1) {
                    rightIds.add(record.getId());
                }
            }
        }

        return list;
    }

}
