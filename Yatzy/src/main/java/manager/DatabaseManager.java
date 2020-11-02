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
    private Statement statement;
    
    public boolean executeStatement(String givenContent,String givenDatabase){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase+".db");
            statement = connection.createStatement();
            statement.execute(givenContent);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
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
            return null;
        }
    }
}
