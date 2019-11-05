package proxy.jdkProxyDemo;

import java.lang.reflect.*;

public class Proxy$1 implements proxy.jdkProxyDemo.Animal{
   private proxy.jdkProxyDemo.MyInvocationHandler handler;
   public Proxy$1(proxy.jdkProxyDemo.AnimalHandler handler){
       this.handler = handler;
   }

 public void sound() {
       try{ 
       Method md = proxy.jdkProxyDemo.Animal.class.getMethod("sound");
       handler.invoke( md,new Object[]{});
       }catch(Exception e){e.printStackTrace();}
   }

}
