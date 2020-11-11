/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tapio Salonen
 */
public class GameMode {
    private final SimpleStringProperty gameModeName;
    private final SimpleStringProperty shownName;
    private final SimpleStringProperty objectiveCollection;
    public GameMode(String gameName, String collection) {
        this.gameModeName = new SimpleStringProperty(gameName);
        this.shownName = new SimpleStringProperty(gameName.substring(0, 1).toUpperCase() + gameName.substring(1));
        this.objectiveCollection = new SimpleStringProperty(collection);
    }
    public void setName(String name) {
        this.gameModeName.set(name);
    }
    public String getName() {
        return this.gameModeName.get();
    }
    public void setCollection(String collection) {
        this.objectiveCollection.set(collection);
    }
    public String getCollection() {
        return this.objectiveCollection.get();
    }
    public String getShownName() {
        return this.shownName.get();
    }
    public void setShownName(String name) {
        this.shownName.set(name);
    }
    @Override
    public String toString() {
        return this.getShownName();
    }
}
