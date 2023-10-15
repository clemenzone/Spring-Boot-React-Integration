package com.clement.timesheet.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.Instant;

public class ResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);
    private static final ThreadLocal<String> responseStatus = new ThreadLocal();

    public ResponseUtil() {
    }

    public static <T> ApiResponse<T> baseResponse(T body) {
        return (ApiResponse<T>) ApiResponse.builder().isSuccess(true).timestamp(Instant.now().getEpochSecond()).data(body).build();
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data, URI location) {
        setResponseStatus("201 location: " + location);
        ApiResponse<T> resp = baseResponse(data);
        return ResponseEntity.created(location).body(resp);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        setResponseStatus("200");
        ApiResponse<T> resp = baseResponse(data);
        return ResponseEntity.ok(resp);
    }

    public static String getResponseStatus() {
        return (String)responseStatus.get();
    }

    public static void setResponseStatus(String status) {
        responseStatus.set(status);
    }

    public static void clean() {
        responseStatus.remove();
    }
}
