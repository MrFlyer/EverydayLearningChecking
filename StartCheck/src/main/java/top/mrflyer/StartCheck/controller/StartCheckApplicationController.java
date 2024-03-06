package top.mrflyer.StartCheck.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mrflyer.StartCheck.bean.Response;
import top.mrflyer.StartCheck.mapper.PostgreSqlJdbcConn;
import top.mrflyer.StartCheck.service.impl.ProgramDataResourceImpl;

@RestController
public class StartCheckApplicationController {
    @Autowired
    ProgramDataResourceImpl programDataResource;

    //查单个日期记录
    @RequestMapping(value = "/GetProgramData")
    public Response getDataFromDate(@RequestParam(value = "dateNow") String dateNow) throws Exception {
        System.out.println(dateNow);
        return programDataResource.getProgramByDate(dateNow);
    }

    //查所有日期记录
    @RequestMapping(value = "/GetAllData")
    public Response getAllDate() throws Exception {
        return programDataResource.getAllData();
    }

    //增当天记录
    @RequestMapping(value = "/AddCurrentDayProgramData")
    public Response addCurrentDayProgramData(@RequestParam(value = "data") String data) throws Exception {
        return programDataResource.addDataWithoutDate(data);
    }

    //删指定日期记录
    @RequestMapping(value = "/DeleteProgramData")
    public Response deleteProgramData(@RequestParam(value = "dateNow") String dateNow) throws Exception {
        return programDataResource.deleteDataByDate(dateNow);
    }

    //在原有基础上指定日期记录
    @RequestMapping(value = "/UpdateProgramData")
    public Response updateProgramData(@RequestParam(value = "dateNow") String dateNow, @RequestParam(value = "data") String data) throws Exception {
        return programDataResource.updateDataByDate(dateNow,data);
    }
}
