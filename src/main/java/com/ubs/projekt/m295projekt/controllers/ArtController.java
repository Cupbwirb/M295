package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.ArtApi;
import ch.ubs.m295.generated.v1.dto.Art;
import com.ubs.projekt.m295projekt.dao.TierDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/art")
@CrossOrigin(origins = "*")
public class ArtController implements ArtApi {
    private final TierDao tierDao;

    public ArtController(TierDao tierDao) {
        this.tierDao = tierDao;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Art>> artGet() {
            return ResponseEntity.ok().body(tierDao.getAllArt());
    }
}
