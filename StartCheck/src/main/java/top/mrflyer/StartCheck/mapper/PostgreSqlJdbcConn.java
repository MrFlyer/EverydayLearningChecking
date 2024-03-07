package top.mrflyer.StartCheck.mapper;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
public class PostgreSqlJdbcConn {
    //创建链接
    public static Connection startConnect(){
        Connection c = null;
        try{
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "3214569987";
            Class.forName("org.postgresql.Driver");
            log.info("开始连接数据库");
            c = DriverManager.getConnection(url,user,password);
            log.info("成功连接数据库");
            return c;
        } catch (ClassNotFoundException | SQLException e) {
            log.error("JDBC HAVE ERROR:" + e.toString());
            throw new RuntimeException(e);
        }
    }
    //创建表，若已有则不创建
    public static void createTable(String tableName) throws Exception{
        try {
            Connection c = startConnect();
            ResultSet rs = c.getMetaData().getTables(null, null, tableName, null);
            if (rs.next()) {
                log.info("表已存在");
            }else {
                Statement statement = c.createStatement();
                String sql = "CREATE TABLE " + tableName + " ("
                        + "current_month DATE NOT NULL DEFAULT CURRENT_DATE,"
                        + "data TEXT)";
                statement.executeUpdate(sql);
                log.info("创建表成功");
                statement.close();
                c.close();
                log.info("数据库连接以及statement链接已关闭");
            }
        }catch (Exception e){
            log.error("创建表出错：" + e.toString());
            throw new Exception(e);
        }
    }
    //增条目
    public static void addData(String tableName, String data) throws Exception {
        try{
            Connection c = startConnect();
            Statement statement = c.createStatement();
            String sql = "INSERT INTO " + tableName + " (data) VALUES ('"+ data +"')";
            statement.executeUpdate(sql);
            log.info("Add Message" + data + "Success");
            statement.close();
            c.close();
            log.info("数据库连接以及statement链接已关闭");

        }catch (Exception e){
            log.error("添加条目出错：" + e.toString());
            throw new Exception(e);
        }
    }
    //更新条目
    public static void updateData(String tableName, String data, String month) throws Exception{
        try{
            Connection c = startConnect();
            Statement statement = c.createStatement();
            String sql = "UPDATE " + tableName + " set data = data || ' "+ data +"' where current_month = '" + month + "'";
            statement.executeUpdate(sql);
            log.info("更新信息" + data + "成功");
            statement.close();
            c.close();
            log.info("数据库连接以及statement链接已关闭");
        }catch (Exception e){
            log.error("更新数据出错" + e.toString());
            throw new Exception(e);
        }
    }
    //减条目
    public static void deleteData(String tableName, String month) throws Exception{
        try{
            Connection c = startConnect();
            Statement statement = c.createStatement();
            String sql = "Delete from " + tableName + " where current_month = '" + month + "'";
            statement.executeUpdate(sql);
            log.info("删除" + month + "月份数据成功");
            statement.close();
            c.close();
            log.info("数据库连接以及statement链接已关闭");
        }catch (Exception e){
            log.error("删除数据出错：" + e.toString());
            throw new Exception(e);
        }
    }
    //获取所有条目
    public static ArrayList<ArrayList<String>> getData(String tableName) throws Exception{
        try{
            Connection c = startConnect();
            Statement statement = c.createStatement();
            String sql = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
            while (resultSet.next()){
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(resultSet.getString("current_month"));
                arrayList.add(resultSet.getString("data"));
                allData.add(arrayList);
            }
            log.info("获取所有月份数据成功");
            log.info("从数据库获取到数据 " + allData);
            statement.close();
            c.close();
            log.info("数据库连接以及statement链接已关闭");
            return allData;
        }catch (Exception e){
            log.error("获取数据出错：" + e.toString());
            throw new Exception(e);
        }
    }
    //获取单天数据
    public static String getDataByTime(String tableName, String time) throws Exception {
        try{
            Connection c = startConnect();
            Statement statement = c.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE current_month = '" + time + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            String dataByTime = "无数据";
            if (resultSet.next()) {
                dataByTime = resultSet.getString("data");
                log.info("获取" + time + "月份数据成功");
                log.info("从数据库获取到数据 " + dataByTime);
            }else {
                log.info("获取月份没有数据");
                log.info("未能从数据库获取到数据 " + dataByTime);
            }
            statement.close();
            c.close();
            log.info("数据库连接以及statement链接已关闭");
            return dataByTime;
        }catch (Exception e){
            log.error("获取单个数据出错：" + e.toString());
            throw new Exception(e);
        }
    }

}
