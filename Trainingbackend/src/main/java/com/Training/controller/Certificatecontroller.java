package com.Training.controller;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.Trainingbackend.entity.Enrollment;
import com.Trainingbackend.repository.EnrollmentRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = "http://localhost:5173")
public class Certificatecontroller {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @GetMapping("/download/{enrollmentId}")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long enrollmentId) {

        Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (enrollment.getProgress() < 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            String certificateId = "GSA-" + UUID.randomUUID().toString()
                    .substring(0, 8)
                    .toUpperCase();

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate(), 35, 35, 30, 30);
            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();

            PdfContentByte canvas = writer.getDirectContent();
            Rectangle page = document.getPageSize();

            canvas.setColorFill(new Color(245, 250, 255));
            canvas.rectangle(0, 0, page.getWidth(), page.getHeight());
            canvas.fill();

            canvas.setColorStroke(new Color(21, 86, 150));
            canvas.setLineWidth(6);
            canvas.rectangle(22, 22, page.getWidth() - 44, page.getHeight() - 44);
            canvas.stroke();

            canvas.setColorStroke(new Color(218, 165, 32));
            canvas.setLineWidth(2);
            canvas.rectangle(38, 38, page.getWidth() - 76, page.getHeight() - 76);
            canvas.stroke();

            drawCenteredText(canvas, "GLOBAL SKILLS ACADEMY", 26, Font.BOLD, new Color(18, 70, 130), page.getWidth() / 2, 515);
            drawCenteredText(canvas, "Professional IT Training Institute", 13, Font.NORMAL, Color.DARK_GRAY, page.getWidth() / 2, 490);

            drawCenteredText(canvas, "CERTIFICATE OF COMPLETION", 34, Font.BOLD, new Color(8, 38, 85), page.getWidth() / 2, 435);
            drawCenteredText(canvas, "This certificate is proudly presented to", 16, Font.NORMAL, Color.DARK_GRAY, page.getWidth() / 2, 390);

            drawCenteredText(canvas, enrollment.getStudent().getName(), 34, Font.BOLD, new Color(15, 82, 160), page.getWidth() / 2, 340);

            canvas.setColorStroke(new Color(218, 165, 32));
            canvas.setLineWidth(1.5f);
            canvas.moveTo(250, 325);
            canvas.lineTo(page.getWidth() - 250, 325);
            canvas.stroke();

            drawCenteredText(canvas, "for successfully completing the course", 16, Font.NORMAL, Color.DARK_GRAY, page.getWidth() / 2, 290);
            drawCenteredText(canvas, enrollment.getCourse().getTitle(), 28, Font.BOLD, new Color(0, 105, 125), page.getWidth() / 2, 245);

            drawCenteredText(
                    canvas,
                    "Duration: " + enrollment.getCourse().getDuration()
                            + "  |  Progress: " + enrollment.getProgress() + "%"
                            + "  |  Status: " + enrollment.getStatus(),
                    13,
                    Font.NORMAL,
                    Color.DARK_GRAY,
                    page.getWidth() / 2,
                    210
            );

            drawCenteredText(canvas, "Certificate ID: " + certificateId, 11, Font.NORMAL, Color.DARK_GRAY, 170, 95);
            drawCenteredText(canvas, "Issued Date: " + LocalDate.now(), 11, Font.NORMAL, Color.DARK_GRAY, page.getWidth() / 2, 95);
            drawCenteredText(canvas, "Verified By: Global Skills Academy", 11, Font.NORMAL, Color.DARK_GRAY, page.getWidth() - 170, 95);

            canvas.setColorStroke(Color.GRAY);
            canvas.setLineWidth(1);
            canvas.moveTo(95, 150);
            canvas.lineTo(245, 150);
            canvas.stroke();

            drawCenteredText(canvas, "Authorized Signature", 12, Font.BOLD, Color.DARK_GRAY, 170, 130);

            canvas.moveTo(page.getWidth() - 245, 150);
            canvas.lineTo(page.getWidth() - 95, 150);
            canvas.stroke();

            drawCenteredText(canvas, "Academic Director", 12, Font.BOLD, Color.DARK_GRAY, page.getWidth() - 170, 130);

            Image qr = createQrImage(
                    "Certificate ID: " + certificateId
                            + "\nStudent: " + enrollment.getStudent().getName()
                            + "\nCourse: " + enrollment.getCourse().getTitle()
                            + "\nIssued: " + LocalDate.now()
            );

            qr.setAbsolutePosition(page.getWidth() - 135, 395);
            qr.scaleAbsolute(82, 82);
            document.add(qr);

            drawCenteredText(canvas, "Scan to Verify", 9, Font.NORMAL, Color.DARK_GRAY, page.getWidth() - 94, 383);

            canvas.setColorFill(new Color(21, 86, 150));
            canvas.circle(95, 455, 38);
            canvas.fill();

            drawCenteredText(canvas, "GSA", 20, Font.BOLD, Color.WHITE, 95, 448);

            document.close();

            byte[] pdfBytes = out.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename("certificate_" + enrollment.getStudent().getName() + ".pdf")
                            .build()
            );

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void drawCenteredText(
            PdfContentByte canvas,
            String text,
            int size,
            int style,
            Color color,
            float x,
            float y
    ) {
        try {
            BaseFont baseFont = BaseFont.createFont(
                    BaseFont.HELVETICA,
                    BaseFont.WINANSI,
                    BaseFont.NOT_EMBEDDED
            );

            canvas.beginText();
            canvas.setFontAndSize(baseFont, size);
            canvas.setColorFill(color);
            canvas.showTextAligned(Element.ALIGN_CENTER, text, x, y, 0);
            canvas.endText();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image createQrImage(String text) throws Exception {
        int size = 160;

        BitMatrix matrix = new MultiFormatWriter()
                .encode(text, BarcodeFormat.QR_CODE, size, size);

        BufferedImage qrImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                qrImage.setRGB(
                        x,
                        y,
                        matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
                );
            }
        }

        ByteArrayOutputStream pngOut = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", pngOut);

        return Image.getInstance(pngOut.toByteArray());
    }
}