package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.ArtApi;
import ch.ubs.m295.generated.v1.dto.Art;
import com.ubs.projekt.m295projekt.dao.TierDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtController implements ArtApi {
    Logger logger = LoggerFactory.getLogger(ArtController.class);
    private final TierDao tierDao;

    public ArtController(TierDao tierDao) {
        this.tierDao = tierDao;
    }

    @Override
    public ResponseEntity<List<Art>> artGet() {
        try{
            logger.info("All species have been listed.");
            return ResponseEntity.ok(tierDao.getAllArt());
        }catch(Exception e){
            logger.error("Failed to get all species.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
