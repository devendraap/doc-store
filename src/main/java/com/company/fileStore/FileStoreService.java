package com.company.fileStore;

import com.company.storage.BlockStorage;

import java.io.FileInputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileStoreService {
    private Logger logger = Logger.getLogger("FileStore.class");;
    private FileStore store;

    public FileStoreService(FileStore store) {

        if (store != null) {
            logger.log(Level.INFO, "File store initialised");
        } else {
            logger.log(Level.WARNING, "Failed to initialise file store");
        }

        this.store = store;
    }

    public FileStore getStorage() {
        return store;
    }

    public Boolean save(String key, FileInputStream stream) {
        return  this.store.save(key, stream);
    }

    public Boolean update(String key, FileInputStream stream) {
        return update(key, stream);
    }

    public FileInputStream get(String key) {
        return get(key);
    }

    public String[] list(String key) {
        return list(key);
    }

    public Boolean delete(String key){
        return delete(key);
    }

    public Long getByteSize(String key) {
        return getByteSize(key);
    }

    public Date getCreateDate(String key){
        return getCreateDate(key);
    }

    public Date getUpdateDate(String key){
        return getUpdateDate(key);
    }

}
