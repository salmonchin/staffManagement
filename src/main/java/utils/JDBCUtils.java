package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 创建数据库的连接池
 */
public class JDBCUtils {
//    创建ThreadLoacl对象，当前线程作为Key
    private static ThreadLocal<Connection> tc = new ThreadLocal<Connection>();
//    读取c3p0-config.xml
    private static DataSource ds = new ComboPooledDataSource();
//    获取连接对象
    public static DataSource getDataSource(){
        return ds;
    }
//    从连接池中获取
    public static Connection getConnection() throws SQLException {
        Connection conn = tc.get();
        if(conn == null){
            conn = ds.getConnection();
//            将conn放入集合
            tc.set(conn);
            System.out.println("首次连接"+conn);

        }
        return conn;
    }
//    开启事务
    public static void startTransaction(){
//        获取连接
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
//    提交
    public static void commit() {
        try {
            Connection conn = tc.get();
            if (conn != null) {
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    回滚
    public static void rollback() {
        try {
            // 从集合tc中得到一个连接
            Connection conn = tc.get();
            if (conn != null) {
                // 该方法用于取消在当前事务中进行的更改，并释放当前Connection对象持有的所有数据库锁。此方法只有在手动事务模式下才可用
                conn.rollback();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
