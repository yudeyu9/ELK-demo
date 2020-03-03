package com.demo.elk.util;


//import lombok.ToString;

import com.demo.elk.util.EnError;

public class Msg<T> {

    public static void main(String[] args){
        String srByBuilder = new
                StringBuilder().append("aa").append("bb").append("cc").append
                ("dd").toString();
        String srByConcat = "aa" + "bb" + "cc" + "dd";
        String test = "aabbccdd";
    }
    public static void  test(){
    }

    private Integer code = EnError.DEFAULT.getCode();

    /* 返回的数据，可以任意集合 */
    private T data;

    /* 结果说明 */
    private String msg = EnError.DEFAULT.getDescription();

    /* 错误 */
    private Integer errCode = EnError.DEFAULT.getErrCode();

    public Msg setResult(EnError result) {
        this.errCode = result.getErrCode();
        this.code = result.getCode();
        this.msg = result.getDescription();
        this.data = null;

        return this;
    }

    public void setErrorResult(@SuppressWarnings("rawtypes") Msg errorMsg) {
        this.msg = errorMsg.getMsg();
        this.code = errorMsg.getCode();
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public Integer getCode() {
        return code;
    }

    public Integer getErrCode() {
        return errCode;
    }

    /**
     * Setter for property 'code'.
     *
     * @param code Value to set for property 'code'.
     */
    public Msg<T> setCode(Integer code) {
        this.code = code;

        return this;
    }

    public Msg<T> setErrCode(Integer errCode) {
        this.errCode = errCode;

        return this;
    }

    /**
     * Getter for property 'data'.
     *
     * @return Value for property 'data'.
     */
    public T getData() {
        return data;
    }

    /**
     * Setter for property 'data'.
     *
     * @param data Value to set for property 'data'.
     */
    public Msg<T> setData(T data) {
        this.data = data;

        return this;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter for property 'msg'.
     *
     * @param msg Value to set for property 'msg'.
     */
    public Msg<T> setMsg(String msg) {
        this.msg = msg;

        return this;
    }



}
