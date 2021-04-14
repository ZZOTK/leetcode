## javaweb中的mvc三层构架

* Model:业务处理 ：业务逻辑 （service),数据持久层： CRUD （Dao）
* View:展示数据,提供链接发起service请求
* Controller :接收用户的请求,交给业务层处理相应的代码,控制视图跳转

## SSM框架中各层的含义和联系
1. pojo层     
    * 也有人称其为model、domain、bean等，pojo层是对应的数据库表的**实体类**。     

2. 持久层：Dao层（Mapper）       
    * Dao（Data access object）层，称为数据访问层。负责与数据库进行联络的一些任务都封装在此，具体到对于某个表、某个实体的增删改查。

3. 服务层：Service层       
    * Service层主要负责业务模块的逻辑应用设计。可以细分为service接口和serviceImpl实现类。

4. Controller层（Handler）
    * Controller层负责具体的业务模块流程的控制。在此层里面要调用Service层的接口来控制业务流程；控制的配置也同样是在Spring的配置文件里面进行，针对具体的业务流程，会有不同的控制器。

各层之间的联系：

对象的调用流程：前端JSP ——> Controller ——>  Service ——> Dao ——> 数据库  

## 转发(Forward)和重定向(Redirect)的区别
转发是服务器行为，重定向是客户端行为。

## MVC工作原理
1. 客户端（浏览器）发送请求，直到请求到DispatchServlet(调度员服务程序)
2. DispatchServlet根据请求信息调用handlerMapping，解析请求对应的Handler(处理者)
3. 解析到对应的Handler后，开始又HandlerAdapter（处理者适配器）处理
4. HandlerAdapter会根据Handler来调用真正的处理器处理请求，并处理业务逻辑
5. 处理完业务之后，会返回一个ModelAndView对象，Model是返回的数据对象，View是逻辑上的View
6. ViewResolver会根据逻辑View查找真正的View
7. DispacherServlet会把返回的Model传给View（视图渲染）
8. 把View返回给请求者（浏览器）



