
1. 建表：sys_email
2. 插入一些必要的字典数据与记录
3. 引包 pom文件增加：
<!-- 邮件工具类 -->
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-email</artifactId>
  <version>1.5</version>
</dependency>
4. application.yml增加内容
# 邮件
email: 
  # 缺省的发送方地址
  sysFrom: xxx@163.com
  # 缺省的FROM地址
  alias: 邮件管理
  # SMTP服务器名称 
  smtp: smtp.163.com
  # SMTP端口
  smtpPort: 25
  # 是否使用SSL
  ssl: false
  # SSL端口
  sslPort: 26
  # 认证用户名
  loginName: xxx@163.com
  # 认证用户密码
  password: xxxx
5. 增加froala文件夹(这个是富文本框插件)，位置：/RuoYi/src/main/resources/static/ajax/libs/froala
6. 增加配置文件，位置：/RuoYi/src/main/java/com/ruoyi/framework/config/EmailConfig.java
    增加模块文件，位置：com.ruoyi.project.tool.email
    增加mapper，位置: /RuoYi/src/main/resources/mybatis/tool/EmailMapper.xml
    增加页面，位置: /RuoYi/src/main/resources/templates/tool/email
    ry-ui.min.js 增加一个跳转：很简单==》
           href: function(url) {
            	window.location.href = ctx + url;
            },
   没有改ry-ui.mini.js的朋友，直接替换也行
7. 启动后给角色加上邮件管理的权限即可


	
	 