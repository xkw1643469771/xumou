package com.xumou.test.function.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class QRCodeTest {

    /**
     * 生成二维码图片
     */
    @Test
    public void generatorImage() throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String contents = "小豪被卖到越南了";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.putIfAbsent(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 100, 100, hints);
        BufferedImage image = new BufferedImage(bitMatrix.getWidth(),bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < bitMatrix.getWidth(); i++) {
            for (int j = 0; j < bitMatrix.getHeight(); j++) {
                boolean is = bitMatrix.get(i, j);
                int color = is ? 0xFF000000 : 0xFFFFFFFF;
                image.setRGB(i,j, color);
            }
        }
        ImageIO.write(image, "png", new File("D:/xumou/ab.png"));
        readImage();
    }

    /**
     * 解析生成的二维码图片
     */
    @Test
    public void readImage() throws Exception {
        BufferedImage image = ImageIO.read(new File("D:/xumou/ab.png"));
        BinaryBitmap img = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        QRCodeReader qrCodeReader = new QRCodeReader();
        Result decode = qrCodeReader.decode(img, hints);
        System.out.println(decode);
    }

}