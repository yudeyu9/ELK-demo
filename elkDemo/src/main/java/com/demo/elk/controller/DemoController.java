package com.demo.elk.controller;

import com.demo.elk.util.EnError;
import com.demo.elk.util.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class DemoController {
    @PostMapping("/demo")
    public Msg demo(String id,String message){
        Map map = new HashMap();
        map.put("id",id);
        map.put("message",message);
       return new Msg().setResult(EnError.DEFAULT).setData(map);
    }
    @PostMapping("/Exception")
    public void Exception(String id,Integer index){
        try {
            int i = 10 / index;
        }catch (Exception e){
           log.error(e.getMessage(),e);
        }
    }
}
