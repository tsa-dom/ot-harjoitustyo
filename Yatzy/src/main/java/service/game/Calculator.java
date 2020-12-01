/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import java.util.HashSet;
import java.util.List;


/**
 *
 * @author Tapio Salonen
 */
public class Calculator {
    private static HashSet<Integer> calculated;
    private static boolean ready;
    private boolean upperCalculated;
    private int upperPoints;
            
    public Calculator() {
        calculated = new HashSet<>();
        upperPoints = 0;
        upperCalculated = false;
    }
    public int getPoints(Objective objective, List<Integer> dices) {
        int points = 0;
        calculated.clear();
        ready = true;
        String[] requirement = objective.getRequirement().split("Z");
        for (int i = 0; i < requirement.length; i++) {
            points = calculate(requirement[i], points, dices);
            if (customOrNot(requirement[i], points, ready) != -1) {
                return customOrNot(requirement[i], points, ready);
            }
        }
        return 0;
    }
    public int customOrNot(String requirement, int points, boolean ready) {
        if (requirement.equals("m") && ready) {
            return points;
        } else if (requirement.split("/")[0].equals("c") && ready) {
            return Integer.valueOf(requirement.split("/")[1]);
        }
        return -1;
    }
    
    public int calculate(String requirement, int points, List<Integer> dices) {
        if (requirement.substring(0, 1).equals("x")) {
            points += times(requirement.substring(1), dices);
        } else if (requirement.substring(0, 1).equals("y")) {
            points += upperSection(requirement.substring(1), dices);
            upperCountCheck(upperSection(requirement.substring(1), dices));
        } else if (requirement.substring(0, 1).equals("r")) {
            points += random(requirement.substring(1), dices);
        } else if (requirement.substring(0, 1).equals("d")) {
            points += straight(requirement.substring(1), dices);
        }
        return points;
    }
    
    public int times(String requirement, List<Integer> dices) {
        int times = Integer.valueOf(requirement);
        int diceFace = -1;
        int count = 0;
        for (int i = 0; i < dices.size(); i++) {
            if (diceFace == dices.get(i)) {
                count++;
            } else {
                diceFace = dices.get(i);
                count = 1;
            }
            if (count >= times && calculated.contains(diceFace) == false) {
                calculated.add(diceFace);
                return times * diceFace;
            }
        }
        ready = false;
        return 0;
    }
    
    public int upperSection(String requirement, List<Integer> dices) {
        int value = Integer.valueOf(requirement);
        int sum = 0;
        sum = dices.stream().filter((dice) -> (dice == value)).map((item) -> value).reduce(sum, Integer::sum);
        return sum;
    }
    
    public int random(String requirement, List<Integer> dices) {
        int times = Integer.valueOf(requirement);
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum += dices.get(i);
        }
        return sum;
    }
    
    public int straight(String requirement, List<Integer> dices) {
        String[] interval = requirement.split("A");
        int highest = Integer.valueOf(interval[0]);
        int lowest = Integer.valueOf(interval[1]);
        int sum = 0;
        for (int dice : dices) {
            if (highest == dice) {
                sum += highest;
                highest--;
            }
            if (highest == lowest - 1) {
                return sum;
            }
        }
        return 0;
    }
    public void upperCountCheck(int points) {
        upperPoints += points;
        if (upperPoints >= Core.getGameMode().getBonusRequirement()) {
            upperCalculated = true;
        }
    }
    public boolean getUpperStatus() {
        return upperCalculated;
    }
}

