package com.ubs.projekt.m295projekt.dao;

import ch.ubs.m295.generated.v1.dto.Tier;
//import com.ubs.projekt.m295projekt.dto.TierDB;
//import com.ubs.projekt.m295projekt.optional.TierOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class TierDao {
    /*@Autowired
    private TierOptional tierOptional;*/
    private final NamedParameterJdbcTemplate jdbc;

    public TierDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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
}
