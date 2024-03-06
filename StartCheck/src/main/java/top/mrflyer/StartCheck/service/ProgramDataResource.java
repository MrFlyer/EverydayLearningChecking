package top.mrflyer.StartCheck.service;

import top.mrflyer.StartCheck.bean.Response;


public interface ProgramDataResource {

    public Response createForm(String tableName) throws Exception;
    //通过日期查单独数据
    public Response getProgramByDate(String dateNow) throws Exception;
    //直接查出来所有
    public Response getAllData() throws Exception;
    //通过日期删数据
    public Response deleteDataByDate(String dateNow) throws Exception;
    //直接添加数据（根据当天日期）
    public Response addDataWithoutDate(String data) throws Exception;
    //通过日期更新当天数据
    public Response updateDataByDate(String dateNow, String data) throws Exception;
}
