
1. ����sys_email
2. ����һЩ��Ҫ���ֵ��������¼
3. ���� pom�ļ����ӣ�
<!-- �ʼ������� -->
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-email</artifactId>
  <version>1.5</version>
</dependency>
4. application.yml��������
# �ʼ�
email: 
  # ȱʡ�ķ��ͷ���ַ
  sysFrom: xxx@163.com
  # ȱʡ��FROM��ַ
  alias: �ʼ�����
  # SMTP���������� 
  smtp: smtp.163.com
  # SMTP�˿�
  smtpPort: 25
  # �Ƿ�ʹ��SSL
  ssl: false
  # SSL�˿�
  sslPort: 26
  # ��֤�û���
  loginName: xxx@163.com
  # ��֤�û�����
  password: xxxx
5. ����froala�ļ���(����Ǹ��ı�����)��λ�ã�/RuoYi/src/main/resources/static/ajax/libs/froala
6. ���������ļ���λ�ã�/RuoYi/src/main/java/com/ruoyi/framework/config/EmailConfig.java
    ����ģ���ļ���λ�ã�com.ruoyi.project.tool.email
    ����mapper��λ��: /RuoYi/src/main/resources/mybatis/tool/EmailMapper.xml
    ����ҳ�棬λ��: /RuoYi/src/main/resources/templates/tool/email
    ry-ui.min.js ����һ����ת���ܼ�==��
           href: function(url) {
            	window.location.href = ctx + url;
            },
   û�и�ry-ui.mini.js�����ѣ�ֱ���滻Ҳ��
7. ���������ɫ�����ʼ������Ȩ�޼���


	
	 