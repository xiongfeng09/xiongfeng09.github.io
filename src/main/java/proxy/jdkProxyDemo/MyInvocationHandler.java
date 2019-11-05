package proxy.jdkProxyDemo;

import java.lang.reflect.Method;

public interface MyInvocationHandler {
    void invoke(Method m, Object... args) throws Exception;
}