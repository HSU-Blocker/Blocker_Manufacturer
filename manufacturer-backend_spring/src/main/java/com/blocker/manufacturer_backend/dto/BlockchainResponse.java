package com.blocker.manufacturer_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// dto/BlockchainResponse.java
@Getter
@Setter
@AllArgsConstructor
public class BlockchainResponse {
    private String transactionHash;
    private String text;
    private LocalDateTime timestamp;
}
