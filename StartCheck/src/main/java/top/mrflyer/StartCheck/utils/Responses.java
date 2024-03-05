package top.mrflyer.StartCheck.utils;

import lombok.Data;

@Data
public class Responses {

    public int code;
    public Object data;
    public String message;

    public Responses(int code,Object data,String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public Responses success(Object data){
        return new Responses(200,data,"success");
    }
    public Responses success(Object data,int code,String message){
        return new Responses(code,data,message);
    }
    public Responses fail(Object data,int code,String message){
        return new Responses(code,data,message);
    }


}
