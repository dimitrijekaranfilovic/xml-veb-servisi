package rs.vakcinacija.zajednicko.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class QRCodeService {

    public BufferedImage generateQRCodeImage(String content) throws Exception {
        var barcodeWriter = new QRCodeWriter();
        var bitMatrix = barcodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public String generateQRCodeBase64String(String content) throws Exception {
        var image = generateQRCodeImage(content);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
    }
}
