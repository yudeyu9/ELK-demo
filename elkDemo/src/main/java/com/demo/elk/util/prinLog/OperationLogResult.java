package com.demo.elk.util.prinLog;

import lombok.Data;

/**
 * @author 鱼鱼鱼
 * @data 2019/11/6 16:31
 * 功能: 返回结果日志字段(特有)
 * 功能描述:
 */
@Data
public class OperationLogResult extends OperationLog {
    //运行时间
    private long runTime;
    //返回结果
    private Object resultData;

}
