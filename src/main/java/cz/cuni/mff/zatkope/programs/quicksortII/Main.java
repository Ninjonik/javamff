package cz.cuni.mff.zatkope.programs.quicksortII;

import cz.cuni.mff.zatkope.toolkit.SimpleDynamicArray;

public class Main {
    public static void main(String[] args) {
        SimpleDynamicArray<String> arr = new SimpleDynamicArray<>(args.length);
        for (String arg : args) {
            arr.add(arg);
        }
        arr.sort(String::compareTo);
        for (int i = 0; i < args.length; i++) {
            System.out.println(arr.get(i));
        }
    }
}
