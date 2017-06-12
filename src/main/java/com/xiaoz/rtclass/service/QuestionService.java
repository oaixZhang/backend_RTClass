package com.xiaoz.rtclass.service;

import com.xiaoz.rtclass.entity.MyResponse;
import com.xiaoz.rtclass.entity.QuestionBean;
import com.xiaoz.rtclass.repository.HomeworkDAO;
import com.xiaoz.rtclass.repository.QuestionDAO;
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
