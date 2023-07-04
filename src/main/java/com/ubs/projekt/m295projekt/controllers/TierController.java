package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.TiereApi;
import ch.ubs.m295.generated.v1.dto.Tier;
import com.ubs.projekt.m295projekt.dao.TierDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiere")
@CrossOrigin(origins = "*")
public class TierController implements TiereApi {
    Logger logger = LoggerFactory.getLogger(TierController.class);
    private final TierDao tierDao;

    public TierController(TierDao tierDao) {
        this.tierDao = tierDao;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Tier>> tiereGet() {
        logger.info("All animals have been listed.");
        return ResponseEntity.ok(tierDao.getAllTiere());
    }

    @Override
    @GetMapping("/{tierId}")
    public ResponseEntity<Tier> tiereTierIdGet(@PathVariable Integer tierId) {
        logger.info("Got animal with tierId: " + tierId);
        return ResponseEntity.ok(tierDao.getTierById(tierId));
    }



    @Override
    @PostMapping
    public ResponseEntity<Void> tierePost(@RequestBody Tier tierJson) {
        try {
            Tier tier = tierJson;
            logger.info("Added this animal: " + tier);
            tierDao.insertTier(tier);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to parse JSON: " + tierJson, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @DeleteMapping("/{tierId}")
    public ResponseEntity<Void> tiereTierIdDelete(@PathVariable Integer tierId) {
            logger.info("Deleted animal with tierId: " + tierId);
            tierDao.deleteTier(tierId);
            return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/{tierId}")
    public ResponseEntity<Void> tiereTierIdPut(@PathVariable Integer tierId, @RequestBody Tier tier) {
        logger.info("Updated animal with tierId: " + tierId);
        tierDao.updateTier(tierId, tier);
        return ResponseEntity.ok().build();
    }
}
