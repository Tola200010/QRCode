package qrcodeapi.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qrcodeapi.dto.ErrorResponseDto;
import qrcodeapi.services.QRGenerateService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QrCodeController {
    private final QRGenerateService qrGenerateService;
    List<String> acceptImageExtension = List.of("png", "jpeg", "gif");
    final List<String> acceptCorrection = List.of("L", "M", "Q", "H");

    public QrCodeController(QRGenerateService qrGenerateService) {
        this.qrGenerateService = qrGenerateService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/qrcode")
    public ResponseEntity<?> qrcode(
            @RequestParam(name = "size", defaultValue = "250") int size,
            @RequestParam(name = "type", defaultValue = "png") String type,
            @RequestParam(name = "correction", defaultValue = "L") String correction,
            @RequestParam(name = "contents", required = false) String contents
    ) {
        if (contents == null || contents.trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseDto("Contents cannot be null or blank"));
        }
        if (size < 150 || size > 350) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseDto("Image size must be between 150 and 350 pixels"));
        }
        if (!acceptCorrection.contains(correction)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseDto("Permitted error correction levels are L, M, Q, H"));
        }
        if (!acceptImageExtension.contains(type)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseDto("Only png, jpeg and gif image types are supported"));
        }
        BufferedImage image = qrGenerateService.generateQr(contents, size,correction);
        try (var bass = new ByteArrayOutputStream()) {
            ImageIO.write(image, type, bass);
            byte[] bytes = bass.toByteArray();
            MediaType imagePng = switch (type) {
                case "jpeg" -> MediaType.IMAGE_JPEG;
                case "gif" -> MediaType.IMAGE_GIF;
                default -> MediaType.IMAGE_PNG;
            };
            return ResponseEntity.ok().contentType(imagePng)
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
