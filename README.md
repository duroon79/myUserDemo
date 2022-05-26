## main class
1. module: starter
1. class: com.demo.UserApplication


##发件箱配置(建议修改)
spring:
  mail:
    host: smtp.163.com #邮件服务器地址
    username: duroon79@163.com  
    password: PATUHLNZSQYARTYO


##程序运行前提条件
1.服务器上必须安装JDK8
2.服务器上必须安装Maven

##程序运行步骤

1.下载源代码到服务器
2.修改userdemo.sh第一行的JAVA_HOME指向服务器的JDK路径
3.运行userdemo.sh <source_code_path> <docker_file_path> <image_tag> 例如:
  ./userdemo.sh /app/user/userdemo /app/user 3
4.使用如下链接访问RESTFUL API http://server_ip:96/user/<inferface>

##接口说明文档地址
http://server_ip:96/user/swagger-ui/index.html#