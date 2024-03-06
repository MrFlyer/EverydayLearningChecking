package top.mrflyer.StartCheck.service.impl;

import org.springframework.stereotype.Service;
import top.mrflyer.StartCheck.bean.Response;
import top.mrflyer.StartCheck.mapper.PostgreSqlJdbcConn;
import top.mrflyer.StartCheck.service.ProgramDataResource;
import top.mrflyer.StartCheck.utils.ResponseUtil;

@Service
public class ProgramDataResourceImpl implements ProgramDataResource {
    private final String DB_NAME = "test";

    @Override
    public Response createForm(String tableName) throws Exception {
        try{
            PostgreSqlJdbcConn.createTable(tableName);
            return ResponseUtil.success("表创建成功或表已经存在");
        }catch (Exception e){
            return ResponseUtil.fail("创建表失败: " + e);
        }
    }

    @Override
    public Response getProgramByDate(String dateNow) throws Exception {
        try{
            return ResponseUtil.success(PostgreSqlJdbcConn.getDataByTime(DB_NAME,dateNow));
        }catch (Exception e){
            return ResponseUtil.fail("查询数据库失败: " + e);
        }
    }

    @Override
    public Response getAllData() throws Exception {
        try{
            return ResponseUtil.success(PostgreSqlJdbcConn.getData(DB_NAME));
        }catch (Exception e){
            return ResponseUtil.fail("查询数据库失败: " + e);
        }
    }

    @Override
    public Response deleteDataByDate(String dateNow) throws Exception {
        try{
            PostgreSqlJdbcConn.deleteData(DB_NAME, dateNow);
            return ResponseUtil.success("条目删除成功");
        }catch (Exception e){
            return ResponseUtil.fail("条目删除失败: " + e);
        }
    }

    @Override
    public Response addDataWithoutDate(String data) throws Exception {
        try{
            PostgreSqlJdbcConn.addData(DB_NAME, data);
            return ResponseUtil.success("数据条目添加成功");
        }catch (Exception e){
            return ResponseUtil.fail("条目添加失败: " + e);
        }
    }

    @Override
    public Response updateDataByDate(String dateNow, String data) throws Exception {
        try{
            PostgreSqlJdbcConn.updateData(DB_NAME, data, dateNow);
            return ResponseUtil.success("数据条目更新添加成功");
        }catch (Exception e){
            return ResponseUtil.fail("条目更新添加失败: " + e);
        }
    }


}
