/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import java.util.Random;
import service.domain.DiceLogicIF;

public class DiceLogic implements DiceLogicIF {
    private final Random random;
    
    /** Creates access to dice logic.
     */
    public DiceLogic() {
        random = new Random();
    }
    @Override
    public String newDiceValue() {
        try {
            int min = Core.getGameMode().getMinDiceNum();
            int max = Core.getGameMode().getMaxDiceNum();
            return String.valueOf(random.nextInt(max - min + 1) + min);
        } catch (Exception ex) {
            return "0";
        }
    }
    
    @Override
    public String[] getSelectStatus(String buttonText) {
        String[] status = new String[2];
        if (Core.getGameMode().getLockStatus()) {
            status[0] = "---";
            status[1] = "Locked";
        } else if (buttonText.equals("Select")) {
            status[0] = "Unselect";
            status[1] = "Selected";
        } else {
            status[0] = "Select";
            status[1] = "";
        }
        return status;
    }
}
