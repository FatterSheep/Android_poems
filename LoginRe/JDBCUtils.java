package com.example.myapplications.LoginRe;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCUtils {
    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url ="jdbc:mysql://139.9.197.84:3306/Poem?useSSL=false";
    //&allowPublicKeyRetrieval=true&serverTimezone=UTC
    //useUnicode=true&characterEncoding=utf8&
    private static final String user ="root";
//    private static final String ip ="10.20.33.118";
    private static final String pass = "Lyy123456";

static {
    try {
        Class.forName(driver).newInstance();
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
        e.printStackTrace();
        Log.e("","ERROR");
    }
}

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn= DriverManager.getConnection(url, user,pass);
            Log.e("","SUCCESS");
//            stmt = conn.createStatement();

        }catch (Exception e) {
            e.printStackTrace();
            Log.e("","ERROR");
        }
        return conn;
    }

//    public static Statement getStmt() {
//        final String URL ="jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//        final String USER ="root";
//        final String PASS = "112233";
//
//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//
//            Class.forName("com.mysql.jdbc.Driver");
//            conn= DriverManager.getConnection(URL,USER,PASS);
//            stmt = conn.createStatement();
//
//        }catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return stmt;
//    }
//    public static Statement getPst() {
//        final String URL ="jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//        final String USER ="root";
//        final String PASS = "112233";
//
//        Connection conn = null;
//        Statement pst = null;
//
//        try {
//
//            Class.forName("com.mysql.jdbc.Driver");
//            conn= DriverManager.getConnection(URL,USER,PASS);
//            pst = conn.createStatement();
//
//        }catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return pst;
//    }
    /**

     * 关闭数据库

     * */
    public static void closeAll(Connection conn) {

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
    }
    public static void closeAll(Connection conn, PreparedStatement pst){

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (pst != null) {

            try {

                pst.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

    /**

     * 关闭数据库

     * */

    public static void closeAll(Connection conn, PreparedStatement pst, ResultSet rs){

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (pst != null) {

            try {

                pst.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (rs != null) {

            try {

                rs.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }
    public static List<HashMap<String, Object>> getinfo1() throws SQLException {

//       先定义一个List<HashMap<String,Object>>类型的数据并实例化
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

//        调用连接函数，传入数据库名的形参，获得conn对象，因为getConnection的返回类型就是Connection及conn
        Connection conn = JDBCUtils.getConn();

//        由conn对象创建执行sql语句的对象（Statement类型),调用方法createStatement()
        Statement sta = conn.createStatement();

//        定义sql语句
        String sql = "select * from jap ";

//        调用Statement对象执行sql语句,返回结果result是ResultSet类型，就是结果集，具体百度
        ResultSet result = sta.executeQuery(sql);

//        判断一下是否为空
        if (result == null) {
            return null;
        }

//        条件是当结果集是否有下一行，这是一个相当于指针的东西，第一次调用时会把第一行设置为当前行，第二次回吧第二行设置为当前行，以此类推，直到没有下一行，循环结束
        while (result.next()) {
//            每次循环都会新实例化一个HashMap对象，用于将遍历到的数据填进去
            HashMap<String, Object> map = new HashMap<>();
//            往map中填数据，map的数据类型相当于键值对
//            键是name，值是result.getString("empname"),意思是结果集指针所在行的字段名中的数据
            map.put("name", result.getString("name"));
            map.put("作者时间", result.getString("作者时间"));
            map.put("title", result.getString("title"));
            map.put("poem", result.getString("poem"));
//            每次循环完就添加到list中，最终list的样子是：[{name=xx},{name=aaa},.......]
            list.add(map);
        }
//        最后记得把list返回出去，不然拿不到这个list
        return list;
    }
    public static List<HashMap<String, Object>> getinfo() throws SQLException {

//       先定义一个List<HashMap<String,Object>>类型的数据并实例化
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

//        调用连接函数，传入数据库名的形参，获得conn对象，因为getConnection的返回类型就是Connection及conn
        Connection conn = JDBCUtils.getConn();

//        由conn对象创建执行sql语句的对象（Statement类型),调用方法createStatement()
        Statement sta = conn.createStatement();

//        定义sql语句
        String sql = "select * from kor ";

//        调用Statement对象执行sql语句,返回结果result是ResultSet类型，就是结果集，具体百度
        ResultSet result = sta.executeQuery(sql);

//        判断一下是否为空
        if (result == null) {
            return null;
        }

//        条件是当结果集是否有下一行，这是一个相当于指针的东西，第一次调用时会把第一行设置为当前行，第二次回吧第二行设置为当前行，以此类推，直到没有下一行，循环结束
        while (result.next()) {
//            每次循环都会新实例化一个HashMap对象，用于将遍历到的数据填进去
            HashMap<String, Object> map = new HashMap<>();
//            往map中填数据，map的数据类型相当于键值对
//            键是name，值是result.getString("empname"),意思是结果集指针所在行的字段名中的数据
            map.put("name", result.getString("name"));
            map.put("嘉庆时间", result.getString("嘉庆时间"));
            map.put("title", result.getString("title"));
            map.put("time", result.getString("time"));
            map.put("poem", result.getString("poem"));
//            每次循环完就添加到list中，最终list的样子是：[{name=xx},{name=aaa},.......]
            list.add(map);
        }
//        最后记得把list返回出去，不然拿不到这个list
        return list;
    }

}

//    public static ResultSet getRs(String sql) {
//        final String URL ="jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//        final String USER ="root";
//        final String PASS = "112233";
//
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//
//            Class.forName("com.mysql.jdbc.Driver");
//            conn= DriverManager.getConnection(URL,USER,PASS);
//            stmt = conn.createStatement();
//            rs=stmt.executeQuery(sql);
//        }catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return rs;
//    }
//    public static void RsClose(ResultSet rs) {
//
//        try {
//            rs.close();
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


