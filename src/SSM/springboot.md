#springboot的自动装配原理
自动配置真正实现是从classpath中搜寻所有的META-INF/spring.factories配置文件,
并将其中对应的 org.springframework.boot.autoconfigure. 包下的配置项，
通过反射实例化为对应标注了 @Configuration的JavaConfig形式的IOC容器配置类，
然后将这些都汇总成为一个实例并加载到IOC容器中。

>结论：        
>+ SpringBoot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值
将这些值作为自动配置类导入容器 ， 自动配置类就生效 ， 帮我们进行自动配置工作；       
>+ 整个J2EE的整体解决方案和自动配置都在springboot-autoconfigure的jar包中；
>+ 它会给容器中导入非常多的自动配置类 （xxxAutoConfiguration）, 就是给容器中导入这个场景需要的所有组件 ， 并配置好这些组件 ； 
>+ 有了自动配置类 ， 免去了我们手动编写配置注入功能组件等的工作；