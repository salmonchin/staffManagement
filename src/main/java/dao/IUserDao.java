package dao;

import pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends CURD<Object, User>{
    /**
     *
     * @param name
     * @return
     * @throws SQLException
     */
    List<User> FindByName(String name) throws SQLException;
    int UpdateByName(User user)throws SQLException;
    int DeleteByName(String name)throws  SQLException;
}