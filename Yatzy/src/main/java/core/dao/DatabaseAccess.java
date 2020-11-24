/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import java.sql.*;
import java.util.*;
import domain.DataIF;

/**
 *
 * @author Tapio Salonen
 */
public class DatabaseAccess implements DataIF {
    private Connection connection;
    @Override
    public boolean executeStatement(String content, String database, String folder) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + folder + database + ".db");
            connection.createStatement().execute(content);
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    @Override
    public boolean executeStatements(List<String> contentList, String database, String folder) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + folder + database + ".db");
            connection.createStatement().execute("BEGIN TRANSACTION");
            for (int i = 0; i < contentList.size(); i++) {
                connection.createStatement().execute(contentList.get(i));
            }
            connection.createStatement().execute("COMMIT");
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    @Override
    public List<String> selectFrom(String content, String database, String columnLabel, String folder) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + folder + database + ".db");
            ResultSet resultSet = connection.createStatement().executeQuery(content);
            List<String> columnList = new ArrayList<>();
            while (resultSet.next()) {
                columnList.add(resultSet.getString(columnLabel));
            }
            resultSet.close();
            connection.close();
            return columnList;
        } catch (SQLException ex) {
            return null;
        }
    }
}
