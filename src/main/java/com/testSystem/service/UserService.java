package com.testSystem.service;

import com.testSystem.dao.UserDao;
import com.testSystem.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public List<User> findAllUser(int pageIndex)
    {
        Sort sort = new Sort(Sort.Direction.DESC, "username");
        Pageable p = PageRequest.of(pageIndex,10,sort);
        List<User> list =  userDao.selectUserInfo(p);

        return list;
    }

    public int countUser()
    {
        return userDao.countUser();
    }

    public String addUser(User user)
    {
        //int result = (Integer)userManageDao.save(user);
        Optional<User> u =  userDao.findById(user.getUserName());
        if(u.orElse(null)!=null)
        {
            return "repeat";
        }
        userDao.save(user);
        return "success";
    }
    public User getUser() {
        String userName = getCurrentUser();
        return userDao.findByUserName(userName);
    }
    public String delUser(String username)
    {
        if (username.equals(getCurrentUser())){
            return "sameuser";
        }
        int num = userDao.delUser(username);
        if(num>0)
        {
            return "success";
        }
        return "error";
    }

    public String updatePassword(String oldPwd,String newPwd)
    {
        String userName = getCurrentUser();
        int num = userDao.countUserByNameAndPassWd(userName,oldPwd);
        if (num == 0)
        {
            return "errPwd";
        }
        int updateNum = userDao.updatePassword(userName,newPwd);
        if (updateNum > 0)
        {
            return "success";
        }
        return "error";
    }

    public String getPasswordByUserName(String userName)
    {
        return userDao.getPasswordByUserName(userName);
    }

    public String getCurrentUser()
    {
        return (String)SecurityUtils.getSubject().getPrincipal();
    }

    public String submitLogin(String userName,String password)
    {
        String result = "success";
        Subject subject= SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password);

            token.setRememberMe(true);
            //登录
            subject.login(token);
        }catch (Exception e)
        {
            e.printStackTrace();
            result="error";
            return  result;
            //认证没成功
        }

        return  result;
    }


    public List<User> selDeUser() {
        List<User> user =   userDao.selDeUser();
        return user;
    }
}
