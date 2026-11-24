package cz.cuni.mff.zatkope.programs.cp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopier {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Program accepts only 2 arguments - source and target file locations.");
        }

        String source = args[0];
        String target = args[1];
        Path sourcePath = Path.of(source);
        Path targetPath = Path.of(target);

        String sourceFileName = String.valueOf(sourcePath.getFileName());
        boolean isTargetDirectory = Files.isDirectory(targetPath);
        targetPath = isTargetDirectory ? Path.of(target + "/" + sourceFileName) : targetPath;

        // via input streams
        try (
                DataInputStream di = new DataInputStream(new BufferedInputStream(new FileInputStream(source)));
                DataOutputStream ds = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(targetPath.toString())))
        ) {
            int lastRead;
            while (true) {
                lastRead = di.read();
                if (lastRead == -1) {
                    break;
                }
                ds.write(lastRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // via Files.copy()
        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
