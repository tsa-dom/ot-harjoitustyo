/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tapio Salonen
 */
public class DataManager {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    public static boolean createTable(String givenContent,String givenDatabase){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase);
            statement = connection.createStatement();
            statement.execute(givenContent);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public static boolean insertInTo(String givenContent,String givenDatabase){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+givenDatabase);
            statement = connection.createStatement();
            statement.execute(givenContent);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
