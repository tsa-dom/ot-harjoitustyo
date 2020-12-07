/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

import java.util.List;

/**
 *
 * @author Tapio Salonen
 */
public interface DataIF {
    
    boolean executeStatement(String content, String databaseName, String folder);
    
    boolean executeStatements(List<String> contentList, String databaseName, String folder);
    
    List<String> selectFrom(String content, String databaseName, String columnLabel, String folder);
}
