package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.ImageFrame;
import com.slin.camera_transfer.utils.LogUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by slin on 2019-06-29.
 */
public class JpgImageFrameWriter implements ImageFrameWriter, Runnable {

    private ImageFrame imageFrame;
    private File file;

    public JpgImageFrameWriter(ImageFrame imageFrame) {
        this.imageFrame = imageFrame;
        file = new File("img/", "pic_" + System.currentTimeMillis() + ".bmp");
    }

    @Override
    public void run() {
        try {
            write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write() throws IOException {
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        writeAsJpg();
        LogUtils.info("write success: " + file.getName());
    }

    //图片写为bmp图片
    //TODO 新建一个writer写bmp
    private void writeAsBmp() throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(imageFrame.getImage().array());
        BufferedImage image = ImageIO.read(is);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);
        ImageIO.write(image, "bmp", file);
    }

    //图片写为jpg图片
    private void writeAsJpg() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(imageFrame.getImage().array());
        outputStream.flush();
        outputStream.close();
    }

}
