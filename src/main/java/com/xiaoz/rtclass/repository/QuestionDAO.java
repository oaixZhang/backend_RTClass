package com.xiaoz.rtclass.repository;

import com.xiaoz.rtclass.entity.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao
 * on 2017/6/3.
 */
@Repository
public class QuestionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<QuestionBean> queryQuestion() {
        List<QuestionBean>  list = new ArrayList<>();
        jdbcTemplate.query("select * from question", new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        QuestionBean questionBean = new QuestionBean();
                        questionBean.setContent(resultSet.getString("content"));
                        questionBean.setType(resultSet.getInt("type"));
                        questionBean.setAnswer(resultSet.getString("answer"));
                        questionBean.setSymbol(resultSet.getString("symbol"));
                        questionBean.setQuestionid(resultSet.getInt("id"));
                        list.add(questionBean);
                    }
                });
        return list;
    }
}
