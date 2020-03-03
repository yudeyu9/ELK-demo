package com.demo.elk.util.prinLog;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 鱼鱼鱼
 * @data 2019/11/6 16:28
 * 功能: 公共日志字段
 * 功能描述:
 */
@Data
public class OperationLog {
    //当前线程名称
    private String threadName;
    //请求的方法
    private String method;
    //日志打印时间
    private LocalDateTime createDate;
    //ip地址
    private String ip;
    //请求路径的上下文
    private String uri;
    //请求的方式 post/get ..
    private String reqMethod;
    //主机名称
    private String hostName;
    //项目名称
    private String appName;
    //日志类型(请求  返回)
    private String logType;

}
