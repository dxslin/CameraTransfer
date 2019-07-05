package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * author: slin
 * date: 2019-07-05
 * description:
 */
public class BmpWriter extends Writer {


    private File file;

    public BmpWriter(ImageFrame frame) {
        super(frame);
        file = new File("img/", "pic_" + System.currentTimeMillis() + ".bmp");
    }

    @Override
    protected void write() throws IOException {
        writeAsBmp();
    }


    //图片写为bmp图片
    private void writeAsBmp() throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(getImageFrame().getImage().array());
        BufferedImage image = ImageIO.read(is);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);
        ImageIO.write(image, "bmp", file);
    }


}
