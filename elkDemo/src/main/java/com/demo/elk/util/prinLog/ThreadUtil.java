package com.demo.elk.util.prinLog;

import java.util.UUID;

/**
 * 线程序号标识生成工具 
 * 
 * @author leizhimin 2008-8-21 21:28:54 
 */ 
public class ThreadUtil {
    private static volatile ThreadLocal<String> threadID=new ThreadLocal<String>();
        public static String setThreadId(){
            String s = UUID.randomUUID().toString();
            threadID.set(s);
            return s;
        }
        public static String getThreadId(){
            return threadID.get();
        }

        }
