package com.slin.camera_transfer.writer;

import com.slin.camera_transfer.bean.ImageFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * author: slin
 * date: 2019-07-05
 * description:
 */
public class BmpImageFrameWriter extends ImageFrameWriter {


    public BmpImageFrameWriter(Executor executor) {
        super(executor);
    }

    @Override
    protected void writeImageFrame(ImageFrame frame) throws Exception {
        writeAsBmp(frame);
    }

    //图片写为bmp图片
    private void writeAsBmp(ImageFrame frame) throws IOException {
        File file = createNewFile();
        ByteArrayInputStream is = new ByteArrayInputStream(frame.getImage().array());
        BufferedImage image = ImageIO.read(is);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);
        ImageIO.write(image, "bmp", file);
    }


    private File createNewFile() {
        return new File("img/", "pic_" + System.currentTimeMillis() + ".bmp");
    }

}
