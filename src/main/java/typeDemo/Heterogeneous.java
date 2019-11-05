package typeDemo;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;

public class Heterogeneous {
    HashMap<Class<?>, Object> hashMap = new HashMap<>();

    <T> void put(Class<T> clazz, T instance)  {
        if (instance == null)
            throw new NullPointerException("instance is empty");

//        hashMap.put(clazz, clazz.cast(instance));
        hashMap.put(clazz, instance);
    }

    <T> T get(Class<T> clazz) {
        return clazz.cast(hashMap.get(clazz));
    }

    public static void main(String[] args) {
        Heterogeneous heterogeneous = new Heterogeneous();

        List<Integer> l = Lists.newArrayList(1);
        heterogeneous.put(List.class, l);
    }
}
