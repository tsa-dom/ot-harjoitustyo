/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.List;

public interface ObjectiveLogicIF {
    /** Increase the number of points a player received.
     * @param points Given points
     */
    void addPoints(int points);
    /** Gets score what the player can get.
     * @return Returns points
     */
    int getScore();
    /** Gives points what player could receive depending on chosen objective.
     * @param objective Given objective
     * @param dices Dices as a list
     * @return Returns points what player could receive
     */
    int getPoints(ObjectiveIF objective, List<Integer> dices);
    /** Gets upper section complete status.
     * @return Returns true if upper section is completed, otherwise false
     */
    boolean getUpperStatus();
}
