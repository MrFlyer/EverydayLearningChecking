package top.mrflyer.StartCheck.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mrflyer.StartCheck.mapper.PostgreSqlJdbcConn;

@RestController
public class StartCheckApplicationController {
    @RequestMapping(value = "/ProgramData")
    public String getDataFromDate(@RequestParam(value = "dateNow") String dateNow) throws Exception {
        System.out.println(PostgreSqlJdbcConn.getData("test"));
        return "hello" + dateNow;

    }
}
