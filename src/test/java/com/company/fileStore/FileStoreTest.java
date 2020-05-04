package com.company.fileStore;

import com.company.fileStore.FileStore;
import com.company.fileStore.FileStoreService;
import com.company.storage.BlockStorage;
import org.junit.Assert;
import org.junit.rules.TemporaryFolder;
import org.junit.Rule;
import org.junit.Test;

public class FileStoreTest{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void storageInitialisation() {
        FileStoreService store = new FileStoreService(new BlockStorage());
        Assert.assertTrue(store.getStorage() instanceof BlockStorage);
    }

}
