package p01_jarOfT;

public class Main {
    public static void main(String[] args) {
        Jar<Pickle> pickleJar = new JarImpl<>();
        pickleJar.add(new Pickle(10));
        pickleJar.add(new Pickle(12));
        pickleJar.add(new Pickle(14));
        System.out.println(pickleJar.remove());
        System.out.println(pickleJar.remove());
        System.out.println(pickleJar.remove());

        Jar<Integer> integerJar = new JarImpl<>();
        integerJar.add(1);
        integerJar.add(2);
        integerJar.add(3);
        System.out.println(integerJar.remove());
        System.out.println(integerJar.remove());
        System.out.println(integerJar.remove());
    }
}
