package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;
import com.slin.camera_transfer.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by slin on 2019-06-29.
 */
public class JpgWriter extends Writer {

    private File file;

    public JpgWriter(ImageFrame imageFrame) {
        super(imageFrame);
        file = new File("img/", "pic_" + System.currentTimeMillis() + ".jpg");
    }

    @Override
    protected void write() throws IOException {
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        writeAsJpg();
        LogUtils.i("write success: " + file.getName());
    }

    //图片写为jpg图片
    private void writeAsJpg() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(getImageFrame().getImage().array());
        outputStream.flush();
        outputStream.close();
    }

}
