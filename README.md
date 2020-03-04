# ELK日志收集系统

#### 介绍
ELK日志收集系统

#### 软件架构
ELK日志收集采用的框架:elasticsearch-6.2.4 + logstash-5.6.1 + kibana-6.2.4-linux-x86_64
*采集的对象:springboot+logback+WebFilter

#### 安装教程
  
  一.ELK的安装请自行百度
  *二.代码中的注意事项
  *1.在build.gradle中添加jar包,该jar包是转换为logstash可以直接解析的json,该项目中主要用户打印异常信息
  *//转换为logstash格式
*	compile 'net.logstash.logback:logstash-logback-encoder:5.1'
  *因为logstash解析json类容,所以请求和返回的日志都转换为json字符串
  *//json
*	compile group: 'com.alibaba', name: 'fastjson', version: '1.2.62'
  
  
 * 2.日志配置文件logback-sprin.xml
 * <!-- 需要更改为对应AOP的包名 切记-->
   * <logger name="com.demo.elk.util.prinLog.PringLog" level="INFO" additivity="false">
 *       <!-- 打印请求和返回的数据 通过ELK进行管理-->
  *      <appender-ref ref="logstash"/>
  *  </logger>
 *   com.demo.elk.util.prinLog.PringLog需要更改为打印日志的那个类地址
 *   三.logstash的配置文件
  *  input是采集的类容,可以用多个file,每个file通过type类区分,包括filter中解析的方式也可以使用type的区分每个file的解析格式
 *   output是采集后输出,也通过type来区分,每个file的索引名称等
    input {
    # 从文件读取日志信息 输送到控制台
    file {
        path => "/logs/elkDemo/info/*.log"
        type => "elkDemo-info"
        start_position => "beginning"
    }
	 file {
        path => "/logs/elkDemo/error/*.log"
        type => "elkDemo-error"
        start_position => "beginning"
    }
}
filter {
	json {
	    source => "message"
	} 
 }

output {
    # 标准输出 
    # stdout {}
    # 输出进行格式化，采用Ruby库来解析日志   
if[type]=="elkDemo-info"{
     stdout { codec => rubydebug }
	 elasticsearch {
        hosts => ["localhost:9200"]
        index => "elkDemo-info-%{+YYYY.MM.dd}"
    }	 
}
if[type]=="elkDemo-error"{
     stdout { codec => rubydebug }
         elasticsearch {
        hosts => ["localhost:9200"]
        index => "elkDemo-error-%{+YYYY.MM.dd}"
    }    
}
}


#### 声明

*1. 本项目素材均来自网络,本人通过整理验证后的类容,如有侵权请联系本人修改.
*2. 有很多不足之处,欢迎各位大牛指正,谢谢
*3. 日志收集方案有很多,本方案只是其中的一种,有更好的方案欢迎提供
