/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tapio Salonen
 */
public class DatabaseManager {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    public boolean createTestConnection(String givenDatabase){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase+".db");
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean createTable(String givenContent,String givenDatabase){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase+".db");
            statement = connection.createStatement();
            statement.execute(givenContent);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    public boolean insertInTo(String givenContent,String givenDatabase){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase+".db");
            statement = connection.createStatement();
            statement.execute(givenContent);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    public List<String> selectFrom(String givenContent, String givenDatabase, String columnLabel){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase+".db");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(givenContent);
            List<String> columnList = new ArrayList<>();
            while(resultSet.next()){
                columnList.add(resultSet.getString(columnLabel));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return columnList;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
