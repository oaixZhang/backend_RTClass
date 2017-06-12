package com.xiaoz.rtclass.controller;

import com.xiaoz.rtclass.entity.MyResponse;
import com.xiaoz.rtclass.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiao
 * on 2017/6/3.
 */
@Controller
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @RequestMapping(value = "/queryHomework/{classId}")
    public @ResponseBody
    MyResponse queryHomework(@PathVariable("classId") int classId) {
        return homeworkService.queryHomework(classId);
    }

}
