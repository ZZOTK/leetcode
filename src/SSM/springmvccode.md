## MVC工作原理
1. 客户端（浏览器）发送请求，直到请求到DispatchServlet(调度员服务程序)
2. DispatchServlet根据请求信息调用handlerMapping，解析请求对应的Handler(处理者)
3. 解析到对应的Handler后，开始又HandlerAdapter（处理者适配器）处理
4. HandlerAdapter会根据Handler来调用真正的处理器处理请求，并处理业务逻辑
5. 处理完业务之后，会返回一个ModelAndView对象，Model是返回的数据对象，View是逻辑上的View
6. ViewResolver会根据逻辑View查找真正的View
7. DispacherServlet会把返回的Model传给View（视图渲染）
8. 把View返回给请求者（浏览器）



## 请求分发器DispatcherServlet的设计与实现

* DispatcherServlet是SpringMVC的一个前端控制器，是MVC架构中的C，即controller的实现，用于拦截这个web应用的所有请求，具体为在web.xml中配置这个servlet，对应的url-pattern设置为“/”，或者使用servlet3.0之后的WebApplicationInitializer来配置。
  在web容器启动这个应用时，会创建和初始化这个DispatcherServlet对象实例。

* DispatcherServlet在接收到请求之后，会根据请求的url信息，找到对应的某个controller的某个方法来处理这个请求。通常在controller对应的类中使用@Controller和@RequestMapping来唯一确定类的一个方法处理哪些url请求，具体包括路径，http请求方法等信息。

在DispatcherServlet的类的继承体系中，从下到上依次为：DispatcherServlet -> FrameworkServlet -> HttpServletBean。

### 1. HttpServletBean

HttpServletBean的主要作用就是将于Httpservlet相关的init-param，封装成bean属性，然后保存到Environment当中，从而可以在spring容器中被其他bean访问。

### 2. FrameworkServlet

因为DispatcherServlet通常包含一个独立的WebApplication，所以增加FrameworkServlet这层设计。作用就是用于获取，创建与管理，DispatcherServlet所绑定的WebApplicationContext对象.

### 3. DispatcherServlet

从FrameworkServlet中获取WebApplicationContext，然后从WebApplicationContext中获取DispatcherServlet的相关功能子组件bean，然后在自身维护一个引用。实现doService方法并使用这些功能子组件来完成请求的处理和生成响应。

dispatcherserclet的功能子模块：
* 即核心功能组件，主要包括multipart请求处理器multipartResolver，本地化处理器localeResolver，主题（JSP的css样式）处理器themeResolver，请求URI和处理方法映射handlerMappings，请求处理器适配器handlerAdapters，请求异常处理器handlerExceptionResolvers，视图名称解析器viewNameTranslator，视图处理器viewResolvers，转发请求数据存储器flashMapManager。
* 最重要的就是handlerMapping了。


dispatcherservlet的请求处理：
* DispatcherServlet在doService方法中定义对请求的处理逻辑。
* doService方法对请求request进行一些加工，即添加一些attribute，包含当前DispatcherServlet的WebApplicationContext，本地化处理器localeResolver，主题处理器themeResolver等功能组件
* 由子方法doDispatch来选择哪个controler的哪个方法来处理的逻辑，并且生成response响应客户端.

## 请求处理器体系结构设计与实现（handlerMapping）






