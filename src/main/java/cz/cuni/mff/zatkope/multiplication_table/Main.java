package org.example;

public class Main {
    public static void main(String[] args) {
        int parameter = Integer.parseInt(args[0]);

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s%d * %d = %d%n", i < 10 ? " " : "", i, parameter, i * parameter);
        }
    }
}