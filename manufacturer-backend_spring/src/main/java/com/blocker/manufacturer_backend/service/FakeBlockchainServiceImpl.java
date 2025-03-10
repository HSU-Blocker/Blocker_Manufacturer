package com.blocker.manufacturer_backend.service;

import com.blocker.manufacturer_backend.dto.BlockchainRequest;
import com.blocker.manufacturer_backend.dto.BlockchainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Primary
public class FakeBlockchainServiceImpl implements BlockchainService {

    // 임시 저장소 (트랜잭션 해시 - 응답 객체 매핑)
    private final Map<String, BlockchainResponse> tempStorage = new ConcurrentHashMap<>();

    // 최대 저장 가능 항목 수
    private static final int MAX_STORAGE_SIZE = 1000;

    @Override
    public BlockchainResponse uploadToBlockchain(BlockchainRequest request) {
        try {
            // 1. 요청 유효성 검증
            validateRequest(request);

            // 2. 로깅 추가 (요청 수신 로그)
            log.info("블록체인 업로드 요청 수신: {}", request.getText());

            // 3. 가짜 트랜잭션 해시 생성
            String fakeHash = generateFakeHash();

            // 4. 응답 객체 생성
            BlockchainResponse response = createResponse(request, fakeHash);

            // 5. 임시 저장소에 저장 (최대 크기 제한)
            storeResponse(fakeHash, response);

            // 6. 성공 로깅
            log.info("임시 저장 완료 | 해시: {} | 내용: {}", fakeHash, request.getText());
            return response;

        } catch (IllegalArgumentException e) {
            // 유효성 검증 실패 시 예외 처리
            log.error("유효하지 않은 요청: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // 일반 예외 처리
            log.error("블록체인 업로드 실패: {}", e.getMessage());
            throw new RuntimeException("서버 처리 중 오류 발생");
        }
    }

    // 요청 유효성 검증 메서드
    private void validateRequest(BlockchainRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("요청 객체가 null입니다.");
        }
        if (request.getText() == null || request.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("텍스트 내용이 비어있습니다.");
        }
    }

    // 가짜 해시 생성 메서드
    private String generateFakeHash() {
        return "0x" + UUID.randomUUID().toString().replace("-", "");
    }

    // 응답 객체 생성 메서드
    private BlockchainResponse createResponse(BlockchainRequest request, String hash) {
        return new BlockchainResponse(
                hash,
                request.getText(),
                LocalDateTime.now()
        );
    }

    // 저장소 관리 메서드
    private void storeResponse(String hash, BlockchainResponse response) {
        // 저장소 크기 제한
        if (tempStorage.size() >= MAX_STORAGE_SIZE) {
            log.warn("임시 저장소가 가득 찼습니다. 가장 오래된 항목을 제거합니다.");
            String oldestKey = tempStorage.keySet().iterator().next();
            tempStorage.remove(oldestKey);
        }
        tempStorage.put(hash, response);
    }

    // 임시 저장소 조회 메서드 (추가 기능)
    public BlockchainResponse getStoredData(String hash) {
        return tempStorage.get(hash);
    }

    // 저장소 크기 확인 메서드 (추가 기능)
    public int getStorageSize() {
        return tempStorage.size();
    }
}