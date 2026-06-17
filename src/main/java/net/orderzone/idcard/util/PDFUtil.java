package net.orderzone.idcard.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import net.orderzone.idcard.model.Profile;

import java.io.FileOutputStream;

public class PDFUtil {

    public static String generate(Profile profile)
            throws Exception {

        String fileName =
                "idcard_" + profile.getId() + ".pdf";

        Document document =
                new Document();

        PdfWriter.getInstance(
                document,
                new FileOutputStream(
                        "uploads/" + fileName));

        document.open();

        document.add(
                new Paragraph("ID CARD"));

        document.add(
                new Paragraph("Name: "
                        + profile.getFullName()));

        document.add(
                new Paragraph("Registration: "
                        + profile.getRegistrationNumber()));

        document.add(
                new Paragraph("Department: "
                        + profile.getDepartment()));

        document.close();

        return fileName;
    }
}