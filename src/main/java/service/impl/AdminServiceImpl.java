package service.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.IAdminService;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements IAdminService {
    private IUserDao iUserDao = null;
    public AdminServiceImpl() {
        this.iUserDao = new UserDaoImpl();
    }
    // 查询所有用户的方法
    @Override
    public List<User> Query()  {

       List<User> users = null;

        try {
            users = iUserDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // System.out.println(users.toString());
        return users;
    }
    // 查询单个用户方法
    @Override
    public User Query(String name) {
        User user = new User();
        try {
            user = iUserDao.FindByName(name).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    // 增加用户的方法
    @Override
    public boolean Update(User user)  {
        int status = 0;
        try {
           status = iUserDao.UpdateByName(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status == 1;
    }

    // 删除用户的方法
    @Override
    public boolean Remove(String username) {
        int status = 0;
        try {
            status = iUserDao.DeleteByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status == 1;
    }

}
