package com.example.Gestion_Acceso.controllers;

import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.models.Visitors;
import com.example.Gestion_Acceso.services.VisitorQRService;
import com.example.Gestion_Acceso.services.QrCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitor-qr")
public class VisitorQRController {

    @Autowired
    private VisitorQRService visitorQRService;

    @Autowired
    private QrCodeService qrCodeService;

    @Operation(summary = "Generate QR code for a visitor")
    @ApiResponse(responseCode = "200", description = "QR code generated successfully",
            content = @Content(schema = @Schema(implementation = QRCode_Entity.class)))
    @PostMapping("/generate")
    public ResponseEntity<?> generateQRForVisitor(@RequestBody Visitors visitor) {
        try {
            QRCode_Entity qrCode = visitorQRService.generateAndSaveQRForVisitor(visitor);
            return ResponseEntity.ok(qrCode);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error generating QR for visitor: " + e.getMessage());
        }
    }

    @Operation(summary = "Validate a QR code")
    @ApiResponse(responseCode = "200", description = "QR code validation result",
            content = @Content(schema = @Schema(implementation = Boolean.class)))
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateQRCode(@RequestBody String qrCodeData) {
        try {
            boolean isValid = visitorQRService.validateQRCode(qrCodeData);
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @Operation(summary = "Get QR code image")
    @ApiResponse(responseCode = "200", description = "QR code image",
            content = @Content(mediaType = "image/png"))
    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeImage(
            @Parameter(description = "ID of the QR code") @PathVariable Long id) {
        try {
            QRCode_Entity qrCode = visitorQRService.getQRCodeById(id);
            if (qrCode == null) {
                return ResponseEntity.notFound().build();
            }
            byte[] qrCodeImage = qrCodeService.generateQRCodeImage(qrCode.getQrCodeData());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}