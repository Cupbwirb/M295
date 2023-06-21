package com.ubs.projekt.m295projekt.dao;

import ch.ubs.m295.generated.v1.dto.Art;
import ch.ubs.m295.generated.v1.dto.Tier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TierDaoTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Captor
    private ArgumentCaptor<String> sqlC;
    @Captor
    private ArgumentCaptor<SqlParameterSource> paramSourceC;

    @Test
    void testInsertTier() {
        // Arrange
        TierDao tierDao = new TierDao(this.namedParameterJdbcTemplate);
        Tier tier = new Tier();
        tier.setTierName("TestAnimal");
        tier.setTierAlter(5);
        tier.setArtId(1);

        when(this.namedParameterJdbcTemplate.update(anyString(), any(SqlParameterSource.class)))
                .thenReturn(1);

        ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<SqlParameterSource> paramSourceCaptor = ArgumentCaptor.forClass(SqlParameterSource.class);

        // Act
        int result = tierDao.insert(tier);

        // Assert
        assertEquals(1, result);

        verify(this.namedParameterJdbcTemplate, times(1))
                .update(sqlCaptor.capture(), paramSourceCaptor.capture());

        String capturedSql = sqlCaptor.getValue();
        SqlParameterSource capturedParamSource = paramSourceCaptor.getValue();

        assertEquals("INSERT INTO Tier (tierName, tierAlter, artId) VALUES (:tierName, :tierAlter, :artId)", capturedSql);

        assertEquals("TestAnimal", capturedParamSource.getValue("tierName"));
        assertEquals(5, capturedParamSource.getValue("tierAlter"));
        assertEquals(1, capturedParamSource.getValue("artId"));
    }


    @Test
    void testDeleteTier() {
        // Arrange
        int tierId = 1;
        String sql = "DELETE FROM Tier WHERE tierId = :tierId";

        when(namedParameterJdbcTemplate.update(anyString(), any(SqlParameterSource.class))).thenReturn(1);

        TierDao tierDao = new TierDao(namedParameterJdbcTemplate);

        // Act
        int result = tierDao.deleteTier(tierId);

        // Assert
        assertEquals(1, result);
        verify(namedParameterJdbcTemplate).update(eq(sql), paramSourceC.capture());

        SqlParameterSource capturedParamSource = paramSourceC.getValue();
        assertEquals(tierId, capturedParamSource.getValue("tierId"));
    }

    @Test
    void testUpdateTier() {
        // Arrange
        int tierId = 1;
        TierDao tierDao = new TierDao(namedParameterJdbcTemplate);
        Tier tier = new Tier();
        tier.setTierName("UpdatedAnimal");
        tier.setTierAlter(10);
        tier.setArtId(2);

        when(namedParameterJdbcTemplate.update(anyString(), any(SqlParameterSource.class)))
                .thenReturn(1);

        // Act
        int result = tierDao.updateTier(tierId, tier);

        // Assert
        assertEquals(1, result);

        verify(namedParameterJdbcTemplate, times(1))
                .update(sqlC.capture(), paramSourceC.capture());

        String capturedSql = sqlC.getValue();
        SqlParameterSource capturedParamSource = paramSourceC.getValue();

        assertEquals("UPDATE Tier SET tierName = :tierName, tierAlter = :tierAlter, artId = :artId WHERE tierId = :tierId", capturedSql);

        assertEquals(tierId, capturedParamSource.getValue("tierId"));
        assertEquals("UpdatedAnimal", capturedParamSource.getValue("tierName"));
        assertEquals(10, capturedParamSource.getValue("tierAlter"));
        assertEquals(2, capturedParamSource.getValue("artId"));
    }

    @Test
    void testGetTierByIdExisting() {
        // Arrange
        int tierId = 1;
        TierDao tierDao = new TierDao(namedParameterJdbcTemplate);
        Tier tier = new Tier();
        tier.setTierId(tierId);
        tier.setTierName("TestAnimal");
        tier.setTierAlter(5);
        tier.setArtId(1);

        when(namedParameterJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenReturn(Arrays.asList(tier));

        // Act
        Tier resultTier = tierDao.getTierById(tierId);

        // Assert
        assertEquals(tier, resultTier);

        verify(namedParameterJdbcTemplate, times(1))
                .query(sqlC.capture(), paramSourceC.capture(), any(RowMapper.class));

        String capturedSql = sqlC.getValue();
        MapSqlParameterSource capturedParamSource = (MapSqlParameterSource) paramSourceC.getValue();

        assertEquals("SELECT * FROM Tier WHERE tierId = :tierId", capturedSql);
        assertEquals(tierId, capturedParamSource.getValue("tierId"));
    }

    @Test
    void testGetAllTiere() {
        // Arrange
        TierDao tierDao = new TierDao(namedParameterJdbcTemplate);
        List<Tier> tiers = Arrays.asList(
                createTier(1, "TestAnimal1", 5, 1),
                createTier(2, "TestAnimal2", 3, 2),
                createTier(3, "TestAnimal3", 7, 3)
        );

        when(namedParameterJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenReturn(tiers);

        // Act
        List<Tier> resultTiere = tierDao.getAllTiere();

        // Assert
        assertEquals(tiers, resultTiere);

        verify(namedParameterJdbcTemplate, times(1))
                .query(sqlC.capture(), paramSourceC.capture(), any(RowMapper.class));

        String capturedSql = sqlC.getValue();
        MapSqlParameterSource capturedParamSource = (MapSqlParameterSource) paramSourceC.getValue();

        assertEquals("SELECT * FROM Tier", capturedSql);
        assertEquals(0, capturedParamSource.getValues().size());
    }

    private Tier createTier(int tierId, String tierName, int tierAlter, int artId) {
        Tier tier = new Tier();
        tier.setTierId(tierId);
        tier.setTierName(tierName);
        tier.setTierAlter(tierAlter);
        tier.setArtId(artId);
        return tier;
    }

    @Test
    void testGetAllArt() {
        // Arrange
        TierDao tierDao = new TierDao(namedParameterJdbcTemplate);
        List<Art> expectedArts = Arrays.asList(
                createArt(1, "Art1"),
                createArt(2, "Art2"),
                createArt(3, "Art3")
        );

        when(namedParameterJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenReturn(expectedArts);

        // Act
        List<Art> resultArts = tierDao.getAllArt();

        // Assert
        assertEquals(expectedArts, resultArts);

        verify(namedParameterJdbcTemplate, times(1))
                .query(sqlC.capture(), paramSourceC.capture(), any(RowMapper.class));

        String capturedSql = sqlC.getValue();
        MapSqlParameterSource capturedParamSource = (MapSqlParameterSource) paramSourceC.getValue();

        assertEquals("SELECT * FROM tierarten", capturedSql);
        assertEquals(0, capturedParamSource.getValues().size());
    }

    private Art createArt(int artId, String arten) {
        Art art = new Art();
        art.setArtId(artId);
        art.setArten(arten);
        return art;
    }
}