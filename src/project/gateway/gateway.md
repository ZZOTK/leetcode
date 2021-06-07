## API网关
API网关是一个服务器，是系统的唯一入口。从面向对象设计的角度看，与外观模式类似。

API网关封装了系统内部架构，为每个客户端提供一个定制的API。它可能还具有其他职责，比如身份验证，监控，负载均很，缓存，请求分片与管理等。

API网关方式的核心要点是，所有的客户端和消费端都通过同一的网关接入微服务，在网关层处理所有的非业务功能。通常，网关也提供REST/HTTP访问的API。

## 为什么要网关？
微服务下一个系统被拆分为多个服务，但是像 安全认证，流量控制，日志，监控等功能是每个服务都需要的，没有网关的话，我们就需要在每个服务中单独实现，这使得我们做了很多重复的事情并且没有一个全局的视图来统一管理这些功能。

综上：一般情况下，网关一般都会提供请求转发、安全认证（身份/权限认证）、流量控制、负载均衡、容灾、日志、监控这些功能。

实际上网关主要做了一件事情：请求过滤 。权限校验、流量控制这些都可以通过过滤器实现，请求转也是通过过滤器实现的。

## 限流的算法有哪些？


