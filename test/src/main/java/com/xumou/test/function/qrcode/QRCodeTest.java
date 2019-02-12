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
import java.util.Random;

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
        String contents = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E4%BB%80%E4%B9%88%E4%BA%8B%E6%8A%80%E6%9C%AF&rsv_pq=e04891fb00060bcd&rsv_t=1973cuwQ4T5DxKzrXAFkoXyztwdm6kteMSPNMi0UGCVy1LOVJ0uhIuST4Sc&rqlang=cn&rsv_enter=1&rsv_sug3=24&rsv_sug1=30&rsv_sug7=100&rsv_sug2=0&inputT=12176&rsv_sug4=19251";
        contents = "https://www.cnblogs.com/flashsun/p/9266343.html";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.putIfAbsent(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 480, 480, hints);
        BufferedImage image = new BufferedImage(bitMatrix.getWidth(),bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        int[] as = {0xFF00FFFF,0xFFFF4500,0xFFFFD700,0xFFD02090,0xFFB22222,0xFF483D8B};
        Random r = new Random();
        for (int i = 0; i < bitMatrix.getWidth(); i++) {
            for (int j = 0; j < bitMatrix.getHeight(); j++) {
                boolean is = bitMatrix.get(i, j);
                int color = is ? 0xFF000000 : 0xFFFFFFFF;
                if(false){
                    color = 0xFFFFFFFF;
                    if(is){
                        color = as[r.nextInt(as.length)];
                        color = 0xFF838B8B;
                    }
                }
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