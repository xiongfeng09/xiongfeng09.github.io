package typeDemo;

public class GenericTest<T> {
    public static void main(String[] args) {
        //下面的代码使用了泛型的数组，是无法通过编译的
//        GenericContainer<String> genArr[] = new GenericContainer<String>[2];
//        Object[] test = genArr;
//
//        GenericContainer<StringBuffer> strBuf = new GenericContainer<StringBuffer>();
//        strBuf.setValue(new StringBuffer());
//        test[0] = strBuf;
//        GenericContainer<String> ref = genArr[0]; //上面两行相当于使用数组移花接木，让Java编译器把GenTest<StringBuffer>当作了GenTest<String>
//        String value = ref.getValue();// 这里是重点！
//
//        List<String> a = new ArrayList<>();
//        Object x = a;
    }
}

class GenericContainer<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
