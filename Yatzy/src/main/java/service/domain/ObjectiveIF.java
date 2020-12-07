/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

/**
 *
 * @author Tapio Salonen
 */
public interface ObjectiveIF {
    
    String getName();
    
    String getPoints();
    
    int getId();
    
    String getRequirement();
    
    void setPoints(String points);
    
}
