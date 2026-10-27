package cz.cuni.mff.zatkope.programs.test;

import cz.cuni.mff.zatkope.toolkit.MString;

public class Main {
    public static void main(String[] args) {
        MString test = new MString("abcdefghij");
        System.out.println(test.toString());

        test.append("klm");
        System.out.println(test.toString());

        test.append('n');
        System.out.println(test.toString());

        test.delete(2, 2);
        System.out.println(test.toString());

        test.insert(2, "cd");
        System.out.println(test.toString());

        test.append("z");
        System.out.println(test.toString());
    }
}
