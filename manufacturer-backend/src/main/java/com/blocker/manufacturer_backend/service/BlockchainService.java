package com.blocker.manufacturer_backend.service;

import com.blocker.manufacturer_backend.dto.BlockchainRequest;
import com.blocker.manufacturer_backend.dto.BlockchainResponse;

// service/BlockchainService.java
public interface BlockchainService {
    BlockchainResponse uploadToBlockchain(BlockchainRequest request);
}
