package com.xumou.test.function.zip;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 */
public class ZipTest {

    // 解压zip文件
    @Test
    public void zipTest() throws IOException {
        ZipFile zipFile = new ZipFile(
                new File("D:\\Downloads\\非材料类变更模板1.zip"),
                Charset.forName("GBK")
        );
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while(entries.hasMoreElements()){
            ZipEntry zipEntry = entries.nextElement();
            System.out.println(zipEntry.getName());
            InputStream inputStream = zipFile.getInputStream(zipEntry);
        }
    }

}