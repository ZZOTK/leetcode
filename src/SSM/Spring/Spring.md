官方文档（中文）：       
https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference

# IOC(控制反转)
IoC（Inverse of Control:控制反转）是一种设计思想，就是 将原本在程序中手动创建对象的控制权，交由Spring框架来管理。 IoC 在其他语言中也有应用，并非 Spring 特有。 
IoC 容器是 Spring 用来实现 IoC 的载体， IoC 容器实际上就是个Map（key，value）,Map 中存放的是各种对象。
* 依赖注入： 我们可以通过写java代码或者xml文件配置的方式，把我们想要注入对象所依赖的Bean注入进去。通过type类型的方式注入。
* 有了依赖注入，我们做到了解耦。
* Spring容器初始化时，会把Bean都创建好，就不需要再创建，速度变快。

IoC 容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。

IOC的初始化过程：
![img.png](ioc.png)

**获取依赖对象的方式从程序到第三方（用户）**。


# AOP(面向切面编程)
AOP(Aspect-Oriented Programming:面向切面编程)能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。

Spring AOP就是基于动态代理的，如果要代理的对象，实现了某个接口，那么Spring AOP会使用JDK Proxy，去创建代理对象，而对于没有实现接口的对象，就无法使用 JDK Proxy 去进行代理了，这时候Spring AOP会使用Cglib ，这时候Spring AOP会使用 Cglib 生成一个被代理对象的子类来作为代理，如下图所示

![img.png](aop.png)

AOC的本质：不影响原有类的功能的基础下，横向实现类功能的增强。

代理模式的好处：
* 可以使真实角色的操作更加纯粹，不关注公共的业务
* 公共部分交给了代理角色，实现了业务的分工
* 公共业务发生拓展时，方便集中管理

## Spring 中的 bean 的作用域有哪些?
* singleton : 唯一 bean 实例，Spring 中的 bean 默认都是单例的。
* prototype : 每次请求都会创建一个新的 bean 实例。
* request : 每一次HTTP请求都会产生一个新的bean，该bean仅在当前HTTP request内有效。
* session : 每一次HTTP请求都会产生一个新的 bean，该bean仅在当前 HTTP session 内有效。
* global-session： 全局session作用域，仅仅在基于portlet的web应用中才有意义，Spring5已经没有了。Portlet是能够生成语义代码(例如：HTML)片段的小型Java Web插件。它们基于portlet容器，可以像servlet一样处理HTTP请求。但是，与 servlet 不同，每个 portlet 都有不同的会话


# Bean的生命周期
Bean可以借鉴Servlet的生命周期，实例化、初始init、接收请求service、销毁destroy。
![img.png](bean.png)

简单理解一下：
1. 实例化bean对象
    * 设置对象属性
    * BeanPostProcessor后置处理工作
2. 实例化完成，就可以开始工作    
3. 销毁

## Spring 中的单例 bean 的线程安全问题了解吗？
的确是存在安全问题的。因为，当多个线程操作同一个对象的时候，对这个对象的成员变量的写操作会存在线程安全问题。

但是，一般情况下，我们常用的 Controller、Service、Dao 这些 Bean 是无状态的。无状态的 Bean 不能保存数据，因此是线程安全的。

常见的有 2 种解决办法：

* 在类中定义一个 ThreadLocal 成员变量，将需要的可变成员变量保存在 [ThreadLocal]() 中（推荐的一种方式）。
* 改变 Bean 的作用域为 “prototype”：每次请求都会创建一个新的 bean 实例，自然不会存在线程安全问题