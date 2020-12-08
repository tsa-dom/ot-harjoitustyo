/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import service.domain.StatisticIF;

public class Statistic implements StatisticIF {
    private final String player;
    private final int score;
    private final String gameMode;
    private final String maxScore;
    
    /** Creates new statistic object.
     * @param player Given player
     * @param score Given score as a String
     * @param gameMode Given gamemode name
     * @param maxScore Given max score amount as a String
     */
    public Statistic(String player, int score, String gameMode, String maxScore) {
        this.player = player;
        this.score = score;
        this.gameMode = gameMode;
        this.maxScore = maxScore;
    }
    /** Creates new statistic object.
     * @param player Given player
     * @param score Given score as a String
     */
    public Statistic(String player, int score) {
        this.player = player;
        this.score = score;
        this.gameMode = null;
        this.maxScore = null;
    }
    @Override
    public String getPlayer() {
        return this.player;
    }
    @Override
    public int getScore() {
        return this.score;
    }
    @Override
    public String getGameMode() {
        return this.gameMode;
    }
    @Override
    public String getMaxScore() {
        return this.maxScore;
    }
}
