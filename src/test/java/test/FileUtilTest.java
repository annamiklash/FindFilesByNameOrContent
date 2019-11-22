package test;

import org.junit.Assert;
import org.junit.Test;
import util.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtilTest {

    private final File file = new File("data/catalog");

    @Test
    public void searchByNameTest() {
        final List<String> actual = FileUtil.searchByName(file, "a");
        final List<String> expected = Arrays.asList("ahaha.txt", "alala.txt", "ania.txt", "maks.txt", "misha.txt");

        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(expected, actual);

    }

    @Test
    public void searchByContentTest() {
        final List<String> actual = FileUtil.searchByContent(file, "al");

        final List<String> expected = Arrays.asList("alala.txt", "maks.txt", "misha.txt");

        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(expected, actual);

    }


}
