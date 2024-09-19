package com.example.Gestion_Acceso.services;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface QrCodeService {
    /**
     * Genera una imagen de código QR a partir del texto proporcionado.
     *
     * @param text El texto a codificar en el QR.
     * @return Un array de bytes que representa la imagen del código QR en formato PNG.
     * @throws WriterException Si ocurre un error durante la generación del código QR.
     * @throws IOException Si ocurre un error de E/S durante la escritura de la imagen.
     */
    byte[] generateQRCodeImage(String text) throws WriterException, IOException;
}
