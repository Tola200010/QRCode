package qrcodeapi.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

@Service
public class QRGenerateService {
    public BufferedImage generateQr(String contents, int size, String correction) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            Map<EncodeHintType, ?> hints = switch (correction) {
                case "M" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
                case "Q" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
                case "H" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                default -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            };
            BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size, hints);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
