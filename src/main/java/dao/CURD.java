package dao;

import java.sql.SQLException;
import java.util.List;

public interface CURD<K,V> {
//    增加

    /**
     *
     * @param v
     * @return
     * @throws SQLException
     */
    int insert (V v) throws SQLException;
//    删除

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteById (int id) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    int deleteAll() throws SQLException;
//    修改

    /**
     *
     * @param v
     * @return
     * @throws SQLException
     */
    int UpdateById(V v) throws SQLException;

//    查询

    /**
     *
     * @return
     * @throws SQLException
     */
    List<V> findAll() throws SQLException;

    /**
     *
     * @param k
     * @return
     * @throws SQLException
     */
    List<V> findByColumn(K k) throws SQLException;
}
