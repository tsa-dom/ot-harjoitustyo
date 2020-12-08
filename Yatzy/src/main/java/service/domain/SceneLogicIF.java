/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface SceneLogicIF {
    /** Calculates new number of rolls.
     * @return Returns number of rolls
     */
    int newReRollCount();
    /** Gets status of the reroll as a identifier integer.
     * @return Returns integer that matches correct status
     */
    int getReRollStatus();
    /** Gets number of rerolls.
     * @return Returns number of rerolls
     */
    int getReRollCount();
    /** Gets waiting time what player have to wait until player can reroll again.
     * @return Returns time in milliseconds
     */
    long getWaitingTime();
    /** Decreaces amount of rerolls.
     */
    void upDateReRollCount();
    
}
