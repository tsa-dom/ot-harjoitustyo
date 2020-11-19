/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
/**
 *
 * @author Tapio Salonen
 */
public class Objective {
    protected static int nextId = 0;
    private final String name;
    private final int id;
    private final String requirement;
    private String points;
    public Objective(String givenName, String givenRequirement) {
        this.requirement = givenRequirement;
        this.name = givenName;
        this.points  = "---";
        this.id = nextId;
        Objective.nextId += 1;
    }
    public String getName() {
        return this.name;
    }
    public String getPoints() {
        return this.points;
    }
    public int getId() {
        return this.id;
    }
    public String getRequirement() {
        return this.requirement;
    }
    public void setPoints(String givenPoints) {
        this.points = givenPoints;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
