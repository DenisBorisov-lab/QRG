package ru.QR;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Solution {

    public static void main(String[] args) throws WriterException, IOException {
        System.out.print("Enter the link: ");
        Scanner sc = new Scanner(System.in);
        String link = sc.nextLine();
        String filePath = "QRcode.jpeg";
        int QRsize = 500;
        String type = "jpeg";
        File mainFile = new File(filePath);
        createQR(mainFile, link, QRsize, type);
        System.out.println("check jpeg file!!!");
    }

    public static void createQR(File qrFile, String link, int QRsize, String type) throws WriterException, IOException {
        //encoding from String to QR
        Map<EncodeHintType, ErrorCorrectionLevel> hint = new HashMap<>();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //QRcodeWrite renders a QR as a 2D array
        QRCodeWriter writer = new QRCodeWriter();
        //BitMatrix creates QR image with parameters weigh, height, rows position(so, create a matrix)
        BitMatrix matrix = writer.encode(link, BarcodeFormat.QR_CODE, QRsize, QRsize, hint);
        //BufferedImage processes and manages image data
        int width = matrix.getWidth();
        BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        // create a Draw image
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, width);
        // Paint and save image with BitMatrix
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, type, qrFile);
    }

}