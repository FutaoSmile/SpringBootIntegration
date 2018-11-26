package com.futao.springmvcdemo.utils;

import com.futao.springmvcdemo.model.system.Constant;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author futao
 * Created on 2018/11/15.
 */
public class ZxingUtils {
    private int width = 300;
    private int hight = 300;
    private String format = "PNG";
    private String context = "������ְ�";

    public void generat() {

        //�����ά�����
        HashMap map = new HashMap();
        //����
        map.put(EncodeHintType.CHARACTER_SET, Constant.UTF8_ENCODE);
        //����ȼ�
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //margin
        map.put(EncodeHintType.MARGIN, 2);

        try {
            BitMatrix qrCode = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, hight);
//            BitMatrix.

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void analys(String filePath) throws IOException {
        MultiFormatReader reader = new MultiFormatReader();
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
//        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImage()));
//        Result result = reader.decode();

    }


}
