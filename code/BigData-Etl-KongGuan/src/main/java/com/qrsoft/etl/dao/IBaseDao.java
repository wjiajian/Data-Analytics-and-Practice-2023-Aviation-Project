package com.qrsoft.etl.dao;

import com.qrsoft.etl.common.db.ConnectionPoolManager;
import com.qrsoft.etl.common.db.IConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IBaseDao {

    private final static Logger logger = LoggerFactory.getLogger(IBaseDao.class);
    public ResultSet rs = null;
    private IConnectionPool pool= ConnectionPoolManager.getInstance().getPool("pool");
    private Connection conn = getConnection();
    // 定义sql语句的执行对象
    private PreparedStatement pstmt;
    public Connection getConnection(){
        Connection conn = null;
        if(pool != null && pool.isActive()){
            conn = pool.getConnection();
        }
        return conn;
    }
    public Connection getCurrentConnection(){
        Connection conn = null;
        if(pool != null && pool.isActive()){
            conn = pool.getCurrentConnecton();
        }
        return conn;
    }
    /**
     * 查询
     */
    public ResultSet execute(String sql, Object params[]) {
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();
            pool.releaseConn(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("查询失败！", e.getMessage());
        }
        return rs;
    }
    /**
     * 更新
     */
    public boolean update(String sql, Object params[]) throws SQLException {
        boolean flag = false;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            int i = pstmt.executeUpdate();
            if (i > 0){
                flag = true;
            }else{
                flag = false;
            }
            pool.releaseConn(conn);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            logger.info("更新失败！", e.getMessage());
        }
        return flag;
    }
    /**
     * 更新一个
     */
    public boolean updateOne(String sql) throws SQLException {
        boolean flag = false;
        try {
            pstmt = conn.prepareStatement(sql);
            int i = pstmt.executeUpdate();
            if (i > 0){
                flag = true;
            } else{
                flag = false;
            }
            pool.releaseConn(conn);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            logger.info("更新失败！", e.getMessage());
        }
        return flag;
    }
    /**
     * 执行查询操作
     */
    public List<Map<String, Object>> findResult(String sql, List<?> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = conn.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = rs.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    public Boolean getBool(ResultSet airRs){
        boolean bool = false;
        Integer no = 0;
        try {
            while (airRs.next()) {
                no = airRs.getInt(1);
                if (no>0) {
                    bool = true;
                }
            }
        } catch (SQLException e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }
}