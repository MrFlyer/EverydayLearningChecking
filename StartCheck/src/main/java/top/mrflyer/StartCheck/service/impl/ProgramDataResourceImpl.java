package top.mrflyer.StartCheck.service.impl;

import top.mrflyer.StartCheck.mapper.PostgreSqlJdbcConn;
import top.mrflyer.StartCheck.service.ProgramDataResource;

import java.util.ArrayList;

public class ProgramDataResourceImpl implements ProgramDataResource {
    private final String DB_NAME = "postgres";

    @Override
    public void createForm(String tableName) throws Exception {
        PostgreSqlJdbcConn.createTable(tableName);
    }

    @Override
    public String getProgramByDate(String dateNow) throws Exception {
        return PostgreSqlJdbcConn.getDataByTime(DB_NAME,dateNow);
    }

    @Override
    public ArrayList<ArrayList<String>> getAllData() throws Exception {
        return PostgreSqlJdbcConn.getData(DB_NAME);
    }

    @Override
    public void deleteDataByDate(String dateNow) throws Exception {
        PostgreSqlJdbcConn.deleteData(DB_NAME, dateNow);
    }

    @Override
    public void addDataWithoutDate(String data) throws Exception {
        PostgreSqlJdbcConn.addData(DB_NAME,data);
    }

    @Override
    public void updateDataByDate(String dateNow, String data) throws Exception {
        PostgreSqlJdbcConn.updateData(DB_NAME,data,dateNow);
    }


}
