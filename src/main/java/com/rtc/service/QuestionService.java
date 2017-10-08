package com.rtc.service;

import com.rtc.entity.QuestionBean;
import com.rtc.repository.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiao
 * on 2017/6/3.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    public List<QuestionBean> queryQuestion() {
        return questionDAO.queryQuestion();
    }

}
