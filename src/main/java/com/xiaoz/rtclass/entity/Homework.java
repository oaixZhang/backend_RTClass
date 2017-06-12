package com.xiaoz.rtclass.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao
 * on 2017/6/3.
 */
public class Homework {
    private int id;
    private String date;

    private List<QuestionBean> questions;
    private int[] questionIds;

    private int[] stuRightIds;

    private int[] targetClassIds;
    private String targetClassStr;

    private int[] submittedUserIds;
    private String submittedUserIdStr;

    private String content;

    private List<StuQuestionRecord> stuQuestionRecords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTargetClassStr() {
        return targetClassStr;
    }

    public void setTargetClassStr(String str) {
        this.targetClassStr = str;

        //去除头尾 , 号
        if (str.charAt(0) == 44)
            str = str.substring(1);
        if (str.charAt(str.length() - 1) == 44)
            str = str.substring(0, str.length());

        String[] split = str.split(",");
        if (targetClassIds == null)
            targetClassIds = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            targetClassIds[i] = Integer.parseInt(split[i]);
        }
    }

    public void setContent(String content) {
        //去除头尾 , 号
        if (content.charAt(0) == 44)
            content = content.substring(1);
        if (content.charAt(content.length() - 1) == 44)
            content = content.substring(0, content.length());

        this.content = content;

        String[] split = content.split(",");

        if (questionIds == null)
            questionIds = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            questionIds[i] = Integer.parseInt(split[i]);
        }
    }

    public int[] getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(int[] questionIds) {
        this.questionIds = questionIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<QuestionBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionBean> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuestionBean question) {
        if (this.questions == null)
            questions = new ArrayList<>();
        this.questions.add(question);
    }

    public int[] getTargetClassIds() {
        return targetClassIds;
    }

    public void setTargetClassIds(int[] ints) {
        this.targetClassIds = ints;
    }

    public int[] getSubmittedUserIds() {
        return submittedUserIds;
    }

    public void setSubmittedUserId(int[] submittedUserIds) {
        this.submittedUserIds = submittedUserIds;
    }

    public void setSubmittedUserIdStr(String str) {
        submittedUserIdStr = str;
    }

    public void addStuQuestionRecords(StuQuestionRecord record) {
        if (stuQuestionRecords == null)
            stuQuestionRecords = new ArrayList<>();
        stuQuestionRecords.add(record);
    }

    public List<StuQuestionRecord> getStuQuestionRecords() {
        return stuQuestionRecords;
    }
}
