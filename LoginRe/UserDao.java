package com.example.myapplications.LoginRe;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDao {

    private static Connection con = null;
    private static PreparedStatement pst = null;
    private ResultSet rs = null;
    private static final String TAG = "mysql-user-UserDao";

//    public static List login(String name,String password) {
//
//        List list=new ArrayList();
//
//        //获取连接数据库对象
//        Connection con = JDBCUtils.getConn();
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//
//        //mysql语句
//        String sql = "select * from user where name = ? and password = ?";
//        try {
//
//            if(con!=null&&(!con.isClosed())){
//
//                pst= con.prepareStatement(sql);
//
//                if(pst!=null){
//
//                    rs= pst.executeQuery();
//
//                    if(rs!=null){
//
//                        while(rs.next()){
//
//                            User user=new User();
////                            user.setId(rs.getString("id"));
//
//                            user.setName(rs.getString("name"));
//                            user.setPassword(rs.getString("password"));
//                            list.add(user);
//
//                        }
//
//                    }
//
//                }
//
//            }
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//
//        }
//
//        JDBCUtils.closeAll(con,pst,rs);//关闭相关操作
//
//        return list;
//
//    }
//    public boolean register (User user){
////        int result=-1;
//
//        if((user!=null)){
////获取链接数据库对象
//            String sql = "INSERT INTO user (name,password) VALUES (?,?)";
//            con= JDBCUtils.getConn();
////MySQL 语句
//            try {
//                if((con!=null&&pst!=null)) {
//                    pst = con.prepareStatement(sql);
//                    pst.setString(1,user.getName());//第一个参数 name 规则同上
//                    pst.setString(2,user.getPassword());//第二个参数 password 规则同上
//                    int value = pst.executeUpdate();
//                    if(value>0){
//                        return true;
//                    }
////                    int rs =pst.executeUpdate();//返回1 执行成功
////                    con.commit();
////                    return rs;
//                }
//            } catch (SQLException e) {
//                Log.i("","ERROR,register");
//                e.printStackTrace();
//            }finally {
//                JDBCUtils.closeAll(con,pst);//关闭相关操作
//            }
//
//        }
//        return false;
//    }
public boolean login(String name,String password){

    String sql = "select * from user where name = ? and password = MD5(?)";

    Connection  con = JDBCUtils.getConn();

    try {
        PreparedStatement pst=con.prepareStatement(sql);

        pst.setString(1,name);
        pst.setString(2,password);

        if(pst.executeQuery().next()){

            return true;

        }

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        JDBCUtils.closeAll(con);
    }

    return false;
}


    /**
     * function: 注册
     * */
    public boolean register(User user){
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();

        try {
            String sql = "insert into user(name,password) values (?,MD5(?))";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库
                    ps.setString(1,user.getName());
                    ps.setString(2,user.getPassword());


                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0)
                        return true;
                    else
                        return false;
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常register：" + e.getMessage());
            return false;
        }

    }
    public User findUser(String name){
        Connection con = JDBCUtils.getConn();

        User user = null;
        try {
            String sql = "select * from user where name = ?";
            if (con != null) {
                pst = con.prepareStatement(sql);
                if (pst != null) {
                    pst.setString(1, name);
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String name1 = rs.getString(2);
                        String password = rs.getString(3);
                        user = new User(id, name1, password);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Log.d(TAG, "异常findUser：");
            return null;
        }
//        finally {
//            if(pst!=null){
//                try {
//                    pst.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            if(rs!=null){
//                try {
//                    rs.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            JDBCUtils.closeAll(con,pst);
//        }
        return user;
    }
}