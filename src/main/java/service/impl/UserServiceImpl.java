package service.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.IUserService;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao iUserDao = null;
    public UserServiceImpl() {
        this.iUserDao = new UserDaoImpl();
    }
    // 用户注册
    @Override
    public boolean Reg(User user) {
        int status = 0;

        try {
            if(iUserDao.FindByName(user.getUserName()).size() == 0){
                status = iUserDao.insert(user);
            }

//            System.out.println(iUserDao.FindByName(user.getUserName()).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status==1;
    }

    // 用户登录
    @Override
    public boolean Login(User user) {
        int status = 0;

        List<User> userdata = null;

        try{
            userdata = iUserDao.FindByName(user.getUserName());
            if(userdata.size() == 1 && userdata.get(0).getPassword().equals(user.getPassword())){
                status = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status  == 1;
    }
}
