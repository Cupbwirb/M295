package com.ubs.projekt.m295projekt.controllers;

import ch.ubs.m295.generated.v1.controller.TiereApi;
import ch.ubs.m295.generated.v1.dto.Tier;
import com.ubs.projekt.m295projekt.dao.TierheimDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public abstract class TiereController {/*implements TiereApi {
    @Autowired
    private TierheimDB tierheimDB;

    @Override
    public ResponseEntity<List<Tier>> tiereGet() {
        return ResponseEntity.ok(tierheimDB.getAllTiere());
    }

    @Override
    public ResponseEntity<Void> tierePost(Tier tier) {
        tierheimDB.insertTier(tier);
        return ResponseEntity.ok().build();
    }
*/

}
