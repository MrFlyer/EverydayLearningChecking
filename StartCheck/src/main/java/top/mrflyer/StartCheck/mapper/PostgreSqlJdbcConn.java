package top.mrflyer.StartCheck.mapper;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class PostgreSqlJdbcConn {
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
            log.error("Get Error" + e.toString());
        }
    }

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
            log.error("Get Error" + e.toString());
        }
    }

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
            log.error("Get Error" + e.toString());
        }
    }

}
