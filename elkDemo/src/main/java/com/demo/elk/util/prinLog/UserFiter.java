package com.demo.elk.util.prinLog;


import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(1)
@WebFilter(urlPatterns = "/*") //拦截所有请求
public class UserFiter implements Filter {
    String START_TIME = "startTime";//开始时间
    String START = "start";//表示请求
    String END = "end";//表示返回

    @Autowired
    PringLog pringLog;
    //服务端ip地址
    private static String localIp;

    @Override
    public void init(FilterConfig filterConfig) {
        //初始化服务端ip地址
        localIp = IpUtils.getLocalIP();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //先判断是否排除拦截
        boolean path = isPath(request, response);
        if (path) {//排除的请求不打印请求和返回的日志信息
            try {
                chain.doFilter(request, response);
            }catch (Exception e){
                //打印异常
//                pringLog.error(e.getMessage(),e);
                log.error(e.getMessage(),e);
            }
        } else {
            //需要打印请求和返回的日志信息
            saveSysLog(request, response);//打印请求日志
            ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);//封装response 打印返回数据
            try {
                chain.doFilter(request, wrapper);
                //把返回的值  添加到response中
                response.getOutputStream().write(wrapper.getContent());
                around(request, wrapper);//打印返回日志
            }catch (Exception e){
                //打印异常
                System.out.println(e.getMessage()+"  异常");
               log.error(e.getMessage(),e);
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 打印请求日志
     *
     * @param request
     * @param response
     */
    public void saveSysLog(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;//转换request
        request.setAttribute(START_TIME, System.currentTimeMillis());//设置接收请求时间  用于计算运行时间
        OperationLogRequest logs = new OperationLogRequest();
        //请求地址
        logs.setUri(req.getRequestURI());
        //消息体格式
        logs.setContentType(req.getContentType());
        //当前线程名称
        logs.setThreadName(Thread.currentThread().getName()+"    "+ThreadUtil.setThreadId());
        //请求方式 （例如GET、POST等等）
        logs.setReqMethod(req.getMethod());
        //当前时间
        logs.setCreateDate(LocalDateTime.now());
        //请求地址
        logs.setRequestURL(req.getRequestURL().toString());
        //本地ip
        logs.setIp(localIp);
        //获取项目名称
        logs.setAppName(req.getContextPath());
        //获取请求参数
        logs.setParams(req.getQueryString());
        //设置容器名称 暂时获取不到
        logs.setHostName(System.getenv().get("COMPUTERNAME"));
        //日志类型  请求
        logs.setLogType(START);
        //获取当前登录用户
        pringLog.insertLog(logs);
    }

    /**
     * 打印返回日志
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void around(ServletRequest request, ResponseWrapper response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        OperationLogResult logs = new OperationLogResult();
        Long startTime = (Long) request.getAttribute(START_TIME);
        //当前线程名称
        logs.setThreadName(Thread.currentThread().getName()+"   "+ThreadUtil.getThreadId());
        //返回地址
        logs.setCreateDate(LocalDateTime.now());
        if (response.getContent().length>0){
            //返回的数据  new HashMap()是格式化数据 kibana中方便查看
            Map map = new HashMap();
            map.put("msg",new String(response.getContent(), "UTF-8"));
            logs.setResultData(map);
        }
        //请求路径的上下文
        logs.setUri(req.getRequestURI());
        //请求方式
        logs.setReqMethod(req.getMethod());
        //项目名称
        logs.setAppName(req.getContextPath());
        //        //设置容器名称
        logs.setHostName(System.getenv().get("COMPUTERNAME"));
        //本机ip地址
        logs.setIp(localIp);
        //日志类型  返回
        logs.setLogType(END);
        //运行时间
        logs.setRunTime(System.currentTimeMillis() - startTime);
        pringLog.insertLog(logs);
    }

    /**
     * 排除拦截请求地址
     * @param request
     * @param response
     * @return
     */
    public boolean isPath(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;//转换request
        boolean allowedPath = req.getRequestURI().contains("swagger-") || req.getRequestURI().contains("api-docs") || req.getRequestURI().contains("favicon");
        return allowedPath;
    }
}
