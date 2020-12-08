/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;
import service.domain.UpperNodeIF;

public class UpperNode implements UpperNodeIF {
    @Override
    public void clearUpperCount() {
        Core.clearUpperCount();
    }
    @Override
    public void addUpperCount() {
        Core.addUpperCount();
    }
    @Override
    public void setBonusPoints(int points) {
        Core.setBonusPoints(points);
    }
    @Override
    public int getBonusPoints() {
        Core.getGameMode().setScore(Core.getGameMode().getScore() + Core.getBonusPoints());
        return Core.getBonusPoints();
    }
}
