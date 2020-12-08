/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface StatisticIF {
    /** Gets player name.
     * @return Name of the player
     */
    String getPlayer();
    /** Gets player score.
     * @return Score of the player
     */
    int getScore();
    /** Gets gamemode what player was played.
     * @return Name of the gamemode
     */
    String getGameMode();
    /** Gets maximum score of the gamemode.
     * @return Maximum score
     */
    String getMaxScore();

}
