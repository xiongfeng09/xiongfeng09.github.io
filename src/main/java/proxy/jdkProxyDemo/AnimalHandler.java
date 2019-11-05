package proxy.jdkProxyDemo;

import java.lang.reflect.Method;

public class AnimalHandler implements MyInvocationHandler {

    private Object animal;

    public AnimalHandler (Object animal){
        this.animal = animal;
    }

    public void invoke(Method m,Object[] args) throws Exception{
        System.out.println("before");
        m.invoke(animal, args);
        System.out.println("after");
    }
}
