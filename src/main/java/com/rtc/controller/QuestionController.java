package com.rtc.controller;

import com.rtc.entity.QuestionBean;
import com.rtc.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiao
 * on 2017/6/2.
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/question")
    public @ResponseBody
    List<QuestionBean> queryHomework() {
        System.out.println(" http requset questionController: ");
        return  questionService.queryQuestion();
    }

}
