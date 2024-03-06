package top.mrflyer.StartCheck.service;

import java.util.ArrayList;

public interface ProgramDataResource {

    public void createForm(String tableName) throws Exception;
    //通过日期查单独数据
    public String getProgramByDate(String dateNow) throws Exception;
    //直接查出来所有
    public ArrayList<ArrayList<String>> getAllData() throws Exception;
    //通过日期删数据
    public void deleteDataByDate(String dateNow) throws Exception;
    //直接添加数据（根据当天日期）
    public void addDataWithoutDate(String data) throws Exception;
    //通过日期更新当天数据
    public void updateDataByDate(String dateNow, String data) throws Exception;
}
