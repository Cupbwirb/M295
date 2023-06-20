package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.TiereApi;
import ch.ubs.m295.generated.v1.dto.Tier;
import com.ubs.projekt.m295projekt.dao.TierDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TierController implements TiereApi {
    Logger logger = LoggerFactory.getLogger(TierController.class);
    private final TierDao tierDao;

    public TierController(TierDao tierDao) {
        this.tierDao = tierDao;
    }

    @Override
    public ResponseEntity<List<Tier>> tiereGet() {
        logger.info("All animals have been listed.");
        return ResponseEntity.ok(tierDao.getAllTiere());
    }

    @Override
    public ResponseEntity<Tier> tiereTierIdGet(Integer tierId) {
        logger.info("Got animal with tierId: " + tierId);
        return ResponseEntity.ok(tierDao.getTierById(tierId));
    }
    @Override
    public ResponseEntity<Void> tierePost(Tier tier) {
        logger.info("Added this animal: " + tier);
        tierDao.insertTier(tier);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tiereTierIdDelete(Integer tierId) {
        logger.info("Deleted animal with tierId: " + tierId);
        tierDao.deleteTier(tierId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tiereTierIdPut(Integer tierId, Tier tier) {
        logger.info("Updated animal with tierId: " + tierId);
        tierDao.updateTier(tierId, tier);
        return ResponseEntity.ok().build();
    }
}
