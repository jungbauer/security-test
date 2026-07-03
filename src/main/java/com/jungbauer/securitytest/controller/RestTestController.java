package com.jungbauer.securitytest.controller;

import com.jungbauer.securitytest.model.dto.RestResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/rest")
public class RestTestController {

    @GetMapping("/open")
    public ResponseEntity<RestResponseDTO> open() {
        return ResponseEntity.ok(new RestResponseDTO("this is from open REST", 16));
    }

    @GetMapping("/notopen")
    public ResponseEntity<RestResponseDTO> notOpen() {
        return ResponseEntity.ok(new RestResponseDTO("this is from NOT open REST", 37));
    }
}
