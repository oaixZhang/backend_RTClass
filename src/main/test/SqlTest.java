import com.google.gson.Gson;
import com.xiaoz.rtclass.entity.Homework;
import com.xiaoz.rtclass.entity.QuestionBean;
import com.xiaoz.rtclass.repository.HomeworkDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiao
 * on 2017/6/3.
 */
public class SqlTest {
    private ApplicationContext ctx = null;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");

    }

    @After
    public void destory() {
        ctx = null;
    }

    @Test
    public void testSql() {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

        List<Homework> homeworkList = new ArrayList<>();

        System.out.println("select * from homework where targetClass like ?" +
                Arrays.toString(new Object[]{"%'" + 1 + "'%"}));

        jdbcTemplate.query("select * from homework where targetClass like ?",
                new Object[]{"%" + 1 + "%"}, new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        Homework homework = new Homework();

                        homework.setContent(resultSet.getString("content"));
                        homework.setSubmittedUserIdStr(resultSet.getString("submittedUserId"));
                        homework.setDate(resultSet.getString("date"));
                        homework.setTargetClassStr(resultSet.getString("targetClass"));
                        homeworkList.add(homework);
                    }
                });

        for (Homework homework : homeworkList) {
            for (int i = 0; i < homework.getQuestionIds().length; i++) {
                QuestionBean question = new QuestionBean();
                jdbcTemplate.query("select * from question where id = ?",
                        new Object[]{homework.getQuestionIds()[i]}, resultSet -> {
                            question.setQuestionid(resultSet.getInt("id"));
                            question.setContent(resultSet.getString("content"));
                            question.setAnswer(resultSet.getString("answer"));
                            question.setType(resultSet.getInt("type"));
                            question.setSymbol(resultSet.getString("symbol"));
                            homework.addQuestion(question);
                        });
            }
        }
        System.out.println(new Gson().toJson(homeworkList));
//        return homework;
    }

    @Test
    public void testHomework() {
        HomeworkDAO homeworkDAO = (HomeworkDAO) ctx.getBean("homeworkDAO");

        System.out.println(new Gson().toJson(homeworkDAO.queryHomeworkList(1,1)));

    }

}
