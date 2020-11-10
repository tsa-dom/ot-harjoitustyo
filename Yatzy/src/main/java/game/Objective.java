/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tapio Salonen
 */
public class Objective {
    public static int nextId = 0;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty requirement;
    private final SimpleStringProperty points;
    public Objective(String givenName, String givenRequirement) {
        this.requirement = new SimpleStringProperty(givenRequirement);
        this.name = new SimpleStringProperty(givenName);
        this.points  = new SimpleStringProperty("---");
        this.id = new SimpleIntegerProperty(nextId);
        Objective.nextId += 1;
    }
    public String getName() {
        return name.get();
    }
    public String getPoints() {
        return points.get();
    }
    public int getId() {
        return this.id.get();
    }
    public String getRequirement() {
        return this.requirement.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setPoints(String points) {
        this.points.set(points);
    }
    public SimpleStringProperty nameProperty() {
        return this.name;
    }
    public SimpleStringProperty pointsProperty() {
        return this.points;
    }
    @Override
    public String toString() {
        return this.name.get();
    }
}
