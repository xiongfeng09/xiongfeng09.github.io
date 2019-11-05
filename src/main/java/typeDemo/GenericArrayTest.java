package typeDemo;

import java.util.HashMap;
import java.util.Map;

public class GenericArrayTest {
    public static void main(String[] args) {
        /**
         * 数组有两个特性
         * 1.协变
         * 2.运行时检测类型
         */


        String[] strArray = new String[2];
        Object[] objArray = strArray;
        objArray[0] = "fasd";
        objArray[1] = 12312;
        for(String s: strArray)
            System.out.println(s);

        Map[] m = new HashMap[2];

        Map m1 = new HashMap();
        m1.put(1,2);
    }
}
