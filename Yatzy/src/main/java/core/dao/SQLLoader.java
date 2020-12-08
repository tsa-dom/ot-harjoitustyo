/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import core.Core;
import core.domain.SQLIF;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SQLLoader implements SQLIF {
    
    @Override
    public List<String> searchStatements(String path) {
        InputStream inputStream = Core.class.getResourceAsStream(path + ".sql");
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
