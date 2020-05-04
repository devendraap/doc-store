package com.company.fileStore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public interface FileStore {
    public Boolean save(String key, FileInputStream stream);
    public Boolean update(String key, FileInputStream stream);
    public FileInputStream get(String key);
    public String[] list(String key);
    public Boolean delete(String key);
    public Long getByteSize(String key);
    public Date getCreateDate(String key);
    public Date getUpdateDate(String key);
}
