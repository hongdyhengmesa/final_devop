package net.orderzone.idcard.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.nio.file.*;

public class BarcodeUtil {

    public static String generateBarcode(
            String text,
            String fileName) throws Exception {

        Code128Writer writer =
                new Code128Writer();

        BitMatrix matrix =
                writer.encode(
                        text,
                        BarcodeFormat.CODE_128,
                        400,
                        120);

        Path path =
                Paths.get(
                        "uploads/barcodes/" + fileName);

        Files.createDirectories(
                path.getParent());

        MatrixToImageWriter.writeToPath(
                matrix,
                "PNG",
                path);

        return fileName;
    }
}