/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.List;

public interface SQLNodeIF {
    /** Loads objectives from given folder.
     * @param folder Given database folder name
     */
    void loadObj(String folder);
    /** Loads statistics from given folder.
     * @param folder Given database folder name
     */
    void loadStat(String folder);
    /** Loads statistics from given folder.
     * @param folder Given database folder name
     */
    void loadTopStat(String folder);
    /** Searching passwords from database which matches with given username as a list.
     * @param username Given username
     * @param folder Given database folder name
     * @return Returns passwords as a list
     */
    List<String> getLogin(String username, String folder);
    /** Creates new user to the game.
     * @param username Given username
     * @param password Given password
     * @param folder Given database folder name
     * @return Returns true if user is created, otherwise false
     */
    boolean createUser(String username, String password, String folder);
    /** Gets names of the objectives as a list.
     * @return Returns objective names as a list
     */
    List<String> getObjNames();
    /** Gets requirements of the objectives as a list.
     * @return Returns objective requirements
     */
    List<String> getObjRequirements();
    /** Gets loaded player names.
     * @return Returns player names as a list
     */
    List<String> getStatPlayers();
    /** Gets loaded player scores.
     * @return Returns scores as a String list
     */
    List<String> getStatScores();
    /** Gets loaded gamemode names.
     * @return Returns gamemode names as a list
     */
    List<String> getStatGameModes();
    /** Gets loaded maximum scores depending on gamemode.
     * @return Returns maximum scores as a String list
     */
    List<String> getStatMaxScores();
    /** Gets max score of the game depending on the gamemode.
     * @param objectiveType Given objective type name
     * @param folder Given database folder name
     * @return Returns list of max scores
     */
    String getMaxScore(String objectiveType, String folder);
}
