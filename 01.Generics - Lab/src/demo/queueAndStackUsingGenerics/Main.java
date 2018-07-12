package demo.queueAndStackUsingGenerics;

public class Main {
    public static void main(String[] args) {
        Collection2<Integer> queueInteger = new CollectionImplQueue();
        Collection2<String> queueString = new CollectionImplQueue<>();

        Collection2<Integer> stackInteger = new CollectionImplStack<>();
        Collection2<String> stackString = new CollectionImplStack<>();

        queueInteger.add(10);
        queueInteger.add(11);
        queueInteger.add(12);
        System.out.println(queueInteger.remove());
        System.out.println(queueInteger.remove());
        System.out.println(queueInteger.remove());

        stackString.add("one");
        stackString.add("two");
        stackString.add("three");
        System.out.println(stackString.remove());
        System.out.println(stackString.remove());
        System.out.println(stackString.remove());

    }
}
