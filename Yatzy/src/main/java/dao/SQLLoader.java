/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import core.Core;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;

/**
 *
 * @author Tapio Salonen
 */
public class SQLLoader {
    
    public List<String> searchStatements(String givenPath) {
        InputStream inputStream = Core.class.getResourceAsStream(givenPath + ".sql");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String statement = null;
        List<String> statementList = new ArrayList<>();
        try {
            while ((statement = reader.readLine()) != null) {
                statementList.add(statement);
            }
        } catch (IOException ex) {
            return null;
        }
        return statementList;
    }
}
