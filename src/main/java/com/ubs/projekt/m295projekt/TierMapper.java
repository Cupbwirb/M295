package com.ubs.projekt.m295projekt;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TierMapper implements RowMapper<TierRecord> {

    @Override
    public TierRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        TierRecord tier = new TierRecord(
                rs.getInt("tierId"),
                rs.getString("tierName"),
                rs.getInt("tierAlter"),
                rs.getInt("artId"));
        return tier;
    }
}