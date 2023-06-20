package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.TiereApi;
import ch.ubs.m295.generated.v1.dto.Tier;
import com.ubs.projekt.m295projekt.dao.TierheimDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TierController implements TiereApi {

    private final TierheimDB tierheimDB;

    public TierController(TierheimDB tierheimDB) {
        this.tierheimDB = tierheimDB;
    }

    @Override
    public ResponseEntity<List<Tier>> tiereGet() {
        return TiereApi.super.tiereGet();
    }

    @Override
    public ResponseEntity<Void> tierePost(Tier tier) {
        return TiereApi.super.tierePost(tier);
    }

    @Override
    public ResponseEntity<Void> tiereTierIdDelete(Integer tierId) {
        return TiereApi.super.tiereTierIdDelete(tierId);
    }

    @Override
    public ResponseEntity<Tier> tiereTierIdGet(Integer tierId) {
        return ResponseEntity.ok(tierheimDB.getTierById(tierId));
    }

    @Override
    public ResponseEntity<Void> tiereTierIdPut(Integer tierId, Tier tier) {
        return TiereApi.super.tiereTierIdPut(tierId, tier);
    }

    /*@Override
    public ResponseEntity<Tier> tierIdGet(@PathVariable Integer tierId) {
        return ResponseEntity.ok(tierheimDB.getTierById(tierId));
    }

    @Override
    public ResponseEntity<Void> tierIdPost(Tier tier) {
        tierheimDB.insertTier(tier);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tierIdDelete(@PathVariable int tierId) {
        tierheimDB.deleteTier(tierId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> tierIdPut(@PathVariable int tierId, @RequestBody Tier tier) {
        tierheimDB.updateTier(tierId, tier);
        return ResponseEntity.ok().build();
    }*/
}
