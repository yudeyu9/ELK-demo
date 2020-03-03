package com.demo.elk.util;

public enum EnError {
    DEFAULT(0,0,"操作成功");
	private Integer errCode = 0;
	
    private Integer code = 0;

    private String description = "";

    public Integer getCode() {
        return code;
    }

    public Integer getErrCode() {
    	return errCode;
    }

    public String getDescription() {
        return description;
    }
    
    EnError(Integer errCode, Integer code, String description) {
        this.errCode = errCode;
    	this.code = code;
        this.description = description;
    }
    
}
