package com.rtc.repository;

import com.rtc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by xiao
 * on 2017/6/2.
 */
@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        String sql = "insert into user(name,account,password,tag) values(?,?,?,?)";
//        int a = jdbcTemplate.update(sql, user.getName(), user.getAccout(), user.getPassword(), 1);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = jdbcTemplate.getDataSource().getConnection()
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getAccout());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getTag());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public User selectUserById(int id) {
        User user = new User();
        jdbcTemplate.query("SELECT * FROM user WHERE id = ?", new Object[]{id},
                resultSet -> {
                    user.setName(resultSet.getString("name"));
                    user.setId(resultSet.getInt("id"));
                    user.setAccout(resultSet.getString("account"));
                    user.setPassword(resultSet.getString("password"));
                    user.setTag(resultSet.getInt("tag"));
                });
        return user;
    }

    public User selectUserByAccount(String account) {
        User user = new User();
        jdbcTemplate.query("SELECT * FROM user WHERE account = ?", new Object[]{account},
                resultSet -> {
                    user.setName(resultSet.getString("name"));
                    user.setId(resultSet.getInt("id"));
                    user.setAccout(resultSet.getString("account"));
                    user.setPassword(resultSet.getString("password"));
                    user.setTag(resultSet.getInt("tag"));
                });
        return user;
    }

}
