package com.demo.elk.util.prinLog;

import lombok.Data;

/**
 * @author 鱼鱼鱼
 * @data 2019/11/6 13:50
 * 功能: 请求日志封装字段(特有)
 * 功能描述:
 */
@Data
public class OperationLogRequest extends OperationLog {

    //请求参数
    private Object params;
    //消息体格式
    private String contentType;
    //请求地址
    private String requestURL;
}
