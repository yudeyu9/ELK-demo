package com.demo.elk.util.prinLog;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 鱼鱼鱼
 * @data 2019/11/6 15:24
 * 功能: 使用log4j来打印日志(根据自己业务需求可以把日志插入到数据库中)
 * 功能描述:
 */
@Component
@Slf4j
public class PringLog {

    public void insertLog(Object logs) {
        log.info(JSON.toJSONString(logs));
    }

    public void logInfo(Object logs) {
        log.info(JSON.toJSONString(logs));
    }
    public void errorJson(Object error){
        log.error(JSON.toJSONString(error));
    }
    public void error(String message,Exception e){
        log.error(message,e);
    }
}
