package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static List<String> searchByName(File dir, String pattern) {

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("file has to be a directory");
        }

        final Stream<String> filesInSubDirs = Arrays.stream(dir.listFiles())
                .filter(file -> file.isDirectory())
                .map(subDir -> searchByName(subDir, pattern))
                .flatMap(List::stream);

        final Stream<String> filesInCurrentDir = Arrays.stream(dir.listFiles())
                .filter(file -> file.isFile())
                .filter(file -> file.getName().contains(pattern))
                .map(File::getName);

        return Stream.concat(filesInSubDirs, filesInCurrentDir)
                .collect(Collectors.toList());

    }

    public static List<String> searchByContent(File dir, String pattern) {

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("file has to be a directory");
        }

        final Stream<String> filesInSubDirs = Arrays.stream(dir.listFiles())
                .filter(file -> file.isDirectory())
                .map(subDir -> searchByContent(subDir, pattern))
                .flatMap(List::stream);

        final Stream<String> filesInCurrentDir = Arrays.stream(dir.listFiles())
                .filter(file -> file.isFile())
                .filter(file -> contains(file, pattern))
                .map(File::getName);

        return Stream.concat(filesInSubDirs, filesInCurrentDir)
                .collect(Collectors.toList());
    }

    private static boolean contains(File file, String pattern) {
        try (Scanner scanner = new Scanner(file)) {
            return (scanner.findWithinHorizon(pattern, 0) != null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}

