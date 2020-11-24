/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

/**
 *
 * @author Tapio Salonen
 */
public class Statistic {
    private final String player;
    private final String score;
    private final String gameMode;
    private final String maxScore;
    
    public Statistic(String player, String score, String gameMode, String maxScore) {
        this.player = player;
        this.score = score;
        this.gameMode = gameMode;
        this.maxScore = maxScore;
    }
    public String getPlayer() {
        return this.player;
    }
    public String getScore() {
        return this.score;
    }
    public String getGameMode() {
        return this.gameMode;
    }
    public String getMaxScore() {
        return this.maxScore;
    }
}
