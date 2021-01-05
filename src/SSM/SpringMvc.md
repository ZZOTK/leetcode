#javaweb中的mvc三层构架
>Model:     
> + 业务处理 ：业务逻辑 （service)
> + 数据持久层： CRUD （Dao）   
> 
>View:
> + 展示数据
> + 提供链接发起service请求
> 
> Controller
> + 接收用户的请求
> + 交给业务层处理相应的代码
> + 控制视图跳转

#SSM框架中各层的含义和联系
>pojo层     
也有人称其为model、domain、bean等，pojo层是对应的数据库表的**实体类**。     

>持久层：Dao层（Mapper）       
Dao（Data access object）层，称为数据访问层。负责与数据库进行联络的一些任务都封装在此，具体到对于某个表、某个实体的增删改查。

>服务层：Service层       
Service层主要负责业务模块的逻辑应用设计。可以细分为service接口和serviceImpl实现类。

>Controller层（Handler）
Controller层负责具体的业务模块流程的控制。      
在此层里面要调用Service层的接口来控制业务流程；     
控制的配置也同样是在Spring的配置文件里面进行，针对具体的业务流程，会有不同的控制器。
各层之间的联系：

对象的调用流程：前端JSP ——> Controller ——>  Service ——> Dao ——> 数据库  