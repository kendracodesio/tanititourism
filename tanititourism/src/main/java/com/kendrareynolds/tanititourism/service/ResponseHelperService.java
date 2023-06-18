package com.kendrareynolds.tanititourism.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseHelperService {

    public ResponseEntity<Map<String, String>> getErrorResponse(String message) {
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("error", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
    }

    public ResponseEntity<Map<String, String>> getSuccessResponse(String message) {
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", message);
        return ResponseEntity.ok(responseMessage);
    }
}

