package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.response.Response;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

public class ResponseController {

    public static <T> ResponseEntity<Response<T>> createResponseEntity(Callable<T> callable, Logger logger, T defaultResponse) {
        try {
            T result = callable.call();
            Response<T> response = new Response<>(result, "Success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            Response<T> response = new Response<>(defaultResponse, e.getMessage(), false);
            return ResponseEntity.status(500).body(response);
        }
    }
}
