package demoClasses;

public class Main {
    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();

        myCollection.add("Hello");
        myCollection.add(23);

        MyNewCollection myNewCollection = new MyNewCollection();
        myNewCollection.add("100");
        myNewCollection.remove();
    }
}
