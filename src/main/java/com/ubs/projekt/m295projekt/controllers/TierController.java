package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.TiereApi;
import ch.ubs.m295.generated.v1.dto.Tier;
import com.ubs.projekt.m295projekt.dao.TierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TierController implements TiereApi {

    private final TierDao tierDao;

    public TierController(TierDao tierDao) {
        this.tierDao = tierDao;
    }

    @Override
    public ResponseEntity<List<Tier>> tiereGet() {
        return ResponseEntity.ok(tierDao.getAllTiere());
    }

    @Override
    public ResponseEntity<Tier> tiereTierIdGet(Integer tierId) {
        return ResponseEntity.ok(tierDao.getTierById(tierId));
    }
    @Override
    public ResponseEntity<Void> tierePost(Tier tier) {
        tierDao.insertTier(tier);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tiereTierIdDelete(Integer tierId) {
        tierDao.deleteTier(tierId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tiereTierIdPut(Integer tierId, Tier tier) {
        tierDao.updateTier(tierId, tier);
        return ResponseEntity.ok().build();
    }
}
