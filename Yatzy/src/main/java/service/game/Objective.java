/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import service.domain.ObjectiveIF;

public class Objective implements ObjectiveIF {
    private final String name;
    private final int id;
    private final String requirement;
    private String points;
    
    /** Creates new objective.
     * @param name Given objective name.
     * @param requirement Given objective requirement.
     * @param id Given objetive identifier.
     */
    public Objective(String name, String requirement, int id) {
        this.requirement = requirement;
        this.name = name;
        this.points  = "---";
        this.id = id;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getPoints() {
        return this.points;
    }
    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public String getRequirement() {
        return this.requirement;
    }
    @Override
    public void setPoints(String points) {
        this.points = points;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
