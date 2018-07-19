package demo.enums;

public class MyEnumTest {
    public static void main(String[] args) {
        MyEnum myEnum = MyEnum.valueOf("MERCEDES");
        myEnum.setOwner("Sasho");
        myEnum.setPrice(10000.99);
        System.out.println(myEnum.getOwner());
        System.out.println(myEnum.getPrice());
        System.out.println(myEnum.randomMethod());


    }
}
