package com.dn.dao;

import com.dn.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Maggie on 2017/9/21 0021.
 */
@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_LOG_SQL= " INSERT INTO" +
            " t_login_log(user_id,ip,login_datetime) values(?,?,?) ";

    public void insertLoginLog(LoginLog loginLog){
        Object[] args = { loginLog.getUserId() , loginLog.getIp() , loginLog.getLoginDate() };
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,args);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
