/*package com.ubs.projekt.m295projekt.optional;

import com.ubs.projekt.m295projekt.dto.TierDB;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TierOptional implements ResultSetExtractor<Optional<TierDB>> {
    @Override
    public Optional<TierDB> extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()) {
            TierDB tier = new TierDB();
            tier.setTierId(rs.getInt("tierId"));
            tier.setTierName(rs.getString("tierName"));
            tier.setTierAlter(rs.getInt("tierAlter"));
            tier.setArtId(rs.getInt("artId"));
            return Optional.of(tier);
        }else{
            return Optional.empty();
        }
    }
}*/
