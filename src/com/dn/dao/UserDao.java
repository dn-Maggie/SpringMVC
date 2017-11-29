package com.dn.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dn.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

/**
 * Created by Maggie on 2017/9/20 0020.
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL =" SELECT COUNT(*) FROM " +
            " t_user WHERE user_name=? AND password=? ";
    private final static String UPDATE_LOGIN_INFO_SQL =" UPDATE t_user SET " +
            " last_visit=?,last_ip=?,credits=? WHERE user_id=? ";
    private final static String QUERY_BY_USERNAME = " SELECT user_id,user_name,credits " +
            " FROM t_user WHERE user_name=? ";
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName,String password){
        String sqlStr = "select count(*) from t_user" +
                "where user_name=? and password=?";
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},Integer.class);
    }

    public User findUserByUserName(final String userName){
        final User user = new User();
        jdbcTemplate.query(QUERY_BY_USERNAME, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                }
        );
        return  user;
    }

    public void UpdateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),
                user.getLastIp(),user.getCredits(),user.getUserId()});
    }

}
