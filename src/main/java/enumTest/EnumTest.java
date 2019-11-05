package enumTest;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EnumTest {
    enum Number {One, Two, Three}

    public static void main(String[] args) {
        Map<Number, String> map = new HashMap<>();
        Map<Number, String> map1 = new EnumMap<>(Number.class);

        map.put(Number.One, "1");
        map.put(Number.Two, "2");


    }
}
