package com.blocker.manufacturer_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// dto/BlockchainRequest.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainRequest {
    @NotBlank(message = "텍스트는 필수 입력 값입니다.")
    private String text;
}