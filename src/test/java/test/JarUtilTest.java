package test;

import org.junit.Assert;
import org.junit.Test;
import util.JarUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JarUtilTest {

    private final File file = new File("data/jar/jar.jar");

    @Test
    public void searchByNameTest() {

        final List<String> actual = JarUtil.searchByName(file, "il");
        final List<String> expected = Arrays.asList("textFile.txt", "file.txt");

        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(expected, actual);

    }

    @Test
    public void searchByContentTest() {


        final List<String> actual = JarUtil.searchByContent(file, "et");
        //onyx.txt -> bouquet
        //file.txt -> etymology

        final List<String> expected = Arrays.asList("onyx.txt", "file.txt");

        Assert.assertEquals(expected, actual);
        Assert.assertNotSame(expected, actual);

    }
}
