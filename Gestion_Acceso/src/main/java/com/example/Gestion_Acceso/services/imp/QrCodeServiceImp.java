package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.services.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrCodeServiceImp implements QrCodeService {
    // Estas son las medidas del código QR (300x300 píxeles)
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    @Override
    public byte[] generateQRCodeImage(String text) throws WriterException, IOException {
        // Creamos una "máquina" para escribir códigos QR
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // Le decimos a la máquina que nos haga un código QR con el texto que le damos
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        // Preparamos un "contenedor" especial para guardar nuestra imagen
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Convertimos nuestro código QR en una imagen PNG y la guardamos en el contenedor
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
            // Sacamos la imagen del contenedor como una lista de números (bytes)
            return baos.toByteArray();
        }
    }
}
