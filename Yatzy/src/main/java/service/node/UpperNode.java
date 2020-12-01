/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;

/**
 *
 * @author Tapio Salonen
 */
public class UpperNode {
    public void clearUpperCount() {
        Core.clearUpperCount();
    }
    public void addUpperCount() {
        Core.addUpperCount();
    }
    public void setBonusPoints(int points) {
        Core.setBonusPoints(points);
    }
    public int getBonusPoints() {
        Core.getGameMode().setScore(Core.getGameMode().getScore() + Core.getBonusPoints());
        return Core.getBonusPoints();
    }
}
