package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;


public class JarUtil {

    public static List<String> searchByName(File file, String pattern) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Should be a file");
        }
        try (JarFile jarFile = new JarFile(file)) {

            return jarFile.stream()
                    .filter(zipEntry -> zipEntry.getName().contains(pattern))
                    .map(JarEntry::getName)
                    .map(s -> s.substring(s.lastIndexOf('/') + 1))
                    .collect(Collectors.toList());
            
//            final Enumeration<JarEntry> entries = jarFile.entries();
//            List<JarEntry> jarEntryList = new ArrayList<>();
//            while (entries.hasMoreElements()){
//                final JarEntry jarEntry = entries.nextElement();
//                if (jarEntry.getName().contains(pattern))
//                    jarEntryList.add(jarEntry);
//            }
//
//            return jarEntryList.stream()
//                    .map(JarEntry::getName)
//                    .map(s -> s.substring(s.lastIndexOf('/') + 1))
//                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> searchByContent(File file, String content) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Should be a file");
        }
        try (JarFile jarFile = new JarFile(file)) {

            return jarFile.stream()
                    .filter(entry -> contains(entry, jarFile, content))
                    .map(JarEntry::getName)
                    .map(s -> s.substring(s.lastIndexOf('/') + 1))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean contains(JarEntry jarEntry, JarFile jarFile, String content) {
        try {
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            Scanner scanner = new Scanner(inputStream);
            final String withinHorizon = scanner.findWithinHorizon(content, (int) jarEntry.getSize());
            scanner.close();
            return withinHorizon != null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
