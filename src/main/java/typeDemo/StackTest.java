package typeDemo;

public class StackTest<T> {
    Iterable<T> t;

    public void push(T t) {

    }

    public T pop(){
       return null;
    }

    public void pushAll(Iterable<? extends T> e) {

    }

    public void popAll(Iterable<? super T> e) {

    }


    public static void main(String[] args) {
        StackTest<Number> s = new StackTest<>();
        Iterable<Integer> i = () -> null;
        s.pushAll(i);

        Iterable<Number> numbers = () -> null;
        Iterable<Object> objects = () -> null;


        s.popAll(numbers);
        s.popAll(objects);
    }
}
