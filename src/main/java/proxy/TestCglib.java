package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

public class TestCglib {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallback( new MethodInterceptorImpl() );


        MyClass my = (MyClass)enhancer.create();
        System.out.println(enhancer.create().getClass());

        my.method("a", Optional.empty());
        Date start = new Date();
        long nums = 10000;
        for (int i = 0; i < nums; i++) {
            MyClass userService = (MyClass)enhancer.create();
        }
        Date end = new Date();
        System.out.println("创建"+nums+"个代理代理对象用时："+(end.getTime()-start.getTime())+"毫秒。");
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        public Object intercept(Object obj,
                                Method method,
                                Object[] args,
                                MethodProxy proxy) throws Throwable {

            System.out.println(method);

            args[1] = Optional.of("a");
            proxy.invokeSuper(obj, args);

            return null;
        }
    }
}

class MyClass {

    public void method(String a, Optional<String> b) {
        System.out.println("MyClass.method()" + "a:" + a + " b:" + b);
    }
}
