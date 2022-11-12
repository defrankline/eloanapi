package com.kachinga.eloanapi.util;

import com.kachinga.eloanapi.domain.payload.ApiResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class Util {
    private static final String NUMERIC_STRING = "0123456789";

    private static final int DEF_COUNT = 20;

    private Util() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT).toUpperCase();
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(32).toUpperCase();
    }

    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT).toUpperCase();
    }

    public static String generateTransactionNumber() {
        return RandomStringUtils.randomAlphanumeric(16).toUpperCase();
    }

    public static String generateOrderNumber() {
        return RandomStringUtils.randomAlphanumeric(20).toUpperCase();
    }

    public static String generateDivisionCode() {
        return RandomStringUtils.randomAlphanumeric(5).toUpperCase();
    }

    public static String generateBranchCode() {
        return RandomStringUtils.randomAlphanumeric(5).toUpperCase();
    }

    public static String generateItemCode() {
        return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
    }

    public static String generateContractNumber() {
        return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
    }

    public static String randomNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static ResponseEntity<? extends ApiResponse<?>> respond(Object data, String message, HttpStatus httpStatus) {
        ApiResponse<?> response = new ApiResponse<>(message, data);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<? extends ApiResponse<?>> respond(Object data) {
        ApiResponse<?> response = new ApiResponse<>("Data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<? extends ApiResponse<?>> respond(Object data, String message) {
        ApiResponse<?> response = new ApiResponse<>(message, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<? extends ApiResponse<?>> respond(Object data, HttpStatus httpStatus) {
        ApiResponse<?> response = new ApiResponse<>("Data", data);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<? extends ApiResponse<?>> respond(String message, HttpStatus httpStatus) {
        ApiResponse<?> response = new ApiResponse<>(message);
        return new ResponseEntity<>(response, httpStatus);
    }

}
