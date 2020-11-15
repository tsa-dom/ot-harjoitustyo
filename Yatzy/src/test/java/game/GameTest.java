/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio Salonen
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

}

/*
public void setRandom() {
        this.random = new Random();
    }
    
    public void setObjectives() {
        this.classicCalc = new Calculator();
        this.reRollCount = GameManager.currentGameMode.getReRollCount();
        this.objectives = FXCollections.observableArrayList();
        this.objectiveNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM objectives WHERE gamemode='" + GameManager.currentGameMode.getObjectiveType() + "';";
        List<String> names = YatzyUi.databaseManager.selectFrom(sql, "database", "name");
        List<String> requirements = YatzyUi.databaseManager.selectFrom(sql, "database", "requirements");
        Objective.nextId = 0;
        for (int i = 0; i < names.size(); i++) {
            Objective object = new Objective(names.get(i), requirements.get(i));
            this.objectives.add(object);
            if (requirements.get(i).charAt(0) != 'b') {
                this.objectiveNames.add(object);
            }
        }
        Objective.nextId = 0;
    }
    public void setReady(int givenId, int givenPoints) {
        this.objectives.get(givenId).setPoints(String.valueOf(givenPoints));
    }
    public String[] getStatus(String givenText) {
        String[] status = new String[2];
        if (GameManager.currentGameMode.getLockStatus() && openLocks == false) {
            status[0] = "---";
            status[1] = "Locked";
        } else if (givenText.equals("Select")) {
            status[0] = "Unselect";
            status[1] = "Selected";
        } else {
            status[0] = "Select";
            status[1] = "";
        }
        openLocks = false;
        return status;
    }
    public String getDices(String givenStatus, String givenDice) {
        if (givenStatus.equals("") || givenDice.equals("-")) {
            int min = GameManager.currentGameMode.getMinDiceNum();
            int max = GameManager.currentGameMode.getMaxDiceNum();
            return String.valueOf(this.random.nextInt(max - min + 1) + min);
        }
        return givenDice;
    }
    public void endSession() {
        this.objectiveNames.clear();
        this.objectives.clear();
    }
    public int getPoints(Objective givenObjective, Label[] dices) {
        List<Integer> diceList = new ArrayList<>();
        for (int i = 0; i < dices.length; i++) {
            diceList.add(Integer.valueOf(dices[i].getText().subSequence(0, 1).charAt(0)));
        }
        Collections.sort(diceList, Collections.reverseOrder());
        return this.classicCalc.getPoints(givenObjective, diceList);
    }
    public void updateScore() {
        int score = 0;
        for (Objective objective:this.objectives) {
            if (objective.getPoints().equals("---") == false) {
                score += Integer.valueOf(objective.getPoints());
            }
        }
        GameManager.currentGameMode.setScore(score);
    }
    public int numberOfReRolls(int oldCount) {
        reRollCount = GameManager.currentGameMode.getReRollCount();
        if (GameManager.currentGameMode.getStoreStatus()) {
            return GameManager.currentGameMode.getReRollCount() + oldCount;
        }
        return GameManager.currentGameMode.getReRollCount();
    }
    public void executeIfEnd() throws IOException{
        if (this.objectiveNames.isEmpty()) {
            YatzyUi.setRoot("endgame");
        }
    }
*/