package cz.cuni.mff.zatkope.programs.simple_dynamic_array;
import cz.cuni.mff.zatkope.toolkit.SimpleDynamicArray;

public class Main {

    public static void main(String[] args){
        SimpleDynamicArray<String> arr = new SimpleDynamicArray<String>(args.length);
        for (String arg : args) {
            arr.add(arg);
        }

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }

}
