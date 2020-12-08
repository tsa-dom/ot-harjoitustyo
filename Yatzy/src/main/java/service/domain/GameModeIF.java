/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface GameModeIF {
    
    /** Sets new score to the gamemode.
     * @param score Given score 
     */
    void setScore(int score);
    /** Gets score stored to the gamemode.
     * @return Returns score stored to choosen gamemode
     */
    int getScore();
    /** Gets name of the gamemode.
     * @return Returns name of the gamemode
     */
    String getName();
    /** Gets time in milliseconds that player have to wait until player can reroll again.
     * @return Returns Time in milliseconds
     */
    long getReRollTime();
    /** Gets number of rerolls depending on the gamemode.
     * @return Returns number of rerolls
     */
    int getReRollCount();
    /** Gets if unused rolls can be used on the next round.
     * @return Returns true if this option is selected, otherwise false
     */
    boolean getStoreStatus();
    /** Gets minimum number that dice can show to the player.
     * @return Returns minimum number
     */
    int getMinDiceNum();
    /** Gets maximum number that dice can show to the player.
     * @return Returns maximum number
     */
    int getMaxDiceNum();
    /** Gets gamemode objective type.
     * @return Returns objective type as a String
     */
    String getObjectiveType();
    /** Gets controller name.
     * @return Returns controller name
     */
    String getController();
    /** Gets status if dices are locked when selected.
     * @return Returns true if option is selected, otherwise false
     */
    boolean getLockStatus();
    /** Gets required points to get bonus points.
     * @return Returns required points
     */
    int getBonusRequirement();
    
}
