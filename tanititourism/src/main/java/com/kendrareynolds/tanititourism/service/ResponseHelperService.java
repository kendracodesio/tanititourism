package com.kendrareynolds.tanititourism.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseHelperService {

    public ResponseEntity<Map<String, String>> getBindingErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

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

