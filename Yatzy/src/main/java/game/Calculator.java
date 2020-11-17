/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.*;

/**
 *
 * @author Tapio Salonen
 */
public class Calculator {
    private int upperComplete;
    public Calculator() {
        this.upperComplete = 0;
    }
    public int getPoints(Objective objective, List<Integer> diceList) {
        int points = 0;
        String requirement = objective.getRequirement();
        if (requirement.charAt(0) == 121) {
            points = upperSection(requirement, diceList);
        } else if (requirement.charAt(0) == 100) {
            points = straight(requirement, diceList);
        } else if (requirement.charAt(0) > 48 && requirement.charAt(0) < 58) {
            points = counter(requirement, diceList);
        }
        return points;
    }
    public int upperSection(String requirement, List<Integer> diceList) {
        int count = 0;
        int diceFace = Integer.valueOf(requirement.charAt(1));
        for (int i = 0; i < diceList.size(); i++) {
            if (diceFace == diceList.get(i)) {
                count += diceFace - 48;
            }
        }
        upperComplete++;
        return ifCustomPoints(count, requirement.charAt(2), requirement);
    }
    public int straight(String requirement, List<Integer> diceList) {
        int count = 0;
        int startFace = Integer.valueOf(requirement.charAt(1));
        boolean coming = true;
        for (int i = 0; i < diceList.size(); i++) {
            if (diceList.get(i) == startFace) {
                count += startFace - 48;
                startFace--;
                continue;
            } 
            coming = false;
        }
        if (coming) {
            return ifCustomPoints(count, requirement.charAt(3), requirement);
        }
        return 0;
    }
    public int counter(String requirement, List<Integer> diceList) {
        int count = 0;
        int amount = requirement.charAt(0) - 48;
        boolean sessionEnd = true;
        HashSet<Integer> calculated = new HashSet<>();
        for (int i = 1; i < requirement.length(); i++) {
            if (requirement.charAt(i) == 120) {
                int numberCount = 1;
                for (int j = 0; j < diceList.size() - 1; j++) {
                    if (diceList.get(j) == diceList.get(j + 1)) {
                        numberCount++;
                    } else {
                        numberCount = 1;
                    }
                    if (numberCount == amount && calculated.contains(diceList.get(j)) == false) {
                        count += amount * (diceList.get(j) - 48);
                        calculated.add(diceList.get(j));
                        break;
                    }
                    if (j == diceList.size() - 2) {
                        sessionEnd = false;
                    }
                }
            } else if (requirement.charAt(i) == 114) {
                for (int j = 0; j < amount; j++) {
                    count += diceList.get(j) - 48;
                }
            } else if (requirement.charAt(i) > 48 && requirement.charAt(i) < 58) {
                amount = requirement.charAt(i) - 48;
            } else if (ifCustomPoints(count, requirement.charAt(i), requirement) != -1 && sessionEnd == true) {
                return ifCustomPoints(count, requirement.charAt(i), requirement);
            }
        }
        return 0;
    }
    public int ifCustomPoints(int points, char identifier, String requirement){
        if (identifier == 99) {
            return Integer.valueOf(requirement.split("/")[1]);
        } else if (identifier == 109) {
            return points;
        }
        return -1;
    }
}

