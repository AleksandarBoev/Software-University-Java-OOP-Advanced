package demo.variableArguments;

public class Main {
    public static void main(String[] args) {
        //String/Integer/SomethingElse... --> variable arguments = varargs, can be 0 or more.
        linkStrings();
        int[] someNumbers = new int[]{1, 2, 3};
        linkStrings(someNumbers);
        System.out.println();
        linkStrings(10, 20, 30, 40, 99);
    }

    private static void linkStrings(int... variables) {
        for (int i = 0; i < variables.length; i++) {
            System.out.println(variables[i]);
        }
    }

}
