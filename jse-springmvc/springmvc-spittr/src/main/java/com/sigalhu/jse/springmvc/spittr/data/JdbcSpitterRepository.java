package com.sigalhu.jse.springmvc.spittr.data;

import com.sigalhu.jse.springmvc.spittr.Spitter;
import com.sigalhu.jse.springmvc.spittr.exception.DuplicateSpitterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private static Map<String, Spitter> CACHE = new HashMap<>();

    @Autowired(required = false)
    private JdbcOperations jdbc;

    @Override
    public Spitter save(Spitter spitter) {
        if (jdbc == null) {
            if (CACHE.containsKey(spitter.getUsername())) {
                throw new DuplicateSpitterException();
            }
            CACHE.put(spitter.getUsername(), spitter);
            return spitter;
        }

        jdbc.update(
                "insert into Spitter (username, password, first_name, last_name, email)" +
                        " values (?, ?, ?, ?, ?)",
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getEmail());
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        if (jdbc == null) {
            return CACHE.get(username);
        }

        return jdbc.queryForObject(
                "select id, username, null, first_name, last_name, email from Spitter where username=?",
                new SpitterRowMapper(),
                username);
    }

    private static class SpitterRowMapper implements RowMapper<Spitter> {

        @Override
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    null,
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"));
        }
    }

}
