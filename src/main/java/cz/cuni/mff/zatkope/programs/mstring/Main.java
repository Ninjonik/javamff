package cz.cuni.mff.zatkope.programs.mstring;

import cz.cuni.mff.zatkope.toolkit.MString;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        MString str = new MString();

        int i = 0;
        while (i < args.length) {
            String current = args[i];
            try {
                switch (current) {
                    case "a" -> {
                        String text = args[++i];
                        str.append(text);
                    }
                    case "i" -> {
                        int position = Integer.parseInt(args[++i]);
                        String text = args[++i];
                        str.insert(position, text);
                    }
                    case "d" -> {
                        int position = Integer.parseInt(args[++i]);
                        int count = Integer.parseInt(args[++i]);
                        str.delete(position, count);
                    }
                    default -> throw new InputMismatchException();
                }
                i++;
            } catch (Exception e) {
                System.out.println("INPUT ERROR");
                return;
            }
        }

        System.out.println(str);
    }
}
