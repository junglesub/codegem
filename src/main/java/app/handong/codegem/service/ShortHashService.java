package app.handong.codegem.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class ShortHashService {

    public static final int MINLENGTH = 3;

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection
    public ShortHashService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getUniqueShortHash(String fullHash, String tableName, String fieldName) {
        int currentLength = MINLENGTH;

        // Keep extending the length until we have a unique short hash
        while (currentLength <= fullHash.length()) {
            String shortHash = fullHash.substring(0, currentLength);

            // Check if the short hash already exists in the specified table and field
            if (!existsInDatabase(shortHash, tableName, fieldName)) {
                return shortHash; // Return the unique short hash
            }

            currentLength++;
        }

        // If no unique short hash was found, return the full hash as a fallback
        return fullHash;
    }

    private boolean existsInDatabase(String shortHash, String tableName, String fieldName) {
        String sql = String.format("SELECT COUNT(*) AS count FROM %s WHERE %s LIKE ?", tableName, fieldName);

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, shortHash + "%");
        if (rowSet.next()) {
            return rowSet.getInt("count") > 1; // Returns true if the count is greater than 0
        }

        return false; // If no rows were returned, the hash does not exist
    }
}
