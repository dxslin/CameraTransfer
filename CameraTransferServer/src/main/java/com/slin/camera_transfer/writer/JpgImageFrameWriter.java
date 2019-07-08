package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;
import com.slin.camera_transfer.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by slin on 2019-06-29.
 */
public class JpgImageFrameWriter extends ImageFrameWriter {

    @Override
    protected void writeImageFrame(ImageFrame frame) throws IOException {
        File file = createNewFile();
        writeAsJpg(frame, file);
        LogUtils.i("write success: " + file.getName());
    }

    private File createNewFile() {
        File file = new File("img/", "pic_" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        return file;
    }

    //图片写为jpg图片
    private void writeAsJpg(ImageFrame frame, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(frame.getImage().array());
        outputStream.flush();
        outputStream.close();
    }

}
