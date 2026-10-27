package cz.cuni.mff.zatkope.multiplication_table;

public class Main {
    private static String printSpaces(Integer count){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++){
            output.append(" ");
        }

        return output.toString();
    }

    private static int getIntegerLength(Integer input){
        return Integer.toString(input).length();
    }

    public static void main(String[] args) {
        int parameter = Integer.parseInt(args[0]);

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%2d * %d = %s%d%n",
                    i,
                    parameter,
                    printSpaces(
                            getIntegerLength(10 * parameter)
                                    - getIntegerLength(i * parameter)),
                    i * parameter);
        }
    }
}