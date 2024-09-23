package com.example.Gestion_Acceso.services.imp;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QrCodeServiceImpTest {
    @Autowired
    private QrCodeServiceImp qrCodeService;

    @Test
    void testGenerateQRCodeImage() throws Exception {
        String textoParaQR = "que onda reik";
        byte[] imagenQR = qrCodeService.generateQRCodeImage(textoParaQR);
        assertNotNull(imagenQR);
        assertTrue(imagenQR.length > 0);
        // Convertimos los bytes de la imagen a un formato que se pueda leer
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagenQR));
        // Preparamos la imagen para que la biblioteca ZXing pueda leerla
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        // Leemos el contenido del QR
        Result resultado = new MultiFormatReader().decode(binaryBitmap);
        // Comprobamos que el contenido del QR es el mismo que le dimos
        assertEquals(textoParaQR, resultado.getText());
    }
}