package utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {
    private Connection connection = null;
    private QueryRunner queryRunner = null;

    public Test() {
        try {
             connection = JDBCUtils.getConnection();
             queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void add(User user) throws SQLException{
        String sql = "insert into `user` values(?,?,?,?,?,?)";
        queryRunner.update(sql,user.getId(),user.getUserName(),user.getPassword()
        ,user.getIdent(),user.getTelephone(),user.getAddress());
}
public void Update(User user) throws SQLException{
    String sql = "update `user` set address = ? where id = 5";
    queryRunner.update(sql,user.getAddress());
}
public List<User> findAll() throws SQLException{
    String sql = "select * from `user`";
    return queryRunner.query(sql,new BeanListHandler<User>(User.class));
}
public void Delete(User user) throws SQLException{
        String sql = "delete from `user` where id = ?";
        queryRunner.update(sql,user.getId());

}

    public static void main(String[] args) throws SQLException{
        User user =  new User(){{
            setId(5);
            setUserName("user5");
            setPassword("123456");
            setIdent("0");
            setTelephone("041184835202");
            setAddress("大连东软信息学院");
        }};
        Test test = new Test();
//        test.add(user);
        test.Update(user);
        test.Delete(user);
        List<User> list = test.findAll();
        for(User user1 : list){
            System.out.println(user1.toString());
        }

    }
}
