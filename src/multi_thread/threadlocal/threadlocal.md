## ThreadLocal 实现原理是什么？
这使我们能够为当前线程单独存储数据 - 并简单地将其包装在特殊类型的对象中。

该TheadLocal结构使我们能够存储数据，这将是访问只通过一个特定的线程。

假设我们想要一个与特定线程捆绑在一起的Integer值：

```java
ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();
```

接下来，当我们想从线程使用这个值时，我们只需要调用get()或set()方法。简单地说，我们可以认为ThreadLocal将数据存储在一个映射中——以线程为键。

因此，当我们在threadLocalValue上调用get()方法时，我们将获得请求线程的Integer值：

```java
threadLocalValue.set(1);
Integer result = threadLocalValue.get();
```

我们可以通过使用withInitial()静态方法并将供应商传递给它来构造ThreadLocal的实例：

```java
ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);
```

要从ThreadLocal 中删除值，我们可以调用 remove()方法：

```java
threadLocal.remove();
```

## threadlocal与线程池
同时使用时应该各外小心：
1. 首先，应用程序从池中借用一个线程。
2. 然后它将一些线程限制值存储到当前线程的 ThreadLocal 中。
3. 当前执行完成后，应用程序将借用的线程返回到池中。
4. 过了一会儿，应用程序借用同一个线程来处理另一个请求。
5. **由于应用程序上次没有执行必要的清理，它可能会 为新请求重新使用相同的ThreadLocal数据。**

