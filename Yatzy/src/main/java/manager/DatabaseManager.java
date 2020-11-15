/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.*;
import java.util.*;
/**
 *
 * @author Tapio Salonen
 */
public class DatabaseManager {
    private Connection connection;
    
    public boolean executeStatement(String givenContent, String givenDatabase) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Programfiles/" + givenDatabase + ".db");
            connection.createStatement().execute(givenContent);
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean executeStatements(List<String> givenContentList, String givenDatabase) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Programfiles/" + givenDatabase + ".db");
            connection.createStatement().execute("BEGIN TRANSACTION");
            for (int i = 0; i < givenContentList.size(); i++) {
                connection.createStatement().execute(givenContentList.get(i));
            }
            connection.createStatement().execute("COMMIT");
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public List<String> selectFrom(String givenContent, String givenDatabase, String columnLabel) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Programfiles/" + givenDatabase + ".db");
            ResultSet resultSet = connection.createStatement().executeQuery(givenContent);
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
