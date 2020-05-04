package com.company.storage;

import org.junit.Assert;
import org.junit.rules.TemporaryFolder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Date;

public class BlockStorageTest{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void saveOperation() {
        try {
            File tempFile = folder.newFile("tempFile.txt");
            BlockStorage storage = new BlockStorage();
            String key = folder.getRoot().getAbsolutePath() + "/tempFile1.txt";
            storage.save(key, new FileInputStream(tempFile.getAbsolutePath()));
            Assert.assertEquals(tempFile.length(), new File(key).length());
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void deleteOperation() {
        try {
            File tempFile = folder.newFile("tempFile.txt");
            BlockStorage storage = new BlockStorage();
            storage.delete(tempFile.getAbsolutePath());
            Assert.assertFalse(tempFile.exists());
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void getByteSizeOperation() {
        try {
            File tempFile = folder.newFile("tempFile.txt");
            BlockStorage storage = new BlockStorage();
            Assert.assertEquals(storage.getByteSize(tempFile.getAbsolutePath()), (Long) 0L);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void getOperation() {
        try {
            File tempFile = folder.newFile("tempFile.txt");
            BlockStorage storage = new BlockStorage();

            int ch = storage.get(tempFile.getAbsolutePath()).read();
            long c = 0;
            while (ch != -1) {
                c += 1;
                ch = storage.get(tempFile.getAbsolutePath()).read();
            }

            Assert.assertEquals(c, tempFile.length());
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void listOperation() {
        try {
            File tempFile = folder.newFile("tempFile.txt");
            BlockStorage storage = new BlockStorage();
            String[] expectList = new String[]{"tempFile.txt"};

            String[] list = storage.list(folder.getRoot().getAbsolutePath());
            Assert.assertEquals(expectList.length, list.length);

            Boolean listMatches = (expectList.length == list.length);
            if (listMatches) {
                for (Integer i = 0; i < expectList.length; i++)
                    listMatches &= list[i].equals(expectList[i]);
            }
            Assert.assertTrue(listMatches);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void updateOperation() {
        try {
            File tempFile = folder.newFile("tempFile.txt");
            BlockStorage storage = new BlockStorage();
            String key = folder.getRoot().getAbsolutePath() + "/tempFile1.txt";
            storage.update(key, new FileInputStream(tempFile.getAbsolutePath()));
            Assert.assertEquals(tempFile.length(), new File(key).length());
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
