package com.example.myapplications.Data;

import com.example.myapplications.LoginRe.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoemDao {

    private static Connection con = null;
    private static PreparedStatement pst = null;
    private ResultSet rs = null;
    private static final String TAG = "mysql-user-UserDao";

    public static List<HashMap<String, Object>> Select (String time,String jiaqin,String title, String name,String poem) throws SQLException {

//       先定义一个List<HashMap<String,Object>>类型的数据并实例化
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

//        调用连接函数，传入数据库名的形参，获得conn对象，因为getConnection的返回类型就是Connection及conn
        Connection con = JDBCUtils.getConn();

//        由conn对象创建执行sql语句的对象（Statement类型),调用方法createStatement()
//        Statement sta = con.createStatement();

//        定义sql语句
        String sql = "select * from kor where time like '%' ? '%' or 嘉庆时间 like '%' ? '%' or title like '%' ? '%' or name like '%' ? '%' or poem like '%' ? '%'";

//        调用Statement对象执行sql语句,返回结果result是ResultSet类型，就是结果集，具体百度
        try{

        PreparedStatement pst=con.prepareStatement(sql);

//        pst.setString(int parmeterIndex,String x);

        pst.setString(1,time);
        pst.setString(2,jiaqin);
        pst.setString(3,title);
        pst.setString(4,name);
        pst.setString(5,poem);
//        判断一下是否为空
        ResultSet result = pst.executeQuery();
        if (result == null) {
            return null;
        }

//        条件是当结果集是否有下一行，这是一个相当于指针的东西，第一次调用时会把第一行设置为当前行，第二次回吧第二行设置为当前行，以此类推，直到没有下一行，循环结束
        while (result.next()) {
//            每次循环都会新实例化一个HashMap对象，用于将遍历到的数据填进去
            HashMap<String, Object> map = new HashMap<>();
//            往map中填数据，map的数据类型相当于键值对
//            键是name，值是result.getString("empname"),意思是结果集指针所在行的字段名中的数据
            map.put("name",result.getString("name"));
            map.put("嘉庆时间", result.getString("嘉庆时间"));
            map.put("title",result.getString("title"));
            map.put("time", result.getString("time"));
            map.put("poem", result.getString("poem"));
//            每次循环完就添加到list中，最终list的样子是：[{name=xx},{name=aaa},.......]
            list.add(map);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        最后记得把list返回出去，不然拿不到这个list

        return list;
    }
    public boolean select (String time,String name,String jiaqin,String title,String poem){

//      String sql = "select * from kor where INSTR(time,'?')>0 ";

        String sql = " select * from kor where time like '%' ? '%' or name like '%' ? '%' or 嘉庆时间 like '%' ? '%' or title like '%' ? '%' or poem like '%' ? '%'";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,time);
            pst.setString(2,jiaqin);
            pst.setString(3,title);
            pst.setString(4,name);
            pst.setString(5,poem);

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
    public static List<HashMap<String, Object>> Select1 (String zuozhe,String title, String name,String poem) throws SQLException {

//       先定义一个List<HashMap<String,Object>>类型的数据并实例化
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

//        调用连接函数，传入数据库名的形参，获得conn对象，因为getConnection的返回类型就是Connection及conn
        Connection con = JDBCUtils.getConn();

//        由conn对象创建执行sql语句的对象（Statement类型),调用方法createStatement()
//        Statement sta = con.createStatement();

//        定义sql语句
        String sql = "select * from jap where 作者时间 like '%' ? '%' or title like '%' ? '%' or name like '%' ? '%' or poem like '%' ? '%'";

//        调用Statement对象执行sql语句,返回结果result是ResultSet类型，就是结果集，具体百度
        try{

            PreparedStatement pst=con.prepareStatement(sql);

//        pst.setString(int parmeterIndex,String x);

            pst.setString(1,zuozhe);
            pst.setString(2,title);
            pst.setString(3,name);
            pst.setString(4,poem);
//        判断一下是否为空
            ResultSet result = pst.executeQuery();
            if (result == null) {
                return null;
            }

//        条件是当结果集是否有下一行，这是一个相当于指针的东西，第一次调用时会把第一行设置为当前行，第二次回吧第二行设置为当前行，以此类推，直到没有下一行，循环结束
            while (result.next()) {
//            每次循环都会新实例化一个HashMap对象，用于将遍历到的数据填进去
                HashMap<String, Object> map = new HashMap<>();
//            往map中填数据，map的数据类型相当于键值对
//            键是name，值是result.getString("empname"),意思是结果集指针所在行的字段名中的数据
                map.put("作者时间", result.getString("作者时间"));
                map.put("title",result.getString("title"));
                map.put("name",result.getString("name"));
                map.put("poem", result.getString("poem"));
//            每次循环完就添加到list中，最终list的样子是：[{name=xx},{name=aaa},.......]
                list.add(map);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        最后记得把list返回出去，不然拿不到这个list

        return list;
    }
    public boolean select1 (String zuozhe,String title,String name,String poem){

//      String sql = "select * from kor where INSTR(time,'?')>0 ";

        String sql = " select * from jap where 作者时间 like '%' ? '%' or title like '%' ? '%' or name like '%' ? '%' or poem like '%' ? '%'";

        Connection  con = JDBCUtils.getConn();

        try {
            PreparedStatement pst=con.prepareStatement(sql);

            pst.setString(1,zuozhe);
            pst.setString(2,title);
            pst.setString(3,name);
            pst.setString(4,poem);

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
}
