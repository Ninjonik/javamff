package cz.cuni.mff.zatkope.programs.file_cmp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileCompare {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Program accepts only 2 arguments - file1 and file2 file locations.");
        }

        String file1 = args[0];
        String file2 = args[1];

        boolean success = true;

        try (
                DataInputStream di1 = new DataInputStream(new BufferedInputStream(new FileInputStream(file1)));
                DataInputStream di2 = new DataInputStream(new BufferedInputStream(new FileInputStream(file2)))
        ) {
            int lastRead1;
            int lastRead2;
            while (true) {
                lastRead1 = di1.read();
                lastRead2 = di2.read();
                if (lastRead1 != lastRead2) {
                    success = false;
                    break;
                }
                if (lastRead1 == -1) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(success ? "YES" : "NO");
    }
}
