package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {


    public static List<String> searchByName(File file, String pattern) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Should be a file");
        }
        try (ZipFile zipFile = new ZipFile(file)) {

            return zipFile.stream()
                    .filter(zipEntry -> zipEntry.getName().contains(pattern))
                    .map(ZipEntry::getName)
                    .map(s -> s.substring(s.lastIndexOf('/') + 1))
                    .collect(Collectors.toList());

//
//            Enumeration<? extends ZipEntry> e = zipFile.entries();
//
//            List<ZipEntry> zipEntryList = new ArrayList<>();
//            while (e.hasMoreElements()) {
//                final ZipEntry zipEntry = e.nextElement();
//                if (zipEntry.getName().contains(pattern))
//                    zipEntryList.add(zipEntry);
//            }
//
//            return zipEntryList.stream()
//                    .map(ZipEntry::getName)
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
        try (ZipFile zipFile = new ZipFile(file)) {

            return zipFile.stream()
                    .filter(entry -> contains(entry, zipFile, content))
                    .map(ZipEntry::getName)
                    .map(s -> s.substring(s.lastIndexOf('/') + 1))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean contains(ZipEntry zipEntry, ZipFile zipFile, String content) {
        try {
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            Scanner scanner = new Scanner(inputStream);
            final String withinHorizon = scanner.findWithinHorizon(content, (int) zipEntry.getSize());
            scanner.close();
            return withinHorizon != null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

