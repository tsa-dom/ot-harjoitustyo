/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.DataInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tapio Salonen
 */
public class DatabaseAccess implements DataInterface{
    private Connection connection;
    
    @Override
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
    @Override
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
    @Override
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
