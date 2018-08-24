package com.sigalhu.jse.springmvc.spittr.data;

import com.sigalhu.jse.springmvc.spittr.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcSpittleRepository implements SpittleRepository {

    @Autowired(required = false)
    private JdbcOperations jdbc;

    @Override
    public List<Spittle> findRecentSpittles() {
        if (jdbc == null) {
            return Collections.emptyList();
        }

        return jdbc.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Spittle" +
                        " order by created_at desc limit 20",
                new SpittleRowMapper());
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        if (jdbc == null) {
            return Collections.emptyList();
        }

        return jdbc.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Spittle" +
                        " where id < ?" +
                        " order by created_at desc limit 20",
                new SpittleRowMapper(), max);
    }

    @Override
    public Spittle findOne(long id) {
        if (jdbc == null) {
            return null;
        }

        List<Spittle> spittles = jdbc.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Spittle" +
                        " where id = ?",
                new SpittleRowMapper(), id);
        return spittles.size() > 0 ? spittles.get(0) : null;
    }

    @Override
    public void save(Spittle spittle) {
        if (jdbc == null) {
            return;
        }

        jdbc.update(
                "insert into Spittle (message, created_at, latitude, longitude)" +
                        " values (?, ?, ?, ?)",
                spittle.getMessage(),
                spittle.getTime(),
                spittle.getLatitude(),
                spittle.getLongitude());
    }

    private static class SpittleRowMapper implements RowMapper<Spittle> {

        @Override
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spittle(
                    rs.getLong("id"),
                    rs.getString("message"),
                    rs.getDate("created_at"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude"));
        }
    }

}
