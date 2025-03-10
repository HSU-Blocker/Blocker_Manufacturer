package com.blocker.manufacturer_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import com.blocker.manufacturer_backend.dto.BlockchainRequest;
import com.blocker.manufacturer_backend.dto.BlockchainResponse;
import com.blocker.manufacturer_backend.service.BlockchainService;

// controller/BlockchainController.java
@RestController
@RequestMapping("/api/blockchain")
@RequiredArgsConstructor
public class BlockchainController {

    private final BlockchainService blockchainService;

    @PostMapping("/upload")
    public ResponseEntity<BlockchainResponse> uploadText(
            @Valid @RequestBody BlockchainRequest request
    ) {
        return ResponseEntity.ok(blockchainService.uploadToBlockchain(request));
    }

    @GetMapping("/transaction/{hash}")
    public ResponseEntity<BlockchainResponse> getTransaction(
            @PathVariable String hash
    ) {
        // 실제 구현시 DB 조회 로직 추가
        return ResponseEntity.ok(new BlockchainResponse(hash, "Sample Text", LocalDateTime.now()));
    }
}
