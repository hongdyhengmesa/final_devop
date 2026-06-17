package net.orderzone.idcard.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class QRCodeUtil {

    public static String generateQRCode(
            String text,
            String fileName) throws Exception {

        QRCodeWriter writer =
                new QRCodeWriter();

        BitMatrix matrix =
                writer.encode(
                        text,
                        BarcodeFormat.QR_CODE,
                        250,
                        250);

        Path path =
                Paths.get(
                        "uploads/qr/" + fileName);

        MatrixToImageWriter
                .writeToPath(
                        matrix,
                        "PNG",
                        path);

        return fileName;
    }
}