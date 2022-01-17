# projact

## 环境：

基本运行：JDK.18

项目构建：maven

图像环境：OpenCV – 3.4.14

## 工程结构：

````
|_annotation：放置项目自定义注解（无）
|_aspect：可以创建来放日志（无）
|_config：放置配置类
|_constant：放置常量、枚举等定义
   |__consist：存放常量定义
   |__enums：存放枚举定义
|_controller：放置控制器代码
	|__client：放置客户端代码
	|__impl：存放控制层的一些接口
	|__invoker：客户端和命令之间的调度者
|_filter：放置一些过滤、拦截相关的代码（无）
|_mapper：放置数据访问层代码接口（无）
|_model：放置数据模型代码
   |__entity：放置数据库实体对象定义
   |__dto：存放数据传输对象定义
   |__vo：存放显示层对象定义
|_service：放置具体的业务逻辑代码（接口和实现分离）
   |__intf：存放业务逻辑接口定义
   |__impl：存放业务逻辑实际实现
   |___command:存放图片比对，移动键盘，鼠标命令
   |___factory:工厂类，通过xml进行生成类
   |___Image:存放图像类，不建议新手阅读
   |___receive:真正的命令执行存在这里
   |___Thread:存放线程
|_utils：放置工具类和辅助代码
	|__factory:IOC工具，用来读取XML文件的工具类
	|__Image:存放图像相关工具类，比如转换格式
````







