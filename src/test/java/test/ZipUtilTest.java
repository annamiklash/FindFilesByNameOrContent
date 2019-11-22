package test;

import org.junit.Assert;
import org.junit.Test;
import util.ZipUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ZipUtilTest {

    private final File file = new File("data/zip/folder.zip");

    @Test
    public void searchByNameTest() {

        final List<String> actual = ZipUtil.searchByName(file, "al");
        final List<String> expected = Arrays.asList("alaska.txt", "alabama.txt");

        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(expected, actual);

    }

    @Test
    public void searchByContentTest() {

        final List<String> actual = ZipUtil.searchByContent(file, "el");
        final List<String> expected = Arrays.asList("alaska.txt", "alabama.txt", "textfile.txt");

        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(expected, actual);

    }
}
