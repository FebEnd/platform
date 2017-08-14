package com.platform.parent.easemob;

import com.platform.parent.easemob.api.impl.EasemobFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by tqyao.
 */
public class FileTest {
    private EasemobFile easemobFile = new EasemobFile();

    @Test
    public void uploadFile() {
//        String path = "test.jpg";
        String path = EasemobFile.class.getClassLoader().getResource("test.jpg").getPath().substring(1);
//        EasemobFile.class.getResource("test.jpg").getPath();
//        if (path == null) {
//            System.out.println("null");
//        }
        System.out.println(EasemobFile.class.getClassLoader().getResource("test.jpg").getPath().substring(1));
        File file = new File(path);
//        easemobFile = new EasemobFile();
        Object result = easemobFile.uploadFile(file);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void downloadFile() {
        String uuid = "b660a5c0-7f83-11e7-9257-b1c599d7cc0c";
        String shareSecret = "tmClyn-DEeeNFOPBqLe6zrx5ZZwWh-bpkmPtyMVBKAlI-vP7";
        Boolean thumbnail = false;
        File result = (File) easemobFile.downloadFile(uuid, shareSecret, thumbnail);
        System.out.println(result);
    }
}
