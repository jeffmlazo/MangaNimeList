package com.manganimelist.libraries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.manganimelist.configs.DbHandler;

/**
 *
 * @author jeprox
 */
public class SqlUtil {

    Connection conn;
    ResultSet rs = null;
    PreparedStatement preparedStmt = null;

    public SqlUtil() {
        conn = DbHandler.connector();
        if (conn == null) {
            System.out.println("Database connection failed!");
            System.exit(1);
        }
    }

    /**
     * Get random generated string base from the table name and column name.
     * This method also check if there was a duplicate id in the database if it
     * does it will loop through again to generate until it generate a unique
     * id.
     *
     * @param tableName the table name in the database
     * @param columnName the column name in the database
     * @return random generated string id
     * @throws SQLException
     */
    public String getRandGenId(String tableName, String columnName) throws SQLException {
        String genId = "";
        try {
            boolean isGenId = true;
            do {
                genId = RandChar.getRandGenCode(22);
                String query = "SELECT " + columnName + " FROM " + tableName + " WHERE " + columnName + " = ?";
                preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                preparedStmt.setString(1, genId);
                rs = preparedStmt.executeQuery();

                // Check if there was no match id in the database if true set the isGenId to false
                if (!rs.next()) {
                    isGenId = false;
                }
            } while (isGenId);
        } catch (Exception e) {
            /**
             * TODO: Need to improve error handling here because the generated
             * id was still stored even though table or column name are not
             * existing in the database.
             */
            System.err.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStmt != null) {
                preparedStmt.close();
            }
        }
        return genId;
    }

}
