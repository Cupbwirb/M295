package com.ubs.projekt.m295projekt.dao;

import ch.ubs.m295.generated.v1.dto.Tier;
//import com.ubs.projekt.m295projekt.DBConvert;
//import com.ubs.projekt.m295projekt.dto.TierDB;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TierheimDB {

    private final TierDao tierDao;

    public TierheimDB(TierDao tierDao) {
        this.tierDao = tierDao;
    }


    public void insertTier(Tier tier) {
        tierDao.insert(tier);
    }
    public int deleteTier(int tierId) {
        return tierDao.delete(tierId);
    }
    public int updateTier(int tierId, Tier tier) {
        tier.setTierId(tierId);
        return tierDao.update(tier);
    }
    public Tier getTierById(int tierId) {
        Tier ut = tierDao.get(tierId).orElseThrow();
        return ut;
    }

    public List<Tier> getAllTiere() {
        List<Tier> tiere = tierDao.getAll();
        List<Tier> tierr = new ArrayList<>();
        tiere.forEach(tiers -> {
            Tier t = tiers;
            tierr.add(t);
        });
        return tierr;
    }
}
