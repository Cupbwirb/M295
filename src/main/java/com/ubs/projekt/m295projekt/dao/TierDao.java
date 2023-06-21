package com.ubs.projekt.m295projekt.dao;

import ch.ubs.m295.generated.v1.dto.Art;
import ch.ubs.m295.generated.v1.dto.Tier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TierDao {
    private final NamedParameterJdbcTemplate jdbc;

    public TierDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //From Tier
    public Tier getTierById(int tierId) {
        Tier ti = get(tierId).orElseThrow();
        return ti;
    }
    public int deleteTier(int tierId) {
        return delete(tierId);
    }
    public int updateTier(int tierId, Tier tier) {
        tier.setTierId(tierId);
        return update(tier);
    }
    public List<Tier> getAllTiere() {
        List<Tier> tiere = getAll();
        List<Tier> tierr = new ArrayList<>();
        tiere.forEach(tiers -> {
            Tier t = tiers;
            tierr.add(t);
        });
        return tierr;
    }
    public void insertTier(Tier tier) {
        insert(tier);
    }

    //SQL
    public Optional<Tier> get(int tierId) {
        String sql = "SELECT * FROM Tier WHERE tierId = :tierId";
        List<Tier> tierList = jdbc.query(sql, new MapSqlParameterSource()
                        .addValue("tierId", tierId),
                (ResultSet rs, int rowNum) -> {
                    Tier tier = new Tier();
                    tier.tierId(rs.getInt("tierId"))
                            .tierName(rs.getString("tierName"))
                            .tierAlter(rs.getInt("tierAlter"))
                            .artId(rs.getInt("artId"));
                    return tier;
                });
        if (tierList.isEmpty()) return Optional.empty();
        else return Optional.of(tierList.get(0));
    }

    public int insert(Tier record){
        String sql = "INSERT INTO Tier (tierName, tierAlter, artId) VALUES (:tierName, :tierAlter, :artId)";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("tierName", record.getTierName())
                .addValue("tierAlter", record.getTierAlter())
                .addValue("artId", record.getArtId());
        return jdbc.update(sql, paramSource);
    }

    public int update(Tier record) {
        String sql = "UPDATE Tier SET tierName = :tierName, tierAlter = :tierAlter, artId = :artId WHERE tierId = :tierId";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("tierId", record.getTierId())
                .addValue("tierName", record.getTierName())
                .addValue("tierAlter", record.getTierAlter())
                .addValue("artId", record.getArtId());
        return jdbc.update(sql, paramSource);
    }

    public int delete(int tierId) {
        String sql = "DELETE FROM Tier WHERE tierId = :tierId";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("tierId", tierId);
        return jdbc.update(sql, paramSource);
    }

    public List<Tier> getAll() {
        String sql = "SELECT * FROM Tier";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbc.query(sql, namedParameters, (rs, rowNum) -> {
            Tier tier = new Tier();
            tier.tierId(rs.getInt("tierId"))
                    .tierName(rs.getString("tierName"))
                    .tierAlter(rs.getInt("tierAlter"))
                    .artId(rs.getInt("artId"));
            return tier;
        });
    }

    public List<Art> getAllArt() {
        String sql = "SELECT * FROM tierarten";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbc.query(sql, namedParameters, (rs, rowNum) -> {
            Art art = new Art();
            art.artId(rs.getInt("artId"))
                    .arten(rs.getString("arten"));
            return art;
        });
    }
}
