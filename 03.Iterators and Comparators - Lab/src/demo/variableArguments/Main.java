package demo.variableArguments;

public class Main {
    public static void main(String[] args) {
        //String/Integer/SomethingElse... --> variable arguments = var args
        linkStrings();
    }

    private static void linkStrings(int... variables) {
        for (int i = 0; i < variables.length; i++) {
            System.out.println(variables[i]);
        }
    }

}
