package dao.impl;

import dao.IUserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.User;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    private Connection connection = null;
    private QueryRunner queryRunner = null;
    public UserDaoImpl() {
        try {
            connection = JDBCUtils.getConnection();
            queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(User user) throws SQLException{
        String sql = "INSERT INTO `user` VALUES(?,?,?,?,?,?)";
        Object[] param = {user.getId(),user.getUserName(),user.getPassword(),
                          user.getIdent(),user.getTelephone(),user.getAddress()};
        return queryRunner.update(sql,param);
    }

    @Override
    public int deleteById(int id)  throws SQLException{
        String sql = "DELETE FROM `user` WHERE id = ?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int DeleteByName(String name) throws SQLException {
        String sql = "DELETE FROM `user` WHERE username = ?";
        return queryRunner.update(sql,name);
    }

    @Override
    public int deleteAll()  throws SQLException{
        return 0;
    }

    @Override
    public int UpdateById(User user)  throws SQLException{
        String sql = "UPDATE `user` SET  password = ?,telephone = ?,address = ? WHERE id = ?";
        Object[] param = {user.getPassword(),
                          user.getTelephone(),
                          user.getAddress(),user.getId()};
        return queryRunner.update(sql,param);
    }

    @Override
    public int UpdateByName(User user) throws SQLException {
        String sql = "UPDATE `user` SET  password = ?,telephone = ?,address = ? WHERE username = ?";
        Object[] param = {user.getPassword(),
                user.getTelephone(),
                user.getAddress(),user.getUserName()};
        return queryRunner.update(sql,param);
    }



    @Override
    public List<User> findAll()  throws SQLException{
        String sql = "SELECT * FROM `user`";
        return queryRunner.query(sql,new BeanListHandler<User>(User.class));
    }

    @Override
    public List<User> findByColumn(Object o) throws SQLException{
        return null;
    }

    @Override
    public List<User> FindByName(String name) throws SQLException{
        String sql = "SELECT * FROM `user` where username = ?";
        return queryRunner.query(sql,new BeanListHandler<User>(User.class),name);
    }

}
