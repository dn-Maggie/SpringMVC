package com.dn.service;

import com.dn.dao.LoginLogDao;
import com.dn.dao.UserDao;
import com.dn.domain.LoginLog;
import com.dn.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Maggie on 2017/9/21 0021.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private LoginLogDao loginLogDao;
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
    public boolean hasMatchUser(String userName , String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount>0;
    }
    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.UpdateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }


}
