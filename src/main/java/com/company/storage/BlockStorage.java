package com.company.storage;


import com.company.fileStore.FileStore;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockStorage implements FileStore {
    public Logger logger = Logger.getLogger("BlockStorage.class");

    public Boolean save(String key, FileInputStream stream) {
        try {
            Integer ch = null;
            FileOutputStream outputStream = new FileOutputStream(key);
            do {
                ch = stream.read();
                if (!ch.equals(-1)) {
                    outputStream.write(ch);
                } else {
                    break;
                }
            } while (true);
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return false;
    }

    
    public Boolean update(String key, FileInputStream stream) {
        delete(key);
        return save(key, stream);
    }

    
    public FileInputStream get(String key){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(key);
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return inputStream ;
    }

    
    public String[] list(String key) {
        return new File(key).list();
    }

    
    public Boolean delete(String key){
        return new File(key).delete();
    }

    
    public Long getByteSize(String key) {
        File file = new File(key);
        Long size = 0L;

        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            for (File i : subFiles) size += getByteSize(i.getAbsolutePath());
        } else {
            size += file.length();
        }

        return size;
    }

    
    public Date getCreateDate(String key){
        try {
            BasicFileAttributes attributes = Files.readAttributes(Paths.get(new File(key).toURI()), BasicFileAttributes.class);
            Date date = new Date(attributes.creationTime().toMillis());
            return date;
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return null;
    }

    
    public Date getUpdateDate(String key) {
        try {
            BasicFileAttributes attributes = Files.readAttributes(Paths.get(new File(key).toURI()), BasicFileAttributes.class);
            Date date = new Date(attributes.lastModifiedTime().toMillis());
            return date;
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return null;
    }
}
