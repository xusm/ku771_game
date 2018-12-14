package com.ku771.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**   
 * @title：服务器响应封装类
 * @class：ServerResponse   
 * @description：   统一服务器响应封装类
 * @author：Eric
 * @date： 2018年12月6日
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    /**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	//返回数据状态---0：成功； 1：失败
	private int status;
	
	//返回消息
    private String msg;
    
    //返回数据
    private T data;
    
    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    public ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    @SuppressWarnings("rawtypes")
	public static ServerResponse createByError(){
        return new ServerResponse(ResponseCode.ERROR.getCode());
    }

    @SuppressWarnings("rawtypes")
    public static ServerResponse createByErrorMessage(String errorMessage){
        return new ServerResponse(ResponseCode.ERROR.getCode(),errorMessage);
    }

    @SuppressWarnings("rawtypes")
    public static ServerResponse createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse(errorCode,errorMessage);
    }

    @JsonIgnore
    public boolean isSuccess(){
        return ResponseCode.SUCCESS.getCode() == status;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
