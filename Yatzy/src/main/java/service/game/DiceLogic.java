/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import java.util.*;
import javafx.scene.control.*;

/**
 *
 * @author Tapio Salonen
 */
public class DiceLogic {
    private final Random random;
    
    public DiceLogic() {
        random = new Random();
    }
    
    public int getDicesSum(Label[] dices) {
        int sum = 0;
        for (Label dice : dices) {
            sum += Integer.valueOf(dice.getText());
        }
        return sum;
    }
    
    public void newDiceValues(Label[] dices, Label[] status) {
        for (int i = 0; i < dices.length; i++) {
            if (status[i].getText().equals("") || dices[i].getText().equals("-")) {
                try {
                    int min = Core.getGameMode().getMinDiceNum();
                    int max = Core.getGameMode().getMaxDiceNum();
                    dices[i].setText(String.valueOf(random.nextInt(max - min + 1) + min));
                } catch (Exception ex) {

                }
            }
        }
    }
    
    public void clearDices(Label[] dices, Label[] status, Button[] select) {
        for (int i = 0; i < dices.length; i++) {
            dices[i].setText("-");
            try {
                select[i].setText("Select");
                status[i].setText("");
            } catch (Exception ex) {
                
            }
        }
    }
    
    public List<Integer> createDiceList(Label[] dices) {
        List<Integer> diceList = new ArrayList<>();
        for (Label dice : dices) {
            diceList.add(Integer.valueOf(dice.getText()));
        }
        Collections.sort(diceList, Collections.reverseOrder());
        return diceList;
    }
}
