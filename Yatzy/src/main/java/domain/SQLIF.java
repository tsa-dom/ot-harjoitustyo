/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Tapio Salonen
 */
public interface SQLIF {
    
    List<String> searchStatements(String givenPath);
    
}
