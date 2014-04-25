创智播客 客户管理系统案例
==============

不用安装Mysql数据库, 直接内嵌运行H2db

请安装, 使用 sbt 运行

`sbt test` 运行测试, 只在Linux测试过, 如果是Windows, 请修改DbUtil.makeTmpDir方法,

`sbt container start` 运行服务器, 通过 http//localhost:8080访问

