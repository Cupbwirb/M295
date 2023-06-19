package com.ubs.projekt.m295projekt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractController {
    protected static <T> ResponseEntity<T> getRespond(T result) {
        return ResponseEntity.ok(result);
    }

    protected static <T> ResponseEntity<T>  postRespond(){
        return ResponseEntity.ok().build();
    }
}