package top.mrflyer.StartCheck.utils;


import top.mrflyer.StartCheck.bean.Response;


public class ResponseUtil {
    private ResponseUtil(){}
    public static Response success(int code, String message, Object data) {
        return new Response(code, message, data);
    }

    public static Response success(Object data){
        return success(200, null, data);
    }

    public static Response fail(int code, String message, Object data) {
        return new Response(code, message, data);
    }

    public static Response fail(String message) {
        return fail(400, message, null);
    }


}
